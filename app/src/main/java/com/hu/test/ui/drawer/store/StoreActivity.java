package com.hu.test.ui.drawer.store;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.LayoutHelper;
import com.alibaba.android.vlayout.VirtualLayoutManager;
import com.alibaba.android.vlayout.VirtualLayoutManager.LayoutParams;
import com.alibaba.android.vlayout.layout.ColumnLayoutHelper;
import com.alibaba.android.vlayout.layout.FixLayoutHelper;
import com.alibaba.android.vlayout.layout.FloatLayoutHelper;
import com.alibaba.android.vlayout.layout.GridLayoutHelper;
import com.alibaba.android.vlayout.layout.LinearLayoutHelper;
import com.alibaba.android.vlayout.layout.SingleLayoutHelper;
import com.facebook.drawee.drawable.ScalingUtils;
import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.generic.GenericDraweeHierarchyBuilder;
import com.facebook.drawee.view.SimpleDraweeView;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;
import com.hu.test.R;
import com.hu.test.adapter.AndroidAdapter;
import com.hu.test.adapter.HMeiTuAdapter;
import com.hu.test.adapter.MainViewHolder;
import com.hu.test.adapter.NbaNewsAdapter;
import com.hu.test.adapter.OneonePlusNLayoutAdapter;
import com.hu.test.bean.GankDataModle;
import com.hu.test.bean.JockImageModle;
import com.hu.test.bean.JsonCallback;
import com.hu.test.bean.NewsMoble;
import com.hu.test.bean.WeatherMoble;
import com.hu.test.okgohttp.Constants;
import com.hu.test.utils.FrescoUtils;
import com.hu.test.utils.StatusBarUtil;
import com.hu.test.wight.ImageCycleView;
import com.hu.test.wight.UPMarqueeView;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheMode;
import com.lzy.okgo.model.Response;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 * Created by TT on 2017/7/17.
 */
public class StoreActivity extends Activity {

    private static final boolean BANNER_LAYOUT = true;

    private static final boolean H_LAYOUT = true;

    private static final boolean ONEPLUS_LAYOUT = true;

    private static final boolean FIX_LAYOUT = true;

    private static final boolean LINEAR_LAYOUT = true;

    private static final boolean SINGLE_LAYOUT = true;

    private static final boolean FLOAT_LAYOUT = true;

    private static final boolean ONEN_LAYOUT = true;

    private static final boolean COLUMN_LAYOUT = true;

    private static final boolean GRID_LAYOUT = true;

    private static final boolean STICKY_LAYOUT = true;

    private static final boolean STAGGER_LAYOUT = true;

    private static final boolean MARQUEE_LAYOUT = true;

    private static final boolean SEARCH_LAYOUT = true;
    private static final boolean LIST_ANDROID = true ;


    private RecyclerView recyclerView;
    private RecyclerView recyclerViewH;
    private SwipeRefreshLayout swipeRefreshLayout;
    private Runnable trigger;
    private List<String> adList = new ArrayList<>();
    private UPMarqueeView upMarqueeView;

    List<String> data = new ArrayList<>();
    List<View> views = new ArrayList<>();
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;
    private List<JockImageModle.ResultBean.DataBean> dataBeen = new ArrayList<>();
    private List<DelegateAdapter.Adapter> adapters;
    private Handler mainHandler;
    private ImageCycleView viewPager;
    private LinearLayout llSearch;
    private List<GankDataModle.ResultsBean> results = new ArrayList<>();
    private List<GankDataModle.ResultsBean> resultsBeen = new ArrayList<>();
    private HMeiTuAdapter hMeiTuAdapter;
    private TextView tvMoreFuli;
    private int red;
    private int grenn;
    private int blue;
    private SimpleDraweeView sdWeather;
    private TextView tvWeather;
    private SingleLayoutHelper singleLayoutHelper;
    private LayoutParams oPlp;
    private WeatherMoble.ShowapiResBodyBean showapi_res_body;
    private WeatherMoble.ShowapiResBodyBean.NowBean showapi_res_bodyNow;
    private OneonePlusNLayoutAdapter onePlusNLayoutAdapter;
    private DelegateAdapter delegateAdapter;
    private TextView tvVTip;
    private RecyclerView recyclerViewNba;
    private SubAdapter nbaContentAdapter;
    private SubAdapter nbaSubAdapter;
    private RecyclerView recyclerViewAndroid;
    private AndroidAdapter androidAdapter;
    private List<NewsMoble.DataBean> dataNba = new ArrayList<>();
    private SubAdapter nbaSubAdapterLast;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store);

        initID();
        initView();
        StatusBarUtil.setTranslucentForImageView(StoreActivity.this, 0, null);
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    private void initView() {

        final VirtualLayoutManager layoutManager = new VirtualLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        //创建分割线
        RecyclerView.ItemDecoration itemDecoration = new RecyclerView.ItemDecoration() {
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                int position = ((LayoutParams) view.getLayoutParams()).getViewPosition();
                outRect.set(4, 4, 4, 4);
            }
        };

        final RecyclerView.RecycledViewPool viewPool = new RecyclerView.RecycledViewPool();
        viewPool.setMaxRecycledViews(0, 20);
        //设置回收池
        recyclerView.setRecycledViewPool(viewPool);


        //创建vlayout的委派适配器
        delegateAdapter = new DelegateAdapter(layoutManager, true);

        recyclerView.setAdapter(delegateAdapter);

        //创建链式适配器
        //
        // 集合
        adapters = new LinkedList<>();

        //是否显示头布局，1代表数量
        if (BANNER_LAYOUT) {
            //请求头部url数据
            requestBannder();

            adapters.add(new SubAdapter(getApplicationContext(), new LinearLayoutHelper(), 1) {

                @Override
                public void onViewRecycled(MainViewHolder holder) {
                    if (holder.itemView instanceof ViewPager) {
                        ((ViewPager) holder.itemView).setAdapter(null);
                    }
                    Log.e("TAG", "头部回收");
                }

                @Override
                public MainViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                    if (viewType == 1) {
                        return new MainViewHolder(
                                LayoutInflater.from(StoreActivity.this).inflate(R.layout.view_pager, parent, false)
                        );
                    }
                    return super.onCreateViewHolder(parent, viewType);
                }

                @Override
                public int getItemViewType(int position) {
                    return 1;
                }

                @Override
                protected void onBindViewHolderWithOffset(
                        MainViewHolder holder, int position, int offsetTotal) {

                }

                @Override
                public void onBindViewHolder(MainViewHolder holder, int position) {
                    if (holder.itemView instanceof ImageCycleView) {
                        viewPager = (ImageCycleView) holder.itemView;
                        viewPager.setFocusable(false);
                        viewPager.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 600));
                        if(adList != null){
                            viewPager.setImageResources(adList, new ImageCycleView.ImageCycleViewListener() {
                                @Override
                                public void displayImage(String imageURL, SimpleDraweeView imageView) {

                                    GenericDraweeHierarchyBuilder builder =
                                            new GenericDraweeHierarchyBuilder(getResources());
                                    builder.setActualImageScaleType(ScalingUtils.ScaleType.FIT_XY);
                                    GenericDraweeHierarchy hierarchy = builder
                                            .setFadeDuration(300)
                                            .build();
                                    imageView.setHierarchy(hierarchy);
                                    FrescoUtils.setController(imageURL, imageView);
                                }

                                @Override
                                public void onImageClick(int position, View imageView) {

                                }
                            });
                        }


                    }
                }
            });


        }

        /**
         * 搜索欄
         */
        if (SEARCH_LAYOUT) {
            //      默认为左上，跟需要一样
            //设置布局大小
            FixLayoutHelper fixLayoutHelper = new FixLayoutHelper(FixLayoutHelper.TOP_RIGHT, 68, 68);
            adapters.add(new SubAdapter(getApplicationContext(), fixLayoutHelper, 1) {


                @Override
                public MainViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                    return new MainViewHolder(
                            LayoutInflater.from(StoreActivity.this).inflate(R.layout.item_vlayout_search, parent, false)
                    );
                }

                @Override
                public void onBindViewHolder(MainViewHolder holder, int position) {
                    if (holder.itemView instanceof LinearLayout) {
                        LinearLayout llSearch = (LinearLayout) holder.itemView;
                        VirtualLayoutManager.LayoutParams layoutParams = new VirtualLayoutManager.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, VirtualLayoutManager.LayoutParams.WRAP_CONTENT);
                        llSearch.setLayoutParams(layoutParams);
                        llSearch.findViewById(R.id.re_home_search).setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Toast.makeText(getApplication(), "去搜索框", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }


                }
            });

        }


        //存在浮动布局，可拖动
        if (FLOAT_LAYOUT) {
            FloatLayoutHelper layoutHelper = new FloatLayoutHelper();
            //设置队列方向,底部左边
            layoutHelper.setAlignType(FixLayoutHelper.BOTTOM_RIGHT);
            //设置默认位置,默认位置方向远离
            layoutHelper.setDefaultLocation(100, 600);
            //设置布局大小
            LayoutParams layoutParams = new LayoutParams(300, 150);
            adapters.add(new SubAdapter(getApplicationContext(), layoutHelper, 1, layoutParams) {
                @Override
                public void onViewRecycled(MainViewHolder holder) {

                }

                @Override
                public MainViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                    if (viewType == 1) {
                        return new MainViewHolder(
                                LayoutInflater.from(StoreActivity.this).inflate(R.layout.item_vlayout_float, parent, false)
                        );
                    }
                    return super.onCreateViewHolder(parent, viewType);
                }

                @Override
                public int getItemViewType(int position) {
                    return 1;
                }

                @Override
                protected void onBindViewHolderWithOffset(
                        MainViewHolder holder, int position, int offsetTotal) {

                }

                @Override
                public void onBindViewHolder(MainViewHolder holder, int position) {
                    ImageView imageView = (ImageView) holder.itemView.findViewById(R.id.icon);
                    imageView.setImageResource(R.mipmap.book);
                }
            });
        }

        //设置网格布局
        if (GRID_LAYOUT) {
            //请求数据，模拟加载数
            GridLayoutHelper gridLayoutHelper = new GridLayoutHelper(5, 10);
            //设置布局里item的个数
            //  gridLayoutHelper.setItemCount(10);
            // gridLayoutHelper.setPadding(20, 20, 20, 20);// 设置LayoutHelper的子元素相对LayoutHelper边缘的距离
            //  gridLayoutHelper.setMargin(20, 20, 20, 20);// 设置LayoutHelper边缘相对父控件（即RecyclerView）的距离
            gridLayoutHelper.setBgColor(Color.WHITE);// 设置背景颜色
            //   gridLayoutHelper.setAspectRatio(6);// 设置设置布局内每行布局的宽与高的比


            //特有属性
            // gridLayoutHelper特有属性
          /*  gridLayoutHelper.setWeights(new float[]{1, 1, 1});//设置每行中 每个网格宽度 占 每行总宽度 的比例
            gridLayoutHelper.setVGap(20);// 控制子元素之间的垂直间距
            gridLayoutHelper.setHGap(20);// 控制子元素之间的水平间距
            gridLayoutHelper.setAutoExpand(false);//是否自动填充空白区域
            gridLayoutHelper.setSpanCount(5);// 设置每行多少个网格*/
            // 通过自定义SpanSizeLookup来控制某个Item的占网格个数
           /* gridLayoutHelper.setSpanSizeLookup(new GridLayoutHelper.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    if (position > 7 ) {
                        return 3;
                        // 第7个位置后,每个Item占3个网格
                    }else {
                        return 2;
                        // 第7个位置前,每个Item占2个网格
                    }
                }
            });*/
            //设置布局大小
            LayoutParams layoutParams = new LayoutParams(300, 800);
            adapters.add(new SubAdapter(this, gridLayoutHelper, 10) {
                @Override
                public void onViewRecycled(MainViewHolder holder) {

                }

                @Override
                public MainViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                    if (viewType == 1) {
                        return new MainViewHolder(
                                LayoutInflater.from(StoreActivity.this).inflate(R.layout.item_vlayout_float, parent, false)
                        );
                    }
                    return super.onCreateViewHolder(parent, viewType);
                }

                @Override
                public int getItemViewType(int position) {
                    return 1;
                }

                @Override
                protected void onBindViewHolderWithOffset(
                        MainViewHolder holder, int position, int offsetTotal) {

                }

                @Override
                public void onBindViewHolder(MainViewHolder holder, int position) {
                    ImageView imageView = (ImageView) holder.itemView.findViewById(R.id.icon);
                    if (position % 2 == 0) {
                        imageView.setImageResource(R.mipmap.book);
                    } else {
                        imageView.setImageResource(R.mipmap.account);
                    }

                }
            });

        }


        if (MARQUEE_LAYOUT) {
            //跑马灯
            initDataMarquee();
            ColumnLayoutHelper columnLayoutHelper = new ColumnLayoutHelper();
            columnLayoutHelper.setBgColor(Color.WHITE);
            adapters.add(new SubAdapter(this, columnLayoutHelper, 1, new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 500)) {

                @Override
                public void onBindViewHolder(MainViewHolder holder, int position) {
                    upMarqueeView = (UPMarqueeView) holder.itemView.findViewById(R.id.upview);
                    setView();//获取数据
                    upMarqueeView.setViews(views);//填充数据
                    upMarqueeView.setSetAutoPlay(true);//设置是否自动播放
                   upMarqueeView.setFocusable(false);
                    upMarqueeView.setAnimDuration(520);
                    /**
                     * 设置item_view的监听
                     */
                    upMarqueeView.setOnItemClickListener(new UPMarqueeView.OnItemClickListener() {
                        @Override
                        public void onItemClick(int position, View view) {
                            Toast.makeText(StoreActivity.this, "你点击了第几个items" + position, Toast.LENGTH_SHORT).show();
                        }
                    });
                }

                @Override
                public MainViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                    return new MainViewHolder(
                            LayoutInflater.from(StoreActivity.this).inflate(R.layout.item_vlayout_marquee, parent, false)
                    );
                }

            });
        }


        /**
         * 横向的listview福利图片
         */
        if (H_LAYOUT) {

            adapters.add(new SubAdapter(getApplicationContext(), new LinearLayoutHelper(), 1) {
                @Override
                public void onBindViewHolder(MainViewHolder holder, int position) {
                    if (holder.itemView instanceof LinearLayout) {
                        LinearLayout ll = (LinearLayout) holder.itemView;
                        recyclerViewH = (RecyclerView) ll.findViewById(R.id.recycler_h);
                        recyclerViewH.setFocusable(false);
                        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplication());
                        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
                        recyclerViewH.setLayoutManager(linearLayoutManager);


                        tvMoreFuli = (TextView) ll.findViewById(R.id.tv_morefili);
                        tvMoreFuli.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {

                                Toast.makeText(getApplicationContext(), "更多", Toast.LENGTH_SHORT).show();
                            }
                        });

                    }
                }

                @Override
                public MainViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                    return new MainViewHolder(
                            LayoutInflater.from(StoreActivity.this).inflate(R.layout.item_vlayout_h, parent, false)
                    );
                }
            });

            initDataH();
           /* start = new CountDownTimer(Integer.MAX_VALUE, 1) {
                @Override
                public void onTick(long l) {
                    if (blue >= 256) {
                        blue = 0;
                        if (grenn >= 256) {
                            red = red + 20;
                            grenn = 0;
                        } else {
                            grenn = grenn + 20;
                        }
                    } else {
                        blue = blue + 20;
                    }

                    if (red >= 257) {
                        red = 0;
                        blue = 0;
                        grenn = 0;
                    }

                    tvMoreFuli.post(new Runnable() {
                        @Override
                        public void run() {
                            tvMoreFuli.setTextColor(Color.rgb(red, grenn, blue));
                        }
                    });

                }

                @Override
                public void onFinish() {

                }
            }.start();*/
        }




        /**
         * 隔栏推荐
         */
        if(true){

            initNBA();

        }
        /**
         *天气布局
         */
        if (ONEPLUS_LAYOUT) {
            initDataWeather();
            singleLayoutHelper = new SingleLayoutHelper();

        }

        if(LIST_ANDROID){

            adapters.add(new SubAdapter(getApplicationContext(), new LinearLayoutHelper(), 1) {
                @Override
                public void onBindViewHolder(MainViewHolder holder, int position) {
                    if (holder.itemView instanceof LinearLayout) {
                        LinearLayout ll = (LinearLayout) holder.itemView;
                        recyclerViewAndroid = (RecyclerView) ll.findViewById(R.id.recycler_android);
                        recyclerViewAndroid.setFocusable(false);
                        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplication());
                        recyclerViewAndroid.setLayoutManager(linearLayoutManager);

                        SimpleDraweeView androidSD = (SimpleDraweeView) ll.findViewById(R.id.sd_android);
                        FrescoUtils.setController("http://www.sxdaily.com.cn/NMediaFile/2014/0327/SXRB201403270751000502473647385.jpg",androidSD);
                    }
                }

                @Override
                public MainViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                    return new MainViewHolder(
                            LayoutInflater.from(StoreActivity.this).inflate(R.layout.item_vlayout_android, parent, false)
                    );
                }
            });
            initDataAndroid();

        }

        delegateAdapter.setAdapters(adapters);

        mainHandler = new Handler(Looper.getMainLooper());

        trigger = new Runnable() {
            @Override
            public void run() {
                //recyclerView.scrollToPosition(22);
                //recyclerView.getAdapter().notifyDataSetChanged();
                //mainHandler.postDelayed(trigger, 1000);
                //List<DelegateAdapter.Adapter> newAdapters = new ArrayList<>();
                //newAdapters.add((new SubAdapter(VLayoutActivity.this, new ColumnLayoutHelper(), 3)));
                //newAdapters.add((new SubAdapter(VLayoutActivity.this, new GridLayoutHelper(4), 24)));
                //delegateAdapter.addAdapter(0, new SubAdapter(VLayoutActivity.this, new ColumnLayoutHelper(), 3));
                //delegateAdapter.addAdapter(1, new SubAdapter(VLayoutActivity.this, new GridLayoutHelper(4), 24));
                //delegateAdapter.notifyDataSetChanged();

            }
        };

        mainHandler.postDelayed(trigger, 1000);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mainHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        swipeRefreshLayout.setRefreshing(false);
                    }
                }, 2000L);
            }
        });
        setListenerToRootView();

    }




    /**
     * nba数据
     */
    private void initNBA() {

        OkGo.<NewsMoble>get("http://120.76.205.241:8000/news/qihoo?")
                .params("kw", "nba")
                .params("site", "qq.com")
                .params("apikey", "Z2oKZ5IXifjcMnP7nYkTyOJG1785Q3VDySZgKsrJPXgNY6wc86BRAxNZZf928v0P")
                .execute(new JsonCallback<NewsMoble>() {
                    @Override
                    public void onSuccess(Response<NewsMoble> response) {
                        NewsMoble newsMoble = response.body();
                        Log.e("okgo", "新闻请求码："+newsMoble.getRetcode());
                        final List<NewsMoble.DataBean> data = newsMoble.getData();
                        if(data == null){
                            return;
                        }else {
                            for (int i = 0; i < 5; i++) {
                                dataNba.add(data.get(i));

                            }
                        }
                        //list的nba数据
                        nbaContentAdapter = new SubAdapter(getApplicationContext(), new LinearLayoutHelper(), 1) {
                            @Override
                            public void onBindViewHolder(MainViewHolder holder, int position) {
                                if (holder.itemView instanceof LinearLayout) {
                                    LinearLayout ll = (LinearLayout) holder.itemView;
                                    recyclerViewNba = (RecyclerView) ll.findViewById(R.id.recycler_nba);
                                    recyclerViewNba.setFocusable(false);
                                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplication());
                                    recyclerViewNba.setLayoutManager(linearLayoutManager);
                                    NbaNewsAdapter nbaNewsAdapter = new NbaNewsAdapter(dataNba);
                                    recyclerViewNba.setAdapter(nbaNewsAdapter);
                                }
                            }

                            @Override
                            public MainViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                                return new MainViewHolder(
                                        LayoutInflater.from(StoreActivity.this).inflate(R.layout.item_vlayout_nba, parent, false)
                                );
                            }
                        };


                        nbaSubAdapter = new SubAdapter(getApplicationContext(), new LinearLayoutHelper(), 1, new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT)) {
                            @Override
                            public void onBindViewHolder(MainViewHolder holder, int position) {
                                tvVTip = (TextView) holder.itemView.findViewById(R.id.tv_vtip);
                                tvVTip.setText("NBA");
                                tvVTip.setTypeface(Typeface.SERIF);
                            }
                        };

                        nbaSubAdapterLast = new SubAdapter(getApplicationContext(), new LinearLayoutHelper(), 1, new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT)) {
                            @Override
                            public void onBindViewHolder(MainViewHolder holder, int position) {
                                tvVTip = (TextView) holder.itemView.findViewById(R.id.tv_vtip);
                                tvVTip.setText("我们是有底线的~~");
                                tvVTip.setTypeface(Typeface.SERIF);
                            }
                        };
                        if(data != null){
                            delegateAdapter.addAdapter(nbaSubAdapter);
                            delegateAdapter.addAdapter(nbaContentAdapter);
                            delegateAdapter.addAdapter(nbaSubAdapterLast);
                        }

                    }
                });
    }

    /**
     * 请求天气数据
     */
    private void initDataWeather() {
        OkGo.<WeatherMoble>get(Constants.API_Weather)
                .params("areaid", "101020900")
                .params("area", "")
                .params("needMoreDay", "0")
                .params("needIndex", "0")
                .params("needHourData", "0")
                .params("need3HourForcast", "0")
                .params("needAlarm", "0")
                .params("showapi_appid", "45690")
                .params("showapi_sign", "e8a41605afee4760b270c5ddd5da2286")
                .execute(new JsonCallback<WeatherMoble>() {
                    @Override
                    public void onSuccess(Response<WeatherMoble> response) {
                        WeatherMoble body = response.body();
                        showapi_res_body = body.getShowapi_res_body();
                        showapi_res_bodyNow = showapi_res_body.getNow();

                        //请求天气数据
                        SubAdapter weatherSubAdpter = new SubAdapter(getApplicationContext(), new LinearLayoutHelper(), 1, new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT)) {
                            @Override
                            public void onBindViewHolder(MainViewHolder holder, int position) {
                                TextView tvVTip = (TextView) holder.itemView.findViewById(R.id.tv_vtip);
                                tvVTip.setText("实时天气");
                            }
                        };

                        onePlusNLayoutAdapter = new OneonePlusNLayoutAdapter(getApplicationContext(), singleLayoutHelper, showapi_res_bodyNow);

                        delegateAdapter.addAdapter(weatherSubAdpter);
                        delegateAdapter.addAdapter(onePlusNLayoutAdapter);

                    }
                });
    }
    /**
     * 请求android图片
     */
    private void initDataAndroid() {
        OkGo.<GankDataModle>get(Constants.API_GANKIO + "data/" + "Android" + "/" + 6 + "/" + new Random().nextInt(55))
                .execute(new JsonCallback<GankDataModle>() {
                    @Override
                    public void onSuccess(Response<GankDataModle> response) {
                        GankDataModle body = response.body();
                        results = body.getResults();

                        androidAdapter = new AndroidAdapter(StoreActivity.this,results);
                        recyclerViewAndroid.setAdapter(androidAdapter);
                    }
                });
    }

    /**
     * 请求横向图片
     */
    private void initDataH() {
        OkGo.<GankDataModle>get(Constants.API_GANKIO + "data/" + "福利" + "/" + 10 + "/" + new Random().nextInt(55))
                .execute(new JsonCallback<GankDataModle>() {
                    @Override
                    public void onSuccess(Response<GankDataModle> response) {
                        GankDataModle body = response.body();
                        results = body.getResults();
                        hMeiTuAdapter = new HMeiTuAdapter(results);
                        recyclerViewH.setAdapter(hMeiTuAdapter);

                    }
                });
    }

    /**
     * 请求头部数据
     */
    private void requestBannder() {
        long l = System.currentTimeMillis();
        OkGo.<JockImageModle>get("http://japi.juhe.cn/joke/img/text.from")
                .cacheMode(CacheMode.FIRST_CACHE_THEN_REQUEST)
                .params("sort", "asc")
                .params("page", new Random().nextInt(10000))
                .params("pagesize", 5)
                .params("time", l + "")
                .params("key", "a16cb45b21233fe529566a32b961800c")
                .execute(new JsonCallback<JockImageModle>() {
                    @Override
                    public void onSuccess(Response<JockImageModle> response) {
                        JockImageModle body = response.body();
                        dataBeen = body.getResult().getData();
                        adList.clear();
                        for (int i = 0; i < dataBeen.size(); i++) {
                            adList.add(dataBeen.get(i).getUrl());
                        }
                        Log.e("okgo",body.toString());
                        viewPager.setImageResources(adList, new ImageCycleView.ImageCycleViewListener() {
                            @Override
                            public void displayImage(String imageURL, SimpleDraweeView imageView) {

                                GenericDraweeHierarchyBuilder builder =
                                        new GenericDraweeHierarchyBuilder(getResources());
                                builder.setActualImageScaleType(ScalingUtils.ScaleType.FIT_XY);
                                GenericDraweeHierarchy hierarchy = builder
                                        .setFadeDuration(300)
                                        .build();
                                imageView.setHierarchy(hierarchy);
                                FrescoUtils.setController(imageURL, imageView);
                            }

                            @Override
                            public void onImageClick(int position, View imageView) {

                            }
                        });

                    }
                });

    }


    /**
     * 初始化需要循环的View
     * 为了灵活的使用滚动的View，所以把滚动的内容让用户自定义
     * 假如滚动的是三条或者一条，或者是其他，只需要把对应的布局，和这个方法稍微改改就可以了，
     */
    private void setView() {
        for (int i = 0; i < data.size(); i = i + 2) {
            final int position = i;
            //设置滚动的单个布局
            LinearLayout moreView = (LinearLayout) LayoutInflater.from(this).inflate(R.layout.item_marquee, null);
            //初始化布局的控件
            TextView tv1 = (TextView) moreView.findViewById(R.id.tv1);
            TextView tv2 = (TextView) moreView.findViewById(R.id.tv2);

            /**
             * 设置监听
             */
            moreView.findViewById(R.id.rl).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(StoreActivity.this, position + "你点击了" + data.get(position).toString(), Toast.LENGTH_SHORT).show();
                }
            });
            /**
             * 设置监听
             */
            moreView.findViewById(R.id.rl2).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(StoreActivity.this, position + "你点击了" + data.get(position + 1).toString(), Toast.LENGTH_SHORT).show();
                }
            });
            //进行对控件赋值
            tv1.setText(data.get(i).toString());
            if (data.size() > i + 1) {
                //因为淘宝那儿是两条数据，但是当数据是奇数时就不需要赋值第二个，所以加了一个判断，还应该把第二个布局给隐藏掉
                tv2.setText(data.get(i + 1).toString());
            } else {
                moreView.findViewById(R.id.rl2).setVisibility(View.GONE);
            }

            //添加到循环滚动数组里面去
            views.add(moreView);
        }
    }

    /**
     * 初始化假数据
     */
    private void initDataMarquee() {
        data = new ArrayList<>();
        data.add("家人给2岁孩子喝这个，孩子智力倒退10岁!!!");
        data.add("iPhone8最感人变化成真，必须买买买买!!!!");
        data.add("简直是白菜价！日本玩家33万甩卖15万张游戏王卡");
        data.add("iPhone8价格曝光了！看完感觉我的腰子有点疼...");
        data.add("主人内疚逃命时没带够，回废墟狂挖30小时！");
    }


    boolean isOpened = false;

    public void setListenerToRootView() {
        final View activityRootView = getWindow().getDecorView().findViewById(android.R.id.content);
        activityRootView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {

                int heightDiff = activityRootView.getRootView().getHeight() - activityRootView.getHeight();
                if (heightDiff > 100) { // 99% of the time the height diff will be due to a keyboard.
                    if (isOpened == false) {
                        //Do two things, make the view top visible and the editText smaller
                    }
                    isOpened = true;
                } else if (isOpened == true) {
                    isOpened = false;
                    final RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler);
                    recyclerView.getAdapter().notifyDataSetChanged();
                }
            }
        });
    }


    private void initID() {
        recyclerView = (RecyclerView) findViewById(R.id.recycler);
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe);

    }

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("Store Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        AppIndex.AppIndexApi.start(client, getIndexApiAction());
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.end(client, getIndexApiAction());
        client.disconnect();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        delegateAdapter.clear();
        adapters.clear();
        upMarqueeView.clearAnimation();
    }

    /**
     * 默认布局，如果需要重写
     */
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
            return new MainViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item, parent, false));
        }

        @Override
        public void onBindViewHolder(MainViewHolder holder, int position) {
            // only vertical
            holder.itemView.setLayoutParams(
                    new VirtualLayoutManager.LayoutParams(mLayoutParams));
        }


        @Override
        protected void onBindViewHolderWithOffset(MainViewHolder holder, int position, int offsetTotal) {
            // ((TextView) holder.itemView.findViewById(R.id.title)).setText(Integer.toString(offsetTotal));
        }

        @Override
        public int getItemCount() {
            return mCount;
        }
    }


    @Override
    // 设置回退
    // 覆盖Activity类的onKeyDown(int keyCoder,KeyEvent event)方法
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK) ) {
            finish();
            System.gc();

            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

}
