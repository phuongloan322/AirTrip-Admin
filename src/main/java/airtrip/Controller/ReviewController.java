package airtrip.Controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import airtrip.Model.bean.AccountAdminbean;
import airtrip.Model.bean.Accountbean;
import airtrip.Model.bean.Reviewbean;
import airtrip.Model.bo.Reviewbo;

@Controller
public class ReviewController {

	private Reviewbo reviewbo = new Reviewbo();
	
	@RequestMapping(value="/manager-review")
	public ModelAndView ManagerAccount(Model model, HttpServletRequest request, HttpSession session) {
		try {
			AccountAdminbean adminLogin = (AccountAdminbean)session.getAttribute("adminLogin");
			if(adminLogin == null) {
				return new ModelAndView("login");
			}
			
			String search = request.getParameter("search");
			
			if(search != null) {
				
				List<Reviewbean> reviewList = new ArrayList<Reviewbean>();
				
				String[] date = search.split("-");
				if(date.length == 3) {
					search = date[2]+"-"+date[1]+"-"+date[0];
					reviewList = reviewbo.getReviewByDate(search);
				}
				else
					reviewList = reviewbo.getReviewBySearch(search);
					
				session.setAttribute("reviewList", reviewList);
				
				if(search != "")
					model.addAttribute("msg", reviewList.size() +" kết quả tìm kiếm cho: " + search);
			}
			else {
				List<Reviewbean> reviewList = reviewbo.getReviewByPaging(1, 5);
				session.setAttribute("reviewList", reviewList);
			}
			
			int totalPlace = reviewbo.getAllReview().size();
			int totalPageNumber = 1;
			if(totalPlace > 0) {
				if(totalPlace % 5 == 0)
					 totalPageNumber = totalPlace/5;
				else
					totalPageNumber = totalPlace/5 + 1;
			}
			model.addAttribute("totalPageNumber", totalPageNumber);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ModelAndView("managerReview");
	}
	
	@RequestMapping(value = "/getReviewByPaging", method = RequestMethod.GET)
	public @ResponseBody List<Reviewbean> getAccountByPaging(HttpServletRequest request,
			@RequestParam(name = "pagination") int pagination ) {
			List<Reviewbean> reviewList = new ArrayList<Reviewbean>();

		try {
			
			reviewList = reviewbo.getReviewByPaging(pagination, 5);
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		return reviewList;
	}
	
	@RequestMapping(value="/manager-review/delete/{reviewId}")
	public ModelAndView AddAccount(Model model, HttpServletRequest request, HttpSession session, @PathVariable long reviewId) {
		try {
			session.removeAttribute("msg");
			String msg = "";
			
			int rs = reviewbo.DeleteReview(reviewId);
			
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
		return new ModelAndView("redirect:/manager-review");
	}
}
