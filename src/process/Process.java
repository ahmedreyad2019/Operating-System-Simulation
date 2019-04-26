package process;

import java.time.LocalTime;
import memory.Memory;
import semaphores.BinarySemaphore;
import semaphores.BinarySemaphore.values;
import semaphores.Semaphore;

public abstract class Process extends Thread {
	static int globalprocid = 0;
	private int processId;
	int numOfLocations;
	public LocalTime starttime;
	public LocalTime endtime;
	ProcessState state; // the process state
	private int remainingTime;
	private boolean input;// new
	private boolean output;// new

	// constructor
	public Process(int numOfLocations, int time, boolean input, boolean output) {
		starttime = LocalTime.now();
		processId = ++globalprocid;
		this.numOfLocations = numOfLocations;
		state = ProcessState.NEW;
		this.remainingTime = time;
		this.input = input;
		this.output = output;
		create();
	}

	public int getTime() {
		return remainingTime;
	}

	public void setTime(int time) {
		this.remainingTime = time;
	}

	public void create() {
		if (Memory.getFreeSpace() >= numOfLocations) {
			Memory.create(numOfLocations, processId);
			setState(ProcessState.READY);
		} else {
			System.out.print("BLUE SCREEN OF DEATH");
			System.exit(1);
		}
	}

	public void terminate() {
		setState(ProcessState.TERMINATED);
		Memory.delete(processId);
		;
	}

	public String toString() {
		return processId + " " + getClass().getSimpleName();
	}

	public int getProcessId() {
		return processId;
	}

	public ProcessState getStatee() {
		return state;
	}

	public void setState(ProcessState state) {
		this.state = state;
	}

	public int getSize() {
		return numOfLocations;
	}

	public void semWait(Semaphore s) {
		s.count--;
	}

	public void semSignal(Semaphore s) {
		s.count++;
	}

	public void semSignalB(BinarySemaphore s) {
		if (s.getQueue().isEmpty()) {
			s.value = values.one;
		} else {
			Dispatcher.ready(s.getQueue().remove());
		}
	}

	public boolean isInput() {
		return input;
	}

	public void setInput(boolean input) {
		this.input = input;
	}

	public boolean isOutput() {
		return output;
	}

	public void setOutput(boolean output) {
		this.output = output;
	}

	public void semWaitB(BinarySemaphore s) {
		if (s.value == values.one) {
			s.value = values.zero;
			Dispatcher.ready(this);
		} else {
			s.getQueue().add(this);
			this.setState(ProcessState.BLOCKED);
		}
	}

	public abstract void run();
}