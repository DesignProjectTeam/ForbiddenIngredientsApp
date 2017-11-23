package com.example.irem.forbiddenIngredientsApp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Information extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.information);
        TextView t = findViewById(R.id.text);
        t.setText("By using this application, you will be protected against content that is harmful to you. First, click the \"select diseases\" button to identify your health problem from the list. Then press the \"open the camera\" button to open your phone's camera. Pull the contents portion of the product you will eat. If you think that the picture you took is not taken properly, press the \"check again\" button. Click on the submit button to see if your food is harmful to you");

        Button inf=(Button)findViewById(R.id.back);
        inf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent2 = new Intent(Information.this,MainActivity.class);
                Information.this.startActivity(intent2);
                Information.this.finish();
            }
        });

    }
}