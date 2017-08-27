package com.yangsong.bookmarkApp.dao;

import com.yangsong.bookmarkApp.DataStore;
import com.yangsong.bookmarkApp.entities.User;

public class UserDao {
    public User[] getUsers() {
        return DataStore.getUsers();
    }
}
