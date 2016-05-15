package com.wusui.mediaplay.model;

import java.util.List;

/**
 * Created by fg on 2016/5/14.
 */
public class Status {
    private String showapi_res_code,showapi_res_error,showapi_res_body,pagebean,
            cur_song_num;
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
    public String getcur_song_num(){
        return cur_song_num;
    }
    public void setcur_song_num(String cur_song_num){
        this.cur_song_num = cur_song_num;
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
                pagebean + ",cur_song_num=" + cur_song_num + ",mSonglists=" + mSonglists + "]";
    }

    public class songlist {
        private String albumid;
        private String albummid;
        private String albumpic_big;
        private String albumpic_small;
        private String downUrl;
        private String url;
        private String singername;
        private String songname;
        private String seconds;
        private String singerid;
        private String songid;

        public String getSongid() {
            return songid;
        }

        public void setSongid(String songid) {
            this.songid = songid;
        }

        public String getAlbummid() {
            return albummid;
        }

        public String getSingerid() {
            return singerid;
        }

        public void setAlbummid(String albummid) {
            this.albummid = albummid;
        }

        public void setSingerid(String singerid) {
            this.singerid = singerid;
        }

        public String getAlbumid() {
            return albumid;
        }

        public void setAlbumid(String albumid) {
            this.albumid = albumid;
        }

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

        @Override
        public String toString() {
            return "songlist [albumid="+albumid+",albummid="+albummid+",albumpic_big="+
                    albumpic_big+",albumpic_small="+albumpic_small+",downUrl="+downUrl+
                    ",seconds="+seconds+",singerid="+singerid+",singername="+singername+
                    ",songid="+songid+",songname="+songname+",url="+url+"]";
        }
    }
}
