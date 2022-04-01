package com.example.a4praktinis;
import androidx.appcompat.app.AppCompatActivity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.io.PrintStream;
import java.util.HashSet;
import java.util.Set;

public class AddNoteActivity extends AppCompatActivity {
    private TextView txtNote;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addnoteactivity);
    }

    public void onAddNoteClick(View view) {
        EditText txtNote = findViewById(R.id.txtNote);

        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        SharedPreferences.Editor spEd = sp.edit();

        Set<String> oldSet = sp.getStringSet("notes", new HashSet<String>());
        Set<String> newStrSet = new HashSet<String>();
        newStrSet.add(txtNote.getText().toString());
        newStrSet.addAll(oldSet);


        spEd.putStringSet("notes", newStrSet);
        spEd.apply();


        finish();
    }
}