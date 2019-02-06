package com.aeologic.moviemvvmdemo.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.aeologic.moviemvvmdemo.R;
import com.aeologic.moviemvvmdemo.constant.Constants;

public class AddMovieActivity extends AppCompatActivity {

    private EditText titleET;
    private EditText descET;
    private Button addBtn;

    private int requestCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_movie);

        initViews();

       /* Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            requestCode = bundle.getInt(ADD_);
        } else {
            Toast.makeText(this, getString(R.string.error), Toast.LENGTH_SHORT).show();
            finish();
        }*/

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = titleET.getText().toString();
                String desc = descET.getText().toString();
                if (title.trim().isEmpty() || desc.trim().isEmpty()) {
                    Toast.makeText(AddMovieActivity.this, "Please enter Title and Description", Toast.LENGTH_SHORT).show();
                    return;
                }
                Intent result = new Intent();
                result.putExtra(Constants.TITLE, title);
                result.putExtra(Constants.DESC, desc);
                setResult(RESULT_OK, result);
                finish();
            }
        });

    }

    private void initViews() {
        titleET = findViewById(R.id.title_et);
        descET = findViewById(R.id.desc_et);
        addBtn = findViewById(R.id.add_btn);
    }

}
