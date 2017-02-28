package com.veryworks.android.soundplayer;

import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> {

    private List<?> datas;
    private String flag;
    private int item_layout_id;

    public ListAdapter(List<?> datas, String flag) {
        this.datas = datas;
        this.flag = flag;
        switch(flag){
            case ListFragment.TYPE_SONG:
                item_layout_id = R.layout.list_fragment_item;
                break;
            case ListFragment.TYPE_ARTIST:
                item_layout_id = R.layout.list_fragment_item;
                break;
        }

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(item_layout_id, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.mIdView.setText(mValues.get(position).id);
        holder.mContentView.setText(mValues.get(position).content);

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public int id;
        public int artist_id;
        public String title;
        public String artist;
        public String artist_key;
        public int album_id;

        public Uri music_uri;
        public Uri album_image_uri;

        public ViewHolder(View view) {
            super(view);

            mView = view;
            mIdView = (TextView) view.findViewById(R.id.id);
            mContentView = (TextView) view.findViewById(R.id.content);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }
    }
}
