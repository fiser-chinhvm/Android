package com.example.hoang.bookmovietickets.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.hoang.bookmovietickets.R;

import java.util.ArrayList;

public class UserProfile extends AppCompatActivity {

    private EditText edtName;
    private EditText edtTelephone;
    private EditText edtCardNumber;
    private EditText edtEmail;
    Button save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        edtName = (EditText) findViewById(R.id.name);
        edtTelephone = (EditText) findViewById(R.id.tel);
        edtCardNumber = (EditText) findViewById(R.id.card);
        edtEmail = (EditText) findViewById(R.id.email);
        save = (Button) findViewById(R.id.saveProf);

        edtName.setText(MainActivity.Uprof.get(0));
        edtTelephone.setText(MainActivity.Uprof.get(1));
        edtCardNumber.setText(MainActivity.Uprof.get(2));
        edtEmail.setText(MainActivity.Uprof.get(3));

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.Uprof.set(0, edtName.getText().toString());
                MainActivity.Uprof.set(1, edtTelephone.getText().toString());
                MainActivity.Uprof.set(2, edtCardNumber.getText().toString());
                MainActivity.Uprof.set(3, edtEmail.getText().toString());
                Toast.makeText(getApplicationContext(),"YOU SAVEEEEEEe",Toast.LENGTH_SHORT).show();
            }
        });



        System.out.println(MainActivity.Uprof.get(0));

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_user_profile, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
