package net.ltd.dot.wallpaper;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;

public class PhotoListActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
    	super.onCreate(savedInstanceState);
    	setContentView(R.layout.activity_photo_list);

    	List<Bitmap> list = loadExternalThumbnails();
    	BitmapAdapter adapter = new BitmapAdapter(
               getApplicationContext(), R.xml.photo_list_item,
               list);

		GridView grid = (GridView)findViewById(R.id.photo_activity_gridView);
		grid.setAdapter(adapter);

    }


	private List<Bitmap> loadExternalThumbnails() {

		List<Bitmap> list = new ArrayList<Bitmap>();

		// ContentResolver取得＆イメージ取得
		ContentResolver cr = getContentResolver();
		Uri uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
		Cursor c = null;
		try {
			c = managedQuery(uri, null, null, null, null);

			c.moveToFirst();
			for (int k = 0; k < c.getCount(); k++) {
				// ID取得
				long id = c.getLong(c.getColumnIndexOrThrow("_id"));
				list.add(
						MediaStore.Images.Thumbnails.getThumbnail(cr, id, MediaStore.Images.Thumbnails.MICRO_KIND, null));

				c.moveToNext();
			}

			return list;

		} finally {
			if( c != null){
				c.close();
			}
		}
	}


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }

    class BitmapAdapter extends ArrayAdapter<Bitmap> {

	    private int resourceId;

	    public BitmapAdapter(Context context, int resource, List<Bitmap> objects) {
	        super(context, resource, objects);
	        resourceId = resource;
	    }

	    @Override
	    public View getView(int position, View convertView, ViewGroup parent) {

	        if (convertView == null) {
	            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	            convertView = inflater.inflate(resourceId, null);
	        }

	        ImageView view = (ImageView) convertView;
	        view.setImageBitmap(getItem(position));

	        return view;
	    }

	}
}
