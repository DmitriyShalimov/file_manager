package ua.shalimov.filemanager.service;

import javafx.collections.ObservableList;

public interface Service {
    void goBack();

    void goForward();

    ObservableList<FileSpecification> getFileList();

    void selectDirectory(FileSpecification fileSpecification);

    String showFileCountInformation(String clickedButton);

    String getPath();
}
