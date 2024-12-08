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
        window.location.href = "login.jsp"; // Adjust this path to your login page
    </script>
<%
    } else {
%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Rounded:opsz,wght,FILL,GRAD@20..48,100..700,0..1,-50..200" />
    <link rel="stylesheet" href="uploadproject.css">
    <title>Update Your Project</title>
  </head>
  <body id="body-pd">
  <%@include file="studentnavbar.jsp" %>
    <!-- Form Section -->
    <section class="container">
      <header>Update Project</header>
      <form action="saveupdatedproject" method="post" enctype="multipart/form-data" class="form">
      <h4 align="center" style="color:green">
   			 <c:out value="${message}"></c:out>
   		</h4>
   		<div class="input-box">
          <input type="hidden" name="pid" value="${project.id}" readonly required />
        </div>
        <div class="input-box">
          <label>Project Title Name</label>
          <input type="text" name="pname" value="${project.title}" required />
        </div>

        <div class="input-box">
          <label>Project Description</label>
          <textarea id="projectDescription" name="pdescription" rows="6" required>${project.description}</textarea>
        </div>

        <div class="input-box">
          <label>Upload Project Link (Optional)</label>
          <input type="text" id="projectLink" name="plink" value="${project.link}">
        </div>

        <!-- Profile Icon Upload -->
       <div class="image">
	      <img src="displayprojectimage?id=${project.id}"  alt="Project Image" class="profile-img" id="output" />
	      <label for="file-path">
	        <span class="material-symbols-rounded">photo_camera</span>
	      </label>
	      <input type="file" accept="image/jpeg, image/png, image/jpg" name="profileimage" value="displayprojectimage?id=${project.id}" id="file-path" class="user-file">
	    </div>

        <!-- Send For Review Checkbox -->
        <div class="input-box">
	  <input 
	    type="checkbox" 
	    id="sendForReview" 
	    name="sendForReview" 
	    value="true" 
	    ${project.submitForReview ? 'checked' : ''}>
	  <label for="sendForReview">Send For Review</label>
	</div>


        <button>Submit</button>
      </form>
    </section>

    <!-- Script for Ionicons -->
    <script src="https://unpkg.com/ionicons@5.1.2/dist/ionicons.js"></script>

    <script src="uploadproject.js"></script>
    <script src="script.js"></script>
     <script>
    const profilePic = document.querySelector(".image img");
    const userFile = document.querySelector(".user-file");

    userFile.onchange = function () {
      profilePic.src = URL.createObjectURL(userFile.files[0]);
    }
</script>
  </body>
</html>
<%
    }
%>
