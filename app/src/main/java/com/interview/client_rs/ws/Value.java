package com.interview.client_rs.ws;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Value {

    @SerializedName("userId")
    @Expose
    private String userId;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("jndiName")
    @Expose
    private String jndiName;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("category")
    @Expose
    private String category;
    @SerializedName("url")
    @Expose
    private String url;
    @SerializedName("nickname")
    @Expose
    private String nickname;
    @SerializedName("password")
    @Expose
    private String password;
    @SerializedName("timeout")
    @Expose
    private String timeout;
    @SerializedName("maxConnects")
    @Expose
    private String maxConnects;
    @SerializedName("minConnects")
    @Expose
    private String minConnects;

    static final public int maxIndex = 10;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getJndiName() {
        return jndiName;
    }

    public void setJndiName(String jndiName) {
        this.jndiName = jndiName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTimeout() {
        return timeout;
    }

    public void setTimeout(String timeout) {
        this.timeout = timeout;
    }

    public String getMaxConnects() {
        return maxConnects;
    }

    public void setMaxConnects(String maxConnects) {
        this.maxConnects = maxConnects;
    }

    public String getMixConnects() {
        return minConnects;
    }

    public void setMixConnects(String mixConnects) {
        this.minConnects = mixConnects;
    }

    public String getFieldValue(int index) {
        switch (index){
            case 0:
                return userId;
            case 1:
                return name;
            case 2:
                return jndiName;
            case 3:
                return description;
            case 4:
                return category;
            case 5:
                return url;
            case 6:
                return nickname;
            case 7:
                return password;
            case 8:
                return timeout;
            case 9:
                return maxConnects;
            case 10:
                return minConnects;
            default:
                return null;
        }
    }

    static public String getFieldName(int index) {
        switch (index){
            case 0:
                return "Id";
            case 1:
                return "Name";
            case 2:
                return "Jndi";
            case 3:
                return "Description";
            case 4:
                return "Category";
            case 5:
                return "Url";
            case 6:
                return "Nick";
            case 7:
                return "Pass";
            case 8:
                return "Timeout";
            case 9:
                return "Max";
            case 10:
                return "Min";
            default:
                return null;
        }
    }

    @Override
    public String toString() {
        return "| "+userId+" | "+name+" | "+jndiName+" | "+description+" | "+category+" | "+url+" | "+nickname+" | "+password+" | "+timeout+" | "+maxConnects+" | "+minConnects+" |";
    }
}
