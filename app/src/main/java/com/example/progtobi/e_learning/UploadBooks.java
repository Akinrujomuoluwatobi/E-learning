package com.example.progtobi.e_learning;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.NavUtils;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.progtobi.e_learning.Tasks.UploadBooksTask;
import com.nbsp.materialfilepicker.MaterialFilePicker;
import com.nbsp.materialfilepicker.ui.FilePickerActivity;

public class UploadBooks extends AppCompatActivity {
    EditText bookname, bookauthor, bookdesc;
    Button uploadbookbtn;
    ImageView uploadtumbnail;
    TextView clickimg;
    String upload_url = "";
    private Bitmap bitmap;

    public static final int PERMISSIONS_REQUEST_CODE = 0;
    public static final int FILE_PICKER_REQUEST_CODE = 1;
    String path = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_books);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        bookname = (EditText) findViewById(R.id.uploadbookname);
        bookauthor = (EditText) findViewById(R.id.uploadbookauthor);
        bookdesc = (EditText) findViewById(R.id.uploadbookdesc);
        uploadtumbnail = (ImageView) findViewById(R.id.uploadthumbnail);
        clickimg = (TextView) findViewById(R.id.clickimg);
        uploadtumbnail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkPermissionsAndOpenFilePicker();

            }
        });

        uploadbookbtn = (Button) findViewById(R.id.uploadbookbtn);
        uploadbookbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String booknamestr = bookname.getText().toString();
                String bookauthstr = bookauthor.getText().toString();
                String bookdescstr = bookdesc.getText().toString();
                if (!validate()){
                    return;
                }
                UploadBooksTask up = new UploadBooksTask(UploadBooks.this);
                up.execute(path, booknamestr, bookauthstr, bookdescstr );

            }

            ;
        });

    }

    private void checkPermissionsAndOpenFilePicker() {
        String permission = Manifest.permission.READ_EXTERNAL_STORAGE;

        if (ContextCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, permission)) {
                showError();
            } else {
                ActivityCompat.requestPermissions(this, new String[]{permission}, PERMISSIONS_REQUEST_CODE);
            }
        } else {
            openFilePicker();
        }
    }

    private void showError() {
        Toast.makeText(this, "Allow external storage reading", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String permissions[], @NonNull int[] grantResults) {
        switch (requestCode) {
            case PERMISSIONS_REQUEST_CODE: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    openFilePicker();
                } else {
                    showError();
                }
            }
        }
    }

    private void openFilePicker() {
        new MaterialFilePicker()
                .withActivity(this)
                .withRequestCode(FILE_PICKER_REQUEST_CODE)
                .withHiddenFiles(true)
                .start();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == FILE_PICKER_REQUEST_CODE && resultCode == RESULT_OK) {
            path = data.getStringExtra(FilePickerActivity.RESULT_FILE_PATH);

            if (path != null) {

            }
        }
    }

    public boolean validate() {
        boolean valid = true;

        String booknamestr = bookname.getText().toString();
        String bookauthstr = bookauthor.getText().toString();
        String bookdescstr = bookdesc.getText().toString();

        if (booknamestr.isEmpty()) { //|| !android.util.Patterns.EMAIL_ADDRESS.matcher(username).matches())
            bookname.setError("Enter A Book Name");
            valid = false;
        } else {
            bookname.setError(null);
        }

        if (bookauthstr.isEmpty()) {
            bookauthor.setError("Enter an Author");
            valid = false;
        } else {
            bookauthor.setError(null);
        }

        if (bookdescstr.isEmpty()) {
            bookdesc.setError("Enter a Description");
            valid = false;
        } else {
            bookdesc.setError(null);
        }

        return valid;
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_administer_course, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();


        if (id == android.R.id.home) {
            NavUtils.navigateUpFromSameTask(this);
        }

        return super.onOptionsItemSelected(item);
    }

}
