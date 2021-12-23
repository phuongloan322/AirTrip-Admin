package airtrip.Model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import airtrip.Model.bean.Categorybean;
import airtrip.Model.bean.LilteCategorybean;

public class Categorydao {

	private Statement st;
	private PreparedStatement pst;
	private ResultSet rs;
	DungChung dc = new DungChung();
	
	public Categorybean getCategoryId(String ma) throws Exception {
		Categorybean categorybean = new Categorybean();
		String sql = "SELECT * FROM Category WHERE categoryId = ?";
		dc.KetNoi();
		pst = dc.cn.prepareStatement(sql);
		pst.setString(1, ma);
		rs = pst.executeQuery();
		while(rs.next()) {
			categorybean = new Categorybean(rs.getString("categoryId"), rs.getString("name"));
			return categorybean;
		}
		rs.close();
		return null;
	}
	
	public List<Categorybean> getCategory(String search) throws Exception {
		List<Categorybean> categoryList = new ArrayList<Categorybean>();
		String sql = "SELECT * FROM Category WHERE categoryId = ? or name LIKE ?";
		dc.KetNoi();
		pst = dc.cn.prepareStatement(sql);
		pst.setString(1, search);
		pst.setString(2, "%"+ search +"%");
		rs = pst.executeQuery();
		while(rs.next()) {
			categoryList.add(new Categorybean(rs.getString("categoryId"), rs.getString("name")));
		}
		rs.close();
		return categoryList;
	}
	
	public List<Categorybean> getAll() throws Exception {
		List<Categorybean> ds = new ArrayList<Categorybean>();
		String sql = "SELECT * FROM Category";
		dc.KetNoi();
		st = dc.cn.createStatement();
		rs = st.executeQuery(sql);
		while(rs.next()) {
			ds.add(new Categorybean(rs.getString("categoryId"), rs.getString("name")));
		}
		rs.close();
		return ds;
	}
	
	public int AddCategoryPlace(Categorybean categorybean) throws Exception {
		int rs = 0;
		try {
			String sql = "INSERT Category VALUES (?,?)";
			dc.KetNoi();
			 pst = dc.cn.prepareStatement(sql);
			 pst.setString(1, categorybean.getCategoryId());
			 pst.setString(2, categorybean.getName());
			 rs = pst.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		 return rs;
	}
	
	public int EditCategoryPlace(Categorybean categorybean) throws Exception {
		int rs = 0;
		String sql = "UPDATE Category SET name = ? WHERE categoryId = ?";
		dc.KetNoi();
		 pst = dc.cn.prepareStatement(sql);
		 pst.setString(1, categorybean.getName());
		 pst.setString(2, categorybean.getCategoryId());
		 rs = pst.executeUpdate();
		 return rs;
	}
	
	public int DeleteCategoryPlace(String CategoryId) throws Exception {
		int rs = 0;
		try {
			String sql = "DELETE Category WHERE categoryId = ?";
			dc.KetNoi();
			 pst = dc.cn.prepareStatement(sql);
			 pst.setString(1, CategoryId);
			 rs = pst.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		 return rs;
	}
}
