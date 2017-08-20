package com.lucaslz.photogallery;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class PhotoGalleryFragment extends Fragment {

    private RecyclerView mPhotoRecyclerView;

    public static PhotoGalleryFragment newInstance() {

        Bundle args = new Bundle();

        PhotoGalleryFragment fragment = new PhotoGalleryFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View containerView = inflater.inflate(R.layout.fragment_photo_gallery, container, false);
        mPhotoRecyclerView = containerView.findViewById(R.id.photo_recycler_view);
        setUpPhotoRecyclerView();
        return containerView;
    }

    private void setUpPhotoRecyclerView() {
        mPhotoRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 3));
        mPhotoRecyclerView.setAdapter(new PhotoAdapter());

    }

    class PhotoAdapter extends RecyclerView.Adapter<PhotoHolder> {

        @Override
        public PhotoHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return null;
        }

        @Override
        public void onBindViewHolder(PhotoHolder holder, int position) {

        }

        @Override
        public int getItemCount() {
            return 0;
        }
    }

    class PhotoHolder extends RecyclerView.ViewHolder {

        public PhotoHolder(View itemView) {
            super(itemView);
        }
    }
}
