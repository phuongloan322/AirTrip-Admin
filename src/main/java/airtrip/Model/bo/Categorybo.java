package airtrip.Model.bo;

import java.util.ArrayList;
import java.util.List;

import airtrip.Model.bean.Categorybean;
import airtrip.Model.bean.LilteCategorybean;
import airtrip.Model.dao.Categorydao;
import airtrip.Model.dao.LilteCategorydao;

public class Categorybo {

	private Categorydao categorydao = new Categorydao();
	private LilteCategorydao lilteCategorydao = new LilteCategorydao();
	
	public Categorybean getCategoryId(String ma) throws Exception {
		return categorydao.getCategoryId(ma);
	}
	
	public List<Categorybean> getAll() throws Exception {
		return categorydao.getAll();
	}
	
	public List<Categorybean> getCategory(String search) throws Exception {
		return categorydao.getCategory(search);
	}
	
	public int AddCategoryPlace(Categorybean categorybean) throws Exception {
		return categorydao.AddCategoryPlace(categorybean);
	}
	
	public int EditCategoryPlace(Categorybean categorybean) throws Exception {
		return categorydao.EditCategoryPlace(categorybean);
	}
	
	public int DeleteCategoryPlace(String CategoryId) throws Exception {
		return categorydao.DeleteCategoryPlace(CategoryId);
	}

	public List<LilteCategorybean> getLitleCategorySearch(String search) throws Exception {
		return lilteCategorydao.getSearch(search);
	}
	
	public List<LilteCategorybean> getLitleCategory(String CategoryId) throws Exception {
		return lilteCategorydao.getAll(CategoryId);
	}
	
	public int AddLitleCategoryPlace(LilteCategorybean litleCategorybean) throws Exception {
		return lilteCategorydao.AddLitleCategoryPlace(litleCategorybean);
	}
	
	public int EditLitleCategoryPlace(LilteCategorybean litleCategorybean) throws Exception {
		return lilteCategorydao.EditLitleCategoryPlace(litleCategorybean);
	}
	
	public int DeleteLitleCategoryPlace(String litleCategoryId) throws Exception {
		return lilteCategorydao.DeleteLitleCategoryPlace(litleCategoryId);
	}
}
