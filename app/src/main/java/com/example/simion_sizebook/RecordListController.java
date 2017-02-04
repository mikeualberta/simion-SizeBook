package com.example.simion_sizebook;


//import java.util.ArrayList;
//import java.util.Collection;

import java.io.IOException;

/**
 * Created by mike on 2017-01-30.
 */

/* Static is a class level designation meaning the property does not belong to an instance */

public class RecordListController {
    /* Code from Abram Hindle Studentpicker how do I source??? */
    private static RecordList recordList = null;
    private static int recordPosition;


    static public RecordList getRecordList(){
        if (recordList == null){
            try {
                recordList = RecordListManager.getManager().loadRecordList();
            } catch (IOException e) {
                e.printStackTrace();
                throw new RuntimeException("Recordlist cannot be de-serialized from recordListManager");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
                throw new RuntimeException("Recordlist cannot be de-serialized from recordListManager");
            }
//            recordList = new RecordList();
        }
        return recordList;
    }

    static public void saveRecordList(){
        try {
            RecordListManager.getManager().saveRecordList(getRecordList());
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Recordlist cannot be de-serialized from recordListManager");
        }
    }

    public void addRecord(Record record){
        getRecordList().addRecord(record);
    }


    public static void findPosition(int position) {
        recordPosition = position;
    }

    /* Recordlist controller calls on recordList object to return the record at the selected
    position */

    public static Record selectRecord(){
        return recordList.pickRecord(recordPosition);
    }

    public static void deleteRecord(Record record){
        recordList.removeRecord(record);
    }

}
