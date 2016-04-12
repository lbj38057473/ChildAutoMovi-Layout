package com.houzhi.childautomovi.demo.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.houzhi.childautomovi.demo.R;

import java.util.ArrayList;


/**
 * Created by houzhi on 15-5-11.
 */
public class TagAdapter extends BaseAdapter {

    public TagAdapter(){
        list.add("Gloomy");
        list.add("Happy");
        list.add("Love");
        list.add("Beauty");
        list.add("Handsome");
        list.add("Pet");
        list.add("Cars");
        list.add("Wild");

        colors.add(Color.BLUE);
        colors.add(Color.GREEN);
        colors.add(Color.YELLOW);
        colors.add(Color.RED);
        colors.add(Color.BLUE);
        colors.add(Color.GREEN);
        colors.add(Color.YELLOW);
        colors.add(Color.RED);
    }

    private ArrayList<String> list = new ArrayList<>();

    /**
     * color
     */
    private ArrayList<Integer> colors = new ArrayList<>();

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
        TextView tv = ((TextView)view.findViewById(R.id.tv_tag));
        tv.setText(list.get(position));
        tv.setTextColor(colors.get(position));
        return view;
    }


}
