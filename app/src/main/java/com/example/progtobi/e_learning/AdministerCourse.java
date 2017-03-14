package com.example.progtobi.e_learning;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AdministerCourse extends AppCompatActivity {

    EditText topic_name, topic_note, topic_example, topic_hint;
    Button proced_quest;
    AlertDialog.Builder builder;
    String name = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.administer_course);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Intent i = getIntent();
        name = i.getStringExtra("name");

        topic_name = (EditText) findViewById(R.id.tname);
        topic_note = (EditText) findViewById(R.id.tnote);
        topic_example = (EditText) findViewById(R.id.texample);
        topic_hint = (EditText) findViewById(R.id.tnotice);

        proced_quest = (Button) findViewById(R.id.tcontinue);
        proced_quest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!validate()) {
                    return;
                }
                final String t_name = topic_name.getText().toString();
                final String t_note = topic_note.getText().toString();
                final String t_example = topic_example.getText().toString();
                final String t_hint = topic_hint.getText().toString();
                Intent intent = new Intent(AdministerCourse.this, AddQuestionActivity.class);

                intent.putExtra("topic_name", t_name);
                intent.putExtra("topic_note", t_note);
                intent.putExtra("topic_example", t_example);
                intent.putExtra("topic_hint", t_hint);
                intent.putExtra("teacher", name);
                startActivity(intent);


            }
        });


    }

    public boolean validate() {
        boolean valid = true;

        String topic_namestr, topic_notestr, topic_examplestr, tnoticestr;
        topic_namestr = topic_name.getText().toString();
        topic_notestr = topic_note.getText().toString();
        topic_examplestr = topic_example.getText().toString();
        tnoticestr = topic_hint.getText().toString();

        if (topic_namestr.isEmpty()) { //|| !android.util.Patterns.EMAIL_ADDRESS.matcher(username).matches())
            topic_name.setError("Add Topic");
            valid = false;
        } else {
            topic_name.setError(null);
        }

        if (topic_notestr.isEmpty()) {
            topic_note.setError("Add Note(s");
            valid = false;
        } else {
            topic_note.setError(null);
        }

        if (topic_examplestr.isEmpty()) {
            topic_example.setError("Add Example(s)");
            valid = false;
        } else {
            topic_example.setError(null);
        }

        if (tnoticestr.isEmpty()) {
            topic_hint.setError("Add Example(s)");
            valid = false;
        } else {
            topic_hint.setError(null);
        }

        return valid;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_administer_course, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();


        if (id == android.R.id.home) {
            NavUtils.navigateUpFromSameTask(this);
        }

        return super.onOptionsItemSelected(item);


    }

    public void displayAlert(String message) {
        builder.setMessage(message);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                topic_name.setText("");
                topic_note.setText("");
                topic_example.setText("");
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.setCancelable(false);
        alertDialog.show();
    }
}
