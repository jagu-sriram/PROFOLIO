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
<html>
<head>
    <meta charset="UTF-8">
    <title>ProFolio</title>
    <link rel="stylesheet" href="milestoneaction.css">
</head>
<body>
<%@include file="mentornavbar.jsp" %>
    <div class="accept-wrapper reject-wrapper">
        <!-- Page Header -->
        <h1>Reject Milestone</h1>

        <!-- Information Section -->
        <div class="info-section">
            <div class="info-item">
                <label>Milestone Title:</label>
                <span>${milestone.title}</span>
            </div>
            <div class="info-item">
                <label>Milestone Description:</label>
                <span>${milestone.description}</span>
            </div>
            <div class="info-item">
                <label>Project Title:</label>
                <span>${milestone.project.title}</span>
            </div>
        </div>

        <!-- Form Section -->
        <div class="form-section">
            <form action="disapprovemilestone" method="post">
            <input type="hidden" name="milestoneid" value="${milestone.id}"/>
                <!-- Feedback Field -->
                <div class="form-group">
                    <label for="feedback">Feedback Message:</label>
                    <textarea id="feedback" name="feedback" placeholder="Provide your feedback here..." required></textarea>
                </div>
                
                <!-- Submit Button -->
                <div class="form-buttons">
                    <button type="submit" class="btn-submit reject-btn">Submit</button>
                </div>
            </form>
        </div>
    </div>
</body>
</html>
<%
    }
%>