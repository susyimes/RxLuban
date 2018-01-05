package com.susyimes.funbox.rx2luban.luban.luban;

import android.graphics.Bitmap;

import java.io.File;


class LubanBuilder {

    int maxSize;

    int maxWidth;

    int maxHeight;

    File cacheDir;

    Bitmap.CompressFormat compressFormat = Bitmap.CompressFormat.JPEG;

    int gear = Luban.THIRD_GEAR;

    LubanBuilder(File cacheDir) {
        this.cacheDir = cacheDir;
    }

}
