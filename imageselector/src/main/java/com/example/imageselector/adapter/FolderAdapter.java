package com.example.imageselector.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.imageselector.R;
import com.example.imageselector.entry.Folder;
import com.example.imageselector.entry.Image;
import com.example.imageselector.utils.VersionUtils;

import java.util.ArrayList;

public class FolderAdapter extends RecyclerView.Adapter<FolderAdapter.ViewHolder> {

    private Context           mContext;
    private ArrayList<Folder> mFolders;
    private LayoutInflater    mInflater;
    private int                    mSelectItem;
    private OnFolderSelectListener mListener;
    private boolean                isAndroidQ = VersionUtils.isAndroidQ();

    public FolderAdapter(Context context, ArrayList<Folder> folders) {
        mContext = context;
        mFolders = folders;
        this.mInflater = LayoutInflater.from(context);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        android.view.View view = mInflater.inflate(R.layout.adapter_folder, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        final Folder     folder = mFolders.get(position);
        ArrayList<Image> images = folder.getImages();
        holder.tvFolderName.setText(folder.getName());
        holder.ivSelect.setVisibility(mSelectItem == position ? android.view.View.VISIBLE : android.view.View.GONE);
        if (images != null && !images.isEmpty()) {
            holder.tvFolderSize.setText(mContext.getString(R.string.selector_image_num, images.size()));
            Glide.with(mContext).load(isAndroidQ ? images.get(0).getUri() : images.get(0).getPath())
                    .into(holder.ivImage);
        } else {
            holder.tvFolderSize.setText(mContext.getString(R.string.selector_image_num, 0));
            holder.ivImage.setImageBitmap(null);
        }

        holder.itemView.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(android.view.View v) {
                mSelectItem = holder.getAdapterPosition();
                notifyDataSetChanged();
                if (mListener != null) {
                    mListener.OnFolderSelect(folder);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mFolders == null ? 0 : mFolders.size();
    }

    public void setOnFolderSelectListener(OnFolderSelectListener listener) {
        this.mListener = listener;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        ImageView               ivImage;
        ImageView               ivSelect;
        android.widget.TextView tvFolderName;
        android.widget.TextView tvFolderSize;

        public ViewHolder(android.view.View itemView) {
            super(itemView);
            ivImage = itemView.findViewById(R.id.iv_image);
            ivSelect = itemView.findViewById(R.id.iv_select);
            tvFolderName = itemView.findViewById(R.id.tv_folder_name);
            tvFolderSize = itemView.findViewById(R.id.tv_folder_size);
        }
    }

    public interface OnFolderSelectListener {
        void OnFolderSelect(Folder folder);
    }

}
