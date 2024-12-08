<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">

        <!-- ===== CSS ===== -->
        <link rel="stylesheet" href="login.css">
    
        <!-- ===== BOX ICONS ===== -->
        <link href='https://cdn.jsdelivr.net/npm/boxicons@2.0.5/css/boxicons.min.css' rel='stylesheet'>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.1/css/all.min.css" />
        <title>PROFOLIO LOGIN</title>
        <style type="text/css">
        <style>
            .home-link {
    position: fixed;
    top: 2rem;
    left: 2rem;
    display: flex;
    align-items: center;
    gap: 0.5rem;
    text-decoration: none;
    color: #2c3e50;
    font-weight: 500;
    padding: 0.75rem 1.25rem;
    background-color: white;
    border-radius: 30px;
    box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
    transition: all 0.3s ease;
    z-index: 1000;
}

.home-link:hover {
    transform: translateY(-2px);
    box-shadow: 0 6px 8px rgba(0, 0, 0, 0.15);
    color: #3498db;
}

.home-link i {
    font-size: 1.2rem;
}

@media (max-width: 768px) {
    .home-link {
        top: 1rem;
        left: 1rem;
        padding: 0.5rem 1rem;
        font-size: 0.9rem;
    }
}
        </style>
        
    </head>
    <body>
   
   <a href="/" class="home-link">
        <i class="fas fa-arrow-left"></i>
        <span>Home</span>
    </a>
        <div class="login">
        <div class="login__heading">
                <h1 class="big-heading">
                    Login<br>
                    To<br>
                    ProFolio
                </h1>
            </div>
            <div class="login__content">
                <div class="login__img">
                    <img src="images/img-login.svg" alt="">
                </div>

                <div class="login__forms">
                    <form action="checklogin" method="post" class="login__registre" id="login-in">
                        <h1 class="login__title">Sign In</h1>
                        <h4 align="center" style="color:red">
                <c:out value="${message}"></c:out>
            </h4>
                        <div class="login__box">
                            <i class='bx bx-user login__icon'></i>
                            <input type="text" name="identifier" placeholder="Email or Username" class="login__input">
                        </div>
    
                        <div class="login__box">
                            <i class='bx bx-lock-alt login__icon'></i>
                            <input type="password" name="password" placeholder="Password" class="login__input">
                        </div>

                        <!-- <a href="#" class="login__forgot">Forgot password?</a> -->

                        <!-- <a href="#" class="login__button">Sign In</a> -->
                        <button type="submit" class="login__button" >Sign In</button>
                    </form>
  
                </div>
            </div>
        </div>

        <!--===== MAIN JS =====-->
        <script src="login.js"></script>
    </body>
</html>