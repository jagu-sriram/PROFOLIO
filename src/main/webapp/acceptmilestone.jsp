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
    <title>ProFolio</title>
    <link rel="stylesheet" type="text/css" href="milestoneaction.css">
</head>
<body>
<%@include file="mentornavbar.jsp" %>
    <div class="accept-wrapper">
        <h1>Accept Milestone</h1>

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

        <div class="form-section">
            <form action="approvemilestone" method="POST">
            <input type="hidden" name="milestoneid" value="${milestone.id}"/>
                <div class="form-group">
                    <label for="orderIndex">Place After:</label>
                    <select name="orderIndex" id="orderIndex" required>
                      <option value="">--Select--</option>
                        <c:forEach var="milestone" items="${milestoneList}">
                            <option value="${milestone.orderIndex}">
                                ${milestone.orderIndex} - ${milestone.title}
                            </option>
                        </c:forEach>
                    </select>
                </div>

                <div class="form-group">
                    <label for="feedback">Feedback Message:</label>
                    <textarea name="feedback" id="feedback" placeholder="Enter your feedback" required></textarea>
                </div>

                <div class="form-buttons">
                    <button type="submit" class="btn-submit">Submit</button>
                </div>
            </form>
        </div>
    </div>
</body>
</html>

<%
    }
%>