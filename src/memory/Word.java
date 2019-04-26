package memory;

public class Word {
	int userid;
	int processid;
	Object value;
	String type;
	int isEmpty = 1;

	// checks whether if the word is empty or not
	public boolean isEmpty() {
		if (userid == 0 && processid == 0)
			return true;
		else
			return false;
	}

	public void setEmpty(boolean isEmpty) {
		if (isEmpty)
			this.isEmpty = 1;
		else
			this.isEmpty = 0;
	}

	public Word() {
		this.processid = 0;
		this.userid = 0;
		this.value = 0;
		this.isEmpty = 1;
	}

	public void create(int processid) {
		this.processid = processid;
		setEmpty(false);
	}

	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public int getProcess_id() {
		return processid;
	}

	public void setProcess_id(int process_id) {
		this.processid = process_id;
	}

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}

	public void insert(int processid, Object value, String type) {
		this.processid = processid;
		this.type = type;
		this.value = value;
		setEmpty(false);
	}

	public void delete() {
		processid = 0;
		userid = 0;
		value = 0;
		type = null;
		setEmpty(true);
	}

	public String toString() {
		String s = "[";
		s += (userid);
		s += "|" + processid;
		s += "|" + ((value));
		s += "|" + (type);
		s += "|" + (isEmpty);
		s += "]";
		return s;
	}
}