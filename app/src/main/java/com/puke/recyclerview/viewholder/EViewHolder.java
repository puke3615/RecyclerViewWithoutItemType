package com.puke.recyclerview.viewholder;

import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.puke.recyclerview.base.BaseViewHolder;
import com.puke.recyclerview.base.HolderContext;
import com.puke.recyclerview.base.IType;
import com.puke.recyclerview.ItemData;
import com.puke.recyclerview.R;

/**
 * @author puke
 * @version 2018/9/2
 */
public class EViewHolder extends BaseViewHolder implements View.OnClickListener {

    private final TextView textView;

    public EViewHolder(View itemView, HolderContext holderContext) {
        super(itemView, holderContext);
        textView = itemView.findViewById(R.id.item_text_e);
        textView.setOnClickListener(this);
    }

    @Override
    public void onBindData(IType iType) {
        ItemData data = (ItemData) iType;
        textView.setText(data.textE);
    }

    @Override
    public void onClick(View v) {
        Toast.makeText(context, "Click Item E", Toast.LENGTH_SHORT).show();
    }

    public static class Factory implements BaseViewHolder.Factory {

        @NonNull
        @Override
        public BaseViewHolder onCreateViewHolder(@NonNull HolderContext holderContext, @NonNull ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            View view = inflater.inflate(R.layout.item_e, parent, false);
            return new EViewHolder(view, holderContext);
        }

        @Override
        public String getType() {
            return "typeE";
        }
    }

}
