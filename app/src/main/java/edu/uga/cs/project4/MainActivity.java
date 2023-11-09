package edu.uga.cs.project4;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private QuizDBHelper quizDBHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize the database helper
        quizDBHelper = new QuizDBHelper(this);

        // TODO: Add any UI initialization code if necessary

        // Example: If you want to populate the database when the activity starts, call a method here.
        // Remember to run database operations in a separate thread or AsyncTask.

        // initializeDatabase(); // This is just a placeholder for the actual database initialization method.
    }

    @Override
    protected void onResume() {
        super.onResume();
        // You can refresh data here if needed
    }

    // Make sure to close the database when the activity is destroyed to prevent memory leaks
    @Override
    protected void onDestroy() {
        quizDBHelper.close();
        super.onDestroy();
    }

    // TODO: Define other methods based on your application's needs

    // For example, you might have a method that initializes the database by copying from the assets folder
    /*
    private void initializeDatabase() {
        // Perform database copy or initialization here
        // This should be done in a separate thread or AsyncTask to avoid blocking the UI thread
    }
    */
}
