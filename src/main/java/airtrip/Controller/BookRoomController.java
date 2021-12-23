package airtrip.Controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
import airtrip.Model.bean.BookRoombean;
import airtrip.Model.bean.Placebean;
import airtrip.Model.bo.BookRoombo;

@Controller
public class BookRoomController {
  
	private BookRoombo bookbo = new BookRoombo();
	
	@RequestMapping(value="/manager-bookroom")
	public ModelAndView getAllBookRoom(Model model, HttpServletRequest request, HttpSession session) {
		try {
			AccountAdminbean adminLogin = (AccountAdminbean)session.getAttribute("adminLogin");
			if(adminLogin == null) {
				return new ModelAndView("login");
			}
			
			String search = request.getParameter("search");
			
			String msg = null;
			
			if(search != null) {
				List<BookRoombean> searchList = bookbo.getBookRoomBySearch(search);
				session.setAttribute("bookroomList", searchList);
				if(search != "")
					model.addAttribute("msg", searchList.size() + " kết quả tìm kiếm cho: "+search);
			}
			else {
				List<BookRoombean> bookroomList = bookbo.getBookRoomByPaging(1, 10);
				session.setAttribute("bookroomList", bookroomList);
				
				int totalPlace = bookbo.getBookRoom().size();
				int totalPageNumber = 1;
				if(totalPlace > 0) {
					if(totalPlace % 10 == 0)
						 totalPageNumber = totalPlace/10;
					else
						totalPageNumber = totalPlace/10 + 1;
				}
				session.setAttribute("totalPageNumber", totalPageNumber);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ModelAndView("managerBookRoom");
	}
	
	@RequestMapping(value = "/getBookRoomByPaging", method = RequestMethod.GET)
	public @ResponseBody List<BookRoombean> getBookRoomByPaging(
			@RequestParam(name = "pagination") int pagination ) {
			List<BookRoombean> bookroomList = new ArrayList<BookRoombean>();

		try {
			
			bookroomList = bookbo.getBookRoomByPaging(pagination, 10);
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		return bookroomList;
	}
	
	@RequestMapping(value="/manager-bookroom/account/{accountId}")
	public ModelAndView getBookRoomByAccount(Model model, HttpServletRequest request, HttpSession session, @PathVariable long accountId) {
		try {
			
			List<BookRoombean> bookroomByAccount = bookbo.getBookRoom(accountId);
			session.setAttribute("bookroomByAccount", bookroomByAccount);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ModelAndView("managerBookRoom");
	}
	
	@RequestMapping(value="/manager-bookroom/findById/{bookId}")
	public void findById(Model model, HttpServletRequest request, HttpServletResponse response, HttpSession session, @PathVariable long bookId) {
		try {
			response.setContentType("application/json");
		    response.setCharacterEncoding("UTF-8");
			Gson data = new Gson();
			BookRoombean roombean = bookbo.getBookRoomById(bookId);
			String bookroom = data.toJson(roombean);
			response.getWriter().write(bookroom);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value="/manager-bookroom/delete/{bookId}")
	public ModelAndView deleteBookRoom(Model model, HttpServletRequest request, HttpSession session, @PathVariable long bookId) {
		try {
			session.removeAttribute("msg");
			String msg = null;
			
			int rs = bookbo.deleteBookRoom(bookId);
			
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
		return new ModelAndView("redirect:/manager-bookroom");
	}
}
