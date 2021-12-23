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
import airtrip.Model.bean.Categorybean;
import airtrip.Model.bean.Placebean;
import airtrip.Model.bo.Placebo;

@Controller
public class PlaceController {
	
	private Placebo placebo = new Placebo();

	@RequestMapping(value="/manager-place")
	public ModelAndView getAllPlace(Model model, HttpServletRequest request, HttpSession session) {
		try {
			AccountAdminbean adminLogin = (AccountAdminbean)session.getAttribute("adminLogin");
			if(adminLogin == null) {
				return new ModelAndView("login");
			}
			
			String search  = request.getParameter("search");
			
			if(search != null) {
				List<Placebean> placeSearchList = placebo.getAllPlace(search);
				session.setAttribute("placeList", placeSearchList);
				if(search != "")
					model.addAttribute("msg", placeSearchList.size() + " kết quả tìm kiếm cho: "+search);
			}
			else {
				List<Placebean> placeList = placebo.getPlaceByPaging(1, 20);
				session.setAttribute("placeList", placeList);
				
				int totalPlace = placebo.getAllPlace().size();
				int totalPageNumber = 1;
				if(totalPlace > 0) {
					if(totalPlace % 18 == 0)
						 totalPageNumber = totalPlace/20;
					else
						totalPageNumber = totalPlace/20 + 1;
				}
				session.setAttribute("totalPageNumber", totalPageNumber);
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ModelAndView("managerPlace");
	}
	
	@RequestMapping(value = "/getPlaceByPaging", method = RequestMethod.GET)
	public @ResponseBody List<Placebean> getPlaceByPaging(HttpServletRequest request,
			@RequestParam(name = "pagination") int pagination ) {
			List<Placebean> placeList = new ArrayList<Placebean>();

		try {
			
			placeList = placebo.getPlaceByPaging(pagination, 20);
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		return placeList;
	}
	
	@RequestMapping(value="/manager-place/{placeId}")
	public ModelAndView getPlaceId(Model model, HttpServletRequest request, HttpSession session, @PathVariable long placeId) {
		try {
			AccountAdminbean adminLogin = (AccountAdminbean)session.getAttribute("adminLogin");
			if(adminLogin == null) {
				return new ModelAndView("login");
			}

			Placebean place = placebo.findById(placeId);
			session.setAttribute("detailPlace", place);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ModelAndView("placeById");
	}
	
	@RequestMapping(value="/manager-place/delete/{placeId}")
	public ModelAndView deletePlace(Model model, HttpServletRequest request, HttpSession session, @PathVariable long placeId) {
		try {
			session.removeAttribute("msg");
			String msg = null;
			
			int rs = placebo.DeletePlace(placeId);
			
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
		return new ModelAndView("redirect:/manager-place");
	}
	
	@RequestMapping(value="/manager-place/findById/{placeId}")
	public void findById(Model model, HttpServletRequest request, HttpServletResponse response, HttpSession session, @PathVariable long placeId) {
		try {
			response.setContentType("application/json");
		    response.setCharacterEncoding("UTF-8");
			Gson data = new Gson();
			Placebean placebean = placebo.getPlaceId(placeId);
			String place = data.toJson(placebean);
			response.getWriter().write(place);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value="/manager-place/status", method = RequestMethod.GET)
	public @ResponseBody Boolean StatusOffPlaceAjax(
			@RequestParam("placeId") long placeId) {
		
		try {
			int rs = 0;
			
			Placebean placebean = placebo.getPlaceId(placeId);
			
			if(placebean.getIsEmpty() == false)
				rs = placebo.EditPlace(true, placeId);
			else
				rs = placebo.EditPlace(false, placeId);
			
			if(rs > 0) {
				Placebean place = placebo.getPlaceId(placeId);
				return place.getIsEmpty();
			}
			else {
				return placebean.getIsEmpty();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
}
	
	