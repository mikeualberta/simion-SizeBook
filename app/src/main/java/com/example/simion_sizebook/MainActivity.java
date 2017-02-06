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

import java.util.ArrayList;
import java.util.Collection;


/* The main activity of the SizeBook application that begins upon starting the application
 * Application by Michael Simion. Completed Feb. 4, 2017. */

public class MainActivity extends AppCompatActivity {

    /* The onCreate function */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /* Setting the view to the appropriate layout */
        setContentView(R.layout.activity_main);
        /* Initializing the RecordListManager and saving the recordList and its contents*/
        RecordListManager.initManager(this.getApplicationContext());
        RecordListController.saveRecordList();

        /* Accessing the list displayed ont the layout screen */
        final ListView recordListView = (ListView) findViewById(R.id.oldRecordList);
        /* Creating a collection that contains the contents of the recordList. Retrieved by the
        * RecordListController */
        final Collection<Record> recordCollection = RecordListController.getRecordList().getRecords();
        final ArrayList<Record> list = new ArrayList<Record>(recordCollection);

        /* Creating an ArrayAdapter that will display the record entry information on two lines
        * through utilization of simple_list_item_2 */
        final ArrayAdapter<Record> recordAdapter = new ArrayAdapter<Record>(this, android.R.layout.simple_list_item_2,
                android.R.id.text1, list) {
            /* Override of getView based on user winne2's post on StackOverflow (Aug. 30, 2013)
            http://stackoverflow.com/questions/11722885/what-is-difference-between-
            android-r-layout-simple-list-item-1-and-android-r-lay */

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View view = super.getView(position, convertView, parent);
                TextView text1 = (TextView) view.findViewById(android.R.id.text1);
                TextView text2 = (TextView) view.findViewById(android.R.id.text2);
                /* Setting the text that will appear on the two lines of the list entry */
                text1.setText(list.get(position).getName());
                /* Bolding the name entry */
                text1.setTypeface(null, Typeface.BOLD);

                text2.setText("Bust: " + list.get(position).getBust() + "   Chest: " +
                        list.get(position).getChest() + "   Waist: " + list.get(position).getWaist()
                        + "     Inseam: " + list.get(position).getInseam());
                return view;
        }
        };

        /* Setting the listview to display the contents of the record list*/
        recordListView.setAdapter(recordAdapter);

        /* Displaying the size of the record book here */
        TextView numberOfRecordsView = (TextView) findViewById(R.id.numberOfRecords);
        numberOfRecordsView.setText("You have " + recordCollection.size() + " records in your sizebook");

        /* Accessing the add record button displayed on the screen and setting up a listener for it */
        Button addButton = (Button) findViewById(R.id.addButton);

        addButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                setResult(RESULT_OK);
                /* Moving to the add entry activity upon click of add record button */
                Intent intent = new Intent(MainActivity.this, AddEntry.class);
                startActivity(intent);
            }
        });

        /* Inspiration for this came from user xandy's post at StackOverflow (Jan. 29, 2011)
        http://stackoverflow.com/questions/4834750/how-to-get-the-selected-item-from-listview */

        recordListView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                /* Passing the position to the recordListController */
                RecordListController.findPosition(position);
                /* Jumping to the entryView activity once the user selects an entry from the list */
                Intent entryIntent = new Intent(MainActivity.this, EntryViewActivity.class);
                startActivity(entryIntent);
            }
//            }
        });
    }           /* End bracket for onCreate */


}