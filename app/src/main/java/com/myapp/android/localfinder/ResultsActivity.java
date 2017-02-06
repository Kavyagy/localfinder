package com.myapp.android.localfinder;

import android.content.Intent;
import android.database.CursorJoiner;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;

import java.util.ArrayList;

public class ResultsActivity extends AppCompatActivity {
    ListView listview;
    ArrayList<Results> results;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);
        listview = (ListView) findViewById(R.id.listview);
        progressBar = (ProgressBar) findViewById(R.id.progressBar2);
        hideprogressbar();

        String searchterm = getIntent().getStringExtra("searchterm");
        String postalcode = getIntent().getStringExtra("postalcode");
        downloader ddownloder = new downloader(this);
        ddownloder.execute(searchterm,postalcode);

    }
    public void displayprogessbar(){
        progressBar.setVisibility(View.VISIBLE);

    }
    public void setProgressBarprogress(int progress){
        progressBar.setProgress(progress);
        if(progress == 100){
            hideprogressbar();
        }

    }
    public void hideprogressbar(){
        progressBar.setVisibility(View.GONE);

    }

    public void drawlistview(ArrayList<Results> resultsarray){
        results = new ArrayList<Results>();
        results = resultsarray;
        ResultsAdapter adapter = new ResultsAdapter(this, resultsarray);
        listview.setAdapter(adapter);
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {


            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Results result = results.get(position);
                Intent intent = new Intent(ResultsActivity.this, DetailsActivity.class);
                intent.putExtra("result", result);

                startActivity(intent);
            }

        });

    }
}
