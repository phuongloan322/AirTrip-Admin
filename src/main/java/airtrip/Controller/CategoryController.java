package airtrip.Controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;

import airtrip.Model.bean.AccountAdminbean;
import airtrip.Model.bean.Categorybean;
import airtrip.Model.bean.LilteCategorybean;
import airtrip.Model.bo.Categorybo;

@Controller
public class CategoryController {
	
	private Categorybo categorybo = new Categorybo();

	@RequestMapping(value="/manager-category")
	public ModelAndView getCategory(Model model, HttpServletRequest request, HttpSession session) {
		try {
			AccountAdminbean adminLogin = (AccountAdminbean)session.getAttribute("adminLogin");
			if(adminLogin == null) {
				return new ModelAndView("login");
			}
			
			String search = request.getParameter("search");
			
			if(search != null) {
				List<Categorybean> categoryList = categorybo.getCategory(search);
				session.setAttribute("categoryList", categoryList);
				if(search != null)
					model.addAttribute("msg", categoryList.size()+ " kết quả tìm kiếm cho: "+search);
			}
			else {
				List<Categorybean> categoryList = categorybo.getAll();
				session.setAttribute("categoryList", categoryList);
			}			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ModelAndView("managerCategory");
	}
	
	@RequestMapping(value="/manager-category/findById/{categoryId}")
	public void findById(Model model, HttpServletRequest request, HttpServletResponse response, HttpSession session, @PathVariable String categoryId) {
		try {
			response.setContentType("application/json");
		    response.setCharacterEncoding("UTF-8");
			Gson data = new Gson();
			Categorybean categorybean = categorybo.getCategoryId(categoryId);
			String category = data.toJson(categorybean);
			response.getWriter().write(category);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value="/manager-category/add")
	public ModelAndView addCategory(Model model, HttpServletRequest request, HttpSession session) {
		try {
			String msg = null;
			String categoryId = request.getParameter("categoryId");
			String name = request.getParameter("name");
			
			Categorybean categorybean = new Categorybean(categoryId, name);
			
			int rs = categorybo.AddCategoryPlace(categorybean);
			
			if(rs > 0) {
				msg = "addOK";
			}
			else {
				msg = "addERROR";
			}
			
			model.addAttribute("msg", msg);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ModelAndView("redirect:/manager-category");
	}
	
	@RequestMapping(value="/manager-category/edit")
	public ModelAndView editCategory(Model model, HttpServletRequest request, HttpSession session) {
		try {
			session.removeAttribute("msg");
			String msg = null;
			
			String categoryId = request.getParameter("categoryId");
			String name = request.getParameter("name");
			
			Categorybean categorybean = new Categorybean(categoryId, name);
			
			int rs = categorybo.EditCategoryPlace(categorybean);
			
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
		return new ModelAndView("redirect:/manager-category");
	}
	
	@RequestMapping(value="/manager-category/delete/{categoryId}")
	public ModelAndView delereCategory(Model model, HttpServletRequest request, HttpSession session, @PathVariable String categoryId) {
		try {
			session.removeAttribute("msg");
			String msg = null;    
			
			int rs = categorybo.DeleteCategoryPlace(categoryId);
			
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
		return new ModelAndView("redirect:/manager-category");
	}
	
	//xem chi tiet
	
	@RequestMapping(value="/category/{categoryId}")
	public ModelAndView Category(Model model, HttpServletRequest request, HttpSession session, @PathVariable String categoryId) {
		try {
			
			AccountAdminbean adminLogin = (AccountAdminbean)session.getAttribute("adminLogin");
			if(adminLogin == null) {
				return new ModelAndView("login");
			}
			
			String search = request.getParameter("search");
			if(search != null) {
				List<LilteCategorybean> liltecategoryList = categorybo.getLitleCategorySearch(search);
				model.addAttribute("litleCategoryList", liltecategoryList);
			}
			else {
				List<LilteCategorybean> liltecategoryList = categorybo.getLitleCategory(categoryId);
				model.addAttribute("litleCategoryList", liltecategoryList);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ModelAndView("detailCategory");
	}
	//ngang ni
	@RequestMapping(value="/litlecategory/add")
	public ModelAndView addLitleCategory(Model model, HttpServletRequest request, HttpSession session) {
		try {
			session.removeAttribute("msg");
			String msg = null;
			String litleCategoryId = request.getParameter("litleCategoryId");
			String litleName = request.getParameter("litleName");
			String detail = request.getParameter("detail");
			String categoryId = request.getParameter("categoryId");
			
			LilteCategorybean litlecategory = new LilteCategorybean(litleCategoryId, litleName, detail, categoryId);
			
			int rs = categorybo.AddLitleCategoryPlace(litlecategory);
			
			if(rs > 0) {
				msg = "addOK";
			}
			else {
				msg = "addERROR";
			}
			
			model.addAttribute("msg", msg);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ModelAndView("redirect:/manager-category");
	}
	
	@RequestMapping(value="/litlecategory/edit/{litleCategoryId}")
	public ModelAndView editLitleCategory(Model model, HttpServletRequest request, HttpSession session, @PathVariable String litleCategoryId) {
		try {
			session.removeAttribute("msg");
			String msg = null;
			String litleName = request.getParameter("litleName");
			String detail = request.getParameter("detail");
			String categoryId = request.getParameter("categoryId");
			
			LilteCategorybean litlecategory = new LilteCategorybean(litleCategoryId, litleName, detail, categoryId);
			
			int rs = categorybo.EditLitleCategoryPlace(litlecategory);
			
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
		return new ModelAndView("redirect:/manager-category");
	}
	
	@RequestMapping(value="/litlecategory/delete/{litleCategoryId}")
	public ModelAndView delereLitleCategory(Model model, HttpServletRequest request, HttpSession session, @PathVariable String litleCategoryId) {
		try {
			session.removeAttribute("msg");
			String msg = null;    
			
			int rs = categorybo.DeleteLitleCategoryPlace(litleCategoryId);
			
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
		return new ModelAndView("redirect:/manager-category");
	}
	
}
