package com.ness.virtualtour;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;
import android.util.Pair;

import com.google.vr.sdk.widgets.pano.VrPanoramaView;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class LoadImageAsyncTask extends AsyncTask<Pair<Uri, VrPanoramaView.Options>, Void, Boolean> {
    public static final String TAG = "TAG";
    private Context context;
    private ILoadImage iImageLoaderListener;
    private int widgetPosition;


    public LoadImageAsyncTask(Context context, ILoadImage iImageLoaderListener, int widgetPosition) {
        this.context = context;
        this.iImageLoaderListener = iImageLoaderListener;
        this.widgetPosition = widgetPosition;
    }


    @Override
    protected Boolean doInBackground(Pair<Uri, VrPanoramaView.Options>... fileInformation) {
        VrPanoramaView.Options panoOptions = null;
        InputStream istr = null;
        if (fileInformation == null || fileInformation.length < 1 || fileInformation[0] == null || fileInformation[0].first == null) {
            AssetManager assetManager = context.getAssets();
            try {
                switch (widgetPosition) {
                    case 0:
                        istr = assetManager.open("lobby.jpg");
                        break;
                    case 1:
                        istr = assetManager.open("reception.jpg");
                        break;
                    case 2:
                        istr = assetManager.open("inside_office_vt.jpg");
                        break;
                    case 3:
                        istr = assetManager.open("canteen.jpg");
                        break;
                }

                panoOptions = new VrPanoramaView.Options();
                panoOptions.inputType = VrPanoramaView.Options.TYPE_STEREO_OVER_UNDER;
            } catch (IOException e) {
                Log.e(TAG, "Could not decode default bitmap: " + e);
                return false;
            }
        } else {
            try {
                istr = new FileInputStream(new File(fileInformation[0].first.getPath()));
                panoOptions = fileInformation[0].second;
            } catch (IOException e) {
                Log.e(TAG, "Could not load file: " + e);
                return false;
            }
        }

        iImageLoaderListener.loadImage(istr, panoOptions, widgetPosition);

        try {
            istr.close();
        } catch (IOException e) {
            Log.e(TAG, "Could not close input stream: " + e);
        }

        return true;
    }
}
