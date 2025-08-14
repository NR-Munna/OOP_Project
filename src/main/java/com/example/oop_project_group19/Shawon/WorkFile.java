package com.example.oop_project_group19.Shawon;

import java.time.LocalDateTime;

public class WorkFile {
    private String fileName;
    private String fileType;
    private double fileSize;
    private LocalDateTime uploadDate;

    public WorkFile() {}

    public WorkFile(String fileName, String fileType, double fileSize) {
        this.fileName = fileName;
        this.fileType = fileType;
        this.fileSize = fileSize;
        this.uploadDate = LocalDateTime.now();
    }

    public String getFileName() { return fileName; }
    public void setFileName(String fileName) { this.fileName = fileName; }

    public String getFileType() { return fileType; }
    public void setFileType(String fileType) { this.fileType = fileType; }

    public double getFileSize() { return fileSize; }
    public void setFileSize(double fileSize) { this.fileSize = fileSize; }

    public LocalDateTime getUploadDate() { return uploadDate; }
    public void setUploadDate(LocalDateTime uploadDate) { this.uploadDate = uploadDate; }
}
