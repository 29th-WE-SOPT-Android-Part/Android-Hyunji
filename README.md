## 1️⃣ Week 7

<p align="center"><img width="35%" src="https://user-images.githubusercontent.com/48755814/146564136-aac8404b-f792-4480-ad3d-50af610eb038.gif"/></p>

   

### ⭐ LEVEL 1 필수과제 ⭐   


**✔ 1-1 온보딩 화면 만들기**   

1) 라이브러리 추가
2) Navigation Graph 만들기

3) Fragment 전환 이루어질 액티비티에 NavHostFragment 설정
4) 온보딩에 보여줄 Fragment 3개 만들고 Navigation Graph에 작업 명세  
<p align="center"><img src="https://user-images.githubusercontent.com/48755814/146556086-8ad570cc-cb64-424e-938b-51c6e359d35b.JPG"/></p> 
5) 각 Fragment에 코드 작성   

```kotlin
binding.btnNext.setOnClickListener {
            findNavController().navigate(R.id.action_secondOnboardingFragment2_to_thirdOnboardingFragment)
        }
```
4) 로그인화면으로 전환시 host 담당하는 액티비티 끝내기
```kotlin
binding.btnLogin.setOnClickListener {
            val intent= Intent(activity,SignInActivity::class.java)
            startActivity(intent)
            activity?.finish()
        }
```


**✔ 1-2 SharedPreferences 활용해서 자동로그인/자동로그인 해제 구현** 
   
 + SOPTSharedPreferences.kt   
 ```kotlin
 package com.example.androidseminar.util

import android.content.Context

object SOPTSharedPreferences {
    private const val USER_AUTH = "USER_AUTH"
    private const val AUTO_LOGIN="AUTO_CLEAR"


    fun getAutoLogin(context:Context):Boolean{ //전역으로 부르기위해 context를 매개변수로 받아와야함
        val preferences=context.getSharedPreferences(USER_AUTH,Context.MODE_PRIVATE)
        return preferences.getBoolean(AUTO_LOGIN,false)
    }

    fun setAutoLogin(context: Context,isAuto:Boolean){
        val preferences=context.getSharedPreferences(USER_AUTH,Context.MODE_PRIVATE)
        preferences.edit()
            .putBoolean(AUTO_LOGIN,isAuto)
            .apply()
    }

    fun removeAutoLogin(context: Context){
        val preferences=context.getSharedPreferences(USER_AUTH,Context.MODE_PRIVATE)
        preferences.edit()
            .remove(AUTO_LOGIN)
            .apply()
    }

    fun clearStorage(context:Context){
        val preferecnes=context.getSharedPreferences(USER_AUTH,Context.MODE_PRIVATE)
        preferecnes.edit()
            .clear()
            .apply()
    }

}
 ```
 + SignInActivity에 자동로그인 적용   
 ```kotlin
 private fun btnLoginClick(){
        binding.btnLogin.setOnClickListener {
            initNetwork()
        }
        binding.ibAutoLogin.setOnClickListener {
            binding.ibAutoLogin.isSelected=!binding.ibAutoLogin.isSelected
            SOPTSharedPreferences.setAutoLogin(this,binding.ibAutoLogin.isSelected)
        }
    }

    private fun isAutoLogin(){
        if(SOPTSharedPreferences.getAutoLogin(this)){
            shortToast("자동로그인 되었습니다")
            startActivity(Intent(this,HomeActivity::class.java))
            finish()
        }
    }
 ```   
    
+ SettingActivity에 자동로그인 해제 적용   
```kotlin
class SettingActivity : BaseActivity<ActivitySettingBinding>({ ActivitySettingBinding.inflate(it)}) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.tvAutoRemove.setOnClickListener {
            binding.tvAutoRemove.text="자동로그인이 해제됨"

            SOPTSharedPreferences.removeAutoLogin(this)
            SOPTSharedPreferences.clearStorage(this)
        }
    }
}

```   
   
   
  **✔ 1-3 Util클래스 및 패키지 방식**    
  
  <p align="center"><img img width="70%" src="https://user-images.githubusercontent.com/48755814/146558697-da0b7f29-d010-4cd0-a1d7-747a0d36c793.png"/></p> 
  
+ 세미나가 진행될수록 클래스가 많아져서 6가지 클래스로 패키징을 하였다.
+ activity : 액티비티 모아둠
+ fragments: 프래그먼트 모아둠
+ adapter: 리사이클러뷰 어댑터, 뷰페이저 어댑터 등 어댑터 모아둠
+ api: 서버작업/retrofit에 필요한 api 관련 클래스
+ data: 데이터 클래스 
+ util : 유틸 관련 클래스, function 안에는 리스너/콜백/itemdecoration 등 세부기능을 위한 클래스 , room은 룸 찍먹을 해보기 위해 만들었지만 아직 미완성   
   
   
         
           
### ⭐ LEVEL 2 과제 ⭐
       
    
**✔ 2-2 NavigationComponent에서 BackStack 관리해보기**

  + app:popUpTo-> 이 코드를 추가해주면 특정 destination(fragment) 로 pop 하도록 navigation에서 처리한다.
  + app:popUpToInclusive -> true로 설정시 app:popUpTo에 지정된 destination(fragment)이 백스택에서도 제거된다. 
     
 <p align="center"><img src="https://user-images.githubusercontent.com/48755814/146560777-147b3a77-1ec4-4522-b622-d0dfae9a5383.JPG"/></p>   
 (이해하기 쉬운 사진이 있어 가지고 와봤습니다..!)   
 
 + nav_onboaring.xml 수정   
 ```kotlin
 <fragment
        android:id="@+id/secondOnboardingFragment2"
        android:name="com.example.androidseminar.fragments.SecondOnboardingFragment"
        android:label="두번째 화면"
        tools:layout="@layout/fragment_second_onboarding" >
        <action
            android:id="@+id/action_secondOnboardingFragment2_to_thirdOnboardingFragment"
            app:destination="@id/thirdOnboardingFragment"
            app:popUpTo="@id/firstOnboardingFragment"
            app:popUpToInclusive="false"
            />
    </fragment>
 ```   
 
 


**✔ 2-2 NavigationComponent와 ToolBar연동**   
   
   1) Activity에 ToolBar 만들어주기
   2) OnboardingActivity 코드 수정하기   
   ```kotlin
   override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.container_onboarding) as NavHostFragment
        val navController=navHostFragment.findNavController()
        setSupportActionBar(binding.tbTop)
        setupActionBarWithNavController(navController)


    }
   ```
   이렇게 해주면 fragment가 바뀜에 따라 툴바의 제목도 변경된다.   
   
   

### ⭐ LEVEL 3 과제 ⭐   

   
   **✔ 3-1 Room 활용해보기**    
    <p align="center"><img src="https://user-images.githubusercontent.com/48755814/146561623-84512dea-000f-4256-8ee9-3a7b6873386d.png"/></p>   
    + Room : 스마트폰 내장 DB에 데이터를 저장하기 위해 사용하는 라이브러리   
    + SQLite의 문제점들은 자동으로 처리해준다
    + 간단한 정보를 저장하는 경우에는 sharedPreferences를 쓰는게 훨씬 편하다   
    
  -> Entity, DAO, Database는 구현완료   
  -> 코드부분은 아직 미완성
 
 
 **🔥이번 과제를 통해 성장한 내용🔥**   
    
    1) NavigationComponent의 활용법   
    2) SharedPreference의 활용법
    3) 확장함수
    4) Room 코드 완성시키고 아직 안한 retrofit call 부분 확장함수까지 구현해서 올리자!
 
 

