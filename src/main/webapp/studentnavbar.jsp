<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="adminnavbar.css">
</head>
<body id="body-pd">
    <!-- Navbar -->
    <div class="navbar">
    <div class="l-navbar" id="navbar">
      <nav class="nav">
        <div>
          <div class="nav__brand">
            <ion-icon name="menu-outline" class="nav__toggle" id="nav-toggle"></ion-icon>
            <a href="#" class="nav__logo">ProFolio</a>
          </div>
          <div class="nav__list">
            <a href="studenthome" class="nav__link active">
              <ion-icon name="home-outline" class="nav__icon"></ion-icon>
              <span class="nav__name">Dashboard</span>
            </a>
            
             <div class="nav__link collapse">
              <ion-icon name="folder-outline" class="nav__icon"></ion-icon>
              <span class="nav__name">Manage Projects</span>
              <ion-icon name="chevron-down-outline" class="collapse__link"></ion-icon>
              <ul class="collapse__menu">
                <a href="uploadproject" class="collapse__sublink">Upload Projects</a>
                <a href="viewprojects" class="collapse__sublink">View All Projects</a>
              </ul>
            </div>
            <div class="nav__link collapse" >
                  <ion-icon name="file-tray-full-outline" class="nav__icon"></ion-icon>
                  <span class="nav__name" >Manage PortFolio</span>
                  <ion-icon name="chevron-down-outline" class="collapse__link" ></ion-icon>
                  <ul class="collapse__menu" >
                      <a href="updateportfolio" class="collapse__sublink" >Update PortFolio</a>
                      <a href="viewportfolio" class="collapse__sublink" >View PortFolio</a>
                  </ul>
              </div>
            <a href="viewmilestones" class="nav__link">
                <ion-icon name="podium-outline" class="nav__icon"></ion-icon>
                <span class="nav__name">View Milestones</span>
              </a>
              
              <a href="viewportfoliofeedback" class="nav__link">
              <ion-icon name="newspaper-outline" class="nav__icon"></ion-icon>
              <span class="nav__name">Portfolio feedback</span>
            </a>
            <a href="studentprofile" class="nav__link">
              <ion-icon name="person-circle-outline" class="nav__icon"></ion-icon>
              <span class="nav__name">My Profile</span>
            </a>
            
            
          </div>
        </div>
        <a href="studentlogout" class="nav__link">
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