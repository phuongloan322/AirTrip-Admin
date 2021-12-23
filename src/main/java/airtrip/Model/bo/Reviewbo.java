package airtrip.Model.bo;

import java.util.List;

import airtrip.Model.bean.Reviewbean;
import airtrip.Model.dao.Reviewdao;

public class Reviewbo {
	
	private Reviewdao reviewdao = new Reviewdao();

	public List<Reviewbean> getAllReview() throws Exception {
		return reviewdao.getAllReview();
	}
	
	public List<Reviewbean> getReviewBySearch(String search) throws Exception{
		return reviewdao.getReviewBySearch(search);
	}
	
	public List<Reviewbean> getReviewByDate(String search) throws Exception {
		return reviewdao.getReviewByDate(search);
	}
	
	public List<Reviewbean> getReviewByPaging(int PageNumber, int PageSize) throws Exception {
		return reviewdao.getReviewByPaging(PageNumber, PageSize);
	}
	
	public int DeleteReview(long reviewId) throws Exception {
		return reviewdao.DeleteReview(reviewId);
	}
}
