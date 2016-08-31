package ua.shalimov.filemanager.gui;
import com.sun.javafx.scene.control.skin.TableColumnHeader;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import ua.shalimov.filemanager.service.FileManagerService;
import ua.shalimov.filemanager.service.FileSpecification;
import ua.shalimov.filemanager.service.Service;

public class FileManagerGui {
    private Service service = new FileManagerService();
    @FXML
    private Label labelPath;
    @FXML
    private TableView<FileSpecification> table;
    @FXML
    private Label labelCounter;
    @FXML
    private TableColumn<FileSpecification, String> columnName;
    @FXML
    private TableColumn<FileSpecification, String> columnSize;
    @FXML
    private TableColumn<FileSpecification, String> columnLastUpdate;
    @FXML
    private TableColumn<FileSpecification, String> columnExtension;
    @FXML
    private TableColumn<FileSpecification, String> columnType;

    @FXML
    private void initialize() {
        columnName.setCellValueFactory(new PropertyValueFactory<FileSpecification, String>("name"));
        columnType.setCellValueFactory(new PropertyValueFactory<FileSpecification, String>("type"));
        columnExtension.setCellValueFactory(new PropertyValueFactory<FileSpecification, String>("extension"));
        columnSize.setCellValueFactory(new PropertyValueFactory<FileSpecification, String>("size"));
        columnLastUpdate.setCellValueFactory(new PropertyValueFactory<FileSpecification, String>("lastUpdate"));
        table.setItems(service.getFileList());
        labelPath.setText(service.getPath());
        initListeners();
    }

    public void goBack() {
        service.goBack();
        initialize();
    }

    public void goForward() {
        service.goForward();
        initialize();
    }

    public void showInformation(ActionEvent actionEvent) {
        Object source = actionEvent.getSource();
        if (!(source instanceof Button)) {
            return;
        }
        Button clickedButton = (Button) source;
        labelCounter.setText(service.showFileCountInformation(clickedButton.getId()));
    }

    private void initListeners() {
        table.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (event.getClickCount() == 2) {
                    if (event.getTarget() instanceof TableColumnHeader) {
                        return;
                    }
                    service.selectDirectory(table.getSelectionModel().getSelectedItem());
                    initialize();
                }
            }
        });
    }
}
