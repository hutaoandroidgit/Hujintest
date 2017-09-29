package com.hu.test.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by TT on 2017/9/7.
 */

public class GankDataModle implements Serializable {
    /**
     * error : false
     * results : [{"_id":"5996c673421aa96729c57262","createdAt":"2017-08-18T18:50:27.254Z","desc":"UiAutomator2.0升级填坑记","publishedAt":"2017-09-07T13:25:26.977Z","source":"web","type":"Android","url":"http://skyseraph.com/2017/06/04/Android/UiAutomator2.0%E5%8D%87%E7%BA%A7%E5%A1%AB%E5%9D%91%E8%AE%B0/","used":true,"who":"SkySeraph"},{"_id":"59a37311421aa901bcb7dbb2","createdAt":"2017-08-28T09:34:09.768Z","desc":"Scrum：官僚者们的游戏","publishedAt":"2017-09-07T13:25:26.977Z","source":"web","type":"Android","url":"https://mp.weixin.qq.com/s?__biz=MzIwMzYwMTk1NA==&mid=2247486650&idx=1&sn=9c6e2e0fd5780aab2bcb02b823e7316e","used":true,"who":"陈宇明"},{"_id":"59b09f31421aa901c1c0a8ec","createdAt":"2017-09-07T09:21:53.130Z","desc":"关于TCP/IP，必知必会的十个问题","publishedAt":"2017-09-07T13:25:26.977Z","source":"web","type":"Android","url":"https://mp.weixin.qq.com/s?__biz=MzIwMzYwMTk1NA==&mid=2247486825&idx=1&sn=db596ed4b69bd9220f6a8ad79ef308db","used":true,"who":"陈宇明"},{"_id":"599e81e1421aa901bcb7db9c","createdAt":"2017-08-24T15:36:01.827Z","desc":"召唤，光能使者--玩转PathMeasure","images":["http://img.gank.io/f2ab16f6-68f7-4030-adcb-d2cccced9c9f"],"publishedAt":"2017-09-06T12:18:11.687Z","source":"web","type":"Android","url":"http://www.jianshu.com/p/4bb16cefca23","used":true,"who":"Vivian"},{"_id":"59a52bce421aa901c1c0a8a2","createdAt":"2017-08-29T16:54:38.770Z","desc":"QMUI 团队出品，一个致力于提高 Android 项目 UI 开发效率的解决方案","publishedAt":"2017-09-06T12:18:11.687Z","source":"web","type":"Android","url":"https://github.com/QMUI/QMUI_Android","used":true,"who":"chanthuang"},{"_id":"59aa81cf421aa901c1c0a8d1","createdAt":"2017-09-02T18:02:55.207Z","desc":"Android Google ARCore尝鲜记录","publishedAt":"2017-09-06T12:18:11.687Z","source":"web","type":"Android","url":"https://mp.weixin.qq.com/s/BwjyJwUJKZSSaaKbzRiT8Q","used":true,"who":"D_clock"},{"_id":"59af4fa6421aa901c85e601f","createdAt":"2017-09-06T09:30:14.786Z","desc":"Android彻底组件化源码分析","publishedAt":"2017-09-06T12:18:11.687Z","source":"web","type":"Android","url":"https://mp.weixin.qq.com/s?__biz=MzIwMzYwMTk1NA==&mid=2247486804&idx=1&sn=664b30f6bdbd55f3b8be706a9a4fe092","used":true,"who":"陈宇明"},{"_id":"59af63a8421aa901c85e6020","createdAt":"2017-09-06T10:55:36.65Z","desc":"一行代码实现软键盘与EditText的交互","images":["http://img.gank.io/ea3059f0-23b7-4cf9-be74-82d9d995107a"],"publishedAt":"2017-09-06T12:18:11.687Z","source":"web","type":"Android","url":"https://github.com/zybieku/SoftKeyboardUtil","used":true,"who":"朱能权"},{"_id":"59ac06be421aa901c85e6006","createdAt":"2017-09-03T21:42:22.920Z","desc":"如何自己实现一个 EventBus","publishedAt":"2017-09-05T11:29:05.240Z","source":"web","type":"Android","url":"https://github.com/Werb/EventBusKotlin/wiki/%E5%A6%82%E4%BD%95%E8%87%AA%E5%B7%B1%E5%AE%9E%E7%8E%B0%E4%B8%80%E4%B8%AA-EventBus","used":true,"who":"Werb"},{"_id":"59aca1b1421aa901bcb7dbf5","createdAt":"2017-09-04T08:43:29.888Z","desc":"自定义 View：用贝塞尔曲线绘制酷炫轮廓背景","publishedAt":"2017-09-05T11:29:05.240Z","source":"web","type":"Android","url":"http://mp.weixin.qq.com/s/SzZuiRMz8QWzNqCjq2gI_A","used":true,"who":null}]
     */

    private boolean error;
    private List<ResultsBean> results;

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public List<ResultsBean> getResults() {
        return results;
    }

    public void setResults(List<ResultsBean> results) {
        this.results = results;
    }

    public static class ResultsBean implements Serializable{
        /**
         * _id : 5996c673421aa96729c57262
         * createdAt : 2017-08-18T18:50:27.254Z
         * desc : UiAutomator2.0升级填坑记
         * publishedAt : 2017-09-07T13:25:26.977Z
         * source : web
         * type : Android
         * url : http://skyseraph.com/2017/06/04/Android/UiAutomator2.0%E5%8D%87%E7%BA%A7%E5%A1%AB%E5%9D%91%E8%AE%B0/
         * used : true
         * who : SkySeraph
         * images : ["http://img.gank.io/f2ab16f6-68f7-4030-adcb-d2cccced9c9f"]
         */

        private String _id;
        private String createdAt;
        private String desc;
        private String publishedAt;
        private String source;
        private String type;
        private String url;
        private boolean used;
        private String who;
        private List<String> images;

        public String get_id() {
            return _id;
        }

        public void set_id(String _id) {
            this._id = _id;
        }

        public String getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(String createdAt) {
            this.createdAt = createdAt;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public String getPublishedAt() {
            return publishedAt;
        }

        public void setPublishedAt(String publishedAt) {
            this.publishedAt = publishedAt;
        }

        public String getSource() {
            return source;
        }

        public void setSource(String source) {
            this.source = source;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public boolean isUsed() {
            return used;
        }

        public void setUsed(boolean used) {
            this.used = used;
        }

        public String getWho() {
            return who;
        }

        public void setWho(String who) {
            this.who = who;
        }

        public List<String> getImages() {
            return images;
        }

        public void setImages(List<String> images) {
            this.images = images;
        }
    }
}
