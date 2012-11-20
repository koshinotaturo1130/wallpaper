package net.ltd.dot.wallpaper;

import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.lang3.StringUtils;

import android.graphics.drawable.BitmapDrawable;
import android.util.Log;
import android.widget.ImageView;

public class Utility {

	public static ImageView setImageDrawableByAssets(ImageView view, String path) {
		return setImageDrawableByAssets(view, path, 0);
	}

	public static ImageView setImageDrawableByAssets(ImageView view, String path, int defaultImageId) {

		Log.d("Utitlity#setImageDrawableByAssets()", "path :" + path);

		InputStream in = null;
		try {

			if (StringUtils.isNotBlank(path)) {
				Log.d("Utitlity#setImageDrawableByAssets()", "path not null. load start");
				in = view.getContext().getAssets().open(path);
				BitmapDrawable bm = new BitmapDrawable(in);
				view.setImageDrawable(bm);
			}
			else if (defaultImageId != 0) {
				Log.d("Utitlity#setImageDrawableByAssets()", "path null. load default ");
				view.setImageResource(defaultImageId);
			}

		} catch (IOException e) {
			Log.d("Utitlity#setImageDrawableByAssets()", "image file load error!", e);

			if (defaultImageId != 0) {
				Log.d("Utitlity#setImageDrawableByAssets()", "path null. load default ");
				view.setImageResource(defaultImageId);
			}
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
				}
			}
		}

		return view;
	}

}
