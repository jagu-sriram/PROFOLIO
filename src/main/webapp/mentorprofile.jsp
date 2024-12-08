<%@page import="com.klef.jfsd.sdp.model.Mentor"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%
    Mentor m = (Mentor) session.getAttribute("mentor");
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
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Mentor Profile</title>
    
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.1/css/all.min.css">
    <style>
    /* Variables */
:root {
    --primary-color: #2c3e50;
    --secondary-color: #3498db;
    --background-color: #f5f5f5;
    --card-background: #ffffff;
    --text-primary: #2c3e50;
    --text-secondary: #666;
    --shadow: 0 10px 20px rgba(0, 0, 0, 0.1);
    --transition: all 0.3s ease;
}

/* Base Styles */
body {
    margin: 0;
    padding: 2rem 0 0 6.75rem;
    font-family: -apple-system, BlinkMacSystemFont, 'Segoe UI', Roboto, Oxygen, Ubuntu, Cantarell, sans-serif;
    background-color: var(--background-color);
    color: var(--text-primary);
    line-height: 1.6;
}

/* Profile Card */
.profile-container {
    max-width: 900px;
    margin: 2rem auto;
    padding: 0 1rem;
}

.profile-card {
    background: var(--card-background);
    border-radius: 20px;
    box-shadow: var(--shadow);
    padding: 3rem;
    text-align: center;
    position: relative;
    overflow: hidden;
}

.profile-card::before {
    content: '';
    position: absolute;
    top: 0;
    left: 0;
    right: 0;
    height: 150px;
    background: linear-gradient(135deg, var(--primary-color), var(--secondary-color));
    z-index: 0;
}

/* Profile Header */
.profile-header {
    position: relative;
    z-index: 1;
    margin-bottom: 3rem;
}

.profile-header h3 {
    color: white;
    font-size: 2rem;
    margin: 0 0 2rem;
    font-weight: 600;
}

/* Profile Image */
.profile-img {
    width: 180px;
    height: 180px;
    border-radius: 50%;
    border: 5px solid white;
    box-shadow: var(--shadow);
    margin: 0 auto 2rem;
    object-fit: cover;
    background-color: white;
}

/* Profile Details */
.profile-details {
    display: grid;
    grid-template-columns: repeat(auto-fit, minmax(250px, 1fr));
    gap: 2rem;
    margin-bottom: 2rem;
}

.detail-item {
    padding: 1rem;
    background-color: #f8f9fa;
    border-radius: 10px;
    transition: var(--transition);
}

.detail-item:hover {
    transform: translateY(-5px);
    box-shadow: 0 5px 15px rgba(0, 0, 0, 0.1);
}

.detail-label {
    font-weight: 600;
    color: var(--primary-color);
    margin-bottom: 0.5rem;
    font-size: 0.9rem;
    text-transform: uppercase;
    letter-spacing: 0.5px;
}

.detail-value {
    color: var(--text-secondary);
    font-size: 1.1rem;
    word-break: break-word;
}

/* Update Profile Button */
.update-profile-btn {
    display: inline-block;
    padding: 1rem 2rem;
    background: linear-gradient(135deg, var(--primary-color), var(--secondary-color));
    color: white;
    border-radius: 30px;
    text-decoration: none;
    font-weight: 500;
    transition: var(--transition);
    margin-top: 2rem;
    border: none;
    cursor: pointer;
}

.update-profile-btn:hover {
    transform: translateY(-2px);
    box-shadow: 0 5px 15px rgba(0, 0, 0, 0.2);
}

/* Responsive Design */
@media (max-width: 768px) {
    .profile-container {
        margin: 1rem;
    }
    
    .profile-card {
        padding: 2rem 1rem;
    }
    
    .profile-details {
        grid-template-columns: 1fr;
        gap: 1rem;
    }
    
    .profile-img {
        width: 150px;
        height: 150px;
    }
    
    .profile-header h3 {
        font-size: 1.75rem;
    }
}
    </style>
</head>
<body>
    <%@include file="mentornavbar.jsp" %>
    
    <div class="profile-container">
        <div class="profile-card">
            <div class="profile-header">
                <h3>My Profile</h3>
                
            </div>
            
            <div class="profile-details">
                <div class="detail-item">
                    <div class="detail-label">ID</div>
                    <div class="detail-value"><%=m.getId()%></div>
                </div>
                
                <div class="detail-item">
                    <div class="detail-label">First Name</div>
                    <div class="detail-value"><%=m.getFirstname()%></div>
                </div>
                
                <div class="detail-item">
                    <div class="detail-label">Last Name</div>
                    <div class="detail-value"><%=m.getLastname()%></div>
                </div>
                
                <div class="detail-item">
                    <div class="detail-label">Gender</div>
                    <div class="detail-value"><%=m.getGender()%></div>
                </div>
                
                <div class="detail-item">
                    <div class="detail-label">Date of Birth</div>
                    <div class="detail-value"><%=m.getDateofBirth()%></div>
                </div>
                 <div class="detail-item">
                    <div class="detail-label">Qualification</div>
                    <div class="detail-value"><%=m.getQualification()%></div>
                </div>
                <div class="detail-item">
                    <div class="detail-label">Department</div>
                    <div class="detail-value"><%=m.getDepartment()%></div>
                </div>
                 <div class="detail-item">
                    <div class="detail-label">Designation</div>
                    <div class="detail-value"><%=m.getDesignation()%></div>
                </div>
                
                <div class="detail-item">
                    <div class="detail-label">Email</div>
                    <div class="detail-value"><%=m.getEmail()%></div>
                </div>
                
                <div class="detail-item">
                    <div class="detail-label">Contact</div>
                    <div class="detail-value"><%=m.getContact()%></div>
                </div>
            </div>
            
            
        </div>
    </div>

    <script src="https://unpkg.com/ionicons@5.1.2/dist/ionicons.js"></script>
</body>
</html>
<% } %>