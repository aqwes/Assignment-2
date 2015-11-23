
import java.util.LinkedList;

/**
 * This is the controller class that deals with all the logic and handles most
 * of the method calls
 * 
 * @author Dennis
 *
 */
public class Controller {
	private CharacterBuffer buffer;
	private GUIMutex gui;
	private char[] chars;
	private LinkedList<Character> charsList;
	private boolean b;
	private Writer writer;
	private Reader reader;
	private String text;
	private String s;

	/**
	 * The constructor instances all the objects
	 * 
	 * @param gui
	 */
	public Controller(GUIMutex gui) {
		this.gui = gui;
	}

	/**
	 * Everytime you press btnrun in in GUIMUTEX, it calls this method. Here we
	 * recevies a message and a decision if we want synchronized or not. Later
	 * on we call CreateWriterAndReaderObject and pass forward the message and
	 * the decision.
	 * 
	 * @param message
	 * @param b
	 */
	public void BtnRun_Click(String message, boolean b) {

		if (message != null) {
			CreateWriterAndReaderObject(message, b);
		}
	}

	/**
	 * Gets a message and a boolean variabel that tells if we want syncronized
	 * or not. Then we make a new buffer, writer and a reader.
	 * 
	 * @param message
	 * @param b
	 */
	public void CreateWriterAndReaderObject(String message, boolean b) {
		this.b = b;
		this.chars = message.toCharArray();
		this.text = message;
		s = "";
		charsList = new LinkedList<Character>();
		buffer = new CharacterBuffer();
		writer = new Writer(buffer, chars, b, this);
		reader = new Reader(buffer, b, this);
		writer.start();
		reader.start();
	}

	/**
	 * This method updates the writelogger in GUIMutex.
	 * 
	 * @param c
	 */
	public void updateWriterLogg(char c) {
		gui.updateWriterLogg("Writing " + c);
	}

	/**
	 * This method returns all the values in the linkedlist (readCharlist)
	 * 
	 * @return
	 */
	public String getRecText() {
		s = "";
		for (char c : charsList) {
			s += c;
		}
		return s;
	}

	/**
	 * This method updates the reader and adds chars to a linkedlist. While the
	 * list is smaller then the textlength we keep on adding chars to the list
	 * until they are the same size. When that happens, the writer interrupts
	 * and the reader stops. Then we call the finalReaderLogger.
	 * 
	 * @param c
	 */
	public void updateReader(char c) {
		if (charsList.size() < text.length()) {
			charsList.add(new Character(c));
			gui.updateReaderLogg("Reading " + c);
			gui.setRecText(getRecText());

			if (charsList.size() == text.length()) {
				writer.interrupt();
				reader.stop();
				finalReaderLogger();
			}
		}
	}

	/**
	 * This method checks if we received the same text that we wrote in correct
	 * order.
	 */
	public void finalReaderLogger() {
		if (b == false && !s.equals(text)) {
			gui.matching(false);

		} else if (b == true && s.equals(text)) {
			gui.matching(true);
		}
	}
}
