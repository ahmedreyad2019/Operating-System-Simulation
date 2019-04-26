package semaphores;

import java.util.LinkedList;
import java.util.Queue;

import process.Process;

public class Semaphore {
	public int count;
	private Queue<Process> queue;

	public Semaphore(int count) {
		this.count = count;
		setQueue(new LinkedList<Process>());
	}

	public Queue<Process> getQueue() {
		return queue;
	}

	public void setQueue(Queue<Process> queue) {
		this.queue = queue;
	}

}
