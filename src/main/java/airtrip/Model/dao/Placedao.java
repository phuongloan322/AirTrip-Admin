package airtrip.Model.dao;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import airtrip.Model.bean.Accountbean;
import airtrip.Model.bean.DetailPlacebean;
import airtrip.Model.bean.Placebean;

public class Placedao {
	PreparedStatement pst;
	Statement st;
	ResultSet rs;
	DungChung dc = new DungChung();
	
	private DetailPlacedao detaildao = new DetailPlacedao();
	private Accountdao accdao = new Accountdao();

	public List<Placebean> getAllPlace() throws Exception {
		List<Placebean> placeList = new ArrayList<Placebean>();
		String sql = "SELECT * FROM Place";
		
		dc.KetNoi();
		st = dc.cn.createStatement();
		rs = st.executeQuery(sql);
		while(rs.next()) {
			String[] images = rs.getString("image").split("[;]");
			DetailPlacebean detail = detaildao.getDetailPlace(rs.getLong("detailId"));
			Accountbean account = accdao.getAccountById(rs.getLong("accountId"));
			placeList.add(new Placebean(rs.getInt("placeId"), rs.getString("name")
					, images
					, rs.getString("address")
					, rs.getLong("price"), rs.getString("phone"), rs.getString("startDay")
					, rs.getString("endDay"), rs.getString("detail"), rs.getBoolean("isEmpty")
					, rs.getString("litleCategoryId")
					, detail, account));
		}
		rs.close();
		return placeList;
	}
	
	public List<Placebean> getPlaceByPaging(int PageNumber, int PageSize) throws Exception {
		List<Placebean> placeList = new ArrayList<Placebean>();
		
		dc.KetNoi();
		String sql = "{CALL proc_PagingPlace(?,?)}";
		CallableStatement cs;
		cs = dc.cn.prepareCall(sql);
		cs.setInt(1, PageNumber);
		cs.setInt(2, PageSize);
		rs = cs.executeQuery();
		
		while(rs.next()) {
			String[] images = rs.getString("image").split("[;]");
			DetailPlacebean detail = detaildao.getDetailPlace(rs.getLong("detailId"));
			Accountbean account = accdao.getAccountById(rs.getLong("accountId"));
			placeList.add(new Placebean(rs.getInt("placeId"), rs.getString("name")
					, images
					, rs.getString("address")
					, rs.getLong("price"), rs.getString("phone"), rs.getString("startDay")
					, rs.getString("endDay"), rs.getString("detail"), rs.getBoolean("isEmpty")
					, rs.getString("litleCategoryId")
					, detail, account));
			}
		rs.close();
		return placeList;
	}
	
	public List<Placebean> getAllPlace(String search) throws Exception {
		List<Placebean> placeList = new ArrayList<Placebean>();
		String sql = "SELECT * FROM vDetailPlace \r\n" + 
					"WHERE nameplace LIKE ?\r\n" + 
					"OR name  LIKE ?\r\n" + 
					"OR litleName  LIKE ?\r\n" + 
					"OR address  LIKE ?\r\n" + 
					"OR price  LIKE ?\r\n" + 
					"OR phone  LIKE ?\r\n" + 
					"OR startDay  LIKE ?";
		
		dc.KetNoi();
		pst = dc.cn.prepareStatement(sql);
		pst.setString(1, "%"+search+"%");
		pst.setString(2, "%"+search+"%");
		pst.setString(3, "%"+search+"%");
		pst.setString(4, "%"+search+"%");
		pst.setString(5, "%"+search+"%");
		pst.setString(6, "%"+search+"%");
		pst.setString(7, "%"+search+"%");
		rs = pst.executeQuery();
		
		while(rs.next()) {
			String[] images = rs.getString("image").split("[;]");
			String[] tienichs = rs.getString("tienich").split("[;]");
			
			DetailPlacebean detail = new DetailPlacebean(rs.getLong("detailId"), rs.getInt("phongkhach"), rs.getInt("phongngu")
					, rs.getInt("giuong"), rs.getInt("phongvs"), tienichs);
			Accountbean account = accdao.getAccountById(rs.getLong("accountId"));
			placeList.add(new Placebean(rs.getInt("placeId"), rs.getString("nameplace")
					, images
					, rs.getString("address")
					, rs.getLong("price"), rs.getString("phone"), rs.getString("startDay")
					, rs.getString("endDay"), rs.getString("detail"), rs.getBoolean("isEmpty")
					, rs.getString("litleCategoryId")
					, detail, account));
		}
		rs.close();
		return placeList;
	}
	
	public List<Placebean> getPlaceByAccId(long accountId) throws Exception {
		List<Placebean> placeList = new ArrayList<Placebean>();
		String sql = "SELECT * FROM vDetailPlace WHERE accountId = ?";
		
		dc.KetNoi();
		pst = dc.cn.prepareStatement(sql);
		pst.setLong(1, accountId);
		rs = pst.executeQuery();
		while(rs.next()) {
			String[] images = rs.getString("image").split("[;]");
			String[] tienichs = rs.getString("tienich").split("[;]");
			
			DetailPlacebean detail = new DetailPlacebean(rs.getLong("detailId"), rs.getInt("phongkhach"), rs.getInt("phongngu")
					, rs.getInt("giuong"), rs.getInt("phongvs"), tienichs);
			Accountbean account = accdao.getAccountById(rs.getLong("accountId"));
			placeList.add(new Placebean(rs.getInt("placeId"), rs.getString("nameplace")
					, images
					, rs.getString("address")
					, rs.getLong("price"), rs.getString("phone"), rs.getString("startDay")
					, rs.getString("endDay"), rs.getString("detail"), rs.getBoolean("isEmpty")
					, rs.getString("litleCategoryId")
					, detail, account));
		}
		rs.close();
		return placeList;
	}
	
	public Placebean findById(long id) throws Exception {
		Placebean place = new Placebean();
		String sql = "SELECT * FROM Place WHERE placeId = ?";
		dc.KetNoi();
		pst = dc.cn.prepareStatement(sql);
		pst.setLong(1, id);
		rs = pst.executeQuery();
		while(rs.next()) {
			String[] images = rs.getString("image").split("[;]");
			DetailPlacebean detail = detaildao.getDetailPlace(rs.getLong("detailId"));
			Accountbean account = accdao.getAccountById(rs.getLong("accountId"));
			place = new Placebean(rs.getInt("placeId"), rs.getString("name")
					, images
					, rs.getString("address")
					, rs.getLong("price"), rs.getString("phone"), rs.getString("startDay")
					, rs.getString("endDay"), rs.getString("detail"), rs.getBoolean("isEmpty")
					, rs.getString("litleCategoryId")
					, detail, account);
			return place;
		}
		rs.close();
		return null;
	}
	
	public int AddDetailPlace(DetailPlacebean detailPlace) throws Exception {
		int rs = 0;
		String tiennghi = "";
		for(String item : detailPlace.getTienich()) {
			tiennghi += item+";";
		}
		String sql = "INSERT DetailPlace VALUES(?,?,?,?,?)";
		dc.KetNoi();
		pst = dc.cn.prepareStatement(sql);
		pst.setInt(1, detailPlace.getPhongkhach());
		pst.setInt(2, detailPlace.getPhongngu());
		pst.setInt(3, detailPlace.getGiuong());
		pst.setInt(4, detailPlace.getPhongvs());
		pst.setString(5, tiennghi);
		rs = pst.executeUpdate();
		return rs;		
	}
	public long GetIDLastDeatilPlace() throws Exception {
		long result = 0;
		String sql = "SELECT MAX(detailId) FROM DetailPlace";
		dc.KetNoi();
		st = dc.cn.createStatement();
		rs = st.executeQuery(sql);
		while(rs.next()) {
			result =  rs.getLong(1);
		}
		return result;
	}
	
	public int AddPlace(Placebean placebean) throws Exception {
		int rs = 0;
		String image = "";
		long detailId = GetIDLastDeatilPlace();
		String sql = "INSERT Place VALUES(?,?,?,?,?,?,?,?,?,?,?,?)";
		dc.KetNoi();
		pst = dc.cn.prepareStatement(sql);
		pst.setString(1, placebean.getName());
		for (String img : placebean.getImage()) {
			image += img+";";
		}
		pst.setString(2, image);
		pst.setString(3, placebean.getAddress());
		pst.setLong(4, placebean.getPrice());
		pst.setString(5, placebean.getPhone());
		pst.setString(6, placebean.getStartDay());
		pst.setString(7, placebean.getEndDay());
		pst.setString(8, placebean.getDetail());
		pst.setBoolean(9, placebean.getIsEmpty());
		pst.setString(10, placebean.getLitleCategoryId());
		pst.setLong(11, detailId);
		pst.setLong(12, placebean.getaccount().getAccountId());
		rs = pst.executeUpdate();
		return rs;		
	}
	
	public int DeletePlace(long placeId) {
		int rs = 0;
		try {
			String sql = "DELETE FROM Place WHERE placeId = ?";
			dc.KetNoi();
			pst = dc.cn.prepareStatement(sql);
			pst.setLong(1, placeId);
			rs = pst.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rs;
	}
	
	public int DeleteAllPlace(long accountId) throws Exception {
		int rs = 0;
		String sql = "DELETE FROM Place WHERE accountId = ?";
		dc.KetNoi();
		pst = dc.cn.prepareStatement(sql);
		pst.setLong(1, accountId);
		rs = pst.executeUpdate();
		return rs;
	} 
	
	public int EditDetailPlace(int khach, int phongngu, int giuong, int phongvs, long detailId) throws Exception {
		int rs = 0;
		String sql = "UPDATE DetailPlace\r\n" + 
				"SET phongkhach = ?, phongngu = ?, giuong = ?, phongvs = ?\r\n" + 
				"WHERE detailId = ?";
		dc.KetNoi();
		pst = dc.cn.prepareStatement(sql);
		pst.setInt(1, khach);
		pst.setInt(2, phongngu);
		pst.setInt(3, giuong);
		pst.setInt(4, phongvs);
		pst.setLong(5, detailId);
		rs = pst.executeUpdate();
		return rs;
	}
	
	public int EditPlace(String name, String detail, String address, long price, String startDay, String endDay, boolean isEmpty, long placeId) throws Exception {
		int rs = 0;
		String sql = "UPDATE Place\r\n" + 
				"SET name = ?, detail = ?, address = ?, price = ?, startDay = ?, endDay = ?, isEmpty = ?\r\n" + 
				"WHERE placeId = ?";
		dc.KetNoi();
		pst = dc.cn.prepareStatement(sql);
		pst.setString(1, name);
		pst.setString(2, detail);
		pst.setString(3, address);
		pst.setLong(4, price);
		pst.setString(5, startDay);
		pst.setString(6, endDay);
		pst.setBoolean(7, isEmpty);
		pst.setLong(8, placeId);
		rs = pst.executeUpdate();
		return rs;
	}
	
	public int EditPlace(boolean isEmpty, long placeId) throws Exception {
		int rs = 0;
		String sql = "UPDATE Place\r\n" + 
				"SET isEmpty = ?\r\n" + 
				"WHERE placeId = ?";
		dc.KetNoi();
		pst = dc.cn.prepareStatement(sql);
		pst.setBoolean(1, isEmpty);
		pst.setLong(2, placeId);
		rs = pst.executeUpdate();
		return rs;
	}
	
	public int EditImagePlace(String image, long placeId) throws Exception {
		int rs = 0;
		String sql = "UPDATE Place\r\n" + 
				"SET image = ?\r\n" + 
				"WHERE placeId = ?";
		dc.KetNoi();
		pst = dc.cn.prepareStatement(sql);
		pst.setString(1, image);
		pst.setLong(2, placeId);
		rs = pst.executeUpdate();
		return rs;
	}
}
