package com.seu.magiccamera.fragment;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.RelativeLayout;
import android.widget.SeekBar;

import com.seu.magiccamera.R;
import com.seu.magiccamera.widget.BubbleSeekBar;
import com.seu.magiccamera.widget.TwoLineSeekBar;
import com.seu.magicfilter.MagicEngine;

public class ImageEditBeautyFragment extends ImageEditBaseFragment {

    private RadioGroup mRadioGroup;
    private RelativeLayout mSkinSmoothView;
    private RelativeLayout mSkinColorView;
    private BubbleSeekBar mSmoothBubbleSeekBar;
    private BubbleSeekBar mWhiteBubbleSeekBar;
    private boolean mIsSmoothed = false;
    private boolean mIsWhiten = false;

    public ImageEditBeautyFragment(Context context, MagicEngine engine) {
        super(context);
        mEngine = engine;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_image_edit_beauty, container, false);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		mSkinSmoothView = (RelativeLayout) getView().findViewById(R.id.fragment_beauty_skin);
        mSkinColorView = (RelativeLayout) getView().findViewById(R.id.fragment_beauty_color);
        mRadioGroup = (RadioGroup) getView().findViewById(R.id.fragment_beauty_radiogroup);
        mRadioGroup.setOnCheckedChangeListener(new OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.fragment_beauty_btn_skinsmooth:
                        mSkinSmoothView.setVisibility(View.VISIBLE);
                        mSkinColorView.setVisibility(View.GONE);
                        break;
                    case R.id.fragment_beauty_btn_skincolor:
                        mSkinColorView.setVisibility(View.VISIBLE);
                        mSkinSmoothView.setVisibility(View.GONE);
                        break;
                    default:
                        break;
                }
            }
        });
        mSmoothBubbleSeekBar = view.findViewById(R.id.fragment_beauty_skin_seekbar);
        mWhiteBubbleSeekBar = view.findViewById(R.id.fragment_beauty_white_seekbar);
        init();
    }

    private float smoothlevel = -1;
    private void init() {
    	mSmoothBubbleSeekBar.setOnBubbleSeekBarChangeListener(new BubbleSeekBar.OnBubbleSeekBarChangeListener() {
			@Override
			public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
				float newlevel = ((float) progress) / 100f;
				if (newlevel != smoothlevel) {
					Log.w("BeautifyEdit", "onSmoothProgressChanged:" + progress + ", level:" + newlevel);
					mEngine.setSmoothSkin(newlevel);
                    smoothlevel = newlevel;
				}
			}

			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {

			}

			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {

			}
		});

        mWhiteBubbleSeekBar.setOnBubbleSeekBarChangeListener(new BubbleSeekBar.OnBubbleSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                Log.w("BeautifyEdit", "onWhiteProgressChanged:" + progress);
                mEngine.setSmoothSkin(((float) progress) / 100f);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    @Override
    protected boolean isChanged() {
        return mIsWhiten || mIsSmoothed;
    }
}
