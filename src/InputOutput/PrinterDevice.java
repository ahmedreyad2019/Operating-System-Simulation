package InputOutput;

import semaphores.BinarySemaphore;

import semaphores.Semaphore;

import java.util.concurrent.TimeUnit;

public class PrinterDevice {

	public static Semaphore s = new Semaphore(1);
	public static BinarySemaphore bs = new BinarySemaphore();

	public static void print(Object a) {
		try {
			TimeUnit.SECONDS.sleep(5);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(a);
	}
}
