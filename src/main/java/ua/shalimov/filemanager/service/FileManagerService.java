package ua.shalimov.filemanager.service;

import javafx.collections.ObservableList;

import java.io.File;
import java.util.ArrayDeque;

public class FileManagerService implements Service {
    private static int fileCount;
    private static int directoryCount;
    private ArrayDeque<String> listPathGoForward = new ArrayDeque<>();
    private ArrayDeque<String> listPathGoBack = new ArrayDeque<>();
    private FileHandler fileList = new FileHandler();

    @Override
    public void goBack() {
        if (!listPathGoBack.isEmpty()) {
            String path = listPathGoBack.pop();
            listPathGoForward.push(fileList.getPath());
            fileList.setPath(path);
        }
        fileList.clearFileList();
    }

    @Override
    public void goForward() {
        if (!listPathGoForward.isEmpty()) {
            String path = listPathGoForward.pop();
            listPathGoBack.push(fileList.getPath());
            fileList.setPath(path);
        }
        fileList.clearFileList();
    }

    @Override
    public ObservableList<FileSpecification> getFileList() {
        fileList.fillOutFileList();
        return fileList.getFileList();
    }

    @Override
    public void selectDirectory(FileSpecification fileSpecification) {
        if (fileSpecification.getName().equals("...")) {
            File file = new File(fileList.getPath());
            listPathGoBack.push(fileList.getPath());
            listPathGoForward.clear();
            String path = file.getParent();
            if (path != null) {
                fileList.setPath(path);
            }
            fileList.clearFileList();
        } else {
            String oldPath = fileList.getPath();
            listPathGoBack.push(fileList.getPath());
            listPathGoForward.clear();
            fileList.clearFileList();
            if (fileSpecification.getType().equals("Directory")) {
                String newPath = oldPath + "\\" + fileSpecification.getName() + "\\";
                fileList.setPath(newPath);
            }
        }
    }

    @Override
    public String showFileCountInformation(String clickedButton) {
        fileCount = 0;
        directoryCount = 0;
        String path = fileList.getPath();
        countFiles(new File(path));
        switch (clickedButton) {
            case "buttonFileCount": {
                return "In " + path + ": " + fileCount + " files";
            }
            case "buttonFolderCount": {
                return "In " + path + ": " + directoryCount + " sub folders";
            }
            case "buttonFileAndFolderCount": {
                return "In " + path + ": " + fileCount + " files and " + directoryCount + " sub folders";
            }
        }
        return "";
    }

    @Override
    public String getPath() {
        return fileList.getPath();
    }

    private void countFiles(File directory) {
        for (File file : directory.listFiles()) {
            if (!file.isHidden()) {
                if (file.isFile()) {
                    fileCount++;
                } else {
                    directoryCount++;
                    countFiles(file);
                }
            }
        }
    }
}
