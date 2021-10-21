# Android-Hyunji
![github_윤현지_ver1-21](https://user-images.githubusercontent.com/70698151/135754394-b330e710-a771-440d-8b38-f3ba5a62545b.png)


## 1️⃣ Week 2

<img width="35%" src="https://user-images.githubusercontent.com/48755814/138306877-00cd2f7e-851d-41b3-8b6a-d71013b14ea9.gif"/>

### <LEVEL 1 필수과제>   

**1-1 FollowerRecyclerView, RepositoryRecyclerView 만들기**



 
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


**1-2 둘 중 하나의 RecyclerView는 GridLayout으로 만들기**

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
   
**2-3 RecyclerView Item 이동/삭제 구현**

  + 아이템을 길게 누르면 위치를 바꿀 수 있다.
  + 옆으로 슬라이드 하면 아이템이 삭제된다.   

<구현방법>
1) ItemDragListener(드래그 액션 시작할때 itemTouchHelper에 이벤트 전달) 인터페이스를 만든다.
2) ItemActionListener(아이템이 Drag & Drop 됐거나 Swiped 됐을 때 어댑터에 이벤트를 전달) 인터페이스를 만든다.
3) RecyclerViewAdapter에 인터페이스를 구현하고 멤버함수를 재정의한다.
4) 어댑터 생성자의 파라미터로 받은 ItemDragListener은 뷰홀더에서 사용된다. 드래그 핸들  뷰에 터치리스너를 달아준다.
5) ItemTouchHelper.Callback을 상속받는 ItemTouchHelperCallback 클래스를 구현한다.
6) Fragment에 ItemTouchHelper를 생성하고 리사이클러뷰에 붙여준다.

Interface
```kotlin
interface ItemActionListener {
    fun onItemMoved(from: Int, to: Int)
    fun onItemSwiped(position: Int)
}

interface ItemDragListener {
    fun onStartDrag(viewHolder: RecyclerView.ViewHolder)
}
```   

FollowerRecyclerAdapter.kt
```kotlin

//인터페이스 함수 재정의
override fun onItemMoved(from: Int, to: Int) {
        if (from == to) {
            return
        }

        val fromItem = infoList.removeAt(from)
        infoList.add(to, fromItem)
    }

    override fun onItemSwiped(position: Int) {
        infoList.removeAt(position)
    }
    
    //뷰홀더 내부 -> 드래그버튼에 setOnTouchListener 
    init {

            binding.dragHandle.setOnTouchListener { v, event ->
                if (event.action == MotionEvent.ACTION_DOWN) {
                    listener.onStartDrag(this)
                }
                false
            }
        }
```   

ItemTouchHelperCallback.kt
```kotlin
class ItemTouchHelperCallback(val listener: ItemActionListener) : ItemTouchHelper.Callback() {
    override fun getMovementFlags(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder
    ): Int {
        val dragFlags = ItemTouchHelper.DOWN or ItemTouchHelper.UP
        val swipeFlags = ItemTouchHelper.START or ItemTouchHelper.END
        return makeMovementFlags(dragFlags, swipeFlags)
    }

    override fun onMove(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder
    ): Boolean {
        listener.onItemMoved(viewHolder!!.adapterPosition, target!!.adapterPosition)
        return true
    }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
        listener.onItemSwiped(viewHolder!!.adapterPosition)
    }

    override fun isLongPressDragEnabled(): Boolean = false

}
```

FollowerFragment.kt   
```kotlin
itemTouchHelper = ItemTouchHelper(ItemTouchHelperCallback(adapter))
itemTouchHelper.attachToRecyclerView(binding.followerRecyclerview)
```

### <LEVEL 3 심화과제>   

**3-2 효율적으로 RecyclerViewItem 갱신하기**   

+ notifyDataSetChanged()의 문제점  
 : notifyDataSetChanged()메소드를 호출하게 되면 화면에 보이는 모든 리스트의 Item에 대하여 변경하게된다. 리스트 크기는 동일한데 아이템만 바뀌는 경우나 순서만 살짝 바뀌는 경우 등에도 리스트를 전부 새로 그리므로 비효율적이다.   
 
 + 나의 해결 방식   
 : NotifyDataSetChanged()대신 notifyItemRemoved(특정한 아이템 1개를 삭제할때 사용하는 메서드)와 notifyItemMoved(아이테이 이동했을 때 리사이클러뷰에 변경사항 반영) 함수를 써서 변경해주었다.   
 이 방법 외에도 적용할 수 있는 방법들이 있는 것 같다. 과제 이후에 찾아보고 정리할 예정이다.   
 
 
 **🔥이번 과제를 통해 성장한 내용🔥**   
 1) RecyclerView와 Fragment를 만들고 버튼으로 연결하기
 2) RecyclerView에서 LinearLayout과 GridLayout 사용법
 3) Fragment-Activity간 이동(Intent사용)
 4) ItemDecorationdm으로 리스트 꾸미기
 5) ItemTouchHelper.Callback을 이용한 아이템 삭제와 이동방법
 6) notifyDataSetChanged()의 
 
 
 
 

