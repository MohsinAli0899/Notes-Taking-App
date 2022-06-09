package com.ali.easynotes;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;

import io.realm.Realm;

public class AddNoteActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);

        EditText titleInput = findViewById(R.id.titleInput);
        EditText descriptionInput = findViewById(R.id.descriptionInput);
        MaterialButton saveBtn = findViewById(R.id.saveBtn);

        Realm.init(getApplicationContext());
        Realm realm = Realm.getDefaultInstance();

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            String title = titleInput.getText().toString();
            String description = descriptionInput.getText().toString();
            long createdTime = System.currentTimeMillis();

            realm.beginTransaction();
            Notes note = realm.createObject(Notes.class);
            note.setTitle(title);
            note.setDescription(description);
            note.setTime(createdTime);

            realm.commitTransaction();
            Toast.makeText(getApplicationContext(), "Note Saved", Toast.LENGTH_SHORT).show();
            finish();
            }
        });
    }
}