package com.alorma.github.sdk.bean.dto.response;

import android.support.annotation.NonNull;

public class GitTreeEntry extends ShaUrl implements Comparable<GitTreeEntry>{

    public String path;
    public String mode;
    public int size;
    public GitTreeType type;


    @Override
    public int compareTo(@NonNull GitTreeEntry another) {
        if (another.type == type) {
            return path.compareTo(another.path);
        } else if (another.type == GitTreeType.tree) {
            return 1;
        } else {
            return another.path.compareTo(path);
        }
    }
}
