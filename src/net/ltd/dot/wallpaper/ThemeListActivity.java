package net.ltd.dot.wallpaper;

import java.util.List;

import net.ltd.dot.wallpaper.data.DataLoader;
import net.ltd.dot.wallpaper.data.Theme;
import net.ltd.dot.wallpaper.data.ThemeFolder;
import net.ltd.dot.wallpaper.data.ThemeManager;

import org.apache.commons.lang3.StringUtils;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class ThemeListActivity extends Activity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_theme_list);

		try {

			Intent intent = getIntent();
			String folderId = intent.getStringExtra("folder");
			if (StringUtils.isBlank(folderId)) {
				folderId = "folder1";
			}

			ThemeFolder folder = getThemeFolder(folderId);
			List<Theme> list = folder.getThemeList();
			ThemeAdapter adapter = new ThemeAdapter(
					getApplicationContext(),
					R.xml.theme_list_item,
					list);

			ListView listView = (ListView) findViewById(R.id.layout_theme_list_theme_list);
			listView.setAdapter(adapter);
			listView.setOnItemClickListener(new ThemeSelectListener(this));

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private ThemeFolder getThemeFolder(String folderId) throws Exception {

		ThemeManager tm = DataLoader.loadByPullParser(getResources());

		List<ThemeFolder> folderList = tm.getFolderList();
		if (folderList == null) {
			return null;
		}

		for (ThemeFolder tf : folderList) {
			if (StringUtils.equals(folderId, tf.getId())) {
				return tf;
			}
		}

		return null;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

	class ThemeSelectListener implements OnItemClickListener {

		private ThemeListActivity activity;

		ThemeSelectListener(ThemeListActivity activity) {
			this.activity = activity;
		}

		@Override
		public void onItemClick(AdapterView<?> adapter, View view, int position, long id) {

			Log.d("ThemeSelectListener", "onItemClick(" + position + ")");

			final Theme theme = (Theme) adapter.getItemAtPosition(position);

			LayoutInflater inflater = getLayoutInflater();
			View layout = inflater.inflate(R.xml.choose_item, null);

			ImageView image = (ImageView) layout.findViewById(R.id.layout_choose_item_image);
			Utility.setImageDrawableByAssets(image, theme.getImageUrl(), R.drawable.np);

			AlertDialog.Builder dialog = new AlertDialog.Builder(this.activity);
			dialog.setTitle(theme.getName());
			// dialog.setMessage("メッセージ");
			dialog.setView(layout);
			dialog.setCancelable(true);
			dialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {

				@Override
				public void onClick(DialogInterface dialog, int id) {
					// 第3引数は、表示期間（LENGTH_SHORT、または、LENGTH_LONG）
					Toast.makeText(activity, "テーマ選択完了:" + theme.getId(), Toast.LENGTH_LONG).show();
					dialog.dismiss();
				}
			});

			dialog.setNegativeButton("No", new DialogInterface.OnClickListener() {

				@Override
				public void onClick(DialogInterface dialog, int id) {
					dialog.cancel();
				}
			});

			dialog.show();
		}

	}

	class ThemeAdapter extends ArrayAdapter<Theme> {

		private LayoutInflater inflater;
		private List<Theme> list;

		public ThemeAdapter(Context context, int resource, List<Theme> list) {
			super(context, resource, list);

			this.list = list;
			this.inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {

			View view = convertView;
			if (view == null) {
				view = inflater.inflate(R.xml.theme_list_item, null);
			}

			Theme theme = list.get(position);
			ImageView imageView = (ImageView) view.findViewById(R.id.layout_theme_list_item_image);
			Utility.setImageDrawableByAssets(imageView, theme.getTmbImageUrl(), R.drawable.np);

			Log.w("ThemeFolderAdapter", "title : " + theme.getName());
			TextView titleView = (TextView) view.findViewById(R.id.layout_theme_list_item_title);
			titleView.setText(theme.getName());

			Log.w("ThemeFolderAdapter", "description : " + theme.getDescription());
			TextView descriptionView = (TextView) view.findViewById(R.id.layout_theme_list_item_description);
			descriptionView.setText(theme.getDescription());

			return view;
		}

	}
}