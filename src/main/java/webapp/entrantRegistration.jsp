<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	
	<title><spring:message code='entrant_registration.title' /></title>
	
	<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
	
	<style>
	.fl_upld {
		width: 200px;
		margin-top: 10px;
	}
	
	#fl_inp {
		display: none;
	}
	
	.fl_upld label {
		cursor: pointer;
		background: pink;
		border-radius: 5px;
		padding: 10px 20px;
		color: whitesmoke;
		font-weight: bold;
		text-align: center;
	}
	
	.fl_upld label:hover {
		background: greenyellow;
	}
	
	#fl_nm {
		margin-top: 10px;
		color: greenyellow;
	}
	</style>
</head>

<body>
	<div class="w3-sidebar w3-light-grey w3-bar-block" style="width: 10%">
		<h3 class="w3-bar-item"><spring:message code='entrant_registration.menu' /></h3> 
		<a href="/home" class="w3-bar-item w3-button"><spring:message code='entrant_registration.home' /></a>
		
		<div class="container" style="margin-left: 1%">
			<c:if test="${pageContext.request.userPrincipal.name != null}">
				<form id="logoutForm" method="POST" action="${contextPath}/logout">
					<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
				</form>
				
				<a class="w3-bar-item w3-button" onclick="document.forms['logoutForm'].submit()"><spring:message code='home.logout' /></a>
			</c:if>
		</div>
	</div>

	<div style="margin-left: 10%">
		<div class="w3-container w3-teal">
			<h2><spring:message code='entrant_registration.entrant_registration' /></h2>
		</div>

		<div class="w3-container" style="padding: 0px; width: 25%; margin-left: 35%; margin-top: 5%;">
			<form:form method="POST" action="${contextPath}/addMarks" enctype="multipart/form-data">
				<table>
					<tr>
						<th style="padding-bottom: 10px; text-align: left; font-style: italic;"><spring:message code='entrant_registration.enter_marks' /></th>
					</tr>

					<c:forEach items="${registeredEntrant.faculty.subjects}" var="currentSubject" varStatus="status">
						<tr>
							<td style="padding-right: 30px; font-style: italic;">${currentSubject.title}</td>
							<td><input type="number" name="marks" required="required" min="130" max="200" step="0.01" style="width: 220px;"/></td>
						</tr>
					</c:forEach>

					<tr>
						<td><input type="hidden" name="facultyId" value="${registeredEntrant.faculty.id}" /></td>
						<td><input type="hidden" name="userEmail" value="${registeredEntrant.user.email}" /></td>
					</tr>
					
					<tr>
						<td style="font-style: italic;"><spring:message code='entrant_registration.upload_photo' /></td>
						
						<td>
							<div class="fl_upld">
								<label><input id="fl_inp" type="file" name="image"><spring:message code='entrant_registration.choose_photo' /></label>
								<div id="fl_nm"><spring:message code='entrant_registration.file_is_not_choosed' /></div>
							</div>
						</td>
						
					</tr>

					<tr>
						<td>
							<div class="fl_upld">
								<label style="float: left;"><input id="fl_inp" type="submit"><spring:message code='entrant_registration.register' /></label>
							</div>
						</td>
					</tr>

				</table>

				<input type="hidden" name="${_csrf.parameterName}"
					value="${_csrf.token}" />
			</form:form>
		</div>
	</div>

	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
	
	<script	src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.0/jquery.min.js"></script>
	
	<script>
		$(document).ready(function() {
			$("#fl_inp").change(function() {
				var filename = $(this).val().replace(/.*\\/, "");
				$("#fl_nm").html(filename);
			});
		});
	</script>
</body>
</html>