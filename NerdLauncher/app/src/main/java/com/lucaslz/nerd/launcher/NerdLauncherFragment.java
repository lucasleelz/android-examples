package com.lucaslz.nerd.launcher;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Collections;
import java.util.List;

public class NerdLauncherFragment extends Fragment {

    private RecyclerView mAppRecyclerView;

    public static NerdLauncherFragment newInstance() {

        Bundle args = new Bundle();

        NerdLauncherFragment fragment = new NerdLauncherFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View containerView = inflater.inflate(R.layout.fragment_nerd_launcher, container, false);
        mAppRecyclerView = containerView.findViewById(R.id.app_recycler_view);
        setAdapter();
        return containerView;
    }

    private void setAdapter() {
        mAppRecyclerView.setAdapter(new ActivityAdapter(getActivities()));
    }

    private List<ResolveInfo> getActivities() {
        Intent startupIntent = new Intent(Intent.ACTION_MAIN);
        startupIntent.addCategory(Intent.CATEGORY_LAUNCHER);
        PackageManager packageManager = getActivity().getPackageManager();
        List<ResolveInfo> activities = packageManager.queryIntentActivities(startupIntent, 0);
        Collections.sort(activities, (ResolveInfo lhs, ResolveInfo rhs) ->
                String.CASE_INSENSITIVE_ORDER.compare(
                        lhs.loadLabel(packageManager).toString(),
                        rhs.loadLabel(packageManager).toString())
        );
        return activities;
    }

    private class ActivityAdapter extends RecyclerView.Adapter<ActivityViewHolder> {

        private List<ResolveInfo> mActivities;

        private ActivityAdapter(List<ResolveInfo> activities) {
            this.mActivities = activities;
        }

        @Override
        public ActivityViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            View view = layoutInflater
                    .inflate(R.layout.fragment_nerd_launcher_item, parent, false);
            return new ActivityViewHolder(view);
        }

        @Override
        public void onBindViewHolder(ActivityViewHolder holder, int position) {
            ResolveInfo resolveInfo = mActivities.get(position);
            holder.bindActivity(resolveInfo);
        }

        @Override
        public int getItemCount() {
            return mActivities.size();
        }
    }

    private class ActivityViewHolder extends RecyclerView.ViewHolder {

        private ResolveInfo mResolveInfo;
        private TextView mNameTextView;
        private ImageView mAppIconImageView;

        private ActivityViewHolder(View itemView) {
            super(itemView);
            mNameTextView = itemView.findViewById(R.id.app_name_text_view);
            mNameTextView.setOnClickListener(view -> {
                ActivityInfo activityInfo = mResolveInfo.activityInfo;
                Intent intent = new Intent(Intent.ACTION_MAIN)
                        .setClassName(activityInfo.applicationInfo.packageName, activityInfo.name);
//                         在新的任务中启动Activity。实际上就是唤醒了其他的应用。
//                        .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            });

            mAppIconImageView = itemView.findViewById(R.id.app_icon_image_view);
        }

        private void bindActivity(ResolveInfo resolveInfo) {
            mResolveInfo = resolveInfo;
            PackageManager packageManager = getActivity().getPackageManager();
            String appName = mResolveInfo.loadLabel(packageManager).toString();
            mNameTextView.setText(appName);
            Drawable appIcon = mResolveInfo.loadIcon(packageManager);
            mAppIconImageView.setImageDrawable(appIcon);
        }
    }
}