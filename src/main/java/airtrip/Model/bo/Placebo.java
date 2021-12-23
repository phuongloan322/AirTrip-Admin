package airtrip.Model.bo;

import java.util.List;

import airtrip.Model.bean.DetailPlacebean;
import airtrip.Model.bean.Placebean;
import airtrip.Model.dao.Placedao;

public class Placebo {

	Placedao placeDao = new Placedao();
	public List<Placebean> getAllPlace() throws Exception {
		return placeDao.getAllPlace();
	}
	
	public List<Placebean> getPlaceByPaging(int PageNumber, int PageSize) throws Exception {
		return placeDao.getPlaceByPaging(PageNumber, PageSize);
	}
	
	public Placebean getPlaceId(long placeId) throws Exception {
		return placeDao.findById(placeId);
	}
	
	public List<Placebean> getPlaceByAccId(long accountId) throws Exception {
		return placeDao.getPlaceByAccId(accountId);
	}
	
	public List<Placebean> getAllPlace(String search) throws Exception {
		return placeDao.getAllPlace(search);
	}
	
	public Placebean findById(long id) throws Exception {
		return placeDao.findById(id);
	}
	
	public int AddDetailPlace(DetailPlacebean detailPlace) throws Exception {
		return placeDao.AddDetailPlace(detailPlace);
	}
	
	public long GetIDLastDeatilPlace() throws Exception {
		return placeDao.GetIDLastDeatilPlace();
	}
	
	public int AddPlace(Placebean placebean) throws Exception {
		return placeDao.AddPlace(placebean);
	}
	
	public int DeletePlace(long placeId) throws Exception {
		return placeDao.DeletePlace(placeId);
	}
	
	public int DeleteAllPlace(long accountId) throws Exception {
		return placeDao.DeleteAllPlace(accountId);
	}
	
	public int EditPlace(boolean isEmpty, long placeId) throws Exception {
		return placeDao.EditPlace(isEmpty, placeId);
	}
	
	public int EditDetailPlace(int khach, int phongngu, int giuong, int phongvs, long detailId) throws Exception {
		return placeDao.EditDetailPlace(khach, phongngu, giuong, phongvs, detailId);
	}
	
	public int EditPlace(String name, String detail, String address, long price, String startDay, String endDay, boolean isEmpty, long placeId) throws Exception {
		return placeDao.EditPlace(name, detail, address, price, startDay, endDay, isEmpty, placeId);
	}
	
	public int EditImagePlace(String image, long placeId) throws Exception {
		return placeDao.EditImagePlace(image, placeId);
	}
}
