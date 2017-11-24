package kmitl.lab09.bosstanayot.moneyflow;

import android.arch.persistence.room.Room;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.NumberFormat;
import java.util.List;

import kmitl.lab09.bosstanayot.moneyflow.adapter.RecyclerItemClickListener;
import kmitl.lab09.bosstanayot.moneyflow.adapter.TransactionAdapter;
import kmitl.lab09.bosstanayot.moneyflow.sql.MoneyFlowDB;
import kmitl.lab09.bosstanayot.moneyflow.model.Summary;
import kmitl.lab09.bosstanayot.moneyflow.model.Transaction;
import kmitl.lab09.bosstanayot.moneyflow.task.FetchTransTask;
import kmitl.lab09.bosstanayot.moneyflow.task.SummaryTransTask;

public class MainActivity extends AppCompatActivity {

    private MoneyFlowDB db;

    private TextView moneytxt;
    private RecyclerView list;
    private TransactionAdapter adapter;
    private Button addbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = Room.databaseBuilder(getApplicationContext(), MoneyFlowDB.class, "DB")
                .fallbackToDestructiveMigration()
                .build();
        moneytxt = findViewById(R.id.moneytxt);
        list = findViewById(R.id.list);
        addbtn = findViewById(R.id.addbtn);

        adapter = new TransactionAdapter(this);
        list.setLayoutManager(new LinearLayoutManager(this));
        list.setAdapter(adapter);
        list.addOnItemTouchListener(new RecyclerItemClickListener(this, list,
                new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        startTransactionActivity(adapter.getTransactionList().get(position));
                    }

                    @Override
                    public void onLongItemClick(View view, int position) {

                    }
                }));

        addbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startTransactionActivity(null);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        new FetchTransTask(db, new FetchTransTask.OnFetchSuccessListener() {
            @Override
            public void onFetchSuccess(List<Transaction> transactionList) {
                updateList(transactionList);
            }
        }).execute();

        new SummaryTransTask(db, new SummaryTransTask.OnSummarySuccessListener() {
            @Override
            public void onSummarySuccess(Summary summary) {
                updateMoney(summary);
            }
        }).execute();
    }


    private void startTransactionActivity(@Nullable Transaction transaction) {
        Intent intent = new Intent(MainActivity.this, TransactionActivity.class);
        intent.putExtra(Transaction.class.getSimpleName(), transaction);
        startActivity(intent);
    }


    private void updateList(List<Transaction> transactionList) {
        if (transactionList.size() == 0) {
            list.setVisibility(View.GONE);
        } else {
            list.setVisibility(View.VISIBLE);
            adapter.setTransactionList(transactionList);
            adapter.notifyDataSetChanged();
        }
    }

    private void updateMoney(Summary summary) {
        int sum = summary.getSum();
        int totalIncome = summary.getInCome();

        if ((float) sum / totalIncome <= 0.25) {
            moneytxt.setTextColor(ContextCompat.getColor(this, android.R.color.holo_red_light));
        } else if ((float) sum / totalIncome <= 0.5) {
            moneytxt.setTextColor(ContextCompat.getColor(this, android.R.color.holo_orange_light));
        } else {
            moneytxt.setTextColor(ContextCompat.getColor(this, android.R.color.holo_green_light));
        }
        moneytxt.setText(NumberFormat.getNumberInstance().format(sum));
    }
}