<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="jakarta.tags.core" prefix="c" %>
    <%@ page import="com.klef.jfsd.sdp.model.Mentor" %>
<%@ page import="java.util.List" %>
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
    </head>
    <body id="body-pd">
        <%@include file="mentornavbar.jsp" %>

        <section class="container">
            <header>Submit Feedback</header>
            <h4 align="center" style="color:green">
    <c:out value="${message}"></c:out>
    </h4>
            <form action="addportfoliofeedback" method="post" class="form" enctype="multipart/form-data">
            
              <div class="input-box">
                
                <input type="hidden" name="portfolioid" value="${portfolio.id}"/>
              </div>
              
              <div class="gender-box">
                <h2>Rating</h2> <br>
                <div class="gender-option">
                  <div class="gender">
                    <input type="radio" id="check-male" name="prating" value="GOOD" />
                    <label for="check-male">GOOD</label>
                  </div>
                  <div class="gender">
                    <input type="radio" id="check-female" name="prating" value="AVERAGE" />
                    <label for="check-female">AVERAGE</label>
                  </div>
                  <div class="gender">
                    <input type="radio" id="check-other" name="prating" value="POOR" />
                    <label for="check-other">POOR</label>
                  </div>
                </div>
              </div>
              <br/>
              
              
              
              <div class="input-box address">
                
                <textarea rows="10" cols="100" name="pmessage" placeholder="Add your comments"></textarea>
                
                </div>
              
              

              

              <button id="send" onclick="message()">Submit</button>
              
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