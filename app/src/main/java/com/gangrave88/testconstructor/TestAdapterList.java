package com.gangrave88.testconstructor;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.TextView;

import io.realm.OrderedRealmCollection;
import io.realm.RealmBaseAdapter;

public class TestAdapterList extends RealmBaseAdapter<Test> implements ListAdapter {

    private static class ViewHolder{
        TextView test;
        TextView difficult;
    }

    public TestAdapterList(@NonNull Context context, @Nullable OrderedRealmCollection<Test> data) {
        super(context, data);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView==null){
            convertView = LayoutInflater.from(parent.getContext()).
                    inflate(R.layout.simple_list_item,parent,false);
            viewHolder = new ViewHolder();
            viewHolder.test = (TextView)convertView.findViewById(R.id.simple_list_item1);
            viewHolder.difficult = (TextView)convertView.findViewById(R.id.difficult);
            convertView.setTag(viewHolder);
        }
        else {
            viewHolder = (ViewHolder)convertView.getTag();
        }

        Test test = adapterData.get(position);
        viewHolder.test.setText(test.getName());
        viewHolder.difficult.setText(String.valueOf(test.getDifficult()));

        return convertView;
    }
}
