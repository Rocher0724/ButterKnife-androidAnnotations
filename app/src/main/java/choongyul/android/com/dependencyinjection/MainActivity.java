package choongyul.android.com.dependencyinjection;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

//    @BindView(R.id.button)
//    Button button;

    @BindView(R.id.textView)
    TextView textView;

    // Injects R.id.myTextView
            TextView result;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

    }

//    @OnClick(R.id.button)
    public void setTextView1(View view) {
        switch (view.getId()) {
            case R.id.button:
                textView.setText("Hello Butterknife!");
                break;
            case R.id.button2:
                Intent intent = new Intent(MainActivity.this, Main2Activity_.class);
                startActivity(intent);
                break;
            case R.id.button3:
                textView.setText("Hello Butterknife!");
                break;
            case R.id.button4:
                textView.setText("Hello Butterknife!");
                break;
            case R.id.button5:
                textView.setText("Hello Butterknife!");
                break;

        }
    }
}
