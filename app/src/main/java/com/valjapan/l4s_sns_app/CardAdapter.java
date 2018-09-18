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

public class CardAdapter extends ArrayAdapter<UserData> {
    List<UserData> mCards;

    public CardAdapter(Context context, int layoutResourceId, List<UserData> objects) {
        super(context, layoutResourceId, objects);

        mCards = objects;
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

    public static class ViewHolder {
        TextView titleTextView;
        TextView contentTextView;
        TextView likeTextView;
        ImageView likeButton;

        public ViewHolder(View view) {
            titleTextView = (TextView) view.findViewById(R.id.title_text_view);
            contentTextView = (TextView) view.findViewById(R.id.content_text_view);
            likeButton = (ImageView) view.findViewById(R.id.like_button);
            likeTextView = (TextView) view.findViewById(R.id.like_text_view);
        }

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        final ViewHolder viewHolder;

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.card, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        final UserData item = getItem(position);

        if (item != null) {
//            set data
            viewHolder.titleTextView.setText(item.title);
            viewHolder.contentTextView.setText(item.content);
            viewHolder.likeButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    item.likeNum++;
                    viewHolder.likeTextView.setText(String.valueOf(item.likeNum));
                }
            });
        }

        return convertView;
    }
}
