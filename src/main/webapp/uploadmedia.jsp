<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ page import="com.klef.jfsd.sdp.model.Student"%>
<%
    Student student = (Student) session.getAttribute("student");
    if (student == null) {
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
    <title>Upload Media - ProFolio</title>
    <link rel="stylesheet" href="uploadmedia.css">
    <link href="https://fonts.googleapis.com/css2?family=Inter:wght@400;500;600;700&display=swap" rel="stylesheet">
</head>
<body>
    <%@include file="studentnavbar.jsp" %>
    <div class="upload-media-container">
        <h1>Upload Project Media</h1>
        
        <c:if test="${not empty message}">
            <div class="success-message">
                <c:out value="${message}"/>
            </div>
        </c:if>

        <form action="saveuploadedmedia" method="post" enctype="multipart/form-data" onsubmit="return validateForm()" id="uploadForm">
            <input type="hidden" name="projectid" value="${project.id}"/>

            <div class="form-group">
                <label for="mediaType">Media Type</label>
                <select id="mediaType" name="mediaType" required>
                    <option value="">Select media type...</option>
                    <option value="Image">Image</option>
                    <option value="Video">Video</option>
                    <option value="Document">Document</option>
                </select>
            </div>

            <div class="form-group">
                <div class="file-upload-container" id="dropZone">
                    <input type="file" id="mediaFile" name="mediaFile" 
                           accept=".jpg,.jpeg,.png,.gif,.mp4,.avi,.mov,.pdf,.doc,.docx" 
                           required>
                    <label for="mediaFile" class="file-upload-label">
                        <div class="file-upload-icon">📁</div>
                        <div class="file-upload-text">
                            Drag and drop your file here or click to browse
                        </div>
                        <div class="selected-file" id="selectedFileName"></div>
                    </label>
                </div>
            </div>

            <div class="form-group">
                <label for="caption">Caption</label>
                <input type="text" id="caption" name="caption" 
                       placeholder="Enter a description for your media" required>
            </div>
            
            <button type="submit" class="upload-media-button" id="submitButton">
                Upload Media
            </button>
        </form>
    </div>

    <script>
        const mediaTypeSelect = document.getElementById('mediaType');
        const mediaFileInput = document.getElementById('mediaFile');
        const captionInput = document.getElementById('caption');
        const submitButton = document.getElementById('submitButton');
        const selectedFileName = document.getElementById('selectedFileName');
        const dropZone = document.getElementById('dropZone');

        // File type mappings
        const allowedTypes = {
            'Image': ['jpg', 'jpeg', 'png', 'gif'],
            'Video': ['mp4', 'avi', 'mov'],
            'Document': ['pdf', 'doc', 'docx']
        };

        // Handle file selection
        mediaFileInput.addEventListener('change', function(e) {
            const file = e.target.files[0];
            if (file) {
                selectedFileName.textContent = file.name;
                validateFileType(file);
            }
        });

        // Drag and drop handlers
        dropZone.addEventListener('dragover', (e) => {
            e.preventDefault();
            dropZone.style.borderColor = 'var(--primary-color)';
            dropZone.style.backgroundColor = 'rgba(79, 70, 229, 0.05)';
        });

        dropZone.addEventListener('dragleave', (e) => {
            e.preventDefault();
            dropZone.style.borderColor = '#e5e7eb';
            dropZone.style.backgroundColor = 'transparent';
        });

        dropZone.addEventListener('drop', (e) => {
            e.preventDefault();
            dropZone.style.borderColor = '#e5e7eb';
            dropZone.style.backgroundColor = 'transparent';
            
            const file = e.dataTransfer.files[0];
            if (file) {
                mediaFileInput.files = e.dataTransfer.files;
                selectedFileName.textContent = file.name;
                validateFileType(file);
            }
        });

        function validateFileType(file) {
            const mediaType = mediaTypeSelect.value;
            if (!mediaType) {
                alert('Please select a media type first');
                mediaFileInput.value = '';
                selectedFileName.textContent = '';
                return false;
            }

            const extension = file.name.split('.').pop().toLowerCase();
            if (!allowedTypes[mediaType].includes(extension)) {
                alert(`Invalid file type for ${mediaType}. Allowed types: ${allowedTypes[mediaType].join(', ')}`);
                mediaFileInput.value = '';
                selectedFileName.textContent = '';
                return false;
            }
            return true;
        }

        function validateForm() {
            if (!mediaTypeSelect.value) {
                alert('Please select a media type');
                return false;
            }

            if (!mediaFileInput.files[0]) {
                alert('Please select a file to upload');
                return false;
            }

            if (!captionInput.value.trim()) {
                alert('Please provide a caption');
                return false;
            }

            return validateFileType(mediaFileInput.files[0]);
        }

        // Update accepted file types based on media type selection
        mediaTypeSelect.addEventListener('change', function() {
            const mediaType = this.value;
            if (mediaType) {
                const acceptedTypes = allowedTypes[mediaType].map(ext => '.' + ext).join(',');
                mediaFileInput.setAttribute('accept', acceptedTypes);
            }
            mediaFileInput.value = '';
            selectedFileName.textContent = '';
        });
    </script>
</body>
</html>
<%
    }
%>