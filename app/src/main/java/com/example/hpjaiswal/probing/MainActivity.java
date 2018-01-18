package com.example.hpjaiswal.probing;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    TextView size, key;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        size = (TextView) findViewById(R.id.size);
        key = (TextView) findViewById(R.id.key);
    }

    public void next(View v) {
        if (size.getText().length()<=0 || key.getText().length()<=0 || Integer.valueOf(size.getText().toString()) < Integer.valueOf(key.getText().toString())) {
            Toast.makeText(this, "Fill proper details to Proceed", Toast.LENGTH_SHORT).show();
        } else {
            String s = size.getText().toString();
            String k = key.getText().toString();
            Intent i = new Intent(this, Main2Activity.class);
            i.putExtra("key",k);
            i.putExtra("size",s);
            startActivity(i);
        }
    }
}