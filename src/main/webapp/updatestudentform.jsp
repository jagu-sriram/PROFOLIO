<%@page import="com.klef.jfsd.sdp.model.Student"%>
<%@page import="com.klef.jfsd.sdp.model.Admin"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/intl-tel-input/17.0.8/css/intlTelInput.css" />
    <script src="https://cdnjs.cloudflare.com/ajax/libs/intl-tel-input/17.0.8/js/intlTelInput.min.js"></script>
    <link rel="stylesheet" href="adminnavbar.css">
    <link rel="stylesheet" href="admin.css">
    <title>ProFolio</title>
</head>
<body id="body-pd">
    <%@include file="adminnavbar.jsp" %>

    <section class="container">
        <header>Update Student</header>
        <h4 align="center" style="color:green">
            <c:out value="${message}"></c:out>
        </h4>
        <form action="saveStudent" method="post" class="form">
            <div class="input-box">
                <label>ID</label>
                <input type="number" name="sid" readonly value="${student.id}" required />
            </div>
            <div class="column">
                <div class="input-box">
                    <label>First Name</label>
                    <input type="text" name="fname" readonly value="${student.firstname}" required />
                </div>
                <div class="input-box">
                    <label>Last Name</label>
                    <input type="text" name="lname" readonly value="${student.lastname}" required />
                </div>
            </div>

            <div class="column">
                <div class="input-box">
                    <label>Age</label>
                    <input type="number" name="sage" readonly value="${student.age}" required />
                </div>
                <div class="input-box">
                    <label>Birth Date</label>
                    <input type="date" name="sdob" readonly value="${student.dateofBirth}" required />
                </div>
            </div>
            <div class="gender-box">
                <h3>Gender</h3>
                <div class="gender-option">
                    <div class="gender">
                        <input type="radio" id="check-male" name="sgender" value="MALE" ${student.gender == 'MALE' ? 'checked' : ''} />
                        <label for="check-male">Male</label>
                    </div>
                    <div class="gender">
                        <input type="radio" id="check-female" name="sgender" value="FEMALE" ${student.gender == 'FEMALE' ? 'checked' : ''} />
                        <label for="check-female">Female</label>
                    </div>
                    <div class="gender">
                        <input type="radio" id="check-other" name="sgender" value="OTHERS" ${student.gender == 'OTHERS' ? 'checked' : ''} />
                        <label for="check-other">Prefer not to say</label>
                    </div>
                </div>
            </div>
            <br/>
            <div class="select-box">
                <select name="sdept">
                    <option value="" hidden>Department</option>
                    <option value="cse" ${student.department == 'cse' ? 'selected' : ''}>CSE</option>
                    <option value="csit" ${student.department == 'csit' ? 'selected' : ''}>CS&IT</option>
                    <option value="ece" ${student.department == 'ece' ? 'selected' : ''}>ECE</option>
                    <option value="ai&ds" ${student.department == 'ai&ds' ? 'selected' : ''}>AI&DS</option>
                </select>
            </div>
            <div class="input-box">
                <label>Email Address</label>
                <input type="text" name="semail" readonly value="${student.email}" required />
            </div>
            <div class="input-box address">
                <label>Contact</label>
                <input type="tel" name="scontact" value="${student.contact}" required />
                
            </div>

            <button id="send">Update</button>
        </form>
    </section>

    <script src="https://unpkg.com/ionicons@5.1.2/dist/ionicons.js"></script>
    <script src="../script.js"></script>
</body>
</html>
<%
    }
%>