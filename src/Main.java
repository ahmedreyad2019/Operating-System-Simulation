import memory.Memory;
import process.Dispatcher;

public class Main {
	static TaskManager a;
	static Memory m;

	public static void main(String[] args) throws InterruptedException {
		m = new Memory();
		new Desktop(m);
		Dispatcher.run();
	}
}