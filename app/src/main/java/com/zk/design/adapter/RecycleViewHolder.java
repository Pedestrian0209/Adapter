package com.zk.design.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.View;
import android.widget.TextView;

import java.lang.ref.WeakReference;

public class RecycleViewHolder extends RecyclerView.ViewHolder {
    private SparseArray<WeakReference<View>> viewSparseArray;

    public RecycleViewHolder(@NonNull View itemView) {
        super(itemView);
        viewSparseArray = new SparseArray<>();
    }

    public View getView(int id) {
        WeakReference<View> weakReference = viewSparseArray.get(id);
        View view = null;
        if (weakReference == null) {
            view = itemView.findViewById(id);
            if (view != null) {
                viewSparseArray.put(id, new WeakReference<View>(view));
            }
        } else {
            view = weakReference.get();
        }
        return view;
    }

    public RecycleViewHolder setText(int id, String text) {
        ((TextView) getView(id)).setText(text);
        return this;
    }

    public RecycleViewHolder setTextColor(int id, int textColor) {
        ((TextView) getView(id)).setTextColor(textColor);
        return this;
    }

    public RecycleViewHolder setTextSize(int id, int textSize) {
        ((TextView) getView(id)).setTextSize(textSize);
        return this;
    }
}
