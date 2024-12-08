<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<head>
    <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>PROFOLIO</title>
    <link rel="stylesheet" href="style.css" />
  </head>
</head>
<body>
<main>
      <div class="big-wrapper light">
        <img src="./img/shape.png" alt="" class="shape" />

        <header>
          <div class="container">
            <div class="logo">
              <img src="./images/logo.jpg" alt="Logo" />
              <h3>ProFolio</h3>
            </div>

            <div class="links">
              <ul>
                <li><a href="#features">Features</a></li>
                <li><a href="#about">About</a></li>
                <li><a href="#contact">Contact Us</a></li>
                <li><a href="login" class="btn">Login</a></li>
              </ul>
            </div>

            <div class="overlay"></div>

            <div class="hamburger-menu">
              <div class="bar"></div>
            </div>
          </div>
        </header>

        <!-- Showcase Area -->
        <div class="showcase-area">
          <div class="container">
            <div class="left">
              <div class="big-title">
                <h1>Manage Projects Effectively,</h1>
                <h1>Showcase Your Portfolio with Ease.</h1>
              </div>
              <p class="text">
                ProFolio is your go-to solution for managing projects and portfolios. Whether you’re a freelancer, team leader, or entrepreneur, organize, track, and present your work in one seamless platform. Collaborate, meet deadlines, and take your productivity to the next level.
              </p>
              <div class="cta">
                <a href="#" class="btn">Get started</a>
              </div>
            </div>

            <div class="right">
              <img src="images/landingpage.png" alt="Person managing projects" class="person" />
            </div>
          </div>
        </div>

        <!-- About Section -->
        <section id="about">
          <div class="container">
            <h2>About Us.</h2>
            <p>
              At ProFolio, we believe that every project deserves to be organized, tracked, and showcased efficiently. We empower professionals and teams to manage their projects, track progress, and deliver results on time. Our platform is designed to simplify project management and make it easier to present your accomplishments through a stunning portfolio.
            </p>
          </div>
        </section>

        <!-- Features Section -->
        <section id="features">
          <h2>Features</h2>
          <div class="features-container">
            <div class="feature">
              <div class="icon">
                <svg
                  xmlns="http://www.w3.org/2000/svg"
                  fill="none"
                  viewBox="0 0 24 24"
                  stroke-width="1.5"
                  stroke="currentColor"
                  class="w-6 h-6"
                >
                  <path
                    stroke-linecap="round"
                    stroke-linejoin="round"
                    d="M3.75 13.5l10.5-11.25L12 10.5h8.25L9.75 21.75 12 13.5H3.75z"
                  />
                </svg>
              </div>
              <div class="feature-title">Project Tracking</div>
              <div class="feature-description">
                Easily track the status and progress of your projects. Set goals, milestones, and deadlines to ensure you stay on track.
              </div>
            </div>

            <div class="feature">
              <div class="icon">
                <svg
                  xmlns="http://www.w3.org/2000/svg"
                  fill="none"
                  viewBox="0 0 24 24"
                  stroke-width="1.5"
                  stroke="currentColor"
                  class="w-6 h-6"
                >
                  <path
                    stroke-linecap="round"
                    stroke-linejoin="round"
                    d="M12 18v-5.25m0 0a6.01 6.01 0 001.5-.189m-1.5.189a6.01 6.01 0 01-1.5-.189m3.75 7.478a12.06 12.06 0 01-4.5 0m3.75 2.383a14.406 14.406 0 01-3 0M14.25 18v-.192c0-.983.658-1.823 1.508-2.316a7.5 7.5 0 10-7.517 0c.85.493 1.509 1.333 1.509 2.316V18"
                  />
                </svg>
              </div>
              <div class="feature-title">Portfolio Showcase</div>
              <div class="feature-description">
                Highlight your projects with a professional portfolio that is easy to customize and share with potential clients or employers.
              </div>
            </div>

            <div class="feature">
              <div class="icon">
                <svg
                  xmlns="http://www.w3.org/2000/svg"
                  fill="none"
                  viewBox="0 0 24 24"
                  stroke-width="1.5"
                  stroke="currentColor"
                  class="w-6 h-6"
                >
                  <path
                    stroke-linecap="round"
                    stroke-linejoin="round"
                    d="M17.982 18.725A7.488 7.488 0 0012 15.75a7.488 7.488 0 00-5.982 2.975m11.963 0a9 9 0 10-11.963 0m11.963 0A8.966 8.966 0 0112 21a8.966 8.966 0 01-5.982-2.275M15 9.75a3 3 0 11-6 0 3 3 0 016 0z"
                />
              </svg>
              </div>
              <div class="feature-title">Team Collaboration</div>
              <div class="feature-description">
                Collaborate with team members in real-time. Share updates, delegate tasks, and stay in sync with everyone on the team.
              </div>
            </div>
          </div>
        </section>

        <!-- Contact Us Section -->
        <section id="contact">
          <div class="container">
            <div class="content">
              <div class="left-side">
                <div class="address details">
                  <ion-icon name="location-outline"></ion-icon>
                  <div class="topic">Address</div>
                  <div class="text-one">1234 Main St</div>
                  <div class="text-two">City, Country</div>
                </div>
                <div class="phone details">
                  <ion-icon name="call-outline"></ion-icon>
                  <div class="topic">Phone</div>
                  <div class="text-one">+123 456 7890</div>
                  <div class="text-two">+098 765 4321</div>
                </div>
                <div class="email details">
                  <ion-icon name="mail-outline"></ion-icon>
                  <div class="topic">Email</div>
                  <div class="text-one">info@profolio.com</div>
                  <div class="text-two">support@profolio.com</div>
                </div>
              </div>
              <div class="right-side">
                <div class="topic-text">Send us a message</div>
                <p>Have any questions about our project management or portfolio system? Reach out to us, and we’ll get back to you as soon as possible!</p>
                <h4 align="center" style="color:green">
    <c:out value="${message}"></c:out>
    </h4>
                <form action="sendemail" method="post">
                  <div class="input-box">
                    <input type="text" placeholder="Enter your name" name="name" required />
                  </div>
                  <div class="input-box">
                    <input type="email" placeholder="Enter your email" name="email" required />
                  </div>
                  <div class="input-box message-box">
                    <textarea placeholder="Enter your message" name="message" required></textarea>
                  </div>
                  <div class="button">
                    <input type="submit" value="Send Now" />
                  </div>
                </form>
              </div>
            </div>
          </div>
        </section>

        <div class="bottom-area"></div>
      </div>
    </main>

    <!-- JavaScript Files -->
    <script src="https://kit.fontawesome.com/a81368914c.js"></script>
    <!-- ===== IONICONS ===== -->
        <script src="https://unpkg.com/ionicons@5.1.2/dist/ionicons.js"></script>
    <script src="./app.js"></script>
</body>
</html>