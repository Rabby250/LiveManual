package com.rabby250.livemanual.provider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

public class LiveManualProvider extends ContentProvider {

    private static final UriMatcher URI_MATCHER = initUriMatcher();
    private static final int CODE_PAGE = 1;
    private static final int CODE_PAGE_ID = 2;
    private static final int CODE_PAGE_CONTENT = 3;
    private static final int CODE_PAGE_CONTENT_ID = 4;

    private static UriMatcher initUriMatcher() {
        final UriMatcher matcher = new UriMatcher(UriMatcher.NO_MATCH);
        matcher.addURI(LiveManualContract.AUTHORITY,
                "page", CODE_PAGE);
        matcher.addURI(LiveManualContract.AUTHORITY,
                "page/#", CODE_PAGE_ID);
        matcher.addURI(LiveManualContract.AUTHORITY,
                "page_content", CODE_PAGE_CONTENT);
        matcher.addURI(LiveManualContract.AUTHORITY,
                "page_content/#", CODE_PAGE_CONTENT_ID);
        return matcher;
    }

    private static SQLiteDatabase sDatabase;

    private synchronized SQLiteDatabase getDatabase(
            @NonNull final Context context) {
        if (sDatabase == null) {
            final DatabaseHelper dbHelper = new DatabaseHelper(context);
            sDatabase = dbHelper.getWritableDatabase();
        }
        return sDatabase;
    }

    @Override
    public boolean onCreate() {
        return false;
    }

    @Override
    public void shutdown() {
        if (sDatabase != null && sDatabase.isOpen()) {
            sDatabase.close();
        }
        sDatabase = null;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        return null;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, ContentValues values) {
        return null;
    }

    @Override
    public int delete(@NonNull Uri uri, String selection, String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        return 0;
    }
}
