package com.yangsong.bookmarkApp;

import com.yangsong.bookmarkApp.constants.KidFriendlyStatus;
import com.yangsong.bookmarkApp.constants.UserType;
import com.yangsong.bookmarkApp.controllers.BookmarkController;
import com.yangsong.bookmarkApp.entities.Bookmark;
import com.yangsong.bookmarkApp.entities.User;
import com.yangsong.bookmarkApp.partner.Shareable;

import java.util.List;

public class View {

    public static void browse(User user, List<List<Bookmark>> bookmarks) {
        System.out.println("\n" + user.getEmail() + " is browsing items ...");
        int bookmarkCount = 0;

        for (List<Bookmark> bookmarkList : bookmarks) {
            for (Bookmark bookmark : bookmarkList) {
                if(bookmarkCount < DataStore.USER_BOOKMARK_LIMIT) {
                    boolean isBookmarked = getBookmarkDecision(bookmark);
                    if (isBookmarked) {
                        bookmarkCount++;
                        BookmarkController.getInstance().saveUserBookmark(user, bookmark);
                        System.out.println("New Item Bookmarked -- " + bookmark);
                    }
                }
                if (user.getUserType().equals(UserType.EDITOR) || user.getUserType().equals(UserType.CHIEF_EDITOR)) {


                    // Mark as kid-friendly
                    if (bookmark.isKidFriendlyEligible() && bookmark.getKidFriendlyStatus().equals(KidFriendlyStatus.UNKNOWN)) {
                        KidFriendlyStatus kidFriendlyStatus = getKidFriendlyStatusDecision(bookmark);
                        if (!kidFriendlyStatus.equals(KidFriendlyStatus.UNKNOWN)) {
                            BookmarkController.getInstance().setKidFriendlyStatus(user, kidFriendlyStatus, bookmark);
                        }
                    }

                    // Sharing
                    if (bookmark.getKidFriendlyStatus().equals(KidFriendlyStatus.APPROVED) && bookmark instanceof Shareable) {
                        boolean isShared = getShareDecision();
                        if (isShared) {
                            BookmarkController.getInstance().share(user, bookmark);
                        }
                    }

                }
            }
        }


    }

    // TODO: Below methods simulate user input. After IO, we take input via console.
    private static boolean getShareDecision() {
        return Math.random() < 0.5;
    }

    private static KidFriendlyStatus getKidFriendlyStatusDecision(Bookmark bookmark) {
        double tmpRandom = Math.random();
        return tmpRandom < 0.4 ? KidFriendlyStatus.APPROVED :
                (tmpRandom >= 0.4 && tmpRandom < 0.8) ? KidFriendlyStatus.REJECTED : KidFriendlyStatus.UNKNOWN;
    }

    private static boolean getBookmarkDecision(Bookmark bookmark) {
        return (Math.random() < 0.5);
    }

}
