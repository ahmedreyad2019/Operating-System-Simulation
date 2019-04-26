package applications;

import memory.Memory;
import process.Dispatcher;

public class Calculator extends process.Process {
	int res;
	int[] memoryLocation;
	int a, c;
	String b;

	public Calculator(int a, String b, int c) {
		super(4, 8, true, false);
		Dispatcher.ready(this);
		int proc_id = this.getProcessId();
		System.out.println(this + "is added to ready queue");
		memoryLocation = Memory.locations(proc_id);
		this.a = a;
		this.b = b;
		this.c = c;
		Memory.getMemory()[memoryLocation[0]].insert(proc_id, a, "int");
		Memory.getMemory()[memoryLocation[1]].insert(proc_id, b, "String");
		Memory.getMemory()[memoryLocation[2]].insert(proc_id, c, "int");
	}

	public void run() {
		int a = (Integer) Memory.getMemory()[memoryLocation[0]].getValue();
		String b = (String) Memory.getMemory()[memoryLocation[1]].getValue();
		int c = (Integer) Memory.getMemory()[memoryLocation[2]].getValue();
		switch (b) {
		case "+":
			res = a + c;
			break;
		case "*":
			res = a * c;
			break;
		case "-":
			res = a - c;
			break;
		case "/":
			res = a / c;
			break;
		}
		Memory.getMemory()[memoryLocation[4]].insert(this.getProcessId(), res, "int");
		this.setInput(false);
		System.out.println("Calculator's output is: " + (Integer) Memory.getMemory()[memoryLocation[4]].getValue());
	}

}
