package com.example.simion_sizebook;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collection;

/* NOTE, PER ASSIGNMENT REQUIREMENTS: The list need not show all the data for a record if space is
limited. Minimally, each record in the list should show the name, and if available, bust, chest,
waist, and inseam values. THIS MEANS I NEED TO EDIT THE LISTVIEW */

/* Idea for how to update number of records in list is:
TextView myTextView = (TextView) findViewById(R.id.mytextview);

to get a dynamic reference to the TextView. Then you can use

myTextView.setText("My double value is " + doubleValue);

http://stackoverflow.com/questions/5169839/display-variable-on-screen-using-android-by-textviews
*/



public class MainActivity extends AppCompatActivity {

    private static final String FILENAME = "file.sav"; /* following format from lab 3 */
//    private ListView oldRecordList;

//    private ArrayList<Record> recordList;
//    private ArrayAdapter<Record> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RecordListManager.initManager(this.getApplicationContext());
        RecordListController.saveRecordList();

//        int number = RecordList.size();


        final ListView recordListView = (ListView) findViewById(R.id.oldRecordList);
        final Collection<Record> recordCollection = RecordListController.getRecordList().getRecords();

        final ArrayList<Record> list = new ArrayList<Record>(recordCollection);
//        ArrayAdapter<Record> recordAdapter = new ArrayAdapter<Record>(this, android.R.layout.simple_list_item_1, list);

        final ArrayAdapter<Record> recordAdapter = new ArrayAdapter<Record>(this, android.R.layout.simple_list_item_2,
                android.R.id.text1, list) {
            /*Based on: http://stackoverflow.com/questions/11722885/what-is-difference-between-
            android-r-layout-simple-list-item-1-and-android-r-lay */

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View view = super.getView(position, convertView, parent);
                TextView text1 = (TextView) view.findViewById(android.R.id.text1);
                TextView text2 = (TextView) view.findViewById(android.R.id.text2);

                text1.setText(list.get(position).getName());
                /* Bolding the name entry */
                text1.setTypeface(null, Typeface.BOLD);

                text2.setText("Bust: " + list.get(position).getBust() + "   Chest: " +
                        list.get(position).getChest() + "   Waist: " + list.get(position).getWaist()
                        + "     Inseam: " + list.get(position).getInseam());
                return view;
        }
        };


        recordListView.setAdapter(recordAdapter);

        /* Displaying the size of the record book here */
        TextView numberOfRecordsView = (TextView) findViewById(R.id.numberOfRecords);
        numberOfRecordsView.setText("You have " + recordCollection.size() + " records in your sizebook");


        Button addButton = (Button) findViewById(R.id.addButton);

        addButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                setResult(RESULT_OK);
                /* Moving to the add entry activity */
                Intent intent = new Intent(MainActivity.this, AddEntry.class);
                startActivity(intent);
            }
        });

        /* Inspiration for this came from
        http://stackoverflow.com/questions/4834750/how-to-get-the-selected-item-from-listview */

        recordListView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                RecordListController.findPosition(position);
                Intent entryIntent = new Intent(MainActivity.this, EntryViewActivity.class);
                startActivity(entryIntent);
            }
//            }
        });
    }           /* End bracket for onCreate */


}