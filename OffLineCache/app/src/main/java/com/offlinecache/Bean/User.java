package com.offlinecache.Bean;

import java.util.List;

/**
 * Created by fang on 2016/7/21.
 */
public class User {
    String username;
    String password;
    List<String> links;

    public User() {
    }

    public User(String username, String password, List<String> links) {
        this.username = username;
        this.password = password;
        this.links = links;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<String> getLinks() {
        return links;
    }

    public void setLinks(List<String> links) {
        this.links = links;
    }
}
