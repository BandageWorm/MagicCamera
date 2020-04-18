package com.seu.magiccamera.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;

import com.seu.magiccamera.R;
import com.seu.magiccamera.fragment.ImageEditAddFragment;
import com.seu.magiccamera.fragment.ImageEditAdjustFragment;
import com.seu.magiccamera.fragment.ImageEditBaseFragment;
import com.seu.magiccamera.fragment.ImageEditBeautyFragment;
import com.seu.magiccamera.fragment.ImageEditFilterFragment;
import com.seu.magiccamera.fragment.ImageEditFrameFragment;
import com.seu.magicfilter.MagicEngine;
import com.seu.magicfilter.widget.MagicImageView;

import java.io.InputStream;
import java.net.URL;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;


public class ImageActivity extends FragmentActivity {

    private RadioGroup mRadioGroup;
    private MagicEngine mMagicEngine;
    private MagicImageView mMagicV;
    private Fragment mFragment;

    private int mFragmentTag = -1;

    private final int REQUEST_PICK_IMAGE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);
        initMagicPreview();
        initRadioButtons();

        findViewById(R.id.image_edit_back).setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {

            }
        });
        findViewById(R.id.image_edit_save).setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
//				mMagicImageDisplay.savePicture(CameraActivity.getOutputMediaFile(), null);
            }
        });
    }

    private void initRadioButtons() {
        mRadioGroup = findViewById(R.id.image_edit_radiogroup);
        final Context context = this;
        mRadioGroup.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.image_edit_adjust:
                        mFragment = new ImageEditAdjustFragment(context, mMagicEngine);
                        break;
                    case R.id.image_edit_filter:
                        mFragment = new ImageEditFilterFragment(context, mMagicEngine);
                        break;
                    case R.id.image_edit_frame:
                        mFragment = new ImageEditFrameFragment(context);
                        break;
                    case R.id.image_edit_adds:
                        mFragment = new ImageEditAddFragment(context);
                        break;
                    case R.id.image_edit_beauty:
                        mFragment = new ImageEditBeautyFragment(context, mMagicEngine);
                        break;
                    default:
                        if (mFragment != null) {
                            getSupportFragmentManager().beginTransaction().hide(mFragment).commit();
                        }
                        break;
                }

                if (mFragment != null) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.edit_container, mFragment).commit();
                }
            }
        });

    }

    private void hideFragment() {
        ((ImageEditBaseFragment) mFragment).onHide();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        switch (keyCode) {
            case KeyEvent.KEYCODE_BACK:
                if (mFragmentTag != -1) {
                    hideFragment();
                    return true;
                }
                break;
            default:
                break;
        }
        return super.onKeyDown(keyCode, event);
    }

    private void initMagicPreview() {
        mMagicV = findViewById(R.id.glsurfaceview_image);
        mMagicEngine = new MagicEngine.Builder().build(mMagicV);

        Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
        photoPickerIntent.setType("image/*");
        startActivityForResult(photoPickerIntent, REQUEST_PICK_IMAGE);
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onActivityResult(final int requestCode, final int resultCode, final Intent data) {
        switch (requestCode) {
            case REQUEST_PICK_IMAGE:
                if (resultCode == RESULT_OK) {
                    try {
                        Uri mUri = data.getData();
                        InputStream inputStream;
                        if (mUri.getScheme().startsWith("http") || mUri.getScheme().startsWith("https")) {
                            inputStream = new URL(mUri.toString()).openStream();
                        } else {
                            inputStream = ImageActivity.this.getContentResolver().openInputStream(mUri);
                        }
                        Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                        mMagicV.setImageBitmap(bitmap);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                } else {
                    finish();
                }
                break;

            default:
                super.onActivityResult(requestCode, resultCode, data);
                break;
        }
    }

    private ImageEditBaseFragment.onHideListener mOnHideListener = new ImageEditBaseFragment.onHideListener() {

        @Override
        public void onHide() {
            mRadioGroup.check(View.NO_ID);
        }
    };
}
