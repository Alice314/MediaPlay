package com.wusui.mediaplay.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.wusui.mediaplay.R;
import com.wusui.mediaplay.Utils.ImageLoader;
import com.wusui.mediaplay.model.Song;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fg on 2016/5/14.
 */
public class MusicAdapter extends RecyclerView.Adapter<MusicAdapter.MyViewHolder> {

    private  List<Song> mSongs = new ArrayList<>();
    private ImageLoader mImageLoader;

    public MusicAdapter(Context context, List<Song>songs,RecyclerView recyclerView){//},List<String>mSongName, List<String>mSingerName){

        mSongs = songs;
        this.mImageLoader = new ImageLoader(context,recyclerView);

    }
    @Override
    public MusicAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_image,parent,false));
    }

    @Override
    public void onBindViewHolder(final MusicAdapter.MyViewHolder holder, int position) {
        holder.mImageView.setImageResource(R.mipmap.ic_launcher);
        if (mSongs.get(position).getSingername() != null) {
            holder.mSingerText.setText(mSongs.get(position).getSingername());
        }
        if (mSongs.get(position).getSongname() != null){
            holder.mSongText.setText(mSongs.get(position).getSongname());
        }
        if (mSongs.get(position).getAlbumpic_small() != null){
            mImageLoader.displayImage(mSongs.get(position).getAlbumpic_small(),holder.mImageView);
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int pos = holder.getLayoutPosition();
                mOnItemClickListener.onItemClick(holder.itemView,pos);
            }
        });

    }

    @Override
    public int getItemCount() {
        return mSongs.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        public ImageView mImageView;
        TextView mSongText;
        TextView mSingerText;

        public MyViewHolder(View itemView) {
            super(itemView);
            mImageView = (ImageView)itemView.findViewById(R.id.small_image);
            mSongText = (TextView) itemView.findViewById(R.id.singerName);
            mSingerText = (TextView) itemView.findViewById(R.id.songName);
        }
    }

    public interface OnItemClickListener{
        void onItemClick(View view,int position);

    }
    private OnItemClickListener mOnItemClickListener;
    public void setmOnItemClickListener(OnItemClickListener mOnItemClickListener){
        this.mOnItemClickListener = mOnItemClickListener;
    }

}
