package com.yangsong.bookmarkApp;

import com.sun.tools.javah.Gen;
import com.yangsong.bookmarkApp.constants.BookGenre;
import com.yangsong.bookmarkApp.constants.Gender;
import com.yangsong.bookmarkApp.constants.MovieGenre;
import com.yangsong.bookmarkApp.constants.UserType;
import com.yangsong.bookmarkApp.entities.Book;
import com.yangsong.bookmarkApp.entities.Bookmark;
import com.yangsong.bookmarkApp.entities.User;
import com.yangsong.bookmarkApp.entities.UserBookmark;
import com.yangsong.bookmarkApp.managers.BookmarkManager;
import com.yangsong.bookmarkApp.managers.UserManager;
import com.yangsong.bookmarkApp.util.IOUtil;

import java.util.ArrayList;
import java.util.List;

public class DataStore {
    public static final int USER_BOOKMARK_LIMIT = 5;

    private static List<User> users = new ArrayList<>();
    private static List<List<Bookmark>> bookmarks = new ArrayList<>();
    private static List<UserBookmark> userBookmarks = new ArrayList<>();

    public static List<User> getUsers() {
        return users;
    }

    public static List<List<Bookmark>> getBookmarks() {
        return bookmarks;
    }

    public static void loadData() {
        loadUsers();
        loadWebLinks();
        loadMovies();
        loadBooks();
    }

    private static void loadUsers() {
        List<String> data = new ArrayList<>();
        IOUtil.read(data, "User.txt");
        for (String row : data) {
            String[] values = row.split("\t");
            Gender gender = Gender.MALE;
            if (values[5].equals("f")) {
                gender = Gender.FEMALE;
            } else if (values[5].equals("t")) {
                gender = Gender.TRANSGENDER;
            }

            User user = UserManager.getInstance().createUser(Long.parseLong(values[0]), values[1], values[2], values[3], values[4], gender, UserType.valueOf(values[6]));
            users.add(user);
        }
    }

    private static void loadWebLinks() {
        List<String> data = new ArrayList<>();
        IOUtil.read(data, "WebLink.txt");
        List<Bookmark> bookmarkList = new ArrayList<>();
        for (String row : data) {
            String[] values = row.split("\t");
            Bookmark bookmark = BookmarkManager.getInstance().createWebLink(Long.parseLong(values[0]), values[1],
                    values[2], values[3]);
            bookmarkList.add(bookmark);
        }
        bookmarks.add(bookmarkList);
    }

    private static void loadMovies() {
        List<String> data = new ArrayList<>();
        IOUtil.read(data, "Movie.txt");
        List<Bookmark> bookmarkList = new ArrayList<>();
        for (String row : data) {
            String[] values = row.split("\t");
            String[] cast = values[3].split(",");
            String[] directors = values[4].split(",");
            Bookmark bookmark = BookmarkManager.getInstance().createMovie(Long.parseLong(values[0]), values[1],
                    Integer.parseInt(values[2]), cast, directors, MovieGenre.valueOf(values[5]), Double.parseDouble(values[6]));
            bookmarkList.add(bookmark);
        }
        bookmarks.add(bookmarkList);
    }

    private static void loadBooks() {
        List<String> data = new ArrayList<>();
        IOUtil.read(data, "Book.txt");
        List<Bookmark> bookmarkList = new ArrayList<>();
        for (String row : data) {
            String[] values = row.split("\t");
            String[] authors = values[4].split(",");
            Bookmark bookmark = BookmarkManager.getInstance().createBook(Long.parseLong(values[0]), values[1],
                    Integer.parseInt(values[2]), values[3], authors, BookGenre.valueOf(values[5]), Double.parseDouble(values[6]));
            bookmarkList.add(bookmark);
        }
        bookmarks.add(bookmarkList);
    }


    public static void add(UserBookmark userBookmark) {
        userBookmarks.add(userBookmark);
    }
}
