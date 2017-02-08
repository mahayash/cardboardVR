package com.ness.virtualtour;

import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.Pair;
import android.view.Menu;

import com.google.vr.sdk.widgets.pano.VrPanoramaView;

import java.io.InputStream;

public class MainActivity extends AppCompatActivity implements ILoadImage {

    private static final String TAG = MainActivity.class.getSimpleName();
    private VrPanoramaView pnvLobby, pnvReception, pnvInsideOffice, pnvCanteen, pnvConference;

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

        /*
        pnvLobby = (VrPanoramaView) findViewById(R.id.pnv_lobby);
        pnvReception = (VrPanoramaView) findViewById(R.id.pnv_reception);
        pnvReception.setEventListener(new VrActivityActionListener());
        pnvInsideOffice = (VrPanoramaView) findViewById(R.id.pnv_inside_office);
        pnvCanteen = (VrPanoramaView) findViewById(R.id.pnv_canteen);
        pnvConference = (VrPanoramaView) findViewById(R.id.pnv_conference);
        */
        loadImages();

    }

    @Override
    protected void onNewIntent(Intent intent) {
        Log.i(TAG, this.hashCode() + ".onNewIntent()");
        setIntent(intent);
        loadImages();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.others, menu);
        return true;
    }

    @Override
    public void loadImage(InputStream inputStream, VrPanoramaView.Options options, int widgetPosition) {

        switch (widgetPosition) {
            case 0:
                pnvLobby.loadImageFromBitmap(BitmapFactory.decodeStream(inputStream), options);
                break;
            case 1:
                pnvReception.loadImageFromBitmap(BitmapFactory.decodeStream(inputStream), options);
                break;
            case 2:
                pnvInsideOffice.loadImageFromBitmap(BitmapFactory.decodeStream(inputStream), options);
                break;
            case 3:
                pnvCanteen.loadImageFromBitmap(BitmapFactory.decodeStream(inputStream), options);
                break;
            case 4:
                pnvConference.loadImageFromBitmap(BitmapFactory.decodeStream(inputStream), options);
                break;
        }

    }

    private void handleIntent(Intent intent, int widgetPosition) {
        // Determine if the Intent contains a file to load.
        if (Intent.ACTION_VIEW.equals(intent.getAction())) {
            fileUri = intent.getData();
            panoOptions.inputType = intent.getIntExtra("inputType", VrPanoramaView.Options.TYPE_MONO);
        } else {
            fileUri = null;
            panoOptions.inputType = VrPanoramaView.Options.TYPE_MONO;
        }

        if (backgroundImageLoaderTask != null) {
            // Cancel any task from a previous intent sent to this activity.
            backgroundImageLoaderTask.cancel(true);
        }
        backgroundImageLoaderTask = new LoadImageAsyncTask(context, this, widgetPosition);
        backgroundImageLoaderTask.execute(Pair.create(fileUri, panoOptions));
    }


    private void loadImages() {

        handleIntent(getIntent(), 0);
        handleIntent(getIntent(), 1);
        handleIntent(getIntent(), 2);
        handleIntent(getIntent(), 3);
    }
}
