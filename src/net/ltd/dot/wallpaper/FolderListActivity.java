package net.ltd.dot.wallpaper;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import net.ltd.dot.wallpaper.data.DataLoader;
import net.ltd.dot.wallpaper.data.ThemeFolder;
import net.ltd.dot.wallpaper.data.ThemeManager;

import org.apache.commons.lang3.StringUtils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class FolderListActivity extends Activity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_folder_list);

		try {
			ThemeManager tm = DataLoader.loadByPullParser(getResources());
			ThemeFolderAdapter adapter = new ThemeFolderAdapter(
					getApplicationContext(), R.xml.photo_list_item, tm.getFolderList());

			ListView listView = (ListView) findViewById(R.id.layout_folder_list_folder_list);
			listView.setAdapter(adapter);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

	class ThemeFolderAdapter extends ArrayAdapter<ThemeFolder> {

		private Context context;
		private LayoutInflater inflater;
		private List<ThemeFolder> list;

		public ThemeFolderAdapter(Context context, int resource, List<ThemeFolder> list) {
			super(context, resource, list);

			this.context = context;
			this.list = list;
			inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {

			View view = convertView;
			if (view == null) {
				view = inflater.inflate(R.xml.folder_list_item, null);
			}

			final ThemeFolder folder = list.get(position);

			view.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					Intent intent = new Intent();
					intent.setClassName("net.ltd.dot.wallpaper", "net.ltd.dot.wallpaper.ThemeListActivity");
					intent.putExtra("folder", folder.getId());
					startActivity(intent);
				}
			});

			ImageView imageView = (ImageView) view.findViewById(R.id.layout_folder_list_item_image);
			InputStream in = null;
			try {
				String imageFileName = folder.getImageUrl();
				if (StringUtils.isNotBlank(imageFileName)) {
					in = context.getAssets().open(folder.getImageUrl());
					BitmapDrawable bm = new BitmapDrawable(in);
					imageView.setImageDrawable(bm);
				}
			} catch (IOException e) {
				Log.w("ThemeFolderAdapter", "image file load failed", e);
			} finally {
				if (in != null) {
					try {
						in.close();
					} catch (IOException e) {
					}
				}
			}

			Log.w("ThemeFolderAdapter", "title : " + folder.getName());
			TextView titleView = (TextView) view.findViewById(R.id.layout_folder_list_item_title);
			titleView.setText(folder.getName());

			Log.w("ThemeFolderAdapter", "description : " + folder.getDescription());
			TextView descriptionView = (TextView) view.findViewById(R.id.layout_folder_list_item_description);
			descriptionView.setText(folder.getDescription());

			return view;
		}

	}

}
