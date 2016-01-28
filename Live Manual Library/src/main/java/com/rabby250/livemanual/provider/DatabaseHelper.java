package com.rabby250.livemanual.provider;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.NonNull;

import com.rabby250.livemanual.content.ManualPage;
import com.rabby250.livemanual.content.PageColumns;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "LiveManual.db";
    private static final int VERSION = 1;

    public DatabaseHelper(@NonNull final Context context) {
        super(context, DATABASE_NAME, null, VERSION, null);
    }

    @Override
    public void onCreate(@NonNull final SQLiteDatabase db) {
        createManualPageTable(db);
    }

    private void createManualPageTable(
            @NonNull final SQLiteDatabase db) {
        final StringBuilder sb = new StringBuilder();
        sb.append("CREATE TABLE ")
                .append(ManualPage.TABLE_NAME).append(" (");
        for (String rule : PageColumns.RULES) {
            sb.append(rule).append(", ");
        }
        sb.setLength(sb.length() - 2); // Trim the last comma
        db.execSQL(sb.append(')').toString());
    }

    @Override
    public void onUpgrade(
            @NonNull final SQLiteDatabase db,
            int oldVersion, int newVersion) {
        // No new version available right now
    }
}
