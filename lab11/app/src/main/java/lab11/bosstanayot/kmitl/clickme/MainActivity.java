package lab11.bosstanayot.kmitl.clickme;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView num;
    private CounterViewModel counterViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        num = findViewById(R.id.num);

        counterViewModel = ViewModelProviders.of(this).get(CounterViewModel.class);
        shownum();
    }

    public void clickbtn(View view) {
        counterViewModel.setCounter(counterViewModel.getCounter()+1);
        shownum();
        //num.setText(String.valueOf(count));
    }
    public void shownum(){
        num.setText(String.valueOf(counterViewModel.getCounter()));
    }
}
