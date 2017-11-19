package com.example.yumnaasim.devfest;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Account extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);
        TextView tv1 = (TextView) findViewById(R.id.signin);
        Typeface face = Typeface.createFromAsset(getAssets(),
                "fonts/nabila.otf");
        tv1.setTypeface(face);

        Button button = (Button) findViewById(R.id.buttonUser);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Account.this,SignIn.class);
                intent.putExtra("Type","Donor");
                startActivity(intent);
            }
        });

    }
}
