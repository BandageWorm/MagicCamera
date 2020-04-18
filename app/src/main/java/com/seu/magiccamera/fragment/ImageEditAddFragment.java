package com.seu.magiccamera.fragment;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.seu.magiccamera.R;

public class ImageEditAddFragment extends ImageEditBaseFragment {

	public ImageEditAddFragment(Context context) {
		super(context);
	}

	@Override
	protected boolean isChanged() {
		return false;
	}

	@Override
	protected void onDialogButtonClick(DialogInterface dialog) {
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_image_edit_adds, container, false);  
	}
}
