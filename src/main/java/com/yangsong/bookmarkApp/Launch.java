package com.yangsong.bookmarkApp;

import com.yangsong.bookmarkApp.entities.Bookmark;
import com.yangsong.bookmarkApp.entities.User;
import com.yangsong.bookmarkApp.managers.BookmarkManager;
import com.yangsong.bookmarkApp.managers.UserManager;

public class Launch {
    private static User[] users;
    private static Bookmark[][] bookmarks;

    private static void loadData() {
        System.out.println("1. Loading data ...");
        DataStore.loadData();

        users = UserManager.getInstance().getUsers();
        bookmarks = BookmarkManager.getInstance().getBookmarks();

//        System.out.println("Printing data ...");
//        printUserData();
//        printBookmarkData();
    }

    private static void printUserData() {
        for(User user : users) {
            System.out.println(user);
        }
    }

    private static void printBookmarkData() {
        for(Bookmark[] bookmarksList : bookmarks) {
            for(Bookmark bookmark : bookmarksList) {
                System.out.println(bookmark);
            }
        }
    }

    private static void start() {
        //System.out.println("\n2. Bookmarking ...");
        for(User user : users) {
            View.browse(user, bookmarks);
        }
    }

    public static void main(String[] args) {
        loadData();
        start();
    }


}
