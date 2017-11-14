package com.yanxing.networklibrary.dialog;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yanxing.networklibrary.R;

/**
 * 进度条
 * Created by 李双祥 on 2017/5/24.
 */
public class LoadDialog extends BaseDialog {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NO_TITLE, R.style.dialog_style);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.load_dialog, container);
        TextView tipTxt= (TextView) view.findViewById(R.id.progress_text);
        Bundle bundle=getArguments();
        if (bundle!=null){
            String tip=bundle.getString("tip");
            if (tip!=null){
                tipTxt.setText(tip);
            }
        }
        getDialog().setCanceledOnTouchOutside(false);
        return view;
    }

    @Override
    public int show(FragmentTransaction transaction, String tag) {
        transaction.add(this,tag);
        return transaction.commitAllowingStateLoss();
    }
}
