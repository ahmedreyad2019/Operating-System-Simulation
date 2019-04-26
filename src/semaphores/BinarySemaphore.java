package semaphores;

import java.util.LinkedList;
import java.util.Queue;

import process.Process;

public class BinarySemaphore {
	public enum values {
		zero, one
	};

	public values value;
	private Queue<Process> queue;

	public BinarySemaphore() {
		setQueue(new LinkedList<Process>());
		value = values.one;
	}

	public Queue<Process> getQueue() {
		return queue;
	}

	public void setQueue(Queue<Process> queue) {
		this.queue = queue;
	}
}
