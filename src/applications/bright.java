package applications;

import memory.Memory;
import process.Dispatcher;

public class bright extends process.Process {
	int[] memoryLocation;

	public bright() {
		super(1, 8, false, false);
		Dispatcher.ready(this);
		int proc_id = this.getProcessId();
		System.out.println(this + "is added to ready queue");
		memoryLocation = Memory.locations(proc_id);
		Memory.getMemory()[memoryLocation[0]].insert(proc_id, "birght up", "String");
	}

	public void run() {
	}
}
