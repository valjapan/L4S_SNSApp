package com.valjapan.l4s_sns_app;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;

public class AddActivity extends AppCompatActivity {

    EditText titleEditText, contentEditText;
    RelativeLayout mFocusView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        mFocusView = (RelativeLayout) findViewById(R.id.relative_layout);

        contentEditText = new EditText(this);
        contentEditText.setOnFocusChangeListener(new View.OnFocusChangeListener() {

            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    contentEditText.setBackgroundResource(R.drawable.edit_text_background);
                    Log.d("SetBackGround", "赤チェンしました");
                } else {
                    contentEditText.setBackgroundResource(R.drawable.edit_text_background_white);
                    Log.d("SetBackGround", "黒チェンしました");
                }
            }
        });

    }

    public void save(View v) {
        finish();
    }
}
