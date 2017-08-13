package com.lucaslz.beatbox;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lucaslz.beatbox.databinding.FragmentBeatBoxBinding;
import com.lucaslz.beatbox.databinding.ItemListSoundBinding;

import java.util.List;

/**
 * Created by lucas lee <llzqingdao2012gmail.com> on 2017/8/13.
 */
public class BeatBoxFragment extends Fragment {

    private BeatBox mBeatBox;

    public static BeatBoxFragment newInstance() {
        Bundle args = new Bundle();
        BeatBoxFragment fragment = new BeatBoxFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBeatBox = new BeatBox(getActivity());
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        FragmentBeatBoxBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_beat_box, container, false);
         binding.recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 3));
         binding.recyclerView.setAdapter(new SoundAdapter(mBeatBox.getSounds()));
        return binding.getRoot();
    }

    private class SoundAdapter extends RecyclerView.Adapter<SoundHolder> {

        private List<Sound> mSounds;

        public SoundAdapter(List<Sound> sounds) {
            mSounds = sounds;
        }

        @Override
        public SoundHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(getActivity());
            ItemListSoundBinding binding = DataBindingUtil.inflate(inflater, R.layout.item_list_sound, parent, false);
            return new SoundHolder(binding);
        }

        @Override
        public void onBindViewHolder(SoundHolder holder, int position) {
            Sound sound = mSounds.get(position);
            holder.bind(sound);
        }

        @Override
        public int getItemCount() {
            return mSounds.size();
        }
    }

    private class SoundHolder extends RecyclerView.ViewHolder {

        private ItemListSoundBinding mBinding;

        public SoundHolder(ItemListSoundBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
            mBinding.setViewModal(new SoundViewModel(mBeatBox));
        }

        public void bind(Sound sound) {
            mBinding.getViewModal().setSound(sound);
            mBinding.executePendingBindings();
        }

    }
}
