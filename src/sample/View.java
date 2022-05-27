package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class View extends Application {

    @Override   //Starts the Application
    public void start(Stage primaryStage) throws Exception{
        //Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        //TODO - create an image and load it here
        /*                                                                    //folderInPackage\\file
        primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("images\\burrito.png")));
         */
        ViewNavigator.setStage(primaryStage) ;
        ViewNavigator.loadScene(MainScene.SCENE_TITLE, new MainScene());

        /*
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();

         */
    }

    @Override   //called when user closes window
    public void stop() throws Exception
    {
        //Tell the controller to save the data in the ObservableLists into the Binary Files
        Controller.getSongsInstance().saveSongData();
        Controller.getScalesInstance().saveScaleData();
    }


    public static void main(String[] args) {
        Application.launch(args);
    }
}
