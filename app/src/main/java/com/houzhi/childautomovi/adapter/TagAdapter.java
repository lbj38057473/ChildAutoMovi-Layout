package com.houzhi.childautomovi.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.houzhi.childautomovi.R;


/**
 * Created by houzhi on 15-5-11.
 */
public class TagAdapter extends BaseAdapter {

    String tmp[] = new String[]{
            "忧郁",
            "欢乐",
            "爱情",
            "美女",
            "帅哥"
    };

    @Override
    public int getCount() {
        return tmp.length;
    }

    @Override
    public Object getItem(int position) {
        return tmp[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater)parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.item_tag,null);
        ((TextView)view.findViewById(R.id.tv_tag)).setText(tmp[position]);
        return view;
    }
}
