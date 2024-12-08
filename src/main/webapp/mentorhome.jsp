<%@page import="com.klef.jfsd.sdp.model.Mentor"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@ taglib uri="jakarta.tags.core" prefix="c" %>
    <%
    Mentor m = (Mentor) session.getAttribute("mentor");
    if (m == null) {
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
    <body>
    <%@include file="mentornavbar.jsp" %>
          <section id="content">
          <main>
            <div class="head-title">
              <div class="left">
                <h1>Welcome, <%=m.getFirstname() %></h1>
              </div>
            </div>
      
            <ul class="box-info">
              <li>
                <box-icon type='solid' name='user'></box-icon>
                <span class="text">
                  <h3>${ts}</h3>
                  <p>Total Students</p>
                </span>
              </li>
              <li>
                <box-icon type='solid' name='check-square'></box-icon>
                <span class="text">
                  <h3>${tcp}</h3>
                  <p>Total Projects Supervised</p>
                </span>
              </li>
              <li>
                <box-icon name='cog' ></box-icon>
                <span class="text">
                  <h3>${tpp}</h3>
                  <p>Ongoing Projects</p>
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
	                      <th>Student Name</th>
	                      <th>Status</th>
	                    </tr>
	                  </thead>
	                  <tbody>
	                  <c:forEach var="project" items="${recentprojects}">
	                    <tr>
	                      <td>
	                        <p>${project.id}</p>
	                      </td>
	                      <td>${project.title}</td>
	                      <td>${project.student.lastname}</td>
	                      <td><span class="${project.status == 'COMPLETED' ? 'status completed' : 'status pending'}">${project.status}</span></td>
	                    </tr>
	                    </c:forEach>
	                  </tbody>
	                </table>
                </c:otherwise>
                </c:choose>
              </div>
              
              <div class="order">
                <div class="head">
                  <h3>Pending MileStone Reviews</h3>
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
	                      <th>MileStone Title</th>
	                      <th>Project Name</th>
	                      <th>Student Name</th>
	                      <th>Review</th>
	                    </tr>
	                  </thead>
	                  <tbody>
	                  <c:forEach var="milestone" items="${unreviewedmilestones}">
	                    <tr>
	                      <td>
	                        ${milestone.title}
	                      </td>
	                      <td>${milestone.project.title}</td>
	                      <td>${milestone.project.student.lastname}</td>
	                      <td><span class="status pending"><a href='<c:url value="reviewmilestone?id=${milestone.id}"></c:url>'></a>Review</span></td>
	                    </tr>
	                    </c:forEach>
	                  </tbody>
	                </table>
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