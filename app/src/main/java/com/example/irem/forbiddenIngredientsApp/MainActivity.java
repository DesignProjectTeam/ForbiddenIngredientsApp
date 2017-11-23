package com.example.irem.forbiddenIngredientsApp;

import android.content.Intent;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button  btn=(Button)findViewById(R.id.open_camera);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent =new Intent(MainActivity.this,Camera.class);
                MainActivity.this.startActivity(intent);//intent’i başlat
                MainActivity.this.finish();
            }
        });

        Button inf=(Button)findViewById(R.id.information);
        inf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent2 = new Intent(MainActivity.this,Information.class);
                MainActivity.this.startActivity(intent2);
                MainActivity.this.finish();
            }
        });

    }





}

