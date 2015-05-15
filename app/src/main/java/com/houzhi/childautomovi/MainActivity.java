package com.houzhi.childautomovi;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.houzhi.childautomovi.adapter.TagAdapter;
import com.houzhi.childautomovi.view.RandomForwardMoving;
import com.houzhi.childautomovi.view.TagRandomView;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.houzhi.childautomovi.R.layout.activity_main);


        TagRandomView tag = (TagRandomView) findViewById(R.id.tagView);
        final LinearLayout linear = (LinearLayout) findViewById(R.id.tag_container);
        final TagAdapter adapter = new TagAdapter();
        tag.setAdapter(adapter,new RandomForwardMoving());

        tag.setOnTagClickListener(new TagRandomView.TagClickListener() {
            @Override
            public void onTagClickListener(View view, int position, long id) {
                //TODO click things
                Log.d("", "tag click " + adapter.getItem(position) + " click");
                View view1 = getLayoutInflater().inflate(R.layout.layout_linear_tag,null);
                ((TextView)view1.findViewById(R.id.tv_tag)).setText(adapter.getItem(position)+"");
                linear.addView(view1);

                adapter.remove(position);
            }
        });

        tag.startMoving();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(com.houzhi.childautomovi.R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == com.houzhi.childautomovi.R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
