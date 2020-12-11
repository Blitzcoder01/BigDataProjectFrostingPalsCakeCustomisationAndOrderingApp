package com.hello.cakeapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.FileNotFoundException;
import java.io.InputStream;

public class UserGalleryPickUp extends AppCompatActivity {
    final int REQUEST_CODE_GALLERY = 999;
    private ImageView iv_user;

    // Folder path for Firebase Storage.
    String Storage_Path = "All_Cake_Uploads";
    // Root Database Name for Firebase Database.
    String Database_Path = "All_Cake_Uploads_Database";
    ProgressDialog progressDialog ;
    Uri Uri;
    private EditText title_user, weight_user, type_user, message_user ,detail_user,cakename_user ,address_user, name_user , phone_user;
    Button button_upload;
    // Creating StorageReference and DatabaseReference object.
    StorageReference storageReference;
    DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_gallery_pick_up);
        title_user=findViewById(R.id.title_user);
        weight_user=findViewById(R.id.weight_user);
        type_user=findViewById(R.id.type_user);
        message_user=findViewById(R.id.message_user);
        detail_user=findViewById(R.id.detail_user);
        cakename_user=findViewById(R.id.cakename_user);
        address_user=findViewById(R.id.address_user);
        name_user= findViewById(R.id.name_user);
        phone_user=findViewById(R.id.phone_user);
        button_upload=findViewById(R.id.button_upload);
        iv_user=findViewById(R.id.iv_user);


        storageReference = FirebaseStorage.getInstance().getReference();
        databaseReference= FirebaseDatabase.getInstance().getReference(Database_Path);




        button_upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uploadFile();
            }
        });
    }
    public void pick(View v) {

        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, REQUEST_CODE_GALLERY);

    }
    public String getFileExtension(Uri uri) {
        ContentResolver cR = getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cR.getType(uri));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if(requestCode == REQUEST_CODE_GALLERY && resultCode == RESULT_OK && data != null){
            Uri = data.getData();

            try {
                InputStream inputStream = getContentResolver().openInputStream(Uri);

                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                iv_user.setImageBitmap(bitmap);

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }

        super.onActivityResult(requestCode, resultCode, data);
    }

    private void uploadFile() {
        //checking if file is available
        if (Uri!= null) {
            //displaying progress dialog while image is uploading
            final ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setTitle("Uploading");
            progressDialog.show();

            //getting the storage reference
            StorageReference sRef = storageReference.child(Storage_Path + System.currentTimeMillis() + "." + getFileExtension(Uri));

            //adding the file to reference
            sRef.putFile(Uri)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            //dismissing the progress dialog
                            progressDialog.dismiss();

                            //displaying success toast
                            Toast.makeText(getApplicationContext(), "File Uploaded ", Toast.LENGTH_LONG).show();

                            //creating the upload object to store uploaded image details
                            UploadCakeDataToFirebase upload = new UploadCakeDataToFirebase(cakename_user.getText().toString(),title_user.getText().toString(),weight_user.getText().toString(),type_user.getText().toString(),message_user.getText().toString(),detail_user.getText().toString(),address_user.getText().toString(), name_user.getText().toString(),phone_user.getText().toString(), taskSnapshot.getMetadata().getReference().getDownloadUrl().toString());

                            //adding an upload to firebase database
                            String uploadCakeId = databaseReference.push().getKey();
                            databaseReference.child(uploadCakeId).setValue(upload);
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception exception) {
                            progressDialog.dismiss();
                            Toast.makeText(getApplicationContext(), exception.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    })
                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                            //displaying the upload progress
                            double progress = (100.0 * taskSnapshot.getBytesTransferred()) / taskSnapshot.getTotalByteCount();
                            progressDialog.setMessage("Uploaded " + ((int) progress) + "%...");
                        }
                    });
        } else {
            //display an error if no file is selected
        }
    }

}
