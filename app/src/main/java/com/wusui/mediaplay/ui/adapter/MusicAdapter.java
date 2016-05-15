package com.wusui.mediaplay.ui.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.wusui.mediaplay.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fg on 2016/5/14.
 */
public class MusicAdapter extends RecyclerView.Adapter<MusicAdapter.MyViewHolder> {
    private Context mContext;
   // private List<String>mSongName;
    //private List<String>mSingerName;
    private List<Bitmap> mSmallBitmaps = new ArrayList<>();

    public MusicAdapter(Context context, List<Bitmap>smallbitmaps){//},List<String>mSongName, List<String>mSingerName){
        mContext = context;
        mSmallBitmaps = smallbitmaps;
      //  this.mSongName = mSongName;
        //this.mSingerName = mSingerName;
    }
    @Override
    public MusicAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_image,parent,false));
    }

    @Override
    public void onBindViewHolder(final MusicAdapter.MyViewHolder holder, int position) {

        if (mSmallBitmaps.get(position) != null) {
            holder.mImageView.setImageBitmap(mSmallBitmaps.get(position));
        } else {

            holder.mImageView.setImageResource(R.mipmap.ic_launcher);
        }
        /*if (mSingerName.get(position)!=null && mSongName != null) {

            for (int j = 0;j < mSongName.size();j++){
                String song;
                song = mSongName.get(j);
                holder.mSongText.setText(song);
            }
            for (int k = 0;k < mSingerName.size();k++){
                String singer;
                singer = mSingerName.get(k);
                holder.mSingerText.setText(singer);
            }

        } else {*/
            holder.mSongText.setText("未知歌名");
            holder.mSingerText.setText("未知歌手");

        /*if (mOnItemClickListener != null){
            holder.mImageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = holder.getLayoutPosition();
                    mOnItemClickListener.onItemClick(holder.mImageView,pos);
                }
            });
        }*/
    }

    @Override
    public int getItemCount() {
        return mSmallBitmaps.size();
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
