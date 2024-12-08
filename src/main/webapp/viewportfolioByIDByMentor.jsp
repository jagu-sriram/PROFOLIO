<%@page import="com.klef.jfsd.sdp.model.MentorStudentMapping"%>
<%@page import="com.klef.jfsd.sdp.model.Mentor"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%
Mentor m = (Mentor) session.getAttribute("mentor");
MentorStudentMapping msm = (MentorStudentMapping) session.getAttribute("msm");
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
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Your Portfolio</title>
    <link rel="stylesheet" href="viewportfolio.css" />
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.1/css/all.min.css" />
</head>
<body>
    <%@include file="mentornavbar.jsp" %>
    
    <div class="container">
        <div style="text-align: right;">
            <a href='<c:url value="portfoliofeedbackform?id=${portfolio.id}"></c:url>' class="btn-feedback">
                Give Feedback
            </a>
        </div>
        
        <!-- Home Section -->
        <div class="home" id="Home">
            <img src="displayprofileimage?id=${portfolio.student.id}" class="profile-image" alt="Profile" />
            <div class="name">
                <p>${portfolio.student.firstname} ${portfolio.student.lastname}</p>
                <p>${portfolio.tagline}</p>
            </div>
            <div class="social-media">
                <ul>
                    <li><a href="#" target="_blank"><i class="fab fa-facebook-f"></i></a></li>
                    <li><a href="#" target="_blank"><i class="fab fa-instagram"></i></a></li>
                    <li><a href="#" target="_blank"><i class="fab fa-linkedin-in"></i></a></li>
                    <li><a href="#" target="_blank"><i class="fab fa-github"></i></a></li>
                </ul>
            </div>
            <a href="portfolio-pdf-dowload?id=${portfolio.id}" target="_blank" class="btn-resume">
                <i class="fas fa-download"></i> Download Resume
            </a>
        </div>
        
        <!-- About Section -->
        <div class="about" id="About">
            <h2>About Me</h2>
            <div class="about-wrapper">
                <div class="left-about">
                    <p>${portfolio.info}</p>
                    <div class="about-image">
                        <img src="displayprofileimage?id=${portfolio.student.id}" alt="About" />
                    </div>
                </div>
                <div class="right-about">
                    <div class="information">
                        <h3>Information</h3>
                        <div class="info">
                            <div class="info-data">
                                <i class="fas fa-user"></i>
                                <p>${portfolio.student.firstname} ${portfolio.student.lastname}</p>
                            </div>
                            <div class="info-data">
                                <i class="fas fa-phone-alt"></i>
                                <p>${portfolio.student.contact}</p>
                            </div>
                            <div class="info-data">
                                <i class="fas fa-envelope"></i>
                                <p>${portfolio.student.email}</p>
                            </div>
                        </div>
                        
                        <div class="Experience">
                            <h3>Achievements</h3>
                            <div class="exp">
                                <c:forEach var="achievement" items="${portfolio.achievements}">
                                    <div class="info-data">
                                        <i>
                                            <c:choose>
                                                <c:when test="${achievement.achievementType.name == 'Internship'}">
                                                    <i class="fas fa-medal"></i>
                                                </c:when>
                                                <c:when test="${achievement.achievementType.name == 'Award'}">
                                                    <i class="fas fa-trophy"></i>
                                                </c:when>
                                                <c:when test="${achievement.achievementType.name == 'Certification'}">
                                                    <i class="fas fa-certificate"></i>
                                                </c:when>
                                            </c:choose>
                                        </i>
                                        <div>
                                            <p>${achievement.title}</p>
                                            <p>${achievement.description}</p>
                                        </div>
                                    </div>
                                </c:forEach>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        
        <!-- Skills Section -->
        <div class="skills" id="Skills">
            <h2>My Expertise Area</h2>
            <div class="technologies">
                <c:forEach var="skill" items="${portfolio.skills}">
                    <div class="tech">
                        <p>
                            ${skill.skillType.name} <span class="percentage">${skill.percentage}%</span>
                        </p>
                        <div>
                            <div style="width: ${skill.percentage}%;"></div>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </div>
        
        <!-- Projects Section -->
        <div class="work" id="Work">
            <h2>My Projects</h2>
            <div class="recent-work">
                <c:forEach items="${portfolio.selectedprojects}" var="project">
                    <div>
                        <img src="displayprojectimage?id=${project.project.id}" alt="Project" />
                        <span>
                            <a href="<c:out value='${project.project.link}'/>" target="_blank">Demo</a>
                        </span>
                        <label>${project.project.title}</label>
                    </div>
                </c:forEach>
            </div>
        </div>
    </div>

    <script src="https://unpkg.com/scrollreveal"></script>
    <script src="https://unpkg.com/ionicons@5.1.2/dist/ionicons.js"></script>
    <script>
        /* // Initialize ScrollReveal
        ScrollReveal().reveal('.home', { delay: 200 });
        ScrollReveal().reveal('.about', { delay: 300 });
        ScrollReveal().reveal('.skills', { delay: 400 });
        ScrollReveal().reveal('.work', { delay: 500 }); */
        
        // Initialize skill bars animation
        document.addEventListener('DOMContentLoaded', function() {
            const techDivs = document.querySelectorAll('.tech > div > div');
            techDivs.forEach(div => {
                const width = div.style.width;
                div.style.width = '0';
                setTimeout(() => {
                    div.style.width = width;
                }, 2000);
            });
        });
    </script>
</body>
</html>
<% } %>