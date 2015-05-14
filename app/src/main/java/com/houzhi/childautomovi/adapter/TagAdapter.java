package com.houzhi.childautomovi.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.houzhi.childautomovi.R;

import java.util.ArrayList;


/**
 * Created by houzhi on 15-5-11.
 */
public class TagAdapter extends BaseAdapter {

    public TagAdapter(){
        list.add("忧郁");
        list.add("欢乐");
        list.add("爱情");
        list.add("美女");
        list.add("帅哥");
    }

    ArrayList<String> list = new ArrayList<>();


    public void remove(int position){
        list.remove(position);
        notifyDataSetChanged();
    }

    public void add(String tag){
        list.add(tag);
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater)parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.item_tag,null);
        ((TextView)view.findViewById(R.id.tv_tag)).setText(list.get(position));
        return view;
    }


}
