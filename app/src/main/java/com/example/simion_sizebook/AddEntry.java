package com.example.simion_sizebook;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
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

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

/*  NOTE: The app must assist the user in proper data entry. For example, use appropriate user
interface controls to enforce particular data types. */

/* Using as reference for datepicker:
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
        setContentView(R.layout.add_entry);
        RecordListManager.initManager(this.getApplicationContext());


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


        /* Cite stackOverflow */

        dateText.setFocusable(false);

//        MyEditTextDatePicker datePick = new MyEditTextDatePicker(AddEntry.this, dateText);

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



        Button confirmAddButton = (Button) findViewById(R.id.confirmAdd);

        confirmAddButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                setResult(RESULT_OK);

                String name = nameText.getText().toString();
                String date = dateText.getText().toString();
                String neck = neckText.getText().toString();
                String bust = bustText.getText().toString();
                String chest = chestText.getText().toString();
                String waist = waistText.getText().toString();
                String hip = hipText.getText().toString();
                String inseam = inseamText.getText().toString();
                String comment = commentText.getText().toString();


                Record record = new Record(name, date, neck, bust, chest, waist, hip, inseam, comment);
        /* Function activated on click of add record on the add_entry screen layout */

                if(record.checkValues() == false){
                    Toast.makeText(AddEntry.this, "Measurements must be positive decimal numbers " +
                            "ending in .0 or .5", Toast.LENGTH_LONG).show();
                    return;
                }

        /* Method of finding empty string from: http://stackoverflow.com/questions/24391809/android-check-if-edittext-is-empty*/
                if (record.getName().trim().length() != 0){
                    Toast.makeText(AddEntry.this, "Record added!", Toast.LENGTH_SHORT).show();
                    RecordListController rc = new RecordListController();
                    rc.addRecord(record);
            /* Return to the list view screen after adding the entry */
                    Intent intent = new Intent(AddEntry.this, MainActivity.class);
                    startActivity(intent);
                }

                else{
                    Toast.makeText(AddEntry.this, "Please enter a name for your record", Toast.LENGTH_SHORT).show();
                    return;
//                Intent intent = new Intent(MainActivity.this, AddEntry.class);
//                startActivity(intent);
            }
        }


    });


    }

    /* Cite StackOverflow */
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

