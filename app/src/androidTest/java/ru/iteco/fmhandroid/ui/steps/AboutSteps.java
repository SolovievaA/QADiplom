package ru.iteco.fmhandroid.ui.steps;

import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;

import static ru.iteco.fmhandroid.ui.data.MainHelper.elementWaiting;

import io.qameta.allure.kotlin.Allure;

import ru.iteco.fmhandroid.ui.pageobject.AboutPage;

public class AboutSteps {

    AboutPage aboutPage = new AboutPage();

    public void checkAboutPageElementFull() {
        Allure.step("Проверка, что страница About содержит все элементы");
        elementWaiting(aboutPage.infoCompanyMatcher, 5000);
        aboutPage.badge.check(matches(isDisplayed()));
        aboutPage.backButton.check(matches(isDisplayed()));
        aboutPage.versionTitleField.check(matches(isDisplayed()));
        aboutPage.versionNumberField.check(matches(isDisplayed()));
        aboutPage.policyText.check(matches(isDisplayed()));
        aboutPage.termsOfUseText.check(matches(isDisplayed()));
        aboutPage.infoCompany.check(matches(isDisplayed()));
        aboutPage.privacyPolicyValue.check(matches(isDisplayed()));
        aboutPage.termsOfUseValue.check(matches(isDisplayed()));
    }


    public void goToPrivacyPolicy() {
        Allure.step("Переход к странице Политика конфиденциальности");
        aboutPage.privacyPolicyValue.perform(click());

    }

    public void goToTermsOfUse() {
        Allure.step("Переход к странице Пользовательское соглашение");
        aboutPage.termsOfUseValue.perform(click());
    }

    public void backMain() {
        Allure.step("Назад на Главную страницу");
        aboutPage.backButton.perform(click());
    }
}