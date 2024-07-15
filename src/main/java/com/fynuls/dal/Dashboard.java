package com.fynuls.dal;

import java.util.Comparator;

/**
 * @author Syed Muhammad Hassan
 * 13th July, 2022
 */

public class Dashboard implements Comparable<Dashboard>{
    private int id;
    private String html;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getHtml() {
        return html;
    }

    public void setHtml(String html) {
        this.html = html;
    }

    @Override
    public int compareTo(Dashboard o) {
        if(id<o.getId())
            return -1;
        else{
            return 1;
        }
    }
}
