package com.zk.design.bean;

import com.zk.design.R;
import com.zk.design.adapter.IRecycleViewData;

public class TimeMessage implements IRecycleViewData {
    private String time;

    public TimeMessage(String time) {
        this.time = time;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public int getItemViewId() {
        return R.layout.view_time_message;
    }
}
