package airtrip.Model.bean;

public class Reviewbean {

	private long reviewId;
	private int rate;
	private String content;
	private String dateSubmit;
	private long placeId;
	private Accountbean account;
	public Reviewbean() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Reviewbean(long reviewId, int rate, String content, String dateSubmit, long placeId, Accountbean account) {
		super();
		this.reviewId = reviewId;
		this.rate = rate;
		this.content = content;
		this.dateSubmit = dateSubmit;
		this.placeId = placeId;
		this.account = account;
	}
	public long getReviewId() {
		return reviewId;
	}
	public void setReviewId(long reviewId) {
		this.reviewId = reviewId;
	}
	public int getRate() {
		return rate;
	}
	public void setRate(int rate) {
		this.rate = rate;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getDateSubmit() {
		return dateSubmit;
	}
	public void setDateSubmit(String dateSubmit) {
		this.dateSubmit = dateSubmit;
	}
	public long getPlaceId() {
		return placeId;
	}
	public void setPlaceId(long placeId) {
		this.placeId = placeId;
	}
	public Accountbean getAccount() {
		return account;
	}
	public void setAccount(Accountbean account) {
		this.account = account;
	}
	
	
	
}
