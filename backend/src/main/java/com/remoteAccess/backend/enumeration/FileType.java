package com.remoteAccess.backend.enumeration;

import org.springframework.http.MediaType;

import java.util.Arrays;

public enum FileType {
    // Enum constants representing file types with their extensions and corresponding media types
    JPG("jpg", MediaType.IMAGE_JPEG),
    JPEG("jpeg", MediaType.IMAGE_JPEG),
    TXT("txt", MediaType.TEXT_PLAIN),
    PNG("png", MediaType.IMAGE_PNG),
    PDF("pdf", MediaType.APPLICATION_PDF);

    // File extension
    private final String extension;

    // Media type associated with the file extension
    private final MediaType mediaType;

    // Enum constructor
    FileType(String extension, MediaType mediaType) {
        this.extension = extension;
        this.mediaType = mediaType;
    }

    // Getter for file extension
    public String getExtension() {
        return extension;
    }

    // Getter for media type
    public MediaType getMediaType() {
        return mediaType;
    }

    // Method to get MediaType based on the filename's extension
    public static MediaType fromFilename(String fileName) {
        // Finding the last index of '.' to get the extension
        int dotIndex = fileName.lastIndexOf('.');
        // Extracting file extension from filename
        String fileExtension = (dotIndex == -1) ? "" : fileName.substring(dotIndex + 1);
        // Finding matching enum constant for the file extension
        return Arrays.stream(values())
                .filter(e -> e.getExtension().equalsIgnoreCase(fileExtension))
                .findFirst()
                .map(FileType::getMediaType)
                .orElse(MediaType.APPLICATION_OCTET_STREAM); // Default to octet-stream if no matching media type found
    }
}
