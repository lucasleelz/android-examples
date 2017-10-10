package com.lucaslz.uicomponentexamples;

import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by lucas on 2017/10/10.
 */

public class CollapsingToolbarLayoutFragment extends Fragment {

    public static CollapsingToolbarLayoutFragment newInstance() {
        Bundle args = new Bundle();

        CollapsingToolbarLayoutFragment fragment = new CollapsingToolbarLayoutFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_collapsing_tool_bar_layout, container, false);

        Toolbar toolbar = view.findViewById(R.id.toolbar);
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        ActionBar actionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        CollapsingToolbarLayout collapsingToolbarLayout = view.findViewById(R.id.collapsing_toolbar);
        collapsingToolbarLayout.setTitle("这是标题~");

        ImageView fruitImageView = view.findViewById(R.id.fruit_image_view);
        fruitImageView.setImageDrawable(getResources().getDrawable(R.drawable.banana));

        TextView fruitContentView = view.findViewById(R.id.fruit_content_text);
        fruitContentView.setText("　原标题  新任韩国大使：期待中韩关系早日改善\n" +
                "\n" +
                "　　“千百年来，中韩两国是邻居，交往历史源远流长，是‘想分都分不开’的关系。希望中韩关系能早日改善。这符合两国共同利益。”\n" +
                "\n" +
                "　　韩国新任驻华大使卢英敏10日赴北京上任。履新前夕，他在首尔接受新华社记者专访。\n" +
                "\n" +
                "　　“邻居就是亲戚”\n" +
                "\n" +
                "　　8月30日，韩国总统文在寅提名曾任三届国会议员的卢英敏出任驻华大使。青瓦台发言人当时表示，期待卢英敏能妥善解决对华外交难题，继续巩固和发展建交25年的韩中关系。\n" +
                "\n" +
                "　　卢英敏表示，千百年来，中韩都是近邻，两国交往源远流长，是“想分都分不开”的关系。韩国有句俗语“邻居就是亲戚”，用来比喻中韩关系十分恰当。");

        return view;
    }
}
