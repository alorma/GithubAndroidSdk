package com.alorma.github.sdk.bean.dto.response;

import java.util.Comparator;
import java.util.List;

/**
 * Created by Bernat on 20/07/2014.
 */
public class Content extends ShaUrl implements Comparable<Content> {
	public ContentType type;
	public int size;
	public String name;
	public String content;
	public String path;
	public String git_url;
	public Links _links;
	public String encoding;
	public List<Content> children;
	public Content parent;

	public boolean isDir() {
		return ContentType.dir.equals(type);
	}

	public boolean isFile() {
		return ContentType.file.equals(type);
	}

	public boolean isSubmodule() {
		return ContentType.symlink.equals(type);
	}

	@Override
	public int compareTo(Content another) {
		if (another.type == type) {
			return another.path.compareTo(path);
		} else if (another.type == ContentType.dir) {
			return 1;
		} else {
			return another.path.compareTo(path);
		}
	}
}
