import java.util.Observable;
import java.util.Observer;
import java.util.Random;

/**
 * This class reads data from the buffer
 * 
 * @author Dennis
 */
public class Reader implements Runnable, Observer {
	private CharacterBuffer buffer;
	private Controller controller;
	private Random rand;
	private boolean sync;
	private Thread thread;

	/**
	 * The constructor receives some object, creates a new random object and
	 * adds this class a a observer to the characterbuffer
	 * 
	 * @param buffer
	 * @param b
	 * @param controller
	 */
	public Reader(CharacterBuffer buffer, boolean b, Controller controller) {
		this.buffer = buffer;
		this.sync = b;
		this.controller = controller;
		buffer.addObserver(this);
		rand = new Random();

	}
	/**
	 * Start the thread
	 */
	public void start() {
		if (thread == null) {
			thread = new Thread(this);
			thread.start();
		}
	}

	/**
	 * Stops the thread
	 */
	public void stop() {
		if (thread != null) {
			thread.interrupt();
		}
	}

	/**
	 * This values comes from the GUIMUTEX and decides if we want sync or none
	 * sync.
	 * 
	 * @return
	 */
	public boolean Sync() {
		return sync;

	}

	/**
	 * In this method we get the char from the buffer and then we pass it
	 * forward to the controller.
	 */
	public void run() {
		while (!Thread.interrupted() && thread != null) {
			try {
				Thread.sleep(rand.nextInt(500) + 100);
				if (sync == true) {
					synchronized (this) {
						char c = buffer.getSyncReadWrite();

						if (c != '\0') {
							controller.updateReader(c);

						}
						this.wait();
					}
				} else if (sync == false) {
					char c = buffer.get();
					if (c != '\0') {
						controller.updateReader(c);
					}
				}
			} catch (InterruptedException e) {
				break;
			}
		}
		thread = null;
	}

	/**
	 * Notifies the observers
	 */
	@Override
	public void update(Observable o, Object arg) {
		synchronized (this) {
			notifyAll();

		}
	}
}
