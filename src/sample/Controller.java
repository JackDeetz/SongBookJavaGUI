package sample;
import com.sun.webkit.Timer;
import javafx.collections.FXCollections ;
import javafx.collections.ObservableList;
import sample.Model ;
import sample.Song ;
import sample.Scale ;


public class Controller {

    private static Controller SongsInstance ;
    private static Controller ScalesInstance ;
    private ObservableList<Song> mAllSongsList ;
    private ObservableList<Song> mFilteredSongsList ;
    private ObservableList<Scale> mAllScalesList ;

    private Controller() {} ;

    public static Controller getSongsInstance()
    {
        System.out.println("Controller - getSongsInstance() ") ;
        if (SongsInstance == null)
        {
            SongsInstance = new Controller() ;

            if (Model.songBinaryFileHasData())
                SongsInstance.mAllSongsList = Model.populateSongListFromBinaryFile() ;
            SongsInstance.mFilteredSongsList = FXCollections.observableArrayList() ;
        }


        return SongsInstance ;
    }

    public static Controller getScalesInstance()
    {
        System.out.println("Controller - getScalesInstance() ") ;
        if (ScalesInstance == null)
        {
            ScalesInstance = new Controller() ;
            
            if (Model.scaleBinaryFileHasData())
                ScalesInstance.mAllScalesList = Model.populateScaleListFromBinaryFile() ;
        }

        return ScalesInstance ;
    }

    //Getter
    public ObservableList<Song> getAllSongs()
    {
        return mAllSongsList ;
    }

    public ObservableList<String> getDistinctSongs()
    {
        System.out.println("Controller - getDistinctSongs() ") ;
        ObservableList<String> songsList = FXCollections.observableArrayList() ;
        songsList.add(" ") ;
        for (Song s : mAllSongsList)
        {
            if (!songsList.contains(s.getmTitle()))
            {
                songsList.add(s.getmTitle()) ;
            }
        }
        FXCollections.sort(songsList);
        return songsList ;
    }

    public ObservableList<String> getDistinctScales()
    {
        System.out.println("Controller - getDistinctScales() ") ;
        ObservableList<String> scalesList = FXCollections.observableArrayList() ;
        scalesList.add(" ") ;
        for (Scale s : mAllScalesList)
        {
            if (!scalesList.contains(s.getmName()))
            {
                scalesList.add(s.getmName()) ;
            }
        }
        //FXCollections.sort(scalesList);
        return scalesList ;
    }

    public ObservableList<String> getDistinctGenres()
    {
        System.out.println("Controller - getDistinctGenres() ") ;
        ObservableList<String> genresList = FXCollections.observableArrayList() ;
        genresList.add(" ") ;
        for (Song s : mAllSongsList)
        {
            if (!genresList.contains(s.getmGenre()))
            {
                System.out.println("Controller - getDistinctGenres() - adding "+ s.getmGenre() ) ;
                genresList.add(s.getmGenre()) ;
            }
        }
        return genresList ;
    }

    public ObservableList<Song> filter(String rootNote, String genre, String scale, double minBPM, double maxBPM)
    {
        System.out.println("Controller - filter( rootNote=" + rootNote + " genre=" + genre + " scale=" + scale + " min/maxBPM=" + minBPM + "/" + maxBPM ) ;
        mFilteredSongsList.clear();
        boolean meetsCriteria;

        for (Song b : mAllSongsList)
        {
            meetsCriteria = true;
            if (rootNote != null && !rootNote.equalsIgnoreCase(" ") && !rootNote.equalsIgnoreCase(b.getmRoot()))
                meetsCriteria = false;
            if (genre != null && !genre.equalsIgnoreCase(" ") && !genre.equalsIgnoreCase(b.getmGenre()))
                meetsCriteria = false;
            if (b.getmTempo() < minBPM || b.getmTempo() > maxBPM)
                meetsCriteria = false;
            if (scale != null)
            {
                if (!scale.equalsIgnoreCase(" "))
                {
                    System.out.println("Controller - Filter() - scale - step 2 = " + scale) ;
                    if (b.getmScale() != null)
                    {

                        System.out.println("Controller - Filter() - scale - step 3 = " + scale) ;
                        //TODO fix Scale Filter
                        if (!scale.equalsIgnoreCase(b.getmScale().getmName()))
                        {
                            System.out.println("Controller - Filter() - scale - step 4 = " + scale) ;
                            meetsCriteria = false;
                        }
                    }
                }
            }
            System.out.println("passed if statements meets criteria =" + meetsCriteria);

            if (meetsCriteria) {
                mFilteredSongsList.add(b);
                System.out.println("met criteria=" + b);
            }
        }
        return mFilteredSongsList;
    }







    public void saveSongData()
    {
        System.out.println("Controller - saveSongData() ") ;
       Model.writeSongDataToBinaryFile(mAllSongsList) ;
    }

    public void saveScaleData()
    {
        System.out.println("Controller - saveScaleData() ") ;
        Model.writeScaleDataToBinaryFile(mAllScalesList) ;
    }



    public ObservableList<Scale> getAllScales()
    {
        System.out.println("Controller - getAllScales() ") ;
        return mAllScalesList ;
    }

    public String getDistinctSongDetails(Song selectedSong)
    {
        System.out.println("Controller - getDistinctSongDetails() ") ;
        return Model.getSelectedSongDetails(selectedSong) ;

    }

    public void updateSongDetails(String text, Song selectedSong)
    {
        System.out.println("Controller - updateSongDetails() ") ;
        Model.songEntryUpdate(text, selectedSong) ;
    }
}
