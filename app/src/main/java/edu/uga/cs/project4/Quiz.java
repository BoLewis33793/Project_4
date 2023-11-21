package edu.uga.cs.project4;

import java.util.List;

public class Quiz {
    private long id;
    private String date;
    private long question1;
    private long question2;
    private long question3;
    private long question4;
    private long question5;
    private long question6;
    private int score;
    private int numAnswers;
    private String question1Answer;
    private String question2Answer;
    private String question3Answer;
    private String question4Answer;
    private String question5Answer;
    private String question6Answer;

    public Quiz() {
        this.id = -1;
        this.date = null;
        this.question1 = -1;
        this.question2 = -1;
        this.question3 = -1;
        this.question4 = -1;
        this.question5 = -1;
        this.question6 = -1;
        this.score = 0;
        this.numAnswers = 0;
        this.question1Answer = null;
        this.question2Answer = null;
        this.question3Answer = null;
        this.question4Answer = null;
        this.question5Answer = null;
        this.question6Answer = null;
    }
    public Quiz(long question1, long question2, long question3, long question4, long question5, long question6) {
        this.id = -1;
        this.date = "";
        this.question1 = question1;
        this.question2 = question2;
        this.question3 = question3;
        this.question4 = question4;
        this.question5 = question5;
        this.question6 = question6;
        this.score = 0;
        this.numAnswers = 0;
        this.question1Answer = "";
        this.question2Answer = "";
        this.question3Answer = "";
        this.question4Answer = "";
        this.question5Answer = "";
        this.question6Answer = "";
    }
    public Quiz(String date, long question1, long question2, long question3, long question4, long question5, long question6,
                int score, int numAnswers, String question1Answer, String question2Answer,
                String question3Answer, String question4Answer, String question5Answer, String question6Answer) {
        this.id = -1;
        this.date = date;
        this.question1 = question1;
        this.question2 = question2;
        this.question3 = question3;
        this.question4 = question4;
        this.question5 = question5;
        this.question6 = question6;
        this.score = score;
        this.numAnswers = numAnswers;
        this.question1Answer = question1Answer;
        this.question2Answer = question2Answer;
        this.question3Answer = question3Answer;
        this.question4Answer = question4Answer;
        this.question5Answer = question5Answer;
        this.question6Answer = question6Answer;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public long getQuestion1() {
        return question1;
    }

    public void setQuestion1(long question1) {
        this.question1 = question1;
    }

    public long getQuestion2() {
        return question2;
    }

    public void setQuestion2(long question2) {
        this.question2 = question2;
    }

    public long getQuestion3() {
        return question3;
    }

    public void setQuestion3(long question3) {
        this.question3 = question3;
    }

    public long getQuestion4() {
        return question4;
    }

    public void setQuestion4(long question4) {
        this.question4 = question4;
    }

    public long getQuestion5() {
        return question5;
    }

    public void setQuestion5(long question5) {
        this.question5 = question5;
    }

    public long getQuestion6() {
        return question6;
    }

    public void setQuestion6(long question6) {
        this.question6 = question6;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getNumAnswers() {
        return numAnswers;
    }

    public void setNumAnswers(int numAnswers) {
        this.numAnswers = numAnswers;
    }

    public String getQuestion1Answer() {
        return question1Answer;
    }

    public void setQuestion1Answer(String question1Answer) {
        this.question1Answer = question1Answer;
    }

    public String getQuestion2Answer() {
        return question2Answer;
    }

    public void setQuestion2Answer(String question2Answer) {
        this.question2Answer = question2Answer;
    }

    public String getQuestion3Answer() {
        return question3Answer;
    }

    public void setQuestion3Answer(String question3Answer) {
        this.question3Answer = question3Answer;
    }

    public String getQuestion4Answer() {
        return question4Answer;
    }

    public void setQuestion4Answer(String question4Answer) {
        this.question4Answer = question4Answer;
    }

    public String getQuestion5Answer() {
        return question5Answer;
    }

    public void setQuestion5Answer(String question5Answer) {
        this.question5Answer = question5Answer;
    }

    public String getQuestion6Answer() {
        return question6Answer;
    }

    public void setQuestion6Answer(String question6Answer) {
        this.question6Answer = question6Answer;
    }

    @Override
    public String toString() {
        return "Quiz{" +
                "id=" + id +
                ", date='" + date + '\'' +
                ", question1=" + question1 +
                ", question2=" + question2 +
                ", question3=" + question3 +
                ", question4=" + question4 +
                ", question5=" + question5 +
                ", question6=" + question6 +
                ", score=" + score +
                ", numAnswers=" + numAnswers +
                ", question1Answer='" + question1Answer + '\'' +
                ", question2Answer='" + question2Answer + '\'' +
                ", question3Answer='" + question3Answer + '\'' +
                ", question4Answer='" + question4Answer + '\'' +
                ", question5Answer='" + question5Answer + '\'' +
                ", question6Answer='" + question6Answer + '\'' +
                '}';
    }
}
