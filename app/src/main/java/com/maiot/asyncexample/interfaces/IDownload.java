package com.maiot.asyncexample.interfaces;

import android.graphics.Bitmap;

import com.maiot.asyncexample.misc.SingleImage;

@FunctionalInterface
public interface IDownload {
    void onDownloadDone(Bitmap bitmap, SingleImage imageDone);
}
