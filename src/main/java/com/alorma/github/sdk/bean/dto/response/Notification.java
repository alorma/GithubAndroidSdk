package com.alorma.github.sdk.bean.dto.response;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.util.Comparator;

/**
 * Created by Bernat on 18/02/2015.
 */
public class Notification extends ShaUrl implements Comparable<Notification>{
	public long id;
	public Repo repository;
	public NotificationSubject subject;
	public String reason;
	public boolean unread;
	public String updated_at;
	public String last_read_at;
	
	public Long adapter_repo_parent_id;

	@Override
	public int compareTo(Notification another) {
		if (this.adapter_repo_parent_id > another.adapter_repo_parent_id) {
			return 1;
		} else if (this.adapter_repo_parent_id < another.adapter_repo_parent_id) {
			return -1;
		} else {
			String currentDate = this.updated_at;
			String otherDate = another.updated_at;

			DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy-MM-dd'T'HH:mm:ss'Z'");

			DateTime dtCurrent = formatter.parseDateTime(currentDate);
			DateTime dtOther = formatter.parseDateTime(otherDate);
			
			return dtCurrent.compareTo(dtOther);
		}
	}

	public static class Comparators {
		public static Comparator<Notification> REPO_ID = new Comparator<Notification>() {
			@Override
			public int compare(Notification notification, Notification notification2) {
				return notification.compareTo(notification2);
			}
		};
	}
}
