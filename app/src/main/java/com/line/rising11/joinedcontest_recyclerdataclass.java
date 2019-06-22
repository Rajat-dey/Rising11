package com.line.rising11;

public class joinedcontest_recyclerdataclass {

    private  String prizepool,spots,entry,joined_with,points,rank;

    public joinedcontest_recyclerdataclass(String prizepool,String spots,String entry,String joined_with,String points,String rank)
    {

        this.prizepool=prizepool;
        this.spots=spots;
        this.entry=entry;
        this.joined_with=joined_with;
        this.points=points;
        this.rank=rank;

    }

    public String getPrizepool() {
        return prizepool;
    }

    public void setPrizepool(String prizepool) {
        this.prizepool = prizepool;
    }

    public String getSpots() {
        return spots;
    }


    public void setSpots(String spots) {
        this.spots=spots;
    }


    public String getEntry() {
        return entry;
    }

    public void setEntry(String entry) {
        this.entry = entry;
    }

    public String getJoined_with() {
        return joined_with;
    }

    public void setJoined_with(String joined_with) {
        this.joined_with=joined_with;
    }

    public String getPoints() {
        return points;
    }

    public void setPoints(String points) {
        this.points=points;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank=rank;
    }


}
