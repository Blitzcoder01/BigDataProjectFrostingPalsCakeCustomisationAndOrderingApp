package com.hello.cakeapp;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;


import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;

import java.io.InputStream;

public class GalleryPickUp extends AppCompatActivity {

    final int REQUEST_CODE_GALLERY = 999;
    private ImageView iv;
    private EditText title, weight, type, cost ,detail,cakename;
    Button upload;
    byte[] byteArray;
    int nextId;
    public static SQLiteHelper sqLiteHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery_pick_up);
        iv = (ImageView) findViewById(R.id.iv);
        title=findViewById(R.id.title);
        weight=findViewById(R.id.weight);
        type=findViewById(R.id.type);
        cost=findViewById(R.id.cost);
        detail=findViewById(R.id.detail);
        cakename=findViewById(R.id.cakename);
        //Realm.init(this);
       // Realm realm = Realm.getDefaultInstance();
        sqLiteHelper = new SQLiteHelper(this, "CakeDB.sqlite", null, 1);
        sqLiteHelper.queryData("CREATE TABLE IF NOT EXISTS CAKETABLE(Id INTEGER PRIMARY KEY AUTOINCREMENT, cakename VARCHAR, title VARCHAR, weight VARCHAR ,type VARCHAR, cost VARCHAR ,detail VARCHAR , image BLOB)");

        upload= findViewById(R.id.buttonupload);
        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    sqLiteHelper.insertData(
                            cakename.getText().toString().trim(),
                            title.getText().toString().trim(),
                            weight.getText().toString().trim(),
                            type.getText().toString().trim(),
                            cost.getText().toString().trim(),
                            detail.getText().toString().trim(),
                            imageViewToByte(iv)
                    );

                    Toast.makeText(getApplicationContext(), "Added successfully!", Toast.LENGTH_SHORT).show();
                    cakename.setText("");
                    title.setText("");
                    weight.setText("");
                    type.setText("");
                    cost.setText("");
                    detail.setText("");
                    iv.setImageResource(R.mipmap.ic_launcher);

                }
                catch (Exception e){
                    e.printStackTrace();
                }
            }
        });


    }

    private byte[] imageViewToByte(ImageView iv) {
        Bitmap bitmap = ((BitmapDrawable)iv.getDrawable()).getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] byteArray = stream.toByteArray();
        return byteArray;
    }


    public void pick(View v) {
      /**  ActivityCompat.requestPermissions(
                GalleryPickUp.this,
                new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                REQUEST_CODE_GALLERY
        );**/
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, REQUEST_CODE_GALLERY);

    }

    /**@Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        if(requestCode == REQUEST_CODE_GALLERY){
            if(grantResults.length >0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, REQUEST_CODE_GALLERY);
            }
            else {
                Toast.makeText(getApplicationContext(), "You don't have permission to access file location!", Toast.LENGTH_SHORT).show();
            }
            return;
        }

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
**/
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if(requestCode == REQUEST_CODE_GALLERY && resultCode == RESULT_OK && data != null){
            Uri uri = data.getData();

            try {
                InputStream inputStream = getContentResolver().openInputStream(uri);

                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                iv.setImageBitmap(bitmap);

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }

        super.onActivityResult(requestCode, resultCode, data);
    }

}
