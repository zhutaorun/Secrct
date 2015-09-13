package com.tangko.secret.atys;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.tangko.secret.R;
import com.tangko.secret.net.Comment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2015/7/21.
 */
public class AtyMessageCommentListAdapter extends BaseAdapter {

    public AtyMessageCommentListAdapter(Context context){
        this.context = context;
    }

    @Override
    public int getCount() {
        return comments.size();
    }

    @Override
    public Comment getItem(int position) {
        return comments.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public void addAll(List<Comment> data){
        comments.addAll(data);
        notifyDataSetChanged();
    }

    public void clear(){
        comments.clear();
        notifyDataSetChanged();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView==null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.aty_timeline_list_cell,null);
            convertView.setTag(new ListCell((TextView) convertView.findViewById(R.id.tvCellLabel)));
        }

        ListCell lc = (ListCell) convertView.getTag();
       Comment comment = getItem(position);
        lc.getTvCellLabel().setText(comment.getContent());
        return convertView;
    }

    private List<Comment> comments = new ArrayList<Comment>();
    private Context context;

    public Context getContext() {
        return context;
    }

    private static class ListCell{

        public ListCell(TextView tvCellLable){
            this.tvCellLable = tvCellLable;

        }
        private TextView tvCellLable;

        public TextView getTvCellLabel(){
            return tvCellLable;
        }

    }
}
