package com.puke.recyclerview.base;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author puke
 * @version 2018/8/26
 */
public class BaseAdapter extends RecyclerView.Adapter<BaseViewHolder> {

    @NonNull
    private final List<IType> dataSource;
    @NonNull
    private final HolderContext holderContext;
    @NonNull
    private final List<? extends BaseViewHolder.Factory> factories;
    private final Map<String, Integer> type2ItemType = new HashMap<>();

    public BaseAdapter(@NonNull HolderContext holderContext, @NonNull List<? extends BaseViewHolder.Factory> factories) {
        this.holderContext = holderContext;
        this.dataSource = new ArrayList<>();
        this.factories = factories;
        for (int i = 0; i < factories.size(); i++) {
            BaseViewHolder.Factory factory = factories.get(i);
            type2ItemType.put(factory.getType(), i);
        }
    }

    @Override
    public int getItemViewType(int position) {
        IType iType = dataSource.get(position);
        String type = iType.getType();
        return type2ItemType.get(type);
    }

    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        BaseViewHolder.Factory targetFactory = factories.get(viewType);
        if (targetFactory == null) {
            throw new RuntimeException("No factory found with type equals " + viewType);
        }
        return targetFactory.onCreateViewHolder(holderContext, parent, viewType);
    }

    /**
     * 数据刷新
     *
     * @param dataSource 数据源
     */
    public void setDataSource(@Nullable List<IType> dataSource) {
        this.dataSource.clear();
        if (dataSource != null) {
            this.dataSource.addAll(dataSource);
        }
        notifyDataSetChanged();
    }

    /**
     * 数据添加
     *
     * @param dataSource 数据源
     */
    public void addDataSource(@Nullable List<IType> dataSource) {
        if (dataSource != null && !dataSource.isEmpty()) {
            this.dataSource.addAll(dataSource);
            notifyDataSetChanged();
        }
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {
        IType itemData = dataSource.get(position);
        holder.onBindData(itemData);
    }

    @Override
    public int getItemCount() {
        return dataSource.size();
    }

}
