package airtrip.Model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import airtrip.Model.bean.Categorybean;
import airtrip.Model.bean.DetailPlacebean;

public class DetailPlacedao {
	PreparedStatement pst;
	Statement st;
	ResultSet rs;
	DungChung dc = new DungChung();

	public DetailPlacebean getDetailPlace(long detailId) throws Exception {
		DetailPlacebean detail = new DetailPlacebean();
		String sql = "SELECT * FROM DetailPlace WHERE detailId = ?";
		dc.KetNoi();
		pst = dc.cn.prepareStatement(sql);
		pst.setLong(1, detailId);
		rs = pst.executeQuery();
		while(rs.next()) {
			String[] tienichs = rs.getString("tienich").split("[;]");
			detail = new DetailPlacebean(rs.getLong("detailId"), rs.getInt("phongkhach"), rs.getInt("phongngu")
					, rs.getInt("giuong"), rs.getInt("phongvs"), tienichs);
		}
		rs.close();
		return detail;
	}
	
	
}
