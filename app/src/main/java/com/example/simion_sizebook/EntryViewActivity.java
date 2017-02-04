package com.example.simion_sizebook;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class EntryViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.entry_view);
        RecordListManager.initManager(this.getApplicationContext());


        /* Retrieving current record */
        Record currentEntry = RecordListController.selectRecord();
        /* Getting the IDs for all the text values on screen */
        TextView entryName = (TextView) findViewById(R.id.entryName);
        TextView entryDate = (TextView) findViewById(R.id.entryDate);
        TextView entryNeck = (TextView) findViewById(R.id.entryNeck);
        TextView entryBust = (TextView) findViewById(R.id.entryBust);
        TextView entryChest = (TextView) findViewById(R.id.entryChest);
        TextView entryWaist = (TextView) findViewById(R.id.entryWaist);
        TextView entryHip = (TextView) findViewById(R.id.entryHip);
        TextView entryInseam = (TextView) findViewById(R.id.entryInseam);
        TextView entryComment = (TextView) findViewById(R.id.entryComment);


        /* Need to add other values!!!!!!!! */
        entryName.setText(currentEntry.getName());

        entryDate.setText("Date: " + currentEntry.getDate());
        entryNeck.setText("Neck: " + currentEntry.getNeck());
        entryBust.setText("Bust: " + currentEntry.getBust());
        entryChest.setText("Chest: " + currentEntry.getChest());
        entryWaist.setText("Waist: " + currentEntry.getWaist());
        entryHip.setText("Hip: " + currentEntry.getHip());
        entryInseam.setText("Inseam: " + currentEntry.getInseam());

        entryComment.setText("Comment: " + currentEntry.getComment());


        Button deleteButton = (Button) findViewById(R.id.deleteButton);

        deleteButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                setResult(RESULT_OK);
                Record deleteEntry = RecordListController.selectRecord();
                RecordListController.deleteRecord(deleteEntry);
                /* Moving to the add entry activity */
                Intent intent = new Intent(EntryViewActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        Button editButton = (Button) findViewById(R.id.editButton);

        editButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                setResult(RESULT_OK);
                /* Moving to the add entry activity */
                Intent intent = new Intent(EntryViewActivity.this, EditActivity.class);
                startActivity(intent);
            }
        });




    }


}
