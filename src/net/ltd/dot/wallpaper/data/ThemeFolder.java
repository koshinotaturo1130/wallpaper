package net.ltd.dot.wallpaper.data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;

public class ThemeFolder {

	@Element(name = "id")
	public String id;

	@Element(name = "name")
	public String name;

	@Element(name = "description", required = false)
	public String description;

	@Element(name = "image", required = false)
	public String imageUrl;

	@Element(name = "registerDate", required = false)
	public Date downloadDate;

	@ElementList(name = "theme", required = false)
	public List<Theme> themeList;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public Date getDownloadDate() {
		return downloadDate;
	}

	public void setDownloadDate(Date downloadDate) {
		this.downloadDate = downloadDate;
	}

	public List<Theme> getThemeList() {
		return themeList;
	}

	public void setThemeList(List<Theme> themeList) {
		this.themeList = themeList;
	}

	public void addTheme(Theme theme) {
		List<Theme> list = getThemeList();
		if (list == null) {
			list = new ArrayList<Theme>();
			setThemeList(list);
		}
		list.add(theme);
	}

}
