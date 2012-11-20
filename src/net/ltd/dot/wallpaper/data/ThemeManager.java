package net.ltd.dot.wallpaper.data;

import java.util.ArrayList;
import java.util.List;

import org.simpleframework.xml.ElementList;

public class ThemeManager {

	@ElementList(name = "folder")
	public List<ThemeFolder> folderList;

	public List<ThemeFolder> getFolderList() {
		return folderList;
	}

	public void setFolderList(List<ThemeFolder> folderList) {
		this.folderList = folderList;
	}

	public void addFolder(ThemeFolder folder) {
		List<ThemeFolder> list = getFolderList();
		if (list == null) {
			list = new ArrayList<ThemeFolder>();
			setFolderList(list);
		}
		list.add(folder);
	}

}
