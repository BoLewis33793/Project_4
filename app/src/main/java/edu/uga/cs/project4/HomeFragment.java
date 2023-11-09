package edu.uga.cs.project4;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class HomeFragment extends Fragment {

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

        return rootView;
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
        Fragment quizFragment = new QuizFragment();
        // Perform the fragment transaction to replace the current fragment with the QuizFragment
        FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, quizFragment);
        transaction.addToBackStack(null); // Add this transaction to the back stack
        transaction.commit();
    }

    private void showQuizScoresFragment() {
        Fragment quizScoresFragment = new ScoreQuizFragment();
        // Perform the fragment transaction to replace the current fragment with the QuizScoresFragment
        FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, quizScoresFragment);
        transaction.addToBackStack(null); // Add this transaction to the back stack
        transaction.commit();
    }
}
