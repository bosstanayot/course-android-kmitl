package kmitl.lab09.bosstanayot.moneyflow.task;

import android.os.AsyncTask;

import java.util.List;

import kmitl.lab09.bosstanayot.moneyflow.sql.MoneyFlowDB;
import kmitl.lab09.bosstanayot.moneyflow.model.Transaction;

public class FetchTransTask extends AsyncTask<Void, Void, List<Transaction>> {

    private MoneyFlowDB db;
    private OnFetchSuccessListener listener;

    public FetchTransTask(MoneyFlowDB db, OnFetchSuccessListener l) {
        this.db = db;
        this.listener = l;
    }

    @Override
    protected List<Transaction> doInBackground(Void... voids) {
        return db.transactionDAO().getTransactions();
    }

    @Override
    protected void onPostExecute(List<Transaction> transactionList) {
        super.onPostExecute(transactionList);
        listener.onFetchSuccess(transactionList);
    }

    public interface OnFetchSuccessListener {
        void onFetchSuccess(List<Transaction> transactionList);
    }

}