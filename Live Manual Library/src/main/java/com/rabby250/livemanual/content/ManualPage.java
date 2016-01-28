package com.rabby250.livemanual.content;

import android.content.ContentValues;
import android.database.Cursor;
import android.support.annotation.NonNull;

import org.json.JSONException;
import org.json.JSONObject;

public class ManualPage {

    public static final String TABLE_NAME = "ManualPage";

    // _id for local DB
    private long mId = -1;
    // The ID used by server to identify this page
    private String mPageId = "";
    // The title of this page
    private String mTitle = "";
    // Server-side status of this page
    private int mStatus = 0;
    // Marks set on this page by users
    private int mMarks = 0;
    // Minimum app version required by this page
    private int mMinVerCode = 0;
    private String mMinVerName = "";
    // UI components associated with this page
    private String[] mRelatedUi = new String[0];
    // TODO: page contents

    // Direct initialization not allowed
    private ManualPage() {}

    public static ManualPage fromCursor(@NonNull final Cursor cursor) {
        final ManualPage page = new ManualPage();
        page.setId(cursor.getLong(PageColumns.INDEX_ID));
        page.setPageId(cursor.getString(PageColumns.INDEX_PAGE_ID));
        page.setTitle(cursor.getString(PageColumns.INDEX_TITLE));
        page.setStatus(cursor.getInt(PageColumns.INDEX_STATUS));
        page.setMarks(cursor.getInt(PageColumns.INDEX_MARKS));
        page.setMinVerCode(cursor.getInt(
                PageColumns.INDEX_MIN_VER_CODE));
        page.setMinVerName(cursor.getString(
                PageColumns.INDEX_MIN_VER_NAME));
        page.setRelatedUi(cursor.getString(
                PageColumns.INDEX_RELATED_UI));
        return page;
    }

    public static ManualPage fromJsonObject(
            @NonNull final JSONObject json) {
        final ManualPage page = new ManualPage();
        // Fallback values should match the initial values
        // JSON objects should not contain _id
        page.setPageId(json.optString(PageColumns.PAGE_ID, ""));
        page.setTitle(json.optString(PageColumns.TITLE, ""));
        page.setStatus(json.optInt(PageColumns.STATUS, 0));
        page.setMarks(json.optInt(PageColumns.MARKS, 0));
        page.setMinVerCode(json.optInt(PageColumns.MIN_VER_CODE, 0));
        page.setMinVerName(json.optString(PageColumns.MIN_VER_NAME, ""));
        page.setRelatedUi(json.optString(PageColumns.RELATED_UI, ""));
        return page;
    }

    private String packRelatedUi() {
        if (mRelatedUi.length < 1) {
            return "";
        }
        final StringBuilder sb = new StringBuilder();
        for (String ui : mRelatedUi) {
            sb.append(ui).append(',');
        }
        sb.setLength(sb.length() - 1); // Trim the last comma
        return sb.toString();
    }

    public ContentValues toContentValues() {
        final ContentValues cv = new ContentValues();
        // _id should not be put into CVs
        cv.put(PageColumns.PAGE_ID, mPageId);
        cv.put(PageColumns.TITLE, mTitle);
        cv.put(PageColumns.STATUS, mStatus);
        cv.put(PageColumns.MARKS, mMarks);
        cv.put(PageColumns.MIN_VER_CODE, mMinVerCode);
        cv.put(PageColumns.MIN_VER_NAME, mMinVerName);
        cv.put(PageColumns.RELATED_UI, packRelatedUi());
        return cv;
    }

    public JSONObject toJsonObject() {
        final JSONObject json = new JSONObject();
        try {
            // _id should not be put into JSON objects
            json.put(PageColumns.PAGE_ID, mPageId);
            json.put(PageColumns.TITLE, mTitle);
            json.put(PageColumns.STATUS, mStatus);
            json.put(PageColumns.MARKS, mMarks);
            json.put(PageColumns.MIN_VER_CODE, mMinVerCode);
            json.put(PageColumns.MIN_VER_NAME, mMinVerName);
            json.put(PageColumns.RELATED_UI, packRelatedUi());
        } catch (JSONException e) {
            // Unreachable since we never put NaNs or infinite numbers
            e.printStackTrace();
        }
        return json;
    }

    public void setId(final long id) {
        mId = id >= 0 ? id : -1;
    }

    public void setPageId(final String pageId) {
        mPageId = pageId != null ? pageId : "";
    }

    public void setTitle(final String title) {
        mTitle = title != null ? title : "";
    }

    public void setStatus(final int status) {
        mStatus = status;
    }

    public void setMarks(final int marks) {
        mMarks = marks;
    }

    public void setMinVerCode(final int minVerCode) {
        mMinVerCode = minVerCode > 0 ? minVerCode : 0;
    }

    public void setMinVerName(final String minVerName) {
        mMinVerName = minVerName != null ? minVerName : "";
    }

    public void setRelatedUi(final String packedRelatedUi) {
        mRelatedUi = packedRelatedUi != null ?
                packedRelatedUi.split(",") : new String[0];
    }
}
