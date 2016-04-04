package com.joyin.volleydemo.view.dialog;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.joyin.volleydemo.R;

/**
 * Created by joyin on 16-4-4.
 */
public class MessageDialog extends BaseDialog {

    private TextView mTvMessage;

    public MessageDialog(Context context) {
        super(context);
    }

    public void setMessage(String message) {
        mTvMessage.setText(message);
    }

    @Override
    protected View getDefaultView(Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_message, null);
        mTvMessage = (TextView) view.findViewById(R.id.tv_dialog_message);

        TextView tvOk = (TextView) view.findViewById(R.id.tv_dialog_ok);
        tvOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
                if (mOnClickListener != null) {
                    mOnClickListener.onClick(v);
                }
            }
        });
        return view;
    }

    private View.OnClickListener mOnClickListener;

    public void setOnClickListener(View.OnClickListener listener) {
        this.mOnClickListener = listener;
    }
}
