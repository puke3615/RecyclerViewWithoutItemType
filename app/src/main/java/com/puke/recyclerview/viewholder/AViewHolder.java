package com.puke.recyclerview.viewholder;

import android.support.annotation.NonNull;
import android.view.View;
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
public class AViewHolder extends BaseViewHolder implements View.OnClickListener {

    public static final Factory FACTORY = new DefaultFactory(
            AViewHolder.class, "typeA", R.layout.item_a
    );

    private final TextView textView;

    public AViewHolder(View itemView, @NonNull HolderContext holderContext) {
        super(itemView, holderContext);
        textView = itemView.findViewById(R.id.item_text_a);
        textView.setOnClickListener(this);
    }

    @Override
    public void onBindData(IType iType) {
        super.onBindData(iType);
        ItemData data = (ItemData) iType;
        textView.setText(data.textA);
    }

    @Override
    public void onClick(View v) {
        Toast.makeText(context, "Click Item A", Toast.LENGTH_SHORT).show();
        holderContext.onClickA();
    }

}
