package net.ltd.dot.wallpaper;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		Button button1 = (Button) findViewById(R.id.button_test1);
		button1.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				Intent intent = new Intent();
				intent.setClassName("net.ltd.dot.wallpaper", "net.ltd.dot.wallpaper.PhotoListActivity");
				startActivity(intent);
			}
		});

		Button folderListButton = (Button) findViewById(R.id.layout_main_button_folder_list);
		folderListButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setClassName("net.ltd.dot.wallpaper", "net.ltd.dot.wallpaper.FolderListActivity");
				startActivity(intent);
			}
		});

		Button themeListButton = (Button) findViewById(R.id.layout_main_button_theme_list);
		themeListButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setClassName("net.ltd.dot.wallpaper", "net.ltd.dot.wallpaper.ThemeListActivity");
				// startActivity(intent);
				intent.putExtra("folder", "folder2");
				startActivityForResult(intent, 0);
			}
		});

		Button frameTestButton = (Button) findViewById(R.id.layout_main_button_frame_test);
		frameTestButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setClassName("net.ltd.dot.wallpaper", "net.ltd.dot.wallpaper.FrameLayoutTestActivity");
				startActivity(intent);
			}
		});

		Button topButton = (Button) findViewById(R.id.layout_main_button_top);
		topButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setClassName("net.ltd.dot.wallpaper", "net.ltd.dot.wallpaper.TopActivity");
				startActivity(intent);
			}
		});

	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		Log.d("Theme", "name:");
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

}