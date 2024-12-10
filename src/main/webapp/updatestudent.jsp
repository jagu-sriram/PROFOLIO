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
<html>
<head>
    <meta charset="UTF-8">
    <title>ProFolio</title>
    
</head>
<body>
    <%@include file="adminnavbar.jsp" %>
    <h3 align="center"><u>Student List</u></h3>
    <div class="table-container">
    <table>
        <thead>
            <tr>
                <th>ID</th>
            <th>FULL NAME</th>
            <th>GENDER</th>
            <th>DATE OF BIRTH</th>
            <th>AGE</th>
            <th>DEPARTMENT</th>
            <th>EMAIL</th>
            <th>CONTACT</th>
            <th>ACTION</th>
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
                
                 <td>
             <a href='<c:url value="update?id=${student.id}"></c:url>'>Update</a>
           </td>
                
                </tr>
            </c:forEach>
        </tbody>
    </table>
    </div>
</body>
</html>
<%
    }
%>