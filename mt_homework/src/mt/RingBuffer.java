package mt;

public interface RingBuffer {
	int get() throws InterruptedException;

    void put(int value) throws InterruptedException;
}
