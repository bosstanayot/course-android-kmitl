package kmitl.lab09.bosstanayot.moneyflow;

import android.arch.persistence.room.Room;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import kmitl.lab09.bosstanayot.moneyflow.sql.MoneyFlowDB;
import kmitl.lab09.bosstanayot.moneyflow.model.Transaction;
import kmitl.lab09.bosstanayot.moneyflow.task.AddTransTask;
import kmitl.lab09.bosstanayot.moneyflow.task.DeleteTransTask;
import kmitl.lab09.bosstanayot.moneyflow.task.UpdateTransTask;

public class TransactionActivity extends AppCompatActivity implements View.OnClickListener {

    private MoneyFlowDB db;
    private Transaction transaction;

    private RadioGroup radio;
    private EditText moneyTitle, money_amt;
    private Button savebtn, delbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction);
        db = Room.databaseBuilder(getApplicationContext(), MoneyFlowDB.class, "DB")
                .fallbackToDestructiveMigration()
                .build();

        radio = findViewById(R.id.radio);
        moneyTitle = findViewById(R.id.moneyTitle);
        money_amt = findViewById(R.id.money_amt);
        savebtn = findViewById(R.id.savbtn);
        delbtn = findViewById(R.id.delbtn);

        radio.check(R.id.income);
        savebtn.setOnClickListener(this);
        delbtn.setOnClickListener(this);

        transaction = getIntent().getParcelableExtra(Transaction.class.getSimpleName());

        if (transaction != null) {
            setTitle(getString(R.string.title_edit));
            if (transaction.getType().equals(getString(R.string.type_income))) {
                radio.check(R.id.income);
            } else {
                radio.check(R.id.outcome);
            }
            moneyTitle.setText(transaction.getDesc());
            money_amt.setText(String.valueOf(transaction.getAmount()));
            delbtn.setVisibility(View.VISIBLE);
        } else {
            setTitle(getString(R.string.title_add));
            delbtn.setVisibility(View.GONE);
        }
    }

    private void save() {
        String type;
        String describe;
        int amount;

        switch (radio.getCheckedRadioButtonId()) {
            case R.id.income:
                type = getString(R.string.type_income);
                break;
            case R.id.outcome:
                type = getString(R.string.type_outcome);
                break;
            default:
                type = "";
        }

        describe = moneyTitle.getText().toString();

        try {
            amount = Integer.parseInt(money_amt.getText().toString());
        } catch (IllegalArgumentException ignore) {
            Toast.makeText(this, "Invalid input", Toast.LENGTH_SHORT).show();
            return;
        }

        Transaction transaction = new Transaction(type, describe, amount);

        if (this.transaction != null) {
            this.transaction.updateInfo(transaction);
            new UpdateTransTask(db, new UpdateTransTask.OnUpdateSuccessListener() {
                @Override
                public void onUpdateSuccess() {
                    finish();
                }
            }).execute(this.transaction);

        } else {
            new AddTransTask(db, new AddTransTask.OnAddSuccessListener() {
                @Override
                public void onAddSuccess() {
                    finish();
                }
            }).execute(transaction);
        }
    }

    private void delete() {
        new DeleteTransTask(db, new DeleteTransTask.OnDeleteSuccessListener() {
            @Override
            public void onDeleteSuccess() {
                finish();
            }
        }).execute(transaction);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.savbtn) {
            save();
        } else if (view.getId() == R.id.delbtn) {
            delete();
        }
    }
}
