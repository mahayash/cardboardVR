package com.ness.virtualtour;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;

import java.io.IOException;
import java.io.InputStream;


public class LoadImageAsyncTask2 extends AsyncTask<String, Void, InputStream> {

    private Context mContext;
    private ILoadImage iLoadImageListener;

    public LoadImageAsyncTask2(Context mContext, ILoadImage iLoadImageListener) {
        this.mContext = mContext;
        this.iLoadImageListener = iLoadImageListener;
    }

    @Override
    protected InputStream doInBackground(String... params) {

        InputStream ims = null;
        try {
            // get input stream
            ims = mContext.getAssets().open("andes.jpg");
            // load image as Drawable
            Drawable d = Drawable.createFromStream(ims, null);
            // set image to ImageView
        } catch (IOException ex) {


        }
        return ims;
    }

    @Override
    protected void onPostExecute(InputStream inputStream) {
        super.onPostExecute(inputStream);
        iLoadImageListener.loadImage(inputStream, null);
    }
}
