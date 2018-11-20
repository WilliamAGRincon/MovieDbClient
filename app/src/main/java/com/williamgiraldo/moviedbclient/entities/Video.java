package com.williamgiraldo.moviedbclient.entities;

import java.util.List;

public class Video {

    /**
     * id : 424694
     * results : [{"id":"5afbcaab925141075700373d","iso_639_1":"es","iso_3166_1":"ES","key":"BczK44FcMLA","name":"BOHEMIAN RHAPSODY - TEASER TRAILER","site":"YouTube","size":1080,"type":"Trailer"}]
     */

    private int id;
    private List<ResultsBean> results;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<ResultsBean> getResults() {
        return results;
    }

    public static class ResultsBean {
        /**
         * id : 5afbcaab925141075700373d
         * iso_639_1 : es
         * iso_3166_1 : ES
         * key : BczK44FcMLA
         * name : BOHEMIAN RHAPSODY - TEASER TRAILER
         * site : YouTube
         * size : 1080
         * type : Trailer
         */
        private String key;

        public String getKey() {
            return key;
        }
    }
}
