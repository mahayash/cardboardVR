package com.ness.virtualtour;

import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.Pair;
import android.view.Menu;
import android.widget.ImageView;

import com.google.vr.sdk.widgets.pano.VrPanoramaView;

import java.io.IOException;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity implements ILoadImage {

    private static final String TAG = MainActivity.class.getSimpleName();
    private VrPanoramaView pnvReception, pnvInsideOffice, pnvCanteen;
    private ImageView pnvLobby;
    private Uri fileUri;
    private VrPanoramaView.Options panoOptions = new VrPanoramaView.Options();
    private LoadImageAsyncTask backgroundImageLoaderTask;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        InitView();
    }

    private void InitView() {

        context = this;
        //pnvLobby = (VrPanoramaView) findViewById(R.id.pnv_lobby);
        pnvLobby = (ImageView) findViewById(R.id.pnv_lobby);
        pnvReception = (VrPanoramaView) findViewById(R.id.pnv_reception);
        pnvInsideOffice = (VrPanoramaView) findViewById(R.id.pnv_inside_office);
        pnvCanteen = (VrPanoramaView) findViewById(R.id.pnv_canteen);
        handleIntent(getIntent());

        //loadImage();
        //LoadImageAsyncTask2 loadImageAsyncTask2 = new LoadImageAsyncTask2(context, this);
        //loadImageAsyncTask2.execute("");
    }

    @Override
    protected void onNewIntent(Intent intent) {
        Log.i(TAG, this.hashCode() + ".onNewIntent()");
        setIntent(intent);
        //handleIntent(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.others, menu);
        return true;
    }

    @Override
    public void loadImage(InputStream inputStream, VrPanoramaView.Options options) {

        //pnvLobby.loadImageFromBitmap(BitmapFactory.decodeStream(inputStream), options);
        Drawable d = Drawable.createFromStream(inputStream, null);
        //pnvLobby.setImageBitmap(BitmapFactory.decodeStream(inputStream));
        //pnvLobby.setImageDrawable(d);
        pnvReception.loadImageFromBitmap(BitmapFactory.decodeStream(inputStream), options);
    }

    private void handleIntent(Intent intent) {
        // Determine if the Intent contains a file to load.
        if (Intent.ACTION_VIEW.equals(intent.getAction())) {
            Log.i(TAG, "ACTION_VIEW Intent recieved");

            fileUri = intent.getData();
            if (fileUri == null) {
                Log.w(TAG, "No data uri specified. Use \"-d /path/filename\".");
            } else {
                Log.i(TAG, "Using file " + fileUri.toString());
            }

            panoOptions.inputType = intent.getIntExtra("inputType", VrPanoramaView.Options.TYPE_MONO);
            Log.i(TAG, "Options.inputType = " + panoOptions.inputType);
        } else {
            Log.i(TAG, "Intent is not ACTION_VIEW. Using default pano image.");
            fileUri = null;
            panoOptions.inputType = VrPanoramaView.Options.TYPE_MONO;
        }

        if (backgroundImageLoaderTask != null) {
            // Cancel any task from a previous intent sent to this activity.
            backgroundImageLoaderTask.cancel(true);
        }
        backgroundImageLoaderTask = new LoadImageAsyncTask(context,this);
        backgroundImageLoaderTask.execute(Pair.create(fileUri, panoOptions));
    }



    private void loadImage() {

        try {
            // get input stream
            InputStream ims = getAssets().open("andes.jpg");
            // load image as Drawable
            Drawable d = Drawable.createFromStream(ims, null);
            // set image to ImageView
            pnvLobby.setImageDrawable(d);
        } catch (IOException ex) {
            return;
        }
    }
}
