package com.ness.virtualtour;

import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.Pair;
import android.view.Menu;
import android.widget.ImageView;

import com.google.vr.sdk.widgets.pano.VrPanoramaView;

import java.io.InputStream;

public class MainActivity extends AppCompatActivity implements ILoadImage {

    private static final String TAG = MainActivity.class.getSimpleName();
    private VrPanoramaView pnvReception, pnvInsideOffice, pnvCanteen;
    private ImageView pnvLobby;
    private Uri fileUri;
    private VrPanoramaView.Options panoOptions = new VrPanoramaView.Options();
    private LoadImageAsyncTask loadImageAsyncTask;
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
    }

    @Override
    protected void onNewIntent(Intent intent) {
        Log.i(TAG, this.hashCode() + ".onNewIntent()");
        setIntent(intent);
        handleIntent(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.others, menu);
        return true;
    }

    @Override
    public void loadImage(InputStream inputStream, VrPanoramaView.Options options) {

        //pnvLobby.loadImageFromBitmap(BitmapFactory.decodeStream(inputStream), options);
        pnvLobby.setImageBitmap(BitmapFactory.decodeStream(inputStream));
    }

    private void handleIntent(Intent intent) {

        if (Intent.ACTION_VIEW.equals(intent.getAction())) {

            fileUri = intent.getData();
            panoOptions.inputType = intent.getIntExtra("inputType", VrPanoramaView.Options.TYPE_MONO);

        } else {
            panoOptions.inputType = VrPanoramaView.Options.TYPE_MONO;
        }

        if (loadImageAsyncTask != null) {
            loadImageAsyncTask.cancel(true);
        }

        loadImageAsyncTask = new LoadImageAsyncTask(context, this);
        loadImageAsyncTask.execute(Pair.create(fileUri, panoOptions));
    }
}
