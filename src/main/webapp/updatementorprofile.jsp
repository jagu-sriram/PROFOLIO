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
        <link
     rel="stylesheet"
     href="https://cdnjs.cloudflare.com/ajax/libs/intl-tel-input/17.0.8/css/intlTelInput.css"
   />
   <script src="https://cdnjs.cloudflare.com/ajax/libs/intl-tel-input/17.0.8/js/intlTelInput.min.js"></script>
        <!-- ===== CSS ===== -->
        <link rel="stylesheet" href="adminnavbar.css">
        <link rel="stylesheet" href="admin.css">
        <title>PROFOLIO</title>
        
        <style type="text/css">
        password {
        flex: 1;
        padding: 8px;
        font-size: 14px;
    }

    .toggle-label {
        margin-left: 8px;
        font-size: 12px;
        cursor: pointer;
        color: #007bff;
        text-decoration: underline;
    }

    .toggle-label:hover {
        color: #0056b3;
    }
        </style>
    </head>
    <body id="body-pd">
        <%@include file="mentornavbar.jsp" %>

        <section class="container">
            <header>Register Mentor</header>
            <h4 align="center" style="color:green">
    <c:out value="${message}"></c:out>
    </h4>
            <form action="updatementorprofile" method="post" class="form" enctype="multipart/form-data">
            <div class="input-box">
                <label>ID</label>
                <input type="number" readonly value="<%=m.getId() %>" name="mid" required />
              </div>
            <div class="column">
              <div class="input-box">
                <label>First Name</label>
                <input type="text" readonly value="<%=m.getFirstname() %>" name="fname" required />
              </div>
              <div class="input-box">
                <label>Last Name</label>
                <input type="text" readonly value="<%=m.getLastname()%>" name="lname" required />
              </div>
              </div>
              
     
              
                
                <div class="input-box">
                  <label>Birth Date</label>
                  <input type="text" readonly value="<%=m.getDateofBirth()%>" name="mdob" required />
                </div>
             
              <div class="gender-box">
                <h3>Gender</h3>
                <div class="gender-option">
                  <div class="gender">
                    <input type="radio" id="check-male" disabled name="mgender" value="MALE" <%= m.getGender().equals("MALE") ? "checked" : "" %> />
                    <label for="check-male">Male</label>
                  </div>
                  <div class="gender">
                    <input type="radio" id="check-female" disabled name="mgender" value="FEMALE" value="FEMALE" <%= m.getGender().equals("FEMALE") ? "checked" : "" %> />
                    <label for="check-female">Female</label>
                  </div>
                  <div class="gender">
                    <input type="radio" id="check-other" disabled name="mgender" value="OTHERS" <%= m.getGender().equals("OTHERS") ? "checked" : "" %> />
                    <label for="check-other">Prefer not to say</label>
                  </div>
                </div>
              </div>
              <br/>
              <div class="select-box">
                
                <select name="mdept">
                  <option value="" hidden>Department</option>
                  <option value="cse" <%= m.getDepartment().equals("cse") ? "selected" : "" %>>CSE</option>
                  <option value="csit" <%= m.getDepartment().equals("csit") ? "selected" : "" %>>CS&IT</option>
                  <option value="ece" <%= m.getDepartment().equals("ece") ? "selected" : "" %>>ECE</option>
                  <option value="ai&ds" <%= m.getDepartment().equals("ai&ds") ? "selected" : "" %>>AI&DS</option>
                </select>
              </div>
              <div class="select-box">
                
                <select name="qualification">
                  <option value="" hidden>Qualification</option>
                  <option value="mtech" <%= m.getQualification().equals("mtech") ? "selected" : "" %>>M.Tech</option>
                  <option value="phd" <%= m.getQualification().equals("phd") ? "selected" : "" %>>Phd</option>
     
                </select>
              </div>
              <div class="select-box">
                
                <select name="mdesg">
                  <option value="" hidden>Designation</option>
                  <option value="professor" <%= m.getDesignation().equals("professor") ? "selected" : "" %>>Professor</option>
                  <option value="associateprofessor" <%= m.getDesignation().equals("associateprofessor") ? "selected" : "" %>>Associate Professor</option>
                  
                </select>
              </div>
               <div class="input-box">
                <label>Experience (in years)</label>
                <input type="number" readonly value="<%=m.getExperience() %>" step=1 name="mexp" required />
              </div>
              
              <div class="input-box">
                <label>Email Address</label>
                <input type="email" readonly value="<%=m.getEmail() %>" name="memail" required />
              </div>
              
              <div class="input-box">
    <label>Password</label>
    <div class="password-container">
        <input type="password"  name="mpwd" value="<%=m.getPassword()%>" required />
         
    </div>
</div>

              
              <div class="input-box address">
                <label>Contact</label>
                <input type="tel" readonly value="<%=m.getContact() %>" name="mcontact" required />
                
                </div>
              
              

              

              <button id="send" onclick="message()">Update</button>
              
            </form>
            <!-- <div class="message" type="hidden"></div>
                <div class="success" id="success">Your Message Successfully Sent!</div>
                <div class="danger" id="danger">Fields Can't be Empty!</div>
            </div> -->
          </section>
        <!-- ===== IONICONS ===== -->
        <script src="https://unpkg.com/ionicons@5.1.2/dist/ionicons.js"></script>
        
        <!-- ===== MAIN JS ===== -->
        <script src="../script.js"></script>
        <script type="text/javascript"></script>
        
        
       
    </body>
</html>
<%
    }
%>