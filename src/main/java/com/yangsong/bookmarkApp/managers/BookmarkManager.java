package com.yangsong.bookmarkApp.managers;

import com.yangsong.bookmarkApp.dao.BookmarkDao;
import com.yangsong.bookmarkApp.entities.*;

public class BookmarkManager {
    private static BookmarkManager instance = new BookmarkManager();
    private static BookmarkDao dao = new BookmarkDao();

    private BookmarkManager() {
    }

    public static BookmarkManager getInstance() {
        return instance;
    }

    public Movie createMovie(long id, String title, int releaseYear, String[] cast,
                             String[] directors, String genre, double imdbRating) {
        Movie movie = new Movie();
        movie.setId(id);
        movie.setTitle(title);
        movie.setReleaseYear(releaseYear);
        movie.setCast(cast);
        movie.setDirectors(directors);
        movie.setGenre(genre);
        movie.setImdbRating(imdbRating);

        return movie;
    }

    public Book createBook(long id, String title, int publicationYear, String publisher,
                           String[] authors, String genre, double amazonRating) {
        Book book = new Book();
        book.setId(id);
        book.setTitle(title);
        book.setPublicationYear(publicationYear);
        book.setPublisher(publisher);
        book.setAuthors(authors);
        book.setGenre(genre);
        book.setAmazonRating(amazonRating);

        return book;
    }

    public WebLink createWebLink(long id, String title, String url, String host) {
        WebLink webLink = new WebLink();
        webLink.setId(id);
        webLink.setTitle(title);
        webLink.setUrl(url);
        webLink.setHost(host);
        return webLink;
    }

    public Bookmark[][] getBookmarks() {
        return dao.getBookmarks();
    }

    public void saveUserBookmark(User user, Bookmark bookmark) {
        UserBookmark userBookmark = new UserBookmark();
        userBookmark.setUser(user);
        userBookmark.setBookmark(bookmark);

        dao.saveUserBookmark(userBookmark);
    }

    public void setKidFriendlyStatus(User user, String kidFriendlyStatus, Bookmark bookmark) {
        bookmark.setKidFriendlyStatus(kidFriendlyStatus);
        bookmark.setKidFriendlyMarkedBy(user);
        System.out.println("Kid-friendly status: " + kidFriendlyStatus + ", Marked by: " + user.getEmail() + ", " + bookmark);
    }

    public void share(User user, Bookmark bookmark) {
        bookmark.setShareBy(user);
        System.out.println("Data to be shared: ");
        if (bookmark instanceof Book) {
            System.out.println(((Book)bookmark).getItemData());
        } else if (bookmark instanceof WebLink){
            System.out.println(((WebLink)bookmark).getItemData());
        }
    }
}
