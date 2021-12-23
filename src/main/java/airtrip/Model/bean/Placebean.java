package airtrip.Model.bean;

public class Placebean {

	private long placeId;
	private String name;
	private String image[];
	private String address;
	private long price;
	private String phone;
	private String startDay;
	private String endDay;
	private String detail;
	private Boolean isEmpty;
	private String litleCategoryId;
	private DetailPlacebean detailId;
	private Accountbean account;
	
	public Placebean() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Placebean(long placeId, String name, String[] image, String address, long price, String phone,
			String startDay, String endDay, String detail, Boolean isEmpty, String litleCategoryId,
			DetailPlacebean detailId, Accountbean account) {
		super();
		this.placeId = placeId;
		this.name = name;
		this.image = image;
		this.address = address;
		this.price = price;
		this.phone = phone;
		this.startDay = startDay;
		this.endDay = endDay;
		this.detail = detail;
		this.isEmpty = isEmpty;
		this.litleCategoryId = litleCategoryId;
		this.detailId = detailId;
		this.account = account;
	}
	
	public Placebean(String name, String[] image, String address, long price, String phone,
			String startDay, String endDay, String detail, Boolean isEmpty, String litleCategoryId,
			DetailPlacebean detailId, Accountbean account) {
		super();
		this.name = name;
		this.image = image;
		this.address = address;
		this.price = price;
		this.phone = phone;
		this.startDay = startDay;
		this.endDay = endDay;
		this.detail = detail;
		this.isEmpty = isEmpty;
		this.litleCategoryId = litleCategoryId;
		this.detailId = detailId;
		this.account = account;
	}

	public long getPlaceId() {
		return placeId;
	}

	public void setPlaceId(long placeId) {
		this.placeId = placeId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String[] getImage() {
		return image;
	}

	public void setImage(String[] image) {
		this.image = image;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public long getPrice() {
		return price;
	}

	public void setPrice(long price) {
		this.price = price;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getStartDay() {
		return startDay;
	}

	public void setStartDay(String startDay) {
		this.startDay = startDay;
	}

	public String getEndDay() {
		return endDay;
	}

	public void setEndDay(String endDay) {
		this.endDay = endDay;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public Boolean getIsEmpty() {
		return isEmpty;
	}

	public void setIsEmpty(Boolean isEmpty) {
		this.isEmpty = isEmpty;
	}

	public String getLitleCategoryId() {
		return litleCategoryId;
	}

	public void setLitleCategoryId(String litleCategoryId) {
		this.litleCategoryId = litleCategoryId;
	}

	public DetailPlacebean getDetailId() {
		return detailId;
	}

	public void setDetailId(DetailPlacebean detailId) {
		this.detailId = detailId;
	}
	public Accountbean getaccount() {
		return account;
	}

	public void setaccount(Accountbean account) {
		this.account = account;
	}
}
