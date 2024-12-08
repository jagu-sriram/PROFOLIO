<%@page import="com.klef.jfsd.sdp.model.Student"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/intl-tel-input/17.0.8/css/intlTelInput.css" />
    <script src="https://cdnjs.cloudflare.com/ajax/libs/intl-tel-input/17.0.8/js/intlTelInput.min.js"></script>
    <link rel="stylesheet" href="adminnavbar.css">
    <link rel="stylesheet" href="admin.css">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Rounded:opsz,wght,FILL,GRAD@20..48,100..700,0..1,-50..200" />
<title>ProFolio</title>
    <style type="text/css">
    .image img{
  width: 200px;
  height: 200px;
  object-fit: cover;
  border-radius: 50%;
  cursor: pointer;
}

#file-path{
  display: none;
}

.wrapper h2{
  margin-bottom: 20px;
}

.image{
  position: relative;
}

.image label{
  position: absolute;
  top: 10px;
  right: 13px;
  color: #fff;
  background-color: #1b74e4;
  width: 40px;
  height: 40px;
  display: flex;
  align-items: center;
  justify-content: center;
  border-radius: 50%;
  font-size: 20px;
  cursor: pointer;
  opacity: 0;
  pointer-events: none;
  transition: 0.2s;
}

.image:hover label{
  opacity: 1;
  pointer-events: all;
}

.wrapper{
  background-color: #fff;
  padding: 3% 5%;
  border-radius: 7px;
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
}

    </style>
</head>
<body>
    <%@include file="studentnavbar.jsp" %>

    <section class="container">
        <header>My Profile</header>
        <h4 align="center" style="color:green">
            <c:out value="${message}"></c:out>
        </h4>
        
    <!-- Profile Image -->
    
    
    
        <form action="updatestudentprofile" method="post" enctype="multipart/form-data" class="form" >
			<div class="wrapper">
			    <div class="image">
			      <img src="displayprofileimage?id=${student.id}"  alt="Profile Image" class="profile-img" id="output" />
			      <label for="file-path">
			        <span class="material-symbols-rounded">photo_camera</span>
			      </label>
			      <input type="file" accept="image/jpeg, image/png, image/jpg" name="profileimage" id="file-path" class="user-file">
			    </div>
			  </div>		
            <div class="input-box">
                <label>ID</label>
                <input type="number" name="sid" readonly value="<%=s.getId() %>" required />
            </div>
            <div class="column">
                <div class="input-box">
                    <label>First Name</label>
                    <input type="text" name="fname" readonly value="<%=s.getFirstname() %>" required />
                </div>
                <div class="input-box">
                    <label>Last Name</label>
                    <input type="text" name="lname" readonly value="<%=s.getLastname() %>" required />
                </div>
            </div>

            <div class="column">
                <div class="input-box">
                    <label>Age</label>
                    <input type="number" name="sage" readonly value="<%=s.getAge() %>" required />
                </div>
                <div class="input-box">
                    <label>Birth Date</label>
                    <input type="date" name="sdob" readonly value="<%=s.getDateofBirth()%>" required />
                </div>
            </div>
            <div class="gender-box">
                <h3>Gender</h3>
                <div class="gender-option">
                    <div class="gender">
                        <input type="radio" id="check-male" name="sgender" value="MALE" <%= s.getGender().equals("MALE") ? "checked" : "" %> />
                        <label for="check-male">Male</label>
                    </div>
                    <div class="gender">
                        <input type="radio" id="check-female" name="sgender" value="FEMALE" <%= s.getGender().equals("FEMALE") ? "checked" : "" %> />
                        <label for="check-female">Female</label>
                    </div>
                    <div class="gender">
                        <input type="radio" id="check-other" name="sgender" value="OTHERS" <%= s.getGender().equals("OTHERS") ? "checked" : "" %> />
                        <label for="check-other">Prefer not to say</label>
                    </div>
                </div>
            </div>
            <br/>
            <div class="select-box">
                <select name="sdept">
                    <option value="" hidden>Department</option>
                    <option value="cse" <%= s.getDepartment().equals("cse") ? "selected" : "" %>>CSE</option>
                    <option value="csit" <%= s.getDepartment().equals("csit") ? "selected" : "" %>>CS&IT</option>
                    <option value="ece" <%= s.getDepartment().equals("ece") ? "selected" : "" %>>ECE</option>
                    <option value="ai&ds" <%= s.getDepartment().equals("ai&ds") ? "selected" : "" %>>AI&DS</option>
                </select>
            </div>
            <div class="input-box">
                <label>Email Address</label>
                <input type="text" name="semail" readonly value="<%=s.getEmail() %>" required />
            </div>
            <div class="input-box">
                <label>Password</label>
                <input type="password" name="spwd"  value="<%=s.getPassword()%>" required />
            </div>
            <div class="input-box address">
                <label>Contact</label>
                <input type="tel" name="scontact" value="<%=s.getContact() %>" required />
            </div>
            
<!-- <div class="input-box">
    Provide an option to upload a new image
    <label>Update Profile Image</label>
    <input type="file" name="profileimage"  />
</div> -->
            <button id="send">Update</button>
        </form>
    </section>

    <script src="https://unpkg.com/ionicons@5.1.2/dist/ionicons.js"></script>
    <script src="../script.js"></script>
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
