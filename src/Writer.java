
import java.util.Observable;
import java.util.Observer;
import java.util.Random;

/**
 * This class writes data to the characterBuffe
 * 
 * @author Dennis
 *
 */
public class Writer extends Thread implements Observer {
	private CharacterBuffer buffer;
	private Random rand;
	private boolean sync;
	private char[] charArray;
	private Controller controller;


	/**
	 * The constructor instances all the objects
	 * 
	 * @param buffer
	 * @param chars
	 * @param b
	 * @param controller
	 */
	public Writer(CharacterBuffer buffer, char[] chars, boolean b, Controller controller) {
		this.buffer = buffer;
		this.charArray = chars;
		this.sync = b;
		this.controller = controller;
		buffer.addObserver(this);
		rand = new Random();
	}

	/**
	 * This method writes to the buffer. Depending on sync or not we calls
	 * different put-methods in the buffer.
	 */
	@Override
	public void run() {
		try {
			for (char c : charArray) {
				Thread.sleep(rand.nextInt(300) + 100);
				controller.updateWriterLogg(c);
				if (sync == true) {
					synchronized (this) {
						buffer.putSync(c);
						this.wait();
						
					}
				} else if (sync == false) {
					buffer.put(c);
				}
			}
		} catch (InterruptedException e) {
		}
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
