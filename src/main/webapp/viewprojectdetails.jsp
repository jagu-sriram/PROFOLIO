<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ page import="com.klef.jfsd.sdp.model.*" %>
<%@ page import="java.util.List" %>
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
    <title>Project Details</title>
    <link rel="stylesheet" href="viewprojectindetail.css">
    <link href="https://fonts.googleapis.com/css2?family=Inter:wght@400;500;600;700&display=swap" rel="stylesheet">
</head>
<body>
    <%@include file="studentnavbar.jsp" %>
    
    <div class="project-details-container">
        <h1><c:out value="${project.title}"/></h1>
        
        <div class="project-info">
            <p><strong>Description:</strong> <c:out value="${project.description}"/></p>
            <p><strong>Status:</strong> 
                <span class="status-badge ${project.status.toLowerCase()}">
                    <c:out value="${project.status}"/>
                </span>
            </p>
            <p><strong>Project Link:</strong> 
                <a href="<c:out value='${project.link}'/>" target="_blank" class="project-link">
                    View Project <span class="link-icon">↗</span>
                </a>
            </p>
        </div>

        <section class="media-section">
            <h2>Project Media</h2>

            <div class="media-container">
                <h3>Images</h3>
                <div class="media-gallery images">
                    <c:forEach var="media" items="${project.media}">
                        <c:if test="${media.mediatype == 'Image'}">
                            <div class="media-item">
                                <img src='displaymedia?id=${media.id}' 
                                     alt="<c:out value='${media.caption}'/>" 
                                     onclick="openMediaModal('displaymedia?id=${media.id}')">
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
                                    <source src="displaymedia?id=${media.id}" type="video/mp4">
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
                                <a href="displaymedia?id=${media.id}" target="_blank" class="document-link">
                                    📄 <c:out value='${media.caption}'/>
                                </a>
                            </div>
                        </c:if>
                    </c:forEach>
                </div>
            </div>

            <a class="upload-media-button" href='<c:url value="uploadmedia?id=${project.id}"></c:url>'>
                + Upload New Media
            </a>
        </section>

        <section class="feedback-section">
            <h2>Mentor Feedback</h2>
            <c:forEach var="feedback" items="${feedbacklist}">
                <div class="feedback-item">
                    <div class="feedback-meta">
                        <strong>Mentor: <c:out value='${feedback.mentor.firstname} ${feedback.mentor.lastname}'/></strong>
                        <span><c:out value='${feedback.submitteddate}'/></span>
                    </div>
                    <p><c:out value='${feedback.comments}'/></p>
                </div>
            </c:forEach>
        </section>
    </div>

    <!-- Modal for Full-Screen Media View -->
    <div id="mediaModal" class="modal">
        <span class="close" onclick="closeMediaModal()">&times;</span>
        <img class="modal-content" id="mediaFullView" alt="Full size media">
    </div>

    <script>
        function openMediaModal(url) {
            const modal = document.getElementById("mediaModal");
            const modalImg = document.getElementById("mediaFullView");
            modal.style.display = "block";
            modalImg.src = url;
        }

        function closeMediaModal() {
            document.getElementById("mediaModal").style.display = "none";
        }

        // Close modal when clicking outside the image
        document.getElementById("mediaModal").addEventListener("click", function(e) {
            if (e.target === this) {
                closeMediaModal();
            }
        });

        // Handle escape key
        document.addEventListener("keydown", function(e) {
            if (e.key === "Escape") {
                closeMediaModal();
            }
        });
    </script>
</body>
</html>
<%
    }
%>