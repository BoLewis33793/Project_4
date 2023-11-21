package edu.uga.cs.project4;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;


public class ScoreQuizFragment extends Fragment {

    private static final String TAG = "ScoreQuizFragment";

    private QuizData quizData = null;
    private List<Quiz> quizListFull;

    private RecyclerView recyclerView;
    private QuizRecyclerAdapter recyclerAdapter;

    public ScoreQuizFragment() {
        // Required empty public constructor
    }

    public static ScoreQuizFragment newInstance() {
        ScoreQuizFragment fragment = new ScoreQuizFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Enable the search menu population.
        // When the parameter of this method is true, Android will call onCreateOptionsMenu on
        // this fragment, when the options menu is being built for the hosting activity.
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_score_quiz, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = getView().findViewById(R.id.scores_recycler_view);

        // use a linear layout manager for the recycler view
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        quizListFull = new ArrayList<Quiz>();

        // Create a JobLeadsData instance, since we will need to save a new JobLead to the dn.
        // Note that even though more activites may create their own instances of the JobLeadsData
        // class, we will be using a single instance of the JobLeadsDBHelper object, since
        // that class is a singleton class.
        quizData = new QuizData(getActivity());

        // Open that database for reading of the full list of job leads.
        // Note that onResume() hasn't been called yet, so the db open in it
        // was not called yet!
        quizData.open();

        // Execute the retrieval of the job leads in an asynchronous way,
        // without blocking the main UI thread.
        new QuizDBReader().execute();

    }

    // This is an AsyncTask class (it extends AsyncTask) to perform DB reading of job leads, asynchronously.
    private class QuizDBReader extends AsyncTask<Void, List<Quiz>> {
        // This method will run as a background process to read from db.
        // It returns a list of retrieved JobLead objects.
        // It will be automatically invoked by Android, when we call the execute method
        // in the onCreate callback (the job leads review activity is started).
        @Override
        protected List<Quiz> doInBackground(Void... params) {
            List<Quiz> quizListFull = quizData.retrieveAllQuizzes();

            Log.d(TAG, "QuizDBReader: quizzes retrieved: " + quizListFull.size());

            return quizListFull;
        }

        // This method will be automatically called by Android once the db reading
        // background process is finished.  It will then create and set an adapter to provide
        // values for the RecyclerView.
        // onPostExecute is like the notify method in an asynchronous method call discussed in class.
        @Override
        protected void onPostExecute(List<Quiz> quizList) {
            Log.d(TAG, "QuizDBReader: quizList.size(): " + quizList.size());
            quizListFull.addAll(quizList);

            // create the RecyclerAdapter and set it for the RecyclerView
            recyclerAdapter = new QuizRecyclerAdapter(getActivity(), quizListFull);
            recyclerView.setAdapter(recyclerAdapter);
        }
    }

    // This is an AsyncTask class (it extends AsyncTask) to perform DB writing of a job lead, asynchronously.
    public class QuizDBWriter extends AsyncTask<Quiz, Quiz> {

        // This method will run as a background process to write into db.
        // It will be automatically invoked by Android, when we call the execute method
        // in the onClick listener of the Save button.
        @Override
        protected Quiz doInBackground(Quiz... quizzes) {
            quizData.storeQuiz(quizzes[0]);
            return quizzes[0];
        }

        // This method will be automatically called by Android once the writing to the database
        // in a background process has finished.  Note that doInBackground returns a JobLead object.
        // That object will be passed as argument to onPostExecute.
        // onPostExecute is like the notify method in an asynchronous method call discussed in class.
        @Override
        protected void onPostExecute(Quiz quiz) {
            // Update the recycler view to include the new job lead
           quizListFull.add(quiz);
            // Sync the originalValues list in the recyler adapter to the new updated list (JoLeadsList)
            recyclerAdapter.sync();

            // Notify the adapter that an item has been inserted
            recyclerAdapter.notifyItemInserted(quizListFull.size() - 1);

            // Reposition the view to show to newly added item by smoothly scrolling to it

            Log.d(TAG, "Quiz saved: " + quiz);
        }
    }

    @Override
    public void onResume() {
        super.onResume();

        // Open the database
        if (quizData != null && !quizData.isDBOpen()) {
            quizData.open();
            Log.d(TAG, "ScoreQuizFragment.onResume(): opening DB");
        }

        // Update the app name in the Action Bar to be the same as the app's name
    }

    // We need to save job leads into a file as the activity stops being a foreground activity
    @Override
    public void onPause() {
        super.onPause();

        // close the database in onPause
        if (quizData != null) {
            quizData.close();
            Log.d(TAG, "ScoreQuizFragment.onPause(): closing DB");
        }
    }
}