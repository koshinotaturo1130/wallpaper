package net.ltd.dot.wallpaper.data;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.text.SimpleDateFormat;

import net.ltd.dot.wallpaper.R;

import org.apache.commons.lang3.StringUtils;
import org.xmlpull.v1.XmlPullParser;

import android.content.res.Resources;
import android.util.Log;
import android.util.Xml;

public class DataLoader {

	private static final String DATE_FORMAT = "yyyy/MM/dd HH:mi:ss";

	public static ThemeManager loadByPullParser(Resources resource) throws Exception {

		Log.d("DataLoader", "loadByPullParser(Resource)");
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(
					new InputStreamReader(resource.openRawResource(R.raw.original_theme), "UTF-8"));
			return loadByPullParser(reader);
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
				}
			}
		}
	}

	public static ThemeManager loadByPullParser(Reader reader) throws Exception {

		XmlPullParser parser = Xml.newPullParser();
		parser.setInput(reader);

		ThemeManager manager = new ThemeManager();
		ThemeFolder currentFolder = new ThemeFolder();

		for (int e = parser.getEventType(); e != XmlPullParser.END_DOCUMENT; e = parser.next()) {

			String tagName = parser.getName();

			switch (e) {
				case XmlPullParser.START_TAG:
					Log.d("PullParser", "tagName :" + tagName + ": start");

					if (StringUtils.equals(tagName, "folder")) {
						currentFolder = loadThemeFolder(parser);
					} else if (StringUtils.equals(tagName, "theme")) {
						currentFolder.addTheme(loadTheme(parser));
					}
					break;

				case XmlPullParser.END_TAG:

					Log.d("PullParser", "tagName :" + tagName + ": end");

					if (StringUtils.equals(tagName, "folder")) {
						manager.addFolder(currentFolder);
					}

					break;
			}
		}

		return manager;
	}

	private static Theme loadTheme(XmlPullParser parser) throws Exception {

		Theme theme = new Theme();
		theme.setId(parser.getAttributeValue(null, "id"));
		theme.setName(parser.getAttributeValue(null, "name"));
		theme.setDescription(parser.getAttributeValue(null, "description"));
		theme.setImageUrl(parser.getAttributeValue(null, "image"));
		theme.setTmbImageUrl(parser.getAttributeValue(null, "tmb_image"));
		String strDate = parser.getAttributeValue(null, "date");
		if (StringUtils.isNotBlank(strDate)) {
			SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
			theme.setDownloadDate(sdf.parse(strDate));
		}

		return theme;
	}

	private static ThemeFolder loadThemeFolder(XmlPullParser parser) throws Exception {

		ThemeFolder folder = new ThemeFolder();
		folder.setId(parser.getAttributeValue(null, "id"));
		folder.setName(parser.getAttributeValue(null, "name"));
		folder.setDescription(parser.getAttributeValue(null, "description"));
		folder.setImageUrl(parser.getAttributeValue(null, "image"));
		String strDate = parser.getAttributeValue(null, "date");
		if (StringUtils.isNotBlank(strDate)) {
			SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
			folder.setDownloadDate(sdf.parse(strDate));
		}

		return folder;
	}

}
