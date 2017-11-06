
/*
* Copyrights (R) Arun sudharsan 2017
* Developer name: Arun sudharsan
* Developed App: CSE Elections 2k17
* Date: 12/07/17
* */
// 1- HOD 25 points
//
// 2-25 5 points
//
// 30-35 10 points

package com.example.balilihan.election2017;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.List;


public class SubmitVoteMain extends AppCompatActivity {

private Context context;
    //voted for president
    List<Long> Votespresident1 = new ArrayList<Long>();
    List<Long> Votespresident2 = new ArrayList<Long>();

    //voted for Vice president
    List<Long> VotesVicepresident1 = new ArrayList<Long>();
    List<Long> VotesVicepresident2 = new ArrayList<Long>();

    //voted for Treasurer
    List<Long> Votestreasurer1 = new ArrayList<Long>();
    List<Long> Votestreasurer2 = new ArrayList<Long>();

    //voted for Secretary
    List<Long> VotesSecretary1 = new ArrayList<Long>();
    List<Long> VotesSecretary2 = new ArrayList<Long>();

    //voted for Joint Secretary
    List<Long> VotesjointSecretary1 = new ArrayList<Long>();
    List<Long> VotesjointSecretary2 = new ArrayList<Long>();


    private String regnumberstring;
    private long regnumber;
    private View coordinatorLayout;
    private ArrayList<Long> VotedRegisterNumbers = new ArrayList<Long>();
    private EditText inputRegisternumber;
    private TextInputLayout inputLayoutRegisternumber;

    private RadioGroup rgpresident, rgvpresident, rgtreasurer, rgsec, rgjs;
    private RadioButton president1rb, president2rb, vpresident1rb, vpresident2rb, treasurer1rb, treasurer2rb, sec1rb, sec2rb, js1rb, js2rb;
    //Radio buttons for all

    private int voteP, voteVP, voteT, voteS, voteJS;
    private static int countPresident1 = 0, countPresident2 = 0, countVicePresident1 = 0, countVicePresident2 = 0, TCOFVOTES = 0;
    ;
    private static int countTreasurer1 = 0, countTreasurer2 = 0, countSecretary1 = 0, countSecretary2 = 0, countJointSecretary1 = 0, countJointSecretary2 = 0;
    private FloatingActionButton Submit;


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.Winners) {
            final EditText taskEditText = new EditText(this);
            AlertDialog dialog = new AlertDialog.Builder(this)
                    .setTitle("Check Results")
                    .setMessage("Enter Password:(9566)")
                    .setView(taskEditText)
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                          if(taskEditText.getText().toString().equals("9566"))
                          {

                              startActivity(new Intent(SubmitVoteMain.this, WinnersActivity.class));

                          }
                           }
                    })
                    .setNegativeButton("Cancel", null)
                    .create();
            dialog.show();

        } else if (id == R.id.ExtraVotes) {
            startActivity(new Intent(this, TeacherVotesLegend.class));

        } else if (id == R.id.About) {
            new AlertDialog.Builder(this)
                    .setTitle("Elections 2017")
                    .setMessage("Balilihan BISU.")
                    .setPositiveButton("OK", null).show();
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public boolean onSupportNavigateUp() {
       return true;
    }

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submit_vote_main);

        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        initvar();
        Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                main();
            }

        });

    }

    private void main() {

        boolean regtrue = getregnumber();
        if (!regtrue)
            return;
        //check for redundancy
        Boolean RegNumbercheck = validateRegisternumber(regnumber);


        if (RegNumbercheck) {


            radiobuttonnullcheck();


        } else {
            Snackbar snackbar = Snackbar
                    .make(coordinatorLayout, "Already Registered", Snackbar.LENGTH_LONG);

            snackbar.show();
            return;
        }

        //Toast.makeText(SubmitVoteMain.this,regnumber +" \n "+voteP+"\n"+voteVP+"\n"+voteT+"\n"+voteS+"\n"+voteJS  , Toast.LENGTH_SHORT).show();

    }

    private void radiobuttonnullcheck() {
        voteP = rgpresident.getCheckedRadioButtonId();
        voteVP = rgvpresident.getCheckedRadioButtonId();
        voteT = rgtreasurer.getCheckedRadioButtonId();
        voteS = rgsec.getCheckedRadioButtonId();
        voteJS = rgjs.getCheckedRadioButtonId();

        if (voteP == -1 || voteVP == -1 || voteT == -1 || voteS == -1 || voteJS == -1) {

            Snackbar snackbar = Snackbar
                    .make(coordinatorLayout, "Please select any one of the options", Snackbar.LENGTH_LONG);

            snackbar.show();
            // return;

        } else {
            votingfun(regnumber);

        }
    }

    private void votingfun(long regnumber) {

        SharedPreferences pref = getSharedPreferences("MYDB", MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();

        //RegNumber 1 HOD Anand sir
        if (regnumber==1) {

            if (president1rb.isChecked()) {
                countPresident1 += 10;
                Votespresident1.add(regnumber);
                editor.putInt("CP1", countPresident1);
                editor.apply();


            }
            if (president2rb.isChecked()) {
                countPresident2 += 10;
                Votespresident2.add(regnumber);
                editor.putInt("CP2", countPresident2);
                editor.apply();

            }
            if (vpresident1rb.isChecked()) {
                countVicePresident1 += 10;
                VotesVicepresident1.add(regnumber);
                editor.putInt("CVP1", countVicePresident1);
                editor.apply();

            }
            if (vpresident2rb.isChecked()) {
                countVicePresident2 += 10;
                VotesVicepresident2.add(regnumber);
                editor.putInt("CVP2", countVicePresident2);
                editor.apply();
            }

            if (treasurer1rb.isChecked()) {
                countTreasurer1 += 10;
                Votestreasurer1.add(regnumber);
                editor.putInt("CT1", countTreasurer1);
                editor.apply();
            }
            if (treasurer2rb.isChecked()) {
                countTreasurer2 += 10;
                Votestreasurer2.add(regnumber);

                editor.putInt("CT2", countTreasurer2);
                editor.apply();
            }

            if (sec1rb.isChecked()) {
                countSecretary1 += 10;
                VotesSecretary1.add(regnumber);

                editor.putInt("CS1", countSecretary1);
                editor.apply();
            }
            if (sec2rb.isChecked()) {
                countSecretary2 += 10;
                VotesSecretary2.add(regnumber);

                editor.putInt("CS2", countSecretary2);
                editor.apply();
            }

            if (js1rb.isChecked()) {
                countJointSecretary1 += 10;
                VotesjointSecretary1.add(regnumber);

                editor.putInt("CJS1", countJointSecretary1);
                editor.apply();
            }
            if (js2rb.isChecked()) {
                countJointSecretary2 += 10;

                VotesjointSecretary2.add(regnumber);

                editor.putInt("CJS2", countJointSecretary2);
                editor.apply();
            }


            VotedRegisterNumbers.add(regnumber);
            TCOFVOTES++;
            editor.putInt("allvotesn", TCOFVOTES);
            editor.apply();


            Snackbar snackbar = Snackbar
                    .make(coordinatorLayout, " Thank you Sir :) Vote Successfully Registered!", Snackbar.LENGTH_LONG);


            snackbar.show();
            cleareverything();

            return;
        }


//RegNumber 2-25 for Teaching staffs 5 points
         if (regnumber >= 2 && regnumber <= 50) {

            if (president1rb.isChecked()) {
                countPresident1 += 5;
                Votespresident1.add(regnumber);
                editor.putInt("CP1", countPresident1);
                editor.apply();


            }
            if (president2rb.isChecked()) {
                countPresident2 += 5;
                Votespresident2.add(regnumber);
                editor.putInt("CP2", countPresident2);
                editor.apply();

            }
            if (vpresident1rb.isChecked()) {
                countVicePresident1 += 5;
                VotesVicepresident1.add(regnumber);
                editor.putInt("CVP1", countVicePresident1);
                editor.apply();

            }
            if (vpresident2rb.isChecked()) {
                countVicePresident2 += 5;
                VotesVicepresident2.add(regnumber);
                editor.putInt("CVP2", countVicePresident2);
                editor.apply();
            }

            if (treasurer1rb.isChecked()) {
                countTreasurer1 += 5;
                Votestreasurer1.add(regnumber);
                editor.putInt("CT1", countTreasurer1);
                editor.apply();
            }
            if (treasurer2rb.isChecked()) {
                countTreasurer2 += 5;
                Votestreasurer2.add(regnumber);

                editor.putInt("CT2", countTreasurer2);
                editor.apply();
            }

            if (sec1rb.isChecked()) {
                countSecretary1 += 5;
                VotesSecretary1.add(regnumber);

                editor.putInt("CS1", countSecretary1);
                editor.apply();
            }
            if (sec2rb.isChecked()) {
                countSecretary2 += 5;
                VotesSecretary2.add(regnumber);

                editor.putInt("CS2", countSecretary2);
                editor.apply();
            }

            if (js1rb.isChecked()) {
                countJointSecretary1 += 5;
                VotesjointSecretary1.add(regnumber);

                editor.putInt("CJS1", countJointSecretary1);
                editor.apply();
            }
            if (js2rb.isChecked()) {
                countJointSecretary2 += 5;

                VotesjointSecretary2.add(regnumber);

                editor.putInt("CJS2", countJointSecretary2);
                editor.apply();
            }


            VotedRegisterNumbers.add(regnumber);
            TCOFVOTES += 1;
            editor.putInt("allvotesn", TCOFVOTES);
            editor.apply();


            Snackbar snackbar = Snackbar
                    .make(coordinatorLayout, "Teacher Vote Successfully Registered !", Snackbar.LENGTH_LONG);


            snackbar.show();
            cleareverything();

            return;
        }





        if (president1rb.isChecked()) {
            countPresident1++;
            Votespresident1.add(regnumber);
            editor.putInt("CP1", countPresident1);
            editor.apply();


        }
        if (president2rb.isChecked()) {
            countPresident2++;
            Votespresident2.add(regnumber);
            editor.putInt("CP2", countPresident2);
            editor.apply();

        }
        if (vpresident1rb.isChecked()) {
            countVicePresident1++;
            VotesVicepresident1.add(regnumber);
            editor.putInt("CVP1", countVicePresident1);
            editor.apply();

        }
        if (vpresident2rb.isChecked()) {
            countVicePresident2++;
            VotesVicepresident2.add(regnumber);
            editor.putInt("CVP2", countVicePresident2);
            editor.apply();
        }

        if (treasurer1rb.isChecked()) {
            countTreasurer1++;
            Votestreasurer1.add(regnumber);
            editor.putInt("CT1", countTreasurer1);
            editor.apply();
        }
        if (treasurer2rb.isChecked()) {
            countTreasurer2++;
            Votestreasurer2.add(regnumber);

            editor.putInt("CT2", countTreasurer2);
            editor.apply();
        }

        if (sec1rb.isChecked()) {
            countSecretary1++;
            VotesSecretary1.add(regnumber);

            editor.putInt("CS1", countSecretary1);
            editor.apply();
        }
        if (sec2rb.isChecked()) {
            countSecretary2++;
            VotesSecretary2.add(regnumber);

            editor.putInt("CS2", countSecretary2);
            editor.apply();
        }

        if (js1rb.isChecked()) {
            countJointSecretary1++;
            VotesjointSecretary1.add(regnumber);

            editor.putInt("CJS1", countJointSecretary1);
            editor.apply();
        }
        if (js2rb.isChecked()) {
            countJointSecretary2++;

            VotesjointSecretary2.add(regnumber);

            editor.putInt("CJS2", countJointSecretary2);
            editor.apply();
        }


        VotedRegisterNumbers.add(regnumber);
        TCOFVOTES++;
        editor.putInt("allvotesn", TCOFVOTES);
        editor.apply();

        Snackbar snackbar = Snackbar
                .make(coordinatorLayout, "Successfully Registered!", Snackbar.LENGTH_LONG);


        snackbar.show();
        cleareverything();

    }

    private void cleareverything() {
        inputLayoutRegisternumber.getEditText().setText("");
        rgpresident.clearCheck();
        rgvpresident.clearCheck();
        rgtreasurer.clearCheck();
        rgsec.clearCheck();
        rgjs.clearCheck();

    }

    private boolean validateRegisternumber(long x) {

        for (int i = 0; i < VotedRegisterNumbers.size(); i++) {
            if (x == VotedRegisterNumbers.get(i))
                return false;
        }

        return true;
    }

    private boolean getregnumber() {
        regnumberstring = inputRegisternumber.getText().toString();
        boolean digitsOnly = TextUtils.isDigitsOnly(regnumberstring);

        if (!digitsOnly) {
            Snackbar snackbar = Snackbar
                    .make(coordinatorLayout, "Enter a  Number", Snackbar.LENGTH_LONG);

            snackbar.show();

            return false;
        }

        if (regnumberstring.matches("")) {
            Snackbar snackbar = Snackbar.make(coordinatorLayout, "Enter a Register Number", Snackbar.LENGTH_LONG);

            snackbar.show();
            return false;

        } else {
            regnumber = Long.parseLong(regnumberstring);

        }
        return true;
    }

    private void initvar() {


        //Register Number edit text
        inputLayoutRegisternumber = (TextInputLayout) findViewById(R.id.input_layout_registernumber);
        inputRegisternumber = (EditText) findViewById(R.id.et_registernumber);

        coordinatorLayout = (RelativeLayout) findViewById(R.id.submitvoterl);
        //president
        rgpresident = (RadioGroup) findViewById(R.id.presidentrg);
        president1rb = (RadioButton) findViewById(R.id.president1rb);
        president2rb = (RadioButton) findViewById(R.id.president2rb);
        //vp
        rgvpresident = (RadioGroup) findViewById(R.id.vpresidentrg);
        vpresident1rb = (RadioButton) findViewById(R.id.vpresident1rb);
        vpresident2rb = (RadioButton) findViewById(R.id.vpresident2rb);

        //Treasurer
        rgtreasurer = (RadioGroup) findViewById(R.id.treasurerrg);
        treasurer1rb = (RadioButton) findViewById(R.id.t1rb);
        treasurer2rb = (RadioButton) findViewById(R.id.t2rb);


        //Secretary
        rgsec = (RadioGroup) findViewById(R.id.secrg);
        sec1rb = (RadioButton) findViewById(R.id.s1rb);
        sec2rb = (RadioButton) findViewById(R.id.s2rb);

        //js
        rgjs = (RadioGroup) findViewById(R.id.jsecrg);
        js1rb = (RadioButton) findViewById(R.id.js1rb);
        js2rb = (RadioButton) findViewById(R.id.js2rb);

        Submit = (FloatingActionButton) findViewById(R.id.fab);

    }

}
