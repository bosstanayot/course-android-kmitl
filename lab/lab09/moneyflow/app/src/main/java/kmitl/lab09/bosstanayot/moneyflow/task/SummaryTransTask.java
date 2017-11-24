package kmitl.lab09.bosstanayot.moneyflow.task;

import android.os.AsyncTask;

import kmitl.lab09.bosstanayot.moneyflow.sql.MoneyFlowDB;
import kmitl.lab09.bosstanayot.moneyflow.model.Summary;

public class SummaryTransTask extends AsyncTask<Void, Void, Summary> {

    private MoneyFlowDB db;
    private OnSummarySuccessListener listener;

    public SummaryTransTask(MoneyFlowDB db, OnSummarySuccessListener l) {
        this.db = db;
        this.listener = l;
    }

    @Override
    protected Summary doInBackground(Void... voids) {
        return db.transactionDAO().getSummary();
    }

    @Override
    protected void onPostExecute(Summary summary) {
        super.onPostExecute(summary);
        listener.onSummarySuccess(summary);
    }

    public interface OnSummarySuccessListener {
        void onSummarySuccess(Summary summary);
    }
}