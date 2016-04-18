package com.wmm.antman.following;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.wmm.antman.R;
import com.wmm.antman.data.Followers;

import java.util.List;


/**
 * 活动预告适配器
 */

public class FollowingAdapter extends RecyclerView.Adapter<FollowingAdapter.ViewHolder> {

    private Context mContext;
    private List<Followers> listItems;
    private OnItemClickListener onItemClickListener;

    public FollowingAdapter(Context context) {
        this.mContext = context;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = View.inflate(viewGroup.getContext(), R.layout.following_item, null);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    public void setData(List<Followers> data) {
        listItems = data;
        this.notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, final int i) {
        viewHolder.title.setText(listItems.get(i).getLogin());
        viewHolder.img.setImageURI(Uri.parse(listItems.get(i).getAvatar_url()));

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int pos = viewHolder.getLayoutPosition();
                onItemClickListener.onItemClick(viewHolder.itemView, pos-1);
            }
        });

        viewHolder.itemView.setOnLongClickListener(new View.OnLongClickListener() {

            @Override
            public boolean onLongClick(View v) {
                int pos = viewHolder.getLayoutPosition();
                onItemClickListener.onItemLongClick(viewHolder.itemView, pos-1);
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
        public SimpleDraweeView img;

        public ViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.txt_item_my_following);
            img = (SimpleDraweeView) itemView.findViewById(R.id.img_item_my_following);
        }
    }

    public interface OnItemClickListener {

        void onItemClick(View view, int position);

        void onItemLongClick(View view, int position);

    }
}
