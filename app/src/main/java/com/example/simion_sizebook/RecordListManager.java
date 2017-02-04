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

public class RecordListManager {
    static final String prefFile = "RecordList";
    static final String rlkey = "recordList";

    Context context;

    static private RecordListManager recordListManager = null;

    public static void initManager(Context context){
        if(recordListManager == null){
            if(context == null){
                throw new RuntimeException("Missing context for recordListManager");
            }
            recordListManager = new RecordListManager(context);
        }
    }

    public static RecordListManager getManager(){
        if(recordListManager == null){
                throw new RuntimeException("Didn't initialize manager");
        }
        return recordListManager;
    }

    public RecordListManager(Context context){
        this.context = context;
    }

    public RecordList loadRecordList() throws IOException, ClassNotFoundException {
        SharedPreferences settings = context.getSharedPreferences(prefFile, context.MODE_PRIVATE);
        String recordListData = settings.getString(rlkey, "");
        if(recordListData.equals("")){
            /*Messier solution implemented here due to use of static size */
//            RecordList createdRecordList = new RecordList();
//            createdRecordList.setSize();
//            return  createdRecordList;
            return new RecordList();
        } else{
            return recordListFromString(recordListData);
        }
    }

    static public RecordList recordListFromString(String recordListData) throws IOException, ClassNotFoundException {
        ByteArrayInputStream bi = new ByteArrayInputStream(Base64.decode(recordListData, Base64.DEFAULT));
        ObjectInputStream oi = new ObjectInputStream(bi);
        return (RecordList)oi.readObject();
    }

    public void saveRecordList(RecordList rl) throws IOException {
        SharedPreferences settings = context.getSharedPreferences(prefFile, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString(rlkey, recordListToString(rl));
        editor.commit();
    }

    static public String recordListToString(RecordList rl) throws IOException {
        ByteArrayOutputStream bo = new ByteArrayOutputStream();
        ObjectOutputStream oo = new ObjectOutputStream(bo);
        oo.writeObject(rl);
        oo.close();
        byte bytes[] = bo.toByteArray();
        return Base64.encodeToString(bytes, Base64.DEFAULT);
    }
}
