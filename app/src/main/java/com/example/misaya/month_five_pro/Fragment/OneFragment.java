package com.example.misaya.month_five_pro.Fragment;


import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.misaya.month_five_pro.Adapter.InfoAdapter;
import com.example.misaya.month_five_pro.Bean.InfoBean;
import com.example.misaya.month_five_pro.Okhttp;
import com.example.misaya.month_five_pro.R;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment } subclass.
 */
public class OneFragment extends Fragment {


    private RecyclerView one_rv;
    List<InfoBean.SongListBean> mDatas = new ArrayList<>();

    String path = "http://tingapi.ting.baidu.com/v1/restserver/ting?method=baidu.ting.billboard.billList&type=1&size=10&offset=23";
    public  static InfoAdapter adapter;

    public OneFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_one, container, false);
        initView(view);
        new Task().execute();
        one_rv.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        adapter = new InfoAdapter(getContext(), mDatas);
        one_rv.setAdapter(adapter);
        return view;
    }

    private void initView(View view) {
        one_rv = (RecyclerView) view.findViewById(R.id.one_rv);
    }

    class Task extends AsyncTask<Void, Void, String> {

        @Override
        protected String doInBackground(Void... voids) {
            return Okhttp.getString(path);
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            if (s != null && !s.isEmpty()) {
                Gson gson = new Gson();
                InfoBean infoBean = gson.fromJson(s, InfoBean.class);
                List<InfoBean.SongListBean> song_list = infoBean.getSong_list();
                mDatas.addAll(song_list);
                adapter.notifyDataSetChanged();
            }
        }


    }

    @Override
    public void onPause() {
        super.onPause();
        InfoAdapter.tag = false;
        InfoAdapter.iselected=false;
    }
}
