package ru.iteco.fmhandroid.ui.pageobject;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;

import static ru.iteco.fmhandroid.ui.data.MainHelper.childAtPosition;
import static ru.iteco.fmhandroid.ui.data.MainHelper.withIndex;
import android.view.View;
import androidx.test.espresso.ViewInteraction;

import org.hamcrest.Matcher;


import ru.iteco.fmhandroid.R;

public class MainNewsPage {

    public ViewInteraction badge;
    public ViewInteraction title;
    public ViewInteraction sort;
    public ViewInteraction filter;
    public ViewInteraction filterButton;
    public static ViewInteraction categoryText;
    public ViewInteraction controlPanelButton;
    public ViewInteraction allNewsBlock;
    public ViewInteraction childNewsButton;
    public ViewInteraction publicationDateText;
    public Matcher<View> recyclerMatcher;
    private final Matcher<View> itemTitleMatcher;
    private final Matcher<View> itemDescriptionMatcher;
    public Matcher<View> newsImageMatcher;


    public MainNewsPage() {
        badge = onView(withId(R.id.trademark_image_view));
        title = onView(withText("News"));
        sort = onView(withId(R.id.sort_news_material_button));
        filter = onView(withId(R.id.filter_news_material_button));
        categoryText =
                onView(withId(R.id.news_item_category_text_auto_complete_text_view));
        filterButton = onView(withId(R.id.filter_button));
        recyclerMatcher = withId(R.id.news_list_recycler_view);
        itemTitleMatcher = withId(R.id.news_item_title_text_view);
        itemDescriptionMatcher = withId(R.id.news_item_description_text_view);
        controlPanelButton = onView(withId(R.id.edit_news_material_button));
        allNewsBlock = onView(withId(R.id.all_news_cards_block_constraint_layout));
        childNewsButton = onView(allOf(withId(R.id.news_list_recycler_view),
                childAtPosition(withClassName(is("android.widget.LinearLayout")),
                        withId(R.id.all_news_cards_block_constraint_layout), 0)));
        newsImageMatcher = withId(R.id.add_news_image_view);
        publicationDateText = onView(withIndex(withId(R.id.news_item_publication_date_text_view), 0));

    }

    public ViewInteraction getNewsItemTitle(int index)  {
        return onView(withIndex(itemTitleMatcher, index));
    }

    public ViewInteraction getNewsItemDescription(int index)  {
        return onView(withIndex(itemDescriptionMatcher, index));
    }

}