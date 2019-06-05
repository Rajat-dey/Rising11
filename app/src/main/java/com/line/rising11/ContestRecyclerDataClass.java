package com.line.rising11;

public class ContestRecyclerDataClass {
    private String prize,entry,max_pb_val,min_pb_val,winner,box1,box2;
    public ContestRecyclerDataClass(String prize,String entry,String max_pb_val,String min_pb_val,String winner,String box1,String box2)
    {
        this.prize=prize;
        this.entry=entry;
        this.max_pb_val=max_pb_val;
        this.min_pb_val=min_pb_val;
        this.winner=winner;
        this.box1=box1;
        this.box2=box2;
    }

    public String getEntry() {
        return entry;
    }

    public void setEntry(String entry) {
        this.entry = entry;
    }

    public String getBox1() {
        return box1;
    }

    public void setBox1(String box1) {
        this.box1 = box1;
    }

    public String getMax_pb_val() {
        return max_pb_val;
    }

    public void setMax_pb_val(String max_pb_val) {
        this.max_pb_val = max_pb_val;
    }

    public String getMin_pb_val() {
        return min_pb_val;
    }

    public void setMin_pb_val(String min_pb_val) {
        this.min_pb_val = min_pb_val;
    }

    public String getBox2() {
        return box2;
    }

    public void setBox2(String box2) {
        this.box2 = box2;
    }

    public String getPrize() {
        return prize;
    }

    public void setPrize(String prize)
    {
        this.prize = prize;
    }

    public String getWinner()
    {
        return winner;
    }

    public void setWinner(String winner) {
        this.winner = winner;
    }

}
