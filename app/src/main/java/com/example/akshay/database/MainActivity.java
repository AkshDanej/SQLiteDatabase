package com.example.akshay.database;

import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
EditText e1,e2,e3,e4;
    Database db;
    String id,name,surname,marks;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        e1=(EditText)findViewById(R.id.editText);
        e2=(EditText)findViewById(R.id.editText2);
        e3=(EditText)findViewById(R.id.editText3);
        e4=(EditText)findViewById(R.id.editText4);
db=new Database(this);
    }
    public void Stringer(){
        id=e1.getText().toString();
        name=e2.getText().toString();
        surname=e3.getText().toString();
        marks=e4.getText().toString();

    }
    public void add(View view){
Stringer();
        boolean insert=db.insertData(name,surname,marks);
        if (insert==true)
            Toast.makeText(this, "data inserted", Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(this, "data not inserted", Toast.LENGTH_SHORT).show();


    }



    public void vie(View v){
Stringer();
        Cursor res=db.getAllData();
        if (res.getCount()==0)
        { message("Error","nothing found");
            return;}
        StringBuffer buffer=new StringBuffer();
        while (res.moveToNext()){
            buffer.append("ID :"+res.getString(0)+"\n");
            buffer.append("Name :"+res.getString(1)+"\n");
            buffer.append("surName :"+res.getString(2)+"\n");
            buffer.append("marks :"+res.getString(3)+"\n\n");


        }
        message("DATA",buffer.toString());

    }
    public void del(View v){
        Stringer();
        Integer delete=db.delete(id);
        if (delete>0)
            Toast.makeText(this, "data deleted", Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(this, "data not deleted", Toast.LENGTH_SHORT).show();
    }
    public void modi(View v){

        Stringer();
        boolean modi=db.modifyData(id,name,surname,marks);
        if (modi==true)
            Toast.makeText(this, "data modified", Toast.LENGTH_SHORT).show();
else Toast.makeText(this, "data not modified", Toast.LENGTH_SHORT).show();

    }
    public void message(String title,String msg){
        AlertDialog.Builder b=new AlertDialog.Builder(this);
        b.setCancelable(true);
        b.setTitle(title);
        b.setMessage(msg);
        b.show();


    }
}
