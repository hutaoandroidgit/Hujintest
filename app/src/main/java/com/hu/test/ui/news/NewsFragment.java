package com.hu.test.ui.news;

import android.content.Context;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.LayoutHelper;
import com.alibaba.android.vlayout.RecyclablePagerAdapter;
import com.alibaba.android.vlayout.VirtualLayoutManager;
import com.alibaba.android.vlayout.layout.LinearLayoutHelper;
import com.hu.test.R;
import com.hu.test.ui.BaseFragment;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by TT on 2017/6/29.
 */
public class NewsFragment extends BaseFragment {

    private RecyclerView rvNews;

    private static final boolean BANNER_LAYOUT = true;
    private SwipeRefreshLayout srlNews;

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        showContentView();

        rvNews = (RecyclerView) child.findViewById(R.id.rv_news);

        srlNews = (SwipeRefreshLayout) child.findViewById(R.id.swl_news);
        srlNews.setColorSchemeColors(getResources().getColor(R.color.orangered));
        srlNews.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
            }
        });



        VirtualLayoutManager layoutManager = new VirtualLayoutManager(getActivity());
        rvNews.setLayoutManager(layoutManager);

        //创建分割线
        RecyclerView.ItemDecoration itemDecoration = new RecyclerView.ItemDecoration() {
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                int position = ((VirtualLayoutManager.LayoutParams) view.getLayoutParams()).getViewPosition();
                outRect.set(4, 4, 4, 4);
            }
        };

        final RecyclerView.RecycledViewPool viewPool = new RecyclerView.RecycledViewPool();
        //设置回收池
        rvNews.setRecycledViewPool(viewPool);
        viewPool.setMaxRecycledViews(0, 20);

        //创建vlayout的委派适配器
        final DelegateAdapter delegateAdapter = new DelegateAdapter(layoutManager , true);

        rvNews.setAdapter(delegateAdapter);

        //创建链式适配器集合
        final List<DelegateAdapter.Adapter> adapters = new LinkedList<>();

        if(BANNER_LAYOUT){
            adapters.add(new SubAdapter(getContext(), new LinearLayoutHelper(), 1){

                @Override
                public void onViewRecycled(MainViewHolder holder) {
                    if(holder.itemView instanceof ViewPager){
                        ((ViewPager) holder.itemView).setAdapter(null);
                    }
                }

                @Override
                public MainViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                    if(viewType == 1){
                        return  new MainViewHolder(
                                LayoutInflater.from(getActivity()).inflate(R.layout.view_pager, parent, false)
                        );
                    }
                    return super.onCreateViewHolder(parent, viewType);
                }

                @Override
                public int getItemViewType(int position) {
                    return 1;
                }

                @Override
                protected void onBindViewHolderWithOffset(MainViewHolder holder, int position, int offsetTotal) {

                }

                @Override
                public void onBindViewHolder(MainViewHolder holder, int position) {
                    if(holder.itemView instanceof  ViewPager){
                        ViewPager viewPager = (ViewPager) holder.itemView;

                        viewPager.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 400));

                        viewPager.setAdapter(new PagerAdapter(this, viewPool));
                    }
                }
            });
        }

    }

    @Override
    public int setContent() {
        return R.layout.fragment_book;
    }


    static class SubAdapter extends DelegateAdapter.Adapter<MainViewHolder> {

        private Context mContext;

        private LayoutHelper mLayoutHelper;


        private VirtualLayoutManager.LayoutParams mLayoutParams;
        private int mCount = 0;


        public SubAdapter(Context context, LayoutHelper layoutHelper, int count) {
            this(context, layoutHelper, count, new VirtualLayoutManager.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 300));
        }

        public SubAdapter(Context context, LayoutHelper layoutHelper, int count, @NonNull VirtualLayoutManager.LayoutParams layoutParams) {
            this.mContext = context;
            this.mLayoutHelper = layoutHelper;
            this.mCount = count;
            this.mLayoutParams = layoutParams;
        }

        @Override
        public LayoutHelper onCreateLayoutHelper() {
            return mLayoutHelper;
        }

        @Override
        public MainViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new MainViewHolder(LayoutInflater.from(mContext).inflate(R.layout.fingerprint_dialog, parent, false));
        }

        @Override
        public void onBindViewHolder(MainViewHolder holder, int position) {
            // only vertical
            holder.itemView.setLayoutParams(
                    new VirtualLayoutManager.LayoutParams(mLayoutParams));
        }


        @Override
        protected void onBindViewHolderWithOffset(MainViewHolder holder, int position, int offsetTotal) {
            ((TextView) holder.itemView.findViewById(R.id.title)).setText(Integer.toString(offsetTotal));
        }

        @Override
        public int getItemCount() {
            return mCount;
        }
    }


    static class MainViewHolder extends RecyclerView.ViewHolder {

        public static volatile int existing = 0;
        public static int createdTimes = 0;

        public MainViewHolder(View itemView) {
            super(itemView);
            createdTimes++;
            existing++;
        }

        @Override
        protected void finalize() throws Throwable {
            existing--;
            super.finalize();
        }
    }

    static class PagerAdapter extends RecyclablePagerAdapter<MainViewHolder> {
        public PagerAdapter(SubAdapter adapter, RecyclerView.RecycledViewPool pool) {
            super(adapter, pool);
        }

        @Override
        public int getCount() {
            return 6;
        }

        @Override
        public void onBindViewHolder(MainViewHolder viewHolder, int position) {
            // only vertical
            viewHolder.itemView.setLayoutParams(
                    new VirtualLayoutManager.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            ((TextView) viewHolder.itemView.findViewById(R.id.title)).setText("Banner: " + position);
        }

        @Override
        public int getItemViewType(int position) {
            return 0;
        }
    }

}



