package com.example.arafat.myapplication;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.Manifest;
import android.content.res.AssetManager;
import android.os.Environment;
import android.widget.Button;
import android.widget.ImageView;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Handler;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseArray;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.view.View;

import com.google.android.gms.vision.CameraSource;
import com.google.android.gms.vision.Detector;
import com.google.android.gms.vision.text.TextBlock;
import com.google.android.gms.vision.text.TextRecognizer;
import com.googlecode.tesseract.android.TessBaseAPI;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;




public class Camera extends Activity {
    private static final int CAMERA_REQUEST = 1888;
    private ImageView imageView;
    private static String LOG_TAG = Camera.class.getSimpleName();
    // SurfaceView surfaceView;
    private InputStream m_instream;
    Context context;
    TextView textView;
    //  TextView textView1;
    //  CameraSource cameraSource;
    //  StringBuilder stringBuilder = new StringBuilder();
    //  StringBuilder stringBuilder1 = new StringBuilder();
    // String line=null;
    // final static String TAG = Camera.class.getName();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.open_camera);


        //TextView res = (TextView) findViewById(R.id.);
        Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(cameraIntent, CAMERA_REQUEST);



    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CAMERA_REQUEST && resultCode == Activity.RESULT_OK) {
            imageView = (ImageView) findViewById(R.id.imageView);
            Bitmap photo = (Bitmap) data.getExtras().get("data");
            imageView.setImageBitmap(photo);
            imageView.setVisibility(View.VISIBLE);
            Log.v(LOG_TAG, "Before");
            TessBaseAPI baseAPI = new TessBaseAPI();
            baseAPI.setDebug(true);
            this.context = context;



                String  datapath = Environment.getExternalStorageDirectory() + "/ocrctz"+"/tessdata/" ;
            String language = "eng";
            /*File dir = new File(datapath + "tessdata/");
            if (!dir.exists())
                dir.mkdirs();*/
            //String path = FileUtils.getDirectory(this.directoryPath);

            copyFile(datapath);
            datapath=Environment.getExternalStorageDirectory() + "/ocrctz/";
            baseAPI.init(datapath, language);
            baseAPI.setImage(photo);
            String recognizer = baseAPI.getUTF8Text();
            baseAPI.end();
            Log.v(LOG_TAG, "OCR Result: " + recognizer);
            textView=findViewById(R.id.textView);
            textView.setText(recognizer.trim());
            ;

            //mTess.init(datapath, language);

        }
    }

    private void copyFile(String datapath) {
        AssetManager assetManager = getAssets();
        try {
            InputStream in = assetManager.open("eng.traineddata");


            OutputStream out = new FileOutputStream(datapath +"eng.traineddata" );
            byte[] buffer = new byte[1024];
            int read = in.read(buffer);
            while (read != -1) {
                out.write(buffer, 0, read);
                read = in.read(buffer);
            }
        } catch (Exception e) {
            Log.d("mylog", "couldn't copy with the following error : " + e.toString());
        }
    }
}
/*
    private void copyFiles() {
        try {
            String m_datapath = Environment.getExternalStorageDirectory().toString() + "/MyLibApp/tesscv/tesseract";
            String resInPath = "/home/leyla/tessdata/eng.traineddata";
            //Log.d(TAG, "copyFiles: resInPath " + resInPath);
            m_instream = new FileInputStream(resInPath);
            //location we want the file to be a
            String resOutPath = m_datapath + "/tessdata/eng.traineddata";
            //open byte streams for writing
            OutputStream outstream = new FileOutputStream(resOutPath);
            //copy the file to the location specified by filepath
            byte[] buffer = new byte[1024];
            int read;
            while ((read = m_instream.read(buffer)) != -1) {
                outstream.write(buffer, 0, read);
            }
            outstream.flush();
            outstream.close();
            m_instream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

/*

        }
    }
    private void copyFile(Context context) {
        AssetManager assetManager = context.getAssets();        try {
            InputStream in = assetManager.open("eng.traineddata");            OutputStream out = new FileOutputStream(datapath + "/tessdata/" + "eng.traineddata");            byte[] buffer = new byte[1024];            int read = in.read(buffer);            while (read != -1) {
                out.write(buffer, 0, read);                read = in.read(buffer);            }
        } catch (Exception e) {
            Log.d("mylog", "couldn't copy with the following error : "+e.toString());        }
    }
}
*/
 /*   @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CAMERA_REQUEST && resultCode == Activity.RESULT_OK) {
            Bitmap photo = (Bitmap) data.getExtras().get("data");
            imageView.setImageBitmap(photo);
        }
    }


        if(requestCode==33){
            ImageView resim= (ImageView)findViewById(R.id.imageView);
            Bitmap image=(Bitmap)data.getExtras().get("data");//Çekilen resim id olarak bitmap şeklinde alındı ve imageview'e atandı
            resim.setImageBitmap(image);
            ImageView iv = (ImageView) findViewById(R.id.image);
            iv.setImageBitmap(bitmap);
            iv.setVisibility(View.VISIBLE);

            Log.v(LOG_TAG, "Before baseApi");

            TessBaseAPI baseApi = new TessBaseAPI();
            baseApi.setDebug(true);
            baseApi.init(DATA_PATH, LANG);
            baseApi.setImage(bitmap);
            String recognizedText = baseApi.getUTF8Text();
            baseApi.end();

            Log.v(LOG_TAG, "OCR Result: " + recognizedText);

            // clean up and show
            if (LANG.equalsIgnoreCase("eng")) {
                recognizedText = recognizedText.replaceAll("[^a-zA-Z0-9]+", " ");
            }
            if (recognizedText.length() != 0) {
                ((TextView) findViewById(R.id.field)).setText(recognizedText.trim());
            }
        }


        // super.onActivityResult(requestCode, resultCode, data);
    }
    }
/*  TextRecognizer textRecognizer = new TextRecognizer.Builder(getApplicationContext()).build();


        cameraSource =new CameraSource.Builder(getApplicationContext(), textRecognizer)
                .setFacing(CameraSource.CAMERA_FACING_BACK)
                .setAutoFocusEnabled(true)
                .build();


        surfaceView.getHolder().addCallback(new SurfaceHolder.Callback() {

            @Override
            public void surfaceCreated(SurfaceHolder surfaceHolder) {
                try{
                    cameraSource.start(surfaceView.getHolder());
                } catch (IOException e){
                    e.printStackTrace();
                }

            }


            @Override
            public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {

            }

            @Override
            public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
                cameraSource.stop();
            }
        });



        textRecognizer.setProcessor(new Detector.Processor<TextBlock>() {
            @Override
            public void release() {

            }

            @Override
            public void receiveDetections(final Detector.Detections<TextBlock> detections) {
                final SparseArray<TextBlock> items = detections.getDetectedItems();
                if (items.size()!=0){
                    textView.post(new Runnable() {
                        @Override
                        public void run() {

                            for (int i=0; i<items.size(); i++){

                                TextBlock item = items.valueAt(i);
                                stringBuilder.append(item.getValue());
                                stringBuilder.append("\n");
                                textView.setText(stringBuilder.toString());
                            }



                        }

                    });
                }

                try {
                    FileInputStream fileInputStream = new FileInputStream (new File( "araf.txt"));
                    InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
                    BufferedReader bufferedReader = new BufferedReader(inputStreamReader);


                    while ( (line = bufferedReader.readLine()) != null )
                    {
                        stringBuilder1.append(line + System.getProperty("line.separator"));
                    }
                    fileInputStream.close();
                    line = stringBuilder1.toString();

                    bufferedReader.close();
                }
                catch(FileNotFoundException ex) {
                    Log.d(TAG, ex.getMessage());
                }
                catch(IOException ex) {
                    Log.d(TAG, ex.getMessage());
                }
                if(stringBuilder.toString().equals(stringBuilder1.toString())){
                        textView.setError("This product is harmfull for you this contains:"+ stringBuilder.toString());

                }
            }
        });

*/







