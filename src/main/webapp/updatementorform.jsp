<%@page import="com.klef.jfsd.sdp.model.Mentor"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="jakarta.tags.core" prefix="c" %>
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
    <body>
        <%@include file="adminnavbar.jsp" %>

        <section class="container">
            <header>Update Mentor</header>
            <h4 align="center" style="color:green">
    <c:out value="${message}"></c:out>
    </h4>
            <form action="saveMentor" method="post" class="form" enctype="multipart/form-data">
            <div class="input-box">
                <label>ID</label>
                <input type="number"  name="mid" readonly value="${mentor.id }" required />
              </div>
            <div class="column">
              <div class="input-box">
                <label>First Name</label>
                <input type="text" name="fname" readonly value="${mentor.firstname }" required />
              </div>
              <div class="input-box">
                <label>Last Name</label>
                <input type="text"  name="lname" readonly value="${mentor.lastname }" required />
              </div>
              </div>
              
              
              
                
                <div class="input-box">
                  <label>Birth Date</label>
                  <input type="date"  name="mdob" readonly value="${mentor.dateofBirth}" required />
                </div>
             
              <div class="gender-box">
                <h3>Gender</h3>
                <div class="gender-option">
                    <div class="gender">
                        <input type="radio" id="check-male" name="mgender" readonly value="MALE" ${mentor.gender == 'MALE' ? 'checked' : ''}  />
                        <label for="check-male">Male</label>
                    </div>
                    <div class="gender">
                        <input type="radio" id="check-female" name="mgender" readonly value="FEMALE" ${mentor.gender == 'FEMALE' ? 'checked' : ''} />
                        <label for="check-female">Female</label>
                    </div>
                    <div class="gender">
                        <input type="radio" id="check-other" name="mgender" value="OTHERS" ${mentor.gender == 'OTHERS' ? 'checked' : ''} readonly />
                        <label for="check-other">Prefer not to say</label>
                    </div>
                </div>
            </div>
              <br/>
             <div class="select-box">
                <select name="mdept">
                    <option value="" hidden>Department</option>
                    <option value="cse" ${mentor.department == 'cse' ? 'selected' : ''}>CSE</option>
                    <option value="csit" ${mentor.department == 'csit' ? 'selected' : ''}>CS&IT</option>
                    <option value="ece" ${mentor.department == 'ece' ? 'selected' : ''}>ECE</option>
                    <option value="ai&ds" ${mentor.department == 'ai&ds' ? 'selected' : ''}>AI&DS</option>
                </select>
            </div>
              <div class="select-box">
                
                <select name="qualification">
                  <option value="" hidden>Qualification</option>
                  <option value="mtech" ${mentor.qualification=='mtech' ? 'selected' : '' }>M.Tech</option>
                  <option value="phd" ${mentor.qualification=='phd' ? 'selected' : '' }>Phd</option>
     
                </select>
              </div>
              <div class="select-box">
                
                <select name="mdesg">
                  <option value="" hidden>Designation</option>
                  <option value="professor" ${mentor.designation=='professor' ? 'selected' : '' }>Professor</option>
                  <option value="associateprofessor" ${mentor.designation=='associateprofessor' ? 'selected' : '' }>Associate Professor</option>
                  
                </select>
              </div>
               <div class="input-box">
                <label>Experience (in years)</label>
                <input type="number"  step=1 name="mexp" readonly value="${mentor.experience }" required />
              </div>
              
              <div class="input-box">
                <label>Email Address</label>
                <input type="email"  name="memail" readonly value="${mentor.email }" required />
              </div>
              
              <div class="input-box address">
                <label>Contact</label>
                <input type="tel"  name="mcontact" value="${mentor.contact }" required />
                
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
        
    </body>
</html>