<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ page import="com.klef.jfsd.sdp.model.Mentor" %>
<%
Mentor m = (Mentor) session.getAttribute("mentor");

if (m == null) {
%>
    <script type="text/javascript">
        alert("Mentor Session Expired. Please Login Again");
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
    <link rel="stylesheet" href="viewprojectdetails.css">
</head>
<body>
<%@include file="mentornavbar.jsp" %>
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
    
    <a class="upload-media-button" href='<c:url value="viewmediabymentor?id=${project.id}"></c:url>'>View Media</a> 
    
    <!-- Milestones Section -->
<h2>Project Milestones</h2>

<div class="milestones-section">
    <c:forEach var="milestone" items="${project.milestones}">
        <div class="milestone-item">
            <h3><c:out value="${milestone.orderIndex}"/>. <c:out value="${milestone.title}"/>
            
            </h3>
            <p><strong>Description:</strong> <c:out value="${milestone.description}"/></p>
            <p><strong>Progress:</strong> <c:out value="${milestone.progressPercentage}"/></p>
            <p><strong>Status:</strong> <c:out value="${milestone.status}"/></p>
            <p><strong>Status:</strong> 
			    <c:choose>
			        <c:when test="${milestone.isReviewed()}">
			            Review Completed
			        </c:when>
			        <c:otherwise>
			            Review Pending
			        </c:otherwise>
			    </c:choose>
			</p>
             <div class="actions">
	 <a class="btn update-btn" href='<c:url value="reviewmilestone?id=${milestone.id}"></c:url>'>Review</a>
</div> 
        </div>
    </c:forEach> 
</div>
<br>


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