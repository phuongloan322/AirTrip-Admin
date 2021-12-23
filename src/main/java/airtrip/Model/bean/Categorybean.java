package airtrip.Model.bean;

public class Categorybean {

	private String categoryId;
	private String name;
	public Categorybean() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Categorybean(String categoryId, String name) {
		super();
		this.categoryId = categoryId;
		this.name = name;
	}
	public String getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
}
