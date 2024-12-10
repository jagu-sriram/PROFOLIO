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
     <link rel="stylesheet" href="viewportfoliofeedback.css"> 
    <title>All Projects</title>
    
</head>
<body id="body-pd">
    <%@include file="studentnavbar.jsp" %>
    <div class="wrapper">
        <h2>All Feedbacks</h2>
        <div class="view_main">
        <c:choose>
        	 <c:when test="${empty feedbacklist}">
                    <p class="no-data-message">No Feedbacks Given Yet</p>
             </c:when>
        <c:otherwise>
            <div class="view_wrap list-view" style="display: block;">
                <c:forEach var="feedback" items="${feedbacklist}">
                    <div class="view_item">
                        <div class="vi_left">
                            <p class="title"><c:out value="${feedback.message}"/></p>
                            <p class="content">Submitted date: <c:out value="${feedback.submitteddate}"/></p>
                            <p><strong>Rating:<c:out value="${feedback.rating}"/></strong></p>
							<p><strong>Submitted by:<c:out value="${feedback.mentor.id} ${feedback.mentor.lastname}"/></strong></p>
                             
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
