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

        <!-- ===== CSS ===== -->
        <link rel="stylesheet" href="studenthome.css">
        <title>PROFOLIO</title>
    </head>
    <body id="body-pd">
    <%@include file="studentnavbar.jsp" %>
          <section id="content">
          <main>
            <div class="head-title">
              <div class="left">
                <h1>Welcome, <%=s.getFirstname() %></h1>
              </div>
            </div>
      
            <ul class="box-info">
              <li>
                <box-icon type='logo' name='html5'></box-icon>
                <span class="text">
                  <h3>${tpc}</h3>
                  <p>Total Projects</p>
                </span>
              </li>
              <li>
                <box-icon type='solid' name='check-square'></box-icon>
                <span class="text">
                  <h3>${cpc}</h3>
                  <p>Completed</p>
                </span>
              </li>
              <li>
                <box-icon name='cog' ></box-icon>
                <span class="text">
                  <h3>${ppc}</h3>
                  <p>In Development</p>
                </span>
              </li>
            </ul>
      
      
            <div class="table-data">
              <div class="order">
                <div class="head">
                  <h3>Recent Projects</h3>
                  <i class='bx bx-search' ></i>
                  <i class='bx bx-filter' ></i>
                </div>
                <c:choose>
                <c:when test="${empty recentprojects}">
                    <p class="no-data-message">No Projects Created Yet.</p>
                </c:when>
                <c:otherwise>
	                <table>
	                  <thead>
	                    <tr>
	                      <th>Project No</th>
	                      <th>Project Name</th>
	                      <th>Status</th>
	                    </tr>
	                  </thead>
	                  <tbody>
	                  <c:forEach var="project" items="${recentprojects}">
	                    <tr>
	                      <td>
	                        <p>${project.id}</p>
	                      </td>
	                      <td>${project.title }</td>
	                      <td><span class="${project.status == 'COMPLETED' ? 'status completed' : 'status pending'}">${project.status}</span></td>
	                    </tr>
	                    </c:forEach>
	                  </tbody>
	                </table>
                </c:otherwise>
                </c:choose>
              </div>
              
              <div class="todo">
                <div class="head">
                  <h3>Pending MileStones</h3>
                  <i class='bx bx-plus' ></i>
                  <i class='bx bx-filter' ></i>
                </div>
                <c:choose>
	                <c:when test="${empty pendingmilestones}">
	                    <p class="no-data-message">No pending milestones to display.</p>
	                </c:when>
                <c:otherwise>
	                <ul class="todo-list">
	                <c:forEach var="milestone" items="${pendingmilestones}">
	                  <li class="not-completed">
	                    <p>${milestone.title} - ${milestone.project.title}</p>
	                    <i class='bx bx-dots-vertical-rounded' ></i>
	                  </li>
	                  </c:forEach>
	                </ul>
                </c:otherwise>
                </c:choose>
              </div>
            </div>
          </main>
          </section>
        <!-- ===== IONICONS ===== -->
        <script src="https://unpkg.com/ionicons@5.1.2/dist/ionicons.js"></script>
        <script src="https://unpkg.com/boxicons@2.1.4/dist/boxicons.js"></script>
        
        <!-- ===== MAIN JS ===== -->
        <script src="script.js"></script>
    </body>
</html>
<%
    }
%>