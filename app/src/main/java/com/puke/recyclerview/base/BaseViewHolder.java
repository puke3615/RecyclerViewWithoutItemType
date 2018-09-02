package com.puke.recyclerview.base;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.lang.reflect.Constructor;

/**
 * @author puke
 * @version 2018/8/28
 */
public class BaseViewHolder extends RecyclerView.ViewHolder {

    protected final Context context;
    protected final HolderContext holderContext;

    public BaseViewHolder(View itemView, HolderContext holderContext) {
        super(itemView);
        context = itemView.getContext();
        this.holderContext = holderContext;
    }

    public void onBindData(IType iType) {
    }

    public interface Factory {

        /**
         * 创建当前ViewHolder实例
         */
        @NonNull
        BaseViewHolder onCreateViewHolder(@NonNull HolderContext holderContext, @NonNull ViewGroup parent, int viewType);

        /**
         * 返回当前Factory对应的ItemType标识
         */
        String getType();
    }

    public static class DefaultFactory implements Factory {

        private final Class<? extends BaseViewHolder> holderType;
        private final String type;
        private final int layoutRes;

        public DefaultFactory(Class<? extends BaseViewHolder> holderType, String type, @LayoutRes int layoutRes) {
            this.holderType = holderType;
            this.type = type;
            this.layoutRes = layoutRes;
        }

        @NonNull
        @Override
        public BaseViewHolder onCreateViewHolder(@NonNull HolderContext holderContext, @NonNull ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            View view = inflater.inflate(layoutRes, parent, false);
            try {
                Constructor<? extends BaseViewHolder> constructor = holderType.getConstructor(View.class, HolderContext.class);
                return constructor.newInstance(view, holderContext);
            } catch (NoSuchMethodException e) {
                throw new RuntimeException("No legal constructor found.");
            } catch (Exception e) {
                throw new RuntimeException("Instance view holder failure.");
            }
        }

        @Override
        public String getType() {
            return type;
        }
    }

}
