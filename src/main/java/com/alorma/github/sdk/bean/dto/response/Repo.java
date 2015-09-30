package com.alorma.github.sdk.bean.dto.response;

import android.os.BadParcelableException;
import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

/**
 * Created by Bernat on 13/07/2014.
 */
public class Repo extends ShaUrl {

	public boolean fork;

	@SerializedName("private")
	public boolean isPrivate;

	public Date created_at;

	public Date pushed_at;

	public Date updated_at;

	public int forks_count;

	public long id;

	public Repo parent;

	public Repo source;

	public String clone_url;

	public String description;

	public String homepage;

	public String git_url;

	public String language;

	public String default_branch;

	public String mirror_url;

	public String name;

	public String full_name;

	public String ssh_url;

	public String svn_url;

	public User owner;

	public int stargazers_count;

	public int subscribers_count;

	public int network_count;

	public int watchers_count;

	public int size;

	public int open_issues_count;

	public boolean has_issues;

	public boolean has_downloads;

	public boolean has_wiki;

	public Permissions permissions;

	public License license;

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder("Repo{");
		sb.append("fork=").append(fork);
		sb.append(", isPrivate=").append(isPrivate);
		sb.append(", created_at=").append(created_at);
		sb.append(", pushed_at=").append(pushed_at);
		sb.append(", updated_at=").append(updated_at);
		sb.append(", forks_count=").append(forks_count);
		sb.append(", id=").append(id);
		sb.append(", parent=").append(parent);
		sb.append(", source=").append(source);
		sb.append(", clone_url='").append(clone_url).append('\'');
		sb.append(", description='").append(description).append('\'');
		sb.append(", homepage='").append(homepage).append('\'');
		sb.append(", git_url='").append(git_url).append('\'');
		sb.append(", html_url='").append(html_url).append('\'');
		sb.append(", language='").append(language).append('\'');
		sb.append(", default_branch='").append(default_branch).append('\'');
		sb.append(", mirror_url='").append(mirror_url).append('\'');
		sb.append(", name='").append(name).append('\'');
		sb.append(", full_name='").append(full_name).append('\'');
		sb.append(", ssh_url='").append(ssh_url).append('\'');
		sb.append(", svn_url='").append(svn_url).append('\'');
		sb.append(", url='").append(url).append('\'');
		sb.append(", owner=").append(owner);
		sb.append(", stargazers_count=").append(stargazers_count);
		sb.append(", subscribers_count=").append(subscribers_count);
		sb.append(", subscribers_count=").append(network_count);
		sb.append(", watchers_count=").append(watchers_count);
		sb.append(", size=").append(size);
		sb.append(", open_issues_count=").append(open_issues_count);
		sb.append(", has_issues=").append(has_issues);
		sb.append(", has_downloads=").append(has_downloads);
		sb.append(", has_wiki=").append(has_wiki);
		sb.append(", permissions=").append(permissions);
		sb.append('}');
		return sb.toString();
	}

	public boolean canPull() {
		return permissions != null && permissions.pull;
	}

	public boolean canPush() {
		return permissions != null && permissions.push;
	}
	public boolean canAdmin() {
		return permissions != null && permissions.admin;
	}

	public Repo() {
		super();
	}

	protected Repo(Parcel in) {
		super(in);
		fork = in.readByte() != 0x00;
		isPrivate = in.readByte() != 0x00;
		long tmpCreated_at = in.readLong();
		created_at = tmpCreated_at != -1 ? new Date(tmpCreated_at) : null;
		long tmpPushed_at = in.readLong();
		pushed_at = tmpPushed_at != -1 ? new Date(tmpPushed_at) : null;
		long tmpUpdated_at = in.readLong();
		updated_at = tmpUpdated_at != -1 ? new Date(tmpUpdated_at) : null;
		forks_count = in.readInt();
		id = in.readLong();
		parent = in.readParcelable(Repo.class.getClassLoader());
		source = in.readParcelable(Repo.class.getClassLoader());
		clone_url = in.readString();
		description = in.readString();
		homepage = in.readString();
		git_url = in.readString();
		html_url = in.readString();
		language = in.readString();
		default_branch = in.readString();
		mirror_url = in.readString();
		name = in.readString();
		full_name = in.readString();
		ssh_url = in.readString();
		svn_url = in.readString();
		url = in.readString();
		owner = in.readParcelable(User.class.getClassLoader());
		stargazers_count = in.readInt();
		subscribers_count = in.readInt();
		network_count = in.readInt();
		watchers_count = in.readInt();
		size = in.readInt();
		open_issues_count = in.readInt();
		has_issues = in.readByte() != 0x00;
		has_downloads = in.readByte() != 0x00;
		has_wiki = in.readByte() != 0x00;
		permissions = in.readParcelable(Permissions.class.getClassLoader());
	}

	@Override
	public int describeContents() {
		return super.describeContents();
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		super.writeToParcel(dest, flags);
		dest.writeByte((byte) (fork ? 0x01 : 0x00));
		dest.writeByte((byte) (isPrivate ? 0x01 : 0x00));
		dest.writeLong(created_at != null ? created_at.getTime() : -1L);
		dest.writeLong(pushed_at != null ? pushed_at.getTime() : -1L);
		dest.writeLong(updated_at != null ? updated_at.getTime() : -1L);
		dest.writeInt(forks_count);
		dest.writeLong(id);
		dest.writeParcelable(parent, flags);
		dest.writeParcelable(source, flags);
		dest.writeString(clone_url);
		dest.writeString(description);
		dest.writeString(homepage);
		dest.writeString(git_url);
		dest.writeString(html_url);
		dest.writeString(language);
		dest.writeString(default_branch);
		dest.writeString(mirror_url);
		dest.writeString(name);
		dest.writeString(full_name);
		dest.writeString(ssh_url);
		dest.writeString(svn_url);
		dest.writeString(url);
		dest.writeParcelable(owner, flags);
		dest.writeInt(stargazers_count);
		dest.writeInt(subscribers_count);
		dest.writeInt(network_count);
		dest.writeInt(watchers_count);
		dest.writeInt(size);
		dest.writeInt(open_issues_count);
		dest.writeByte((byte) (has_issues ? 0x01 : 0x00));
		dest.writeByte((byte) (has_downloads ? 0x01 : 0x00));
		dest.writeByte((byte) (has_wiki ? 0x01 : 0x00));
		dest.writeParcelable(permissions, flags);
	}

	@SuppressWarnings("unused")
	public static final Parcelable.Creator<Repo> CREATOR = new Parcelable.Creator<Repo>() {
		@Override
		public Repo createFromParcel(Parcel in) {
			return new Repo(in);
		}

		@Override
		public Repo[] newArray(int size) {
			return new Repo[size];
		}
	};
}