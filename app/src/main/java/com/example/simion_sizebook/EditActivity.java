package com.example.simion_sizebook;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputFilter;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;


/* Still need to implement unit check and date picker for the edit activity. Then include
* a way to restrict digits after the decimal. Also need
* to properly display records in the list. Need to implement saving in the app and loading
* Then video and uml diagram. Add comments and proper citations at the end*/
public class EditActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText editName;
    private EditText editDate;
    private EditText editNeck;
    private EditText editBust;
    private EditText editChest;
    private EditText editWaist;
    private EditText editHip;
    private EditText editInseam;
    private EditText editComment;

    private Record editEntry;

    private Calendar editCalendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_entry);
        RecordListManager.initManager(this.getApplicationContext());


        editEntry = RecordListController.selectRecord();

        editName = (EditText) findViewById(R.id.editName);
        editDate = (EditText) findViewById(R.id.editDate);
        editNeck = (EditText) findViewById(R.id.editNeck);
        editBust = (EditText) findViewById(R.id.editBust);
        editChest = (EditText) findViewById(R.id.editChest);
        editWaist = (EditText) findViewById(R.id.editWaist);
        editHip = (EditText) findViewById(R.id.editHip);
        editInseam = (EditText) findViewById(R.id.editInseam);
        editComment = (EditText) findViewById(R.id.editComment);

        /* Restricting the number of digits total and digits after the decimal place */

        editNeck.setFilters(new InputFilter[] {new DecimalDigitsInputFilter(4,1)});
        editBust.setFilters(new InputFilter[] {new DecimalDigitsInputFilter(4,1)});
        editChest.setFilters(new InputFilter[] {new DecimalDigitsInputFilter(4,1)});
        editWaist.setFilters(new InputFilter[] {new DecimalDigitsInputFilter(4,1)});
        editHip.setFilters(new InputFilter[] {new DecimalDigitsInputFilter(4,1)});


        /* Filling the editText boxes with the values for the selected record */

        editName.setText(editEntry.getName());
        editDate.setText(editEntry.getDate());
        editNeck.setText(editEntry.getNeck());
        editBust.setText(editEntry.getBust());
        editChest.setText(editEntry.getChest());
        editWaist.setText(editEntry.getWaist());
        editHip.setText(editEntry.getHip());
        editInseam.setText(editEntry.getInseam());
        editComment.setText(editEntry.getComment());

        /* Handling date entry stuff */

        editDate.setFocusable(false);


        editDate.setOnClickListener(this);

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MMM-dd");

        editDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editCalendar = Calendar.getInstance();
                new DatePickerDialog(EditActivity.this, onDateSetListener,
                        editCalendar.get(Calendar.YEAR), editCalendar.get(Calendar.MONTH),
                        editCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });




        Button confirmEditButton = (Button) findViewById(R.id.editCompleteButton);

        confirmEditButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                setResult(RESULT_OK);


                editEntry.setName(editName.getText().toString());
                editEntry.setDate(editDate.getText().toString());
                editEntry.setNeck(editNeck.getText().toString());
                editEntry.setBust(editBust.getText().toString());
                editEntry.setChest(editChest.getText().toString());
                editEntry.setWaist(editWaist.getText().toString());
                editEntry.setHip(editHip.getText().toString());
                editEntry.setInseam(editInseam.getText().toString());
                editEntry.setComment(editComment.getText().toString());


                if(editEntry.checkValues() == false){
                    Toast.makeText(EditActivity.this, "Measurements must be positive decimal numbers " +
                            "ending in .0 or .5", Toast.LENGTH_LONG).show();
                    return;
                }

                if (editEntry.getName().trim().length() != 0){
                    Toast.makeText(EditActivity.this, "Edit Complete!", Toast.LENGTH_SHORT).show();

            /* Return to the list view screen after editing the entry */

                    Intent doneEditintent = new Intent(EditActivity.this, MainActivity.class);
                    startActivity(doneEditintent);
                }

                else{
                    Toast.makeText(EditActivity.this, "Please enter a name for your record", Toast.LENGTH_SHORT).show();
                    return;
                }


            }
        });


    }


    DatePickerDialog.OnDateSetListener onDateSetListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            editCalendar.set(Calendar.YEAR, year);
            editCalendar.set(Calendar.MONTH, monthOfYear);
            editCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            editDate.setText(year + "-" +  (monthOfYear + 1) + "-" + dayOfMonth);
        }
    };

    @Override
    public void onClick(View v) {

    }
}
