<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">

        <!-- ===== CSS ===== -->
        <link rel="stylesheet" href="adminnavbar.css">
        <link rel="stylesheet" href="admindashboard.css">
        <title>PROFOLIO</title>
    </head>
    <body >   
<%@include file="adminnavbar.jsp" %>     
        <section class="dashboard">
            <div class="container">
                <div class="overview">
                    <div class="title">
                        <ion-icon name="speedometer"></ion-icon>
                        <span class="text">Dashboard</span>
                    </div>
                    <div class="boxes">
                        <div class="box box1">
                            <ion-icon name="eye-outline"></ion-icon>
                            <span class="text">Total Students</span>
                            <span class="number">${studentcount}</span>
                        </div>
                        <div class="box box2">
                            <ion-icon name="people-outline"></ion-icon>
                            <span class="text">Total Mentors</span>
                            <span class="number">${mentorcount}</span>
                        </div>
                        <div class="box box3">
                            <ion-icon name="chatbubbles-outline"></ion-icon>
                            <span class="text">Total Projects</span>
                            <span class="number">${projectscount}</span>
                        </div>
                    </div> 
                </div>
                
    
                <!-- Recent Activities -->
                <div class="data-table activityTable">
                    <div class="title">
                        <ion-icon name="time-outline"></ion-icon>
                        <span class="text">Recent Activities</span>
                    </div>
                    <div>
                        <!-- Enter any table or section here -->
                    </div>
                </div>
    
                
                <!-- Content -->
                <div style="display:none" class="data-table userDetailsTable">
                    <div class="title">
                        <ion-icon name="folder-outline"></ion-icon>
                        <span class="text">Content</span>
                    </div>
                    <div>
                        <!-- Enter any table or section here -->
                    </div>
                </div>
    
                <!-- Analytics -->
                <div style="display:none" class="data-table EditUserRole">
                    <div class="title">
                        <ion-icon name="analytics-outline"></ion-icon>
                        <span class="text">Analytics</span>
                    </div>
                    <div>
                        <!-- Enter any table or section here -->
                    </div>
                </div>
    
                <!--  Likes -->
                <div style="display:none" class="data-table VehicleDetails">
                    <div class="title">
                        <ion-icon name="heart-outline"></ion-icon>
                        <span class="text">Vehicles</span>
                    </div>
                    <div>
                        <!-- Enter any table or section here -->
                    </div>
                </div>
    
                <!-- Downloads section -->
                <div style="display:none" class="data-table downloads">
                    <div class="title">
                        <ion-icon name="chatbubbles-outline"></ion-icon>
                        <span class="text">Comments</span>
                    </div>
                    <div>
                        <!-- Enter any table or section here -->
                    </div>
                </div>
            </div>
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