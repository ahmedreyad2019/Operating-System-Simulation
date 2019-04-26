package process;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.TimeUnit;

public class Dispatcher {
	private static Queue<Process> Ready_Queue = new LinkedList<Process>();
	private static Queue<Process> Blocked_Queue = new LinkedList<Process>();
	private static Queue<Process> Running_Queue = new LinkedList<Process>();
	private static Queue<Process> Terminated_Queue = new LinkedList<Process>();
	private static final int maxTime = 2;

	public static Queue<Process> getReady_Queue() {
		return Ready_Queue;
	}

	public static void setReady_Queue(Queue<Process> ready_Queue) {
		Ready_Queue = ready_Queue;
	}

	public static Queue<Process> getBlocked_Queue() {
		return Blocked_Queue;
	}

	public static void setBlocked_Queue(Queue<Process> blocked_Queue) {
		Blocked_Queue = blocked_Queue;
	}

	public static Queue<Process> getRunning_Queue() {
		return Running_Queue;
	}

	public static void setRunning_Queue(Queue<Process> running_Queue) {
		Running_Queue = running_Queue;
	}

	public static Queue<Process> getTerminated_Queue() {
		return Terminated_Queue;
	}

	public static void setTerminated_Queue(Queue<Process> terminated_Queue) {
		Terminated_Queue = terminated_Queue;
	}

	public static float avg = 0;
	public static int count = 0;

	public static void ready(Process p) {
		Ready_Queue.add(p);
	}

	public static void block(Process p) {
		Blocked_Queue.add(p);
	}

	public static float gettime(LocalTime a, LocalTime b) {
		float q = 0;
		q += (a.getHour() - b.getHour()) * 3600;
		q += (a.getMinute() - b.getMinute()) * 60;
		q += (a.getSecond() - b.getSecond());
		return q;
	}

	@SuppressWarnings("unlikely-arg-type")
	public static void run() {

		ArrayList<Integer> al = new ArrayList<>();
		ArrayList<Integer> term = new ArrayList<>();
		while (true) {
			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
			if (!Ready_Queue.isEmpty() && Running_Queue.isEmpty()) {
				Process p1 = Ready_Queue.remove();
				p1.setState(ProcessState.RUNNING);
				Running_Queue.add(p1);
				if (al.contains(p1))
					System.out.println(p1 + " added to  running queue");
				else
					System.out.println(p1 + " added to ready queue");
			}
			System.out.println(".");
			if (!Blocked_Queue.isEmpty()) {
				Process p2 = Blocked_Queue.peek();
				if (!p2.isOutput() && !p2.isInput()) {
					Ready_Queue.add(Blocked_Queue.remove());
					p2.setState(ProcessState.READY);
					System.out.println(p2 + "is added to  ready queue!!");
				}
			}
			if (!Running_Queue.isEmpty()) {
				Process p = Running_Queue.peek();
				System.out.println(p + " is running");
				try {
					TimeUnit.SECONDS.sleep(Math.min(p.getTime(), maxTime));

				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				p.setTime(p.getTime() - maxTime);
				if (p.getTime() <= 0) {
					p.endtime = LocalTime.now();
					count++;
					avg = avg * (count - 1) / count + (gettime(p.endtime, p.starttime)) / count;
					Running_Queue.remove().terminate();
					term.add(p.getProcessId());
					System.out.println(p + " has finished");
				}
				if (p.isInput() && !al.contains(p.getProcessId())) {
					p.setState(ProcessState.BLOCKED);
					System.out.println(p + " is blocked waiting for input");
					if (!Running_Queue.isEmpty())
						Blocked_Queue.add(Running_Queue.remove());
					CPU.run(p);
					al.add(p.getProcessId());
				}
				if (p.isOutput() && !al.contains(p.getProcessId())) {
					p.setState(ProcessState.BLOCKED);
					if (!Running_Queue.isEmpty())
						Blocked_Queue.add(Running_Queue.remove());
					System.out.println(p + " is blocked waiting for output");
					CPU.run(p);
					al.add(p.getProcessId());
				}
				if (!p.isInput() && !p.isOutput() && !al.contains(p.getProcessId()) && !Running_Queue.isEmpty()) {
					CPU.run(p);
					al.add(p.getProcessId());
					Ready_Queue.add(Running_Queue.remove());
				}
				if (!p.isInput() && !p.isOutput() && al.contains(p.getProcessId())
						&& !term.contains(p.getProcessId())) {
					if (!Running_Queue.isEmpty())
						Ready_Queue.add(Running_Queue.remove());
				}
			}
		}
	}
}