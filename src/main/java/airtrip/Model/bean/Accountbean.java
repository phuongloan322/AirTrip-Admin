package airtrip.Model.bean;

public class Accountbean {
	private long accountId;
	private String name;
	private String address;
	private String phone;
	private String email;
	private String username;
	private String password;
	private String image;
	public Accountbean() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Accountbean(long accountId, String name, String address, String phone, String email, String username,
			String password, String image) {
		super();
		this.accountId = accountId;
		this.name = name;
		this.address = address;
		this.phone = phone;
		this.email = email;
		this.username = username;
		this.password = password;
		this.image = image;
	}
	
	public Accountbean(String name, String address, String phone, String email, String username,
			String password,String image) {
		super();
		this.name = name;
		this.address = address;
		this.phone = phone;
		this.email = email;
		this.username = username;
		this.password = password;
		this.image = image;
	}
	public long getAccountId() {
		return accountId;
	}
	public void setAccountId(long accountId) {
		this.accountId = accountId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	
}
