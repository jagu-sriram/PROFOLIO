package com.klef.jfsd.sdp.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

public class PortfolioDTO {

    private String tagline;
    private String info;
    private List<SkillDTO> skills;
    private List<AchievementDTO> achievements;
    private List<ProjectDTO> selectedProjects;
    private String status;

    // Getters and Setters

    public String getTagline() {
        return tagline;
    }

    public void setTagline(String tagline) {
        this.tagline = tagline;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public List<SkillDTO> getSkills() {
        return skills;
    }

    public void setSkills(List<SkillDTO> skills) {
        this.skills = skills;
    }

    public List<AchievementDTO> getAchievements() {
        return achievements;
    }

    public void setAchievements(List<AchievementDTO> achievements) {
        this.achievements = achievements;
    }

    public List<ProjectDTO> getSelectedProjects() {
        return selectedProjects;
    }

    public void setSelectedProjects(List<ProjectDTO> selectedProjects) {
        this.selectedProjects = selectedProjects;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    // SkillDTO
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class SkillDTO {
        private int skillTypeId;
        private int proficiency;

        // Getters and Setters
        public int getSkillTypeId() {
            return skillTypeId;
        }

        public void setSkillTypeId(int skillTypeId) {
            this.skillTypeId = skillTypeId;
        }

        public int getProficiency() {
            return proficiency;
        }

        public void setProficiency(int proficiency) {
            this.proficiency = proficiency;
        }
    }

    // AchievementDTO
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class AchievementDTO {
        private String title;
        private String description;
        private int achievementTypeId;

        // Getters and Setters
        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public int getAchievementTypeId() {
            return achievementTypeId;
        }

        public void setAchievementTypeId(int achievementTypeId) {
            this.achievementTypeId = achievementTypeId;
        }
    }

    // ProjectDTO
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class ProjectDTO {
        private int projectId;
        private int order;

        // Getters and Setters
        public int getProjectId() {
            return projectId;
        }

        public void setProjectId(int projectId) {
            this.projectId = projectId;
        }

        public int getOrder() {
            return order;
        }

        public void setOrder(int order) {
            this.order = order;
        }
    }
}
