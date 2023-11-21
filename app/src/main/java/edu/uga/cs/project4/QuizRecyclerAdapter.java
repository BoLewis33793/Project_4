package edu.uga.cs.project4;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * This is an adapter class for the RecyclerView to show all job leads.
 */
public class QuizRecyclerAdapter
        extends RecyclerView.Adapter<QuizRecyclerAdapter.QuizHolder> {

    public static final String DEBUG_TAG = "QuizRecyclerAdapter";

    private final Context context;

    private List<Quiz> values;
    private List<Quiz> originalValues;

    public QuizRecyclerAdapter( Context context, List<Quiz> quizList ) {
        this.context = context;
        this.values = quizList;
        this.originalValues = new ArrayList<Quiz>( quizList );
    }

    // reset the originalValues to the current contents of values
    public void sync()
    {
        originalValues = new ArrayList<Quiz>( values );
    }

    // The adapter must have a ViewHolder class to "hold" one item to show.
    public static class QuizHolder extends RecyclerView.ViewHolder {

        TextView date;
        TextView score;

        public QuizHolder( View itemView ) {
            super( itemView );

            date = itemView.findViewById( R.id.date );
            score = itemView.findViewById( R.id.score );
        }
    }

    @NonNull
    @Override
    public QuizHolder onCreateViewHolder( ViewGroup parent, int viewType ) {
        // We need to make sure that all CardViews have the same, full width, allowed by the parent view.
        // This is a bit tricky, and we must provide the parent reference (the second param of inflate)
        // and false as the third parameter (don't attach to root).
        // Consequently, the parent view's (the RecyclerView) width will be used (match_parent).
        View view = LayoutInflater.from( parent.getContext()).inflate( R.layout.quiz, parent, false );
        return new QuizHolder( view );
    }

    // This method fills in the values of a holder to show a JobLead.
    // The position parameter indicates the position on the list of jobs list.
    @Override
    public void onBindViewHolder( QuizHolder holder, int position ) {

        Quiz quiz = values.get( position );

        Log.d( DEBUG_TAG, "onBindViewHolder: " + quiz );

        holder.date.setText( quiz.getDate() );
        holder.score.setText( String.valueOf(quiz.getScore()) );
    }

    @Override
    public int getItemCount() {
        if( values != null )
            return values.size();
        else
            return 0;
    }
}
