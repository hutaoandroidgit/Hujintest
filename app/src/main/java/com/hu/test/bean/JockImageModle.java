package com.hu.test.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by TT on 2017/9/6.
 */

public class JockImageModle implements Serializable {

    /**
     * error_code : 0
     * reason : Success
     * result : {"data":[{"content":"这是STEAM的规矩！","hashId":"A9A6ABA505CC2C59C5ADB5433F9F6A0C","unixtime":1494385423,"updatetime":"2017-05-10 11:03:43","url":"http://juheimg.oss-cn-hangzhou.aliyuncs.com/joke/201705/10/A9A6ABA505CC2C59C5ADB5433F9F6A0C.png"},{"content":"一点职业道德都没有","hashId":"779F3D2AC8924E6567EF3D1082F67583","unixtime":1494385423,"updatetime":"2017-05-10 11:03:43","url":"http://juheimg.oss-cn-hangzhou.aliyuncs.com/joke/201705/10/779F3D2AC8924E6567EF3D1082F67583.jpg"},{"content":"养狗你可以遛狗，但是养猫\u2026\u2026\u2026","hashId":"D9725EC3275A989796001E5C3432BC8D","unixtime":1494385423,"updatetime":"2017-05-10 11:03:43","url":"http://juheimg.oss-cn-hangzhou.aliyuncs.com/joke/201705/10/D9725EC3275A989796001E5C3432BC8D.gif"},{"content":"太不公平","hashId":"02571AD2D58648B315F320F85FCE8F3D","unixtime":1494385423,"updatetime":"2017-05-10 11:03:43","url":"http://juheimg.oss-cn-hangzhou.aliyuncs.com/joke/201705/10/02571AD2D58648B315F320F85FCE8F3D.jpg"},{"content":"被这样无辜的眼神看着，都不敢把喵喵弄疼了","hashId":"AFFE901413FC00E1DB0F97A6966ED34F","unixtime":1494385423,"updatetime":"2017-05-10 11:03:43","url":"http://juheimg.oss-cn-hangzhou.aliyuncs.com/joke/201705/10/AFFE901413FC00E1DB0F97A6966ED34F.jpg"}]}
     */

    private int error_code;
    private String reason;
    private ResultBean result;

    public int getError_code() {
        return error_code;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public static class ResultBean {
        private List<DataBean> data;

        public List<DataBean> getData() {
            return data;
        }

        public void setData(List<DataBean> data) {
            this.data = data;
        }

        public static class DataBean {
            /**
             * content : 这是STEAM的规矩！
             * hashId : A9A6ABA505CC2C59C5ADB5433F9F6A0C
             * unixtime : 1494385423
             * updatetime : 2017-05-10 11:03:43
             * url : http://juheimg.oss-cn-hangzhou.aliyuncs.com/joke/201705/10/A9A6ABA505CC2C59C5ADB5433F9F6A0C.png
             */

            private String content;
            private String hashId;
            private int unixtime;
            private String updatetime;
            private String url;

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public String getHashId() {
                return hashId;
            }

            public void setHashId(String hashId) {
                this.hashId = hashId;
            }

            public int getUnixtime() {
                return unixtime;
            }

            public void setUnixtime(int unixtime) {
                this.unixtime = unixtime;
            }

            public String getUpdatetime() {
                return updatetime;
            }

            public void setUpdatetime(String updatetime) {
                this.updatetime = updatetime;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }
        }
    }
}
