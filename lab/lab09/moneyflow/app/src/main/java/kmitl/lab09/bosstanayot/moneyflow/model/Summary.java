package kmitl.lab09.bosstanayot.moneyflow.model;

import android.arch.persistence.room.ColumnInfo;

public class Summary {

    @ColumnInfo(name = "total_income")
    private int inCome;

    @ColumnInfo(name = "total_outcome")
    private int outCome;

    public int getInCome() {
        return inCome;
    }

    public void setInCome(int inCome) {
        this.inCome = inCome;
    }

    public int getOutCome() {
        return outCome;
    }

    public void setOutCome(int outCome) {
        this.outCome = outCome;
    }

    public int getSum() {
        return inCome - outCome;
    }
}