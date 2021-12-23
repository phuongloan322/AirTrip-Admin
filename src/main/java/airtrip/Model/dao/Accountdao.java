package airtrip.Model.dao;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import airtrip.Model.bean.Accountbean;
import airtrip.Model.bean.DetailPlacebean;
import airtrip.Model.bean.Placebean;

public class Accountdao {
	
	private Statement st;
	private PreparedStatement pst;
	private ResultSet rs;
	DungChung dc = new DungChung();

	public List<Accountbean> getAccount() throws Exception {
		List<Accountbean> accList = new ArrayList<Accountbean>();
		String sql = "SELECT * FROM Account";
		dc.KetNoi();
		st = dc.cn.createStatement();
		rs = st.executeQuery(sql);
		while(rs.next()) {
			accList.add(new Accountbean(rs.getLong("accountId"), rs.getString("name"), rs.getString("address")
					, rs.getString("phone"), rs.getString("email"), rs.getString("username")
					, rs.getString("password"), rs.getString("image")));
		}
		rs.close();
		return accList;
	}
	
	public List<Accountbean> getAccountByPaging(int PageNumber, int PageSize) throws Exception {
		List<Accountbean> accList = new ArrayList<Accountbean>();
		
		dc.KetNoi();
		String sql = "{CALL proc_PagingAccount(?,?)}";
		CallableStatement cs;
		cs = dc.cn.prepareCall(sql);
		cs.setInt(1, PageNumber);
		cs.setInt(2, PageSize);
		rs = cs.executeQuery();
		
		while(rs.next()) {
			accList.add(new Accountbean(rs.getLong("accountId"), rs.getString("name"), rs.getString("address")
					, rs.getString("phone"), rs.getString("email"), rs.getString("username")
					, rs.getString("password"), rs.getString("image")));
		}
		rs.close();
		return accList;
	}
	
	public Accountbean getAccountByUsername(String username) throws Exception {
		Accountbean accountbean = null;
		String sql = "SELECT * FROM Account WHERE username = ?";
		dc.KetNoi();
		pst = dc.cn.prepareStatement(sql);
		pst.setString(1, username);
		rs = pst.executeQuery();
		while(rs.next()) {
			accountbean = new Accountbean(rs.getLong("accountId"), rs.getString("name"), rs.getString("address")
					, rs.getString("phone"), rs.getString("email"), rs.getString("username")
					, rs.getString("password"), rs.getString("image"));
		}
		rs.close();
		return accountbean;
	}
	
	public List<Accountbean> getAccountSearch(String search) throws Exception {
		List<Accountbean> accList = new ArrayList<Accountbean>();
		String sql = "SELECT * FROM Account\r\n" + 
				"WHERE name LIKE ? or phone = ? or username = ? or address LIKE ? ";
		dc.KetNoi();
		pst = dc.cn.prepareStatement(sql);
		pst.setString(1, "%"+search+"%");
		pst.setString(2, search);
		pst.setString(3, search);
		pst.setString(4, "%"+search+"%");
		rs = pst.executeQuery();
		while(rs.next()) {
			accList.add(new Accountbean(rs.getLong("accountId"), rs.getString("name"), rs.getString("address")
					, rs.getString("phone"), rs.getString("email"), rs.getString("username")
					, rs.getString("password"), rs.getString("image")));
		}
		rs.close();
		return accList;
	}
	
	public Accountbean getAccountById(long accId) throws Exception {
		Accountbean accountbean = null;
		String sql = "SELECT * FROM Account WHERE accountId = ?";
		dc.KetNoi();
		pst = dc.cn.prepareStatement(sql);
		pst.setLong(1, accId);
		rs = pst.executeQuery();
		while(rs.next()) {
			accountbean = new Accountbean(rs.getLong("accountId"), rs.getString("name"), rs.getString("address")
					, rs.getString("phone"), rs.getString("email"), rs.getString("username")
					, rs.getString("password"), rs.getString("image"));
					}
		rs.close();
		return accountbean;
	}
	
	public int AddAccount(Accountbean account) throws Exception {
		int rs = 0;
		String sql = "INSERT Account\r\n" + 
					"VALUES (?,?,?,?,?,?,?)";
		dc.KetNoi();
		pst = dc.cn.prepareStatement(sql);
		pst.setString(1, account.getName());
		pst.setString(2, account.getAddress());
		pst.setString(3, account.getPhone());
		pst.setString(4, account.getEmail());
		pst.setString(5, account.getUsername());
		pst.setString(6, account.getPassword());
		pst.setString(7, account.getImage());
		rs = pst.executeUpdate();
		return rs;
	}
	
	public int EditAccount(Accountbean account) throws Exception {
		int rs = 0;
		String sql = "UPDATE Account\r\n" + 
				"SET name = ?, phone = ?, address = ?, email = ?\r\n" + 
				"WHERE accountId = ?";
		dc.KetNoi();
		pst = dc.cn.prepareStatement(sql);
		pst.setString(1, account.getName());
		pst.setString(2, account.getPhone());
		pst.setString(3, account.getAddress());
		pst.setString(4, account.getEmail());
		pst.setLong(5, account.getAccountId());
		
		rs = pst.executeUpdate();
		return rs;
	}
	
	public int DeleteAccount(long accountId) {
		int rs = 0;
		try {
			String sql = "DELETE Account WHERE accountId = ?";
			dc.KetNoi();
			pst = dc.cn.prepareStatement(sql);
			pst.setLong(1, accountId);
			rs = pst.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rs;
	}
	
}
