package edu.uga.cs.project4;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class QuizDBHelper extends SQLiteOpenHelper {

    private static final String DEBUG_TAG = "QuizDBHelper";
    private static final String DB_NAME = "stateCapitalsQuiz.db";
    private static final int DB_VERSION = 1;
    private static QuizDBHelper helperInstance;

    public static final String TABLE_QUIZ = "quiz";
    public static final String QUIZ_COLUMN_ID = "_id";
    public static final String QUIZ_COLUMN_DATE = "date";
    public static final String QUIZ_COLUMN_QUESTION1 = "question1_id";
    public static final String QUIZ_COLUMN_QUESTION2 = "question2_id";
    public static final String QUIZ_COLUMN_QUESTION3 = "question3_id";
    public static final String QUIZ_COLUMN_QUESTION4 = "question4_id";
    public static final String QUIZ_COLUMN_QUESTION5 = "question5_id";
    public static final String QUIZ_COLUMN_QUESTION6 = "question6_id";
    public static final String QUIZ_COLUMN_SCORE = "result";
    public static final String QUIZ_COLUMN_NUM_ANSWERS = "numAnswers";
    public static final String QUIZ_COLUMN_QUESTION1_ANSWER = "question1Answer";
    public static final String QUIZ_COLUMN_QUESTION2_ANSWER = "question2Answer";
    public static final String QUIZ_COLUMN_QUESTION3_ANSWER = "question3Answer";
    public static final String QUIZ_COLUMN_QUESTION4_ANSWER = "question4Answer";
    public static final String QUIZ_COLUMN_QUESTION5_ANSWER = "question5Answer";
    public static final String QUIZ_COLUMN_QUESTION6_ANSWER = "question6Answer";

    public static final String STATES_COLUMN_ID = "_id";

    // SQL statement to create a new table
    private static final String CREATE_QUIZ =
            "create table " + TABLE_QUIZ + "("
                    + QUIZ_COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + QUIZ_COLUMN_DATE + " TEXT,"
                    + QUIZ_COLUMN_QUESTION1 + " INTEGER,"
                    + QUIZ_COLUMN_QUESTION2 + " INTEGER,"
                    + QUIZ_COLUMN_QUESTION3 + " INTEGER,"
                    + QUIZ_COLUMN_QUESTION4 + " INTEGER,"
                    + QUIZ_COLUMN_QUESTION5 + " INTEGER,"
                    + QUIZ_COLUMN_QUESTION6 + " INTEGER,"
                    + QUIZ_COLUMN_SCORE + " INTEGER,"
                    + QUIZ_COLUMN_NUM_ANSWERS + " INTEGER,"
                    + QUIZ_COLUMN_QUESTION1_ANSWER + " TEXT,"
                    + QUIZ_COLUMN_QUESTION2_ANSWER + " TEXT,"
                    + QUIZ_COLUMN_QUESTION3_ANSWER + " TEXT,"
                    + QUIZ_COLUMN_QUESTION4_ANSWER + " TEXT,"
                    + QUIZ_COLUMN_QUESTION5_ANSWER + " TEXT,"
                    + QUIZ_COLUMN_QUESTION6_ANSWER + " TEXT,"
                    + "FOREIGN KEY(" + QUIZ_COLUMN_QUESTION1 + ") REFERENCES states(" + STATES_COLUMN_ID + "),"
                    + "FOREIGN KEY(" + QUIZ_COLUMN_QUESTION2 + ") REFERENCES states(" + STATES_COLUMN_ID + "),"
                    + "FOREIGN KEY(" + QUIZ_COLUMN_QUESTION3 + ") REFERENCES states(" + STATES_COLUMN_ID + "),"
                    + "FOREIGN KEY(" + QUIZ_COLUMN_QUESTION4 + ") REFERENCES states(" + STATES_COLUMN_ID + "),"
                    + "FOREIGN KEY(" + QUIZ_COLUMN_QUESTION5 + ") REFERENCES states(" + STATES_COLUMN_ID + "),"
                    + "FOREIGN KEY(" + QUIZ_COLUMN_QUESTION6 + ") REFERENCES states(" + STATES_COLUMN_ID + ")"
                    + ")";

    QuizDBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    public static synchronized QuizDBHelper getInstance(Context context){
        if(helperInstance == null) {
            helperInstance = new QuizDBHelper(context.getApplicationContext());
        }
        return helperInstance;
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL( CREATE_QUIZ );
        Log.d( DEBUG_TAG, "Table " + TABLE_QUIZ + " created" );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL( "drop table if exists " + TABLE_QUIZ);
        onCreate( db );
        Log.d( DEBUG_TAG, "Table " + TABLE_QUIZ + " upgraded" );
    }

}
