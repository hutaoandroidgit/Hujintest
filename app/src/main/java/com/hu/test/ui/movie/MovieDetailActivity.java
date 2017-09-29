package com.hu.test.ui.movie;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.renderscript.Allocation;
import android.renderscript.Element;
import android.renderscript.RenderScript;
import android.renderscript.ScriptIntrinsicBlur;
import android.support.v7.app.AppCompatActivity;
import android.transition.ArcMotion;
import android.transition.ChangeBounds;
import android.transition.TransitionValues;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.binaryresource.FileBinaryResource;
import com.facebook.cache.common.SimpleCacheKey;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.view.SimpleDraweeView;
import com.hu.test.R;
import com.hu.test.bean.JsonCallback;
import com.hu.test.bean.Movie250Modle;
import com.hu.test.bean.MovieDetailMoble;
import com.hu.test.utils.EasyTransition;
import com.hu.test.utils.FrescoUtils;
import com.hu.test.utils.UtilCom;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;

import java.io.File;

/**
 * Created by TT on 2017/9/21.
 */
public class MovieDetailActivity extends AppCompatActivity {

    private Movie250Modle.SubjectsBean subjectsBean;
    private SimpleDraweeView ivHead;
    private View view;
    private boolean finishEnter;
    private TextView tvORR;
    private TextView tvORN;
    private TextView tvOD;
    private TextView tvOC;
    private TextView tvOG;
    private TextView tvODt;
    private TextView tvOCity;
    private ImageView ivHeadBG;
    private Bitmap bitmap;
    private Bitmap blurImageAmeliorate;
    private TextView tvOneTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);
        if (getIntent() != null) {
            subjectsBean = (Movie250Modle.SubjectsBean) getIntent().getSerializableExtra("bean");
        }

        //找id
        initID();


        initGs();
        //转场动画
        initTs(savedInstanceState);

    }

    private Bitmap blur(Bitmap bitmap,float radius) {
        Bitmap output = Bitmap.createBitmap(bitmap); // 创建输出图片
        RenderScript rs = RenderScript.create(this); // 构建一个RenderScript对象
        ScriptIntrinsicBlur gaussianBlue = ScriptIntrinsicBlur.create(rs, Element.U8_4(rs)); // 创建高斯模糊脚本
        Allocation allIn = Allocation.createFromBitmap(rs, bitmap); // 创建用于输入的脚本类型
        Allocation allOut = Allocation.createFromBitmap(rs, output); // 创建用于输出的脚本类型
        gaussianBlue.setRadius(radius); // 设置模糊半径，范围0f<radius<=25f
        gaussianBlue.setInput(allIn); // 设置输入脚本类型
        gaussianBlue.forEach(allOut); // 执行高斯模糊算法，并将结果填入输出脚本类型中
        allOut.copyTo(output); // 将输出内存编码为Bitmap，图片大小必须注意
        rs.destroy(); // 关闭RenderScript对象，API>=23则使用rs.releaseAllContexts()
        return output;
    }

    private void initGs() {

        if (subjectsBean != null) {
            Bitmap bitmap = returnBitmap(Uri.parse(subjectsBean.getImages().getLarge()));
            if(bitmap == null){
                Log.e("TAG","bitmap为空");
                return;
            }
            blurImageAmeliorate = blur(bitmap, 25);
            blurImageAmeliorate = blur(blurImageAmeliorate, 25);
            blurImageAmeliorate = blur(blurImageAmeliorate, 25);
            blurImageAmeliorate = blur(blurImageAmeliorate, 25);
            blurImageAmeliorate = blur(blurImageAmeliorate, 25);
            blurImageAmeliorate = blur(blurImageAmeliorate, 25);
            blurImageAmeliorate = blur(blurImageAmeliorate, 25);
            if(blurImageAmeliorate == null){
                Log.e("TAG","blurImageAmeliorate为空");
            }
            ivHeadBG.setImageBitmap(blurImageAmeliorate);
        }
    }

    private Bitmap returnBitmap(Uri uri) {

        Bitmap bitmap = null;
        FileBinaryResource resource = (FileBinaryResource) Fresco.getImagePipelineFactory().getSmallImageFileCache().getResource(new SimpleCacheKey(uri.toString()));
        File file = resource.getFile();
        bitmap = BitmapFactory.decodeFile(file.getPath());
        return bitmap;

    }

    private void initTs(Bundle savedInstanceState) {
        setMotion(ivHead, true);
        // setMotion(setHeaderPicView(),false);


        FrescoUtils.setController(subjectsBean.getImages().getLarge(), ivHead);

        // if re-initialized, do not play any anim
        long transitionDuration = 800;
        if (null != savedInstanceState)
            transitionDuration = 0;
        // transition enter
        finishEnter = false;
        EasyTransition.enter(
                this,
                transitionDuration,
                new DecelerateInterpolator(),
                new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        // init other views after transition anim
                        finishEnter = true;

                    }
                });
    }

    @Override
    protected void onResume() {
        super.onResume();
        initData();
    }

    private void initData() {
        tvORR.setText("评分：" + subjectsBean.getRating().getAverage());

        OkGo.<MovieDetailMoble>get("Https://api.douban.com/v2/movie/subject/"+subjectsBean.getId())
                .execute(new JsonCallback<MovieDetailMoble>() {
                    @Override
                    public void onSuccess(Response<MovieDetailMoble> response) {
                        if (response.isSuccessful()) {
                            MovieDetailMoble movieDetailMoble = response.body();
                            tvOCity.setText("制片国家/地区：" + UtilCom.formatGenres(movieDetailMoble.getCountries()));
                            tvOD.setText("上映日期：" + movieDetailMoble.getYear());
                            tvODt.setText(UtilCom.formatName(subjectsBean.getDirectors()));
                            tvOC.setText(UtilCom.formatCastsName(subjectsBean.getCasts()));
                            tvOG.setText("类型："+UtilCom.formatGenres(subjectsBean.getGenres()));
                            tvOneTitle.setText(UtilCom.formatGenres(movieDetailMoble.getOriginal_title()));
                        } else {
                            response.getException();
                        }
                    }
                });
    }

    private void initID() {
        view = findViewById(R.id.include_movie);
        ivHead = (SimpleDraweeView) view.findViewById(R.id.iv_one_photo);
        tvORR = (TextView) view.findViewById(R.id.tv_one_rating_rate);
        tvORN = (TextView) view.findViewById(R.id.tv_one_rating_number);
        tvODt = (TextView) view.findViewById(R.id.tv_one_directors);
        tvOC = (TextView) view.findViewById(R.id.tv_one_casts);
        tvOG = (TextView) view.findViewById(R.id.tv_one_genres);
        tvOD = (TextView) view.findViewById(R.id.tv_one_day);
        tvOCity = (TextView) view.findViewById(R.id.tv_one_city);
        ivHeadBG = (ImageView) findViewById(R.id.iv_title_head_bg);
        tvOneTitle = (TextView) findViewById(R.id.tv_one_title);



    }

    /**
     * 设置头部header布局 左侧的图片(需要设置曲线路径切换动画时重写)
     */
    protected ImageView setHeaderPicView() {
        return new ImageView(this);
    }

    /**
     * 设置自定义 Shared Element切换动画
     * 默认不开启曲线路径切换动画，
     * 开启需要重写setHeaderPicView()，和调用此方法并将isShow值设为true
     *
     * @param imageView 共享的图片
     * @param isShow    是否显示曲线动画
     */
    protected void setMotion(ImageView imageView, boolean isShow) {
        if (!isShow) {
            return;
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            //定义ArcMotion
            ArcMotion arcMotion = new ArcMotion();
            arcMotion.setMinimumHorizontalAngle(50f);
            arcMotion.setMinimumVerticalAngle(50f);
            //插值器，控制速度
            Interpolator interpolator = AnimationUtils.loadInterpolator(this, android.R.interpolator.fast_out_slow_in);

            //实例化自定义的ChangeBounds
            CustomChangeBounds changeBounds = new CustomChangeBounds();
            changeBounds.setPathMotion(arcMotion);
            changeBounds.setInterpolator(interpolator);
            changeBounds.addTarget(imageView);
            //将切换动画应用到当前的Activity的进入和返回
            getWindow().setSharedElementEnterTransition(changeBounds);
            getWindow().setSharedElementReturnTransition(changeBounds);
        }
    }

    class CustomChangeBounds extends ChangeBounds {

        @Override
        public Animator createAnimator(final ViewGroup sceneRoot,
                                       TransitionValues startValues,
                                       final TransitionValues endValues) {

            Animator changeBounds = super.createAnimator(sceneRoot, startValues, endValues);
            if (startValues == null || endValues == null || changeBounds == null)
                return null;

//        if (endValues.view instanceof ViewGroup) {
//            ViewGroup vg = (ViewGroup) endValues.view;
//            float offset = vg.getHeight() / 3;
//            for (int i = 0; i < vg.getChildCount(); i++) {
//                View v = vg.getChildAt(i);
//                v.setTranslationY(offset);
//                v.setAlpha(0f);
//                v.animate()
//                        .alpha(1f)
//                        .translationY(0f)
//                        .setDuration(150)
//                        .setStartDelay(150)
//                        .setInterpolator(AnimationUtils.loadInterpolator(vg.getContext(),
//                                android.R.interpolator.fast_out_slow_in));
//                offset *= 1.8f;
//            }
//        }

            changeBounds.setDuration(500);
            changeBounds.setInterpolator(AnimationUtils.loadInterpolator(sceneRoot.getContext(),
                    android.R.interpolator.fast_out_slow_in));
            return changeBounds;
        }

    }
}
