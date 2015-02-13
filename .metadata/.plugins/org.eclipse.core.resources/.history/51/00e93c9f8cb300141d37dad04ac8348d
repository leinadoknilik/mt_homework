package mt;

public class RightRingBuffer implements RingBuffer {

	private final int[] buffer;
	private volatile long readCount;
	private volatile long writeCount;

	private static final Object guard = new Object();

	public RightRingBuffer(int size) {
		buffer = new int[size];
	}
	
	@Override
	public void put(int value) throws InterruptedException {
		synchronized (guard) {
			while (writeCount >= readCount + buffer.length) {
				guard.wait();
			}

			int writeIndex = (int) (writeCount % buffer.length);
			buffer[writeIndex] = value;
			writeCount++;

			if (readCount < writeCount) {
				guard.notifyAll();
			}
		}
	}

	@Override
	public int get() throws InterruptedException {
		synchronized (guard) {
			while (readCount >= writeCount) {
				guard.wait();
			}

			int readIndex = (int) (readCount % buffer.length);
			readCount++;
			if (writeCount < readCount + buffer.length) {
				guard.notifyAll();
			}
			return buffer[readIndex];
		}
	}


}
