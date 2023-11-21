package edu.uga.cs.project4;

import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager2.widget.ViewPager2;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

public class QuizFragment extends Fragment {

    private static final String DEBUG_TAG = "QuizFragment";
    int questionNumber;
    public static QuizFragment newInstance(int questionNumber) {
        QuizFragment fragment = new QuizFragment();
        Bundle args = new Bundle();
        args.putInt("questionNumber", questionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    public class QuizDBWriter extends AsyncTask<Quiz, Quiz, Quiz> {
        QuizData quizData = new QuizData(getActivity());

        @Override
        protected Quiz doInBackground(Quiz... quizzes) {
            quizData.updateQuiz(quizzes[0]);
            return quizzes[0];
        }

        @Override
        protected void onPostExecute(Quiz quiz) {
            Log.d(DEBUG_TAG, "Quiz saved: " + quiz);
        }
    }
    @Override
    public void onCreate( Bundle savedInstanceState ) {
        super.onCreate( savedInstanceState );
        if( getArguments() != null ) {
            questionNumber = getArguments().getInt( "questionNumber" );
        }
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        Log.d(DEBUG_TAG,"Question Number: " + questionNumber);
        if (questionNumber == 0) {
            return inflater.inflate(R.layout.fragment_quiz_start, container, false);
        } else if (questionNumber == 7) {
            QuizData quizData = new QuizData(getActivity());
            return inflater.inflate(R.layout.fragment_result, container, false);
        } else {
            return inflater.inflate(R.layout.fragment_question, container, false);
        }
    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        QuizData quizData = new QuizData(getActivity());
        Quiz quiz = quizData.retrieveQuiz();

        if (questionNumber == 0) {
            TextView textView = view.findViewById(R.id.quiz_start);
            textView.setText("Swipe to start the quiz!");
        } else if (questionNumber == 7) {
                LocalDateTime currentDateTime = LocalDateTime.now();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                String formattedDateTime = currentDateTime.format(formatter);
                Log.d(DEBUG_TAG,"Formatted date and time: " + formattedDateTime);

            Button button = view.findViewById(R.id.submit_button);

            TextView textView = view.findViewById(R.id.score_view);



            Button button2 = view.findViewById(R.id.home_button);
            button2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Fragment homeFragment = new HomeFragment();
                    // Perform the fragment transaction to replace the current fragment with the QuizScoresFragment
                    FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
                    transaction.replace(R.id.fragment_container, homeFragment);
                    transaction.addToBackStack(null); // Add this transaction to the back stack
                    transaction.commit();
                }
            });

            button.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {

                    StateQuizData stateQuizData = new StateQuizData(getActivity());

                    State question1 = stateQuizData.retrieveState(quiz.getQuestion1());
                    State question2 = stateQuizData.retrieveState(quiz.getQuestion2());
                    State question3 = stateQuizData.retrieveState(quiz.getQuestion3());
                    State question4 = stateQuizData.retrieveState(quiz.getQuestion4());
                    State question5 = stateQuizData.retrieveState(quiz.getQuestion5());
                    State question6 = stateQuizData.retrieveState(quiz.getQuestion6());

                    String question1_right_answer = question1.getCapital();
                    String question2_right_answer = question2.getCapital();
                    String question3_right_answer = question3.getCapital();
                    String question4_right_answer = question4.getCapital();
                    String question5_right_answer = question5.getCapital();
                    String question6_right_answer = question6.getCapital();

                    String question1_answer = quiz.getQuestion1Answer();
                    String question2_answer = quiz.getQuestion2Answer();
                    String question3_answer = quiz.getQuestion3Answer();
                    String question4_answer = quiz.getQuestion4Answer();
                    String question5_answer = quiz.getQuestion5Answer();
                    String question6_answer = quiz.getQuestion6Answer();

                    int score = 0;

                    if (question1_answer.equals(question1_right_answer)) {
                        score = score + 1;
                    }
                    if (question2_answer.equals(question2_right_answer)) {
                        score+=1;
                    }
                    if (question3_answer.equals(question3_right_answer)) {
                        score+=1;
                    }
                    if (question4_answer.equals(question4_right_answer)) {
                        score+=1;
                    }
                    if (question5_answer.equals(question5_right_answer)) {
                        score+=1;
                    }
                    if (question6_answer.equals(question6_right_answer)) {
                        score+=1;
                    }
                    Log.d(DEBUG_TAG, "Score: " + score);

                    quiz.setScore(score);

                    textView.setText("Questions Right: " + String.valueOf(score));

                    quiz.setDate(formattedDateTime);
                    new QuizDBWriter().execute(quiz);
                }
            });
        } else {
            Log.d(DEBUG_TAG, "The found last quiz: " + quiz);

            StateQuizData stateQuizData = new StateQuizData(getActivity());

            State state = new State();

            if (questionNumber == 1) {
                state = stateQuizData.retrieveState(quiz.getQuestion1());
            } else if (questionNumber == 2) {
                state = stateQuizData.retrieveState(quiz.getQuestion2());
            } else if (questionNumber == 3) {
                state = stateQuizData.retrieveState(quiz.getQuestion3());
            } else if (questionNumber == 4) {
                state = stateQuizData.retrieveState(quiz.getQuestion4());
            } else if (questionNumber == 5) {
                state = stateQuizData.retrieveState(quiz.getQuestion5());
            } else if (questionNumber == 6) {
                state = stateQuizData.retrieveState(quiz.getQuestion6());
            }

            String[] questions = {state.getCapital(), state.getCity1(), state.getCity2()};

            shuffleArray(questions);

            // Inflate the layout for this fragment
            TextView question = view.findViewById(R.id.question_text);

            question.setText("What is the capital of " + state.getStateName() + "?");

            RadioGroup answerGroup = view.findViewById(R.id.answers_group);

            RadioButton answer1 = view.findViewById(R.id.answer1);
            answer1.setText(questions[0]);
            RadioButton answer2 = view.findViewById(R.id.answer2);
            answer2.setText(questions[1]);
            RadioButton answer3 = view.findViewById(R.id.answer3);
            answer3.setText(questions[2]);

            answer1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (answer1.isChecked()) {
                        if (questionNumber == 1) {
                            quiz.setQuestion1Answer(answer1.getText().toString());
                            new QuizDBWriter().execute(quiz);
                        } else if (questionNumber == 2) {
                            quiz.setQuestion2Answer(answer1.getText().toString());
                            new QuizDBWriter().execute(quiz);
                        } else if (questionNumber == 3) {
                            quiz.setQuestion3Answer(answer1.getText().toString());
                            new QuizDBWriter().execute(quiz);
                        } else if (questionNumber == 4) {
                            quiz.setQuestion4Answer(answer1.getText().toString());
                            new QuizDBWriter().execute(quiz);
                        } else if (questionNumber == 5) {
                            quiz.setQuestion5Answer(answer1.getText().toString());
                            new QuizDBWriter().execute(quiz);
                        } else if (questionNumber == 6) {
                            quiz.setQuestion6Answer(answer1.getText().toString());
                            new QuizDBWriter().execute(quiz);
                        }
                    }
                }
            });
            answer2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (answer2.isChecked()) {
                        if (questionNumber == 1) {
                            quiz.setQuestion1Answer(answer2.getText().toString());
                            new QuizDBWriter().execute(quiz);
                        } else if (questionNumber == 2) {
                            quiz.setQuestion2Answer(answer2.getText().toString());
                            new QuizDBWriter().execute(quiz);
                        } else if (questionNumber == 3) {
                            quiz.setQuestion3Answer(answer2.getText().toString());
                            new QuizDBWriter().execute(quiz);
                        } else if (questionNumber == 4) {
                            quiz.setQuestion4Answer(answer2.getText().toString());
                            new QuizDBWriter().execute(quiz);
                        } else if (questionNumber == 5) {
                            quiz.setQuestion5Answer(answer2.getText().toString());
                            new QuizDBWriter().execute(quiz);
                        } else if (questionNumber == 6) {
                            quiz.setQuestion6Answer(answer2.getText().toString());
                            new QuizDBWriter().execute(quiz);
                        }
                    }
                }
            });
            answer3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (answer3.isChecked()) {
                        if (questionNumber == 1) {
                            quiz.setQuestion1Answer(answer3.getText().toString());
                            new QuizDBWriter().execute(quiz);
                        } else if (questionNumber == 2) {
                            quiz.setQuestion2Answer(answer3.getText().toString());
                            new QuizDBWriter().execute(quiz);
                        } else if (questionNumber == 3) {
                            quiz.setQuestion3Answer(answer3.getText().toString());
                            new QuizDBWriter().execute(quiz);
                        } else if (questionNumber == 4) {
                            quiz.setQuestion4Answer(answer3.getText().toString());
                            new QuizDBWriter().execute(quiz);
                        } else if (questionNumber == 5) {
                            quiz.setQuestion5Answer(answer3.getText().toString());
                            new QuizDBWriter().execute(quiz);
                        } else if (questionNumber == 6) {
                            quiz.setQuestion6Answer(answer3.getText().toString());
                            new QuizDBWriter().execute(quiz);
                        }
                    }
                }
            });

            Log.d(DEBUG_TAG, "This is the quiz with answers selected: " + quiz);

        }
    }
    public static int getNumberOfQuestions() {
        return 8;
    }
    private static void shuffleArray(String[] array) {
        int n = array.length;
        Random random = new Random();

        for (int i = n - 1; i > 0; i--) {
            // Generate a random index between 0 and i (inclusive)
            int randomIndex = random.nextInt(i + 1);

            // Swap array[i] with the randomly selected element
            String temp = array[i];
            array[i] = array[randomIndex];
            array[randomIndex] = temp;
        }
    }
    public boolean correctAnswer(String answer1, String answer2) {
        if (answer1.equals(answer2)) {
            return true;
        } else {
            return false;
        }
    }


}
