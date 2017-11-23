package com.example.irem.forbiddenIngredientsApp;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.ImageView;
import android.widget.TextView;




public class Camera extends Activity {

    // SurfaceView surfaceView;
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



        Intent kamera=new Intent(MediaStore.ACTION_IMAGE_CAPTURE); // Resim çekme isteği ve activity başlatılıp id'si tanımlandı
        startActivityForResult(kamera,33);




    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {


        if(requestCode==33){
            Bitmap image=(Bitmap)data.getExtras().get("data");//Çekilen resim id olarak bitmap şeklinde alındı ve imageview'e atandı
            ImageView resim= (ImageView)findViewById(R.id.imageView);
            resim.setImageBitmap(image);

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







