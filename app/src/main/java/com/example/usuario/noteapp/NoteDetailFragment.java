package com.example.usuario.noteapp;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

public class NoteDetailFragment extends Fragment {

    EditText tittleText;
    EditText descriptionText;

    public NoteDetailFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_note_detail, container, false);

        tittleText = (EditText) view.findViewById(R.id.fragment_note_detail_tittle_text);
        descriptionText = (EditText) view.findViewById(R.id.fragment_note_detail_description);

        return view;
    }

    @Override
    public void onPause() {
        super.onPause();
        //save all screen to disk
        saveAllDataToDisk();
    }

    @Override
    public void onResume() {
        super.onResume();
        //load data to show on screen
        loadAllDataFromDisk();
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getContext());


        SharedPreferences.Editor editor = preferences.edit();
        editor.remove("NOTE_TITTLE");
        editor.remove("NOTE_DESCRIPTION");
        editor.apply();
    }

    private void loadAllDataFromDisk() {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getContext());

        SharedPreferences.Editor editor = preferences.edit();
        String noteTittle = preferences.getString("NOTE_TITTLE", "");
        String noteDescription = preferences.getString("NOTE_DESCRIPTION", "");

        tittleText.setText(noteTittle);
        descriptionText.setText(noteDescription);


    }

    private void saveAllDataToDisk() {
        //abro fichero para escribir
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        SharedPreferences.Editor editor = preferences.edit();

        //leo lo que has escrito
        String tittle = tittleText.getText().toString();
        String description = descriptionText.getText().toString();

        //grabo eso en el fichero
        editor.putString("NOTE_TITTLE", tittle);
        editor.putString("NOTE_DESCRIPTION", description);
        editor.apply();
    }
}


