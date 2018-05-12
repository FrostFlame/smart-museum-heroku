package ru.kpfu.itis.group11501.smartmuseum.util;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Amir Kadyrov
 * Date: 12.05.2018
 */
public class UserBlockForm {
    private Long userID;
    private int blockDate;
    private Map blockDates;

    public UserBlockForm() {
    }

    public UserBlockForm(Long userID) {
        this.userID = userID;
        this.blockDates = new HashMap();
        blockDates.put("30 минут", 0.5);
        blockDates.put("1 час", 1);
        blockDates.put("24 часа", 24);
        blockDates.put("Навсегда", Integer.MAX_VALUE);
    }

    public int getBlockDate() {
        return blockDate;
    }

    public void setBlockDate(int blockDate) {
        this.blockDate = blockDate;
    }

    public Long getUserID() {
        return userID;
    }

    public void setUserID(Long userID) {
        this.userID = userID;
    }

    public Map getBlockDates() {
        return blockDates;
    }

    public void setBlockDates(Map blockDates) {
        this.blockDates = blockDates;
    }
}
