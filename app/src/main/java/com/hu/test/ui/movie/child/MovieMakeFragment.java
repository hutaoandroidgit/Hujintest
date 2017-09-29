package com.hu.test.ui.movie.child;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.hu.test.MainActivity;
import com.hu.test.R;
import com.hu.test.adapter.Movie250Adapter;
import com.hu.test.adapter.MovieHotAdapter;
import com.hu.test.adapter.MovieStoryAdapter;
import com.hu.test.adapter.MovieWillAdapter;
import com.hu.test.bean.JsonCallback;
import com.hu.test.bean.Movie250Modle;
import com.hu.test.ui.BaseFragment;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by TT on 2017/7/12.
 */

public class MovieMakeFragment extends BaseFragment {


    private static final String TYPE = "param1";
    private String mType = "综合";
    private MainActivity activity;
    private SwipeRefreshLayout srlMovie;
    private RecyclerView mRecyclerView;
    private Movie250Modle movie250Modle;
    private List<Movie250Modle.SubjectsBean> movie250List = new ArrayList<>();
    private List<Movie250Modle.SubjectsBean> movieHotList = new ArrayList<>();
    private List<Movie250Modle.SubjectsBean> movieWillList = new ArrayList<>();
    private List<Movie250Modle.SubjectsBean> movieStoreList = new ArrayList<>();
    private Movie250Adapter movie250Adapter;
    private MovieHotAdapter movieHotAdapter;
    private MovieWillAdapter movieWillAdapter;
    private MovieStoryAdapter movieStoryAdapter;

    public static MovieMakeFragment newInstance(String param1) {

        MovieMakeFragment fragment = new MovieMakeFragment();
        Bundle args = new Bundle();
        args.putString(TYPE, param1);
        fragment.setArguments(args);//fragment通过build传值

        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mType = getArguments().getString(TYPE);
            Log.e("type==", mType);
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        showContentView();

        srlMovie = (SwipeRefreshLayout) child.findViewById(R.id.srl_movie);
        srlMovie.setColorSchemeColors(getResources().getColor(R.color.orangered));
        srlMovie.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadChildData();
            }
        });

        mRecyclerView = (RecyclerView) child.findViewById(R.id.xrv_movie);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        initAdapter();
    }

    private void initAdapter() {
        switch (mType) {
            case "正在热映":
                movieHotAdapter = new MovieHotAdapter(getActivity(),movieHotList);
                mRecyclerView.setAdapter(movieHotAdapter);
                break;
            case "即将上映":
                movieWillAdapter = new MovieWillAdapter(getContext(),movieWillList );
                mRecyclerView.setAdapter(movieWillAdapter);
                break;
            case "剧情":
                movieStoryAdapter = new MovieStoryAdapter(getContext(),movieStoreList );
                mRecyclerView.setAdapter(movieStoryAdapter);
                break;
            case "爱情":
                break;
            case "动画":


                break;
            case "TOP-250":
                movie250Adapter = new Movie250Adapter(getContext(),movie250List );
                mRecyclerView.setAdapter(movie250Adapter);
                break;
            default:
                break;
        }
    }


    @Override
    public void onResume() {
        super.onResume();
        initData();
    }

    private void initData() {
        switch (mType) {
            case "正在热映":

                OkGo.<Movie250Modle>get("Https://api.douban.com/v2/movie/in_theaters")
                        .params("start", 0)
                        .params("count",10)
                        .params("city","上海")
                        .execute(new JsonCallback<Movie250Modle>() {
                            @Override
                            public void onSuccess(Response<Movie250Modle> response) {
                                Movie250Modle body = response.body();
                                Log.e("okgo", body.toString());
                                movieHotList.addAll(body.getSubjects());
                                movieHotAdapter.notifyDataSetChanged();
                            }
                        });
                break;
            case "即将上映":
                OkGo.<Movie250Modle>get("Https://api.douban.com/v2/movie/coming_soon")
                        .params("start", 0)
                        .params("count",10)
                        .execute(new JsonCallback<Movie250Modle>() {
                            @Override
                            public void onSuccess(Response<Movie250Modle> response) {
                                Movie250Modle body = response.body();
                                Log.e("okgo", body.toString());
                                movieWillList.addAll(body.getSubjects());
                                movieWillAdapter.notifyDataSetChanged();
                            }
                        });

                break;
            case "剧情":
                OkGo.<Movie250Modle>get("Https://api.douban.com/v2/movie/search?tag=剧情")
                        .params("start", 0)
                        .params("count",10)
                        .execute(new JsonCallback<Movie250Modle>() {
                            @Override
                            public void onSuccess(Response<Movie250Modle> response) {
                                Movie250Modle body = response.body();
                                Log.e("okgo剧情", body.toString());
                                movieStoreList.addAll(body.getSubjects());
                                movieStoryAdapter.notifyDataSetChanged();
                            }
                        });
                break;
            case "爱情":
               break;
            case "动画":

                break;
            case "TOP-250":

                OkGo.<Movie250Modle>get("Https://api.douban.com/v2/movie/top250")
                        .params("start", 0)
                        .params("count", 10)
                        .execute(new JsonCallback<Movie250Modle>() {
                            @Override
                            public void onSuccess(Response<Movie250Modle> response) {
                                movie250Modle = response.body();
                                movie250List.addAll(movie250Modle.getSubjects());
                                movie250Adapter.notifyDataSetChanged();
                            }
                        });
                break;
            default:
                break;
        }
    }

    private void loadChildData() {

    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        activity = (MainActivity) context;
    }

    @Override
    public int setContent() {
        return R.layout.fragment_movie_make;
    }
}
