package com.example.simion_sizebook;

import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by simion on 1/28/17.
 */

public class RecordList implements Serializable {


    private static final long serialVersionUID = 1L;


    protected ArrayList<Record> recordList = null;
//    public static int size; /* CHECK what static does to size here */

    public RecordList() {
        recordList = new ArrayList<Record>();
    }

    public void addRecord(Record record){
        /* Use method from http://stackoverflow.com/questions/6290531/check-if-edittext-is-empty ???*/
        /* Do I need to error check the argument? */
        recordList.add(record);
//        size++;
    }

    public int size() {
        /* use static variable due to how function is called in main activity... not very elegant*/
        return this.getRecords().size();
    }

    public Collection<Record> getRecords() {
        return recordList;
    }

//    public void setSize(){
//        /* Called only when created */
//        size = 0;
//    }


    public void removeRecord(Record record){
        recordList.remove(record);
//        size--;
    }

    /* Returns the record at position recordPosition */
    public Record pickRecord(int recordPosition) {
        return recordList.get(recordPosition);
    }
}