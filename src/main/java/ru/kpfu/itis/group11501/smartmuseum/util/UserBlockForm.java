package ru.kpfu.itis.group11501.smartmuseum.util;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by Amir Kadyrov
 * Date: 12.05.2018
 */
public class UserBlockForm {
    private Long userID;
    private double blockDate;

    public UserBlockForm() {
    }

    public UserBlockForm(Long userID) {
        this.userID = userID;
    }

    public double getBlockDate() {
        return blockDate;
    }

    public void setBlockDate(double blockDate) {
        this.blockDate = blockDate;
    }

    public Long getUserID() {
        return userID;
    }

    public void setUserID(Long userID) {
        this.userID = userID;
    }
}
