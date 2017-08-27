package com.yangsong.bookmarkApp.controllers;

import com.yangsong.bookmarkApp.entities.Bookmark;
import com.yangsong.bookmarkApp.entities.User;
import com.yangsong.bookmarkApp.managers.BookmarkManager;

public class BookmarkController {
    private static BookmarkController instance = new BookmarkController();
    private BookmarkController() {}
    public static BookmarkController getInstance() {
        return instance;
    }

    public void saveUserBookmark(User user, Bookmark bookmark) {
        BookmarkManager.getInstance().saveUserBookmark(user, bookmark);

    }
}
