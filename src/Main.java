/**
 * Main class that starts the program
 * 
 * @author Dennis
 *
 */
public class Main {
	private CharacterBuffer charBuff;

	public static void main(String[] args) {
		CharacterBuffer<String> charBuff = new CharacterBuffer<String>();
		GUIMutex test = new GUIMutex(charBuff);
		test.Start();

		Writer writer = new Writer("hej", 3, charBuff);
		writer.start();

		Reader reader = new Reader(5, charBuff);
		reader.start();



}
}
