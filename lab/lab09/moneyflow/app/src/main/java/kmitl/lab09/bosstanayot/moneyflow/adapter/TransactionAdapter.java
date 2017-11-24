package kmitl.lab09.bosstanayot.moneyflow.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

import kmitl.lab09.bosstanayot.moneyflow.R;
import kmitl.lab09.bosstanayot.moneyflow.model.Transaction;

public class TransactionAdapter extends RecyclerView.Adapter<TransactionAdapter.TransactionHolder> {

    private Context context;
    private List<Transaction> transactionList;

    public TransactionAdapter(Context context) {
        this.context = context;
        this.transactionList = new ArrayList<>();
    }

    @Override
    public TransactionHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_transaction, parent, false);
        return new TransactionHolder(view);
    }

    @Override
    public void onBindViewHolder(TransactionHolder holder, int position) {
        Transaction transaction = transactionList.get(position);

        if (transaction.getType().equals(context.getString(R.string.type_income))) {
            holder.linear_layout.setBackgroundResource(R.color.itemDefault);
        } else {
            holder.linear_layout.setBackgroundResource(R.color.itemHighlight);
        }

        if (transaction.getType().equals(context.getString(R.string.type_income))) {
            holder.plus.setText("+");
            holder.plus.setTextColor(Color.parseColor("#000000"));
        } else if (transaction.getType().equals(context.getString(R.string.type_outcome))) {
            holder.plus.setText("-");
            holder.plus.setTextColor(Color.parseColor("#000000"));
        }

        holder.desctext.setText(transaction.getDesc());
        holder.amounttxt.setText(NumberFormat.getNumberInstance().format(transaction.getAmount()));
    }

    @Override
    public int getItemCount() {
        return transactionList.size();
    }

    public List<Transaction> getTransactionList() {
        return transactionList;
    }

    public void setTransactionList(List<Transaction> transactionList) {
        this.transactionList = transactionList;
    }

    public class TransactionHolder extends RecyclerView.ViewHolder {
        public View linear_layout;
        public TextView plus, desctext, amounttxt;
        public TransactionHolder(View itemView) {
            super(itemView);

            linear_layout = itemView.findViewById(R.id.linear_Layout);
            plus = itemView.findViewById(R.id.plus);
            desctext = itemView.findViewById(R.id.desc);
            amounttxt = itemView.findViewById(R.id.amount);
        }
    }
}