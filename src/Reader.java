public class Reader implements Runnable {
	private Thread thread;
	private CharacterBuffer buffer;
	private long delay;
	private String txt;

	public Reader(long seconds, CharacterBuffer buffer) {
		this.delay = seconds * 1000;
		this.buffer = buffer;
	}

	public void start() {
		if (thread == null) {
			thread = new Thread(this);
			thread.start();

		}
	}
	public void stop() {
		if (thread != null) {
		thread.interrupt();
		}
	}

	@Override
	public void run() {
		while (!Thread.interrupted()) {
			try {
				Thread.sleep(delay);
				txt = (String) buffer.get();
				System.out.println(txt);

			} catch (InterruptedException e) {
				break;
			}
		}
		thread = null;
	
	}
}

