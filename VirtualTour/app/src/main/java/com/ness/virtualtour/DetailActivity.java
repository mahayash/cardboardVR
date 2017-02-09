package com.ness.virtualtour;

import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Pair;
import android.widget.TextView;

import com.google.vr.sdk.widgets.pano.VrPanoramaView;

import java.io.InputStream;


public class DetailActivity extends AppCompatActivity implements ILoadImage {

    private Uri fileUri;
    private VrPanoramaView pnvView;
    private VrPanoramaView.Options panoOptions = new VrPanoramaView.Options();
    private LoadImageAsyncTask backgroundImageLoaderTask;
    private Context context;
    private int widgetPosition;
    private InfraDO infraInfo;
    private TextView txtTitle, txtDescription;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this;
        widgetPosition = getIntent().getIntExtra("widgetPosition", 0);
        infraInfo = (InfraDO) getIntent().getSerializableExtra("Information");
        InitView();
    }


    private void InitView() {

        pnvView = (VrPanoramaView) findViewById(R.id.pnv_view);

        txtTitle = (TextView) findViewById(R.id.txt_lobby);
        txtTitle.setText(infraInfo.getText());

        txtDescription = (TextView) findViewById(R.id.txt_description_detail);
        txtDescription.setText(infraInfo.getDescription());

        handleIntent(getIntent(), widgetPosition);

    }


    private void handleIntent(Intent intent, int widgetPosition) {

        if (Intent.ACTION_VIEW.equals(intent.getAction())) {
            fileUri = intent.getData();
            panoOptions.inputType = intent.getIntExtra("inputType", VrPanoramaView.Options.TYPE_MONO);
        } else {
            fileUri = null;
            panoOptions.inputType = VrPanoramaView.Options.TYPE_STEREO_OVER_UNDER;
        }

        if (backgroundImageLoaderTask != null) {
            backgroundImageLoaderTask.cancel(true);
        }
        backgroundImageLoaderTask = new LoadImageAsyncTask(context, this, widgetPosition);
        backgroundImageLoaderTask.execute(Pair.create(fileUri, panoOptions));
    }


    @Override
    public void loadImage(InputStream inputStream, VrPanoramaView.Options options, int widgetPosition) {

        pnvView.loadImageFromBitmap(BitmapFactory.decodeStream(inputStream), options);
    }
}
