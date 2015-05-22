#子View按规则自动移动Layout



之前看到一个屏幕上有点不断闪动的，并且移动的界面。当时久萌生一种想法，能不能做一个这样的选择界面呢？

实现起来其实比较简单，写了一下之后，感觉完全可以做一个可以很好地重用，并且方便扩展移动规则的View。趁着平时时间，就干脆实现了它，并且粗略写了几种移动规则。

##ChildAutoMoviLayout实现
###父类
考虑到平时使用起来比较方便使用的是RelativeLayout，所以用ChildAutoMoviLayout作为RelativeLayout的子类。这样用户在使用这个Layout的时候，
可以更加方便地扩展。比如使用Margins。

###添加子View方式
另外，因为每个子View可以要求为一致，因为自动移动，基本上是一些差不多形式的View移动。所以我使用Android AbsListView的Adapter作为ChildAutoMoviLayout
的Adapter。ChildAutoMoviLayout通过读取Adapter来给它自己添加子View 。ChildAutoMoviLayout不通过addView来添加View。这样用户只要实现一个
Adapter就可以了。甚至可以直接把ListView的Adapter拿来使用。

###移动规则
移动规则，我设计一个接口，用户只要实现那个接口，并且给ChildAutoMoviLayout就可以了。setMoviInterface。移动规则采用两个方法

            /**    
                 * 移动    
                 * @param view    
                 * @param height 移动高度    
                 * @param width 移动宽度    
                 */    
            void move(View view,int width,int height);    
            
            
            /**    
                 * 初始化位置    
                 * @param view    
                 * @param height 移动高度    
                 * @param width 移动宽度    
                 */    
            void init(View view,int width,int height);    
            


##使用demo
xml使用：

                <com.houzhi.childautomovi.view.ChildAutoMoviLayout    
                android:id="@+id/tagView"    
                android:layout_below="@+id/tag_container"    
                android:layout_width="match_parent"    
                android:layout_height="match_parent">    
                </com.houzhi.childautomovi.view.ChildAutoMoviLayout>    


java代码使用示例：

                //从xml中解析出
    ​    ​    ​    ​ChildAutoMoviLayout tagRandomView = (ChildAutoMoviLayout) findViewById(R.id.tagView);    
    ​    ​    ​    ​final LinearLayout linear = (LinearLayout) findViewById(R.id.tag_container);    
    ​    ​    ​    ​final TagAdapter adapter = new TagAdapter();    
    ​    ​    ​    ​tagRandomView.setAdapter(adapter, new BubbleLineUpMoving());    
    ​    ​    ​    ​
    ​    ​    ​    ​tagRandomView.setOnTagClickListener(new ChildAutoMoviLayout.TagClickListener() {    
    ​    ​    ​    ​    ​    ​@Override    
    ​    ​    ​    ​    ​    ​public void onTagClickListener(View view, int position, long id) {    
    ​    ​    ​    ​    ​    ​    ​    ​//TODO click things    
    ​    ​    ​    ​    ​    ​    ​    ​Log.d("", "tag click " + adapter.getItem(position) + " click");    
    ​    ​    ​    ​    ​    ​    ​    ​View view1 = getLayoutInflater().inflate(R.layout.layout_linear_tag, null);    
    ​    ​    ​    ​    ​    ​    ​    ​final String tag = adapter.getItem(position) + "";    
    ​    ​    ​    ​    ​    ​    ​    ​((TextView) view1.findViewById(R.id.tv_tag)).setText(tag);    
    ​    ​    ​    ​    ​    ​    ​    ​linear.addView(view1);    
    ​    ​    ​    ​    ​    ​    ​    ​
    ​    ​    ​    ​    ​    ​    ​    ​adapter.remove(position);    
    ​    ​    ​    ​    ​    ​    ​    ​
    ​    ​    ​    ​    ​    ​    ​    ​//移除处理    
    ​    ​    ​    ​    ​    ​    ​    ​view1.setOnLongClickListener(new View.OnLongClickListener() {    
    ​    ​    ​    ​    ​    ​    ​    ​
    ​    ​    ​    ​    ​    ​    ​    ​    ​    ​@Override    
    ​    ​    ​    ​    ​    ​    ​    ​    ​    ​public boolean onLongClick(View v) {    
    ​    ​    ​    ​    ​    ​    ​    ​    ​    ​linear.removeView(v);    
    ​    ​    ​    ​    ​    ​    ​    ​    ​    ​linear.invalidate();    
    ​    ​    ​    ​    ​    ​    ​    ​    ​    ​adapter.add(tag);    
    ​    ​    ​    ​    ​    ​    ​    ​    ​    ​return true;    
    ​    ​    ​    ​    ​    ​    ​    ​    ​    ​}    
    ​    ​    ​    ​    ​    ​    ​    ​});    
    ​    ​    ​    ​    ​    ​
    ​    ​    ​    ​    ​    ​}    
    ​    ​    ​    ​​});    
    ​    ​    ​    ​
    ​    ​    ​    ​tagRandomView.startMoving();    
    ​    ​    ​    ​
