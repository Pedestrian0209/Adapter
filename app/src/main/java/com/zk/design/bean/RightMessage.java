package com.zk.design.bean;

import com.zk.design.R;

public class RightMessage extends LeftMessage {
    public RightMessage(String message) {
        super(message);
    }

    @Override
    public int getItemViewId() {
        return R.layout.view_right_message;
    }
}
