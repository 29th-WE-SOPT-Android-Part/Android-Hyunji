# Android-Hyunji
![github_윤현지_ver1-21](https://user-images.githubusercontent.com/70698151/135754394-b330e710-a771-440d-8b38-f3ba5a62545b.png)


## 1️⃣ Week 1

### <LEVEL 1 필수과제>

**📌 SignInActivity**

<img width="35%" src="https://user-images.githubusercontent.com/48755814/136640122-c77fd162-9045-4693-b8bb-fbe438af34d8.gif"/>


  + 로그인 버튼 클릭 시 아이디, 비밀번호 모두 입력된 경우에만 HomeActivity로 이동
  + 그렇지 않다면 "로그인 실패"라는 토스트 메시지 출력

```kotlin
binding.btnLogin.setOnClickListener {
            if(canLogin()){
                Toast.makeText(this,"안녕하세요 현지님",Toast.LENGTH_SHORT).show()
                startActivity(intent1)
            }
            else{
                Toast.makeText(this,"로그인 실패",Toast.LENGTH_SHORT).show()
            }
        }
        
 fun canLogin():Boolean{
        return(binding.homeIdEdit.text.toString().isNotEmpty() && binding.homePwEdit.text.toString().isNotEmpty())

    }
```

  + EditText는 미리보기 글씨 필요
  + 비밀번호 EditText는 입력 내용이 가려져야 함
  ```kotlin
  <EditText
        android:id="@+id/home_pw_edit"
        android:hint="비밀번호를 입력해주세요"
        android:inputType="textPassword"/>
  ```
  

**📌 SignUpActivity**

<img width="35%" src="https://user-images.githubusercontent.com/48755814/136640389-caab707a-adf2-4514-b917-4f73762bd36a.gif"/>

  + 이름,아이디,비밀번호,입력이 모두 되어있을 때만 다시 SignInActivity로 이동
  + 모든 입력이 되어있지 않다면 토스트 메세지 출력
```kotlin
binding.btnRegisterFinish.setOnClickListener {
            if(canRegister()) { //칸 다 채웠을 때
                finish() //다시 SignInActivity로 이동
                } 
            else
            {
                Toast.makeText(this,"입력되지 않은 정보가 있습니다", Toast.LENGTH_SHORT).show()
            }
        }
        setContentView(binding.root)
    }

    private fun canRegister():Boolean{ //모든 칸 입력됐는지 확인하는 함수
        return(binding.registerIdEdit.text.toString().isNotEmpty() && binding.registerPwEdit.text.toString().isNotEmpty() && binding.registerNameEdit.text.toString().isNotEmpty())
    }
  ```
       
    
**📌 HomeActivity**

<img width="35%" src="https://user-images.githubusercontent.com/48755814/136640733-e3bf0f06-75f5-4f07-8388-3b3ee80e4304.gif"/>

  + Constraintlayout, ImageView, TextView를 활용해서 자기소개 페이지 만들기   
  (이 부분은 코드로 첨부하기 너무 길어서,,Github를 확인해주세요! 😅)

---

### <LEVEL 2 도전과제>


**1. 화면이동+@**
  + 회원가입 성공 시 이전 로그인 화면으로 돌아오기
  + 단, 회원가입 시 입력한 아이디와 비밀번호가 로그인 창에 입력되어 있어야 함   

SignUpActivity.kt
```kotlin
 binding.btnRegisterFinish.setOnClickListener {
            if(canRegister()) { //칸 다 채웠을 때
                val intent_s= Intent(this,SignInActivity::class.java).apply {
                    putExtra("id",binding.registerIdEdit.text.toString())
                    putExtra("pw",binding.registerPwEdit.text.toString())
                }
                setResult(RESULT_OK,intent_s)
                finish() 
              }
  ```   
  SignInActivity.kt
```kotlin
 class SignInActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignInBinding
    lateinit var activityResultLauncher: ActivityResultLauncher<Intent>
    
    override fun onCreate(savedInstanceState: Bundle?) {
    
    activityResultLauncher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()) {
            if (it.resultCode == RESULT_OK) {
                val id = it.data?.getStringExtra("id")
                Log.d("SignInActivity",id.toString())
                binding.homeIdEdit.setText(id)

                val pw = it.data?.getStringExtra("pw")
                Log.d("SignInActivity",pw.toString())
                binding.homePwEdit.setText(pw)
            }
        else{
            Log.d("SignInActivity","result failed")
        }}
        
        binding.btnRegister.setOnClickListener{
            activityResultLauncher.launch(Intent(this,SignUpActivity::class.java))

        }
        .
        .

  ```
              
**2. 암시적 인텐트**
  + 깃허브로 이동하는 버튼을 만들고 본인의 깃허브 페이지로 암시적 인텐트를 활용해 이동

 ```kotlin
 binding.homeGithubiconIv.setOnClickListener {
            val intent=Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/hyunji24"))
            startActivity(intent)
        }
```
 > 💡 명시적 인텐트 vs.암시적 인텐트   
 ```
  명시적 인텐트: 주로 애플리케이션 내부, 실행하고자 하는 컴포넌트나 액티비티가 명확할 때 사용   
  암시적 인텐트: 어떤 의도만으로 원하는 컴포넌트 실행(ex. 웹페이지,외부 앱)   
                -> 인텐트 객체에 '특정 웹페이지 띄우고 싶다'라는 정보만 담아 startActivity()함수를 호출하면 시스템은 의도를 적절히 처리할 수 있는 컴포넌트를 찾아 처리결과를 사용자에게 제공한다.
 ```

**3. ScrollView와 사진비율**
  + ScrollView를 이용해 뷰가 스크롤 되도록 구현
  + constraintDimensionRatio 속성을 이용해서 1:1로 사진 비율 맞추기
```kotlin
<ScrollView
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context=".HomeActivity">

    <androidx.constraintlayout.widget.ConstraintLayout
        android:layout_width="match_parent"
        android:layout_height="wrap_content">
        
        <ImageView
        android:id="@+id/home_profile_iv"
        app:layout_constraintDimensionRatio="1:1"/>
        
   </androidx.constraintlayout.widget.ConstraintLayout>
 </ScrollView>
 ```
   
---

### <LEVEL 3 심화과제>   

**1. DataBinding**  
  + DataBinding을 이용해 HomeActivity의 정보를 코드단에서 한번에 집어넣기
  + DataBinding을 사용하려면 build.gradle(:app)에 buildFeatures{dataBinding true} 입력해줘야 함   
  

User.kt
```kotlin
data class User(val name:String, val age:String, val mbti:String)

```
HomeActivity.kt의 onCreate() 내부
```kotlin
binding.user=User("윤현지","24","isfp")
  ```
activity_home.xml
```kotlin
<layout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools">

    <data>
        <variable
            name="user"
            type="com.example.androidseminar.User" />
    </data>
    
    <TextView
       .
       .
        android:text="@{user.name}"
       />
  ```

> 💡 ViewBinding vs.DataBinding   
 ```
  * ViewBinding:
  - view와 상호 작용하는 코드를 더욱 쉽게 작성할 수 있도록 도와주는 기능
  - xml파일에서 각 뷰는 id값을 가진다.
  - 이전에 쓰이던 findViewById 보다 훨씬 효율적이다! findViewById 같은 경우 컴포넌트들이 늘어나면 코드가 매우매우 길어진다..
  - null safety를 보장한다.
  -ViewBinding 설정을 하면 각 xml파일에 대해 binding class를 자동으로 생성한다.
  - 카멜 표기법에 따라 네이밍이 된다.
  - 상대적으로 간단하며 용량이 절약된다.
 ```
 ```
  * DataBinding:
  - 데이터와 뷰를 연결하는 작업을 레이아웃에서 처리하는 기술
  - 실습처럼, xml에 직접 값을 입력하지 않고 코드를 통해 입력될 수 있도록 한다.
  - ViewBinding처럼 뷰를 직접 참조하는 바인딩 클래스를 생성한다.
  - 차이: 데이터 바인딩 라이브러리는 <layout> 태그를 사용하여 만든 레이아웃만 처리한다.
  - 내부적으로 데이터 바인딩 클래스를 생성할때 루트뷰에 tag를 삽입하는데 뷰바인딩은 그런 작업이 없다.
  
 ```
 
**2. setOnClickListener과 람다식** 

코틀린은 자바와는 달리 '함수형 프로그래밍'이 가능한 언어이다!   
> 💡 함수형 프로그래밍?   
함수의 유기적 연결 및 동작이 프로그램의 최우선이 되는 프로그래밍 방식.    
함수가 일급 객체로써의 의미를 가짐.

> 💡 람다식(lambda)   
 코틀린에서는 기본으로 함수 자체를 람다식으로 생성하는 것을 지원한다. =익명함수   

자바
```java
button.setOnClickListener(new OnClickListener(){ 
      @Override 
    public void onClick(View v){ 
    //... 
    } 
   });
   ```

위와 같이 Button에 Click 발생시 그 이벤트를 캐치할 수 있는 ClickListener를 등록 할 수 있다. 그리고 이때 위와 같이 자바는 무명클래스의 인스턴스를 만들어야 한다.

코틀린에서는 위의 무명클래스 인스턴스 대신 람다를 넘겨줄 수 있다.


```kotlin
button.setOnClickListener{view -> ...}
```
이런 코드가 작동하는 이유는 OnClickListener에 추상 메소드가 단 하나만 있기 때문이다.   
(그런 인터페이스를 함수형 인터페이스 또는 SAM 인터페이스라고 한다.)   
자바는 위와 같이 함수형 인터페이스를 활용하는 메소드가 많기 때문에 코틀린에서 함수형 인터페이스를 인자로 취하는 자바 메소드를 호출할 때 람다를 넘길 수 있게 해준다.


