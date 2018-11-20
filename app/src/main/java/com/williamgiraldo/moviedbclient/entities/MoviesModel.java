package com.williamgiraldo.moviedbclient.entities;

import java.util.List;

public class MoviesModel {
    private List<ResultsBean> results;

    public List<ResultsBean> getResults() {
        return results;
    }

    public static class ResultsBean {
        /**
         * vote_count : 1898
         * id : 19404
         * video : false
         * vote_average : 9.2
         * title : Un amor contra viento y marea
         * popularity : 18.433
         * poster_path : /uC6TTUhPpQCmgldGyYveKRAu8JN.jpg
         * original_language : hi
         * original_title : दिलवाले दुल्हनिया ले जायेंगे
         * genre_ids : [35,18,10749]
         * backdrop_path : /nl79FQ8xWZkhL3rDr1v2RFFR6J0.jpg
         * adult : false
         * overview : Los Singh son una familia india con grandes convicciones culturales de su nación de origen, que emigraron a Reino Unido antes de nacer sus primeros hijos. Uno de ellos querrá casarse con una mujer ajena a su cultura y para ello deberá hacer todos los esfuerzos por convencer a su familia.
         * release_date : 1995-10-20
         */
        private int id;
        private String title;
        private String poster_path;
        private String backdrop_path;
        private String overview;
        private String release_date;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getPoster_path() {
            return poster_path;
        }

        public String getBackdrop_path() {
            return backdrop_path;
        }

        public String getOverview() {
            return overview;
        }

        public String getRelease_date() {
            return release_date;
        }

        public void setRelease_date(String release_date) {
            this.release_date = release_date;
        }
    }
}
