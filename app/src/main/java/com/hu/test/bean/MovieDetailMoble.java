package com.hu.test.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by TT on 2017/9/28.
 */

public class MovieDetailMoble implements Serializable {
    /**
     * rating : {"max":10,"average":0,"stars":"00","min":0}
     * reviews_count : 48
     * wish_count : 13139
     * douban_site :
     * year : 2017
     * images : {"small":"https://img1.doubanio.com/view/movie_poster_cover/ipst/public/p2499793218.jpg","large":"https://img1.doubanio.com/view/movie_poster_cover/lpst/public/p2499793218.jpg","medium":"https://img1.doubanio.com/view/movie_poster_cover/spst/public/p2499793218.jpg"}
     * alt : https://movie.douban.com/subject/27038183/
     * id : 27038183
     * mobile_url : https://movie.douban.com/subject/27038183/mobile
     * title : 羞羞的铁拳
     * do_count : null
     * share_url : https://m.douban.com/movie/subject/27038183
     * seasons_count : null
     * schedule_url : https://movie.douban.com/subject/27038183/cinema/
     * episodes_count : null
     * countries : ["中国大陆"]
     * genres : ["喜剧"]
     * collect_count : 641
     * casts : [{"alt":"https://movie.douban.com/celebrity/1350408/","avatars":{"small":"https://img3.doubanio.com/img/celebrity/small/1437031126.82.jpg","large":"https://img3.doubanio.com/img/celebrity/large/1437031126.82.jpg","medium":"https://img3.doubanio.com/img/celebrity/medium/1437031126.82.jpg"},"name":"艾伦","id":"1350408"},{"alt":"https://movie.douban.com/celebrity/1319032/","avatars":{"small":"https://img3.doubanio.com/img/celebrity/small/1444800807.11.jpg","large":"https://img3.doubanio.com/img/celebrity/large/1444800807.11.jpg","medium":"https://img3.doubanio.com/img/celebrity/medium/1444800807.11.jpg"},"name":"马丽","id":"1319032"},{"alt":"https://movie.douban.com/celebrity/1325700/","avatars":{"small":"https://img1.doubanio.com/img/celebrity/small/1356510694.28.jpg","large":"https://img1.doubanio.com/img/celebrity/large/1356510694.28.jpg","medium":"https://img1.doubanio.com/img/celebrity/medium/1356510694.28.jpg"},"name":"沈腾","id":"1325700"},{"alt":"https://movie.douban.com/celebrity/1316331/","avatars":{"small":"https://img3.doubanio.com/img/celebrity/small/50212.jpg","large":"https://img3.doubanio.com/img/celebrity/large/50212.jpg","medium":"https://img3.doubanio.com/img/celebrity/medium/50212.jpg"},"name":"田雨","id":"1316331"}]
     * current_season : null
     * original_title : 羞羞的铁拳
     * summary : 靠打假拳混日子的艾迪生（艾伦 饰），本来和正义感十足的体育记者马小（马丽 饰）是一对冤家，没想到因为一场意外的电击，男女身体互换。性别错乱后，两人互坑互害，引发了拳坛的大地震，也揭开了假拳界的秘密，惹来一堆麻烦，最终两人在“卷莲门”副掌门张茱萸（沈腾 饰）的指点下，向恶势力挥起了羞羞的铁拳。
     * subtype : movie
     * directors : [{"alt":"https://movie.douban.com/celebrity/1350407/","avatars":{"small":"https://img3.doubanio.com/img/celebrity/small/1437031175.04.jpg","large":"https://img3.doubanio.com/img/celebrity/large/1437031175.04.jpg","medium":"https://img3.doubanio.com/img/celebrity/medium/1437031175.04.jpg"},"name":"宋阳","id":"1350407"},{"alt":"https://movie.douban.com/celebrity/1381643/","avatars":{"small":"https://img3.doubanio.com/img/celebrity/small/1506162881.51.jpg","large":"https://img3.doubanio.com/img/celebrity/large/1506162881.51.jpg","medium":"https://img3.doubanio.com/img/celebrity/medium/1506162881.51.jpg"},"name":"张吃鱼","id":"1381643"}]
     * comments_count : 432
     */

    private RatingBean rating;
    private int reviews_count;
    private int wish_count;
    private String douban_site;
    private String year;
    private ImagesBean images;
    private String alt;
    private String id;
    private String mobile_url;
    private String title;
    private Object do_count;
    private String share_url;
    private Object seasons_count;
    private String schedule_url;
    private Object episodes_count;
    private int collect_count;
    private Object current_season;
    private String original_title;
    private String summary;
    private String subtype;
    private int comments_count;
    private List<String> countries;
    private List<String> genres;
    private List<CastsBean> casts;
    private List<DirectorsBean> directors;

    public RatingBean getRating() {
        return rating;
    }

    public void setRating(RatingBean rating) {
        this.rating = rating;
    }

    public int getReviews_count() {
        return reviews_count;
    }

    public void setReviews_count(int reviews_count) {
        this.reviews_count = reviews_count;
    }

    public int getWish_count() {
        return wish_count;
    }

    public void setWish_count(int wish_count) {
        this.wish_count = wish_count;
    }

    public String getDouban_site() {
        return douban_site;
    }

    public void setDouban_site(String douban_site) {
        this.douban_site = douban_site;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public ImagesBean getImages() {
        return images;
    }

    public void setImages(ImagesBean images) {
        this.images = images;
    }

    public String getAlt() {
        return alt;
    }

    public void setAlt(String alt) {
        this.alt = alt;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMobile_url() {
        return mobile_url;
    }

    public void setMobile_url(String mobile_url) {
        this.mobile_url = mobile_url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Object getDo_count() {
        return do_count;
    }

    public void setDo_count(Object do_count) {
        this.do_count = do_count;
    }

    public String getShare_url() {
        return share_url;
    }

    public void setShare_url(String share_url) {
        this.share_url = share_url;
    }

    public Object getSeasons_count() {
        return seasons_count;
    }

    public void setSeasons_count(Object seasons_count) {
        this.seasons_count = seasons_count;
    }

    public String getSchedule_url() {
        return schedule_url;
    }

    public void setSchedule_url(String schedule_url) {
        this.schedule_url = schedule_url;
    }

    public Object getEpisodes_count() {
        return episodes_count;
    }

    public void setEpisodes_count(Object episodes_count) {
        this.episodes_count = episodes_count;
    }

    public int getCollect_count() {
        return collect_count;
    }

    public void setCollect_count(int collect_count) {
        this.collect_count = collect_count;
    }

    public Object getCurrent_season() {
        return current_season;
    }

    public void setCurrent_season(Object current_season) {
        this.current_season = current_season;
    }

    public String getOriginal_title() {
        return original_title;
    }

    public void setOriginal_title(String original_title) {
        this.original_title = original_title;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getSubtype() {
        return subtype;
    }

    public void setSubtype(String subtype) {
        this.subtype = subtype;
    }

    public int getComments_count() {
        return comments_count;
    }

    public void setComments_count(int comments_count) {
        this.comments_count = comments_count;
    }

    public List<String> getCountries() {
        return countries;
    }

    public void setCountries(List<String> countries) {
        this.countries = countries;
    }

    public List<String> getGenres() {
        return genres;
    }

    public void setGenres(List<String> genres) {
        this.genres = genres;
    }

    public List<CastsBean> getCasts() {
        return casts;
    }

    public void setCasts(List<CastsBean> casts) {
        this.casts = casts;
    }

    public List<DirectorsBean> getDirectors() {
        return directors;
    }

    public void setDirectors(List<DirectorsBean> directors) {
        this.directors = directors;
    }

    public static class RatingBean {
        /**
         * max : 10
         * average : 0
         * stars : 00
         * min : 0
         */

        private int max;
        private int average;
        private String stars;
        private int min;

        public int getMax() {
            return max;
        }

        public void setMax(int max) {
            this.max = max;
        }

        public int getAverage() {
            return average;
        }

        public void setAverage(int average) {
            this.average = average;
        }

        public String getStars() {
            return stars;
        }

        public void setStars(String stars) {
            this.stars = stars;
        }

        public int getMin() {
            return min;
        }

        public void setMin(int min) {
            this.min = min;
        }
    }

    public static class ImagesBean {
        /**
         * small : https://img1.doubanio.com/view/movie_poster_cover/ipst/public/p2499793218.jpg
         * large : https://img1.doubanio.com/view/movie_poster_cover/lpst/public/p2499793218.jpg
         * medium : https://img1.doubanio.com/view/movie_poster_cover/spst/public/p2499793218.jpg
         */

        private String small;
        private String large;
        private String medium;

        public String getSmall() {
            return small;
        }

        public void setSmall(String small) {
            this.small = small;
        }

        public String getLarge() {
            return large;
        }

        public void setLarge(String large) {
            this.large = large;
        }

        public String getMedium() {
            return medium;
        }

        public void setMedium(String medium) {
            this.medium = medium;
        }
    }

    public static class CastsBean {
        /**
         * alt : https://movie.douban.com/celebrity/1350408/
         * avatars : {"small":"https://img3.doubanio.com/img/celebrity/small/1437031126.82.jpg","large":"https://img3.doubanio.com/img/celebrity/large/1437031126.82.jpg","medium":"https://img3.doubanio.com/img/celebrity/medium/1437031126.82.jpg"}
         * name : 艾伦
         * id : 1350408
         */

        private String alt;
        private AvatarsBean avatars;
        private String name;
        private String id;

        public String getAlt() {
            return alt;
        }

        public void setAlt(String alt) {
            this.alt = alt;
        }

        public AvatarsBean getAvatars() {
            return avatars;
        }

        public void setAvatars(AvatarsBean avatars) {
            this.avatars = avatars;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public static class AvatarsBean {
            /**
             * small : https://img3.doubanio.com/img/celebrity/small/1437031126.82.jpg
             * large : https://img3.doubanio.com/img/celebrity/large/1437031126.82.jpg
             * medium : https://img3.doubanio.com/img/celebrity/medium/1437031126.82.jpg
             */

            private String small;
            private String large;
            private String medium;

            public String getSmall() {
                return small;
            }

            public void setSmall(String small) {
                this.small = small;
            }

            public String getLarge() {
                return large;
            }

            public void setLarge(String large) {
                this.large = large;
            }

            public String getMedium() {
                return medium;
            }

            public void setMedium(String medium) {
                this.medium = medium;
            }
        }
    }

    public static class DirectorsBean {
        /**
         * alt : https://movie.douban.com/celebrity/1350407/
         * avatars : {"small":"https://img3.doubanio.com/img/celebrity/small/1437031175.04.jpg","large":"https://img3.doubanio.com/img/celebrity/large/1437031175.04.jpg","medium":"https://img3.doubanio.com/img/celebrity/medium/1437031175.04.jpg"}
         * name : 宋阳
         * id : 1350407
         */

        private String alt;
        private AvatarsBeanX avatars;
        private String name;
        private String id;

        public String getAlt() {
            return alt;
        }

        public void setAlt(String alt) {
            this.alt = alt;
        }

        public AvatarsBeanX getAvatars() {
            return avatars;
        }

        public void setAvatars(AvatarsBeanX avatars) {
            this.avatars = avatars;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public static class AvatarsBeanX {
            /**
             * small : https://img3.doubanio.com/img/celebrity/small/1437031175.04.jpg
             * large : https://img3.doubanio.com/img/celebrity/large/1437031175.04.jpg
             * medium : https://img3.doubanio.com/img/celebrity/medium/1437031175.04.jpg
             */

            private String small;
            private String large;
            private String medium;

            public String getSmall() {
                return small;
            }

            public void setSmall(String small) {
                this.small = small;
            }

            public String getLarge() {
                return large;
            }

            public void setLarge(String large) {
                this.large = large;
            }

            public String getMedium() {
                return medium;
            }

            public void setMedium(String medium) {
                this.medium = medium;
            }
        }
    }
}
