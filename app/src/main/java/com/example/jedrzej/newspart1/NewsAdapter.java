package com.example.jedrzej.newspart1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class NewsAdapter extends ArrayAdapter<News> {

    public NewsAdapter (Context context){
        super(context, -1, new ArrayList<News>());
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }

        // get the News object located at this position in the list
        News currentNews = getItem(position);

        // find TextViews in list_item layout
        TextView titleTextView = convertView.findViewById(R.id.title);
        TextView authorTextView = convertView.findViewById(R.id.author);
        TextView dateTextView = convertView.findViewById(R.id.date);
        TextView categoryTextView = convertView.findViewById(R.id.category);

        // get data from currentAttraction object and setText
        titleTextView.setText(currentNews.getTitle());
        authorTextView.setText(currentNews.getAuthor());
        dateTextView.setText(currentNews.getDate());
        categoryTextView.setText(currentNews.getCategory());

        return convertView;
    }
}
