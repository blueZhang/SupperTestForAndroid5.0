package com.bluezhang.supperapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

public class ResetActivity extends AppCompatActivity {

    private EditText editText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset);
        editText = (EditText) findViewById(R.id.edit_text);
        String txt = getIntent().getStringExtra("A");
        editText.setText(txt);

    }

    public void cancle(View view) {
        finish();
    }

    public void save(View view) {
       Intent intent = new Intent();
        String text = editText.getText().toString();
        intent.putExtra("B",text);
        setResult(RESULT_OK,intent );
        finish();


    }
}
