<%@page import="com.klef.jfsd.sdp.model.Student"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%
    Student s = (Student) session.getAttribute("student");
    if (s == null) {
%>
    <script type="text/javascript">
        alert("Session Expired. Please Login Again");
        window.location.href = "login"; // Adjust this path to your login page
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
    <title>Propose Milestone</title>
  </head>
  <body>
  <%@include file="studentnavbar.jsp" %>
    <!-- Form Section -->
    <section class="container">
      <header>Propose A Milestone</header>
      <form action="addcustommilestone" method="post" class="form">
      <h4 align="center" style="color:green">
   			 <c:out value="${message}"></c:out>
   		<div class="input-box">
          <input type="hidden" name="projectid" value="${project.id}"  />
        </div>
        <div class="input-box">
          <label>Milestone Title</label>
          <input type="text" name="title"  />
        </div>

        <div class="input-box">
          <label>Milestone Description</label>
          <textarea id="projectDescription" name="description" rows="6" required></textarea>
        </div>


        <button>Propose Milestone</button>
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