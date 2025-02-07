package com.maiot.asyncexample.interfaces;

import android.graphics.Bitmap;

public interface IDownload {
    void onDownloadDone(Bitmap bitmap, String urlDone);
}
