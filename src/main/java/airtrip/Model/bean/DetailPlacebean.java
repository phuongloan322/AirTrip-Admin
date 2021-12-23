package airtrip.Model.bean;

public class DetailPlacebean {

	private long detailId;
	private int phongkhach;
	private int phongngu;
	private int giuong;
	private int phongvs;
	private String tienich[];
	
	public DetailPlacebean() {
		super();
		// TODO Auto-generated constructor stub
	}

	public DetailPlacebean(long detailId, int phongkhach, int phongngu, int giuong, int phongvs, String[] tienich) {
		super();
		this.detailId = detailId;
		this.phongkhach = phongkhach;
		this.phongngu = phongngu;
		this.giuong = giuong;
		this.phongvs = phongvs;
		this.tienich = tienich;
	}
	
	public DetailPlacebean( int phongkhach, int phongngu, int giuong, int phongvs, String[] tienich) {
		super();
		this.phongkhach = phongkhach;
		this.phongngu = phongngu;
		this.giuong = giuong;
		this.phongvs = phongvs;
		this.tienich = tienich;
	}

	public long getDetailId() {
		return detailId;
	}

	public void setDetailId(long detailId) {
		this.detailId = detailId;
	}

	public int getPhongkhach() {
		return phongkhach;
	}

	public void setPhongkhach(int phongkhach) {
		this.phongkhach = phongkhach;
	}

	public int getPhongngu() {
		return phongngu;
	}

	public void setPhongngu(int phongngu) {
		this.phongngu = phongngu;
	}

	public int getGiuong() {
		return giuong;
	}

	public void setGiuong(int giuong) {
		this.giuong = giuong;
	}

	public int getPhongvs() {
		return phongvs;
	}

	public void setPhongvs(int phongvs) {
		this.phongvs = phongvs;
	}

	public String[] getTienich() {
		return tienich;
	}

	public void setTienich(String[] tienich) {
		this.tienich = tienich;
	}
	
	
}
