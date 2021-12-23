package airtrip.Model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import airtrip.Model.bean.AccountAdminbean;
import airtrip.Model.bean.Accountbean;

public class AccountAdmindao {

	private Statement st;
	private PreparedStatement pst;
	private ResultSet rs;
	DungChung dc = new DungChung();
	
	public List<AccountAdminbean> getAccount() throws Exception {
		List<AccountAdminbean> accList = new ArrayList<AccountAdminbean>();
		String sql = "SELECT * FROM AccountAdmin";
		dc.KetNoi();
		st = dc.cn.createStatement();
		rs = st.executeQuery(sql);
		while(rs.next()) {
			accList.add(new AccountAdminbean(rs.getLong("accountId"), rs.getString("name")
					, rs.getString("address"), rs.getString("phone"), rs.getString("email")
					, rs.getString("username"), rs.getString("password"), rs.getString("img")));
		}
		rs.close();
		return accList;
	}
	
	public AccountAdminbean getAccountById(long accId) throws Exception {
		AccountAdminbean accountbean = null;
		String sql = "SELECT * FROM AccountAdmin WHERE accountId = ?";
		dc.KetNoi();
		pst = dc.cn.prepareStatement(sql);
		pst.setLong(1, accId);
		rs = pst.executeQuery();
		while(rs.next()) {
			accountbean = new AccountAdminbean(rs.getLong("accountId"), rs.getString("name")
					, rs.getString("address"), rs.getString("phone"), rs.getString("email")
					, rs.getString("username"), rs.getString("password"), rs.getString("img"));
		}
		rs.close();
		return accountbean;
	}
	
	public AccountAdminbean getAccountByUsername(String username) throws Exception {
		AccountAdminbean accountbean = null;
		String sql = "SELECT * FROM AccountAdmin WHERE username = ?";
		dc.KetNoi();
		pst = dc.cn.prepareStatement(sql);
		pst.setString(1, username);
		rs = pst.executeQuery();
		while(rs.next()) {
			accountbean = new AccountAdminbean(rs.getLong("accountId"), rs.getString("name")
					, rs.getString("address"), rs.getString("phone"), rs.getString("email")
					, rs.getString("username"), rs.getString("password"), rs.getString("img"));
		}
		rs.close();
		return accountbean;
	}
}
