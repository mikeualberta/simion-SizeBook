package com.example.simion_sizebook;

import java.io.Serializable;

/**
 * Created by simion on 1/28/17.
 */

public class Record implements Serializable {

    /* https://www.mkyong.com/java/how-to-generate-serialversionuid/ says self generated is fine */
    private static final long serialVersionUID = 2L;


    private String name;            /* MIGHT NEED TO CHANGE NUMBER VALUES BACK TO FLOATS! */
    private String neck;
    private String bust;
    private String chest;
    private String waist;
    private String hip;
    private String inseam;
    private String comment;
    private String date;

    public Record(String name, String date, String neck, String bust, String chest, String waist,
                  String hip, String inseam, String comment) {
        this.name = name;
        this.date = date;
        this.neck = neck;
        this.bust = bust;
        this.chest = chest;
        this.waist = waist;
        this.hip = hip;
        this.inseam = inseam;
        this.comment = comment;

    }

    public String toString(){
        return getName();
    }

    public String getName() {
        return name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getNeck() {
        return neck;
    }

    public String getBust() {
        return bust;
    }

    public String getChest() {
        return chest;
    }

    public String getWaist() {
        return waist;
    }

    public String getHip() {
        return hip;
    }

    public String getInseam() {
        return inseam;
    }

    public String getComment() {
        return comment;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }


    public void setNeck(String neck) {
        this.neck = neck;
    }

    public void setBust(String bust) {
        this.bust = bust;
    }

    public void setChest(String chest) {
        this.chest = chest;
    }

    public void setWaist(String waist) {
        this.waist = waist;
    }

    public void setHip(String hip) {
        this.hip = hip;
    }

    public void setInseam(String inseam) {
        this.inseam = inseam;
    }

    public boolean checkValues(){
        int flag = 0;
        if (this.getNeck().trim().length() != 0){
            if(Double.parseDouble(this.getNeck()) % 0.5 != 0){
                return false;
            }
        }
        if (this.getBust().trim().length() != 0){
            if(Double.parseDouble(this.getBust()) % 0.5 != 0){
                return false;
            }
        }        if (this.getChest().trim().length() != 0){
            if(Double.parseDouble(this.getChest()) % 0.5 != 0){
                return false;
            }
        }
        if (this.getWaist().trim().length() != 0){
            if(Double.parseDouble(this.getWaist()) % 0.5 != 0){
                return false;
            }
        }        if (this.getHip().trim().length() != 0){
            if(Double.parseDouble(this.getHip()) % 0.5 != 0){
                return false;
            }
        }        if (this.getInseam().trim().length() != 0){
            if(Double.parseDouble(this.getInseam()) % 0.5 != 0){
                return false;
            }
        }
        return true;
    }
}