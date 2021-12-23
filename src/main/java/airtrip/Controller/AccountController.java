package airtrip.Controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;

import airtrip.Model.bean.AccountAdminbean;
import airtrip.Model.bean.Accountbean;
import airtrip.Model.bean.Placebean;
import airtrip.Model.bo.Accountbo;

@Controller
public class AccountController {
	
	private Accountbo accbo = new Accountbo();

	@RequestMapping(value="/manager-account")
	public ModelAndView ManagerAccount(Model model, HttpServletRequest request, HttpSession session) {
		try {
			AccountAdminbean adminLogin = (AccountAdminbean)session.getAttribute("adminLogin");
			if(adminLogin == null) {
				return new ModelAndView("login");
			}
			
			String search = request.getParameter("search");
			
			if(search != null && search != "") {
				List<Accountbean> accList = accbo.getAccountSearch(search);
				session.setAttribute("accountList", accList);
				if(search != "")
					model.addAttribute("msg", accList.size() +" kết quả tìm kiếm cho: " + search);
			}
			else {
				List<Accountbean> accList = accbo.getAccountByPaging(1, 5);
				session.setAttribute("accountList", accList);
			}
			
			int totalPlace = accbo.getAccount().size();
			int totalPageNumber = 1;
			if(totalPlace > 0) {
				if(totalPlace % 5 == 0)
					 totalPageNumber = totalPlace/5;
				else
					totalPageNumber = totalPlace/5 + 1;
			}
			session.setAttribute("totalPageNumber", totalPageNumber);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ModelAndView("managerAccount");
	}
	
	@RequestMapping(value = "/getAccountByPaging", method = RequestMethod.GET)
	public @ResponseBody List<Accountbean> getAccountByPaging(HttpServletRequest request,
			@RequestParam(name = "pagination") int pagination ) {
			List<Accountbean> accList = new ArrayList<Accountbean>();

		try {
			
			accList = accbo.getAccountByPaging(pagination, 5);
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		return accList;
	}
	
	@RequestMapping(value="/manager-account/delete/{accountId}")
	public ModelAndView AddAccount(Model model, HttpServletRequest request, HttpSession session, @PathVariable long accountId) {
		try {
			session.removeAttribute("msg");
			String msg = "";
			
			int rs = accbo.DeleteAccount(accountId);
			
			if(rs > 0) {
				msg = "deleteOK";
			}
			else {
				msg = "deleteERROR";
			}
			
			model.addAttribute("msg", msg);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ModelAndView("redirect:/manager-account");
	}
	
	@RequestMapping(value="/manager-account/findById/{accountId}")
	public void findById(Model model, HttpServletRequest request, HttpServletResponse response, HttpSession session, @PathVariable long accountId) {
		try {
			response.setContentType("application/json");
		    response.setCharacterEncoding("UTF-8");
			Gson data = new Gson();
			Accountbean accountbean = accbo.getAccountById(accountId);
			String account = data.toJson(accountbean);
			response.getWriter().write(account);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value="/manager-account/findById", method = RequestMethod.GET)
	public @ResponseBody Accountbean findByIdAjax(
			@RequestParam("accountId") long accountId) {
		Accountbean accountbean = new Accountbean();
		try { 
			accountbean = accbo.getAccountById(accountId);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return accountbean;
	}
	
	@RequestMapping(value="/manager-account/delete", method = RequestMethod.GET)
	public @ResponseBody String DeleteAccountAjax(
			@RequestParam("accountId") long accountId) {
		try {
			
			int rs = accbo.DeleteAccount(accountId);
			
			if(rs > 0) {
				return "Xóa người dùng thành công";
			}
			else {
				return "Xóa người dùng thất bại";
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "Xóa người dùng thất bại";
	}
	
	@RequestMapping(value="/manager-account/edit")
	public ModelAndView EditAccount(Model model, HttpServletRequest request, HttpSession session) {
		try {
			session.removeAttribute("msg");
			String msg = null;
			
			String accountId = request.getParameter("accountId");
			String name = request.getParameter("name");
			String address = request.getParameter("address");
			String phone = request.getParameter("phone");
			String email = request.getParameter("email");
			String username = request.getParameter("username");
			
			Accountbean account = new Accountbean(Long.parseLong(accountId), name, address, phone, email, username, "", null);
			
			int rs = accbo.EditAccount(account);
			
			if(rs > 0) {
				msg = "editOK";
			}
			else {
				msg = "editERROR";
			}
			
			model.addAttribute("msg", msg);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ModelAndView("redirect:/manager-account"); 	//new ModelAndView("managerAccount")
	}
	
	@RequestMapping(value="/manager-account/add")
	public ModelAndView AddAccount(Model model, HttpServletRequest request, HttpSession session) {
		try {
			session.removeAttribute("msg");
			String msg = null;
			
			String name = request.getParameter("name");
			String address = request.getParameter("address");
			String phone = request.getParameter("phone");
			String email = request.getParameter("email");
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			
			Accountbean account = new Accountbean(name, address, phone, email, username, password, null);
			if(accbo.getAccountByUsername(username) == null) {
				int rs = accbo.AddAccount(account);
				if(rs > 0) {
					msg = "addOK";
				}
				else msg = "addERROR";
			}
			else {
				msg = "addexists";
			}
			
			model.addAttribute("msg", msg);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ModelAndView("redirect:/manager-account"); 	//new ModelAndView("managerAccount")
	}
	
}
