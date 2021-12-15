## 1️⃣ Week 4

<p align="center"><img width="35%" src="https://user-images.githubusercontent.com/48755814/141454411-f61ee992-068d-404a-95b8-39ffdea6f9be.gif"/></p>


+ Week3 심화과제 3-2도 제출했습니다! :-)   
   
   

### ⭐ LEVEL 1 필수과제 ⭐   


**로그인/회원가입 API 연동**   

1) POSTMAN 테스트   
+ 회원가입
<p align="center"><img src="https://user-images.githubusercontent.com/48755814/140775643-8ab9bfe9-32e8-4062-85a8-026a79c70455.JPG"/></p>   

   + 로그인
<p align="center"><img src="https://user-images.githubusercontent.com/48755814/140775662-aa1d149f-3e46-4649-a469-a3deac1adf21.JPG"/></p>

2) retrofit 인터페이스   
+ http메소드, 헤더, URI 등을 정의하는 부분이다. 
+ 서버에 어떠한 요청을 보내고 어떻게 오는지 상호작용 방법을 정의하는 부분이다.
+ API명세서를 살펴보면서 헤더, http메서드 등 필요한 내용을 적절히 넣어주면 된다.
```kotlin

interface SampleService {
    @Headers("Content-Type:application/json")
    @POST("user/login")
    fun postLogin(
        @Body body:RequestLoginData
    ) : Call<ResponseLoginData>

    @Headers("Content-Type:application/json")
    @POST("user/signup")
    fun postSignUp(
        @Body body:RequestSignUpData
    ) : Call<ResponseSignUpData>


}
```   

3) retrofit 구현체   
+ Retrofit Interface를 실질적으로 구현하여 생성해주는 객체
   (= 정의한 상호작용방법을 실제로 구현하는 부분)
   
```kotlin
object ServiceCreator {
    private const val BASE_URL = "https://asia-northeast3-we-sopt-29.cloudfunctions.net/api/"

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val sampleService: SampleService = retrofit.create(SampleService::class.java)
}
```   
4) Request/Response 객체 코드
+ Retrofit.Builder() : 레트로핏 빌더 생성
+ baseUrl( BASE_URL ) : 빌더 객체의 baseUrl을 호출하고 서버의 메인 URL 전달
+ addConverterFactory(GsonConverterFactory.create()): gson 컨버터 연동
+ build(): Retrofit 객체 반환
```kotlin

    private fun initNetwork(){
        val requestLoginData=RequestLoginData(
            email=binding.homeIdEdit.text.toString(),
            password=binding.homePwEdit.text.toString()
        )

        val call: Call<ResponseLoginData> = ServiceCreator.sampleService.postLogin(requestLoginData)

        call.enqueue(object: Callback<ResponseLoginData> {
            override fun onResponse(
                call: Call<ResponseLoginData>,
                response: Response<ResponseLoginData>
            ) {
                if(response.isSuccessful){
                    val data=response.body()?.data

                    Toast.makeText(this@SignInActivity,"${data?.name}님 반갑습니다!",Toast.LENGTH_LONG).show()
                    startActivity(intent1)
                }else{
                    Toast.makeText(this@SignInActivity,"로그인에 실패하셨습니다.",Toast.LENGTH_LONG).show()
                }
            }

            override fun onFailure(call: Call<ResponseLoginData>, t: Throwable) {
                Log.e("NetworkTest","error:$t")
            }

        })
    }
```




           
           
### ⭐ LEVEL 2 과제 ⭐
       
    
**✔ 2-2 OkHttp 활용해보기**

  + Okhttp 설명 : <https://square.github.io/okhttp//>
  +  Okhttp도 REST API, HTTP 통신을 간편하게 사용할 수 있도록 다양한 기능을 제공해주는 자바 라이브러리다
  +  Retrofit , Okhttp 중 하나만 사용해도 통신이 가능하고 둘다 사용하는 것도 가능하다.   
     
     
    + 왜❓❓  =레트로핏을 더 편리하게 쓰기 위해서 , 간결해짐   
    + 입력값이나 서버 응답을 로그로 쉽게 확인 가능   
    + 중복되는 헤더값들을 편하게 입력가능   
 ![image](https://user-images.githubusercontent.com/48755814/140780290-1a1e2df3-2eba-4630-a6fe-723e1e875edf.png)  
 
 
 
 "Interceptor" 라는 것이 있는데 이 아이는 무슨 역할을 하느냐..   
 >안드로이드(클라이언트)와 서버 간에 Retrofit2를 사용하여 통신을 하는데 안드로이드 클라이언트단 쪽에서 인터셉터를 추가로 사용하면 안드로이드에서 서버에게 데이터 전송 및 수신받을때 인터셉터 말 그대로 중간에 매개체가 되어 어떠한 처리를 해줄 수 있다.
 

   
   <사용법> 
  
  1) Manifest 및 build. gradle(app) 추가
  ```kotlin
  <uses-permission android:name="android.permission.INTERNET"/> //이건 manifest
  
  
  implementation 'com.squareup.okhttp3:okhttp:4.9.2' //여기부턴 gradle
  implementation 'com.squareup.okhttp3:logging-interceptor:4.9.2'
  ```
 
  3) (Retrofit 코드가 작성되었다고 가정) retrofit 구현체 부분 수정   
  ```kotlin
  object ServiceCreator {
    private const val BASE_URL = "https://asia-northeast3-we-sopt-29.cloudfunctions.net/api/"

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(provideOkHttpClient(AppInterceptor())) //추가3
        .addConverterFactory(GsonConverterFactory.create())
        .build()


    private fun provideOkHttpClient(interceptor: AppInterceptor): OkHttpClient //추가2
    = OkHttpClient.Builder()
        .run {
            addInterceptor(interceptor).build()
        }


    class AppInterceptor : Interceptor { //추가1
        override fun intercept(chain: Interceptor.Chain): Response = with(chain) {
            val newRequest = request().newBuilder()
                .addHeader("Content-Type", "application/json")
                .build()

            proceed(newRequest)
        }
    }


    val sampleService: SampleService = retrofit.create(SampleService::class.java)
}
  
  ```

- 추가1: Interceptor 클래스를 만들어준다. intercept라는 함수를 만들고 어떤 동작을 할지 정의해주면 된다.   
        나는 중복된 헤더부분을 없앨거라서 .addHeader("키값","value값")을 넣고 build를 해주었다.
- 추가2: OkHttpClient를 반환값으로 하는 함수를 만든다. 빌더 안에서 좀 전에 만든 AppInterceptor를 인자로 넣어 빌드한다.
- 추가3: 레트로핏을 빌드할때 인터셉트를 클라이언트에 추가해(= .client(provideOkHttpClient(AppInterceptor())) ) 적용한다.
- 여기까지 다 했으면 인터페이스에 공통으로 들어있는 헤더들을 다 지워도 된다^___^


**✔ 2-3 Wrapper 클래스**
- Response data class를 보면 겹치는 부분들이 있다.
- val status:Int, success : Boolean, message: String , data : 어떤 데이터클래스
- 이걸 계속 반복해서 쓰기 귀찮으니까 wrapper클래스로 만들면 편하다

```kotlin
data class ResponseWrapper<T>(
    val status: Int,
    val success: Boolean,
    val message: String,
    val data: T?
)
```
- 이렇게 wrapper 데이터 클래스를 만들어주고 기존 Response~Data 에서는 해당 부분을 지워준다.   
- 레트로핏 인터페이스에서 콜할때 Call<Response..Data> 였던걸 Call<ResponseWrapper<Response..Data>> 로 바꿔주면 된다.   

그럼 이런 형태와 같아지는 것!
```kotlin
data class ResponseSignUpData(
   val status: Int,
    val success: Boolean,
    val message: String,
    val data:Data
    ){
    data class Data(
    val id: Int,
    val name: String,
    val password: String,
    val email:String
)}
```





 
 
 **🔥이번 과제를 통해 성장한 내용🔥**   
    
    1) Postman을 통해 서버통신 확인해보는 법   
    2) http 통신에 활용되는 retrofit 사용법
    3) OkHttp가 뭔지, retrofit과 왜 같이 쓰이는지, 사용법
    4) data class에서 반복되는 부분은 Wrapper<T> 클래스로 묶어서 사용하면 편하다!
 
 

