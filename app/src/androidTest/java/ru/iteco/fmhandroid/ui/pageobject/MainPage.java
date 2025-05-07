package ru.iteco.fmhandroid.ui.pageobject;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;
import static ru.iteco.fmhandroid.ui.data.MainHelper.childAtPosition;

import android.view.View;

import androidx.test.espresso.ViewInteraction;

import org.hamcrest.Matcher;

import ru.iteco.fmhandroid.R;

public class MainPage {
    public ViewInteraction mainBadge;
    public ViewInteraction profileButton;
    public ViewInteraction logOutButton;
    public ViewInteraction menuButton;
    public ViewInteraction mainOfMenu;
    public ViewInteraction newsOfMenu;
    public ViewInteraction  aboutOfMenu;
    public ViewInteraction ourMissionButton;
    public ViewInteraction titleOfNewsContainer;
    public ViewInteraction allNewsButton;
    public ViewInteraction collapseAllNewsButton;
    public static ViewInteraction childNewsButton;
    public Matcher<View> allNewsTextMatcher;

    public MainPage() {
        mainBadge = onView(withId(R.id.trademark_image_view));
        profileButton = onView(withId(R.id.authorization_image_button));
        logOutButton = onView(withText("Log out"));
        menuButton = onView(withId(R.id.main_menu_image_button));
        mainOfMenu = onView(withText("Main"));
        newsOfMenu = onView(withText("News"));
        aboutOfMenu = onView(withText("About"));
        ourMissionButton = onView(withId(R.id.our_mission_image_button));
        titleOfNewsContainer = onView(withText("News"));
        allNewsTextMatcher = withId(R.id.all_news_text_view);
        allNewsButton = onView(withId(R.id.all_news_text_view));
        collapseAllNewsButton = onView(withId(R.id.expand_material_button));
        childNewsButton = onView(allOf(withId(R.id.news_list_recycler_view),
                childAtPosition(withClassName(is("android.widget.LinearLayout")),
                        withId(R.id.all_news_cards_block_constraint_layout), 0)));
    }
}