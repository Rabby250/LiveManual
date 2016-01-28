package com.rabby250.livemanual.content;

import android.provider.BaseColumns;

public interface PageColumns {
    String _ID = BaseColumns._ID;
    String PAGE_ID = "page_id";
    String TITLE = "title";
    String STATUS = "status";
    String MARKS = "marks";
    String MIN_VER_CODE = "min_ver_code";
    String MIN_VER_NAME = "min_ver_name";
    String RELATED_UI = "related_ui";

    int INDEX_ID = 0;
    int INDEX_PAGE_ID = 1;
    int INDEX_TITLE = 2;
    int INDEX_STATUS = 3;
    int INDEX_MARKS = 4;
    int INDEX_MIN_VER_CODE = 5;
    int INDEX_MIN_VER_NAME = 6;
    int INDEX_RELATED_UI = 7;

    String[] FULL_PROJECTION = {
            _ID, PAGE_ID, TITLE, STATUS,
            MARKS, MIN_VER_CODE, MIN_VER_NAME, RELATED_UI
    };

    // SQLite rules; use INDEX_* to access
    String[] RULES = {
            _ID + " INTEGER PRIMARY KEY AUTOINCREMENT",
            PAGE_ID + " TEXT NOT NULL",
            TITLE + " TEXT NOT NULL",
            STATUS + " INTEGER NOT NULL DEFAULT 0",
            MARKS + " INTEGER NOT NULL DEFAULT 0",
            MIN_VER_CODE + " INTEGER NOT NULL DEFAULT 0",
            MIN_VER_NAME + " TEXT NOT NULL",
            RELATED_UI + " TEXT NOT NULL"
    };
}
