public class Writer extends Thread {
	private CharacterBuffer<String> buffer;
	private long delay;
	private String message;

	public Writer(String message, long seconds, CharacterBuffer<String> buffer) {
		this.buffer = buffer;
		this.delay = seconds * 1000;
		this.message = message;
	}


	public void run(){
		while (!Thread.interrupted() && message != null) {
			try{
				Thread.sleep(delay);
				buffer.put(message);
			}
 catch (InterruptedException e) {
				break;
			}
        	}
        }
	}

