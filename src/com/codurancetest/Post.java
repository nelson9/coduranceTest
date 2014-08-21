package com.codurancetest;


import org.joda.time.Interval;
import org.joda.time.Period;

import java.text.DateFormat;
import java.util.Date;


/**
 * Created by niallferguson on 14/08/2014.
 * Class for user post, contains information including
 * user who created post, time created and content of post
 */

public class Post {

    private String user;
    private String content;
    private Date dateCreated;
    private DateFormat df;

    public Post(String user) {
        this.user = user;
        dateCreated = new Date();
    }

    public void setContent(String content) {
        this.content = content;
    }
    public Date getTime() {
        return dateCreated;
    }

    /**
     * Prints users posts to console, this includeds username
     * content of post and elapsed time.
     */
    public void printPost() {
        Date dateNow = new Date();

        Interval interval = new Interval(dateCreated.getTime(), dateNow.getTime());
        Period period = interval.toPeriod();

        if (period.getYears() >= 1) {
            System.out.println(user + " - " + content + " (" + period.getYears() + " years ago)");
        }
        else if (period.getYears() < 1 && period.getMonths() >= 1) {
            System.out.println(user + " - " + content + " (" + period.getMonths() + " months ago)");
        }
        else if (period.getMonths() < 1 && period.getDays() >= 1) {
            System.out.println(user + " - " + content + " (" + period.getDays() + " days ago)");
        }
        else if (period.getDays() < 1 && period.getHours() >= 1) {
            System.out.println(user + " - " + content + " (" + period.getHours() + " hours ago)");
        }
        else if (period.getHours() < 1 && period.getMinutes() >= 1) {
            System.out.println(user + " - " + content + " (" + period.getMinutes() + " minutes ago)");
        }
        else if (period.getMinutes() < 1 && period.getSeconds() >= 1) {
            System.out.println(user + " - " + content + " (" + period.getSeconds() + " seconds ago)");
        }
    }
}
