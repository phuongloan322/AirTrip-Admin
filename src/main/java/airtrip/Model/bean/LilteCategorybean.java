package airtrip.Model.bean;

public class LilteCategorybean {

	private String litleCategoryId;
	private String litleName;
	private String detail;
	private String categoryId;
	public LilteCategorybean() {
		super();
		// TODO Auto-generated constructor stub
	}
	public LilteCategorybean(String litleCategoryId, String litleName, String detail, String categoryId) {
		super();
		this.litleCategoryId = litleCategoryId;
		this.litleName = litleName;
		this.detail = detail;
		this.categoryId = categoryId;
	}
	public String getLitleCategoryId() {
		return litleCategoryId;
	}
	public void setLitleCategoryId(String litleCategoryId) {
		this.litleCategoryId = litleCategoryId;
	}
	public String getLitleName() {
		return litleName;
	}
	public void setLitleName(String litleName) {
		this.litleName = litleName;
	}
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	public String getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}
	
	
	
}
