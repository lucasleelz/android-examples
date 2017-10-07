package lucaslz.com.uicomponentexamples;

import android.os.Bundle;
import android.support.annotation.Nullable;
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

    private List<String> dataSource = ImmutableList.of(
            "Layout",
            "TextView",
            "Button",
            "EditText",
            "ImageView",
            "ProgressView",
            "AlertDialog",
            "ProgressDialog",
            "ListView",
            "RecyclerView"
    );

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

        private List<String> dataSource;

        private ExampleAdapter(List<String> dataSource) {
            this.dataSource = dataSource;
        }

        @Override
        public ExampleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            View view = layoutInflater.inflate(R.layout.item_main, parent, false);
            return new ExampleViewHolder(view);
        }

        @Override
        public void onBindViewHolder(ExampleViewHolder holder, int position) {
            String name = this.dataSource.get(position);
            holder.setName(name);
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

        private void setName(String name) {
            mNameTextView.setText(name);
        }
    }
}
