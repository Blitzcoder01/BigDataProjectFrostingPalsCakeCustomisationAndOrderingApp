package com.hello.cakeapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class FinalizeOrder extends AppCompatActivity {
    SQLiteHelper DQ;
    TextView cakename_copy,flavour_copy,cost_copy,weight_copy,type_copy,detail_copy;
    Button finalize_order;
    ImageView iv_copy;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finalize_order);
        cakename_copy=findViewById(R.id.cakename_copy);
        flavour_copy=findViewById(R.id.flavour_copy);
        cost_copy=findViewById(R.id.cost_copy);
        weight_copy=findViewById(R.id.weight_copy);
        type_copy=findViewById(R.id.type_copy);
        detail_copy=findViewById(R.id.detail_copy);
        finalize_order=findViewById(R.id.finalize_order);
        iv_copy=findViewById(R.id.iv_copy);


       // iv_copy.setImageResource(getIntent().getIntExtra("image",0));
        cakename_copy.setText(getIntent().getStringExtra("cakename"));
        flavour_copy.setText(getIntent().getStringExtra("flavor"));
        cost_copy.setText(getIntent().getStringExtra("cost"));
        weight_copy.setText(getIntent().getStringExtra("weight"));
        type_copy.setText(getIntent().getStringExtra("type"));
        detail_copy.setText(getIntent().getStringExtra("detail"));

        finalize_order.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {

           }
       });


    }

}
