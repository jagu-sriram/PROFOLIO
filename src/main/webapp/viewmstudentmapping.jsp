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
    <h3 align="center"><u>Mapping List</u></h3>
    <div class="table-container">
    <table>
        <thead>
            <tr>
                <th>ID</th>
            <th>Mentor ID</th>
            <th>Mentor Name</th>
            <th>Student ID</th>
            <th>Student Name</th>
            
            
            
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${mcmdata}" var="mcm">
                
<tr> 
<td><c:out value="${mcm.mappingid}" /></td> 
<td><c:out value="${mcm.mentor.id}" /></td> 
<td><c:out value="${mcm.mentor.lastname}" /></td>
<td><c:out value="${mcm.student.id}" /></td> 
<td><c:out value="${mcm.student.lastname}" /></td> 

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
