package com.bishedemo.imageloader.core.display;


import android.graphics.Bitmap;

import com.bishedemo.imageloader.core.assist.LoadedFrom;
import com.bishedemo.imageloader.core.imageaware.ImageAware;


/**
 * 仅仅是在{@link ImageAware}里面展示Bitmap
 * @author renhui
 */
public class SimpleBitmapDisplayer implements BitmapDisplayer {

	@Override
	public void display(Bitmap bitmap, ImageAware imageAware, LoadedFrom loadedFrom) {
		imageAware.setImageBitmap(bitmap);
	}
}
