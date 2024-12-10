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
    <title>PROFOLIO</title>
    
</head>
<body>
    <%@include file="adminnavbar.jsp" %>
    <h3 align="center"><u>Mentor List</u></h3>
    <div class="table-container">
    <table>
        <thead>
            <tr>
                <th>ID</th>
            <th>FULL NAME</th>
            <th>GENDER</th>
            <th>DATE OF BIRTH</th>
            <th>DEPARTMENT</th>
             <th>QUALIFICATION</th>
              <th>DESIGNATION</th>
               <th>EXPERIENCE</th>
            <th>EMAIL</th>
            <th>CONTACT</th>
            <th>Action</th>
            
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${mentorlist}" var="mentor">
                <tr>
                   <td><c:out value="${mentor.id}"/></td>
                <td><c:out value="${mentor.firstname} ${mentor.lastname}"/></td>
                <td><c:out value="${mentor.gender}"/></td>
                <td><c:out value="${mentor.dateofBirth}"/></td>
                <td><c:out value="${mentor.department}"/></td>
                <td><c:out value="${mentor.qualification}"/></td>
               <td><c:out value="${mentor.designation}"/></td>
               <td><c:out value="${mentor.experience}"/></td>
                <td><c:out value="${mentor.email}"/></td>
                <td><c:out value="${mentor.contact}"/></td>
                <td>
             <a href='<c:url value="updateM?id=${mentor.id}"></c:url>'>Update</a>
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
