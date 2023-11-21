package edu.uga.cs.project4;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

public class StateQuizDBHelper extends SQLiteOpenHelper {

    private static final String DEBUG_TAG = "StateQuizDBHelper";
    private static final String DB_NAME = "stateQuestions.db";
    private static final int DB_VERSION = 1;
    private static StateQuizDBHelper helperInstance;

    public static final String TABLE_STATES = "states";
    public static final String STATES_COLUMN_ID = "_id";
    public static final String STATES_COLUMN_NAME = "name";
    public static final String STATES_COLUMN_CAPITAL = "capital";
    public static final String STATES_COLUMN_CITY1 = "city1";
    public static final String STATES_COLUMN_CITY2 = "city2";

    public static final String CREATE_STATES =
            "create table " + TABLE_STATES + "("
                + STATES_COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + STATES_COLUMN_NAME + " TEXT,"
                + STATES_COLUMN_CAPITAL + " TEXT,"
                + STATES_COLUMN_CITY1 + " TEXT,"
                + STATES_COLUMN_CITY2 + " TEXT"
                + ")";

    StateQuizDBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    public static synchronized StateQuizDBHelper getInstance(Context context){
        if(helperInstance == null) {
            helperInstance = new StateQuizDBHelper(context.getApplicationContext());
        }
        return helperInstance;
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL( CREATE_STATES );
        Log.d( DEBUG_TAG, "Table " + TABLE_STATES + " created" );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL( "drop table if exists " + TABLE_STATES);
        onCreate( db );
        Log.d( DEBUG_TAG, "Table " + TABLE_STATES + " upgraded" );
    }
}
