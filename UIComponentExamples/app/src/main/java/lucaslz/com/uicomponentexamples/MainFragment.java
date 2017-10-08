package lucaslz.com.uicomponentexamples;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by lucas on 2017/10/7.
 */
public class MainFragment extends Fragment {

    public static MainFragment newInstance() {
        Bundle args = new Bundle();
        MainFragment fragment = new MainFragment();
        fragment.setArguments(args);
        return fragment;
    }

    private List<Example> dataSource = new ArrayList<>();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        dataSource.addAll(
                ImmutableList.of(
                        new Example("Layout 布局", "Layout"),
                        new Example("TextView", "Text"),
                        new Example("Button", "Button"),
                        new Example("EditText", "EditText"),
                        new Example("ImageView", "ImageView"),
                        new Example("ProgressView", "ProgressView"),
                        new Example("AlertDialog", "AlertDialog"),
                        new Example("ProgressDialog", "ProgressDialog"),
                        new Example("ListView", "ListView"),
                        new Example("RecyclerView", "RecyclerView"))
        );
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(new ExampleAdapter(dataSource));
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
        return view;
    }

    class ExampleAdapter extends RecyclerView.Adapter<ExampleViewHolder> {

        private List<Example> dataSource;

        private ExampleAdapter(List<Example> dataSource) {
            this.dataSource = dataSource;
        }

        @Override
        public ExampleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            View itemView = layoutInflater.inflate(R.layout.item_main, parent, false);
            return new ExampleViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(ExampleViewHolder holder, int position) {
            Example example = this.dataSource.get(position);
            holder.bind(example);
        }

        @Override
        public int getItemCount() {
            return dataSource.size();
        }
    }

    class ExampleViewHolder extends RecyclerView.ViewHolder {

        private TextView mNameTextView;

        private ExampleViewHolder(View itemView) {
            super(itemView);
            mNameTextView = itemView.findViewById(R.id.name_text_view);
        }

        private void bind(Example example) {
            mNameTextView.setText(example.getName());
            this.itemView.setOnClickListener(view -> {
                if (example.getType() == null) {
                    Snackbar.make(this.itemView, example.getName(), Snackbar.LENGTH_LONG).show();
                } else {
                    startActivity(UIComponentActivity.newIntent(getContext(), example.getType()));
                }
            });
        }
    }
}
