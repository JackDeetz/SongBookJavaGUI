package sample;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Objects;

public class Scale implements Serializable {

    private String mName ;
    private String[] mNotesInE ;


    public Scale(String name, String[] notesInE)
    {
        mName = name ;
        mNotesInE = notesInE ;
    }
    public Scale()
    {
        this("",null);
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public String[] getmNotesInE() {
        return mNotesInE;
    }

    public void setmNotesInE(String[] mNotesInE) {
        this.mNotesInE = mNotesInE;
    }

    @Override
    public String toString()
    {
        return mName + " - " + Arrays.toString(mNotesInE);
    }

    @Override
    /**
     * See #Object.hashCode
     */
    public int hashCode() {
        return Objects.hash(mName, mNotesInE);
    }

}
