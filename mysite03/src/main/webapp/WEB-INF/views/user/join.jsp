<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%> 
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="${pageContext.request.contextPath }/assets/css/user.css" rel="stylesheet" type="text/css">
</head>
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/includes/header.jsp"></c:import>
		<div id="content">
			<div id="user">
				<form:form 
					modelAttribute="userVo"
					id="join-form" 
					name="joinForm" 
					method="post" 
					action="${pageContext.request.contextPath }/user/join"> <!-- spring-servlet.xml -->
					
					<label class="block-label" for="name"><spring:message code="Join.form.label.name" /></label>
					<form:input path="name" /> <!-- modelAttribute -->
					<p style="text-align: left; padding: 2px 0 2px 0; color: red">
						<spring:hasBindErrors name="userVo">
						   <c:if test="${errors.hasFieldErrors('name') }">
						        <spring:message 
						        	code="${errors.getFieldError('name').codes[0] }" 
						        	text="${errors.getFieldError('name').defaultMessage }" />
						   </c:if>
						</spring:hasBindErrors>
					</p>

					<label class="block-label" for="email">이메일</label>
					<form:input path="email" /> <!-- modelAttribute -->
					<input type="button" value="가입 확인">
					<p style="text-align: left; padding: 2px 0 2px 0; color: red">
						<spring:hasBindErrors name="userVo">
						   <c:if test="${errors.hasFieldErrors('email') }">
						        <spring:message code="${errors.getFieldError('email').codes[0] }" />
						   </c:if>
						</spring:hasBindErrors>
					</p>
					
					<label class="block-label">패스워드</label>
					<form:password path="password" /> <!-- modelAttribute -->
					<p style="text-align: left; padding: 2px 0 2px 0; color: red">
						<spring:hasBindErrors name="userVo">
						   <c:if test="${errors.hasFieldErrors('password') }">
						        <spring:message code="${errors.getFieldError('password').codes[0] }" />
						   </c:if>
						</spring:hasBindErrors>
					</p>
					
					<fieldset>
						<legend>성별</legend>
						<form:radiobutton path="gender" value="female" label="여" checked="${userVo.gender == 'female' }"/>
						<form:radiobutton path="gender" value="male" label="남" checked="${userVo.gender == 'male' }"/>
						<!-- <c:choose>
							<c:when test="${userVo.gender == 'female' }">
								<label>여</label> <input type="radio" name="gender" value="female" checked="checked">
								<label>남</label> <input type="radio" name="gender" value="male">
							</c:when>
							<c:otherwise>
								<label>여</label> <input type="radio" name="gender" value="female">
								<label>남</label> <input type="radio" name="gender" value="male" checked="checked">
							</c:otherwise>
						</c:choose> -->
					</fieldset>
					
					<fieldset>
						<legend>약관동의</legend>
						<input id="agree-prov" type="checkbox" name="agreeProv" value="y">
						<label>서비스 약관에 동의합니다.</label>
					</fieldset>
					
					<input type="submit" value="가입하기">
					
				</form:form>
			</div>
		</div>
		<c:import url="/WEB-INF/views/includes/navigation.jsp"></c:import>
		<c:import url="/WEB-INF/views/includes/footer.jsp"></c:import>
	</div>
</body>
</html>