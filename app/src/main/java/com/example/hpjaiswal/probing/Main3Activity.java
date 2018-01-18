package com.example.hpjaiswal.probing;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class Main3Activity extends AppCompatActivity {

    TextView t1, t2, t3,t4;
    int bs;
    int[] values;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        Intent intent = getIntent();
        String keyy = intent.getStringExtra("str");
        values = intent.getIntArrayExtra("arr");
        bs = intent.getIntExtra("bucketsize", -1);
        linearprobing(values, bs);
        quadprobing(values, bs);
        doublehashing(values,bs,5);
    }

    public void change(View v){
        t4=(TextView)findViewById(R.id.ri);
        if(t4.getText().length()<=0){
            Toast.makeText(this,"enter value For R",Toast.LENGTH_SHORT).show();
        }else{
            int i=0,j=0,k=0;
            j=Integer.valueOf(t4.getText().toString());
            for(i=1;i<j;i++){
                if(j%i ==0){
                    k++;
                }
            }
            if(j==1 || k !=1 || j>bs){
                Toast.makeText(this,"enter proper value for R",Toast.LENGTH_SHORT).show();
            }else{
                TextView t= (TextView)findViewById(R.id.dh);
                t.setText("\n Double Hashing : (R="+String.valueOf(j)+")");
                linearprobing(values, bs);
                quadprobing(values, bs);
                doublehashing(values,bs,j);
            }
        }
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent i=new Intent(this,MainActivity.class);
        startActivity(i);
    }

    public void linearprobing(int[] key, int s) {
        t1 = (TextView) findViewById(R.id.bucket);
        t2 = (TextView) findViewById(R.id.bucketcoll);
        t3 = (TextView) findViewById(R.id.collision);
        t1.setText("");
        t2.setText("");
        t3.setText("");
        int x, z, collision = 0;
        int op[] = new int[s];
        int coll[] = new int[s];
        for (int i = 0; i < s; i++) {
            op[i] = -1;
        }
        for (int i = 0; i < key.length; i++) {
            x = key[i] % s;
            if (op[x] == -1) {
                op[x] = key[i];
                coll[x] = 1;
            } else {
                z = 1;
                while (true) {
                    if (op[(key[i] + z) % s] == -1) {
                        op[(key[i] + z) % s] = key[i];
                        coll[(key[i] + z) % s] = z + 1;
                        break;
                    } else {
                        z++;
                    }
                }
            }
        }

        t1.setText("bucket value \n");
        t2.setText("collision \n");
        for (int i = 0; i < s; i++) {
            // System.out.println(op[i] + "  " + coll[i]);
            t1.setText(t1.getText() + "\n" + String.valueOf(op[i]));
            t2.setText(t2.getText() + "\n" + String.valueOf(coll[i]));
            collision = collision + coll[i];
        }
        t3.setText("Total collision: " + String.valueOf(collision));

    }

    public void quadprobing(int[] key, int s) {
        t1 = (TextView) findViewById(R.id.bucket1);
        t2 = (TextView) findViewById(R.id.bucketcoll1);
        t3 = (TextView) findViewById(R.id.collision1);
        t1.setText("");
        t2.setText("");
        t3.setText("");
        int x, z, collision = 0;
        int op[] = new int[s];
        int coll[] = new int[s];
        for (int i = 0; i < s; i++) {
            op[i] = -1;
        }
        for (int i = 0; i < key.length; i++) {
            x = key[i] % s;
            if (op[x] == -1) {
                op[x] = key[i];
                coll[x] = 1;
            } else {
                z = 1;
                while (true) {
                    if (op[(key[i] + (z * z)) % s] == -1) {
                        op[(key[i] + (z * z)) % s] = key[i];
                        coll[(key[i] + (z * z)) % s] = z + 1;
                        break;
                    }
                    if (z == 30) {
                        break;
                    }
                    z++;
                }
            }
        }

        t1.setText("bucket value \n");
        t2.setText("collision \n");
        for (int i = 0; i < s; i++) {
            // System.out.println(op[i] + "  " + coll[i]);
            t1.setText(t1.getText() + "\n" + String.valueOf(op[i]));
            t2.setText(t2.getText() + "\n" + String.valueOf(coll[i]));
            collision = collision + coll[i];
        }
        t3.setText("Total collision: "+ String.valueOf(collision));
    }

    public void doublehashing(int[] key, int s,int r) {
        t1 = (TextView) findViewById(R.id.bucket2);
        t2 = (TextView) findViewById(R.id.bucketcoll2);
        t3 = (TextView) findViewById(R.id.collision2);
        t1.setText("");
        t2.setText("");
        t3.setText("");
        int x, z, collision = 0;
        int op[] = new int[s];
        int coll[] = new int[s];
        for (int i = 0; i < s; i++) {
            op[i] = -1;
        }
        for (int i = 0; i < key.length; i++) {
            x = key[i] % s;
            if (op[x] == -1) {
                op[x] = key[i];
                coll[x] = 1;
            } else {
                z = 1;
                while (true) {
                    if (op[(key[i] + (z * (r - (key[i] % r)))) % s] == -1) {
                        op[(key[i] + (z * (r - (key[i] % r)))) % s] = key[i];
                        coll[(key[i] + (z * (r - (key[i] % r)))) % s] = z + 1;
                        break;
                    }
                    if (z == 30) {
                        break;
                    }
                    z++;
                }
            }
        }
        t1.setText("bucket value \n");
        t2.setText("collision \n");
        for (int i = 0; i < s; i++) {
            // System.out.println(op[i] + "  " + coll[i]);
            t1.setText(t1.getText() + "\n" + String.valueOf(op[i]));
            t2.setText(t2.getText() + "\n" + String.valueOf(coll[i]));
            collision = collision + coll[i];
        }
        t3.setText("Total collision: " + String.valueOf(collision));
    }
}
