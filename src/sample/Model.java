package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.* ;
import java.util.Scanner ;

import java.io.*;

public class Model {

    public static final String SONGS_BINARY_FILE = "SavedSongs.dat" ;
    public static final String SCALES_BINARY_FILE = "SavedScaled.dat" ;


    //Method to check File
    public static boolean binaryFileHasData(String binaryFileArgu)
    {
        // Done: Create a File reference to the binary file
        File binaryFile = new File(binaryFileArgu) ;

        // DONE: Return whether the binary file exists and has data
        return binaryFile.exists() && binaryFile.length() > 0 ;
    }
    //Method takes data from binaryFile and loads it into an ObservableList(Songs)
    public static ObservableList<Song> populateSongListFromBinaryFile()
    {
        ObservableList<Song> allSongs = FXCollections.observableArrayList() ;
        File binaryFile = new File(SONGS_BINARY_FILE) ;
        if (binaryFileHasData(SONGS_BINARY_FILE))
        {
            try {
                //open a stream to the BinaryFile Data Objects
                ObjectInputStream fileReader = new ObjectInputStream(new FileInputStream(binaryFile)) ;
                //downCast Objects to Song[] information and apply it to the temp array
                Song[] tempArray = (Song[]) fileReader.readObject() ;
                //System.out.println("Im here") ;
                //add all the entries in the temp array to the ObservableList
                allSongs.addAll(tempArray) ;
                fileReader.close() ;    //close the resource
            } catch (IOException | ClassNotFoundException e) {
                System.err.println("The Song data did not load: " + e.getMessage()) ;
            }
        }
        return allSongs ;
    }
    //Method takes data from binaryFile and loads it into an ObservableList(Scales)
    public static ObservableList<Scale> populateScaleListFromBinaryFile()
    {
        ObservableList<Scale> allScales = FXCollections.observableArrayList() ;
        File binaryFile = new File(SCALES_BINARY_FILE) ;
        if (binaryFileHasData(SCALES_BINARY_FILE))
        {
            try {
                //open a stream to the BinaryFile Data Objects
                ObjectInputStream fileReader = new ObjectInputStream(new FileInputStream(binaryFile)) ;
                //downCast Objects to Song[] information and apply it to the temp array
                Scale[] tempScalesArray = (Scale[]) fileReader.readObject() ;
                //System.out.println("Im here") ;
                //add all the entries in the temp array to the ObservableList
                allScales.addAll(tempScalesArray) ;
                fileReader.close() ;    //close the resource
            } catch (IOException | ClassNotFoundException e) {
                System.err.println("The Scale data did not load: " + e.getMessage()) ;
            }
        }
        return allScales ;
    }
    //Method takes the data in the ObservableList of Songs and writes the data to a BinaryFile
    public static boolean writeSongDataToBinaryFile(ObservableList<Song> allSongsList)
    {
        File binaryFile = new File(SONGS_BINARY_FILE) ; // File Object
        try {
            //create file connection
            ObjectOutputStream fileWriter = new ObjectOutputStream(new FileOutputStream(binaryFile)) ;
            //create a temp Song[] array the size of the ObservableList
            Song[] tempArray = new Song[allSongsList.size()] ;
            //copy all the data from the ObservableListParameter to the temp array
            for (int i = 0 ; i < tempArray.length ; i++)
            {
                tempArray[i] = allSongsList.get(i) ;
            }
            fileWriter.writeObject(tempArray);  //write all the array information to the BinaryFile
            fileWriter.close(); //close the resource
            return true ;   //success
        } catch (IOException e) {
            System.err.println("Error writing: " + e.getMessage()) ;
            return false ;  //failure
        }
    }
    //Method takes the data in the ObservableList of Scales and writes the data to a BinaryFile
    public static boolean writeScaleDataToBinaryFile(ObservableList<Scale> allScalesList)
    {
        File binaryFile = new File(SCALES_BINARY_FILE) ; // File Object
        try {
            System.out.println("Writing file step 1") ;
            //create file connection
            ObjectOutputStream fileWriter = new ObjectOutputStream(new FileOutputStream(binaryFile)) ;
            System.out.println("Writing file step 2") ;
            //create a temp Scale[] array the size of the ObservableList
            Scale[] tempArray = new Scale[allScalesList.size()] ;
            System.out.println("Writing file step 3") ;
            //copy all the data from the ObservableListParameter to the temp array
            for (int i = 0 ; i < tempArray.length ; i++)
            {
                tempArray[i] = allScalesList.get(i) ;
                System.out.println(tempArray[i] + " being written to tempArray") ;
            }
            System.out.println("Writing file step 4") ;
            fileWriter.writeObject(tempArray);  //write all the array information to the BinaryFile
            System.out.println("Writing file step 5") ;
            fileWriter.close(); //close the resource
            return true ;   //success
        } catch (IOException e) {
            System.err.println("Error writing: " + e.getMessage()) ;
            return false ;  //failure
        }
    }


    public static boolean songBinaryFileHasData()
    {
        // Done: Create a File reference to the binary file
        File binaryFile = new File(SONGS_BINARY_FILE) ;

        // DONE: Return whether the binary file exists and has data
        return binaryFile.exists() && binaryFile.length() > 0 ;
    }
    public static boolean scaleBinaryFileHasData()
    {
        // Done: Create a File reference to the binary file
        File binaryFile = new File(SCALES_BINARY_FILE) ;

        // DONE: Return whether the binary file exists and has data
        return binaryFile.exists() && binaryFile.length() > 0 ;
    }

    public static String getSelectedSongDetails(Song selectedSong)
    {
        System.out.println("Model - getSelectedSongDetails() mSongNotes of 'selectedSong' -> " + selectedSong.getmSongNotes()) ;
        return selectedSong.getmSongNotes() ;
    }

    public static void songEntryUpdate(String text, Song selectedSong)
    {
        selectedSong.setmSongNotes(text);
    }
}
