package kmitl.lab09.bosstanayot.moneyflow.task;

import android.os.AsyncTask;

import kmitl.lab09.bosstanayot.moneyflow.sql.MoneyFlowDB;
import kmitl.lab09.bosstanayot.moneyflow.model.Transaction;

public class AddTransTask extends AsyncTask<Transaction, Void, Void> {

    private MoneyFlowDB db;
    private OnAddSuccessListener listener;

    public AddTransTask(MoneyFlowDB db, OnAddSuccessListener l) {
        this.db = db;
        this.listener = l;
    }

    @Override
    protected Void doInBackground(Transaction... transactions) {
        for (int i = 0; i < transactions.length; i++) {
            db.transactionDAO().insert(transactions[i]);
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        listener.onAddSuccess();
    }

    public interface OnAddSuccessListener {
        void onAddSuccess();
    }
}
