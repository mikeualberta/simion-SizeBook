package com.example.simion_sizebook;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputFilter;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

/*  The AddEntry activity allows the user to add a record to his/her SizeBook */

/* Used this link as a reference for the implementation of my datepicker
https://developer.android.com/guide/topics/ui/controls/pickers.html
 */


public class AddEntry extends AppCompatActivity implements View.OnClickListener {

    private EditText nameText;
    private EditText dateText;
    private EditText neckText;
    private EditText bustText;
    private EditText chestText;
    private EditText waistText;
    private EditText hipText;
    private EditText inseamText;
    private EditText commentText;

    private ArrayList<Record> recordList;
    private ArrayAdapter<Record> adapter;

    private Calendar myCalendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /* Setting the view to the add_entry layout view*/
        setContentView(R.layout.add_entry);
        /* Initializing the RecordListManager*/
        RecordListManager.initManager(this.getApplicationContext());

        /* Accessing the editText fields on the screen */
        nameText = (EditText) findViewById(R.id.addName);
        dateText = (EditText) findViewById(R.id.addDate);
        neckText = (EditText) findViewById(R.id.addNeck);
        bustText = (EditText) findViewById(R.id.addBust);
        chestText = (EditText) findViewById(R.id.addChest);
        waistText = (EditText) findViewById(R.id.addWaist);
        hipText = (EditText) findViewById(R.id.addHip);
        inseamText = (EditText) findViewById(R.id.addInseam);
        commentText = (EditText) findViewById(R.id.addComment);

        /* Restricting the numbers of digits that can be entered total and digits after the decimal */

        neckText.setFilters(new InputFilter[] {new DecimalDigitsInputFilter(4,1)});
        bustText.setFilters(new InputFilter[] {new DecimalDigitsInputFilter(4,1)});
        chestText.setFilters(new InputFilter[] {new DecimalDigitsInputFilter(4,1)});
        waistText.setFilters(new InputFilter[] {new DecimalDigitsInputFilter(4,1)});
        hipText.setFilters(new InputFilter[] {new DecimalDigitsInputFilter(4,1)});


    /* Basis for datepicker came from user Android_coder's post on StackOverflow (Feb 18, 2013) */
    /* http://stackoverflow.com/questions/14933330/datepicker-how-to-popup-datepicker-when-click-on-edittext */


        /* Prevents keyboard from coming up when user clicks on field*/
        dateText.setFocusable(false);

        /* Will create a pop-up calendar when user clicks on editText field */
        dateText.setOnClickListener(this);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MMM-dd");

        dateText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myCalendar = Calendar.getInstance();
                new DatePickerDialog(AddEntry.this, onDateSetListener,
                        myCalendar.get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });


        /* Accessing the confirm addButton on the screen*/
        Button confirmAddButton = (Button) findViewById(R.id.confirmAdd);
        /* Listener to see if user clicks on add button */
        confirmAddButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                setResult(RESULT_OK);

                /* Storing the values in the editText fields to variables */
                String name = nameText.getText().toString();
                String date = dateText.getText().toString();
                String neck = neckText.getText().toString();
                String bust = bustText.getText().toString();
                String chest = chestText.getText().toString();
                String waist = waistText.getText().toString();
                String hip = hipText.getText().toString();
                String inseam = inseamText.getText().toString();
                String comment = commentText.getText().toString();

                /* Passing the values to a new record entry */
                Record record = new Record(name, date, neck, bust, chest, waist, hip, inseam, comment);
                /* Running the checkValues() method to determine that numeric values conform to
                * the assignment requirements*/
                if(record.checkValues() == false){
                    /* Displaying message to user to inform him of required entry types*/
                    Toast.makeText(AddEntry.this, "Measurements must be positive decimal numbers " +
                            "ending in .0 or .5", Toast.LENGTH_LONG).show();
                    return;
                }

                /* Determining if the user entered a name for the record*/
                if (record.getName().trim().length() != 0){
                    Toast.makeText(AddEntry.this, "Record added!", Toast.LENGTH_SHORT).show();
                    /* Adding the record to the list through the controller*/
                    RecordListController rc = new RecordListController();
                    rc.addRecord(record);
                     /* Return to the list view screen after adding the entry */
                    Intent intent = new Intent(AddEntry.this, MainActivity.class);
                    startActivity(intent);
                }

                else{
                    /* Informing the user that he/she is required to enter a name for his/her entry*/
                    Toast.makeText(AddEntry.this, "Please enter a name for your record", Toast.LENGTH_SHORT).show();
                    return;

            }
        }


    });


    }

    /* Basis for datepicker came from user Android_coder's post on StackOverflow (Feb 18, 2013) */
    /* http://stackoverflow.com/questions/14933330/datepicker-how-to-popup-datepicker-when-click-on-edittext */

    DatePickerDialog.OnDateSetListener onDateSetListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            myCalendar.set(Calendar.YEAR, year);
            myCalendar.set(Calendar.MONTH, monthOfYear);
            myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            dateText.setText(year + "-" +  (monthOfYear + 1) + "-" + dayOfMonth);
        }
    };


    @Override
    public void onClick(View v) {

    }
}

