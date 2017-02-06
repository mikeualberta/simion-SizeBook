package com.example.simion_sizebook;

import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by simion on 1/28/17.
 */

/* The RecordList class stores the Records in a user's SizeBook. The class implements
 * Serializable to allow a RecordList object to be saved, as demonstrated by Abram Hindle in his
 * tutorial video series of the StudentPicker application:
 * https: www.youtube.com/watch?v=5PPD0ncJU1g&list=PL240uJOh_Vb4PtMZ0f7N8ACYkCLv0673O */

public class RecordList implements Serializable {


    /* Self-generated serialID. Discovered that self-generating an ID is fine from:
    * https://www.mkyong.com/java/how-to-generate-serialversionuid/ */
    private static final long serialVersionUID = 1L;


    protected ArrayList<Record> recordList = null;

    /* Constructor for the class */
    public RecordList() {
        recordList = new ArrayList<Record>();
    }

    /* addRecord method */
    public void addRecord(Record record){
        recordList.add(record);
    }

    /* getter */
    public Collection<Record> getRecords() {
        return recordList;
    }

    /* Remove record method*/
    public void removeRecord(Record record){
        recordList.remove(record);
    }

    /* Returns the record at position recordPosition */
    public Record pickRecord(int recordPosition) {
        return recordList.get(recordPosition);
    }
}