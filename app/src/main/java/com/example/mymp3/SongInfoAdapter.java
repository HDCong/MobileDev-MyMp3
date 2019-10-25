package com.example.mymp3;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

class SongInfoAdapter extends ArrayAdapter<SongInfo> {
    private Context context;
    private int layout;
    private ArrayList<SongInfo> songInfoArrayList;

    public SongInfoAdapter(@NonNull Context context, int resource, ArrayList<SongInfo> songInfoArrayList) {
        super(context,resource);
        this.context=context;
        this.layout=resource;
        this.songInfoArrayList = songInfoArrayList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = layoutInflater.inflate(layout, null);

        ImageView imageView = convertView.findViewById(R.id.imageViewSong);
        TextView textViewName = convertView.findViewById(R.id.textViewSongName);
        TextView textViewSinger = convertView.findViewById(R.id.textViewSinger);


        SongInfo item = songInfoArrayList.get(position);

        imageView.setImageResource(item.getmImageID());
        textViewName.setText(item.getmSongName());

        textViewSinger.setText(item.getmSinger());



        return convertView;
    }

    @Override
    public int getCount() {
        return songInfoArrayList.size();
    }
}
