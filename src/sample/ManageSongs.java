package sample;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color ;

import java.awt.*;

import static javafx.scene.paint.Color.RED;

public class ManageSongs extends Scene {
    private Scene mPreviousScene ;
    public static final String SCENE_TITLE = "Managing Songs" ;

    private Button backButton = new Button("Back") ; //
    private ListView<Song> SongsLV = new ListView<>() ;
    private Button newSongButton = new Button("New Song") ;

    //New Song Visible Componants
    private Button cancelNewSongButton = new Button("Cancel") ;
    private Button createNewSongButton = new Button("Create") ;
    private Label titleLabel = new Label("Title:") ;
    private TextField createSongTitleTF = new TextField() ;
    private Label genreLabel = new Label("Genre:") ;
    private TextField createSongGenreTF = new TextField() ;
    private Label createSongTitleEmptyLabel = new Label("Field Empty") ;
    private Label createSongGenreEmptyLabel = new Label("Field Empty") ;
    private Label SongCreatedSuccessLabel = new Label("New Song Successfully Added") ;
    private ComboBox<Scale> scalesCB ;
    private Label scaleLabel = new Label("Scale:") ;
    private Label rootLabel = new Label("Root:") ;
    private TextField createSongRootTF = new TextField() ;
    private Label createSongRootEmptyLabel = new Label("Field Empty") ;
    private Slider createSongBPMSlider = new Slider(0, 200, 90) ;
    private Label createSongBPMLabel = new Label("BPM:") ;

    private Button deleteSelectedSongButton = new Button("Delete") ;

    private ObservableList<Song> SongsList ;

    private Song selectedSong ;

    public ManageSongs(Scene previousScene)
    {
        super(new GridPane(), 850, 400) ;
        mPreviousScene = previousScene ;

        createSongBPMSlider.setShowTickMarks(true);
        createSongBPMSlider.setShowTickLabels(true);
        createSongBPMSlider.setMajorTickUnit(5.0f);
        createSongBPMSlider.setBlockIncrement(1.0f);
        createSongBPMSlider.setVisible(false);

        backButton.setOnAction(event -> goBackToMainScene());
        SongsList = Controller.getSongsInstance().getAllSongs();
        SongsLV.setItems(SongsList);
        SongsLV.setPrefWidth(850);
        SongsLV.getSelectionModel().selectedItemProperty().addListener((observableValue, song, sSong) -> SongSelected(sSong));
        newSongButton.setOnAction(event -> createNewSongButton());
        cancelNewSongButton.setOnAction(event -> cancelNewSongButton()) ;
        cancelNewSongButton.setVisible(false);
        titleLabel.setVisible(false);
        genreLabel.setVisible(false);
        createSongGenreTF.setVisible(false);
        createSongGenreTF.setOnKeyTyped(e -> createSongGenreEmptyLabel.setVisible(false));
        createSongTitleTF.setVisible(false) ;
        createSongTitleTF.setOnKeyTyped(e -> createSongTitleEmptyLabel.setVisible(false));
        createSongRootTF.setOnKeyTyped(e -> createSongRootEmptyLabel.setVisible(false));
        createNewSongButton.setVisible(false);
        createNewSongButton.setOnAction(event -> newSongAction());
        createSongTitleEmptyLabel.setTextFill(RED);
        createSongTitleEmptyLabel.setVisible(false);
        createSongGenreEmptyLabel.setTextFill(RED);
        createSongGenreEmptyLabel.setVisible(false);
        SongCreatedSuccessLabel.setVisible(false);
        deleteSelectedSongButton.setDisable(true);
        deleteSelectedSongButton.setOnAction(event -> deleteSelectedSong());
        scalesCB = new ComboBox<>(Controller.getScalesInstance().getAllScales()) ;
        scalesCB.setVisible(false);
        scaleLabel.setVisible(false);
        createSongRootTF.setVisible(false);
        rootLabel.setVisible(false);
        createSongRootEmptyLabel.setTextFill(RED);
        createSongRootEmptyLabel.setVisible(false);
        createSongBPMLabel.setVisible(false);

        GridPane pane = new GridPane() ;
        pane.setHgap(10.0);
        pane.setVgap(5);
        pane.setPadding(new Insets(5));

        //pane.add(new Label("go to prev"), 48, 48) ;
        pane.add(backButton, 15, 37, 2, 1);
        pane.add(SongsLV, 0, 2, 20, 30) ;
        pane.add(cancelNewSongButton, 45, 12, 10, 1) ;
        pane.add(newSongButton, 0, 37, 2, 1) ;

        pane.add(titleLabel, 30, 5, 10, 1) ;
        pane.add(createSongTitleTF, 35, 5, 10, 2) ;
        pane.add(createSongTitleEmptyLabel, 45, 5, 10,1) ;

        pane.add(genreLabel, 30, 7, 10,1) ;
        pane.add(createSongGenreTF, 35, 7, 10, 1) ;
        pane.add(createSongGenreEmptyLabel, 45, 7, 10,1) ;

        pane.add(createNewSongButton, 35, 12, 10, 1) ;
        pane.add(SongCreatedSuccessLabel, 30, 0, 15, 1) ;

        pane.add(deleteSelectedSongButton, 6, 37, 8, 1) ;

        pane.add(scalesCB, 35, 8, 15, 1) ;
        pane.add(scaleLabel, 30, 8, 15, 1) ;

        pane.add(rootLabel, 30, 9, 15, 1) ;
        pane.add(createSongRootTF, 35, 9, 10,1) ;
        pane.add(createSongRootEmptyLabel, 45, 9, 15, 1) ;

        pane.add(createSongBPMSlider, 35, 10, 15, 1) ;
        pane.add(createSongBPMLabel, 30, 10, 15, 1) ;


        this.setRoot(pane) ; //essential for the components to show up!!!

    }

    private void deleteSelectedSong()
    {
        Controller.getSongsInstance().getAllSongs().remove(selectedSong) ;
    }

    private void SongSelected(Song sSong)
    {
        selectedSong = sSong ;
        deleteSelectedSongButton.setDisable(false);
    }

    private void newSongAction()
    {
        if (createSongTitleTF.getText().isEmpty()) {
            createSongTitleEmptyLabel.setVisible(true);
        }
        if (createSongGenreTF.getText().isEmpty()) {
            createSongGenreEmptyLabel.setVisible(true);
        }
        if (createSongRootTF.getText().isEmpty()){
            createSongRootEmptyLabel.setVisible(true) ;
        }
            System.out.println("I'm in the newSongAction() method in the ManageSongs") ;
        if (createSongTitleEmptyLabel.isVisible() || createSongTitleEmptyLabel.isVisible() || createSongRootEmptyLabel.isVisible()) {
            return ;
        }

        Controller.getSongsInstance().getAllSongs().add(new Song(createSongTitleTF.getText().toString(), createSongRootTF.getText().toString(), (int) createSongBPMSlider.getValue(), createSongGenreTF.getText().toString(), scalesCB.getSelectionModel().getSelectedItem(), "Write chords and lyrics here.")) ;
        SongCreatedSuccessLabel.setVisible(true);
        createSongTitleTF.clear();
        createSongGenreTF.clear();

        cancelNewSongButton();
    }

    private void createNewSongButton()
    {
        //set visable textboxes to record information for a new Song
        //set visable button for checking userinput/createing new Song with info
        //set invisible regular scene layout componants

        SongCreatedSuccessLabel.setVisible(false);
        backButton.setVisible(false);
        //SongsLV.setVisible(false);
        newSongButton.setVisible(false);
        cancelNewSongButton.setVisible(true);
        createSongTitleTF.setVisible(true);
        titleLabel.setVisible(true);
        genreLabel.setVisible(true);
        createSongGenreTF.setVisible(true);
        createNewSongButton.setVisible(true);
        deleteSelectedSongButton.setVisible(false);
        scalesCB.setVisible(true);
        scaleLabel.setVisible(true);
        createSongRootTF.setVisible(true);
        rootLabel.setVisible(true);
        createSongBPMSlider.setVisible(true);
        createSongBPMLabel.setVisible(true);
    }

    private void cancelNewSongButton()
    {
        //cancel the actions in createNewSongButton()
        backButton.setVisible(true);
        //SongsLV.setVisible(true);
        newSongButton.setVisible(true);
        cancelNewSongButton.setVisible(false);
        createSongTitleTF.setVisible(false);
        titleLabel.setVisible(false);
        genreLabel.setVisible(false);
        createSongGenreTF.setVisible(false);
        createNewSongButton.setVisible(false);
        deleteSelectedSongButton.setVisible(true);
        scalesCB.setVisible(false);
        scaleLabel.setVisible(false);
        createSongTitleEmptyLabel.setVisible(false);
        createSongGenreEmptyLabel.setVisible(false);
        createSongRootTF.setVisible(false);
        rootLabel.setVisible(false);
        createSongRootEmptyLabel.setVisible(false);
        createSongBPMSlider.setVisible(false);
        createSongBPMLabel.setVisible(false);
    }

    private void goBackToMainScene() { ViewNavigator.loadScene(MainScene.SCENE_TITLE, mPreviousScene);
    }
}
