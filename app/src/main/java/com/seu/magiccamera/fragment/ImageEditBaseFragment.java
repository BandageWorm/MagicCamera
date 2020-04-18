package com.seu.magiccamera.fragment;

import android.content.Context;
import android.content.DialogInterface;

import com.seu.magicfilter.MagicEngine;

import androidx.fragment.app.Fragment;

public abstract class ImageEditBaseFragment extends Fragment {

    protected Context mContext;
    protected onHideListener mOnHideListener;
    protected MagicEngine mEngine;


    public ImageEditBaseFragment(Context context) {
        super();
        this.mContext = context;
    }

    public void onHide() {
        if (!isChanged()) {
            mOnHideListener.onHide();
        }
    }

    public void setOnHideListener(onHideListener l) {
        this.mOnHideListener = l;
    }

    protected abstract boolean isChanged();

    protected void onDialogButtonClick(DialogInterface dialog) {
		if (mOnHideListener != null) {
			mOnHideListener.onHide();
		}
        dialog.dismiss();
    }

    public interface onHideListener {

        void onHide();
    }
}
