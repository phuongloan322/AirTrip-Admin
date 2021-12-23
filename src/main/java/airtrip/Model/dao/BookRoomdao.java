package airtrip.Model.dao;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import airtrip.Model.bean.Accountbean;
import airtrip.Model.bean.BookRoombean;
import airtrip.Model.bean.Placebean;

public class BookRoomdao {

	PreparedStatement pst;
	Statement st;
	ResultSet rs;
	DungChung dc = new DungChung();
	Placedao placedao = new Placedao();
	Accountdao accountdao = new Accountdao();
	
	public List<BookRoombean> getBookRoom() throws Exception {
		List<BookRoombean> ds = new ArrayList<BookRoombean>();
		
		String sql = "SELECT * FROM BookRoom";
		dc.KetNoi();
		pst = dc.cn.prepareStatement(sql);
		rs = pst.executeQuery();
		while(rs.next()) {
			Placebean placebean = placedao.findById(rs.getLong("placeId"));
			Accountbean accThue = accountdao.getAccountById(rs.getLong("accountId"));	
			ds.add(new BookRoombean(rs.getLong("bookId"), rs.getString("startDay"), rs.getString("endDay")
					, rs.getLong("totalPrice"), rs.getInt("people")
					, placebean, accThue, rs.getBoolean("isAccept")));
		}
		rs.close();
		return ds;
	}
	
	public List<BookRoombean> getBookRoomByPaging(int PageNumber, int PageSize) throws Exception {
		List<BookRoombean> ds = new ArrayList<BookRoombean>();
		
		dc.KetNoi();
		String sql = "{CALL proc_PagingBookRoom(?,?)}";
		CallableStatement cs;
		cs = dc.cn.prepareCall(sql);
		cs.setInt(1, PageNumber);
		cs.setInt(2, PageSize);
		rs = cs.executeQuery();
		
		while(rs.next()) {
			Placebean placebean = placedao.findById(rs.getLong("placeId"));
			Accountbean accThue = accountdao.getAccountById(rs.getLong("accountId"));	
			ds.add(new BookRoombean(rs.getLong("bookId"), rs.getString("startDay"), rs.getString("endDay")
					, rs.getLong("totalPrice"), rs.getInt("people")
					, placebean, accThue, rs.getBoolean("isAccept")));
		}
		rs.close();
		return ds;
	}
	
	public int deleteBookRoom(long bookId) throws Exception {
		int rs = 0;
		String sql = "DELETE FROM BookRoom WHERE bookId = ?";
		dc.KetNoi();
		pst = dc.cn.prepareStatement(sql);
		pst.setLong(1, bookId);
		rs = pst.executeUpdate();
		return rs;
	}
	
	public BookRoombean getBookRoomById(long bookId) throws Exception {
		BookRoombean roombean = new BookRoombean();
		
		String sql = "SELECT * FROM BookRoom WHERE bookId = ?";
		dc.KetNoi();
		pst = dc.cn.prepareStatement(sql);
		pst.setLong(1, bookId);
		rs = pst.executeQuery();
		while(rs.next()) {
			Placebean placebean = placedao.findById(rs.getLong("placeId"));
			Accountbean accThue = accountdao.getAccountById(rs.getLong("accountId"));	
			roombean = new BookRoombean(rs.getLong("bookId"), rs.getString("startDay"), rs.getString("endDay")
					, rs.getLong("totalPrice"), rs.getInt("people")
					, placebean, accThue, rs.getBoolean("isAccept"));
		}
		rs.close();
		return roombean;
	}
	
	public List<BookRoombean> getBookRoom(long accThueId) throws Exception {
		List<BookRoombean> ds = new ArrayList<BookRoombean>();
		
		String sql = "SELECT * FROM BookRoom WHERE accountId = ?";
		dc.KetNoi();
		pst = dc.cn.prepareStatement(sql);
		pst.setLong(1, accThueId);
		rs = pst.executeQuery();
		while(rs.next()) {
			Placebean placebean = placedao.findById(rs.getLong("placeId"));
			Accountbean accThue = accountdao.getAccountById(accThueId);	//tÃ i khoáº£n ngÆ°á»�i thuÃª
			ds.add(new BookRoombean(rs.getLong("bookId"), rs.getString("startDay"), rs.getString("endDay")
					, rs.getLong("totalPrice"), rs.getInt("people")
					, placebean, accThue, rs.getBoolean("isAccept")));
		}
		rs.close();
		return ds;
	}
	
	public List<BookRoombean> getBookRoomAccept(long accId, boolean isAccept) throws Exception {
		List<BookRoombean> ds = new ArrayList<BookRoombean>();
		
		String sql = "SELECT * FROM BookRoom \r\n" + 
				"WHERE isAccept = ? AND placeId IN \r\n" + 
				"(SELECT placeId FROM Place WHERE accountId= ?)";
		dc.KetNoi();
		pst = dc.cn.prepareStatement(sql);
		pst.setBoolean(1, isAccept);
		pst.setLong(2, accId);	//ng cho thuÃª
		rs = pst.executeQuery();
		while(rs.next()) {
			Placebean placebean = placedao.findById(rs.getLong("placeId"));
			Accountbean accThue = accountdao.getAccountById(rs.getLong("accountId"));	//tÃ i khoáº£n ng thuÃª
			ds.add(new BookRoombean(rs.getLong("bookId"), rs.getString("startDay"), rs.getString("endDay")
					, rs.getLong("totalPrice"), rs.getInt("people")
					, placebean, accThue, rs.getBoolean("isAccept")));
		}
		rs.close();
		return ds;
	}
	
	public List<BookRoombean> getBookRoomAllAccept(long accId) throws Exception {
		List<BookRoombean> ds = new ArrayList<BookRoombean>();
		
		String sql = "SELECT * FROM BookRoom \r\n" + 
				"WHERE placeId IN \r\n" + 
				"(SELECT placeId FROM Place WHERE accountId= ?)";
		dc.KetNoi();
		pst = dc.cn.prepareStatement(sql);
		pst.setLong(1, accId);
		rs = pst.executeQuery();
		while(rs.next()) {
			Placebean placebean = placedao.findById(rs.getLong("placeId"));
			Accountbean accThue = accountdao.getAccountById(rs.getLong("accountId"));	//tÃ i khoáº£n ng thuÃª
			ds.add(new BookRoombean(rs.getLong("bookId"), rs.getString("startDay"), rs.getString("endDay")
					, rs.getLong("totalPrice"), rs.getInt("people")
					, placebean, accThue, rs.getBoolean("isAccept")));
		}
		rs.close();
		return ds;
	} 
	public long daysBetween2Dates(String startDay, String endDay) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

        Date currentDate = new Date();
        Date date1 = null;
        Date date2 = null;
        long getDaysDiff = 0;
        
        try {
         date1 = simpleDateFormat.parse(startDay);
         date2 = simpleDateFormat.parse(endDay);

         long getDiff = date2.getTime() - date1.getTime();

         getDaysDiff = getDiff / (24 * 60 * 60 * 1000);
         if(getDaysDiff < 0) getDaysDiff = -getDaysDiff;
        } catch (Exception e) {
         e.printStackTrace();
        }
        return getDaysDiff;
    }
	
	public List<BookRoombean> getBookRoomBySearch(String search) throws Exception {
		List<BookRoombean> ds = new ArrayList<BookRoombean>();
		String sql = "SELECT * FROM BookRoom as b join Place as p on b.placeId = p.placeId \r\n" + 
				"	join Account as a on b.accountId = a.accountId\r\n" + 
				"WHERE p.name LIKE ? or p.address LIKE ? or a.name LIKE ?\r\n" + 
				"or a.phone LIKE ? or a.email LIKE ? or p.price LIKE ? or b.totalPrice LIKE ?\r\n" + 
				"or b.startDay LIKE ? or b.endDay LIKE ?";
		dc.KetNoi();
		pst = dc.cn.prepareStatement(sql);
		pst.setString(1, "%"+ search + "%");
		pst.setString(2, "%"+ search + "%");
		pst.setString(3, "%"+ search + "%");
		pst.setString(4, "%"+ search + "%");
		pst.setString(5, "%"+ search + "%");
		pst.setString(6, "%"+ search + "%");
		pst.setString(7, "%"+ search + "%");
		pst.setString(8, "%"+ search + "%");
		pst.setString(9, "%"+ search + "%");
		
		rs = pst.executeQuery();
		while(rs.next()) {
			Placebean placebean = placedao.findById(rs.getLong("placeId"));
			Accountbean accThue = accountdao.getAccountById(rs.getLong("accountId"));	
			ds.add(new BookRoombean(rs.getLong("bookId"), rs.getString("startDay"), rs.getString("endDay")
					, rs.getLong("totalPrice"), rs.getInt("people")
					, placebean, accThue, rs.getBoolean("isAccept")));
		}
		rs.close();
		return ds;
	}
}
