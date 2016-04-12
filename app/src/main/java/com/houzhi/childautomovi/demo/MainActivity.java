package com.houzhi.childautomovi.demo;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.houzhi.childautomovi.demo.adapter.TagAdapter;
import com.houzhi.childautomovi.demo.utils.LogUtils;
import com.houzhi.childautomovi.movi.RandomForwardMoving;
import com.houzhi.childautomovi.view.ChildAutoMoviLayout;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        ChildAutoMoviLayout tagRandomView = (ChildAutoMoviLayout) findViewById(R.id.tagView);
        final LinearLayout linear = (LinearLayout) findViewById(R.id.tag_container);
        final TagAdapter adapter = new TagAdapter();
        tagRandomView.setAdapter(adapter, new RandomForwardMoving());

        tagRandomView.setOnTagClickListener(new ChildAutoMoviLayout.TagClickListener() {
            @Override
            public void onTagClickListener(View view, int position, long id) {
                //TODO click things
                LogUtils.d("", "tag click " + adapter.getItem(position) + " click");
                View view1 = getLayoutInflater().inflate(R.layout.layout_linear_tag, null);
                final String tag = adapter.getItem(position) + "";
                ((TextView) view1.findViewById(R.id.tv_tag)).setText(tag);
                linear.addView(view1);

                adapter.remove(position);



                //remove
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
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
