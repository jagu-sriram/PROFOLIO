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
    <title>All Projects</title>
</head>
<body id="body-pd">
    <%@include file="mentornavbar.jsp" %>
    <div class="wrapper">
        <h2>All Portfolios</h2>
        <div class="view_main">
        <c:choose>
        <c:when test="${empty portfoliolist}">
                    <p class="no-data-message">No PortFolios Available to Review</p>
             </c:when>
        <c:otherwise>
            <div class="view_wrap list-view" style="display: block;">
                <c:forEach var="portfolio" items="${portfoliolist}">
                    <div class="view_item">
                        <div class="vi_left">
                            
                            <p class="content">Submitted By: <c:out value="${portfolio.student.id}-${portfolio.student.lastname}"/></p>
                             <a class="btn view-more-btn" href='<c:url value="viewportfolioByID?id=${portfolio.id}"></c:url>'>View More</a>
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
	    
    <script src="https://unpkg.com/ionicons@5.1.2/dist/ionicons.js"></script>
    <script src="script.js"></script>
</body>
</html>
<%
    }
%>
