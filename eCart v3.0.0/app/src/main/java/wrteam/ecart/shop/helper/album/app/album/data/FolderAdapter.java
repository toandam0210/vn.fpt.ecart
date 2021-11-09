/*
 * Copyright 2016 Yan Zhenjie.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package wrteam.ecart.shop.helper.album.app.album.data;

import android.content.Context;
import android.content.res.ColorStateList;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.widget.AppCompatRadioButton;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import wrteam.ecart.shop.R;
import wrteam.ecart.shop.helper.album.Album;
import wrteam.ecart.shop.helper.album.AlbumFile;
import wrteam.ecart.shop.helper.album.AlbumFolder;
import wrteam.ecart.shop.helper.album.impl.OnItemClickListener;

class FolderAdapter extends RecyclerView.Adapter<FolderAdapter.FolderViewHolder> {

    private final LayoutInflater mInflater;
    private final List<AlbumFolder> mAlbumFolders;
    private final ColorStateList mSelector;

    private OnItemClickListener mItemClickListener;

    public FolderAdapter(Context context, List<AlbumFolder> mAlbumFolders, ColorStateList buttonTint) {
        this.mInflater = LayoutInflater.from(context);
        this.mSelector = buttonTint;
        this.mAlbumFolders = mAlbumFolders;
    }

    public void setItemClickListener(OnItemClickListener itemClickListener) {
        this.mItemClickListener = itemClickListener;
    }

    @Override
    public FolderViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new FolderViewHolder(mInflater.inflate(R.layout.album_item_dialog_folder, parent, false),
                mSelector,
                new OnItemClickListener() {

                    private int oldPosition = 0;

                    @Override
                    public void onItemClick(View view, int position) {
                        if (mItemClickListener != null)
                            mItemClickListener.onItemClick(view, position);

                        AlbumFolder albumFolder = mAlbumFolders.get(position);
                        if (!albumFolder.isChecked()) {
                            albumFolder.setChecked(true);
                            mAlbumFolders.get(oldPosition).setChecked(false);
                            notifyItemChanged(oldPosition);
                            notifyItemChanged(position);
                            oldPosition = position;
                        }
                    }
                });
    }

    @Override
    public void onBindViewHolder(FolderViewHolder holder, int position) {
        final int newPosition = holder.getAdapterPosition();
        holder.setData(mAlbumFolders.get(newPosition));
    }

    @Override
    public int getItemCount() {
        return mAlbumFolders == null ? 0 : mAlbumFolders.size();
    }

    static class FolderViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private final OnItemClickListener mItemClickListener;

        private final ImageView mIvImage;
        private final TextView mTvTitle;
        private final AppCompatRadioButton mCheckBox;

        private FolderViewHolder(View itemView, ColorStateList selector, OnItemClickListener itemClickListener) {
            super(itemView);

            this.mItemClickListener = itemClickListener;

            mIvImage = itemView.findViewById(R.id.iv_gallery_preview_image);
            mTvTitle = itemView.findViewById(R.id.tv_gallery_preview_title);
            mCheckBox = itemView.findViewById(R.id.rb_gallery_preview_check);

            itemView.setOnClickListener(this);

            mCheckBox.setSupportButtonTintList(selector);
        }

        public void setData(AlbumFolder albumFolder) {
            List<AlbumFile> albumFiles = albumFolder.getAlbumFiles();
            mTvTitle.setText("(" + albumFiles.size() + ") " + albumFolder.getName());
            mCheckBox.setChecked(albumFolder.isChecked());

            Album.getAlbumConfig().getAlbumLoader().load(mIvImage, albumFiles.get(0));
        }

        @Override
        public void onClick(View v) {
            if (mItemClickListener != null)
                mItemClickListener.onItemClick(v, getAdapterPosition());
        }
    }

}