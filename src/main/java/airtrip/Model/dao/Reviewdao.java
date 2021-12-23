package airtrip.Model.dao;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import airtrip.Model.bean.Accountbean;
import airtrip.Model.bean.Reviewbean;


public class Reviewdao {
	
	PreparedStatement pst;
	Statement st;
	ResultSet rs;
	DungChung dc = new DungChung();
	
	private Accountdao accdao = new Accountdao();
	
	public List<Reviewbean> getAllReview() throws Exception{
		List<Reviewbean> reviewList = new ArrayList<Reviewbean>();
		
		String sql = "select * from Review";
		
		dc.KetNoi();
		pst = dc.cn.prepareStatement(sql);
		rs = pst.executeQuery();
		
		while(rs.next()) {
			
			Accountbean account = accdao.getAccountById(rs.getLong("accountId"));
			
			String[] date = rs.getString("dateSubmit").split("-");
			String dateSubmit = date[2]+"-"+date[1]+"-"+date[0];
					
			reviewList.add(new Reviewbean(rs.getLong("reviewId"), rs.getInt("rate"), rs.getString("content"), dateSubmit
					, rs.getLong("placeId"), account));
		}
		
		rs.close();
		
		return reviewList;
	}
	
	public List<Reviewbean> getReviewBySearch(String search) throws Exception{
		List<Reviewbean> reviewList = new ArrayList<Reviewbean>();
		
		String sql = "SELECT * FROM Review as r join Account as a on r.accountId = a.accountId\r\n" + 
					"WHERE r.content LIKE ? \r\n" + 
					"or rate LIKE ? or placeId LIKE ?\r\n" + 
					"or a.name LIKE ? ";
			
		dc.KetNoi();
		pst = dc.cn.prepareStatement(sql);
		pst.setString(1, "%"+ search +"%");
		pst.setString(2, "%"+ search +"%");
		pst.setString(3, "%"+ search +"%");
		pst.setString(4, "%"+ search +"%");
		
		rs = pst.executeQuery();
		
		while(rs.next()) {
			
			Accountbean account = accdao.getAccountById(rs.getLong("accountId"));
					
			String[] date = rs.getString("dateSubmit").split("-");
			String dateSubmit = date[2]+"-"+date[1]+"-"+date[0];
					
			reviewList.add(new Reviewbean(rs.getLong("reviewId"), rs.getInt("rate"), rs.getString("content"), dateSubmit
					, rs.getLong("placeId"), account));
		}
		
		rs.close();
		
		return reviewList;
	}
	
	public List<Reviewbean> getReviewByDate(String search) throws Exception{
		List<Reviewbean> reviewList = new ArrayList<Reviewbean>();
		
		String sql = "SELECT * FROM Review WHERE dateSubmit LIKE ?";
			
		dc.KetNoi();
		pst = dc.cn.prepareStatement(sql);
		pst.setString(1, "%"+ search +"%");
		
		rs = pst.executeQuery();
		
		while(rs.next()) {
			
			Accountbean account = accdao.getAccountById(rs.getLong("accountId"));
					
			String[] date = rs.getString("dateSubmit").split("-");
			String dateSubmit = date[2]+"-"+date[1]+"-"+date[0];
					
			reviewList.add(new Reviewbean(rs.getLong("reviewId"), rs.getInt("rate"), rs.getString("content"), dateSubmit
					, rs.getLong("placeId"), account));
		}
		
		rs.close();
		
		return reviewList;
	}
	
	public List<Reviewbean> getReviewByPaging(int PageNumber, int PageSize) throws Exception {
		List<Reviewbean> reviewList = new ArrayList<Reviewbean>();
		
		dc.KetNoi();
		String sql = "{CALL proc_PagingReview(?,?)}";
		CallableStatement cs;
		cs = dc.cn.prepareCall(sql);
		cs.setInt(1, PageNumber);
		cs.setInt(2, PageSize);
		rs = cs.executeQuery();
		
		while(rs.next()) {
			
			Accountbean account = accdao.getAccountById(rs.getLong("accountId"));
					
			String[] date = rs.getString("dateSubmit").split("-");
			String dateSubmit = date[2]+"-"+date[1]+"-"+date[0];
					
			reviewList.add(new Reviewbean(rs.getLong("reviewId"), rs.getInt("rate"), rs.getString("content"), dateSubmit
					, rs.getLong("placeId"), account));
		}
		rs.close();
		return reviewList;
	}
	
	public int DeleteReview(long reviewId) throws Exception {
		int rs = 0;
		String sql = "DELETE Review WHERE reviewId = ?";
		dc.KetNoi();
		pst = dc.cn.prepareStatement(sql);
		pst.setLong(1, reviewId);
		rs = pst.executeUpdate();
		return rs;
	}
}
