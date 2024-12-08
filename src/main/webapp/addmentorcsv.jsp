<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@page import="com.klef.jfsd.sdp.model.Admin"%>
    <%@ taglib uri="jakarta.tags.core" prefix="c" %>
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
        
        <link rel="stylesheet" href="uploadmedia.css">
        <title>PROFOLIO</title>
    </head>
    <body id="body-pd">
        <%@include file="adminnavbar.jsp" %>
    <div class="upload-media-container">
        <h1>Upload Mentor CSV</h1>
        <form action="insertmentorcsv" method="post" enctype="multipart/form-data" onsubmit="return validateForm()">
        <h4 align="center" style="color:green">
   			 <c:out value="${message}"></c:out>
   			 
            

            <div class="form-group">
                <label for="mediaFile">CSV File</label>
                <input type="file" id="mediaFile" name="csvfile" accept=".csv" required>
            </div>

            
            
            <button type="submit" class="upload-media-button">Upload File</button>
        </form>
    </div>
    <script src="https://unpkg.com/ionicons@5.1.2/dist/ionicons.js"></script>
    <script src="script.js"></script>
    </body>
</html>
<%
    }
%>