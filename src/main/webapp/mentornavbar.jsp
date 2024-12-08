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
                        <a href="mentorhome" class="nav__link active">
                            <ion-icon name="home-outline" class="nav__icon"></ion-icon>
                            <span class="nav__name">Dashboard</span>
                        </a>
                        <a href="viewstudents" class="nav__link">
                            <ion-icon name="people-outline" class="nav__icon"></ion-icon>
                            <span class="nav__name">View Students</span>
                        </a>

                        <div  class="nav__link collapse">
                            <ion-icon name="folder-outline" class="nav__icon"></ion-icon>
                            <span class="nav__name">Manage Projects</span>

                            <ion-icon name="chevron-down-outline" class="collapse__link"></ion-icon>

                            <ul class="collapse__menu">
                                <a href="viewallprojects" class="collapse__sublink">View Projects</a>
                                
                            </ul>
                        </div>

                        <div class="nav__link collapse">
                            <ion-icon name="documents-outline" class="nav__icon"></ion-icon>
                            <span class="nav__name">Manage Portfolio</span>

                            <ion-icon name="chevron-down-outline" class="collapse__link"></ion-icon>

                            <ul class="collapse__menu">
                                <a href="viewallportfolios" class="collapse__sublink">All Portfolios</a>
                                
                            </ul>
                        </div>
                        <div class="nav__link collapse">
                            <ion-icon name="podium-outline" class="nav__icon"></ion-icon>
                            <span class="nav__name">Manage Milestones</span>

                            <ion-icon name="chevron-down-outline" class="collapse__link"></ion-icon>

                            <ul class="collapse__menu">
                                <a href="reviewmilestones" class="collapse__sublink">Review Milestones</a>
                                <a href="viewallproposedmilestones" class="collapse__sublink">Review Proposed Milestones</a>
                                
                            </ul>
                        </div>
                        <a href="mentorprofile" class="nav__link">
			              <ion-icon name="person-circle-outline" class="nav__icon"></ion-icon>
			              <span class="nav__name">My Profile</span>
			            </a>
                        
                    </div>
                </div>

                <a href="mentorlogout" class="nav__link">
                    <ion-icon name="log-out-outline" class="nav__icon"></ion-icon>
                    <span class="nav__name">Log Out</span>
                </a>
            </nav>
        </div>

        </div>
        

        <!-- ===== IONICONS ===== -->
        <script src="https://unpkg.com/ionicons@5.1.2/dist/ionicons.js"></script>
        
        <!-- ===== MAIN JS ===== -->
        <script src="../script.js"></script>
    </body>
</html>