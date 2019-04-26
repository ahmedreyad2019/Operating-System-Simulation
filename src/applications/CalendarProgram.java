package applications;

import java.util.Calendar;

import InputOutput.PrinterDevice;
import memory.Memory;
import process.Dispatcher;

public class CalendarProgram extends process.Process {
	int[] memoryLocation;

	public CalendarProgram() {
		super(2, 8, false, true);
		Dispatcher.ready(this);
		int proc_id = this.getProcessId();
		System.out.println(this + "is added to ready queue");
		memoryLocation = Memory.locations(proc_id);
		Memory.getMemory()[memoryLocation[0]].insert(proc_id, "The Current Time is: ", "String");
		Memory.getMemory()[memoryLocation[1]].insert(proc_id, cal.getTime(), "datetime");
	}

	Calendar cal = Calendar.getInstance();
	int currentSecond, currentMinute, currentHour, currentDay, currenMonth, currentYear;
	int currentSecondlocation, currentMinuteLocation, currenHourLocation, currentDayLocation, currenMonthLocation,
			currentYearLocation;

	// *Return current Date&time
	public void getDate() {
		PrinterDevice.print(Memory.getMemory()[memoryLocation[0]].getValue());
		PrinterDevice.print(Memory.getMemory()[memoryLocation[1]].getValue());
	}

	/* create an alarm for today */
	public void setCurrentDate(int dayInput, int monthInput, int yearInput) {
		cal.set(Calendar.YEAR, yearInput);
		cal.set(Calendar.MONTH, monthInput);
		cal.set(Calendar.DAY_OF_MONTH, dayInput);
	}

	public void run() {
		if (PrinterDevice.s.count <= 0) {
			System.out.println(this + " w8ing for process to release printer");
			while (true) {
				System.out.print("");
				if (PrinterDevice.s.count > 0) {
					PrinterDevice.s.count -= 1;
					break;
				}
			}
		} else {
			System.out.println(this + " accsesd a critical resource(printer)");
			PrinterDevice.s.count -= 1;
		}
		PrinterDevice.print(Memory.getMemory()[memoryLocation[1]].getValue());
		System.out.println(this + " realeased printer");
		PrinterDevice.s.count = 1;

		this.setOutput(false);
		this.setCurrentDate(28, 1, 1970);
	}
}