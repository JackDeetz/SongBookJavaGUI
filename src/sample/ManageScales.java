package sample;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.layout.GridPane;
import javafx.scene.control.Button ;
import javafx.scene.control.Label ;
import javafx.scene.control.TextField ;
import javafx.scene.paint.Color ;

import java.awt.*;

import static javafx.scene.paint.Color.RED;

public class ManageScales extends Scene {
    private Scene mPreviousScene ;
    private Scale selectedScale ;
    public static final String SCENE_TITLE = "Managing Scales" ;

    private Button backButton = new Button("Back") ; //
    private ListView<Scale> ScalesLV = new ListView<>() ;
    private Button newScaleButton = new Button("New Scale") ;
    
    //New Scale Visible Componants
    private Button deleteScaleButton = new Button("Delete") ;
    private Button cancelNewScaleButton = new Button("Cancel") ;
    private Button createNewScaleButton = new Button("Create") ;
    private Label nameLabel = new Label("Name:") ;
    private TextField createScaleNameTF = new TextField() ;
    private Label scaleLabel = new Label("Scale:") ;
    private TextField createScaleTF = new TextField() ;
    private Label createScaleNameEmptyLabel = new Label("Field Empty") ;
    private Label createScaleEmptyLabel = new Label("Field Empty") ;
    private Label scaleCreatedSuccessLabel = new Label("New Scale Successfully Added") ;

    private ObservableList<Scale> ScalesList ;

    public ManageScales(Scene previousScene)
    {
        super(new GridPane(), 850, 400) ;
        mPreviousScene = previousScene ;

        ScalesLV.getSelectionModel().selectedItemProperty().addListener((observableValue, scale, sScale) -> ScaleSelected(sScale));

        deleteScaleButton.setDisable(true);
        deleteScaleButton.setOnAction(e -> deleteSelectedScale()) ;
        backButton.setOnAction(event -> goBackToMainScene());
        ScalesList = Controller.getScalesInstance().getAllScales();
        ScalesLV.setItems(ScalesList);
        ScalesLV.setPrefWidth(850);
        newScaleButton.setOnAction(event -> createNewScaleButton());
        cancelNewScaleButton.setOnAction(event -> cancelNewScaleButton()) ;
        cancelNewScaleButton.setVisible(false);
        nameLabel.setVisible(false);
        scaleLabel.setVisible(false);
        createScaleTF.setVisible(false);
        createScaleTF.setOnKeyTyped(e -> createScaleEmptyLabel.setVisible(false));
        createScaleNameTF.setVisible(false) ;
        createScaleNameTF.setOnKeyTyped(e -> createScaleNameEmptyLabel.setVisible(false));
        createNewScaleButton.setVisible(false);
        createNewScaleButton.setOnAction(event -> newScaleAction());
        createScaleNameEmptyLabel.setTextFill(RED);
        createScaleNameEmptyLabel.setVisible(false);
        createScaleEmptyLabel.setTextFill(RED);
        createScaleEmptyLabel.setVisible(false);
        scaleCreatedSuccessLabel.setVisible(false);

        GridPane pane = new GridPane() ;
        pane.setHgap(10.0);
        pane.setVgap(5);
        pane.setPadding(new Insets(5));

        //pane.add(new Label("go to prev"), 48, 48) ;
        pane.add(backButton, 8, 28, 2, 2);
        pane.add(ScalesLV, 0, 5, 20, 20) ;
        pane.add(cancelNewScaleButton, 38, 10, 5, 1) ;
        pane.add(newScaleButton, 0, 28, 2, 1) ;

        pane.add(nameLabel, 30, 5, 10, 1) ;
        pane.add(createScaleNameTF, 35, 5, 10, 2) ;
        pane.add(createScaleNameEmptyLabel, 45, 5, 10,1) ;

        pane.add(scaleLabel, 30, 7, 10,1) ;
        pane.add(createScaleTF, 35, 7, 10, 1) ;
        pane.add(createScaleEmptyLabel, 45, 7, 10,1) ;

        pane.add(createNewScaleButton, 30, 10, 10, 1) ;
        pane.add(scaleCreatedSuccessLabel, 30, 8, 15, 1) ;
        pane.add(deleteScaleButton, 5, 28, 2, 1) ;


        this.setRoot(pane) ; //essential for the components to show up!!!

    }

    private void deleteSelectedScale()
    {
       Controller.getScalesInstance().getAllScales().remove(selectedScale) ;
    }

    private void ScaleSelected(Scale sScale)
    {
        selectedScale = sScale ;
        deleteScaleButton.setDisable(false);
    }

    private void newScaleAction()
    {
        if (createScaleNameTF.getText().isEmpty()) {
            createScaleNameEmptyLabel.setVisible(true);
        }
        if (createScaleTF.getText().isEmpty()) {
            createScaleEmptyLabel.setVisible(true);
        }
        if (createScaleNameEmptyLabel.isVisible() || createScaleNameEmptyLabel.isVisible()) {
            return ;
        }
        System.out.println("I'm in the newScaleAction() method in the ManageScales") ;

        Controller.getScalesInstance().getAllScales().add(new Scale(createScaleNameTF.getText(), createScaleTF.getText().split("\\s+"))) ;
        scaleCreatedSuccessLabel.setVisible(true);
        createScaleNameTF.clear();
        createScaleTF.clear();
        cancelNewScaleButton();
    }

    private void createNewScaleButton()
    {
        //set visable textboxes to record information for a new Scale
        //set visable button for checking userinput/createing new Scale with info
        //set invisible regular scene layout componants

        backButton.setVisible(false);
        //ScalesLV.setVisible(false);
        newScaleButton.setVisible(false);
        cancelNewScaleButton.setVisible(true);
        createScaleNameTF.setVisible(true);
        nameLabel.setVisible(true);
        scaleLabel.setVisible(true);
        createScaleTF.setVisible(true);
        createNewScaleButton.setVisible(true);
        scaleCreatedSuccessLabel.setVisible(false);
    }

    private void cancelNewScaleButton()
    {
        //cancel the actions in createNewScaleButton()
        backButton.setVisible(true);
        //ScalesLV.setVisible(true);
        newScaleButton.setVisible(true);
        cancelNewScaleButton.setVisible(false);
        createScaleNameTF.setVisible(false);
        nameLabel.setVisible(false);
        scaleLabel.setVisible(false);
        createScaleTF.setVisible(false);
        createNewScaleButton.setVisible(false);
    }

    private void goBackToMainScene() {
        ViewNavigator.loadScene(MainScene.SCENE_TITLE, mPreviousScene);
    }
}
