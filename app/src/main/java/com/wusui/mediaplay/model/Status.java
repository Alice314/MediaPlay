package com.wusui.mediaplay.model;

import java.util.List;

/**
 * Created by fg on 2016/5/14.
 */
public class Status {
    private String showapi_res_code,showapi_res_error,showapi_res_body,pagebean,
            song_begin;
    private List<songlist>mSonglists;

    public String getShowapi_res_code(){
        return showapi_res_code;
    }
    public void setShowapi_res_code(String showapi_res_code){
        this.showapi_res_code = showapi_res_code;
    }
    public String getshowapi_res_error(){
        return showapi_res_error;
    }
    public void setshowapi_res_error(String showapi_res_error){
        this.showapi_res_error = showapi_res_error;
    }
    public String getshowapi_res_body(){
        return showapi_res_body;
    }
    public void setshowapi_res_body(String showapi_res_body){
        this.showapi_res_body = showapi_res_body;
    }
    public String getpagebean(){
        return pagebean;
    }
    public void setpagebean(String pagebean){
        this.pagebean = pagebean;
    }
    public String getsong_begin(){
        return song_begin;
    }
    public void setsong_begin(String song_begin){
        this.song_begin = song_begin;
    }
    public List<songlist>getSonglists(){
        return mSonglists;
    }
    public void setSonglists(List<songlist>mSonglists){
        this.mSonglists = mSonglists;
    }

    @Override
    public String toString() {
        return "Status [showapi_res_code=" + showapi_res_code+",showapi_res_error=" +
                showapi_res_error + ",showapi_res_body=" + showapi_res_body + ",pagebean=" +
                pagebean + ",song_begin=" + song_begin + ",mSonglists=" + mSonglists + "]";
    }

    public class songlist {
        private String albumid,albummid,singerid,songid;
        private String albumpic_big;
        private String albumpic_small;
        private String downUrl;
        private String url;
        private String singername;
        private String songname;
        private String seconds;


        public String getAlbumpic_big() {
            return albumpic_big;
        }

        public void setAlbumpic_big(String albumpic_big) {
            this.albumpic_big = albumpic_big;
        }

        public String getAlbumpic_small() {
            return albumpic_small;
        }

        public void setAlbumpic_small(String albumpic_small) {
            this.albumpic_small = albumpic_small;
        }

        public String getDownUrl() {
            return downUrl;
        }

        public void setDownUrl(String downUrl) {
            this.downUrl = downUrl;
        }

        public String getSeconds() {
            return seconds;
        }

        public void setSeconds(String seconds) {
            this.seconds = seconds;
        }

        public String getSingername() {
            return singername;
        }

        public void setSingername(String singername) {
            this.singername = singername;
        }

        public String getSongname() {
            return songname;
        }

        public void setSongname(String songname) {
            this.songname = songname;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getAlbumid() {
            return albumid;
        }

        public String getAlbummid() {
            return albummid;
        }

        public String getSingerid() {
            return singerid;
        }

        public String getSongid() {
            return songid;
        }

        public void setAlbumid(String albumid) {
            this.albumid = albumid;
        }

        public void setAlbummid(String albummid) {
            this.albummid = albummid;
        }

        public void setSingerid(String singerid) {
            this.singerid = singerid;
        }

        public void setSongid(String songid) {
            this.songid = songid;
        }


        @Override
        public String toString() {
            return "songlist [_albumid=" + albumid +",albumid="+albumid+",albumpic_big="
                    + albumpic_big+",albumpic_small="+albumpic_small+",downUrl="+
                    downUrl+",seconds="+seconds+",singerid="+singerid+",singername="+
                    singername+",songid="+songid+",songname="+songname+",url="+url+"]";
        }
    }
}
