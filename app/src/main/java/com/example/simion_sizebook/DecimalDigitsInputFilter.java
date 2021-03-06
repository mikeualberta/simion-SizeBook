package com.example.simion_sizebook;

import android.text.InputFilter;
import android.text.Spanned;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by mike on 2017-02-03.
 */


/* Code for this class design is from user user1545654 on stackOverflow (April 29, 2014)
http://stackoverflow.com/questions/17423483/how-to-limit-edittext-length-to-
7-integers-and-2-decimal-places
*/

public class DecimalDigitsInputFilter implements InputFilter {

    Pattern mPattern;

    public DecimalDigitsInputFilter(int digitsBeforeZero, int digitsAfterZero){
        mPattern = Pattern.compile("[0-9]{0," + (digitsBeforeZero-1) + "}+((\\.[0-9]{0," +
                (digitsAfterZero-1) + "})?)||(\\.)?");
    }


    @Override
    public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {

        Matcher matcher=mPattern.matcher(dest);
        if(!matcher.matches())
            return "";
        return null;
    }

}

