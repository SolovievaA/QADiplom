package ru.iteco.fmhandroid.ui.pageobject;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.matcher.ViewMatchers.isCompletelyDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import androidx.test.espresso.ViewInteraction;
import org.hamcrest.Matcher;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;
import android.view.View;
import ru.iteco.fmhandroid.R;
import static ru.iteco.fmhandroid.ui.data.MainHelper.childAtPosition;
public class MissionPage {

    public ViewInteraction badge;
    public ViewInteraction title;
    public ViewInteraction ourMissionList;
    public ViewInteraction quotesLayout;
    private final Matcher<View> ourMissionDescription;


    public MissionPage() {
        badge = onView(withId(R.id.trademark_image_view));
        title = onView(withId(R.id.our_mission_title_text_view));
        ourMissionDescription = withId(R.id.our_mission_item_description_text_view);
        ourMissionList = onView(withId(R.id.our_mission_item_list_recycler_view));
        quotesLayout = onView(allOf(withId(R.id.our_mission_item_list_recycler_view),
                childAtPosition(withClassName(is("androidx.constraintlayout.widget.ConstraintLayout")), 0)));
    }

    public ViewInteraction textMission(String text) {
       return onView(allOf(
               ourMissionDescription,
               withText(text),
                isCompletelyDisplayed()));
    }
}