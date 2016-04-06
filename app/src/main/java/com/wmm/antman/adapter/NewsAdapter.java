package com.wmm.antman.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.DraweeView;
import com.facebook.drawee.view.SimpleDraweeView;
import com.wmm.antman.R;
import com.wmm.antman.entity.News;

import java.util.List;


/**
 * 活动预告适配器
 */

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> {

    private Context mContext;
    private List<News> listItems;
    private OnItemClickListener onItemClickListener;

    public NewsAdapter(Context context) {
        this.mContext = context;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = View.inflate(viewGroup.getContext(), R.layout.item_news, null);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    public void setData(List<News> data) {
        listItems = data;
        this.notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, final int i) {
        viewHolder.title.setText(listItems.get(i).getActivityName());
        viewHolder.img.setImageURI(Uri.parse(listItems.get(i).getActivityIconUrl()));

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int pos = viewHolder.getLayoutPosition();
                onItemClickListener.onItemClick(viewHolder.itemView, pos);
            }
        });

        viewHolder.itemView.setOnLongClickListener(new View.OnLongClickListener() {

            @Override
            public boolean onLongClick(View v) {
                int pos = viewHolder.getLayoutPosition();
                onItemClickListener.onItemLongClick(viewHolder.itemView, pos);
                return false;
            }
        });
    }

    @Override
    public int getItemCount() {
        if (listItems == null) {
            return 0;
        }
        return listItems.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView title;
        public DraweeView img;

        public ViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.txt_item_news);
            img = (DraweeView) itemView.findViewById(R.id.img_item_news);

        }
    }

    public interface OnItemClickListener {

        void onItemClick(View view, int position);

        void onItemLongClick(View view, int position);

    }
}
