package com.yangsong.bookmarkApp.dao;

import com.yangsong.bookmarkApp.DataStore;
import com.yangsong.bookmarkApp.entities.User;

import java.util.List;

public class UserDao {
    public List<User> getUsers() {
        return DataStore.getUsers();
    }
}
