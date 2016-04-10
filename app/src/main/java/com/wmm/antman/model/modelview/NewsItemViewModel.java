package com.wmm.antman.model.modelview;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.facebook.drawee.view.SimpleDraweeView;
import com.wmm.antman.BR;

/**
 * Created by ming on 2016/4/7.
 */
public class NewsItemViewModel extends BaseObservable {
    private String txt;
    private SimpleDraweeView img;

    @Bindable
    public String getTxt() {
        return txt;
    }

    public void setTxt(String txt) {
        this.txt = txt;
        notifyPropertyChanged(BR.txt);
    }

    public SimpleDraweeView getImg() {
        return img;
    }

    public void setImg(SimpleDraweeView img) {
        this.img = img;
    }

}