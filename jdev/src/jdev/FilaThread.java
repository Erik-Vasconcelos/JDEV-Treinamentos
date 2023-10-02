package jdev;

import java.util.Iterator;
import java.util.concurrent.ConcurrentLinkedQueue;

public class FilaThread extends Thread {

	private static ConcurrentLinkedQueue<Email> fila = new ConcurrentLinkedQueue<>();

	public static void add(Email email) {
		fila.add(email);
	}

	@Override
	public void run() {

		while (true) {
			synchronized (fila) {
				
				Iterator<Email> iterator = fila.iterator();
				while (iterator.hasNext()) {

					Email processar = iterator.next();

					System.out.println("------------ Processado email -------------");
					System.out.println(processar.getEmail());

					iterator.remove();

					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}

			}

			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}
	}

}
