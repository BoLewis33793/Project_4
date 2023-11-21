package edu.uga.cs.project4;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class StateQuizData {
    public static final String DEBUG_TAG = "StateQuizData";

    // Reference to database
    private SQLiteDatabase db;
    private SQLiteOpenHelper stateQuizDbHelper;
    private static final String[] allColumns = {
            StateQuizDBHelper.STATES_COLUMN_ID,
            StateQuizDBHelper.STATES_COLUMN_NAME,
            StateQuizDBHelper.STATES_COLUMN_CAPITAL,
            StateQuizDBHelper.STATES_COLUMN_CITY1,
            StateQuizDBHelper.STATES_COLUMN_CITY2
    };

    public StateQuizData( Context context) {
        this.stateQuizDbHelper = StateQuizDBHelper.getInstance( context );
    }
    public void open() {
        db = stateQuizDbHelper.getWritableDatabase();
        Log.d(DEBUG_TAG, "StateQuizData: dp open");
    }
    public void close() {
        if (stateQuizDbHelper != null) {
            stateQuizDbHelper.close();
            Log.d(DEBUG_TAG, "StateQuizData: dp closed");
        }
    }
    public boolean isDBOpen() {
        return db.isOpen();
    }

    public State retrieveState(long id) {
        open();
        State state = new State();
        Cursor cursor = null;
        int columnIndex;

        try {
            String TABLE_STATES = "states";
            String STATE_COLUMN_ID = "_id";

            String[] whereArgs = {String.valueOf(id)};

            cursor = db.rawQuery("Select * FROM " + TABLE_STATES + " WHERE " + STATE_COLUMN_ID + " = ?", whereArgs);

            if (cursor != null && cursor.getCount() > 0) {
                cursor.moveToFirst();
                if (cursor.getColumnCount() >= 5) {
                    columnIndex = cursor.getColumnIndex(StateQuizDBHelper.STATES_COLUMN_ID);
                    long state_id = cursor.getLong(columnIndex);
                    columnIndex = cursor.getColumnIndex(StateQuizDBHelper.STATES_COLUMN_NAME);
                    String name = cursor.getString(columnIndex);
                    columnIndex = cursor.getColumnIndex(StateQuizDBHelper.STATES_COLUMN_CAPITAL);
                    String capital = cursor.getString(columnIndex);
                    columnIndex = cursor.getColumnIndex(StateQuizDBHelper.STATES_COLUMN_CITY1);
                    String city1 = cursor.getString(columnIndex);
                    columnIndex = cursor.getColumnIndex(StateQuizDBHelper.STATES_COLUMN_CITY2);
                    String city2 = cursor.getString(columnIndex);

                    state = new State(name, capital, city1, city2);
                    state.setId(state_id);

                    Log.d(DEBUG_TAG, "Retrieved State by id: " + state);
                }
            }
        if (cursor != null) {
            Log.d(DEBUG_TAG, "Number of records from DB: " + cursor.getCount());
        } else {
            Log.d(DEBUG_TAG, "Number of records from DB: 0");
        }
    }
        catch (Exception e) {
            Log.d(DEBUG_TAG, "Exception caught: " + e);
        }
        finally

    {
        if (cursor != null) {
            cursor.close();
        }
    }
        close();
        return state;
    }

    public List<State> retrieveAllStates() {
        ArrayList<State> states = new ArrayList<>();
        Cursor cursor = null;
        int columnIndex;

        try {
            cursor = db.query(StateQuizDBHelper.TABLE_STATES, allColumns,
                    null,null,null,null,null);
            if (cursor != null && cursor.getCount() > 0) {
                while (cursor.moveToNext()) {
                    if (cursor.getColumnCount() >= 5) {
                        columnIndex = cursor.getColumnIndex( StateQuizDBHelper.STATES_COLUMN_ID);
                        long id = cursor.getLong( columnIndex );
                        columnIndex = cursor.getColumnIndex( StateQuizDBHelper.STATES_COLUMN_NAME);
                        String name = cursor.getString( columnIndex );
                        columnIndex = cursor.getColumnIndex( StateQuizDBHelper.STATES_COLUMN_CAPITAL);
                        String capital = cursor.getString( columnIndex );
                        columnIndex = cursor.getColumnIndex( StateQuizDBHelper.STATES_COLUMN_CITY1);
                        String city1 = cursor.getString( columnIndex );
                        columnIndex = cursor.getColumnIndex( StateQuizDBHelper.STATES_COLUMN_CITY2);
                        String city2 = cursor.getString( columnIndex );

                        State state = new State( name, capital, city1, city2);
                        state.setId(id);

                        states.add( state );
                        Log.d(DEBUG_TAG, "Retrieved State: " + state);
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
        return states;
    }

    public State storeState (State state) {
        open();
        ContentValues values = new ContentValues();
        values.put(StateQuizDBHelper.STATES_COLUMN_NAME, state.getStateName());
        values.put(StateQuizDBHelper.STATES_COLUMN_CAPITAL, state.getCapital());
        values.put(StateQuizDBHelper.STATES_COLUMN_CITY1, state.getCity1());
        values.put(StateQuizDBHelper.STATES_COLUMN_CITY2, state.getCity2());

        long id = db.insert(StateQuizDBHelper.TABLE_STATES, null, values);

        state.setId(id);

        Log.d(DEBUG_TAG, "Stored new state with id: " + String.valueOf(state.getId()));
        close();
        return state;
    }

    public boolean isDatabaseAlreadyPopulated() {
        boolean rowExists;

        open();

        Cursor myCursor = db.rawQuery("SELECT * FROM " + StateQuizDBHelper.TABLE_STATES, null);
        try {
            if (myCursor.moveToFirst()) {
                rowExists = true;
            } else {
                rowExists = false;
            }
        } finally {
            myCursor.close();
        }

        close();

        return rowExists;
    }
}
