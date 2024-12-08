<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@page import="com.klef.jfsd.sdp.model.Mentor"%>
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
    <title>ProFolio</title>
    <link rel="stylesheet" href="styles.css"> <!-- Link to your CSS file -->
    <style>
    .no-data-message {
    text-align: center;
    font-size: 1.2em;
    color: gray;
    margin: 20px 0;
}
    </style>
</head>
<body>
    <%@include file="mentornavbar.jsp" %>
    <h3 align="center"><u>Student List</u></h3>
    <div class="table-container">
        <c:choose>
            <c:when test="${empty studentlist}">
                <p class="no-data-message">No students available.</p>
            </c:when>
            <c:otherwise>
                <table>
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>Full Name</th>
                            <th>Gender</th>
                            <th>Date of Birth</th>
                            <th>Age</th>
                            <th>Department</th>
                            <th>Email</th>
                            <th>Contact</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${studentlist}" var="student">
                            <tr>
                                <td><c:out value="${student.id}"/></td>
                                <td><c:out value="${student.firstname} ${student.lastname}"/></td>
                                <td><c:out value="${student.gender}"/></td>
                                <td><c:out value="${student.dateofBirth}"/></td>
                                <td><c:out value="${student.age}"/></td>
                                <td><c:out value="${student.department}"/></td>
                                <td><c:out value="${student.email}"/></td>
                                <td><c:out value="${student.contact}"/></td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </c:otherwise>
        </c:choose>
    </div>
</body>
</html>
<%
    }
%>
