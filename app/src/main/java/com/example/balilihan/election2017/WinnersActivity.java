
/*
* Copyrights (R) Arun sudharsan 2017
* Developer name: Arun sudharsan
* Developed App: CSE Elections 2k17
* Date: 12/07/17
* */
package com.example.balilihan.election2017;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;


public class WinnersActivity extends AppCompatActivity {

    private TextView tv_totalvotescount, tv_presidentname, tv_presidentwincount;
    private TextView tv_vicepresidentname, tv_vicepresidentwincount;
    private TextView tv_treasurername, tv_treasurerwincount;
    private TextView tv_secretaryname, tv_secretarywincount;
    private TextView tv_jointsecretaryname, tv_jointsecretarywincount;

    private ImageView president, vicepresident, treasurer, secretary, jointsecretary;
    private int tempp1, tempp2, tempvp1, tempvp2, tempt1, tempt2, temps1, temps2, tempjs1, tempjs2, totalvotes;
    int dummy = 0;

    @Override
    public void onBackPressed() {
        finish();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_winners);
        init();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        readdata();

        computewinners();
    }

    private void computewinners() {
//calculating winners........

        tv_totalvotescount.setText(String.valueOf(totalvotes));

        //a for 1 b for 2 percentage =a/tv*100;

        //president
        if (tempp1 == tempp2) {
            tv_presidentname.setText(getString(R.string.Winner) + getString(R.string.draw));
            dummy = 0;
            tv_presidentwincount.setText(getString(R.string.Winsby) + String.valueOf(dummy));


        } else if (tempp1 > tempp2) {

            tv_presidentname.setText(getString(R.string.Winner) + getString(R.string.presidentname1cv));
            dummy = tempp1 - tempp2;
            tv_presidentwincount.setText(getString(R.string.Winsby) + String.valueOf(dummy));
            president.setImageResource(R.drawable.placeholder);
        } else {

            tv_presidentname.setText(getString(R.string.Winner) + getString(R.string.presidentname2cv));
            dummy = tempp2 - tempp1;
            tv_presidentwincount.setText(getString(R.string.Winsby) + String.valueOf(dummy));
            president.setImageResource(R.drawable.placeholder);
        }

//vice president


        if (tempvp1 == tempvp2) {
            //  a = tempvp1;
            //b = tempvp2;

            tv_vicepresidentname.setText(getString(R.string.Winner) + getString(R.string.draw));
            dummy = 0;
            tv_vicepresidentwincount.setText(getString(R.string.Winsby) + String.valueOf(dummy));

        } else if (tempvp1 > tempvp2) {
            tv_vicepresidentname.setText(getString(R.string.Winner) + getString(R.string.vpname1cv));
            dummy = tempvp1 - tempvp2;
            tv_vicepresidentwincount.setText(getString(R.string.Winsby) + String.valueOf(dummy));

            vicepresident.setImageResource(R.drawable.placeholder);

        } else {

            tv_vicepresidentname.setText(getString(R.string.Winner) + getString(R.string.vpname2cv));
            dummy = tempvp2 - tempvp1;
            tv_vicepresidentwincount.setText(getString(R.string.Winsby) + String.valueOf(dummy));

            vicepresident.setImageResource(R.drawable.placeholder);

        }

        //Treasurer

        if (tempt1 == tempt2) {

            tv_treasurername.setText(getString(R.string.Winner) + getString(R.string.draw));
            dummy = 0;
            tv_treasurerwincount.setText(getString(R.string.Winsby) + String.valueOf(dummy));
        } else if (tempt1 > tempt2) {

            tv_treasurername.setText(getString(R.string.Winner) + getString(R.string.treasurername1cv));
            dummy = tempt1 - tempt2;
            tv_treasurerwincount.setText(getString(R.string.Winsby) + String.valueOf(dummy));
            treasurer.setImageResource(R.drawable.placeholder);
        } else {

            tv_treasurername.setText(getString(R.string.Winner) + getString(R.string.treasurername2cv));
            dummy = tempjs2 - tempjs1;
            tv_treasurerwincount.setText(getString(R.string.Winsby) + String.valueOf(dummy));
            treasurer.setImageResource(R.drawable.placeholder);

        }

        //secretary

        if (temps1 == temps2) {
            tv_secretaryname.setText(getString(R.string.Winner) + getString(R.string.draw));
            dummy = 0;
            tv_secretarywincount.setText(getString(R.string.Winsby) + String.valueOf(dummy));
        } else if (temps1 > temps2) {

            tv_secretaryname.setText(getString(R.string.Winner) + getString(R.string.secretary_name1cv));
            dummy = temps1 - temps2;
            tv_secretarywincount.setText(getString(R.string.Winsby) + String.valueOf(dummy));
            secretary.setImageResource(R.drawable.placeholder);

        } else {


            tv_secretaryname.setText(getString(R.string.Winner) + getString(R.string.secretary_name2cv));
            dummy = temps2 - temps1;
            tv_secretarywincount.setText(getString(R.string.Winsby) + String.valueOf(dummy));
            secretary.setImageResource(R.drawable.placeholder);

        }

        //joint secretary

        if (tempjs1 == tempjs2) {
            tv_jointsecretaryname.setText(getString(R.string.Winner) + getString(R.string.draw));
            dummy = 0;
            tv_jointsecretarywincount.setText(getString(R.string.Winsby) + String.valueOf(dummy));

        } else if (tempjs1 > tempjs2) {


            tv_jointsecretaryname.setText(getString(R.string.Winner) + getString(R.string.joint_secretary_name1cv));
            dummy = tempjs1 - tempjs2;
            tv_jointsecretarywincount.setText(getString(R.string.Winsby) + String.valueOf(dummy));
            jointsecretary.setImageResource(R.drawable.placeholder);

        } else {
            tv_jointsecretaryname.setText(getString(R.string.Winner) + getString(R.string.joint_secretary_name2cv));
            dummy = tempjs2 - tempjs1;
            tv_jointsecretarywincount.setText(getString(R.string.Winsby) + String.valueOf(dummy));
           jointsecretary.setImageResource(R.drawable.placeholder);

        }


    }

    private void readdata() {
        SharedPreferences pref = getSharedPreferences("MYDB", MODE_PRIVATE);

        tempp1 = pref.getInt("CP1", 0);
        tempp2 = pref.getInt("CP2", 0);

        tempvp1 = pref.getInt("CVP1", 0);
        tempvp2 = pref.getInt("CVP2", 0);

        tempt1 = pref.getInt("CT1", 0);

        tempt2 = pref.getInt("CT2", 0);

        temps1 = pref.getInt("CS1", 0);

        temps2 = pref.getInt("CS2", 0);

        tempjs1 = pref.getInt("CJS1", 0);

        tempjs2 = pref.getInt("CJS2", 0);

        totalvotes = pref.getInt("allvotesn", 0);
    }

    public void init() {


        tv_totalvotescount = (TextView) findViewById(R.id.tv_totalVotescount);
        tv_presidentname = (TextView) findViewById(R.id.tv_president);
        tv_presidentwincount = (TextView) findViewById(R.id.tv_presidentcount);


        tv_vicepresidentname = (TextView) findViewById(R.id.tv_vicepresident);
        tv_vicepresidentwincount = (TextView) findViewById(R.id.tv_vicepresidentcount);

        tv_treasurername = (TextView) findViewById(R.id.tv_treasurer);
        tv_treasurerwincount = (TextView) findViewById(R.id.tv_treasurercount);

        tv_secretaryname = (TextView) findViewById(R.id.tv_Secretary);
        tv_secretarywincount = (TextView) findViewById(R.id.tv_Secretarycount);

        tv_jointsecretaryname = (TextView) findViewById(R.id.tv_JointSecretary);
        tv_jointsecretarywincount = (TextView) findViewById(R.id.tv_JointSecretarycount);

        president = (ImageView) findViewById(R.id.presidentimage);
        vicepresident = (ImageView) findViewById(R.id.vpimage);
        treasurer = (ImageView) findViewById(R.id.treasurerimage);
        secretary = (ImageView) findViewById(R.id.secimage);
        jointsecretary = (ImageView) findViewById(R.id.jsimage);

    }
}
