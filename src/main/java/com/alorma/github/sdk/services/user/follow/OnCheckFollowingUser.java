package com.alorma.github.sdk.services.user.follow;

public interface OnCheckFollowingUser {
	void onCheckFollowUser(String username, boolean following);
}