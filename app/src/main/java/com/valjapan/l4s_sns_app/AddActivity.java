package com.valjapan.l4s_sns_app;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddActivity extends AppCompatActivity {

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference reference = database.getReference();

    EditText titleEditText, contentEditText;
    RelativeLayout mFocusView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        titleEditText = (EditText) findViewById(R.id.title);
        contentEditText = (EditText) findViewById(R.id.content);


        mFocusView = (RelativeLayout) findViewById(R.id.relative_layout);

    }

    public void save(View v) {
        String title = titleEditText.getText().toString();
        String context = contentEditText.getText().toString();
        String key = reference.push().getKey();

//    引数のUserDataの内容をデータベースに送る。
        UserData userData = new UserData(key, title, context, 0);

        reference.child(key).setValue(userData).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void v) {
                finish();
            }
        });
    }
}
