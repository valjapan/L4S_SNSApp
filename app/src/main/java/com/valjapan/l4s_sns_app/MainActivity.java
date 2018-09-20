package com.valjapan.l4s_sns_app;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements CustomAdapter.OnLikeClickListener {
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference reference = database.getReference();

    public CustomAdapter mCustomAdapter;
    public ListView mListView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mListView = (ListView) findViewById(R.id.list_view);


        mCustomAdapter = new CustomAdapter(this, 0, new ArrayList<UserData>());
        mCustomAdapter.setOnLikeClickListener(this);
        mListView.setAdapter(mCustomAdapter);

        reference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                UserData userData = dataSnapshot.getValue(UserData.class);
                mCustomAdapter.add(userData);
                mCustomAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                UserData result = dataSnapshot.getValue(UserData.class);

                if (result == null) return;

                UserData userData = mCustomAdapter.getUserDataKey(result.getFireBaseKey());
                userData.setTitle(result.getTitle());
                userData.setContent(result.getContent());

                userData.setLikeNum(result.getLikeNum());
                mCustomAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    public void addButton(View v) {
        Intent intent = new Intent(this, AddActivity.class);
        startActivity(intent);
    }

    @Override
    public void onLikeClick(int position) {
        UserData userData = mCustomAdapter.getItem(position);
        if (userData == null) return;

        int likeCount = userData.getLikeNum();
        likeCount = likeCount + 1;

        userData.setLikeNum(likeCount);

        Map<String, Object> postValues = new HashMap<>();
        postValues.put("/" + userData.getFireBaseKey() + "/", userData);

        reference.updateChildren(postValues);

    }
}
