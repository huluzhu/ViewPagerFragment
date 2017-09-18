package com.baway.hujiqiang.bean;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

import java.util.List;



public class Data {

    public long code;
    public String msg;
    @SerializedName("[]")
    public List<ZwlBean> zwl;

    public static class ZwlBean {

        public MomentBean Moment;
        public UserBean User;
        @SerializedName("Comment[]")
        public List<CommentzwlBean> Commentzwl;

        public static class MomentBean {
            public long id;
            public long userId;
            public String date;
            public String content;
            public List<Long> praiseUserIdList;
            public List<String> pictureList;
        }

        public static class UserBean {
            public long id;
            public String name;
            public String head;
        }

        public static class CommentzwlBean {

            public long id;
            public long toId;
            public long userId;
            public long momentId;
            public String date;
            public String content;

            public static CommentzwlBean objectFromData(String str) {
                return new Gson().fromJson(str, CommentzwlBean.class);
            }
        }

        public static class Comment {
            public long id;
            public long toId;
            public long userId;
            public long momentId;
            public String date;
            public String content;
        }
    }
}
