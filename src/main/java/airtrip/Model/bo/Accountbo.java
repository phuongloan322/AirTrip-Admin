package airtrip.Model.bo;

import java.util.ArrayList;
import java.util.List;

import org.mindrot.jbcrypt.BCrypt;

import airtrip.Model.bean.Accountbean;
import airtrip.Model.dao.Accountdao;

public class Accountbo {

	Accountdao accdao = new Accountdao();
	
	public List<Accountbean> getAccount() throws Exception {
		return accdao.getAccount();
	}
	
	public List<Accountbean> getAccountByPaging(int PageNumber, int PageSize) throws Exception {
		return accdao.getAccountByPaging(PageNumber, PageSize);
	}
	
	public List<Accountbean> getAccountSearch(String search) throws Exception {
		return accdao.getAccountSearch(search);
	}
	
	public Accountbean getAccountById(long accId) throws Exception {
		return accdao.getAccountById(accId);
	}
	
	public int AddAccount(Accountbean account) throws Exception {
		account.setPassword(BCrypt.hashpw(account.getPassword(), BCrypt.gensalt(12)));
		return accdao.AddAccount(account);
	}
	
	public int EditAccount(Accountbean account) throws Exception {
		return accdao.EditAccount(account);
	}
	
	public int DeleteAccount(long accountId) throws Exception {
		return accdao.DeleteAccount(accountId);
	}
	
	public Accountbean getAccountByUsername(String username) throws Exception {
		return accdao.getAccountByUsername(username);
	}
}
