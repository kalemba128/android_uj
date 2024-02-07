package com.kalemba128.auth

import android.view.View
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.RootMatchers.withDecorView
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.activityScenarioRule
import androidx.test.runner.AndroidJUnit4
import org.hamcrest.Matchers
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.Before

@RunWith(AndroidJUnit4::class)
class SignInFragmentTests {
    @get:Rule
    var activityScenarioRule = activityScenarioRule<MainActivity>()

    lateinit var decorView: View

    @Before
    fun setUp() {
        activityScenarioRule.scenario.onActivity { activity ->
            decorView = activity.window.decorView
        }
    }

    @Test
    fun navigateToSignUpFragment() {
        onView(withId(R.id.signUpButton)).perform(click())
        onView(withId(R.id.registerFragment)).check(
            matches(
                isDisplayed()
            )
        )
    }

    /* Email */
    @Test
    fun emailIsEmpty1() {
        onView(withId(R.id.signInEmailTextField)).perform(ViewActions.typeText(""))
        onView(withId(R.id.signInPasswordTextField)).perform(ViewActions.typeText("12345678"))
        onView(withId(R.id.signInSubmitButton)).perform(click())
        onView(withText(R.string.email_empty)).inRoot(withDecorView(Matchers.not(decorView))).check(matches(isDisplayed()));
    }

    @Test
    fun emailIsEmpty2() {
        onView(withId(R.id.signInEmailTextField)).perform(ViewActions.typeText("    "))
        onView(withId(R.id.signInPasswordTextField)).perform(ViewActions.typeText("12345678"))
        onView(withId(R.id.signInSubmitButton)).perform(click())
        onView(withText(R.string.email_empty)).inRoot(withDecorView(Matchers.not(decorView))).check(matches(isDisplayed()));
    }

    @Test
    fun emailIsEmpty3() {
        onView(withId(R.id.signInEmailTextField)).perform(ViewActions.typeText("\n\n\n"))
        onView(withId(R.id.signInPasswordTextField)).perform(ViewActions.typeText("12345678"))
        onView(withId(R.id.signInSubmitButton)).perform(click())
        onView(withText(R.string.email_empty)).inRoot(withDecorView(Matchers.not(decorView))).check(matches(isDisplayed()));
    }

    @Test
    fun emailIsEmpty4() {
        onView(withId(R.id.signInEmailTextField)).perform(ViewActions.typeText("\t\t\t"))
        onView(withId(R.id.signInPasswordTextField)).perform(ViewActions.typeText("12345678"))
        onView(withId(R.id.signInSubmitButton)).perform(click())
        onView(withText(R.string.email_empty)).inRoot(withDecorView(Matchers.not(decorView))).check(matches(isDisplayed()));
    }

    @Test
    fun emailIsEmpty5() {
        onView(withId(R.id.signInEmailTextField)).perform(ViewActions.typeText("\t\n "))
        onView(withId(R.id.signInPasswordTextField)).perform(ViewActions.typeText("12345678"))
        onView(withId(R.id.signInSubmitButton)).perform(click())
        onView(withText(R.string.email_empty)).inRoot(withDecorView(Matchers.not(decorView))).check(matches(isDisplayed()));
    }

    @Test
    fun emailIsTooShort1() {
        onView(withId(R.id.signInEmailTextField)).perform(ViewActions.typeText("@"))
        onView(withId(R.id.signInPasswordTextField)).perform(ViewActions.typeText("12345678"))
        onView(withId(R.id.signInSubmitButton)).perform(click())
        onView(withText(R.string.email_short)).inRoot(withDecorView(Matchers.not(decorView))).check(matches(isDisplayed()));
    }

    @Test
    fun emailIsTooShort2() {
        onView(withId(R.id.signInEmailTextField)).perform(ViewActions.typeText("aaaaaaa"))
        onView(withId(R.id.signInPasswordTextField)).perform(ViewActions.typeText("12345678"))
        onView(withId(R.id.signInSubmitButton)).perform(click())
        onView(withText(R.string.email_short)).inRoot(withDecorView(Matchers.not(decorView))).check(matches(isDisplayed()));
    }

    @Test
    fun emailIsTooShort3() {
        onView(withId(R.id.signInEmailTextField)).perform(ViewActions.typeText("aa@a.pl"))
        onView(withId(R.id.signInPasswordTextField)).perform(ViewActions.typeText("12345678"))
        onView(withId(R.id.signInSubmitButton)).perform(click())
        onView(withText(R.string.email_short)).inRoot(withDecorView(Matchers.not(decorView))).check(matches(isDisplayed()));
    }

    @Test
    fun emailIsTooLong1() {
        onView(withId(R.id.signInEmailTextField)).perform(ViewActions.typeText("a".repeat(129)))
        onView(withId(R.id.signInPasswordTextField)).perform(ViewActions.typeText("12345678"))
        onView(withId(R.id.signInSubmitButton)).perform(click())
        onView(withText(R.string.email_long)).inRoot(withDecorView(Matchers.not(decorView))).check(matches(isDisplayed()));
    }

    @Test
    fun emailIsTooLong2() {
        onView(withId(R.id.signInEmailTextField)).perform(ViewActions.typeText(" ".repeat(128) + "a"))
        onView(withId(R.id.signInPasswordTextField)).perform(ViewActions.typeText("12345678"))
        onView(withId(R.id.signInSubmitButton)).perform(click())
        onView(withText(R.string.email_long)).inRoot(withDecorView(Matchers.not(decorView))).check(matches(isDisplayed()));
    }

    @Test
    fun emailIsTooLong3() {
        onView(withId(R.id.signInEmailTextField)).perform(ViewActions.typeText("a ".repeat(64) + " "))
        onView(withId(R.id.signInPasswordTextField)).perform(ViewActions.typeText("12345678"))
        onView(withId(R.id.signInSubmitButton)).perform(click())
        onView(withText(R.string.email_long)).inRoot(withDecorView(Matchers.not(decorView))).check(matches(isDisplayed()));
    } // 12

    @Test
    fun emailIsNotValid1() {
        val email = "abcdefgh"
        onView(withId(R.id.signInEmailTextField)).perform(ViewActions.typeText(email))
        onView(withId(R.id.signInPasswordTextField)).perform(ViewActions.typeText("12345678"))
        onView(withId(R.id.signInSubmitButton)).perform(click())
        onView(withText(R.string.email_valid)).inRoot(withDecorView(Matchers.not(decorView))).check(matches(isDisplayed()));
    }

    @Test
    fun emailIsNotValid2() {
        val email = "a@cdefgh"
        onView(withId(R.id.signInEmailTextField)).perform(ViewActions.typeText(email))
        onView(withId(R.id.signInPasswordTextField)).perform(ViewActions.typeText("12345678"))
        onView(withId(R.id.signInSubmitButton)).perform(click())
        onView(withText(R.string.email_valid)).inRoot(withDecorView(Matchers.not(decorView))).check(matches(isDisplayed()));
    }

    @Test
    fun emailIsNotValid3() {
        val email = "a@cde@gh"
        onView(withId(R.id.signInEmailTextField)).perform(ViewActions.typeText(email))
        onView(withId(R.id.signInPasswordTextField)).perform(ViewActions.typeText("12345678"))
        onView(withId(R.id.signInSubmitButton)).perform(click())
        onView(withText(R.string.email_valid)).inRoot(withDecorView(Matchers.not(decorView))).check(matches(isDisplayed()));
    }

    @Test
    fun emailIsNotValid4() {
        val email = "a@cde.pl.xd"
        onView(withId(R.id.signInEmailTextField)).perform(ViewActions.typeText(email))
        onView(withId(R.id.signInPasswordTextField)).perform(ViewActions.typeText("12345678"))
        onView(withId(R.id.signInSubmitButton)).perform(click())
        onView(withText(R.string.email_valid)).inRoot(withDecorView(Matchers.not(decorView))).check(matches(isDisplayed()));
    }

    @Test
    fun emailIsNotValid5() {
        val email = "a.c.d.e.p.l"
        onView(withId(R.id.signInEmailTextField)).perform(ViewActions.typeText(email))
        onView(withId(R.id.signInPasswordTextField)).perform(ViewActions.typeText("12345678"))
        onView(withId(R.id.signInSubmitButton)).perform(click())
        onView(withText(R.string.email_valid)).inRoot(withDecorView(Matchers.not(decorView))).check(matches(isDisplayed()));
    }


    @Test
    fun emailIsNotValid6() {
        val email = "a#cedfg@email.com"
        onView(withId(R.id.signInEmailTextField)).perform(ViewActions.typeText(email))
        onView(withId(R.id.signInPasswordTextField)).perform(ViewActions.typeText("12345678"))
        onView(withId(R.id.signInSubmitButton)).perform(click())
        onView(withText(R.string.email_valid)).inRoot(withDecorView(Matchers.not(decorView))).check(matches(isDisplayed()));
    }

    @Test
    fun emailIsNotValid7() {
        val email = "a cedfg@email.com"
        onView(withId(R.id.signInEmailTextField)).perform(ViewActions.typeText(email))
        onView(withId(R.id.signInPasswordTextField)).perform(ViewActions.typeText("12345678"))
        onView(withId(R.id.signInSubmitButton)).perform(click())
        onView(withText(R.string.email_valid)).inRoot(withDecorView(Matchers.not(decorView))).check(matches(isDisplayed()));
    }

    @Test
    fun emailIsNotValid8() {
        val email = "abcedfg@emailcom"
        onView(withId(R.id.signInEmailTextField)).perform(ViewActions.typeText(email))
        onView(withId(R.id.signInPasswordTextField)).perform(ViewActions.typeText("12345678"))
        onView(withId(R.id.signInSubmitButton)).perform(click())
        onView(withText(R.string.email_valid)).inRoot(withDecorView(Matchers.not(decorView))).check(matches(isDisplayed()));
    }


    /* -------- Password -------- */
    @Test
    fun passwordIsEmpty1() {
        onView(withId(R.id.signInEmailTextField)).perform(ViewActions.typeText("test@test.pl"))
        onView(withId(R.id.signInPasswordTextField)).perform(ViewActions.typeText(""))
        onView(withId(R.id.signInSubmitButton)).perform(click())
        onView(withText(R.string.password_empty)).inRoot(withDecorView(Matchers.not(decorView))).check(matches(isDisplayed()));
    }

    @Test
    fun passwordIsEmpty2() {
        onView(withId(R.id.signInEmailTextField)).perform(ViewActions.typeText("test@test.pl"))
        onView(withId(R.id.signInPasswordTextField)).perform(ViewActions.typeText("    "))
        onView(withId(R.id.signInSubmitButton)).perform(click())
        onView(withText(R.string.password_empty)).inRoot(withDecorView(Matchers.not(decorView))).check(matches(isDisplayed()));
    }

    @Test
    fun passwordIsEmpty3() {
        onView(withId(R.id.signInEmailTextField)).perform(ViewActions.typeText("test@test.pl"))
        onView(withId(R.id.signInPasswordTextField)).perform(ViewActions.typeText("\n\n\n"))
        onView(withId(R.id.signInSubmitButton)).perform(click())
        onView(withText(R.string.password_empty)).inRoot(withDecorView(Matchers.not(decorView))).check(matches(isDisplayed()));
    }

    @Test
    fun passwordIsEmpty4() {
        onView(withId(R.id.signInEmailTextField)).perform(ViewActions.typeText("test@test.pl"))
        onView(withId(R.id.signInPasswordTextField)).perform(ViewActions.typeText("\t\t\t"))
        onView(withId(R.id.signInSubmitButton)).perform(click())
        onView(withText(R.string.password_empty)).inRoot(withDecorView(Matchers.not(decorView))).check(matches(isDisplayed()));
    }

    @Test
    fun passwordIsEmpty5() {
        onView(withId(R.id.signInEmailTextField)).perform(ViewActions.typeText("test@test.pl"))
        onView(withId(R.id.signInPasswordTextField)).perform(ViewActions.typeText("\t\n "))
        onView(withId(R.id.signInSubmitButton)).perform(click())
        onView(withText(R.string.password_empty)).inRoot(withDecorView(Matchers.not(decorView))).check(matches(isDisplayed()));
    }

    @Test
    fun passwordIsTooShort1() {
        onView(withId(R.id.signInEmailTextField)).perform(ViewActions.typeText("test@test.pl"))
        onView(withId(R.id.signInPasswordTextField)).perform(ViewActions.typeText("1"))
        onView(withId(R.id.signInSubmitButton)).perform(click())
        onView(withText(R.string.password_short)).inRoot(withDecorView(Matchers.not(decorView))).check(matches(isDisplayed()));
    }

    @Test
    fun passwordIsTooShort2() {
        onView(withId(R.id.signInEmailTextField)).perform(ViewActions.typeText("test@test.pl"))
        onView(withId(R.id.signInPasswordTextField)).perform(ViewActions.typeText("aaaaaaa"))
        onView(withId(R.id.signInSubmitButton)).perform(click())
        onView(withText(R.string.password_short)).inRoot(withDecorView(Matchers.not(decorView))).check(matches(isDisplayed()));
    }

    @Test
    fun passwordIsTooShort3() {
        onView(withId(R.id.signInEmailTextField)).perform(ViewActions.typeText("test@test.pl"))
        onView(withId(R.id.signInPasswordTextField)).perform(ViewActions.typeText("123456 "))
        onView(withId(R.id.signInSubmitButton)).perform(click())
        onView(withText(R.string.password_short)).inRoot(withDecorView(Matchers.not(decorView))).check(matches(isDisplayed()));
    }

    @Test
    fun passwordIsTooLong1() {
        onView(withId(R.id.signInEmailTextField)).perform(ViewActions.typeText("test@test.pl"))
        onView(withId(R.id.signInPasswordTextField)).perform(ViewActions.typeText("a".repeat(129)))
        onView(withId(R.id.signInSubmitButton)).perform(click())
        onView(withText(R.string.password_long)).inRoot(withDecorView(Matchers.not(decorView))).check(matches(isDisplayed()));
    }

    @Test
    fun passwordIsTooLong2() {
        onView(withId(R.id.signInEmailTextField)).perform(ViewActions.typeText("test@test.pl"))
        onView(withId(R.id.signInPasswordTextField)).perform(ViewActions.typeText(" ".repeat(128) + "a"))
        onView(withId(R.id.signInSubmitButton)).perform(click())
        onView(withText(R.string.password_long)).inRoot(withDecorView(Matchers.not(decorView))).check(matches(isDisplayed()));
    }

    @Test
    fun passwordIsTooLong3() {
        onView(withId(R.id.signInEmailTextField)).perform(ViewActions.typeText("test@test.pl"))
        onView(withId(R.id.signInPasswordTextField)).perform(ViewActions.typeText("a ".repeat(64) + " "))
        onView(withId(R.id.signInSubmitButton)).perform(click())
        onView(withText(R.string.password_long)).inRoot(withDecorView(Matchers.not(decorView))).check(matches(isDisplayed()));
    } // 12

    @Test
    fun passwordDoesNotHaveDigits1() {
        onView(withId(R.id.signInEmailTextField)).perform(ViewActions.typeText("test@test.pl"))
        onView(withId(R.id.signInPasswordTextField)).perform(ViewActions.typeText("abcdefghijk"))
        onView(withId(R.id.signInSubmitButton)).perform(click())
        onView(withText(R.string.password_digits)).inRoot(withDecorView(Matchers.not(decorView))).check(matches(isDisplayed()));
    }

    @Test
    fun passwordDoesNotHaveDigits2() {
        onView(withId(R.id.signInEmailTextField)).perform(ViewActions.typeText("test@test.pl"))
        onView(withId(R.id.signInPasswordTextField)).perform(ViewActions.typeText("a@cd#fghij^"))
        onView(withId(R.id.signInSubmitButton)).perform(click())
        onView(withText(R.string.password_digits)).inRoot(withDecorView(Matchers.not(decorView))).check(matches(isDisplayed()));
    }

    @Test
    fun passwordIsNotCaseMixed1() {
        onView(withId(R.id.signInEmailTextField)).perform(ViewActions.typeText("test@test.pl"))
        onView(withId(R.id.signInPasswordTextField)).perform(ViewActions.typeText("abcdefghijk1"))
        onView(withId(R.id.signInSubmitButton)).perform(click())
        onView(withText(R.string.password_mixed)).inRoot(withDecorView(Matchers.not(decorView))).check(matches(isDisplayed()));
    }

    @Test
    fun passwordIsNotCaseMixed2() {
        onView(withId(R.id.signInEmailTextField)).perform(ViewActions.typeText("test@test.pl"))
        onView(withId(R.id.signInPasswordTextField)).perform(ViewActions.typeText("ABCDEFGHIJK1"))
        onView(withId(R.id.signInSubmitButton)).perform(click())
        onView(withText(R.string.password_mixed)).inRoot(withDecorView(Matchers.not(decorView))).check(matches(isDisplayed()));
    }

    @Test
    fun passwordIsNotCaseMixed3() {
        onView(withId(R.id.signInEmailTextField)).perform(ViewActions.typeText("test@test.pl"))
        onView(withId(R.id.signInPasswordTextField)).perform(ViewActions.typeText("123456789"))
        onView(withId(R.id.signInSubmitButton)).perform(click())
        onView(withText(R.string.password_mixed)).inRoot(withDecorView(Matchers.not(decorView))).check(matches(isDisplayed()));
    }

    @Test
    fun passwordDoesNotHaveSpecialCharacter1() {
        onView(withId(R.id.signInEmailTextField)).perform(ViewActions.typeText("test@test.pl"))
        onView(withId(R.id.signInPasswordTextField)).perform(ViewActions.typeText("abcDEF123"))
        onView(withId(R.id.signInSubmitButton)).perform(click())
        onView(withText(R.string.password_special)).inRoot(withDecorView(Matchers.not(decorView))).check(matches(isDisplayed()));
    }

    @Test
    fun passwordDoesNotHaveSpecialCharacter2() {
        onView(withId(R.id.signInEmailTextField)).perform(ViewActions.typeText("test@test.pl"))
        onView(withId(R.id.signInPasswordTextField)).perform(ViewActions.typeText("  abcDEF123 "))
        onView(withId(R.id.signInSubmitButton)).perform(click())
        onView(withText(R.string.password_special)).inRoot(withDecorView(Matchers.not(decorView))).check(matches(isDisplayed()));
    }

}