package com.example.android.loadpicture;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    android.widget.ImageView iv;

            private static int RESULT_LOAD_IMAGE = 1;
        private static String[] PERMISSION_STORAGE = {
                android.Manifest.permission.READ_EXTERNAL_STORAGE,
                android.Manifest.permission.WRITE_EXTERNAL_STORAGE
        };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.v("start","");
        TextView tv = (TextView) findViewById(R.id.tv);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
                iv = findViewById(R.id.aks);

                        verifyStoragePermissions(this);
            ((android.widget.Button)findViewById(R.id.button)).setOnClickListener(new android.widget.Button.OnClickListener() {
                @Override
                public void onClick(android.view.View v) {
                    android.content.Intent i = new android.content.Intent(
                            android.content.Intent.ACTION_PICK,
                            android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

                    startActivityForResult(i, RESULT_LOAD_IMAGE);
                }
            });


    }


            @Override
        protected void onActivityResult(int requestCode, int resultCode, android.content.Intent data) {
            super.onActivityResult(requestCode, resultCode, data);




            if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && null != data) {
                android.net.Uri selectedImage = data.getData();
                String[] filePathColumn = { android.provider.MediaStore.Images.Media.DATA };

                android.database.Cursor cursor = getContentResolver().query(selectedImage,
                        filePathColumn, null, null, null);
                cursor.moveToFirst();

                int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                String picturePath = cursor.getString(columnIndex);
                cursor.close();

                android.graphics.Bitmap bmf = android.graphics.BitmapFactory.decodeFile(picturePath);


                iv.setImageBitmap(bmf);

            }





        }




        public static void verifyStoragePermissions(android.app.Activity activity){
            int permission = android.support.v4.app.ActivityCompat.checkSelfPermission(activity,android.Manifest.permission.WRITE_EXTERNAL_STORAGE);
            if (permission != android.content.pm.PackageManager.PERMISSION_GRANTED){
                android.support.v4.app.ActivityCompat.requestPermissions(activity,PERMISSION_STORAGE,1);
            }
    }

}
