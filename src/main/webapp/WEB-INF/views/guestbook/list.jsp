<%@page import="kr.co.itcen.mysite.vo.GuestBookVo"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	List<GuestBookVo> list = (List)request.getAttribute("list");
	String path = request.getContextPath();
%>
<!DOCTYPE html>
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="<%=request.getContextPath() %>/assets/css/guestbook.css" rel="stylesheet" type="text/css">
</head>
<body>
	<div id="container">
		<jsp:include page="/WEB-INF/views/includes/header.jsp" />
		<div id="content">
			<div id="guestbook">
				<form action="<%=path%>/guestbook" method="post">
					<table>
						<tr>
							<td>이름</td><td><input type="text" name="name"></td>
							<td>비밀번호</td><td><input type="password" name="password"></td>
						</tr>
						<tr>
							<td colspan=4><textarea name="contents" id="content"></textarea></td>
						</tr>
						<tr>
							<td colspan=4 align=right><input type="submit" VALUE=" 확인 "></td>
						</tr>
					</table>
					<input type="hidden" name="a" value="add">
				</form>
				<ul>
					<%
						int count = list.size();
						for(GuestBookVo vo : list){ 
					%>
					<li>						
						<table>
							<tr>
								<td><%=count%></td>
								<td><%=vo.getName() %></td>
								<td><%=vo.getDate() %></td>
								<td><a href="<%=path%>/guestbook?a=deleteform&no=<%=vo.getNo()%>">삭제</a></td>
							</tr>
							<tr>
								<td colspan=4>
									<%=vo.getContents().replaceAll("\n", "<br>") %>
								</td>
							</tr>
						</table>
						<br>
					</li>
					<%
							count--;
						}						
					%>
				</ul>
			</div>
		</div>
		<jsp:include page="/WEB-INF/views/includes/navigation.jsp" />
		<jsp:include page="/WEB-INF/views/includes/footer.jsp" />
	</div>
</body>
</html>