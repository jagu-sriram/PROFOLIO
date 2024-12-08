<%@page import="com.klef.jfsd.sdp.model.Mentor"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%
Mentor m = (Mentor) session.getAttribute("mentor");

if (m == null) {
%>
    <script type="text/javascript">
        alert("Session Expired. Please Login Again");
        window.location.href = "login"; 
    </script>
<%
    } else {
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <script src="https://kit.fontawesome.com/b99e675b6e.js"></script>
    <link rel="stylesheet" href="viewprojects.css">
    <title>ProFolio</title>
</head>
<body id="body-pd">
    <%@include file="mentornavbar.jsp" %>
    <div class="wrapper">
        <h2>All Projects</h2>
        <div class="view_main">
          <c:choose>
            <c:when test="${empty projectslist}">
                    <p class="no-data-message">No Projects Available</p>
             </c:when>
             <c:otherwise>
	            <div class="view_wrap list-view" style="display: block;">
	                <c:forEach var="project" items="${projectslist}">
	                    <div class="view_item">
	                        <div class="vi_left">
	                            <p class="title"><c:out value="${project.title}"/></p>
	                            <p class="content">Date Created: <c:out value="${project.datecreated}"/></p>
	                            <p class="content">Submitted By: <c:out value="${project.studentId}-${project.studentLastname}"/></p>
			                    <c:choose>
								    <c:when test="${project.pendingMilestones > 0}">
								        <p class="milestonestatuscontent pending">
								            <c:out value="${project.pendingMilestones}"/> milestones need to be reviewed
								        </p>
								    </c:when>
								    <c:otherwise>
								        <p class="milestonestatuscontent reviewed">All reviewed</p>
								    </c:otherwise>
								</c:choose>
	                             <a class="btn view-more-btn" href='<c:url value="viewprojectwithmilestonesbymentor?id=${project.id}"></c:url>'>View More</a>
	                        </div>
	                        <div class="vi_right">
	                            <span class="status ${project.projectStatus == 'COMPLETED' ? 'complete' : 'incomplete'}"> 
				                  <c:out value="${project.projectStatus}"/>
				              </span>   
	                        </div>
	                    </div>
	                </c:forEach>
	            </div>
            </c:otherwise>
           </c:choose>
        </div>
    </div>
	    
    <script src="https://unpkg.com/ionicons@5.1.2/dist/ionicons.js"></script>
    <script src="script.js"></script>
</body>
</html>
<%
    }
%>
