package Networking;

public class Thread1 implements Runnable {

	@Override
	public void run() {
		// TODO Auto-generated method stub

		for (int i = 0; i < 40; i++) {
			String threadName = Thread.currentThread().getName();
			System.out.println(threadName + "/" + i);
			
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Thread1 runner = new Thread1();
		Thread alpha = new Thread(runner);
		Thread beta = new Thread(runner);

		alpha.setName("Alpha");
		beta.setName("Beta");
		
		alpha.start();
		beta.start();

	}

}
