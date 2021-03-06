package com.lucaslz.weather;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.lucaslz.weather.data.Injection;
import com.lucaslz.weather.data.ProvincesDataSource;
import com.lucaslz.weather.data.ProvincesRepository;
import com.lucaslz.weather.domain.Province;
import com.lucaslz.weather.utils.HttpUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by lucas lee <llzqingdao2012gmail.com> on 2017/10/23.
 */
public class ChooseAreaFragment extends Fragment {
    public static final String TAG = ChooseAreaFragment.class.getSimpleName();

    public static final int LEVEL_PROVINCE = 0;
    public static final int LEVEL_CITY = 1;
    public static final int LEVEL_COUNTRY = 2;

    private RecyclerView mRecyclerView;
    private AddressAdapter mAddressAdapter;
    private ProgressDialog mProgressDialog;
    private ProvincesRepository mProvincesRepository;

    private List<Province> mProvinces;

    public static ChooseAreaFragment newInstance() {
        Bundle args = new Bundle();
        ChooseAreaFragment fragment = new ChooseAreaFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAddressAdapter = new AddressAdapter(new ArrayList<>(0));
        mProvincesRepository = Injection.provideProvincesRepository(getActivity().getApplicationContext());
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_choose_area, container, false);
        mRecyclerView = root.findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setAdapter(mAddressAdapter);
        return root;
    }

    @Override
    public void onResume() {
        super.onResume();

        new Thread(() -> {
            mProvincesRepository.findProvinces(new ProvincesDataSource.LoadProvincesCallback() {
                @Override
                public void onProvincesLoaded(List<Province> provinces) {
                    getActivity().runOnUiThread(() -> {
                        mProvinces = provinces;

                    });
                }

                @Override
                public void onDataNotAvailable() {
                    getActivity().runOnUiThread(() -> {
                        Toast.makeText(getActivity(), "获取数据失败", Toast.LENGTH_LONG).show();
                    });
                }
            });
        }).start();
    }

    private void showProgressDialog() {
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialog(getActivity());
            mProgressDialog.setMessage("正在加载中...");
            mProgressDialog.setCanceledOnTouchOutside(false);
        }
        mProgressDialog.show();
    }

    private void dismissProgressDialog() {
        if (mProgressDialog != null) {
            mProgressDialog.dismiss();
        }
    }

    private void queryFromServer(String address, final String type) {
        showProgressDialog();
        HttpUtil.sendOKHttpRequest(address, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String jsonString = response.body().toString();
                boolean result = false;
                if ("province".equals(type)) {
                }
            }
        });
    }

    private class AddressAdapter extends RecyclerView.Adapter<AddressViewHolder> {

        private List<String> mAddresses;

        public AddressAdapter(List<String> addresses) {
            mAddresses = addresses;
        }

        @Override
        public AddressViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
            View itemView = layoutInflater.inflate(R.layout.item_address, parent, false);
            return new AddressViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(AddressViewHolder holder, int position) {
            holder.bind(mAddresses.get(position));
        }

        @Override
        public int getItemCount() {
            return mAddresses.size();
        }
    }

    private class AddressViewHolder extends RecyclerView.ViewHolder {

        private TextView mAddressTextView;

        public AddressViewHolder(View itemView) {
            super(itemView);
            mAddressTextView = itemView.findViewById(R.id.address_text_view);
        }

        public void bind(String address) {
            mAddressTextView.setText(address);
        }
    }
}
