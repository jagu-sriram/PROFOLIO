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
<body>
    <%@include file="mentornavbar.jsp" %>
    <div class="wrapper">
        <h2>Pending Milestones</h2>
        <div class="view_main">
            <c:choose>
                <c:when test="${empty pendingmilestones}">
                    <p class="no-data-message">No pending milestones to display.</p>
                </c:when>
                <c:otherwise>
                    <div class="view_wrap list-view" style="display: block;">
                        <c:forEach var="milestone" items="${pendingmilestones}">
                            <div class="view_item">
                                <div class="vi_left">
                                    <p class="title"><c:out value="${milestone.title}"/></p>
                                    <p class="content">MileStone Description: <c:out value="${milestone.description}"/></p>
                                    <p class="content">Project title: <c:out value="${milestone.project.title}"/></p>
                                    <p class="content">Submitted By: <c:out value="${milestone.project.student.id}-${milestone.project.student.lastname}"/></p> 
                                    <a class="btn view-more-btn" href='<c:url value="viewprojectwithmilestonesbymentor?id=${milestone.project.id}"></c:url>'>View Project</a>
                                </div>
                                <div class="vi_right">
                                    <div class="actions">
                                        <a class="btn update-btn" href='<c:url value="acceptmilestone?id=${milestone.id}"></c:url>'>Accept</a>
                                        <a class="btn delete-btn" href='<c:url value="rejectmilestone?id=${milestone.id}"></c:url>'>Reject</a>
                                    </div>
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
