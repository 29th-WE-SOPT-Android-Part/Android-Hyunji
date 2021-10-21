# Android-Hyunji
![github_윤현지_ver1-21](https://user-images.githubusercontent.com/70698151/135754394-b330e710-a771-440d-8b38-f3ba5a62545b.png)


## 1️⃣ Week 2

### <LEVEL 1 필수과제>

**1-1 FollowerRecyclerView, RepositoryRecyclerView 만들기 **

<img width="35%" src="https://user-images.githubusercontent.com/48755814/136640122-c77fd162-9045-4693-b8bb-fbe438af34d8.gif"/>

 
  1) activity xml에 recyclerview 추가   
  2) RecyclerView 안 각 아이템 배치할 레이아웃 만들기
  3) 아이템에 들어갈 data class 만들기
  4) ViewHolder 와 RecyclerViewAdapter 만들기
  5) Activity나 Fragment에 연결해주기 (어댑터 달아주기)   

+ RecyclerView의 어댑터 

```kotlin
class RepositoryRecyclerViewAdapter : RecyclerView.Adapter<RepositoryRecyclerViewAdapter.MyViewHolder>() {

    var repoList=mutableListOf<RepoInfo>()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RepositoryRecyclerViewAdapter.MyViewHolder {
        val binding=ItemRepositoryBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RepositoryRecyclerViewAdapter.MyViewHolder, position: Int) {
        holder.bind(repoList[position])
    }

    override fun getItemCount(): Int =repoList.size

    inner class MyViewHolder(private val binding: ItemRepositoryBinding):RecyclerView.ViewHolder(binding.root){

        fun bind(repoinfo:RepoInfo){
            binding.repositoryNameTv.text=repoinfo.repo_name
            binding.repositoryExplainTv.text=repoinfo.repo_explain
        }
    }


}
```

  
+ 버튼 클릭 시 fragment 전환
```kotlin
supportFragmentManager.beginTransaction().add(R.id.container_home,followerFragment).commit()


        binding.homeFollowerBtn.setOnClickListener {
            supportFragmentManager.beginTransaction().replace(R.id.container_home,followerFragment).commit()
        }

        binding.homeRepositoryBtn.setOnClickListener {
            supportFragmentManager.beginTransaction().replace(R.id.container_home,repositoryFragment).commit()
        }

```
  
  + ellipsize 속성 (종류)   
  1) end: 끝 말줄임
  2) marquee: 흐르게
  3) middle: 중간 말줄임
  4) none
  5) start: 중간 말줄임
 + maxLines 옵션과 함께 사용해서 한줄로만 표시
 ```kotlin
 <TextView
        android:id="@+id/repository_explain_tv"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:maxLines="1"
        android:ellipsize="end"
        android:text="안드로이드YB의 레포지토리입니다"
        android:textSize="16sp"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintStart_toStartOf="@id/repository_name_tv"
        app:layout_constraintTop_toBottomOf="@id/repository_name_tv" />
  ```   


**1-2 둘 중 하나의 RecyclerView는 Grid Layout으로 만들기**

  + GridLayoutManager 사용하기
  + 인자로 (context, spanCount) 넣어줘야 한다.
  
```kotlin
private fun initRepoRecyclerView(){
        adapter= RepositoryRecyclerViewAdapter()
        adapter.repoList=repoData
        binding.repositoryRecyclerview.adapter=adapter
        val gridLayoutManager=GridLayoutManager(requireContext(),2)
        binding.repositoryRecyclerview.layoutManager=gridLayoutManager
        

    }
  ```   
     
### <LEVEL 2 도전과제>
       
    
**2-1 아이템 클릭시 상세페이지로 이동**

  + DetailActivity 를 만든다.
  + putExtra, get타입Extra를 통해 데이터 넘기고 받는다.
  
FollowerFragment.kt

```kotlin
adapter.setOnItemClickListener(object:FollowerRecyclerViewAdapter.OnItemClickListener{
            override fun onItemClick(v: View, info: Info, pos: Int) {
                Intent(v.context,DetailActivity::class.java)
                    .putExtra("name",info.follower_name)
                    .putExtra("picture",info.follower_img)
                    .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                    .run{startActivity(this)}
            }
        })
```   

DetailActivity.kt   
```kotlin
  val name=intent.getStringExtra("name")
        val picture=intent.getIntExtra("picture",0)

        binding.detailNameTv.setText(name)
        binding.detailProfileIv.setImageResource(picture)
```   




**2-2 ItemDecoration 활용해서 리스트 간격과 구분선 주기**   
  + HorizontalItemDecorator 클래스를 만든다. (Item 간격을 조정해주는 클래스)   
  + RecyclerView에 ItemDecorator을 적용한다.

HorizontalItemDecorator.kt
```kotlin
 class HorizontalItemDecoration( private val height: Float, private val padding: Float, private val divHeight:Int, @ColorInt private val color: Int ) : RecyclerView.ItemDecoration() {
    private val paint = Paint()
    init { paint.color = color }

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)
        outRect.top=divHeight
        outRect.bottom=divHeight
    }

    override fun onDrawOver(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        val left = parent.paddingStart + padding
        val right = parent.width - parent.paddingEnd - padding
        for (i in 0 until parent.childCount) {
            val child = parent.getChildAt(i)
            val params = child.layoutParams as RecyclerView.LayoutParams
            val top = (child.bottom + params.bottomMargin).toFloat()
            val bottom = top + height
            c.drawRect(left, top, right, bottom, paint)
        }
    }
}

  ```   
  
  FollowerFragment.kt  
  ```kotlin
          binding.followerRecyclerview.addItemDecoration(HorizontalItemDecoration(10f,10f,20, ContextCompat.getColor(requireContext(),R.color.hot_pink)))

  ```
              
