<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ page import="com.klef.jfsd.sdp.model.Project" %>
<%@ page import="com.klef.jfsd.sdp.model.Media" %>
<%@page import="com.klef.jfsd.sdp.model.Student"%>
<%@ page import="com.klef.jfsd.sdp.model.Mentor" %>
<%@ page import="java.util.List" %>
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
    <title>ProFolio</title>
    <link rel="stylesheet" href="viewprojectindetail.css">
</head>
<body>
<%@include file="mentornavbar.jsp" %>
    <div class="project-details-container">
        <h1><c:out value="${project.title}"/></h1>
        <p><strong>Description:</strong> <c:out value="${project.description}"/></p>
        <p><strong>Status:</strong> <c:out value="${project.status}"/></p>
        <p><strong>Submitted By:</strong> <c:out value="${project.student.id}-${project.student.lastname}"/></p>
        <p><strong>Project Link:</strong> <a href="<c:out value='${project.link}'/>" target="_blank">View Project</a></p>

        <!-- Media Section -->
        <h2>Project Media</h2>

        <div class="media-section">
            <h3>Images</h3>
            <div class="media-gallery images">
                <c:forEach var="media" items="${project.media}">
                    <c:if test="${media.mediatype == 'Image'}">
                        <div class="media-item">
                            <img src='displayprojectmediabymentor?id=${media.id}' alt="<c:out value='${media.caption}'/>" onclick="openMediaModal('displaymedia?id=${media.id}')">
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
        <a class="upload-media-button" href='<c:url value="projectfeedbackform?id=${project.id}"></c:url>'>Give Feedback</a>

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
    </div>
 --%>
    <!-- Modal for Full-Screen Media View -->
    <div id="mediaModal" class="modal">
        <span class="close" onclick="closeMediaModal()">&times;</span>
        <img class="modal-content" id="mediaFullView">
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
