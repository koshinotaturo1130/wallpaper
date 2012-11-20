package net.ltd.dot.wallpaper;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import net.ltd.dot.wallpaper.data.Theme;
import net.ltd.dot.wallpaper.data.ThemeFolder;
import net.ltd.dot.wallpaper.data.ThemeManager;

import org.simpleframework.xml.core.Persister;

import android.util.Log;

public class Test {


	public static void main(String[] args) {


		File file = new File("c:/work/eclipseWork_maven/Wallpapaer/res/raw/original_theme.xml");

		InputStreamReader reader = null;
		try {

			reader = new InputStreamReader(
						new BufferedInputStream(
								new FileInputStream(file)), "UTF-8");

			Persister persister = new Persister();
			ThemeManager tm = persister.read( ThemeManager.class, reader);

			Log.d( "TestMain", "xml parse start" );
			try {
				if( tm != null ){

					List<ThemeFolder> list = tm.getFolderList();
					for( ThemeFolder tf: list ){
						Log.d( "Folder", "name:" + tf.getName() );

						List<Theme> tList = tf.getThemeList();
						if( tList != null ){
							for(Theme t: tList){
								Log.d( "Theme", "name:" + t.getName() );
							}
						}
					}
				}

			} catch (Exception e) {
				Log.d( "TestMain", "xml parse failed", e );
			}


		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if( reader != null){
				try {
					reader.close();
				} catch (IOException e) {}
			}
		}



	}


	public static void showDirectory(File dir){

        if( dir.isDirectory()){

        	File[] listFiles = dir.listFiles();
        	if( listFiles != null ){

				Log.d( "showDirectory", "f:" + dir.getAbsolutePath() );

        		for(File f: listFiles){
        			if( f.isDirectory()){
        				showDirectory(f);
        			} else {
        				Log.d( "showDirectory", "f:" + f.getAbsolutePath() );
        			}
        		}
        	}

        } else {
			Log.d( "showDirectory", "f:" + dir.getAbsolutePath() );
        }

	}

}
