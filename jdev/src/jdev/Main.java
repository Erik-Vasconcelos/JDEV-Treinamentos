package jdev;

public class Main {
	public static void main(String[] args) {
		/*Thread thread = new Thread(() ->{
			while(true) {
				try {
					Thread.sleep(5000);
					
					System.out.println("Passou");
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			
		});
		
		thread.start();*/
		
		new Thread() {
			public void run() {}
				
			;
		}.start();
		
		System.out.println("Terminou!!");
	}

}
