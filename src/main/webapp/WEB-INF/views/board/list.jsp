<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/includes/jstl.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="${path }/assets/css/board.css" rel="stylesheet" type="text/css">
</head>
<body>
	<div id="container">
		<jsp:include page="/WEB-INF/views/includes/header.jsp" />
		<div id="content">
			<div id="board">
				<form id="search_form" action="${path }/board?a=list&page=1" method="post">
					<input type="text" id="kwd" name="kwd" value="">
					<input type="submit" value="찾기">
				</form>
				<table class="tbl-ex">
					<tr>
						<th>번호</th>
						<th>제목</th>
						<th>글쓴이</th>
						<th>조회수</th>
						<th>작성일</th>
						<th>&nbsp;</th>
					</tr>				
					<c:set var="count" value="${fn:length(list) }"/>
					<c:forEach items="${list }" var="vo" varStatus="status">	
					<tr>
						<td>${count-status.index }</td>
						<td style="padding-left:${30*vo.depth-20}px;text-align:left">
							<c:if test="${vo.depth > 0 }">
								<img src="${path }/assets/images/reply.png"/>
							</c:if>							
							<c:choose>
								<c:when test="${vo.removed==false}">
									<a href="${path }/board?a=view&no=${vo.no }&kwd=${param.keyWord }&page=${param.page }">${vo.title }</a>							
								</c:when>								
								<c:when test="${vo.removed==true}">								
									<span>삭제된 글</span>	
								</c:when>								
							</c:choose>							
						</td>
						<td>${vo.userName }</td>
						<td>${vo.hit }</td>
						<td>${vo.regDate }</td>
						<td><a href="${path }/board?a=delete&no=${vo.no }&uno=${vo.userNo}" class="del">삭제</a></td>
					</tr>
					</c:forEach>	
				</table>
				
				<!-- pager 추가 -->
				<div class="pager">
					<ul>
						<li><a href="">◀</a></li>
						<li><a href="">1</a></li>
						<li class="selected">2</li>
						<li><a href="">3</a></li>
						<li>4</li>
						<li>5</li>
						<li><a href="">▶</a></li>
					</ul>
				</div>					
				<!-- pager 추가 -->
				
				<div class="bottom">
					<a href="${path }/board?a=writeform" id="new-book">글쓰기</a>
				</div>				
			</div>
		</div>
		<jsp:include page="/WEB-INF/views/includes/navigation.jsp" />
		<jsp:include page="/WEB-INF/views/includes/footer.jsp" />
	</div>
</body>
</html>