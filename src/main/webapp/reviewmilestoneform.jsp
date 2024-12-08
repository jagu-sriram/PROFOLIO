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
    <link rel="stylesheet" href="uploadproject.css">
    <title>ProFolio</title>
  </head>
  <body >
  <%@include file="mentornavbar.jsp" %>
    <!-- Form Section -->
    <section class="container">
      <header>Update Milestone</header>
      <form action="savemilestonereview" method="post" class="form">
      <h4 align="center" style="color:green">
   			 <c:out value="${message}"></c:out>
   		<div class="input-box">
          <input type="hidden" name="mid" value="${milestone.id}" readonly  />
        </div>
        <div class="input-box">
          <label>Milestone Title</label>
          <input type="text" name="mtitle" value="${milestone.title}" readonly  />
        </div>

        <div class="input-box">
          <label>Review Milestone Description</label>
          <textarea id="projectDescription" name="mdescription" rows="6" required>${milestone.description}</textarea>
        </div>

        <div class="input-box">
          <label>Review Progress (in %)</label>
          <input type="number" id="mprogress" step="2" name="mprogress" value="${milestone.mileStoneProgress.studentpercentage}">
        </div>
        <div class="input-box">
          <label>Feedback on Changes</label>
          <textarea id="projectDescription" name="mfeedback" rows="6" required></textarea>
        </div>
        

        <button>Update</button>
      </form>
    </section>

    <!-- Script for Ionicons -->
    <script src="https://unpkg.com/ionicons@5.1.2/dist/ionicons.js"></script>

    <script src="uploadproject.js"></script>
    <script src="/script.js"></script>
  </body>
</html>
<%
    }
%>