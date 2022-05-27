package sample;

import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

import java.util.Collections;

public class MainScene extends Scene {

    public static final String SCENE_TITLE = "Song List" ;


    //Declare Scene components
    //ComboBox components
    private ComboBox<String> scaleCB ;
    //button components
    private Button manageScales =  new Button("Manage Scales") ;
    private Button manageSongs =  new Button("Manage Songs") ;
    private ListView<Song> SongsLV = new ListView<>() ;
    private ObservableList<Song> SongsList ;
    private ComboBox<String> genreCB ;
    private Slider minBPM = new Slider(0,200,0) ;
    private Slider maxBPM = new Slider(0,200,200) ;
    private TextArea selectedSongDetails = new TextArea() ;
    private Button saveSong = new Button("Save Song") ;
    private Song selectedSong ;

    public MainScene() {

        super(new GridPane(), 850, 450);
        System.out.println("In the MainScene() method in the mainScene") ;

        //setup components
        //connect ComboBoxes to ObservableLists from methods in Controller
        refreshComboBoxes() ;
        SongsList = Controller.getSongsInstance().getAllSongs() ;
        SongsLV .setItems(SongsList);
        SongsLV.setVisible(true);
        genreCB.setOnMouseEntered(mouseEvent -> refreshGenreCB());
        scaleCB.setOnMouseEntered(mouseEvent -> refreshScaleCB());
        scaleCB.setOnAction(e -> filter());
        genreCB.setOnAction(e -> filter());

        minBPM.setShowTickMarks(true);
        minBPM.setShowTickLabels(true);
        minBPM.setMajorTickUnit(5.0f);
        minBPM.setBlockIncrement(1.0f);
        minBPM.setOnMouseDragged(e -> filter());

        maxBPM.setShowTickMarks(true);
        maxBPM.setShowTickLabels(true);
        maxBPM.setMajorTickUnit(5.0f);
        maxBPM.setBlockIncrement(1.0f);
        maxBPM.setOnMouseDragged(e -> filter());

        selectedSongDetails.setDisable(true);
        saveSong.setDisable(true);

        //Button Settings (functionality)
        manageScales.setOnAction(event -> GoToManageScales());
        manageSongs.setOnAction(event -> GoToManageSongs());
        saveSong.setOnAction(event -> saveSongDetails());
        SongsLV.getSelectionModel().selectedItemProperty().addListener((observableValue, song, sSong) -> SongSelected(sSong));





        GridPane rightHalf = new GridPane() ;
        rightHalf.setHgap(5.0);
        rightHalf.setVgap(15);
        rightHalf.setPadding(new Insets(5));

        rightHalf.add(selectedSongDetails, 5,1,10,23) ;
        rightHalf.add(saveSong, 5,24,5,1) ;
        //rightHalf.setGridLinesVisible(true);

        GridPane pane = new GridPane();
        pane.setHgap(1.0);
        pane.setVgap(5);
        pane.setPadding(new Insets(5));

        pane.add(new Label("Scale"), 5, 4, 5, 1) ;
        pane.add(scaleCB, 5,5, 15, 1) ;

        pane.add(manageScales, 15, 50) ;
        pane.add(manageSongs, 5, 50) ;

        pane.add(SongsLV, 5, 15, 40, 30) ;

        pane.add(genreCB, 10, 5, 15, 1) ;
        pane.add(new Label("Genre"), 10, 4, 5, 1) ;
        pane.add(minBPM, 5, 7, 20, 2) ;
        pane.add(maxBPM, 5, 9, 20, 2) ;
        pane.add(new Label("  Title - Genre - BPM - Root Note - Scale"), 5, 13, 40, 1) ;
     //   pane.add(selectedSongDetails, 50, 5) ;
        HBox hBox = new HBox();
        hBox.setSpacing(5.0);
        hBox.setPadding(new Insets(5,5,5,5));
        hBox.getChildren().addAll(pane, rightHalf);

        //this.setRoot(rightHalf) ;
        this.setRoot(hBox);
    }

    private void refreshScaleCB() {
        scaleCB.setItems(Controller.getScalesInstance().getDistinctScales());
    }

    private void refreshGenreCB() {
        genreCB.setItems(Controller.getSongsInstance().getDistinctGenres());
    }

    private void saveSongDetails()
    {
        System.out.println("I'm in the saveSongDetails() in the MainScene");
        if (selectedSong == null)
            return ;

        Controller.getSongsInstance().updateSongDetails(selectedSongDetails.getText(), selectedSong) ;
    }

    private void SongSelected(Song sSong)
    {
        System.out.println("I'm in the SongSelected() in the MainScene");
        selectedSongDetails.setDisable(false);
        saveSong.setDisable(false);
        selectedSong = sSong ;
        if (selectedSong == null)
        {
            selectedSongDetails.setText("") ;
            selectedSongDetails.setDisable(true);
            saveSong.setDisable(true);
            return ;
        }
        String temp = Controller.getSongsInstance().getDistinctSongDetails(sSong) ;
        selectedSongDetails.setText(temp);

    }

    private void refreshComboBoxes()
    {
        System.out.println("I'm in the refreshComboBoxes() in the MainScene");

        scaleCB = new ComboBox<>(Controller.getScalesInstance().getDistinctScales()) ;
        genreCB = new ComboBox<>(Controller.getSongsInstance().getDistinctGenres()) ;
    }

    private void filter()
    {
        System.out.println("I'm in the filter() in the MainScene");
        String root = null ;
        System.out.println("I'm in the filter() and this is the selected item 'root'-->" + root) ;
        String genre = genreCB.getValue() ;
        System.out.println("I'm in the filter() and this is the selected item 'genre'-->" + genre) ;
        double lMinBPM = minBPM.getValue() ;
        double lMaxBPM = maxBPM.getValue() ;
        String scale = scaleCB.getValue() ;
        System.out.println("I'm in the filter() and this is the selected item scale-->" + scale) ;
        SongsList = Controller.getSongsInstance().filter(root, genre, scale, lMinBPM, lMaxBPM);
        SongsLV.setItems(SongsList);
    }

    private void GoToManageScales()
    {
        System.out.println("I'm in the GoToManageScales() in the MainScene");
        ViewNavigator.loadScene(ManageScales.SCENE_TITLE, new ManageScales(this)) ;
    }
    private void GoToManageSongs()
    {
        System.out.println("I'm in the GoToManageSongs() in the MainScene");
        ViewNavigator.loadScene(ManageSongs.SCENE_TITLE, new ManageSongs(this)) ;
    }
}


