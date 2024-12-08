<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ page import="com.klef.jfsd.sdp.model.Student" %>
<%
    Student s = (Student) session.getAttribute("student");
    if (s == null) {
%>
    <script type="text/javascript">
        alert("Session Expired. Please Login Again");
        window.location.href = "login"; // Adjust this path to your login page
    </script>
<%
    } else {
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>ProFolio</title>
    <link rel="stylesheet" href="updateportfolio.css">
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/js/materialize.min.js"></script> 
        
    
        <style>
        body {
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            background-color: #e0f7fa;
            margin: 0;
            overflow: hidden; /* Prevents the body from scrolling */
        }
        .form-container {
            background-color: #ffffff;
            border-radius: 8px;
            box-shadow: 0 4px 10px rgba(0, 0, 0, 0.2);
            padding: 20px;
            width: 500px;
            display: flex;
            flex-direction: column;
            align-items: center;
            transition: transform 0.4s ease;
            max-height: 90vh; /* Limits the maximum height to prevent overflow */
    overflow-y: auto; /* Allows scrolling within the form */
        }
        .form-section {
            display: none;
            width: 100%;
        }
        .form-section.active {
            display: block;
        }
        .form-container h4 {
            color: #00796b;
        }
        .btn-custom {
            background-color: #00796b;
            margin: 10px 5px;
            border-radius: 20px;
            width: 150px;
        }
        .btn-custom:hover {
            background-color: #004d40;
        }
        .input-field select, .input-field textarea, .input-field input {
            background-color: #e0f2f1;
        }
        .button-group {
            display: flex;
            justify-content: center;
            gap: 10px;
        }
        .p{
          font-size: 20px;
        }
        
        
    </style>


</head>
<body>

<%@include file="studentnavbar.jsp" %>
<section>
    <div class="form-container">
        <h4>Update Portfolio</h4>
        <form id="portfolioForm" method="post" action="submitPortfolio">
            <!-- Portfolio Form -->
            <div id="portfolio-section" class="form-section active">
                <div class="input-field">
                    <input type="text" id="tagline" name="tagline" value="${portfolio.tagline}" required >
                    <label for="tagline">Portfolio Tagline</label>
                </div>
                <div class="input-field">
                    <textarea id="info" name="info" class="materialize-textarea" required >${portfolio.info}</textarea>
                    <label for="info">Portfolio Info</label>
                </div>
                <div class="button-group">
                    <button type="button" class="btn btn-custom next-button">Next</button>
                </div>
            </div>

            <!-- Skills Form -->
            <div id="skills-section" class="form-section">
            <p>Skills</p>
			    <div id="skills-container">
			        <c:forEach items="${portfolio.skills}" var="skill" varStatus="status">
					    <div class="skill-row" data-skill-id="${skill.id}"> <!-- Set data-skill-id -->
					        <div class="input-field">
					            <select name="skillType[]" required>
					                <option value="" disabled>Select Skill Type</option>
					                <c:forEach items="${skillTypes}" var="skillType">
					                    <option value="${skillType.id}" ${skillType.id == skill.skillType.id ? 'selected' : ''}>${skillType.name}</option>
					                </c:forEach>
					            </select>
					            <label>Skill Type</label>
					        </div>
					        <div class="input-field">
					            <input type="number" name="percentage[]" value="${skill.percentage}" min="0" max="100" required>
					            <label>Skill Proficiency (%)</label>
					        </div>
					        <button type="button" class="btn btn-custom" onclick="removeSkillRow(this)">Remove</button>
					    </div>
					</c:forEach>
			    </div>
			    <button type="button" class="btn btn-custom" onclick="addSkill()">Add Another Skill</button>
			    <div class="button-group">
			        <button type="button" class="btn btn-custom prev-button">Previous</button>
			        <button type="button" class="btn btn-custom next-button">Next</button>
			    </div>
			</div>

            <!-- Achievements Form -->
            <div id="achievements-section" class="form-section">
            <p>Achievements</p>
                <div id="achievements-container">
                    <!-- Loop through existing achievements if any -->
                    <c:forEach items="${portfolio != null ? portfolio.achievements : []}" var="achievement" varStatus="status">
                        <div class="achievement-row" data-achievement-id="${achievement.id}">
						    <div class="input-field">
						        <input type="text" name="title[]" value="${achievement.title}" required maxlength="50">
						        <label>Achievement Title</label>
						    </div>
						    <div class="input-field">
						        <textarea name="description[]" class="materialize-textarea">${achievement.description}</textarea>
						        <label>Achievement Description</label>
						    </div>
						    <div class="input-field">
						        <select name="achievementType[]">
						            <option value="" disabled ${empty achievement.achievementType ? 'selected' : ''}>Select Achievement Type</option>
						            <c:forEach items="${achievementTypes}" var="achievementType">
						                <option value="${achievementType.id}" ${achievementType.id == achievement.achievementType.id ? 'selected' : ''}>${achievementType.name}</option>
						            </c:forEach>
						        </select>
						        <label>Achievement Type</label>
						    </div>
						    <button type="button" class="btn btn-custom" onclick="removeAchievementRow(this)">Remove</button>
						    <hr>
						</div>
                    </c:forEach>
                    <!-- Button to add more achievements -->
                    <button type="button" class="btn btn-custom" onclick="addAchievement()">Add Another Achievement</button>
                </div>
                <div class="button-group">
                    <button type="button" class="btn btn-custom prev-button">Previous</button>
                    <button type="button" class="btn btn-custom next-button">Next</button>
                </div>
            </div>

            <!-- Selected Projects Form -->
			<div id="projects-section" class="form-section">
			<p>Select Projects for Portfolio</p>
			    <div id="projects-container">
			        <!-- Loop through existing projects if any -->
			        <c:forEach items="${portfolio != null ? portfolio.selectedprojects : []}" var="project" varStatus="status">
			            <div class="project-row" data-project-id="${project.project.id}">
			                <div class="input-field">
			                    <!-- Single project selection -->
			                    <select name="projects[][projectId]" required>
			                        <option value="" disabled selected>Select Project</option>
			                        <c:forEach items="${projects}" var="availableProject">
			                            <option value="${availableProject.id}" ${availableProject.id == project.project.id ? 'selected' : ''}>${availableProject.title}</option>
			                        </c:forEach>
			                    </select>
			                    <label>Selected Project</label>
			                </div>
			                <div class="input-field">
			                    <input type="number" name="projects[][order]" min="1" value="${project.orderIndex}" placeholder="Order" required>
			                    <label>Project Order</label>
			                </div>
			                <button type="button" class="btn btn-custom" onclick="removeProjectRow(this)">Remove</button>
			            </div>
			        </c:forEach>
			
			        <!-- Button to add more projects -->
			        <button type="button" class="btn btn-custom" onclick="addProject()">Add Another Project</button>
			    </div>
			
			    <div class="button-group">
			        <button type="button" class="btn btn-custom prev-button">Previous</button>
			        <button type="button" class="btn btn-custom next-button">Next</button>
			    </div>
			</div>


            <!-- Status Form -->
            <div id="status-section" class="form-section">
                <div class="input-field">
                    <select id="status" name="status">
                        <option value="" disabled ${portfolio.status == null ? 'selected' : ''}>Select Status</option>
                        <option value="Updated" ${portfolio.status == 'Updated' ? 'selected' : ''}>Updated</option>
                        <option value="Ready for Review" ${portfolio.status == 'Ready for Review' ? 'selected' : ''}>Ready for Review</option>
                    </select>
                    <label>Status</label>
                </div>
                <input type="hidden" name="portfolioData" id="jsonData">
                <div class="button-group">
                    <button type="button" class="btn btn-custom prev-button">Previous</button>
                    <button type="button" class="btn btn-custom" onclick="submitForm()">Submit</button>
                </div>
            </div>
        </form>
    </div>
</section>


<script>
    $(document).ready(function () {
        $('select').formSelect();
        let currentSectionIndex = 0;
        const sections = $('.form-section');
        $(sections[currentSectionIndex]).addClass('active');

        $('.next-button').click(function () {
            if (currentSectionIndex < sections.length - 1) {
                $(sections[currentSectionIndex]).removeClass('active').hide();
                currentSectionIndex++;
                $(sections[currentSectionIndex]).addClass('active').show();
            }
        });

        $('.prev-button').click(function () {
            if (currentSectionIndex > 0) {
                $(sections[currentSectionIndex]).removeClass('active').hide();
                currentSectionIndex--;
                $(sections[currentSectionIndex]).addClass('active').show();
            }
        });
    });

    function addAchievement() {
        const newAchievementHtml = `
            <div class="achievement-row">
                <div class="input-field">
                    <input type="text" name="title[]" required maxlength="50">
                    <label>Achievement Title</label>
                </div>
                <div class="input-field">
                    <textarea name="description[]" class="materialize-textarea"></textarea>
                    <label>Achievement Description</label>
                </div>
                <div class="input-field">
                    <select name="achievementType[]">
                        <option value="" disabled selected>Select Achievement Type</option>
                        <c:forEach items="${achievementTypes}" var="achievementType">
                            <option value="${achievementType.id}">${achievementType.name}</option>
                        </c:forEach>
                    </select>
                    <label>Achievement Type</label>
                </div>
                <button type="button" class="btn btn-custom" onclick="removeAchievementRow(this)">Remove</button>
                <hr>
            </div>
        `;

        // Append the new achievement row before the 'Add Another Achievement' button
        $('#achievements-container').children('button').before(newAchievementHtml);

        // Reinitialize Materialize form select for dynamically added select inputs
        $('select').formSelect();
    }

    function removeAchievementRow(button) {
        const row = $(button).closest('.achievement-row');
        const achievementId = row.attr('data-achievement-id'); // Check if this achievement has an existing ID

        // If achievement ID exists, mark for deletion
        if (achievementId) {
            if (!window.deletedAchievements) window.deletedAchievements = [];
            window.deletedAchievements.push(parseInt(achievementId));
            console.log("Achievement entered for deletion");
        } else {
            console.log("No achievement entered for deletion");
        }

        // Remove the achievement row
        row.remove();
    }

 // Function to add a new project dynamically
    function addProject() {
        const newProjectHtml = `
            <div class="project-row" data-project-id="">
                <div class="input-field">
                    <select name="projects[][projectId]" required>
                        <option value="" disabled selected>Select Project</option>
                        <c:forEach items="${projects}" var="availableProject">
                            <option value="${availableProject.id}">${availableProject.title}</option>
                        </c:forEach>
                    </select>
                    <label>Selected Project</label>
                </div>
                <div class="input-field">
                    <input type="number" name="projects[][order]" min="1" placeholder="Order" required>
                    <label>Project Order</label>
                </div>
                <button type="button" class="btn btn-custom" onclick="removeProjectRow(this)">Remove</button>
            </div>
        `;

        // Append the new project row before the 'Add Another Project' button
        $('#projects-container').children('button').before(newProjectHtml);

        // Reinitialize Materialize form select for dynamically added select inputs
        $('select').formSelect();
    }

    // Function to remove a project row and mark it for deletion
    function removeProjectRow(button) {
        const row = $(button).closest('.project-row');
        const projectId = row.data('project-id'); // Get existing ID if present

        // If project ID exists, mark for deletion
        if (projectId) {
            if (!window.deletedProjects) window.deletedProjects = [];
            window.deletedProjects.push(parseInt(projectId));
            console.log("Project entered for deletion");
        } else {
            console.log("No project entered for deletion");
        }

        // Remove the project row
        row.remove();
    }

    // Function to collect all project data
    function collectProjectsData() {
        const projects = [];
        $('#projects-container .project-row').each(function () {
            const projectId = $(this).data('project-id'); // Get existing ID if present
            const projectTitle = parseInt($(this).find('select[name="projects[][projectId]"]').val());
            const order = $(this).find('input[name="projects[][order]"]').val();

            console.log("Collected Project:", projectId, projectTitle, order); // Debugging

            // Include valid projects only
            if (projectTitle && !isNaN(order) && order.trim() !== "") {
                projects.push({
                    id: projectId && projectId !== "" ? parseInt(projectId) : null, // Include ID for existing projects or null for new
                    projectId: projectTitle,
                    order: parseInt(order),
                });
            } else {
                console.log("Invalid Project Detected", projectId, projectTitle, order); // Debugging invalid projects
            }
        });

        console.log("Collected Projects Data:", projects); // Final debug log

        return projects;
    }

    
    function addSkill() {
        $('#skills-container').append(`
            <div class="skill-row" data-skill-id="">
                <div class="input-field">
                    <select name="skillType[]">
                        <option value="" disabled selected>Select Skill Type</option>
                        <c:forEach items="${skillTypes}" var="skillType">
                            <option value="${skillType.id}">${skillType.name}</option>
                        </c:forEach>
                    </select>
                    <label>Skill Type</label>
                </div>
                <div class="input-field">
                    <input type="number" name="percentage[]" min="0" max="100" required>
                    <label>Skill Proficiency (%)</label>
                </div>
                <button type="button" class="btn btn-custom" onclick="removeSkillRow(this)">Remove</button>
            </div>
        `);

        // Initialize Materialize dropdown for new select fields
        $('select').formSelect();
    }

    function removeSkillRow(button) {
        const row = $(button).closest('.skill-row');
        const skillId = row.attr('data-skill-id'); // Check if this skill has an existing ID

        // If skill ID exists, mark for deletion
        if (skillId) {
            if (!window.deletedSkills) window.deletedSkills = [];
            window.deletedSkills.push(parseInt(skillId));
            console.log("Skill entered for deletion");
        } else {
            console.log("No skill entered for deletion");
        }

        // Remove the skill row
        row.remove();
    }


    function collectSkillsData() {
        const skills = [];
        $('#skills-container .skill-row').each(function () {
            const skillId = $(this).data('skill-id'); // Get existing ID if present
            const skillTypeId = parseInt($(this).find('select[name="skillType[]"]').val());
            const proficiency = parseInt($(this).find('input[name="percentage[]"]').val());

            console.log("Collected Skill:", skillId, skillTypeId, proficiency); // Debugging

            // Include valid skills only
            if (!isNaN(skillTypeId) && !isNaN(proficiency) && skillTypeId && proficiency) {
                skills.push({
                    id: skillId && skillId !== "" ? parseInt(skillId) : null, // Include ID for existing skills or null for new
                    skillTypeId: skillTypeId,
                    proficiency: proficiency,
                });
            } else {
                console.log("Invalid Skill Detected", skillId, skillTypeId, proficiency); // Debugging invalid skills
            }
        });

        console.log("Collected Skills Data:", skills); // Final debug log

        return skills;
    }
    
    function collectAchievementsData() {
        const achievements = [];
        $('#achievements-container .achievement-row').each(function () {
            const achievementId = $(this).data('achievement-id'); // Get existing ID if present
            const title = $(this).find('input[name="title[]"]').val();
            const description = $(this).find('textarea[name="description[]"]').val();
            const achievementTypeId = parseInt($(this).find('select[name="achievementType[]"]').val());

            console.log("Collected Achievement:", achievementId, title, description, achievementTypeId); // Debugging

            // Include valid achievements only
            if (title && title.trim() !== "" && !isNaN(achievementTypeId)) {
                achievements.push({
                    id: achievementId && achievementId !== "" ? parseInt(achievementId) : null, // Include ID for existing achievements or null for new
                    title: title.trim(),
                    description: description.trim(),
                    achievementTypeId: achievementTypeId,
                });
            } else {
                console.log("Invalid Achievement Detected", achievementId, title, description, achievementTypeId); // Debugging invalid achievements
            }
        });

        console.log("Collected Achievements Data:", achievements); // Final debug log

        return achievements;
    }
   
    function submitForm() {
        var portfolioData = {
            tagline: $('#tagline').val(),
            info: $('#info').val(),
            skills: collectSkillsData(),
            achievements: collectAchievementsData(),
            selectedProjects: collectProjectsData(), // Now collects selected projects
            status: $('#status').val()
        };

        console.log(portfolioData);  // Debugging: Check the collected data

        // Serialize the JSON data into the hidden input field
        $('#jsonData').val(JSON.stringify(portfolioData));

        // Submit the form
        document.getElementById('portfolioForm').submit();
    }


</script>
<script src="https://unpkg.com/ionicons@5.1.2/dist/ionicons.js"></script>
    <script src="script.js"></script>
</body>
</html>
<%
    }
%>