package kmitl.lab09.bosstanayot.moneyflow.task;

import android.os.AsyncTask;

import kmitl.lab09.bosstanayot.moneyflow.sql.MoneyFlowDB;
import kmitl.lab09.bosstanayot.moneyflow.model.Transaction;

public class DeleteTransTask extends AsyncTask<Transaction, Void, Void> {

    private MoneyFlowDB db;
    private OnDeleteSuccessListener listener;

    public DeleteTransTask(MoneyFlowDB db, OnDeleteSuccessListener l) {
        this.db = db;
        this.listener = l;
    }

    @Override
    protected Void doInBackground(Transaction... transactions) {
        for (int i = 0; i < transactions.length; i++) {
            db.transactionDAO().delete(transactions[i]);
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        listener.onDeleteSuccess();
    }

    public interface OnDeleteSuccessListener {
        void onDeleteSuccess();
    }
}