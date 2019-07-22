package com.zk.design.bean;

import com.zk.design.R;
import com.zk.design.adapter.IRecycleViewData;

public class LeftMessage implements IRecycleViewData {
    private String message;
    public LeftMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public int getItemViewId() {
        return R.layout.view_left_message;
    }
}
