package bean;

public class Student {
	private int id;
	private String username;
	private String password;
	private String name;
	private String item;

	public Student(String username, String password, String name, String item) {
		super();
		this.username = username;
		this.password = password;
		this.item = item;
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getItem() {
		return item;
	}

	public void setItem(String item) {
		this.item = item;
	}
	
}
