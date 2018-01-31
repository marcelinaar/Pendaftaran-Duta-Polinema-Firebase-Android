package marcelinaar.uasdutapolinema;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;

public class UploadActivity extends AppCompatActivity implements View.OnClickListener {

    private static final  int PICK_IMAGE_REQ = 234;
    private ImageView imageView;
    private Button bPil, bUp;
    private Uri filePath;
    private StorageReference storageReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload);

        storageReference = FirebaseStorage.getInstance().getReference();

        imageView = (ImageView)findViewById(R.id.imageView);
        bPil = (Button)findViewById(R.id.bPilih);
        bUp = (Button)findViewById(R.id.bUpload);

        bPil.setOnClickListener(this);
        bUp.setOnClickListener(this);
    }


    private void  showFileChooser(){
        Intent i = new Intent();
        i.setType("image/*");
        i.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(i, "Pilih gambar"), PICK_IMAGE_REQ);
    }

    private void uploadFile(){

        if(filePath != null) {

            final ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setTitle("Proses upload...");
            progressDialog.show();
            StorageReference riversRef = storageReference.child("images/rivers.jpg");

            riversRef.putFile(filePath)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            progressDialog.dismiss();
                            Toast.makeText(getApplicationContext(), "Berhasil upload",
                                    Toast.LENGTH_LONG).show();
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception exception) {
                            progressDialog.dismiss();
                            Toast.makeText(getApplicationContext(), exception.getMessage(),
                                    Toast.LENGTH_LONG).show();
                        }
                    })
                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                            double progress = (100.0 * taskSnapshot.getBytesTransferred())/
                                    taskSnapshot.getTotalByteCount();
                            progressDialog.setMessage(((int) progress) + "% proses upload..");
                        }
                    })
            ;
        }else {

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == PICK_IMAGE_REQ && resultCode == RESULT_OK && data
                != null && data.getData() != null){
                filePath = data.getData();

            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(),
                        filePath);
                imageView.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    @Override
    public void onClick(View view) {
        if(view == bPil){
            showFileChooser();
        }else if(view == bUp){
            uploadFile();
        }

    }
}
