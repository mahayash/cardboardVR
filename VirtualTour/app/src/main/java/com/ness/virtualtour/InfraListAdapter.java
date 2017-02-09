package com.ness.virtualtour;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;


public class InfraListAdapter extends BaseAdapter {

    private Context context;
    private List<InfraDO> infraDOList;

    public InfraListAdapter(Context context, List<InfraDO> infraDOList) {
        this.context = context;
        this.infraDOList = infraDOList;
    }

    @Override
    public int getCount() {
        return infraDOList.size();
    }

    @Override
    public Object getItem(int i) {
        return infraDOList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        LayoutInflater inflater;
        ViewHolder viewHolder;
        if (view == null) {

            view = LayoutInflater.from(context).inflate(R.layout.infra_list_item_row, viewGroup, false);
            viewHolder = new ViewHolder();
            viewHolder.txtText = (TextView) view.findViewById(R.id.txt_title);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }

        InfraDO infraDO = infraDOList.get(i);
        viewHolder.txtText.setText(infraDO.getText());

        return view;
    }

    private class ViewHolder {

        TextView txtText;

    }
}
