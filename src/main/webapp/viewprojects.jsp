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
    <title>My Projects - ProFolio</title>
    <link rel="stylesheet" href="viewprojects.css">
    <link href="https://fonts.googleapis.com/css2?family=Inter:wght@400;500;600;700&display=swap" rel="stylesheet">
</head>
<body>
    <%@include file="studentnavbar.jsp" %>
    
    <div class="wrapper">
        <h2>My Projects</h2>
        <div class="view_main">
            <c:choose>
                <c:when test="${empty projectslist}">
                    <p class="no-data-message">No projects available. Start creating your first project!</p>
                </c:when>
                <c:otherwise>
                    <div class="projects-list">
                        <c:forEach var="project" items="${projectslist}">
                            <div class="view_item">
                                <div class="vi_left">
                                    <h3 class="title"><c:out value="${project.title}"/></h3>
                                    <p class="content">Created on: <c:out value="${project.datecreated}"/></p>
                                    
                                    <div class="progress-section">
                                        <p class="progress-label">Project Progress</p>
                                        <div class="progress-bar">
                                            <div class="progress-fill" style="width: ${project.progress}%;"></div>
                                        </div>
                                        <p class="progress-text">${project.progress}% Complete</p>
                                    </div>

                                    <a class="btn view-more-btn" href='<c:url value="viewprojectdetails?id=${project.id}"></c:url>'>
                                        View Details
                                    </a>
                                </div>
                                
                                <div class="vi_right">
                                    <span class="status ${project.status == 'COMPLETED' ? 'complete' : 'incomplete'}">
                                        <c:out value="${project.status}"/>
                                    </span>
                                    
                                    <div class="actions">
                                        <a class="btn update-btn" href='<c:url value="updateproject?id=${project.id}"></c:url>'>
                                            Update Project
                                        </a>
                                        <a class="btn delete-btn" href='<c:url value="deleteproject?id=${project.id}"></c:url>' 
                                           onclick="return confirmDelete();">
                                            Delete Project
                                        </a>
                                    </div>
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
            return confirm("Are you sure you want to delete this project? This action cannot be undone.");
        }
    </script>
</body>
</html>
<%
    }
%>