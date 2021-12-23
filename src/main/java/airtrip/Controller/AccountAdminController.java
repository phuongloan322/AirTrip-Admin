package airtrip.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import airtrip.Model.bean.AccountAdminbean;
import airtrip.Model.bo.AccountAdminbo;

@Controller
public class AccountAdminController {
	
	private AccountAdminbo accbo = new AccountAdminbo();

	@RequestMapping(value="/login")
	public ModelAndView Login(Model model, HttpServletRequest request, HttpSession session) {
		try {
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			
			String error = "";
			
			if(username == null && password == null) {
				error = "";
			}
			else {
				AccountAdminbean accountadmin = accbo.checkAccount(username, password);
				if(accountadmin != null) {
					session.setAttribute("adminLogin", accountadmin);
					return new ModelAndView("redirect:/index");
				}
				else {
					error = "Tên đăng nhập hoặc mật khẩu không chính xác!";
				}
			}
			model.addAttribute("error", error);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ModelAndView("login");
	}

	
	@RequestMapping(value="/index")
	public ModelAndView Home(Model model, HttpServletRequest request, HttpSession session) {
		try {
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ModelAndView("index");
	}
	
	@RequestMapping(value="/logout")
	public String Logout(Model model, HttpServletRequest request, HttpSession session) {
		session.removeAttribute("adminLogin");
		return "redirect:/login";
	}
	
}
