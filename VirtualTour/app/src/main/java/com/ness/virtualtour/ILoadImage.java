package com.ness.virtualtour;

import com.google.vr.sdk.widgets.pano.VrPanoramaView;

import java.io.InputStream;

public interface ILoadImage {

    void loadImage(InputStream inputStream, VrPanoramaView.Options options);

}
