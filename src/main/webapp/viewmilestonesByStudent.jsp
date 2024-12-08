<%@page import="com.klef.jfsd.sdp.model.Student"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%
    Student student = (Student) session.getAttribute("student");
    if (student == null) {
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
    <style type="text/css">
    /* Progress Bar Container */
/* Progress bar styles */
.progress-bar {
    width: 100%;
    background-color: #e0e0e0;
    border-radius: 5px;
    overflow: hidden;
    height: 10px;
    margin: 10px 0;
    position: relative;
}

.progress-fill {
    background-color: #4caf50;
    height: 100%;
    text-align: center;
    color: white;
    border-radius: 5px 0 0 5px;
    transition: width 0.5s ease-in-out;
}

    
    </style>
</head>
<body id="body-pd">
    <%@include file="studentnavbar.jsp" %>
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
                            <p><strong>Progress:</strong></p>
					<div class="progress-bar">
					    <div class="progress-fill" style="width: <c:out value="${project.progress}"/>%;"></div>
					</div>
					<p><c:out value="${project.progress}"/>%</p> <br>
                             <a class="btn view-more-btn" href='<c:url value="viewprojectwithmilestones?id=${project.id}"></c:url>'>View More</a>
	                        </div>
                        <div class="vi_right">
                            <span class="status ${project.getStatus() == 'COMPLETED' ? 'complete' : 'incomplete'}"> 
                  <c:out value="${project.status}"/>
              </span>    
                        </div>
                    </div>
                </c:forEach>
            </div>
            </c:otherwise>
            </c:choose>
        </div>
    </div>
	    <script>
	    function confirmDelete() {
	        return confirm("Are you sure you want to delete this project?");
	    }
	</script>
    <script src="https://unpkg.com/ionicons@5.1.2/dist/ionicons.js"></script>
    <script src="script.js"></script>
</body>
</html>
<%
    }
%>
