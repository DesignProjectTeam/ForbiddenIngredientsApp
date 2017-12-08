package com.example.irem.forbiddenIngredientsApp;

import java.util.ArrayList;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class Ingredients extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingredients);

        //DatabaseHelper sınıfımızdaki CreateDataBase methodunu çağırıp , assest dizinine koymuş
        //olduğumuz veritabanını,Android projesinin içinde  oluşturup,verileri kopyalamasını sağladık...
        DatabaseHelper dbHelper=new DatabaseHelper(this);
        try
        {
            dbHelper.CreateDataBase();
            dbHelper.openDataBase();

        }
        catch (Exception ex)
        {
            Log.w("hata","Veritabanı oluşturulamadı ve kopyalanamadı!");
        }


        //Proje içine kopyalanmış olan veritabanımızdan verileri listview e yazdırdık

       // SQLiteDatabase db=dbHelper.getReadableDatabase();
        String[] getColumnName={"Forbidden"};
        Cursor imlec=dbHelper.getReadableDatabase().query("FIngredients", getColumnName, null, null, null, null, null);
        ListView listview= findViewById(R.id.ingr);
        ArrayList<String> FIngredients=new ArrayList<String>();
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,FIngredients);

        while(imlec.moveToNext()){
            String Forbidden=imlec.getString(imlec.getColumnIndex("Forbidden"));
            //String fb= Forbidden;
            FIngredients.add(Forbidden);

        }
        listview.setAdapter(adapter);
        imlec.close();
        dbHelper.close();

    }
}