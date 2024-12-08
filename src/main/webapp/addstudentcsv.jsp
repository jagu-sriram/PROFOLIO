<%@page import="com.klef.jfsd.sdp.model.Student"%>
<%@page import="com.klef.jfsd.sdp.model.Project"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@page import="com.klef.jfsd.sdp.model.Admin"%>
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
    <title>Upload Media</title>
    <link rel="stylesheet" href="uploadmedia.css">
   <script>
    function validateForm() {
        const mediaType = document.getElementById("mediaType").value;
        const mediaFile = document.getElementById("mediaFile").value;
        const caption = document.getElementById("caption").value;

        // Check if media type is selected
        if (!mediaType) {
            alert("Please select a media type.");
            return false;
        }

        // Check if media file is uploaded
        if (!mediaFile) {
            alert("Please upload a media file.");
            return false;
        }

        // Check file extension based on media type
        const allowedExtensions = {
            "Image": ["jpg", "jpeg", "png", "gif"],
            "Video": ["mp4", "avi", "mov"],
            "Document": ["pdf", "doc", "docx"]
        };

        const fileExtension = mediaFile.split('.').pop().toLowerCase();
        // Ensure the media type exists in allowed extensions
        if (allowedExtensions[mediaType]) {
            if (!allowedExtensions[mediaType].includes(fileExtension)) {
            	alert("Invalid file type for " + mediaType + ". Please upload a " + mediaType.toLowerCase() + " file.");
                return false;
            }
        } else {
            alert("Selected media type is not recognized.");
            return false;
        }

        // Check if caption is provided
        if (!caption) {
            alert("Please provide a caption for the media.");
            return false;
        }

        return true; // Form is valid
    }
</script>

</head>
<body>
<%@include file="adminnavbar.jsp" %>
    <div class="upload-media-container">
        <h1>Upload Student CSV</h1>
        <form action="insertstudentcsv" method="post" enctype="multipart/form-data" onsubmit="return validateForm()">
        <h4 align="center" style="color:green">
   			 <c:out value="${message}"></c:out>
   			 
            

            <div class="form-group">
                <label for="mediaFile">CSV File</label>
                <input type="file" id="mediaFile" name="file" accept=".csv" required>
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
