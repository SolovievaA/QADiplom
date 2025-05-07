package ru.iteco.fmhandroid.ui.pageobject;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

import android.view.View;

import androidx.test.espresso.ViewInteraction;

import org.hamcrest.Matcher;

import ru.iteco.fmhandroid.R;

public class AboutPage {
    public ViewInteraction badge;
    public ViewInteraction backButton;
    public ViewInteraction versionTitleField;
    public ViewInteraction versionNumberField;
    public ViewInteraction privacyPolicyValue;
    public ViewInteraction policyText;
    public ViewInteraction termsOfUseText;
    public ViewInteraction infoCompany;
    public ViewInteraction termsOfUseValue;
    public Matcher<View> infoCompanyMatcher;

    public AboutPage() {
        badge = onView(withId(R.id.trademark_image_view));
        backButton = onView(withId(R.id.about_back_image_button));
        versionTitleField = onView(withId(R.id.about_version_title_text_view));
        versionNumberField = onView(withId(R.id.about_version_value_text_view));
        policyText = onView(withId(R.id.about_privacy_policy_label_text_view));
        termsOfUseText = onView(withId(R.id.about_terms_of_use_label_text_view));
        infoCompanyMatcher = withId(R.id.about_company_info_label_text_view);
        infoCompany = onView(infoCompanyMatcher);
        privacyPolicyValue = onView(withId(R.id.about_privacy_policy_value_text_view));
        termsOfUseValue = onView(withId(R.id.about_terms_of_use_value_text_view));


    }
}