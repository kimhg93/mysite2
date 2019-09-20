package kr.co.itcen.mysite.action.board;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.co.itcen.mysite.dao.BoardDao;
import kr.co.itcen.mysite.vo.BoardVo;
import kr.co.itcen.web.WebUtils;
import kr.co.itcen.web.mvc.Action;

public class ListAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		
		int page = 1;
		if("".equals(request.getParameter("page"))||request.getParameter("page")==null) {
			page = 1;			
		} else {
			page = Integer.parseInt(request.getParameter("page"));
		}
				
		String keyWord = request.getParameter("kwd");
		
		/////////////////
				
		String pageFunction = request.getParameter("pagef");		
		int countAll = new BoardDao().countAll();
		int pageAll = countAll%5==0?countAll/5:countAll/5+1;
		
		int startPage = (page%5)==0?((page/5)-1)*5+1:((page/5)*5)+1;
		int lastPage = startPage + 4;
		if("next".equals(pageFunction)) {
			startPage= page;
			lastPage= startPage+4;
		} else if("prev".equals(pageFunction)) {
			startPage= page-4;
			lastPage= page;
		}
		
		if(pageAll<lastPage) {
			lastPage = pageAll;
		}
		
		request.setAttribute("startPage", startPage);
		request.setAttribute("lastPage", lastPage);
		
		//////////////////
		
		
		List<BoardVo> list =  new BoardDao().getList(page, keyWord);
		int countGroup = 0;
		request.setAttribute("list", list);		
		request.setAttribute("countGroup", countGroup);
		if(session.getAttribute("authUser")==null) {
			WebUtils.redirect(request, response, request.getContextPath()+"/user?a=loginform");
		} else {
			WebUtils.forward(request, response, "/WEB-INF/views/board/list.jsp");
		}
		
		
		
	}
}
