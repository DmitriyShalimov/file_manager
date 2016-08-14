package ua.shalimov.filemanager.logic;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.File;
import java.util.Date;
import java.util.Formatter;

public class FileHandler {
    private String path = System.getProperty("user.dir");
    private ObservableList<FileSpecification> fileList = FXCollections.observableArrayList();

    public String getPath() {
        return path;
    }

    public void setPath(String newPath) {
        this.path = newPath;
    }

    public void fillOutFileList() {
        fileList.add(new FileSpecification("...", "", "", "", ""));
        File file = new File(path);
        for (File item : file.listFiles()) {
            if (!item.isHidden()) {
                String fileName = item.getName();
                Date date = new Date(item.lastModified());
                Formatter fmt = new Formatter();
                fmt.format("%tH:%tM:%tS %td %tB %tY", date, date, date, date, date, date);
                String fileLastUpdate = fmt.toString();
                String fileExtension = getFileExtension(item);
                String fileType;
                if (item.isFile()) {
                    fileType = "File";
                } else {
                    fileType = "Directory";
                }
                String fileSize;
                if (fileType.equals("Directory")) {
                    fileSize = "";
                } else {
                    fileSize = getFileSize(item);
                }
                fileList.add(new FileSpecification(fileName, fileType, fileExtension, fileSize, fileLastUpdate));
            }
        }
    }

    public ObservableList<FileSpecification> getFileList() {
        return fileList;
    }

    public void clearFileList() {
        fileList.clear();
    }

    private String getFileExtension(File file) {
        if (file.isFile()) {
            String name = file.getName();
            if (name.contains(".")) {
                name = name.substring(name.lastIndexOf('.') + 1, name.length());
                return name;
            }
        }
        return "-";
    }

    private String getFileSize(File file) {
        long size = file.length();
        String capacity;
        if (size >= 1024) {
            if (size >= 1024 * 1024) {
                if (size >= 1024 * 1024 * 1024) {
                    size = size / (1024 * 1024 * 1024);
                    return Long.toString(size) + " GB";
                }
                size = size / (1024*1024);
                return Long.toString(size) + " MB";
            }
            size = size / 1024;
            capacity = Long.toString(size) + " KB";
        } else {
            if (size == -1) {
                capacity = "";
            } else {
                capacity = Long.toString(size) + " B";
            }
        }
        return capacity;
    }
}