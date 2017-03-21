package choongyul.android.com.dependencyinjection;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.rest.spring.annotations.Get;
import org.androidannotations.rest.spring.annotations.Rest;
import org.androidannotations.rest.spring.annotations.RestService;
import org.springframework.http.converter.StringHttpMessageConverter;


@EActivity(R.layout.activity_main2)
public class Main2Activity extends AppCompatActivity {

    // 어노테이션을 쓰면 onCreate가 없어도된다.
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main2);
//      }

    @ViewById
    TextView textView;

    public void setTextView2(View view) {
        textView.setText("어노테이션 실험중");
    }

    public void getGoogle(View view) {

        String url = "http://google.com";
        runBackground(url);
    }

    // 백그라운드 thread 에서 동작
    @Background
    public void runBackground(String url) {

        String result = googleService.getData();
        writeOnUi(result);
    }

    // 다시 uithread에서 동작되는 부분
    @UiThread
    public void writeOnUi(String result){
        textView.setText(result);
    }
    // 아래 정의된 인터페이스를 사용
    @RestService
    Google googleService;

}

// Rest 애너테이션은 Top 레벨에서만 사용가능
// (단일 레벨에서만)
@Rest(rootUrl = "http://www.google.com", converters = {StringHttpMessageConverter.class})
interface Google {

    @Get("/")
    String getData();

}
