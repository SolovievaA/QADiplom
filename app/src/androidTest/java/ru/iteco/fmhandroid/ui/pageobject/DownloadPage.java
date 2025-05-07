package ru.iteco.fmhandroid.ui.pageobject;

import static androidx.test.espresso.matcher.ViewMatchers.withId;
import android.view.View;
import org.hamcrest.Matcher;
import ru.iteco.fmhandroid.R;

public class DownloadPage {

    public Matcher<View> splashImageMatcher;

    public DownloadPage() {
        splashImageMatcher = withId(R.id.splashscreen_image_view);

    }
}