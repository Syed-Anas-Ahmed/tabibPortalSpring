package com.fynuls.dal;

import java.util.Comparator;

public class DashboardCom implements Comparator<Dashboard> {
    @Override
    public int compare(Dashboard o1, Dashboard o2) {
        if(o1.getId()<o2.getId()){
            return -1;
        }else{
            return 1;
        }
    }
}
