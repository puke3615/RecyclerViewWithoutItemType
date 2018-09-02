package com.puke.recyclerview.viewholder;

import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.puke.recyclerview.ItemData;
import com.puke.recyclerview.R;
import com.puke.recyclerview.base.BaseViewHolder;
import com.puke.recyclerview.base.HolderContext;
import com.puke.recyclerview.base.IType;

/**
 * @author puke
 * @version 2018/8/28
 */
public class DViewHolder extends BaseViewHolder implements View.OnClickListener {

    private final TextView textView;

    public DViewHolder(View itemView, @NonNull HolderContext holderContext) {
        super(itemView, holderContext);
        textView = itemView.findViewById(R.id.item_text_d);
        textView.setOnClickListener(this);
    }

    @Override
    public void onBindData(IType iType) {
        super.onBindData(iType);
        ItemData data = (ItemData) iType;
        textView.setText(data.textD);
    }

    @Override
    public void onClick(View v) {
        Toast.makeText(context, "Click Item D", Toast.LENGTH_SHORT).show();
    }

    public static class Factory implements BaseViewHolder.Factory {

        @NonNull
        @Override
        public BaseViewHolder onCreateViewHolder(@NonNull HolderContext holderContext, @NonNull ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            final View itemView = inflater.inflate(R.layout.item_d, parent, false);
            return new DViewHolder(itemView, holderContext);
        }

        @Override
        public String getType() {
            return "typeD";
        }
    }

}
