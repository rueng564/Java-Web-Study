<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<jsp:useBean id="member" scope="session" class="spms.vo.Member"></jsp:useBean>
<div style="background-color: #00008b; color: #ffffff; height: 20px; padding: 5px;">
SPMS(Simple Project Management System)
<span style="float:right;">
<% if(member.getEmail()!=null) { %>
<%=member.getName() %>
<a style="color:white;" href="<%=request.getContextPath() %>/auth/logout.do">로그아웃</a>
<% } else { %>
<a style="color:white;" href="<%=request.getContextPath() %>/auth/login.do">로그인</a>
<% } %>
</span>
</div>