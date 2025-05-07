package ru.iteco.fmhandroid.ui.pageobject;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.matcher.ViewMatchers.withHint;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import android.view.View;
import ru.iteco.fmhandroid.R;
import androidx.test.espresso.ViewInteraction;
import org.hamcrest.Matcher;

public class LogPage{
    public ViewInteraction title;
    public ViewInteraction loginField;
    public ViewInteraction passwordField;
    public ViewInteraction loginButton;
    public Matcher<View> loginButtonMatcher;

    public LogPage() {
        title = onView(withText("Authorization"));
        loginField = onView(withHint("Login"));
        passwordField = onView(withHint("Password"));
        loginButtonMatcher = withId(R.id.enter_button);
        loginButton = onView(loginButtonMatcher);
    }

}