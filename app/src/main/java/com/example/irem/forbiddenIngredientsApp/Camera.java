package com.example.arafat.myapplication;
import android.app.Activity;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.SparseArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.vision.Frame;
import com.google.android.gms.vision.text.TextBlock;
import com.google.android.gms.vision.text.TextRecognizer;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.graphics.drawable.ColorDrawable;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Display;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;



import org.opencv.android.BaseLoaderCallback;
import org.opencv.android.CameraBridgeViewBase;
import org.opencv.android.JavaCameraView;
import org.opencv.android.LoaderCallbackInterface;
import org.opencv.android.OpenCVLoader;
import org.opencv.android.Utils;
import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.DMatch;
import org.opencv.core.Mat;
import org.opencv.core.MatOfByte;
import org.opencv.core.MatOfDMatch;
import org.opencv.core.MatOfKeyPoint;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.core.TermCriteria;
import org.opencv.features2d.DescriptorExtractor;
import org.opencv.features2d.DescriptorMatcher;
import org.opencv.features2d.FeatureDetector;
import org.opencv.features2d.Features2d;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.Vector;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.content.res.AssetManager;
import android.os.Environment;
import android.widget.ImageView;
import android.content.Context;
import android.util.Log;
import android.widget.TextView;
import com.googlecode.leptonica.android.Pixa;
import com.googlecode.tesseract.android.ResultIterator;
import com.googlecode.tesseract.android.TessBaseAPI;
import com.googlecode.tesseract.android.TessBaseAPI.PageIteratorLevel;
import com.googlecode.tesseract.android.TessBaseAPI;
import com.googlecode.leptonica.android.Enhance;
import com.googlecode.leptonica.android.Pix;
import java.io.File;
import java.io.OutputStream;
import java.io.InputStream;
import java.io.FileOutputStream;


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
    static {
        if (!OpenCVLoader.initDebug()) {
            Log.i("opencv", "opencv init failed");
        } else {
            Log.i("opencv", "opencv init success");
        }
    }

    private BaseLoaderCallback mLoaderCallback = new BaseLoaderCallback(this) {
        @Override
        public void onManagerConnected(int status) {
            switch (status) {
                case LoaderCallbackInterface.SUCCESS: {
                    Log.i("opencv", "OpenCV loaded successfully");
                }
                break;
                default: {
                    super.onManagerConnected(status);
                }
                break;
            }
        }
    };

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
            TextRecognizer textRecognizer = new TextRecognizer.Builder(this).build();

            Bitmap photo = (Bitmap) data.getExtras().get("data");

            imageView.setImageBitmap(photo);
            imageView.setVisibility(View.VISIBLE);

            if (!textRecognizer.isOperational()) {
                new AlertDialog.Builder(this)
                        .setMessage("Text recognizer could not be set up on your device :(")
                        .show();
                return;
            }

            Frame frame = new Frame.Builder().setBitmap(photo).build();
            SparseArray<TextBlock> text = textRecognizer.detect(frame);
            String a = "";
            for (int i = 0; i < text.size(); ++i) {
                TextBlock item = text.valueAt(i);
                if (item != null && item.getValue() != null) {
                    a = a + item.getValue();
                }
            }
            textView = findViewById(R.id.textView);
            textView.setText(a);


        }
    }
}

