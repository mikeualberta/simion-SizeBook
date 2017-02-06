package com.example.simion_sizebook;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Base64;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * Created by mike on 2017-02-03.
 */

/* RecordListManager class object. Handles the saving and loading of the recordList and records
 * for the SizeBook application. Structure and code of this class follows Abram Hindle's Studentpicker
 * tutorial video series as seen here:
 * www.youtube.com/watch?v=5PPD0ncJU1g&list=PL240uJOh_Vb4PtMZ0f7N8ACYkCLv0673O*/

public class RecordListManager {
    static final String prefFile = "RecordList";
    static final String rlkey = "recordList";

    Context context;

    static private RecordListManager recordListManager = null;

    /* intialization method of the object*/
    public static void initManager(Context context){
        if(recordListManager == null){
            if(context == null){
                throw new RuntimeException("Missing context for recordListManager");
            }
            /* Creating a new manager */
            recordListManager = new RecordListManager(context);
        }
    }
    /* getter */
    public static RecordListManager getManager(){
        if(recordListManager == null){
                throw new RuntimeException("Didn't initialize manager");
        }
        return recordListManager;
    }

    /* constructor */
    public RecordListManager(Context context){
        this.context = context;
    }

    /* loadRecordList method. Following implementation detailed by Abram Hindle in video
     *  referenced above*/
    public RecordList loadRecordList() throws IOException, ClassNotFoundException {
        SharedPreferences settings = context.getSharedPreferences(prefFile, context.MODE_PRIVATE);
        String recordListData = settings.getString(rlkey, "");
        if(recordListData.equals("")){
            /* Returning a new RecordList if one does not exist */
            return new RecordList();
        } else{
            return recordListFromString(recordListData);
        }
    }

    /* recordListFromString method. Following implementation detailed by Abram Hindle in video
    *  referenced above*/
    static public RecordList recordListFromString(String recordListData) throws IOException, ClassNotFoundException {
        ByteArrayInputStream bi = new ByteArrayInputStream(Base64.decode(recordListData, Base64.DEFAULT));
        ObjectInputStream oi = new ObjectInputStream(bi);
        return (RecordList)oi.readObject();
    }

    /* saveRecordList method. Following implementation detailed by Abram Hindle in video
    *  referenced above*/
    public void saveRecordList(RecordList rl) throws IOException {
        SharedPreferences settings = context.getSharedPreferences(prefFile, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString(rlkey, recordListToString(rl));
        editor.commit();
    }
    /* recordListToString method. Following implementation detailed by Abram Hindle in video
     *  referenced above*/
    static public String recordListToString(RecordList rl) throws IOException {
        ByteArrayOutputStream bo = new ByteArrayOutputStream();
        ObjectOutputStream oo = new ObjectOutputStream(bo);
        oo.writeObject(rl);
        oo.close();
        byte bytes[] = bo.toByteArray();
        return Base64.encodeToString(bytes, Base64.DEFAULT);
    }
}
