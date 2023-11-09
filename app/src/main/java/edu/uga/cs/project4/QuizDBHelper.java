package edu.uga.cs.project4;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class QuizDBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "stateCapitalsQuiz.db";
    private static final int DATABASE_VERSION = 1;

    // SQL statement to create a new table
    private static final String TABLE_QUIZZES_CREATE =
            "CREATE TABLE quizzes (" +
                    "_id INTEGER PRIMARY KEY," +
                    "state TEXT NOT NULL," +
                    "capital TEXT NOT NULL," +
                    "city1 TEXT NOT NULL," +
                    "city2 TEXT NOT NULL," +
                    "answer INTEGER," +
                    "timestamp DATETIME DEFAULT CURRENT_TIMESTAMP" +
                    ");";

    public QuizDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_QUIZZES_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        db.execSQL("DROP TABLE IF EXISTS quizzes");
        onCreate(db);
    }
    // Additional methods to insert, update, and retrieve quiz data
}
