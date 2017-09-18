package com.baway.hujiqiang.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.baway.hujiqiang.R;
import com.baway.hujiqiang.adapter.RecyclerViewAdapter;
import com.baway.hujiqiang.bean.Data;
import com.baway.hujiqiang.divider.DividerItemDecoration;
import com.baway.hujiqiang.utils.RequestUrlUtil;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * author:
 * data: 2017/8/25.
 * desc:Fragment1
 */

public class Fragment1 extends Fragment {

    private View view;
    private List<Data.ZwlBean> list = new ArrayList();
    private String url = "http://139.196.140.118:8080/get/%7B%22%5B%5D%22:%7B%22page%22:0,%22count%22:10,%22Moment%22:%7B%22content$%22:%22%2525a%2525%22%7D,%22User%22:%7B%22id@%22:%22%252FMoment%252FuserId%22,%22@column%22:%22id,name,head%22%7D,%22Comment%5B%5D%22:%7B%22count%22:2,%22Comment%22:%7B%22momentId@%22:%22%5B%5D%252FMoment%252Fid%22%7D%7D%7D%7D";
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Gson gson = new Gson();
            Data data = gson.fromJson(msg.obj.toString(), Data.class);
            list.addAll(data.zwl);
            adapter.notifyDataSetChanged();
        }
    };
    private RecyclerView recyclerView;
    private LinearLayoutManager manager;
    private RecyclerViewAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment1, null);
        //初适化数据
        initData();
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //初始化recyclerview
        recyclerView = (RecyclerView) view.findViewById(R.id.recycle_fragment1);
        manager = new LinearLayoutManager(getActivity());
        adapter = new RecyclerViewAdapter(list, getActivity());
        recyclerView.setLayoutManager(manager);
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(),
                DividerItemDecoration.VERTICAL_LIST));
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView,
                                             int newState) {
                super.onScrollStateChanged(recyclerView, newState);

            }

            //滑动监听
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int endCompletelyPosition = manager.
                        findLastCompletelyVisibleItemPosition();
                if (endCompletelyPosition == manager.getItemCount() - 1) {
                    initData();
                }
            }

        });
    }

    //初始化数据方法
    private void initData() {
        new Thread() {
            @Override
            public void run() {
                super.run();
                Call call = RequestUrlUtil.getInstance().getRequest(url);
                call.enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {

                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        String result = response.body().string();
                        Log.e("fragment1", "onResponse: " + result);
                        Message msg = Message.obtain();
                        msg.what = 1;
                        msg.obj = result;
                        handler.sendMessage(msg);
                    }
                });
            }
        }.start();
    }
}
