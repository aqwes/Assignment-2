import java.util.LinkedList;

public class CharacterBuffer<T> {

	private LinkedList<T> buffer = new LinkedList<T>();
	public CharacterBuffer() {
	
}
	public synchronized void put(T obj) {
		buffer.addLast(obj);
		notifyAll();
	}

	public synchronized T get() throws InterruptedException {
		while (buffer.isEmpty()) {
			System.out.println(Thread.currentThread() + " is waiting");
			wait();
		}
		return buffer.removeFirst();
	}

	public synchronized void clear() {
		buffer.clear();
	}

	public int size() {
		return buffer.size();
	}

	public void putUN(T obj) {
		buffer.addLast(obj);
	}

	public T get1() {
		return buffer.removeFirst();
	}
}
