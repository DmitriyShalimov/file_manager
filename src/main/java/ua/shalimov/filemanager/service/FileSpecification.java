package ua.shalimov.filemanager.service;


public class FileSpecification {
    private String name;
    private String size;
    private String lastUpdate;
    private String extension;
    private String type;

    public FileSpecification(String fileName, String fileType, String fileExtension, String fileSize, String fileLastUpdate) {
        this.name = fileName;
        this.size = fileSize;
        this.lastUpdate = fileLastUpdate;
        this.extension = fileExtension;
        this.type = fileType;
    }

    public String getSize() {
        return size;
    }

    public String getLastUpdate() {
        return lastUpdate;
    }

    public String getExtension() {
        return extension;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }
}