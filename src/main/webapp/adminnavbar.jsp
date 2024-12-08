<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">

        <!-- ===== CSS ===== -->
        <link rel="stylesheet" href="adminnavbar.css">
        
        <title>PROFOLIO</title>
    </head>
    <body id="body-pd">
    <div class="navbar">
        <div class="l-navbar" id="navbar">
            <nav class="nav">
                <div>
                    <div class="nav__brand">
                        <ion-icon name="menu-outline" class="nav__toggle" id="nav-toggle"></ion-icon>
                        <a href="#" class="nav__logo">ProFolio</a>
                    </div>
                    <div class="nav__list">
                        <a href="adminhome" class="nav__link active">
                            <ion-icon name="home-outline" class="nav__icon"></ion-icon>
                            <span class="nav__name">Dashboard</span>
                        </a>
                        

                        

                        <div class="nav__link collapse">
                            <ion-icon name="people-outline" class="nav__icon"></ion-icon>
                            <span class="nav__name">Manage Mentor</span>

                            <ion-icon name="chevron-down-outline" class="collapse__link"></ion-icon>

                            <ul class="collapse__menu">
                                <a href="addmentor" class="collapse__sublink">Add</a>
                                <a href="addmentorcsv" class="collapse__sublink">Add Mentor Csv</a>
                                <a href="viewallmentors" class="collapse__sublink">View</a>
                                <a href="updatementor" class="collapse__sublink">Update</a>
                                <a href="deletementor" class="collapse__sublink">Delete</a>
                            </ul>
                        </div>
                        <div class="nav__link collapse">
                            <ion-icon name="people-outline" class="nav__icon"></ion-icon>
                            <span class="nav__name">Manage Student</span>

                            <ion-icon name="chevron-down-outline" class="collapse__link"></ion-icon>

                            <ul class="collapse__menu">
                                <a href="addstudent" class="collapse__sublink">Add</a>
                                <a href="addstudentcsv" class="collapse__sublink">Add Student CSV</a>
                                <a href="viewallstudents" class="collapse__sublink">View</a>
                                <a href="updatestudent" class="collapse__sublink">Update</a>
                                <a href="deletestudent" class="collapse__sublink">Delete</a>
                            </ul>
                        </div>
                        <a href="mstudentmapping" class="nav__link">
                            <ion-icon name="add-circle-outline" class="nav__icon"></ion-icon>
                            <span class="nav__name">Mentor Student Mapping</span>
                        </a>
                        
                        
                        <a href="viewmstudentmapping" class="nav__link">
                            <ion-icon name="people-circle-outline" class="nav__icon"></ion-icon>
                            <span class="nav__name">View Mapping</span>
                        </a>
                    </div>
                </div>

                <a href="login" class="nav__link">
                    <ion-icon name="log-out-outline" class="nav__icon"></ion-icon>
                    <span class="nav__name">Log Out</span>
                </a>
            </nav>
        </div>

        </div>
        

        <!-- ===== IONICONS ===== -->
        <script src="https://unpkg.com/ionicons@5.1.2/dist/ionicons.js"></script>
        
        <!-- ===== MAIN JS ===== -->
        <script src="script.js"></script>
    </body>
</html>