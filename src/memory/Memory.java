package memory;

public class Memory {
	private static int size;// size of the memory
	private static Word[] memory;// a memory of words

	public float utilization() {
		return (size - getFreeSpace()) / size;
	}

	// initializes a new memory with specified size
	public Memory() {
		Memory.size = 256;
		memory = new Word[size];
		for (int i = 0; i < memory.length; i++)
			memory[i] = new Word();
	}

	public static Word[] getMemory() {
		return memory;
	}

	public static void setMemory(Word[] memory) {
		Memory.memory = memory;
	}

	// returns the size of the memory
	public static int getSize() {
		return size;
	}

	// sets the size of the memory
	public static void setSize(int size) {
		Memory.size = size;
	}

	// finds locations reserved for a certain process
	public static int[] locations(int processid) {
		int[] loc = new int[20];
		int i = 0;
		for (int j = 0; j < memory.length; j++)
			if (memory[j].getProcess_id() == processid) {
				loc[i] = j;
				i++;
			}
		return loc;

	}

	// returns a word given a specific location
	public Word getword(int location) {
		return memory[location];
	}

	// deletes a word given a specific location
	public void deletebylocation(int location) {
		memory[location].delete();
	}

	// deletes a word given its process and user ids
	public static void delete(int processid) {
		for (Word w : memory) {
			if (!w.isEmpty() && w.getProcess_id() == processid) {
				w.delete();
				w.setEmpty(true);
			}
		}
	}

	// allocates memory locations for a process given its processid and number of
	// locations it needs
	public static void create(int numOfLocations, int processid) {
		int i = 0;
		for (int j = 0; j < numOfLocations; j++) {
			while (i < memory.length) {
				Word word = memory[i];
				i++;
				if (word.isEmpty()) {
					word.create(processid);
					break;
				}
			}
		}
	}

	// insert into memory location, the process id, value and its type
	public void insert(int location, int processid, Object value, String type) {
		if (memory[location].isEmpty())
			memory[location].insert(processid, value, type);
	}

	// clears the memory
	public void clear() {
		for (Word w : memory)
			w.delete();
	}

	public String toString() {
		String s = "";
		for (int i = 0; i < memory.length; i++)
			s += (i > 15 ? Integer.toHexString(i) : Integer.toHexString(i) + " ") + ": " + memory[i].toString() + "\n";
		return s;
	}

	// get the free space of the memory as number of empty words
	public static int getFreeSpace() {
		int i = 0;
		for (Word w : memory)
			if (w.isEmpty())
				i++;
		return i;
	}
}