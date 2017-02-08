package com.ness.virtualtour;

import android.util.Log;

import com.google.vr.sdk.widgets.pano.VrPanoramaEventListener;

public class VrActivityActionListener extends VrPanoramaEventListener {

    public static final String TAG = VrActivityActionListener.class.getSimpleName();

    @Override
    public void onLoadSuccess() {
        super.onLoadSuccess();
        Log.d(TAG, "onLoadSuccess: ");
    }

    @Override
    public void onLoadError(String errorMessage) {
        super.onLoadError(errorMessage);
        Log.d(TAG, "onLoadError: ");
    }
}
