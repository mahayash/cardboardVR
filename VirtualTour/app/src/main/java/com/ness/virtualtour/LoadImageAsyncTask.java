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
import java.io.InputStream;

public class LoadImageAsyncTask extends AsyncTask<Pair<Uri, VrPanoramaView.Options>, Void, Boolean> {

    private Context mContext;
    private static final String TAG = LoadImageAsyncTask.class.getSimpleName();
    private ILoadImage iLoadImageListener;

    public LoadImageAsyncTask(Context mContext, ILoadImage iLoadImageListener) {
        this.mContext = mContext;
        this.iLoadImageListener = iLoadImageListener;
    }

    @Override
    protected Boolean doInBackground(Pair<Uri, VrPanoramaView.Options>... fileInformation) {

        VrPanoramaView.Options panoOptions = null;
        InputStream inputStream = null;

        if (fileInformation == null || fileInformation.length < 1 || fileInformation[0] == null || fileInformation[0].first == null) {

            AssetManager assetManager = mContext.getAssets();
            try {
                inputStream = assetManager.open("andes.jpg");
                panoOptions = new VrPanoramaView.Options();
                panoOptions.inputType = VrPanoramaView.Options.TYPE_STEREO_OVER_UNDER;

            } catch (Exception ex) {
                Log.d(TAG, "doInBackground: " + ex);
            }
        } else {

            try {
                inputStream = new FileInputStream(new File(fileInformation[0].first.getPath()));
                panoOptions = fileInformation[0].second;

            } catch (Exception ex) {
                Log.d(TAG, "doInBackground: " + ex);
            }
        }


        try {

            inputStream.close();
        } catch (Exception ex) {
            Log.d(TAG, "doInBackground: " + ex);
        }

        iLoadImageListener.loadImage(inputStream, panoOptions);
        return true;
    }

    @Override
    protected void onPostExecute(Boolean inputStream) {
        super.onPostExecute(inputStream);

    }
}
