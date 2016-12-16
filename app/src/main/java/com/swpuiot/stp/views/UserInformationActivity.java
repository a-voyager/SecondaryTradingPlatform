package com.swpuiot.stp.views;

import android.annotation.TargetApi;
import android.app.DatePickerDialog;
import android.content.ContentUris;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.support.annotation.StringRes;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.swpuiot.stp.R;
import com.swpuiot.stp.base.BaseActivity;
import com.swpuiot.stp.base.BaseApplication;
import com.swpuiot.stp.entities.ResponseEntity;
import com.swpuiot.stp.injector.component.ActivityComponent;
import com.swpuiot.stp.injector.component.DaggerActivityComponent;
import com.swpuiot.stp.injector.module.ActivityModule;
import com.swpuiot.stp.interfaces.ISettingView;
import com.swpuiot.stp.interfaces.IUserInformationView;
import com.swpuiot.stp.presenter.impl.SettingPresenter;
import com.swpuiot.stp.presenter.impl.UserInformationPresenter;
import com.swpuiot.stp.utils.SnackBarUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.BreakIterator;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.inject.Inject;

import butterknife.BindView;

public class UserInformationActivity extends BaseActivity implements IUserInformationView {
    @Inject
    UserInformationPresenter mUserInformationPresenter;
    @BindView(R.id.cl_user_information)
    CoordinatorLayout mClUserInformation;
    public static final int TAKE_PHOTO=1;
    public static final int CROP_PHOTO=2;
    private static final int CHOSE_PHOTO=3;
    private LinearLayout llAge;
    private TextView tvAge;
    private LinearLayout llchangesex;
    private LinearLayout llchangenickname;
    private LinearLayout llChangeImage;
    private DatePickerDialog datePickerDialog;
    private Calendar objTime;
    private TextView tv_sex;
    private int iYear;
    private int iMonth;
    private int iDay;
    private TextView tv_nickname_error;
    private TextView tv_nickname;
    private ImageView userImage;
    private Uri imageUri;
    @Override
    protected void initializePresenter() {
        mUserInformationPresenter.attachView(this);

    }

    @Override
    public void initializeInjector() {
        ActivityComponent component = DaggerActivityComponent
                .builder()
                .appComponent(((BaseApplication) getApplication())
                        .getAppComponent())
                .activityModule(new ActivityModule(this))
                .build();
        component.inject(this);


    }

    @Override
    public int getLayoutResID() {
        return R.layout.activity_user_information;
    }

    @Override
    public void initViews(Bundle savedInstanceState) {
        ResponseEntity responseEntity = MainActivity.getResponseEntity();
        llAge = (LinearLayout) findViewById(R.id.ll_userinformation_age);
        tv_nickname = (TextView) findViewById(R.id.tv_nickname);
        tv_sex = (TextView) findViewById(R.id.tv_sex);
        llchangenickname = (LinearLayout) findViewById(R.id.ll_change_nickname);
        llchangesex = (LinearLayout) findViewById(R.id.ll_change_sex);
        tvAge = (TextView) findViewById(R.id.tv_userinformation_age);
        llChangeImage= (LinearLayout) findViewById(R.id.ll_change_image);
        tv_nickname.setText(responseEntity.getNickname());
        userImage = (ImageView) findViewById(R.id.iv_user_image);
        llAge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mUserInformationPresenter.llUserInformationAgeOnClick();
            }
        });
        llchangenickname.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mUserInformationPresenter.llUserInformationNickNameOnClick();
            }
        });
        llchangesex.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mUserInformationPresenter.lluserInformationSexOnClick();
            }
        });
        llChangeImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mUserInformationPresenter.llUserInformationImageOnclick();
            }
        });
    }

    @Override
    public void showSnackBarMsg(@StringRes int msg) {
        SnackBarUtils.show(mClUserInformation, msg);
    }

    @Override
    public void showSnackBarMsg(String msg) {
        SnackBarUtils.show(mClUserInformation, msg);
    }


    @Override
    public void starUserInformationAge() {
        DatePickerDialog.OnDateSetListener DatePickerListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                objTime = Calendar.getInstance();
                iYear = objTime.get(Calendar.YEAR);
                iMonth = objTime.get(Calendar.MONTH);
                iDay = objTime.get(Calendar.DAY_OF_MONTH);
                if (iMonth > monthOfYear) {
                    tvAge.setText(iYear - year + "");
                } else if (iMonth < monthOfYear) {
                    tvAge.setText(iYear - year - 1 + "");
                } else {
                    if (iDay >= dayOfMonth) {
                        tvAge.setText(iYear - year + "");
                    } else {
                        tvAge.setText(iYear - year - 1 + "");
                    }
                }

            }
        };
        datePickerDialog = new DatePickerDialog(UserInformationActivity.this, DatePickerListener, 2000, 1, 1);
        datePickerDialog.setTitle("请选择出生日期");
        datePickerDialog.show();
    }

    @Override
    public void changeUserSex() {
        AlertDialog alertDialog = new AlertDialog.Builder(UserInformationActivity.this).create();
        alertDialog.setView(LayoutInflater.from(this).inflate(R.layout.dialog_change_sex_layout, null));
        alertDialog.show();
        Window window = alertDialog.getWindow();
        RadioGroup radioGroup = (RadioGroup) window.findViewById(R.id.rg_change_sex);
        if (tv_sex.getText().toString().equals("男"))
            radioGroup.check(R.id.rb_man);
        if (tv_sex.getText().toString().equals("女"))
            radioGroup.check(R.id.rb_woman);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i) {
                    case R.id.rb_woman:
                        tv_sex.setText("女");
                        alertDialog.dismiss();
                        break;
                    case R.id.rb_man:
                        tv_sex.setText("男");
                        alertDialog.dismiss();
                        break;
                }
            }
        });
        showSnackBarMsg("修改成功");
    }

    @Override
    public void changeUserNickname() {
        AlertDialog alertDialog = new AlertDialog.Builder(this).create();
        alertDialog.setView(LayoutInflater.from(this).inflate(R.layout.dialog_change_nickname_layout, null));
        alertDialog.show();
        Window window = alertDialog.getWindow();
        EditText et_change_nickname = (EditText) window.findViewById(R.id.et_changed_nickname);
        Button btn_sure = (Button) window.findViewById(R.id.btn_dialog_sure);
        tv_nickname_error = (TextView) window.findViewById(R.id.tv_dialog_nickname_error);
        Button btn_cancel = (Button) window.findViewById(R.id.btn_dialog_cancled);
        btn_sure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (et_change_nickname.getText().toString().isEmpty())
                    tv_nickname_error.setText("昵称不能为空");
                else {
                    tv_nickname.setText(et_change_nickname.getText().toString().trim());
                    showSnackBarMsg("修改成功");
                    alertDialog.dismiss();
                }
            }
        });
        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.cancel();
            }
        });
    }

    @Override
    public void changeUserImage() {
        AlertDialog.Builder dialog=new AlertDialog.Builder(UserInformationActivity.this);
        dialog.setTitle("头像");
        dialog.setMessage("头像来源");
        dialog.setPositiveButton("拍照", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                File outputImage = new File(Environment.getExternalStorageDirectory(), "output_image.jpg");
                try {
                    if (outputImage.exists()) {
                        outputImage.delete();
                    }
                    outputImage.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                imageUri = Uri.fromFile(outputImage);
                Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
                intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
                startActivityForResult(intent, TAKE_PHOTO);
            }
        });
        dialog.setNegativeButton("相册", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent=new Intent("android.intent.action.GET_CONTENT");
                intent.setType("image/*");
                startActivityForResult(intent,CHOSE_PHOTO);
            }
        });
        dialog.show();
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode){
            case TAKE_PHOTO:
                if(resultCode==RESULT_OK){
                    Intent intent=new Intent("com.android.camera.action.CROP");
                    intent.setDataAndType(imageUri,"image/*");
                    intent.putExtra("scale", true);
                    intent.putExtra(MediaStore.EXTRA_OUTPUT,imageUri);
                    startActivityForResult(intent, CROP_PHOTO);
                }
                break;
            case CROP_PHOTO:
                if(resultCode==RESULT_OK){
                    try{
                        Bitmap bitmap= BitmapFactory.decodeStream(getContentResolver().openInputStream(imageUri));
                        userImage.setImageBitmap(bitmap);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }

                }
                break;
            case CHOSE_PHOTO:
                if(resultCode==RESULT_OK){
                    if(Build.VERSION.SDK_INT>=19){
                        handleImageOnKitKat(data);
                    }
                    else{
                        handleImageBeforeKItKat(data);
                    }
                }
                break;
            default:
                break;
        }
    }

    @TargetApi(19)
    private void handleImageOnKitKat(Intent data) {
        String imagePath=null;
        Uri uri=data.getData();
        if(DocumentsContract.isDocumentUri(this, uri)){
            String docId=DocumentsContract.getDocumentId(uri);
            if("com.android.providers.media.documents".equals(uri.getAuthority())){
                String id=docId.split(":")[1];
                String selection=MediaStore.Images.Media._ID+"="+id;
                imagePath=getImagePath(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,selection);
            }
            else if("com.android.providers.downloads.documents".equals(uri.getAuthority())){
                Uri contentUri= ContentUris.withAppendedId(Uri.parse("content://downlads/public_downloads"), Long.valueOf(docId));
                imagePath=getImagePath(contentUri,null);
            }
        }
        else if("content".equalsIgnoreCase(uri.getScheme())){
            imagePath=getImagePath(uri,null);
        }
        displayImage(imagePath);
    }
    private void handleImageBeforeKItKat(Intent data) {
        Uri uri=data.getData();
        String imagePath=getImagePath(uri, null);
        displayImage(imagePath);
    }
    private String getImagePath(Uri uri,String selection) {
        String path=null;
        Cursor cursor=getContentResolver().query(uri, null, selection, null, null);
        if(cursor!=null) {
            if (cursor.moveToFirst()) {
                path = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.DATA));
            }
            cursor.close();
        }
        return path;
    }
    private void displayImage(String imagePath) {
        if(imagePath!=null){
            Bitmap bitmap=BitmapFactory.decodeFile(imagePath);
            userImage.setImageBitmap(bitmap);
        }
        else {
            Toast.makeText(this,"得不到图片",Toast.LENGTH_SHORT).show();
        }
    }

}
