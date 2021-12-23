package airtrip.Model.bo;

import java.util.ArrayList;
import java.util.List;

import org.mindrot.jbcrypt.BCrypt;

import airtrip.Model.bean.AccountAdminbean;
import airtrip.Model.bean.Accountbean;
import airtrip.Model.dao.AccountAdmindao;

public class AccountAdminbo {

	private AccountAdmindao admindao = new AccountAdmindao();
	
	public List<AccountAdminbean> getAccount() throws Exception {
		return admindao.getAccount();
	}
	
	public AccountAdminbean getAccountById(long accId) throws Exception {
		return admindao.getAccountById(accId);
	}
	
	public AccountAdminbean getAccountByUsername(String username) throws Exception {
		return admindao.getAccountByUsername(username);
	}
	
	public AccountAdminbean checkAccount(String username, String password) throws Exception {
		List<AccountAdminbean> accList = admindao.getAccount();
		for(AccountAdminbean acc : accList) {
			if(acc.getUsername().equals(username)) {
				if (acc != null) {
					if (BCrypt.checkpw(password, acc.getPassword())) {
						return acc;
					} else {
						return null;
					}
				}
			}
		}
		return null;
	}
}

