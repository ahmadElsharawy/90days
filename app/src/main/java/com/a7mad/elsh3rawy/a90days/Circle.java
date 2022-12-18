package com.a7mad.elsh3rawy.a90days;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.concurrent.TimeUnit;

public class Circle {

    String title;
    String datecreated;
    int value;

    public Circle(String title, String datecreated, String newDate) {

        this.title = title;
        this.datecreated = datecreated;
        try {
            this.value = (int) TimeUnit.DAYS.convert(new SimpleDateFormat("dd/MM/yyyy").parse(newDate).getTime() - new SimpleDateFormat("dd/MM/yyyy").parse(datecreated).getTime(), TimeUnit.MILLISECONDS);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public String getDatecreated() {
        return datecreated;
    }

    public void setDatecreated(String datecreated) {
        this.datecreated = datecreated;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
