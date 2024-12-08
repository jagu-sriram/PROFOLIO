<%@page import="com.klef.jfsd.sdp.model.Student"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@ taglib uri="jakarta.tags.core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="admin.css">
    <title>PROFOLIO</title>
  </head>
  <body id="body-pd">
  <%@include file="adminnavbar.jsp" %>
    <!-- Form Section -->
    <section class="container">
      <header>Mentor Student Mapping</header>
      <h4 align="center" style="color:green">
    <c:out value="${message}"></c:out>
    </h4>
      <form action="mstudentmappinginsert" method="post"class="form">
      
        <div class="select-box">
                
                <select name="mid">
                  <option value="" hidden>Select Mentor</option>
                  <c:forEach items="${mentordata}" var="mentor"> 
        <option value="${mentor.id}">${mentor.id}-${mentor.lastname}</option>
        </c:forEach>
                </select>
              </div>

        <div class="select-box">
                
                <select name="sid">
                  <option value="" hidden>Select Student</option>
                  <c:forEach items="${studentdata}" var="student"> 
        <option value="${student.id}">${student.id}-${student.lastname}</option>
        </c:forEach>
                </select>
              </div>

        

        <button>Submit</button>
      </form>
    </section>

    <!-- Script for Ionicons -->
    <script src="https://unpkg.com/ionicons@5.1.2/dist/ionicons.js"></script>

    <script src="uploadproject.js"></script>
    <script src="../script.js"></script>
  </body>
</html>
