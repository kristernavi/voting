
/*
* Copyrights (R) Arun sudharsan 2017
* Developer name: Arun sudharsan
* Developed App: CSE Elections 2k17
* Date: 12/07/17
* */
package com.example.balilihan.election2017;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class TeacherVotesLegend extends AppCompatActivity {

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_votes_legend);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}
