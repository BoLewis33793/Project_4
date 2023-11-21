package edu.uga.cs.project4;

public class State {
    private long id;
    private String stateName;
    private String capital;
    private String city1;
    private String city2;

    public State() {
        this.id = -1;
        this.stateName = null;
        this.capital = null;
        this.city1 = null;
        this.city2 = null;
    }

    public State(String stateName, String capital, String city1, String city2) {
        this.id = -1; // Will be set by setter method
        this.stateName = stateName;
        this.capital = capital;
        this.city1 = city1;
        this.city2 = city2;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getStateName() {
        return stateName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }

    public String getCapital() {
        return capital;
    }

    public void setCapital(String capital) {
        this.capital = capital;
    }

    public String getCity1() {
        return city1;
    }

    public void setCity1(String city1) {
        this.city1 = city1;
    }

    public String getCity2() {
        return city2;
    }

    public void setCity2(String city2) {
        this.city2 = city2;
    }

    @Override
    public String toString() {
        return "State{" +
                "stateName='" + stateName + '\'' +
                ", capital='" + capital + '\'' +
                ", city1='" + city1 + '\'' +
                ", city2='" + city2 + '\'' +
                '}';
    }
}
