#ChildAutoMoviLayout
------
There is a layout whoes child view can move by some rules you define.

![Alt Text](https://raw.githubusercontent.com/xxxzhi/ChildAutoMovi-Layout/master/movi.gif)

Usage
------
1.  insert ChildAutoMoviLayout like this:

    <com.houzhi.childautomovi.view.ChildAutoMoviLayout    
      android:id="@+id/tagView"    
      android:layout_below="@+id/tag_container"    
      android:layout_width="match_parent"    
     android:layout_height="match_parent">    
    </com.houzhi.childautomovi.view.ChildAutoMoviLayout>    

2. call in java code

            ChildAutoMoviLayout tagRandomView = (ChildAutoMoviLayout) findViewById(R.id.tagView);    
            final LinearLayout linear = (LinearLayout) findViewById(R.id.tag_container);    
            

3. Using adapter

    I have created a example adapter in the code, you can using like this or you create a new Adapter for youself:
            
            final TagAdapter adapter = new TagAdapter();    
            tagRandomView.setAdapter(adapter, new BubbleLineUpMoving());    

    Setting adapter and moving rules.

            tagRandomView.setAdapter(adapter, new BubbleLineUpMoving());


4. TagClikListener

            tagRandomView.setOnTagClickListener(new ChildAutoMoviLayout.TagClickListener() {    
                        @Override
                        public void onTagClickListener(View view, int position, long id) {
            		//TODO click things
  
            	}
            });

5. Start Moving

            tagRandomView.startMoving();  


Compatibility
------
Android 2.2 (API level 8)



#ChildAutoMoviLayout


之前看到一个屏幕上有点不断闪动的，并且移动的界面。当时久萌生一种想法，能不能做一个这样的选择界面呢？

实现起来其实比较简单，写了一下之后，感觉完全可以做一个可以很好地重用，并且方便扩展移动规则的View。趁着平时时间，就干脆实现了它，并且粗略写了几种移动规则。

![Alt Text](https://raw.githubusercontent.com/xxxzhi/ChildAutoMovi-Layout/master/movi.gif)

ChildAutoMoviLayout是一个让子视图按照自定义的方式在Layout移动的Layout。适合用于在标签，类别选择的时候，需要增加动态效果情况。

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
            ChildAutoMoviLayout tagRandomView = (ChildAutoMoviLayout) findViewById(R.id.tagView);    
            final LinearLayout linear = (LinearLayout) findViewById(R.id.tag_container);    
            final TagAdapter adapter = new TagAdapter();    
            tagRandomView.setAdapter(adapter, new BubbleLineUpMoving());    
            
            tagRandomView.setOnTagClickListener(new ChildAutoMoviLayout.TagClickListener() {    
                        @Override
                        public void onTagClickListener(View view, int position, long id) {
            		//TODO click things
            		Log.d("", "tag click " + adapter.getItem(position) + " click");
            		View view1 = getLayoutInflater().inflate(R.layout.layout_linear_tag, null);
            		final String tag = adapter.getItem(position) + "";
            		((TextView) view1.findViewById(R.id.tv_tag)).setText(tag);
            		linear.addView(view1);
            
            		adapter.remove(position);
            		//remove from adapter
            		view1.setOnLongClickListener(new View.OnLongClickListener() {
            	 		@Override
            			public boolean onLongClick(View v) {
            				linear.removeView(v);
            				linear.invalidate();
            				adapter.add(tag);
            				return true;
            			}
            		});
            	}
            });
            tagRandomView.startMoving();    

你也可以扩展ViewMovingInterface接口，自定义移动方式，源码中提供了5种移动方式，冒泡，抖动，随机移动

## License

    Copyright 2015, Yalantis

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
