<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ page import="com.klef.jfsd.sdp.model.Project" %>
<%@ page import="com.klef.jfsd.sdp.model.Media" %>
<%@page import="com.klef.jfsd.sdp.model.Student"%>
<%@ page import="java.util.List" %>
<%
    Student student = (Student) session.getAttribute("student");
  /*   Project project = (Project) request.getAttribute("project");  */
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
<title>ProFolio</title>
    <link rel="stylesheet" href="viewprojectindetail.css">
    <link rel="stylesheet" href="viewprojectdetails.css">
    <style type="text/css">
    /* Progress Bar Container */
.progress-bar {
    width: 100%;
    background-color: #e0e0e0; /* Light grey background */
    border-radius: 5px;
    overflow: hidden;
    height: 10px; /* Height of the progress bar */
    margin-bottom: 10px;
    position: relative;
}

/* Filled part of the progress bar */
.progress-fill {
    background-color: #4caf50; /* Green for progress */
    height: 100%;
    text-align: center;
    line-height: 20px;
    color: white; /* Text color for visibility */
    border-radius: 5px 0 0 5px; /* Round the left side */
    transition: width 0.5s ease-in-out; /* Smooth transition */
}
    @media (max-width: 768px) {
    .progress-bar {
        height: 15px; /* Slightly smaller on smaller screens */
    }
    .progress-fill {
        font-size: 12px; /* Adjust font size */
    }
}
    </style>
</head>
<body>
<%@include file="studentnavbar.jsp" %>
    <div class="project-details-container">
        <h1><c:out value="${project.title}"/></h1>
        
        <p><strong>Description:</strong> <c:out value="${project.description}"/></p>
        <p><strong>Status:</strong> <c:out value="${project.status}"/></p>
        <p><strong>Project Link:</strong> <a href="<c:out value='${project.link}'/>" target="_blank">View Project</a></p>

       <br>
         
		
        <!-- Display Existing Feedback -->
        <%-- <div class="feedback-section">
            <h3>Feedback</h3>
            <c:forEach var="feedback" items="${project.feedbackList}">
                <div class="feedback-item">
                    <p><strong><c:out value='${feedback.adminName}'/>:</strong> <c:out value='${feedback.text}'/></p>
                    <p><em><c:out value='${feedback.date}'/></em></p>
                </div>
            </c:forEach>
        </div>
    
 --%>
    
    <a class="upload-media-button" href='<c:url value="viewmedia?id=${project.id}"></c:url>'>View Media</a> 
    
    <!-- Milestones Section -->
<h2>Project Milestones</h2>

<div class="milestones-section">
    <c:forEach var="milestone" items="${milestone}">
        <div class="milestone-item">
            <h3><c:out value="${milestone.orderIndex}"/>. <c:out value="${milestone.title}"/>
            
            </h3>
            <p><strong>Description:</strong> <c:out value="${milestone.description}"/></p>
            <p><strong>Progress:</strong></p>
<div class="progress-bar">
    <div class="progress-fill" style="width: <c:out value="${milestone.progressPercentage}"/>%;"></div>
</div>
<p><c:out value="${milestone.progressPercentage}"/>%</p>

            <p><strong>Status:</strong> <c:out value="${milestone.status}"/></p>
            <div class="actions">
	 <a class="btn update-btn" href='<c:url value="updatemilestone?id=${milestone.id}"></c:url>'>Update</a>
</div>
        </div>
    </c:forEach>
</div>
<br>
<div class="actions">
	 <a class="btn update-btn" href='<c:url value="proposemilestone?id=${project.id}"></c:url>'>Propose a Milestone</a>
</div>

</div>
    <script>
        function openMediaModal(url) {
            document.getElementById("mediaModal").style.display = "block";
            document.getElementById("mediaFullView").src = url;
        }

        function closeMediaModal() {
            document.getElementById("mediaModal").style.display = "none";
        }
    </script>
    <script src="https://unpkg.com/ionicons@5.1.2/dist/ionicons.js"></script>
    <script src="script.js"></script>
</body>
</html>
<%
    }
%>