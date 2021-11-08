## 1️⃣ Week 3

<p align="center"><img width="35%" src="https://user-images.githubusercontent.com/48755814/139565029-3e9a9be4-540c-4a74-b309-466079bd2f09.gif"/></p>

### ⭐ LEVEL 1 필수과제 ⭐   


**1-1 과제에 디자인 적용하기**   

피그마와 제플린 이용
+ selector 
+ stated_focused 되었을 때 / 안 되었을 때 drawable 파일을 각각 만들고 selector파일 만들어서 적용
```kotlin
<?xml version="1.0" encoding="utf-8"?>
<selector xmlns:android="http://schemas.android.com/apk/res/android">
    <item android:drawable="@drawable/rectangle_border_pink" android:state_focused="true"/>
    <item android:drawable="@drawable/rectangle_fill_gray" android:state_focused="false"/>

</selector>
```
```kotlin
<EditText
        android:id="@+id/register_name_edit"
        ...
        android:background="@drawable/selector_sample_text"
```

+ Glide 이용해서 CircleCrop 기능 넣기
1)  build.gradle(app)에 라이브러리 implementation하기
2)  레이아웃에 Glide로 이미지를 띄워줄 imageView 배치
3)  인터넷에서 이미지를 불러오기위해 Manifest에 인터넷 권한 추가
4)  코드에서 Glide 이용해서 동그란 이미지 만들기
```kotlin
private fun setProfileImg(){
            Glide.with(this)
                .load("https://yt3.ggpht.com/ytc/AKedOLTBmVN3RYeIJpA6Rlmx1vloR3PGaDYR6sfoCTb4=s900-c-k-c0x00ffffff-no-rj")
                .circleCrop()
                .into(binding.profileIv)

        }
```   

+ BottomNavigation 적용하기   
1) 하단탭(BottomNavigation)에 사용할 아이콘,menu.xml 만들기
2) Activity Layout에 BottomNavigationView 추가
3) BottomNavigation과 ViewPager2 연동
```kotlin
private fun initBottomNavigation(){
        binding.homeVp.registerOnPageChangeCallback(object: ViewPager2.OnPageChangeCallback(){
            override fun onPageSelected(position: Int) {
                binding.bnvHome.menu.getItem(position).isChecked=true
            }
        })

        binding.bnvHome.setOnItemSelectedListener {
            when(it.itemId){
                R.id.menu_profile->{
                    binding.homeVp.currentItem=FIRST_FRAGMENT
                    return@setOnItemSelectedListener true
                }
                R.id.menu_home->{
                    binding.homeVp.currentItem=SECOND_FRAGMENT
                    return@setOnItemSelectedListener true
                }
                else->{
                    binding.homeVp.currentItem=THIRD_FRAGMENT
                    return@setOnItemSelectedListener true
                }
            }
        }
    }
```   
- OnPageChangeCallback : ViewPager2의 화면 전환을 감지하는 추상 클래스
- OnItemSelectedListener: BottomNavigation의 Item들이 선택 되었을 때 호출되는 리스너 (아이템 위치 바꾸기)   


+ TabLayout + ViewPager 적용하기   
1) FragmentLayout에 TabLayout추가
2) TabLayout 과 연동시킬 ViewPager2 추가
3) ViewPager2에 넣어줄 Fragment 생성
4) ViewPager2 어댑터 만들기
5) ViewPager2 와 TabLayout 연동하기
```kotlin
   private fun initTabLayout(){
        val tabLabel=listOf("팔로잉","팔로워")

        TabLayoutMediator(binding.homeTablayout,binding.homefragVp){tab,position->
            tab.text=tabLabel[position]
        }.attach()
    }
```
- TabLayoutMediator: ViewPager2와 Tablayout 연동할 때 사용하는 클래스, attach 호출할 때 여기서 기존 탭들 지우고 새로운 탭 생성   

        
           
           
### ⭐ LEVEL 2 과제 ⭐
       
    
**2-1 ViewPager2 중첩 스크롤 문제 해결하기**

  + 안드로이드 공식문서 : <https://developer.android.com/training/animation/vp2-migration#nested-scrollables/>
  +  ViewPager2 스크롤 보기가 ViewPager2가 포함된 외부 개체와 방향이 같을 경우 중첩된 스크롤 보기를 기본적으로 지원하지 않는다.
  +  내부 ViewPager의 스크롤을 지원하기 위해서는 ViewPager2 객체의 requestDisallowInterceptTouchEvent()을 호출해야한다.
  
  1) 구글에서 제공해준 NestedScrollableHost.kt를 추가해준다. (길어서 코드는 생략합니다..!)
  2) xml 레이아웃 단에서는 위에서 추가한 클래스를 레이아웃으로 사용한다.   
```kotlin
 <com.example.androidseminar.NestedScrollableHost
        android:layout_width="match_parent"
        android:layout_height="0dp"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintTop_toBottomOf="@id/home_tablayout">
    <androidx.viewpager2.widget.ViewPager2
        android:id="@+id/homefrag_vp"
        android:layout_width="match_parent"
        android:layout_height="match_parent" />

    </com.example.androidseminar.NestedScrollableHost>
```
참고) NestedScrolableHost 레이아웃은 스크롤이 가능한 요소 하나만을 "유일한 자식"으로 가져야 한다.    


**2-2 리스트에 각기 다른 이미지 넣기**   
  + 리사이클러뷰의 데이터클래스에 url 을 저장할 변수 추가
```kotlin
data class Info(
    val followerImgUrl: String,
    val followerName: String,
    val followerPart: String
)
```
  + 각각의 아이템에 이미지 url, Glide 활용해 이미지 띄우기
```kotlin
val followerData = mutableListOf(
        Info("https://mblogthumb-phinf.pstatic.net/MjAyMDExMzBfMjY1/MDAxNjA2NzAxNzIzMDkz.mXpHE4L6KPe-c9o5QYP6pml_ck7Up-OsPeMR-cRemCUg.YKL7EaPVhrpTnTB2giNhEn6pVF6SvYb-LLRvp8ulNRcg.JPEG.marxela/IMG_0511.JPG?type=w800", "김길동", "서버YB"),
        Info("https://i.pinimg.com/550x/c2/95/7d/c2957d833f37315e4dd4c51a34ce046c.jpg", "박길동", "아요YB")

    )
```



### ⭐ LEVEL 3 심화과제 ⭐  

**3-1 DataBinding 리사이클러뷰에 적용**   

1) 리사이클러뷰 아이템 뷰 xml 레이아웃을 <layout/> 태그로 감싼다. (리사이클러뷰 아이템 뷰 xml 레이아웃 루트태그 alt+enter 클릭!)
2) 리사이클러뷰에 보여줄 data variable 을 추가해준다.
3) view 와 data 를 @{} 구문을 사용하여 bind 해준다.
    ```kotlin
      fun bind(item:Info){

            with(binding){
                followerItem=item
                executePendingBindings()
            }

    ```
5) 리사이클러뷰에서 사용하는 ViewHolder 클래스의 생성자를 ItemView -> Binding 클래스 객체로 바꿔준다.
6) 이미지를 보여주기 위해선 BindingAdapter를 작성해야 한다.
  - ImageView 의 경우, src 같은 attribute 에 이미지 url을 set 해주는 방식으로 이미지를 보여주는 것이 불가능
  - BindingAdapter 는 아직 정의되지 않은 Binding Attribute 를 정의하고 그 내부 로직을 작성할 때 쓰인다.
  ```kotlin
  object BindingAdapters {

    @BindingAdapter("imageUrl")
    @JvmStatic
    fun loadImage(imageView: ImageView, url:String){
        Glide.with(imageView.context).load(url)
            .circleCrop()
            .error(R.drawable.ic_launcher_foreground)
            .into(imageView)
    }
}
  ```

 
 
 **🔥이번 과제를 통해 성장한 내용🔥**   
    
    1) 안드로이드 스튜디오에 제플린/피그마 적용하기   
    2) BottomNavigationView 와 Tablayout 사용하기   
    3) ViewPager2와 연동하기  
    4) ViewPager2 중첩 스크롤 문제 해결하기   
    5) Glide 라이브러리 사용법   
    6) 리사이클러뷰에 databinding 적용하기   
 

