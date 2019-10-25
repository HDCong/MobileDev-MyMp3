package com.example.mymp3;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ArrayList<SongInfo> songInfoArrayList;
    SongInfoAdapter songInfoAdapter;
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        songInfoArrayList = initListView();
        listView = this.findViewById(R.id.listViewMain);
        createListView();
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(MainActivity.this,Main2Activy.class);

                intent.putExtra("imageid",Integer.toString(songInfoArrayList.get(i).getmImageID()));
                intent.putExtra("song",songInfoArrayList.get(i).getmSongName().toString());
                intent.putExtra("singer",songInfoArrayList.get(i).getmSinger().toString());
                intent.putExtra("resource",songInfoArrayList.get(i).getmSongID().toString());
                MainActivity.this.startActivity(intent);
            }
        });
    }

    private void createListView() {
        songInfoAdapter = new SongInfoAdapter(this.getBaseContext(),R.layout.song_info,songInfoArrayList);
        listView.setAdapter(songInfoAdapter);
    }

    private ArrayList<SongInfo> initListView() {
        ArrayList<SongInfo> result = new ArrayList<SongInfo>();

        result.add(new SongInfo("Message In Rouge (Kiki'ss Delivery Servic)","Unknow", R.drawable.cat,"messageinrougekikisdeliveryservic"));
        result.add(new SongInfo("Always some one","Unknow", R.drawable.catone,"alwayssomeonels"));
        result.add(new SongInfo("The Path Of Wind","Unknow", R.drawable.cattwo, "thepathofwindmyneighbortotoro"));
        result.add(new SongInfo("Terus Song From Tales From Earthsea","CarlOrrjePianoEnsemble",R.drawable.cateight,"terussongfromtalesfromearthsea"));
        result.add(new SongInfo("Old Town Roap3","Unknow", R.drawable.catfour, "oldtownroad"));
        result.add(new SongInfo("I'll Be Fine","Unknow", R.drawable.cat, "illbefine"));
        result.add(new SongInfo("Atown With an Ocean view ","Unknow", R.drawable.cat,"atownwithanoceanview"));
        result.add(new SongInfo("My Neighbor Totoro Theme Song","JoeHisa", R.drawable.cateight,"myneighbortotorothemesong"));

        return result;

    }

}
