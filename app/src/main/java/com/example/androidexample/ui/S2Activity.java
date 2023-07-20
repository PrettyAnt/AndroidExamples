package com.example.androidexample.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.androidexample.R;
import com.example.imageselector.utils.ImageSelector;

public class S2Activity extends BaseActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tree);
        Button btn_s2      = findViewById(R.id.btn_s2);
        Button btn_album = findViewById(R.id.btn_album);
        Button btn_takephoto = findViewById(R.id.btn_takephoto);
        btn_s2.setOnClickListener(this);
        btn_album.setOnClickListener(this);
        btn_takephoto.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_s2:
                Intent intent = new Intent(this, S3Activity.class);
                startActivity(intent);
                break;
            case R.id.btn_album:
                ImageSelector
                        .builder()
                        .useCamera(false) // 设置是否使用拍照
                        .setSingle(false)  //设置是否单选  注意:设置不是当选，才有最大值，最大值为1
                        .setMaxSelectCount(1)
                        .canPreview(false) //是否点击放大图片查看,，默认为true
                        .start(this,999);
                break;
            case R.id.btn_takephoto:
                ImageSelector
                        .builder()
                        .onlyTakePhoto(true) // 设置是否使用拍照
                        .start(this,888);
                break;
            default:
                break;
        }
    }
}