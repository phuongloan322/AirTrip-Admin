package airtrip.Model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import airtrip.Model.bean.Categorybean;
import airtrip.Model.bean.LilteCategorybean;

public class LilteCategorydao {

	PreparedStatement pst;
	Statement st;
	ResultSet rs;
	DungChung dc = new DungChung();
	
	public LilteCategorybean getLitleCategoryId(String ma) throws Exception {
		LilteCategorybean lilteCategorybean = new LilteCategorybean();
		String sql = "SELECT * FROM LitleCategory WHERE litleCategoryId = ?";
		dc.KetNoi();
		pst = dc.cn.prepareStatement(sql);
		pst.setString(1, ma);
		rs = pst.executeQuery();
		while(rs.next()) {
			lilteCategorybean = new LilteCategorybean(rs.getString("litleCategoryId"), rs.getString("litleName")
					, rs.getString("detail"), rs.getString("categoryId"));
			return lilteCategorybean;
		}
		rs.close();
		return null;
	}
	
	public List<LilteCategorybean> getAll(String CategoryId) throws Exception {
		List<LilteCategorybean> ds = new ArrayList<LilteCategorybean>();
		String sql = "SELECT * FROM LitleCategory WHERE categoryId = ?";
		dc.KetNoi();
		pst = dc.cn.prepareStatement(sql);
		pst.setString(1, CategoryId);
		rs = pst.executeQuery();
		
		while(rs.next()) {
			ds.add(new LilteCategorybean(rs.getString("litleCategoryId"), rs.getString("litleName")
					, rs.getString("detail"), rs.getString("categoryId")));
		}
		rs.close();
		return ds;
	}
	
	public List<LilteCategorybean> getSearch(String search) throws Exception {
		List<LilteCategorybean> ds = new ArrayList<LilteCategorybean>();
		String sql = "SELECT * FROM LitleCategory WHERE categoryId = ? or litleName = ? or litleCategoryId = ?";
		dc.KetNoi();
		pst = dc.cn.prepareStatement(sql);
		pst.setString(1, search);
		pst.setString(2, search);
		pst.setString(3, search);
		
		rs = pst.executeQuery();
		
		while(rs.next()) {
			ds.add(new LilteCategorybean(rs.getString("litleCategoryId"), rs.getString("litleName")
					, rs.getString("detail"), rs.getString("categoryId")));
		}
		rs.close();
		return ds;
	}
	
	public int AddLitleCategoryPlace(LilteCategorybean litleCategorybean) throws Exception {
		int rs = 0;
		String sql = "INSERT LitleCategory VALUES(?,?,?,?)";
		dc.KetNoi();
		 pst = dc.cn.prepareStatement(sql);
		 pst.setString(1, litleCategorybean.getLitleCategoryId());
		 pst.setString(2, litleCategorybean.getLitleName());
		 pst.setString(3, litleCategorybean.getDetail());
		 pst.setString(4, litleCategorybean.getCategoryId());
		 rs = pst.executeUpdate();
		 return rs;
	}
	
	public int EditLitleCategoryPlace(LilteCategorybean litleCategorybean) throws Exception {
		int rs = 0;
		String sql = "UPDATE LitleCategory\r\n" + 
				"SET litleName = ?, detail = ?, categoryId = ?\r\n" + 
				"WHERE litleCategoryId = ?";
		dc.KetNoi();
		 pst = dc.cn.prepareStatement(sql);
		
		 pst.setString(1, litleCategorybean.getLitleName());
		 pst.setString(2, litleCategorybean.getDetail());
		 pst.setString(3, litleCategorybean.getCategoryId());
		 pst.setString(4, litleCategorybean.getLitleCategoryId());
		 
		 rs = pst.executeUpdate();
		 return rs;
	}
	
	public int DeleteLitleCategoryPlace(String litleCategoryId) throws Exception {
		int rs = 0;
		String sql = "DELETE LitleCategory\r\n" + 
					"WHERE litleCategoryId = ?";
		dc.KetNoi();
		 pst = dc.cn.prepareStatement(sql);
		 pst.setString(1, litleCategoryId);
		 rs = pst.executeUpdate();
		 return rs;
	}
}
