package kr.co.itcen.mysite.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.itcen.mysite.dao.GuestBookDao;
import kr.co.itcen.mysite.vo.GuestBookVo;
import kr.co.itcen.web.WebUtils;

public class GuestbookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		String action = request.getParameter("a");
		String forwardPath = "/WEB-INF/views/guestbook/";

		if ("add".equals(action)) {
			String name = request.getParameter("name");
			String password = request.getParameter("password");
			String contents = request.getParameter("contents");

			GuestBookVo vo = new GuestBookVo();
			vo.setName(name);
			vo.setPassword(password);
			vo.setContents(contents);

			new GuestBookDao().insert(vo);

			WebUtils.redirect(request, response, request.getContextPath() + "/guestbook");
			
		} else if ("deleteform".equals(action)) {
			
			WebUtils.forward(request, response, forwardPath+"deleteform.jsp");

		} else if ("delete".equals(action)) {
			String paramno = request.getParameter("no");
			String password = request.getParameter("password");
			Long no = Long.parseLong(paramno);

			GuestBookVo vo = new GuestBookVo();
			vo.setNo(no);
			vo.setPassword(password);

			new GuestBookDao().delete(vo);
			
			WebUtils.redirect(request, response, request.getContextPath() + "/guestbook");
			
		} else {
			
			List<GuestBookVo> list = new GuestBookDao().getList();
			request.setAttribute("list", list);

			WebUtils.forward(request, response, forwardPath+"list.jsp");
			
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
