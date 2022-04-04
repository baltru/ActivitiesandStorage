package com.example.a4praktinis;

import android.content.LocusId;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DeleteNoteActivity extends AppCompatActivity {
    Spinner DeleteSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.deletenoteactivity);

        DeleteSpinner = findViewById(R.id.DeleteSpinner);

        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

        ArrayList<String> List_Notes = new ArrayList<String>(sp.getStringSet("notes", new HashSet<String>()));
        ArrayAdapter listAdapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1, List_Notes);
        DeleteSpinner.setAdapter(listAdapter);
       
    }

    public void onDeleteNoteClick(View view) {

        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        SharedPreferences.Editor spEd = sp.edit();

        Set<String> savedNotesList = sp.getStringSet("notes", new HashSet<String>());

        String selectedNote = DeleteSpinner.getSelectedItem().toString();

        Set<String> removedStrSet = new HashSet<String>();

        for (String savedNote : savedNotesList) {
            if (!savedNote.equalsIgnoreCase(selectedNote)) {
                removedStrSet.add(savedNote);
            }
        }

        spEd.putStringSet("notes", removedStrSet);
        spEd.apply();

        finish();
    }
}
