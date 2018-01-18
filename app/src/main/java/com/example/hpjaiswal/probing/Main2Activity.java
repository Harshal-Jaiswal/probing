package com.example.hpjaiswal.probing;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import static com.example.hpjaiswal.probing.R.id.size;

public class Main2Activity extends AppCompatActivity {

    int countkey = 0, c = 0;
    int[] arr;
    String str="",size;
    TextView t1, t2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Intent intent = getIntent();
        String key = intent.getStringExtra("key");
        size = intent.getStringExtra("size");
        countkey = Integer.valueOf(key);
        arr = new int[countkey];
        t1 = (TextView) findViewById(R.id.keys);
        t2 = (TextView) findViewById(R.id.addkey);
    }

    public void add(View v) {
        if (!(t2.getText().length() <= 0)) {

            if (c == 0) {
                t1.setText("Keys: "+t1.getText() + t2.getText().toString());
                arr[c] = Integer.valueOf(t2.getText().toString());
                str=str+t2.getText().toString();
                c++;
            } else {
                t1.setText(t1.getText()+", " + t2.getText().toString());
                arr[c] = Integer.valueOf(t2.getText().toString());
                str=str+","+t2.getText().toString();
                c++;
                if(c==countkey){
                    Intent i=new Intent(this,Main3Activity.class);
                    i.putExtra("str",str);
                    i.putExtra("arr",arr);
                    i.putExtra("bucketsize",Integer.valueOf(size.toString()));
                    startActivity(i);
                }
            }
            t2.setText("");
            t2.setHint("next key");
        } else
            Toast.makeText(this, "error", Toast.LENGTH_SHORT).show();
    }
}
