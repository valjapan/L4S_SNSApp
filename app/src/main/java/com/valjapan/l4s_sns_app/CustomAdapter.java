package com.valjapan.l4s_sns_app;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class CustomAdapter extends ArrayAdapter<UserData> {
    private List<UserData> mCards;
    private OnLikeClickListener likeClickListener = null;


    public CustomAdapter(Context context, int layoutResourceId, List<UserData> userData) {
        super(context, layoutResourceId, userData);

        this.mCards = userData;
    }

    @Override
    public int getCount() {
        return mCards.size();
    }

    @Nullable
    @Override
    public UserData getItem(int position) {
        return mCards.get(position);
    }


    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        final ViewHolder viewHolder;

        if (convertView != null) {
            viewHolder = (ViewHolder) convertView.getTag();

        } else {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.card, null);
            viewHolder = new ViewHolder();

            viewHolder.titleTextView = (TextView) convertView.findViewById(R.id.title_text_view);
            viewHolder.contentTextView = (TextView) convertView.findViewById(R.id.content_text_view);
            viewHolder.likeButton = (ImageView) convertView.findViewById(R.id.like_button);
            viewHolder.likeTextView = (TextView) convertView.findViewById(R.id.like_text_view);

            convertView.setTag(viewHolder);
        }

        UserData userData = mCards.get(position);

        viewHolder.titleTextView.setText(userData.getTitle());
        viewHolder.contentTextView.setText(userData.getContent());
        viewHolder.likeTextView.setText(String.valueOf(userData.getLikeNum()));
        viewHolder.likeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (likeClickListener != null) {
                    likeClickListener.onLikeClick(position);
                }
            }
        });

        return convertView;
    }

    public UserData getUserDataKey(String key) {
        for (UserData userData : mCards) {
            if (userData.getFireBaseKey().equals(key)) {
                return userData;
            }
        }

        return null;
    }

    public void setOnLikeClickListener(OnLikeClickListener likeClickListener) {
        this.likeClickListener = likeClickListener;
    }


    static class ViewHolder {
        TextView titleTextView;
        TextView contentTextView;
        TextView likeTextView;
        ImageView likeButton;
    }

    interface OnLikeClickListener {
        void onLikeClick(int position);
    }

}
