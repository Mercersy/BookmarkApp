package com.yangsong.bookmarkApp.dao;

import com.yangsong.bookmarkApp.DataStore;
import com.yangsong.bookmarkApp.entities.Bookmark;
import com.yangsong.bookmarkApp.entities.UserBookmark;

public class BookmarkDao {
    public Bookmark[][] getBookmarks() {
        return DataStore.getBookmarks();
    }

    public void saveUserBookmark(UserBookmark userBookmark) {
        DataStore.add(userBookmark);
    }
}
