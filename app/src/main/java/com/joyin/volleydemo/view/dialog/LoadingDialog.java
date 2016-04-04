package com.joyin.volleydemo.view.dialog;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.joyin.volleydemo.R;

/**
 * Created by joyin on 16-4-4.
 */
public class LoadingDialog extends BaseDialog {

    public LoadingDialog(Context context) {
        super(context);
    }

    @Override
    protected View getDefaultView(Context context) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View v = inflater.inflate(R.layout.dialog_loading, null);

        ImageView icon = (ImageView) v.findViewById(R.id.icon_loading);
        Animation animation = AnimationUtils.loadAnimation(context, R.anim.loading_rotate);
        icon.startAnimation(animation);
        return v;
    }
}
