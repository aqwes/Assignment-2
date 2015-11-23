import java.util.Observable;

/**
 * This class handles the chars
 * 
 * @author Dennis
 *
 */
public class CharacterBuffer extends Observable {
	private char c;
	private boolean read = true;

	/**
	 * Regular put method
	 * 
	 * @param c1
	 */
	public void put(char c1) {
		this.c = c1;
	}

	/**
	 * Regular get method
	 * 
	 * @param c
	 */
	public char get() {
		return c;
	}

	/**
	 * This method is used when we want to add synchronized chars
	 * 
	 * @param c2
	 */
	public void putSync(char c2) {

		this.c = c2;
		read = false;
		setChanged();
		notifyObservers();

	}

	public boolean read() {
		return read;
	}

	/**
	 * This method is used when we want to get the synchronized chars
	 * 
	 * @param c2
	 */
	public char getSyncReadWrite() {
		read = true;
		setChanged();
		notifyObservers();
		return c;
	}

}
