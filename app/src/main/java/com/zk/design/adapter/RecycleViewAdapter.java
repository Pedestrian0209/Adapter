package com.zk.design.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public abstract class RecycleViewAdapter extends RecyclerView.Adapter<RecycleViewHolder> {
    private List<IRecycleViewData> iRecycleViewData;

    @NonNull
    @Override
    public RecycleViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        if (viewType <= 0) {
            Log.e("RecycleViewAdapter", "Please make your data implement IRecycleViewData");
            return null;
        }
        return new RecycleViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(viewType, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecycleViewHolder viewHolder, int position) {
        bindData(viewHolder, iRecycleViewData.get(position), position);
    }

    @Override
    public void onBindViewHolder(@NonNull RecycleViewHolder holder, int position, @NonNull List<Object> payloads) {
        if (payloads.isEmpty()) {
            onBindViewHolder(holder, position);
        } else {
            bindData(holder, iRecycleViewData.get(position), position, payloads);
        }
    }

    protected abstract void bindData(@NonNull RecycleViewHolder viewHolder, IRecycleViewData data, int position);

    protected void bindData(@NonNull RecycleViewHolder viewHolder, IRecycleViewData data, int position, @NonNull List<Object> payloads) {

    }

    @Override
    public int getItemCount() {
        return iRecycleViewData == null ? 0 : iRecycleViewData.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (iRecycleViewData != null && !iRecycleViewData.isEmpty()) {
            //这里利用item的布局id作为type返回
            return iRecycleViewData.get(position).getItemViewId();
        }
        return super.getItemViewType(position);
    }


    /**
     * 添加单个数据
     *
     * @param data
     */
    public void addData(IRecycleViewData data) {
        if (iRecycleViewData == null) {
            iRecycleViewData = new ArrayList<>();
        }
        iRecycleViewData.add(data);
        notifyDataSetChanged();
    }

    /**
     * 添加一组数据
     *
     * @param data
     */
    public void addData(Collection<? extends IRecycleViewData> data) {
        if (iRecycleViewData == null) {
            iRecycleViewData = new ArrayList<>();
        }
        iRecycleViewData.addAll(data);
        notifyDataSetChanged();
    }

    /**
     * 添加数据到制定位置
     *
     * @param index
     * @param data
     */
    public void addData(int index, IRecycleViewData data) {
        if (iRecycleViewData == null || iRecycleViewData.isEmpty() || index >= iRecycleViewData.size()) {
            addData(data);
        } else {
            iRecycleViewData.add(index, data);
        }
        notifyDataSetChanged();
    }

    /**
     * 修改指定位置的数据
     *
     * @param index
     * @param data
     */
    public void setData(int index, IRecycleViewData data) {
        if (iRecycleViewData == null || iRecycleViewData.isEmpty() || index >= iRecycleViewData.size()) {
            return;
        } else {
            iRecycleViewData.set(index, data);
        }
        notifyDataSetChanged();
    }

    /**
     * 修改指定的数据
     *
     * @param oldData
     * @param newData
     */
    public void setData(IRecycleViewData oldData, IRecycleViewData newData) {
        if (iRecycleViewData == null || iRecycleViewData.isEmpty()) {
            return;
        } else {
            int index = iRecycleViewData.indexOf(oldData);
            if (index < -1) {
                return;
            } else {
                iRecycleViewData.set(index, newData);
            }
        }
        notifyDataSetChanged();
    }

    /**
     * 删除指定位置的数据
     *
     * @param index
     */
    public void removeData(int index) {
        if (iRecycleViewData == null || iRecycleViewData.isEmpty() || index >= iRecycleViewData.size()) {
            return;
        } else {
            iRecycleViewData.remove(index);
        }
        notifyDataSetChanged();
    }

    /**
     * 删除数据
     *
     * @param data
     */
    public void removeData(IRecycleViewData data) {
        if (iRecycleViewData == null || iRecycleViewData.isEmpty()) {
            return;
        } else {
            int index = iRecycleViewData.indexOf(data);
            if (index < -1) {
                return;
            } else {
                iRecycleViewData.remove(data);
            }
        }
        notifyDataSetChanged();
    }

    /**
     * 删除一组数据
     *
     * @param data
     */
    public void removeData(Collection<? extends IRecycleViewData> data) {
        if (iRecycleViewData == null || iRecycleViewData.isEmpty()) {
            return;
        } else {
            iRecycleViewData.removeAll(data);
        }
        notifyDataSetChanged();
    }

    /**
     * 替换所有数据
     *
     * @param data
     */
    public void replaceAllData(Collection<? extends IRecycleViewData> data) {
        if (iRecycleViewData == null) {
            iRecycleViewData = new ArrayList<>();
        }
        iRecycleViewData.clear();
        iRecycleViewData.addAll(data);
        notifyDataSetChanged();
    }

    /**
     * 清除数据
     */
    public void clear() {
        if (iRecycleViewData != null) {
            iRecycleViewData.clear();
            iRecycleViewData = null;
        }
        notifyDataSetChanged();
    }

}
