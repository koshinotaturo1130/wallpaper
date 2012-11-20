package net.ltd.dot.wallpaper.data;

import java.util.Date;

public class Theme {

	public String id;
	public String name;
	public String description;
	public String imageUrl;
	public String tmbImageUrl;
	public Date downloadDate;

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
		return this.description;
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

	public String getTmbImageUrl() {
		return tmbImageUrl;
	}

	public void setTmbImageUrl(String tmbImageUrl) {
		this.tmbImageUrl = tmbImageUrl;
	}

	public Date getDownloadDate() {
		return downloadDate;
	}

	public void setDownloadDate(Date downloadDate) {
		this.downloadDate = downloadDate;
	}

}
