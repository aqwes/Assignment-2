/**
 * Main class that starts the program
 * 
 * @author Dennis
 *
 */
public class Main {
	public static void main(String[] args) {
		GUIMutex gui = new GUIMutex();
		Controller controller = new Controller(gui);
		gui.Start(controller);
}
}
