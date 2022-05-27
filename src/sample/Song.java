package sample;

import java.io.Serializable;
import java.util.Objects;

public class Song implements Serializable, Comparable<Song> {

    private String mTitle ;
    private String mRoot ;
    private int mTempo ;
    private String mGenre ;
    private Scale mScale ;
    private String mSongNotes ;

    public Song(String title, String root, int tempo, String genre, Scale scale, String songNotes)
    {
        mTitle = title ;
        mRoot = root ;
        mTempo = tempo ;
        mGenre = genre ;
        mScale = scale ;
        mSongNotes = songNotes ;

    }
    public Song()
    {
        this("", "", 0, "", null, "") ;
    }

    public String getmTitle() {
        return mTitle;
    }

    public void setmTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public String getmRoot() {
        return mRoot;
    }

    public void setmRoot(String mRoot) {
        this.mRoot = mRoot;
    }

    public int getmTempo() {
        return mTempo;
    }

    public void setmTempo(int mTempo) {
        this.mTempo = mTempo;
    }

    public String getmGenre() {
        return mGenre;
    }

    public void setmGenre(String mGenre) {
        this.mGenre = mGenre;
    }

    public Scale getmScale() {
        return mScale;
    }

    public void setmScale(Scale mScale) {
        this.mScale = mScale;
    }

    public String getmSongNotes() {
        return mSongNotes;
    }

    public void setmSongNotes(String mSongNotes) {
        this.mSongNotes = mSongNotes;
    }

    @Override
    public String toString()
    {
        String scale = (mScale == null ? "none" : mScale.getmName()) ;
        return mTitle + " - " + mGenre + " - " + mTempo + " - " + mRoot + "  -  " + scale ;
    }
    @Override
    /**
     * See #Object.hashCode
     */
    public int hashCode() {
        return Objects.hash(mTitle, mRoot, mTempo, mGenre, mScale, mSongNotes);
    }

    @Override
    public int compareTo(Song o) {
        System.out.println("Im in the compareTo Method!") ;
        //test if o is a Cat object
        if (o instanceof Song)
        {//downcast Object to Song
            Song other =  (Song) o ;

            int titleComp = mTitle.compareTo(other.mTitle) ;

            if (titleComp != 0)
                return titleComp ;

            int rootComp = mRoot.compareTo(other.mRoot) ;

            if (rootComp != 0)
                return rootComp ;

            int tempoComp = mTempo - other.mTempo ;

            return tempoComp ;
        }
        else
            return -1 ; //returns a false

        //sort first by mTitle, mRoot, mTempo, mGenre, mScale, mSongNotes.
    }
}
