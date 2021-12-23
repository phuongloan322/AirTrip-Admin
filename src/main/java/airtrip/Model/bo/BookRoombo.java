package airtrip.Model.bo;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import airtrip.Model.bean.Accountbean;
import airtrip.Model.bean.BookRoombean;
import airtrip.Model.bean.Placebean;
import airtrip.Model.dao.BookRoomdao;

public class BookRoombo {

	private BookRoomdao bookRoomdao = new BookRoomdao();
	
	public List<BookRoombean> getBookRoom() throws Exception {
		return bookRoomdao.getBookRoom();
	}
	
	public List<BookRoombean> getBookRoomByPaging(int PageNumber, int PageSize) throws Exception {
		return bookRoomdao.getBookRoomByPaging(PageNumber, PageSize);
	}
	
	public List<BookRoombean> getBookRoom(long accThueId) throws Exception {
		return bookRoomdao.getBookRoom(accThueId);
	}
	
	public List<BookRoombean> getBookRoomAccept(long accId, boolean isAccept) throws Exception {
		return bookRoomdao.getBookRoomAccept(accId, isAccept);
	}
	
	public List<BookRoombean> getBookRoomAllAccept(long accId) throws Exception {
		return bookRoomdao.getBookRoomAllAccept(accId);
	} 
	public long daysBetween2Dates(String startDay, String endDay) {
        return bookRoomdao.daysBetween2Dates(startDay, endDay);
    }
	
	public BookRoombean getBookRoomById(long bookId) throws Exception {
		return bookRoomdao.getBookRoomById(bookId);
	}
	
	public int deleteBookRoom(long bookId) throws Exception {
		return bookRoomdao.deleteBookRoom(bookId);
	}
	
	public List<BookRoombean> getBookRoomBySearch(String search) throws Exception {
		return bookRoomdao.getBookRoomBySearch(search);
	}
}
