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
    <link rel="stylesheet" href="viewprojectdetails.css">
</head>
<body>
<%@include file="studentnavbar.jsp" %>
    <div class="project-details-container">
        

        <!-- Media Section -->
        <h2>Project Media</h2>

        <div class="media-section">
            <h3>Images</h3>
            <div class="media-gallery images">
                <c:forEach var="media" items="${project.media}">
                    <c:if test="${media.mediatype == 'Image'}">
                        <div class="media-item">
                            <img src='displaymedia?id=${media.id}' alt="<c:out value='${media.caption}'/>" onclick="openMediaModal('displaymedia?id=${media.id}')">
                            <p><c:out value='${media.caption}'/></p>
                        </div>
                    </c:if>
                </c:forEach>
            </div>

            <h3>Videos</h3>
            <div class="media-gallery videos">
                <c:forEach var="media" items="${project.media}">
                    <c:if test="${media.mediatype == 'video'}">
                        <div class="media-item">
                            <video controls>
                                <source src="<c:out value='${media.media}'/>" type="video/mp4">
                                Your browser does not support the video tag.
                            </video>
                            <p><c:out value='${media.caption}'/></p>
                        </div>
                    </c:if>
                </c:forEach>
            </div>

            <h3>Documents</h3>
            <div class="media-gallery documents">
                <c:forEach var="media" items="${project.media}">
                    <c:if test="${media.mediatype == 'Document'}">
                        <div class="media-item">
                            <a href="displaymedia?id=${media.id}" target="_blank" class="document-link">View Document: <c:out value='${media.caption}'/></a>
                        </div>
                    </c:if>
                </c:forEach>
            </div>
        </div>
         
		
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

    <!-- Modal for Full-Screen Media View -->
    <div id="mediaModal" class="modal">
        <span class="close" onclick="closeMediaModal()">&times;</span>
        <img class="modal-content" id="mediaFullView">
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