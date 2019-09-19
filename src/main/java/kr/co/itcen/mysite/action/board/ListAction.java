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
		if(request.getParameter("page")!=null) {
			page = Integer.parseInt(request.getParameter("page"));
		}
				
		String keyWord = request.getParameter("kwd");
		
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
