#ChildAutoMoviLayout
------
There is a layout whoes child view can move by some rules you define.

![Alt Text](https://raw.githubusercontent.com/xxxzhi/ChildAutoMovi-Layout/master/movi.gif)

Usage
------

##Gradle

    compile 'com.houzhi:childautomovi:1.0.0'
    


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

6. Implement MovingInterface

    ​        ​public class SelfDefineMoving implements ViewMovingInterface


​Intro
------
I have implement random-forward moving, bubble moving.


Compatibility
------
Android 2.2 (API level 8)

## License

    Copyright 2015, houzhi

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
