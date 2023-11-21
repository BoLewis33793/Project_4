package edu.uga.cs.project4;

import android.content.Context;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

import java.util.List;
import java.util.Random;

public class HomeFragment extends Fragment {

    private static final String TAG = "HomeFragment";
    private StateQuizData stateQuizData = null;
    private QuizData quizData = null;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);

        Button helpButton = rootView.findViewById(R.id.button);
        helpButton.setOnClickListener(view -> showHelpFragment());

        Button quizButton = rootView.findViewById(R.id.button2);
        quizButton.setOnClickListener(view -> showQuizFragment());

        Button scoresButton = rootView.findViewById(R.id.button3);
        scoresButton.setOnClickListener(view -> showQuizScoresFragment());

        stateQuizData = new StateQuizData(getActivity());
        quizData = new QuizData(getActivity());
        createStatesDatabase();

        return rootView;
    }

    public class QuizDBWriter extends AsyncTask<Quiz, Quiz, Quiz> {

        @Override
        protected Quiz doInBackground(Quiz... quizzes) {
            quizData.storeQuiz(quizzes[0]);
            return quizzes[0];
        }

        @Override
        protected void onPostExecute(Quiz quiz) {
            Log.d(TAG, "Quiz saved: " + quiz);
        }
    }
    public class StateDBWriter extends AsyncTask<State, State, State> {

        @Override
        protected State doInBackground(State... states) {
            stateQuizData.storeState(states[0]);
            return states[0];
        }

        @Override
        protected void onPostExecute(State state) {
            Log.d(TAG, "State saved: " + state);
        }
    }

    private void showHelpFragment() {
        Fragment helpFragment = new HelpFragment();
        // Perform the fragment transaction to replace the current fragment with the HelpFragment
        FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, helpFragment);
        transaction.addToBackStack(null); // Add this transaction to the back stack
        transaction.commit();
    }

    private void showQuizFragment() {

        Random random = new Random();

        // Create an array to store the six unique random numbers
        int[] uniqueNumbers = new int[6];

        // Generate six unique random numbers between 1 and 50
        for (int i = 0; i < 6; i++) {
            int randomNumber;
            boolean isDuplicate;

            // Keep generating random numbers until a unique one is found
            do {
                isDuplicate = false;
                randomNumber = random.nextInt(50) + 1;

                // Check if the generated number is already in the array
                for (int j = 0; j < i; j++) {
                    if (uniqueNumbers[j] == randomNumber) {
                        isDuplicate = true;
                        break;
                    }
                }

            } while (isDuplicate);

            // Store the unique random number in the array
            uniqueNumbers[i] = randomNumber;
        }
            long question1 = uniqueNumbers[0];
            long question2 = uniqueNumbers[1];
            long question3 = uniqueNumbers[2];
            long question4 = uniqueNumbers[3];
            long question5 = uniqueNumbers[4];
            long question6 = uniqueNumbers[5];

            Quiz quiz = new Quiz(question1, question2, question3, question4, question5, question6);

            new QuizDBWriter().execute(quiz);

            Fragment quizFragment = new QuizViewFragment();
            // Perform the fragment transaction to replace the current fragment with the QuizFragment
            FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
            transaction.replace(R.id.fragment_container, quizFragment);
            transaction.addToBackStack(null); // Add this transaction to the back stack
            transaction.commit();
    }

    private void showQuizScoresFragment() {

        List<Quiz> quizzes = quizData.retrieveAllQuizzes();

        Log.d(TAG, "Retrieved all quizzes: " + quizzes);

        Fragment quizScoresFragment = new ScoreQuizFragment();
        // Perform the fragment transaction to replace the current fragment with the QuizScoresFragment
        FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, quizScoresFragment);
        transaction.addToBackStack(null); // Add this transaction to the back stack
        transaction.commit();
    }

    private void createStatesDatabase() {
        if (stateQuizData.isDatabaseAlreadyPopulated() == false) {
            InputStream is = null;
            Context context = getActivity();

            try {
                is = context.getAssets().open("StateCapitals.csv");
            } catch (IOException e) {
                e.printStackTrace();
            }
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(is, Charset.forName("UTF-8"))
            );

            String line = "";
            try {
                // Step over headers
                reader.readLine();


                while ((line = reader.readLine()) != null) {
                    // Split by ','
                    String[] tokens = line.split(",");

                    // Read the data
                    State state = new State();
                    state.setStateName(tokens[0]);
                    state.setCapital(tokens[1]);
                    state.setCity1(tokens[2]);
                    state.setCity2(tokens[3]);


                    new StateDBWriter().execute(state);
                }
            } catch (IOException e) {
                Log.wtf("MyActivity", "Error reading data file on line " + line, e);
                e.printStackTrace();
            }
        } else {
            Log.d(TAG, "Database is already populated!");
        }
    }
    @Override
    public void onResume() {
        super.onResume();
        // open the database in onResume
        if( stateQuizData != null )
            stateQuizData.open();
    }

    // We need to save job leads into a file as the activity stops being a foreground activity
    @Override
    public void onPause() {
        Log.d( TAG, "HomeFragment.onPause()" );
        super.onPause();
        // close the database in onPause
        if( stateQuizData != null )
            stateQuizData.close();
    }
}
