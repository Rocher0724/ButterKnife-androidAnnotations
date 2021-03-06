# ButterKnife-androidAnnotations
버터나이프와 Android Annotations를 사용해보았습니다.


#### :bookmark_tabs:  Android Annotations사용법 :

* gradle 설정 - app gradle

```
// 최상단 app plugin에 apt와 변수 AAVersion에 버전을 담아 선언 
apply plugin: 'com.neenbedankt.android-apt'
def AAVersion = '4.1.0'

// 디펜던시 생성 $AAVersion 을 통해 최상단에서 선언해준 AAVersion을 사용해주었다.
dependencies {
    apt "org.androidannotations:androidannotations:$AAVersion"
    compile "org.androidannotations:androidannotations:$AAVersion"
    compile "org.androidannotations:androidannotations-api:$AAVersion"

    // rest spring을 사용하기위한 디펜던시
    apt "org.androidannotations:rest-spring:$AAVersion"
    compile "org.androidannotations:rest-spring-api:$AAVersion"

    //springframework 사용하기 위한 디펜던시 
    compile 'org.springframework.android:spring-android-rest-template:2.0.0.M3'
}
```

* build gradle 설정 

```
dependencies {
		// 추가 한다.
        classpath 'com.neenbedankt.gradle.plugins:android-apt:1.8' 
    }
```

* Android annotation을 이용한 HttpUrlConnection

```
// import부분 생략 


@EActivity(R.layout.activity_main2)
public class Main2Activity extends AppCompatActivity {

// 	  @EActivity 어노테이션을 쓰면 아래 onCreate가 없어도된다.
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main2);
//      }

	//xml에서 선언된 텍스트뷰의 R.id가 textView이고 클래스에서 선언된 TextView의 변수명이 textView로 같을때 인식한다.
    @ViewById
    TextView textView;

    // onclick method를 만들어주고 xml에서 onClick을 달아준다. 
    public void setTextView2(View view) {
        textView.setText("어노테이션 실험중");
    }

    // onclick method를 만들어주고 xml에서 onClick을 달아준다. 
    public void getGoogle(View view) {

        String url = "http://google.com";
        runBackground(url);
    }

    // 백그라운드 thread 에서 동작. thread를 쉽게 실행시켜준다.
    @Background
    public void runBackground(String url) {

        String result = googleService.getData();
        writeOnUi(result);
    }

    // 다시 uithread에서 동작되는 부분. UiThread를 통해 ui를 변경할 수 있다.
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
```

* 사용시 주의사항 manifest에서 activity를 activity_ 로 사용해줘야하고 class에서 사용할 때 (ex: intent 날릴 때) Main2Activity_.class 라고 해줘야한다.
```
	<activity android:name=".Main2Activity_">
        <intent-filter>
            <action android:name="android.intent.action.MAIN" />

            <category android:name="android.intent.category.LAUNCHER" />
        </intent-filter>
    </activity>
```
자세한내용은 [http://androidannotations.org/] 참조 

[http://androidannotations.org/]:<http://androidannotations.org/>