package com.example.simion_sizebook;


//import java.util.ArrayList;
//import java.util.Collection;

import java.io.IOException;

/**
 * Created by mike on 2017-01-30.
 */

/* RecordListController is a class object that handles changes made to the recordList of the
 * SizeBook. Structure of this class follows Abram Hindle's Studentpicker tutorial video series
 * as seen here: //www.youtube.com/watch?v=5PPD0ncJU1g&list=PL240uJOh_Vb4PtMZ0f7N8ACYkCLv0673O*/


/* Static is a class level designation meaning the property does not belong to an instance */

public class RecordListController {
    private static RecordList recordList = null;
    private static int recordPosition;

    /* Retrieving the recordList */
    static public RecordList getRecordList(){
        if (recordList == null){
            try {
                /* Seeing if a previous recordList was saved */
                recordList = RecordListManager.getManager().loadRecordList();
            } catch (IOException e) {
                e.printStackTrace();
                throw new RuntimeException("Recordlist cannot be de-serialized from recordListManager");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
                throw new RuntimeException("Recordlist cannot be de-serialized from recordListManager");
            }
        }
        return recordList;
    }

    /* saveRecordList method*/
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
