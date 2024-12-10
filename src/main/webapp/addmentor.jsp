<%@page import="com.klef.jfsd.sdp.model.Admin"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="jakarta.tags.core" prefix="c" %>
    <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
    
    <%
Admin a = (Admin) session.getAttribute("admin");

if (a == null) {
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
    </head>
    <body id="body-pd">
        <%@include file="adminnavbar.jsp" %>

        <section class="container">
            <header>Register Mentor</header>
            <h4 align="center" style="${fn:contains(message, 'successfully') ? 'color:green' : 'color:red'}">
    <c:out value="${message}"></c:out>
</h4>
            <form action="insertmentor" method="post" class="form" enctype="multipart/form-data">
            <div class="input-box">
                <label>ID</label>
                <input type="number" placeholder="Enter mentor id" name="mid" required />
              </div>
            <div class="column">
              <div class="input-box">
                <label>First Name</label>
                <input type="text" placeholder="Enter first name" name="fname" required />
              </div>
              <div class="input-box">
                <label>Last Name</label>
                <input type="text" placeholder="Enter last name" name="lname" required />
              </div>
              </div>
              
              
              
                
                <div class="input-box">
                  <label>Birth Date</label>
                  <input type="date" placeholder="Enter birth date" name="mdob" required />
                </div>
             
              <div class="gender-box">
                <h3>Gender</h3>
                <div class="gender-option">
                  <div class="gender">
                    <input type="radio" id="check-male" name="mgender" value="MALE" />
                    <label for="check-male">Male</label>
                  </div>
                  <div class="gender">
                    <input type="radio" id="check-female" name="mgender" value="FEMALE" />
                    <label for="check-female">Female</label>
                  </div>
                  <div class="gender">
                    <input type="radio" id="check-other" name="mgender" value="OTHERS" />
                    <label for="check-other">Prefer not to say</label>
                  </div>
                </div>
              </div>
              <br/>
              <div class="select-box">
                
                <select name="mdept">
                  <option value="" hidden>Department</option>
                  <option value="cse">CSE</option>
                  <option value="csit">CS&IT</option>
                  <option value="ece">ECE</option>
                  <option value="ai&ds">AI&DS</option>
                </select>
              </div>
              <div class="select-box">
                
                <select name="qualification">
                  <option value="" hidden>Qualification</option>
                  <option value="mtech">M.Tech</option>
                  <option value="phd">Phd</option>
     
                </select>
              </div>
              <div class="select-box">
                
                <select name="mdesg">
                  <option value="" hidden>Designation</option>
                  <option value="professor">Professor</option>
                  <option value="associateprofessor">Associate Professor</option>
                  
                </select>
              </div>
               <div class="input-box">
                <label>Experience (in years)</label>
                <input type="number" placeholder="Enter experience" step=1 name="mexp" required />
              </div>
              
              <div class="input-box">
                <label>Email Address</label>
                <input type="email" placeholder="Enter email address" name="memail" required />
              </div>
              
              <div class="input-box address">
                <label>Contact</label>
                <input type="tel" placeholder="Enter contact number" name="mcontact" required />
                
                </div>
              
              

              

              <button id="send" onclick="message()">Register</button>
              
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
        
    </body>
</html>
<%
    }
%>