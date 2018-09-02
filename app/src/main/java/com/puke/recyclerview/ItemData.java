package com.puke.recyclerview;

import android.support.annotation.NonNull;

import com.puke.recyclerview.base.IType;

/**
 * @author puke
 * @version 2018/8/26
 */
public class ItemData implements IType {

    /**
     * typeA: A类型
     * typeB: B类型
     * typeC: C类型
     */
    public String type;

    public String textA;
    public String textB;
    public String textC;
    public String textD;
    public String textE;
    public boolean isVip;

    @NonNull
    @Override
    public String getType() {
        return type;
    }
}
