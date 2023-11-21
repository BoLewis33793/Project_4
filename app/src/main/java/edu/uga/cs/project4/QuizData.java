package edu.uga.cs.project4;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class QuizData {
    public static final String DEBUG_TAG = "QuizData";

    // Reference to database
    private SQLiteDatabase db;
    private SQLiteOpenHelper quizDbHelper;
    Quiz recentQuiz = new Quiz();
    private static final String[] allColumns = {
            QuizDBHelper.QUIZ_COLUMN_ID,
            QuizDBHelper.QUIZ_COLUMN_DATE,
            QuizDBHelper.QUIZ_COLUMN_QUESTION1,
            QuizDBHelper.QUIZ_COLUMN_QUESTION2,
            QuizDBHelper.QUIZ_COLUMN_QUESTION3,
            QuizDBHelper.QUIZ_COLUMN_QUESTION4,
            QuizDBHelper.QUIZ_COLUMN_QUESTION5,
            QuizDBHelper.QUIZ_COLUMN_QUESTION6,
            QuizDBHelper.QUIZ_COLUMN_SCORE,
            QuizDBHelper.QUIZ_COLUMN_NUM_ANSWERS,
            QuizDBHelper.QUIZ_COLUMN_QUESTION1_ANSWER,
            QuizDBHelper.QUIZ_COLUMN_QUESTION2_ANSWER,
            QuizDBHelper.QUIZ_COLUMN_QUESTION3_ANSWER,
            QuizDBHelper.QUIZ_COLUMN_QUESTION4_ANSWER,
            QuizDBHelper.QUIZ_COLUMN_QUESTION5_ANSWER,
            QuizDBHelper.QUIZ_COLUMN_QUESTION6_ANSWER,
    };

    public QuizData( Context context) {
        this.quizDbHelper = QuizDBHelper.getInstance( context );
    }
    public void open() {
        db = quizDbHelper.getWritableDatabase();
        Log.d(DEBUG_TAG, "QuizData: dp open");
    }
    public void close() {
        if (quizDbHelper != null) {
            quizDbHelper.close();
            Log.d(DEBUG_TAG, "QuizData: dp closed");
        }
    }
    public boolean isDBOpen() {
        return db.isOpen();
    }

    public void setRecentQuiz(Quiz quiz) {
        this.recentQuiz = quiz;
    }

    public Quiz getRecentQuiz(){
        return recentQuiz;
    }

    public List<Quiz> retrieveAllQuizzes() {
        open();
        ArrayList<Quiz> quizzes = new ArrayList<>();
        Cursor cursor = null;
        int columnIndex;

        try {
            cursor = db.query(QuizDBHelper.TABLE_QUIZ, allColumns,
                    null,null,null,null,null);
            if (cursor != null && cursor.getCount() > 0) {
                while (cursor.moveToNext()) {
                    if (cursor.getColumnCount() >= 5) {
                        columnIndex = cursor.getColumnIndex(QuizDBHelper.QUIZ_COLUMN_ID);
                        long id = cursor.getLong( columnIndex );
                        columnIndex = cursor.getColumnIndex(QuizDBHelper.QUIZ_COLUMN_DATE);
                        String date = cursor.getString( columnIndex );
                        columnIndex = cursor.getColumnIndex(QuizDBHelper.QUIZ_COLUMN_QUESTION1);
                        long question1 = cursor.getLong( columnIndex );
                        columnIndex = cursor.getColumnIndex(QuizDBHelper.QUIZ_COLUMN_QUESTION2);
                        long question2 = cursor.getLong( columnIndex );
                        columnIndex = cursor.getColumnIndex(QuizDBHelper.QUIZ_COLUMN_QUESTION3);
                        long question3 = cursor.getLong( columnIndex );
                        columnIndex = cursor.getColumnIndex(QuizDBHelper.QUIZ_COLUMN_QUESTION4);
                        long question4 = cursor.getLong( columnIndex );
                        columnIndex = cursor.getColumnIndex(QuizDBHelper.QUIZ_COLUMN_QUESTION5);
                        long question5 = cursor.getLong( columnIndex );
                        columnIndex = cursor.getColumnIndex(QuizDBHelper.QUIZ_COLUMN_QUESTION6);
                        long question6 = cursor.getLong( columnIndex );
                        columnIndex = cursor.getColumnIndex(QuizDBHelper.QUIZ_COLUMN_SCORE);
                        int score = cursor.getInt( columnIndex );
                        columnIndex = cursor.getColumnIndex(QuizDBHelper.QUIZ_COLUMN_NUM_ANSWERS);
                        int numAnswers = cursor.getInt( columnIndex );
                        columnIndex = cursor.getColumnIndex(QuizDBHelper.QUIZ_COLUMN_QUESTION1_ANSWER);
                        String question1Answer = cursor.getString( columnIndex );
                        columnIndex = cursor.getColumnIndex(QuizDBHelper.QUIZ_COLUMN_QUESTION2_ANSWER);
                        String question2Answer = cursor.getString( columnIndex );
                        columnIndex = cursor.getColumnIndex(QuizDBHelper.QUIZ_COLUMN_QUESTION3_ANSWER);
                        String question3Answer = cursor.getString( columnIndex );
                        columnIndex = cursor.getColumnIndex(QuizDBHelper.QUIZ_COLUMN_QUESTION4_ANSWER);
                        String question4Answer = cursor.getString( columnIndex );
                        columnIndex = cursor.getColumnIndex(QuizDBHelper.QUIZ_COLUMN_QUESTION5_ANSWER);
                        String question5Answer = cursor.getString( columnIndex );
                        columnIndex = cursor.getColumnIndex(QuizDBHelper.QUIZ_COLUMN_QUESTION6_ANSWER);
                        String question6Answer = cursor.getString( columnIndex );

                        Quiz quiz = new Quiz(date, question1, question2, question3, question4,
                                question5, question6, score, numAnswers, question1Answer, question2Answer,
                                question3Answer, question4Answer, question5Answer, question6Answer);

                        quiz.setId(id);

                        quizzes.add( quiz );
                        Log.d(DEBUG_TAG, "Retrieved Quiz: " + quiz);
                    }
                }
            }
            if (cursor != null) {
                Log.d(DEBUG_TAG, "Number of records from DB: " + cursor.getCount());
            }
            else {
                Log.d(DEBUG_TAG, "Number of records from DB: 0");
            }
        }
        catch (Exception e) {
            Log.d(DEBUG_TAG, "Exception caught: " + e);
        }
        finally {
            if (cursor != null) {
                cursor.close();
            }
        }
        close();
        return quizzes;
    }

    public Quiz retrieveQuiz() {
        open();
        Quiz quiz = new Quiz();
        Cursor cursor = null;
        int columnIndex;

        try {
            cursor = db.query(QuizDBHelper.TABLE_QUIZ, allColumns,
                    null,null,null,null,null);
            if (cursor != null && cursor.getCount() > 0) {
                cursor.moveToLast();
                    if (cursor.getColumnCount() >= 5) {
                        columnIndex = cursor.getColumnIndex(QuizDBHelper.QUIZ_COLUMN_ID);
                        long id = cursor.getLong( columnIndex );
                        columnIndex = cursor.getColumnIndex(QuizDBHelper.QUIZ_COLUMN_DATE);
                        String date = cursor.getString( columnIndex );
                        columnIndex = cursor.getColumnIndex(QuizDBHelper.QUIZ_COLUMN_QUESTION1);
                        long question1 = cursor.getLong( columnIndex );
                        columnIndex = cursor.getColumnIndex(QuizDBHelper.QUIZ_COLUMN_QUESTION2);
                        long question2 = cursor.getLong( columnIndex );
                        columnIndex = cursor.getColumnIndex(QuizDBHelper.QUIZ_COLUMN_QUESTION3);
                        long question3 = cursor.getLong( columnIndex );
                        columnIndex = cursor.getColumnIndex(QuizDBHelper.QUIZ_COLUMN_QUESTION4);
                        long question4 = cursor.getLong( columnIndex );
                        columnIndex = cursor.getColumnIndex(QuizDBHelper.QUIZ_COLUMN_QUESTION5);
                        long question5 = cursor.getLong( columnIndex );
                        columnIndex = cursor.getColumnIndex(QuizDBHelper.QUIZ_COLUMN_QUESTION6);
                        long question6 = cursor.getLong( columnIndex );
                        columnIndex = cursor.getColumnIndex(QuizDBHelper.QUIZ_COLUMN_SCORE);
                        int score = cursor.getInt( columnIndex );
                        columnIndex = cursor.getColumnIndex(QuizDBHelper.QUIZ_COLUMN_NUM_ANSWERS);
                        int numAnswers = cursor.getInt( columnIndex );
                        columnIndex = cursor.getColumnIndex(QuizDBHelper.QUIZ_COLUMN_QUESTION1_ANSWER);
                        String question1Answer = cursor.getString( columnIndex );
                        columnIndex = cursor.getColumnIndex(QuizDBHelper.QUIZ_COLUMN_QUESTION2_ANSWER);
                        String question2Answer = cursor.getString( columnIndex );
                        columnIndex = cursor.getColumnIndex(QuizDBHelper.QUIZ_COLUMN_QUESTION3_ANSWER);
                        String question3Answer = cursor.getString( columnIndex );
                        columnIndex = cursor.getColumnIndex(QuizDBHelper.QUIZ_COLUMN_QUESTION4_ANSWER);
                        String question4Answer = cursor.getString( columnIndex );
                        columnIndex = cursor.getColumnIndex(QuizDBHelper.QUIZ_COLUMN_QUESTION5_ANSWER);
                        String question5Answer = cursor.getString( columnIndex );
                        columnIndex = cursor.getColumnIndex(QuizDBHelper.QUIZ_COLUMN_QUESTION6_ANSWER);
                        String question6Answer = cursor.getString( columnIndex );

                        quiz = new Quiz(date, question1, question2, question3, question4,
                                question5, question6, score, numAnswers, question1Answer, question2Answer,
                                question3Answer, question4Answer, question5Answer, question6Answer);

                        quiz.setId(id);

                        Log.d(DEBUG_TAG, "Retrieved Quiz from retrieveQuiz(): " + quiz);
                    }
            }
            if (cursor != null) {
                Log.d(DEBUG_TAG, "Number of records from DB: " + cursor.getCount());
            }
            else {
                Log.d(DEBUG_TAG, "Number of records from DB: 0");
            }
        }
        catch (Exception e) {
            Log.d(DEBUG_TAG, "Exception caught: " + e);
        }
        finally {
            if (cursor != null) {
                cursor.close();
            }
        }
        close();
        return quiz;
    }

    public Quiz storeQuiz (Quiz quiz) {
        open();
        ContentValues values = new ContentValues();
        values.put(QuizDBHelper.QUIZ_COLUMN_DATE, quiz.getDate());
        values.put(QuizDBHelper.QUIZ_COLUMN_QUESTION1, quiz.getQuestion1());
        values.put(QuizDBHelper.QUIZ_COLUMN_QUESTION2, quiz.getQuestion2());
        values.put(QuizDBHelper.QUIZ_COLUMN_QUESTION3, quiz.getQuestion3());
        values.put(QuizDBHelper.QUIZ_COLUMN_QUESTION4, quiz.getQuestion4());
        values.put(QuizDBHelper.QUIZ_COLUMN_QUESTION5, quiz.getQuestion5());
        values.put(QuizDBHelper.QUIZ_COLUMN_QUESTION6, quiz.getQuestion6());
        values.put(QuizDBHelper.QUIZ_COLUMN_SCORE, quiz.getScore());
        values.put(QuizDBHelper.QUIZ_COLUMN_NUM_ANSWERS, quiz.getNumAnswers());
        values.put(QuizDBHelper.QUIZ_COLUMN_QUESTION1_ANSWER, quiz.getQuestion1Answer());
        values.put(QuizDBHelper.QUIZ_COLUMN_QUESTION2_ANSWER, quiz.getQuestion2Answer());
        values.put(QuizDBHelper.QUIZ_COLUMN_QUESTION3_ANSWER, quiz.getQuestion3Answer());
        values.put(QuizDBHelper.QUIZ_COLUMN_QUESTION4_ANSWER, quiz.getQuestion4Answer());
        values.put(QuizDBHelper.QUIZ_COLUMN_QUESTION5_ANSWER, quiz.getQuestion5Answer());
        values.put(QuizDBHelper.QUIZ_COLUMN_QUESTION6_ANSWER, quiz.getQuestion6Answer());


        long id = db.insert(QuizDBHelper.TABLE_QUIZ, null, values);

        quiz.setId(id);

        Log.d(DEBUG_TAG, "Stored new quiz with id: " + String.valueOf(quiz.getId()));
        close();
        return quiz;
    }
    public Quiz updateQuiz (Quiz quiz) {
        open();
        ContentValues values = new ContentValues();
        values.put(QuizDBHelper.QUIZ_COLUMN_ID, quiz.getId());
        values.put(QuizDBHelper.QUIZ_COLUMN_DATE, quiz.getDate());
        values.put(QuizDBHelper.QUIZ_COLUMN_QUESTION1, quiz.getQuestion1());
        values.put(QuizDBHelper.QUIZ_COLUMN_QUESTION2, quiz.getQuestion2());
        values.put(QuizDBHelper.QUIZ_COLUMN_QUESTION3, quiz.getQuestion3());
        values.put(QuizDBHelper.QUIZ_COLUMN_QUESTION4, quiz.getQuestion4());
        values.put(QuizDBHelper.QUIZ_COLUMN_QUESTION5, quiz.getQuestion5());
        values.put(QuizDBHelper.QUIZ_COLUMN_QUESTION6, quiz.getQuestion6());
        values.put(QuizDBHelper.QUIZ_COLUMN_SCORE, quiz.getScore());
        values.put(QuizDBHelper.QUIZ_COLUMN_NUM_ANSWERS, quiz.getNumAnswers());
        values.put(QuizDBHelper.QUIZ_COLUMN_QUESTION1_ANSWER, quiz.getQuestion1Answer());
        values.put(QuizDBHelper.QUIZ_COLUMN_QUESTION2_ANSWER, quiz.getQuestion2Answer());
        values.put(QuizDBHelper.QUIZ_COLUMN_QUESTION3_ANSWER, quiz.getQuestion3Answer());
        values.put(QuizDBHelper.QUIZ_COLUMN_QUESTION4_ANSWER, quiz.getQuestion4Answer());
        values.put(QuizDBHelper.QUIZ_COLUMN_QUESTION5_ANSWER, quiz.getQuestion5Answer());
        values.put(QuizDBHelper.QUIZ_COLUMN_QUESTION6_ANSWER, quiz.getQuestion6Answer());


        db.update(QuizDBHelper.TABLE_QUIZ, values, QuizDBHelper.QUIZ_COLUMN_ID+ " =?", new String[]{String.valueOf(quiz.getId())});

        Log.d(DEBUG_TAG, "Updated Quiz: " + quiz);
        close();
        return quiz;
    }
}
