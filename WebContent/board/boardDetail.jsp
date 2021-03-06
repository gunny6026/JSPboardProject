<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>메인 화면</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</head>
<body>
	<jsp:include page="../component/header.jsp" flush="false" />
	<h2> 게시판</h2>
<div class="container">
  <table class="table table-striped" style="text-align :center; border: 1px solid #dddddd">
				<thead>
					<tr>
						<th>번호&nbsp;${content_view.no}</th>
						<th colspan="3" style="background-color: #eeeeee; text-align: center;">상세보기</th>
					</tr>
				</thead>
				<tbody>	
				<tr>
				 	<td>작성자</td>
				 	<td>${content_view.name}</td>
				 	<td>조회수</td>
				 	<td>${content_view.readCount}</td>
				</tr>
				<tr>
				 	<td>제목</td>
				 	<td>${content_view.title}</td>
				 	<td>날짜</td>
				 	<td>${content_view.date}</td>
				</tr>
				<tr>
				 	<td>내용</td>
				 	<td colspan="4"><textarea name="" id="" cols="100" rows="10" readonly>${content_view.content}</textarea></td>
				</tr>
				
				</tbody>
				</table>
  
  <a class="btn btn-primary" href="modify_view.jsp?no=${content_view.no }">수정</a>
   <a class="btn btn-danger"  href="delete.do?no=${content_view.no }" >삭제</a>
    <a class="btn btn-warning"  href="list.do" >목록</a>
</div>
</body>
</html>