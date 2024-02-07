package com.kalemba128.auth

import android.view.View
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.matcher.RootMatchers.withDecorView
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.activityScenarioRule
import androidx.test.runner.AndroidJUnit4
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import androidx.test.espresso.assertion.ViewAssertions.matches
import org.hamcrest.Matchers.not


@RunWith(AndroidJUnit4::class)
class SignUpFragmentTests {
    @get:Rule
    var activityScenarioRule = activityScenarioRule<MainActivity>()
    private lateinit var decorView: View

    @Before
    fun setUp() {
        activityScenarioRule.scenario.onActivity { activity ->
            decorView = activity.window.decorView
        }
        onView(withId(R.id.signUpButton)).perform(click())
    }

    @Test
    fun navigateToSignInFragment() {
        onView(withId(R.id.signInButton)).perform(click())
        onView(withId(R.id.signInFragment)).check(matches(isDisplayed()))
    }

    /* Email */
    @Test
    fun emailIsEmpty1() {
        onView(withId(R.id.signUpEmailTextField)).perform(typeText(""))
        onView(withId(R.id.signUpPasswordTextField)).perform(typeText("12345678"))
        onView(withId(R.id.signUpSubmitButton)).perform(click())
        onView(withText(R.string.email_empty)).inRoot(withDecorView(not(decorView))).check(matches(isDisplayed()));
    }

    @Test
    fun emailIsEmpty2() {
        onView(withId(R.id.signUpEmailTextField)).perform(typeText("    "))
        onView(withId(R.id.signUpPasswordTextField)).perform(typeText("12345678"))
        onView(withId(R.id.signUpSubmitButton)).perform(click())
        onView(withText(R.string.email_empty)).inRoot(withDecorView(not(decorView))).check(matches(isDisplayed()));
    }

    @Test
    fun emailIsEmpty3() {
        onView(withId(R.id.signUpEmailTextField)).perform(typeText("\n\n\n"))
        onView(withId(R.id.signUpPasswordTextField)).perform(typeText("12345678"))
        onView(withId(R.id.signUpSubmitButton)).perform(click())
        onView(withText(R.string.email_empty)).inRoot(withDecorView(not(decorView))).check(matches(isDisplayed()));
    }

    @Test
    fun emailIsEmpty4() {
        onView(withId(R.id.signUpEmailTextField)).perform(typeText("\t\t\t"))
        onView(withId(R.id.signUpPasswordTextField)).perform(typeText("12345678"))
        onView(withId(R.id.signUpSubmitButton)).perform(click())
        onView(withText(R.string.email_empty)).inRoot(withDecorView(not(decorView))).check(matches(isDisplayed()));
    }

    @Test
    fun emailIsEmpty5() {
        onView(withId(R.id.signUpEmailTextField)).perform(typeText("\t\n "))
        onView(withId(R.id.signUpPasswordTextField)).perform(typeText("12345678"))
        onView(withId(R.id.signUpSubmitButton)).perform(click())
        onView(withText(R.string.email_empty)).inRoot(withDecorView(not(decorView))).check(matches(isDisplayed()));
    }

    @Test
    fun emailIsTooShort1() {
        onView(withId(R.id.signUpEmailTextField)).perform(typeText("@"))
        onView(withId(R.id.signUpPasswordTextField)).perform(typeText("12345678"))
        onView(withId(R.id.signUpSubmitButton)).perform(click())
        onView(withText(R.string.email_short)).inRoot(withDecorView(not(decorView))).check(matches(isDisplayed()));
    }

    @Test
    fun emailIsTooShort2() {
        onView(withId(R.id.signUpEmailTextField)).perform(typeText("aaaaaaa"))
        onView(withId(R.id.signUpPasswordTextField)).perform(typeText("12345678"))
        onView(withId(R.id.signUpSubmitButton)).perform(click())
        onView(withText(R.string.email_short)).inRoot(withDecorView(not(decorView))).check(matches(isDisplayed()));
    }

    @Test
    fun emailIsTooShort3() {
        onView(withId(R.id.signUpEmailTextField)).perform(typeText("aa@a.pl"))
        onView(withId(R.id.signUpPasswordTextField)).perform(typeText("12345678"))
        onView(withId(R.id.signUpSubmitButton)).perform(click())
        onView(withText(R.string.email_short)).inRoot(withDecorView(not(decorView))).check(matches(isDisplayed()));
    }

    @Test
    fun emailIsTooLong1() {
        onView(withId(R.id.signUpEmailTextField)).perform(typeText("a".repeat(129)))
        onView(withId(R.id.signUpPasswordTextField)).perform(typeText("12345678"))
        onView(withId(R.id.signUpSubmitButton)).perform(click())
        onView(withText(R.string.email_long)).inRoot(withDecorView(not(decorView))).check(matches(isDisplayed()));
    }

    @Test
    fun emailIsTooLong2() {
        onView(withId(R.id.signUpEmailTextField)).perform(typeText(" ".repeat(128) + "a"))
        onView(withId(R.id.signUpPasswordTextField)).perform(typeText("12345678"))
        onView(withId(R.id.signUpSubmitButton)).perform(click())
        onView(withText(R.string.email_long)).inRoot(withDecorView(not(decorView))).check(matches(isDisplayed()));
    }

    @Test
    fun emailIsTooLong3() {
        onView(withId(R.id.signUpEmailTextField)).perform(typeText("a ".repeat(64) + " "))
        onView(withId(R.id.signUpPasswordTextField)).perform(typeText("12345678"))
        onView(withId(R.id.signUpSubmitButton)).perform(click())
        onView(withText(R.string.email_long)).inRoot(withDecorView(not(decorView))).check(matches(isDisplayed()));
    } // 12

    @Test
    fun emailIsNotValid1() {
        val email = "abcdefgh"
        onView(withId(R.id.signUpEmailTextField)).perform(typeText(email))
        onView(withId(R.id.signUpPasswordTextField)).perform(typeText("12345678"))
        onView(withId(R.id.signUpSubmitButton)).perform(click())
        onView(withText(R.string.email_valid)).inRoot(withDecorView(not(decorView))).check(matches(isDisplayed()));
    }

    @Test
    fun emailIsNotValid2() {
        val email = "a@cdefgh"
        onView(withId(R.id.signUpEmailTextField)).perform(typeText(email))
        onView(withId(R.id.signUpPasswordTextField)).perform(typeText("12345678"))
        onView(withId(R.id.signUpSubmitButton)).perform(click())
        onView(withText(R.string.email_valid)).inRoot(withDecorView(not(decorView))).check(matches(isDisplayed()));
    }

    @Test
    fun emailIsNotValid3() {
        val email = "a@cde@gh"
        onView(withId(R.id.signUpEmailTextField)).perform(typeText(email))
        onView(withId(R.id.signUpPasswordTextField)).perform(typeText("12345678"))
        onView(withId(R.id.signUpSubmitButton)).perform(click())
        onView(withText(R.string.email_valid)).inRoot(withDecorView(not(decorView))).check(matches(isDisplayed()));
    }

    @Test
    fun emailIsNotValid4() {
        val email = "a@cde.pl.xd"
        onView(withId(R.id.signUpEmailTextField)).perform(typeText(email))
        onView(withId(R.id.signUpPasswordTextField)).perform(typeText("12345678"))
        onView(withId(R.id.signUpSubmitButton)).perform(click())
        onView(withText(R.string.email_valid)).inRoot(withDecorView(not(decorView))).check(matches(isDisplayed()));
    }

    @Test
    fun emailIsNotValid5() {
        val email = "a.c.d.e.p.l"
        onView(withId(R.id.signUpEmailTextField)).perform(typeText(email))
        onView(withId(R.id.signUpPasswordTextField)).perform(typeText("12345678"))
        onView(withId(R.id.signUpSubmitButton)).perform(click())
        onView(withText(R.string.email_valid)).inRoot(withDecorView(not(decorView))).check(matches(isDisplayed()));
    }


    @Test
    fun emailIsNotValid6() {
        val email = "a#cedfg@email.com"
        onView(withId(R.id.signUpEmailTextField)).perform(typeText(email))
        onView(withId(R.id.signUpPasswordTextField)).perform(typeText("12345678"))
        onView(withId(R.id.signUpSubmitButton)).perform(click())
        onView(withText(R.string.email_valid)).inRoot(withDecorView(not(decorView))).check(matches(isDisplayed()));
    }

    @Test
    fun emailIsNotValid7() {
        val email = "a cedfg@email.com"
        onView(withId(R.id.signUpEmailTextField)).perform(typeText(email))
        onView(withId(R.id.signUpPasswordTextField)).perform(typeText("12345678"))
        onView(withId(R.id.signUpSubmitButton)).perform(click())
        onView(withText(R.string.email_valid)).inRoot(withDecorView(not(decorView))).check(matches(isDisplayed()));
    }

    @Test
    fun emailIsNotValid8() {
        val email = "abcedfg@emailcom"
        onView(withId(R.id.signUpEmailTextField)).perform(typeText(email))
        onView(withId(R.id.signUpPasswordTextField)).perform(typeText("12345678"))
        onView(withId(R.id.signUpSubmitButton)).perform(click())
        onView(withText(R.string.email_valid)).inRoot(withDecorView(not(decorView))).check(matches(isDisplayed()));
    }


    /* -------- Password -------- */
    @Test
    fun passwordIsEmpty1() {
        onView(withId(R.id.signUpEmailTextField)).perform(typeText("test@test.pl"))
        onView(withId(R.id.signUpPasswordTextField)).perform(typeText(""))
        onView(withId(R.id.signUpSubmitButton)).perform(click())
        onView(withText(R.string.password_empty)).inRoot(withDecorView(not(decorView))).check(matches(isDisplayed()));
    }

    @Test
    fun passwordIsEmpty2() {
        onView(withId(R.id.signUpEmailTextField)).perform(typeText("test@test.pl"))
        onView(withId(R.id.signUpPasswordTextField)).perform(typeText("    "))
        onView(withId(R.id.signUpSubmitButton)).perform(click())
        onView(withText(R.string.password_empty)).inRoot(withDecorView(not(decorView))).check(matches(isDisplayed()));
    }

    @Test
    fun passwordIsEmpty3() {
        onView(withId(R.id.signUpEmailTextField)).perform(typeText("test@test.pl"))
        onView(withId(R.id.signUpPasswordTextField)).perform(typeText("\n\n\n"))
        onView(withId(R.id.signUpSubmitButton)).perform(click())
        onView(withText(R.string.password_empty)).inRoot(withDecorView(not(decorView))).check(matches(isDisplayed()));
    }

    @Test
    fun passwordIsEmpty4() {
        onView(withId(R.id.signUpEmailTextField)).perform(typeText("test@test.pl"))
        onView(withId(R.id.signUpPasswordTextField)).perform(typeText("\t\t\t"))
        onView(withId(R.id.signUpSubmitButton)).perform(click())
        onView(withText(R.string.password_empty)).inRoot(withDecorView(not(decorView))).check(matches(isDisplayed()));
    }

    @Test
    fun passwordIsEmpty5() {
        onView(withId(R.id.signUpEmailTextField)).perform(typeText("test@test.pl"))
        onView(withId(R.id.signUpPasswordTextField)).perform(typeText("\t\n "))
        onView(withId(R.id.signUpSubmitButton)).perform(click())
        onView(withText(R.string.password_empty)).inRoot(withDecorView(not(decorView))).check(matches(isDisplayed()));
    }

    @Test
    fun passwordIsTooShort1() {
        onView(withId(R.id.signUpEmailTextField)).perform(typeText("test@test.pl"))
        onView(withId(R.id.signUpPasswordTextField)).perform(typeText("1"))
        onView(withId(R.id.signUpSubmitButton)).perform(click())
        onView(withText(R.string.password_short)).inRoot(withDecorView(not(decorView))).check(matches(isDisplayed()));
    }

    @Test
    fun passwordIsTooShort2() {
        onView(withId(R.id.signUpEmailTextField)).perform(typeText("test@test.pl"))
        onView(withId(R.id.signUpPasswordTextField)).perform(typeText("aaaaaaa"))
        onView(withId(R.id.signUpSubmitButton)).perform(click())
        onView(withText(R.string.password_short)).inRoot(withDecorView(not(decorView))).check(matches(isDisplayed()));
    }

    @Test
    fun passwordIsTooShort3() {
        onView(withId(R.id.signUpEmailTextField)).perform(typeText("test@test.pl"))
        onView(withId(R.id.signUpPasswordTextField)).perform(typeText("123456 "))
        onView(withId(R.id.signUpSubmitButton)).perform(click())
        onView(withText(R.string.password_short)).inRoot(withDecorView(not(decorView))).check(matches(isDisplayed()));
    }

    @Test
    fun passwordIsTooLong1() {
        onView(withId(R.id.signUpEmailTextField)).perform(typeText("test@test.pl"))
        onView(withId(R.id.signUpPasswordTextField)).perform(typeText("a".repeat(129)))
        onView(withId(R.id.signUpSubmitButton)).perform(click())
        onView(withText(R.string.password_long)).inRoot(withDecorView(not(decorView))).check(matches(isDisplayed()));
    }

    @Test
    fun passwordIsTooLong2() {
        onView(withId(R.id.signUpEmailTextField)).perform(typeText("test@test.pl"))
        onView(withId(R.id.signUpPasswordTextField)).perform(typeText(" ".repeat(128) + "a"))
        onView(withId(R.id.signUpSubmitButton)).perform(click())
        onView(withText(R.string.password_long)).inRoot(withDecorView(not(decorView))).check(matches(isDisplayed()));
    }

    @Test
    fun passwordIsTooLong3() {
        onView(withId(R.id.signUpEmailTextField)).perform(typeText("test@test.pl"))
        onView(withId(R.id.signUpPasswordTextField)).perform(typeText("a ".repeat(64) + " "))
        onView(withId(R.id.signUpSubmitButton)).perform(click())
        onView(withText(R.string.password_long)).inRoot(withDecorView(not(decorView))).check(matches(isDisplayed()));
    } // 12

    @Test
    fun passwordDoesNotHaveDigits1() {
        onView(withId(R.id.signUpEmailTextField)).perform(typeText("test@test.pl"))
        onView(withId(R.id.signUpPasswordTextField)).perform(typeText("abcdefghijk"))
        onView(withId(R.id.signUpSubmitButton)).perform(click())
        onView(withText(R.string.password_digits)).inRoot(withDecorView(not(decorView))).check(matches(isDisplayed()));
    }

    @Test
    fun passwordDoesNotHaveDigits2() {
        onView(withId(R.id.signUpEmailTextField)).perform(typeText("test@test.pl"))
        onView(withId(R.id.signUpPasswordTextField)).perform(typeText("a@cd#fghij^"))
        onView(withId(R.id.signUpSubmitButton)).perform(click())
        onView(withText(R.string.password_digits)).inRoot(withDecorView(not(decorView))).check(matches(isDisplayed()));
    }

    @Test
    fun passwordIsNotCaseMixed1() {
        onView(withId(R.id.signUpEmailTextField)).perform(typeText("test@test.pl"))
        onView(withId(R.id.signUpPasswordTextField)).perform(typeText("abcdefghijk1"))
        onView(withId(R.id.signUpSubmitButton)).perform(click())
        onView(withText(R.string.password_mixed)).inRoot(withDecorView(not(decorView))).check(matches(isDisplayed()));
    }

    @Test
    fun passwordIsNotCaseMixed2() {
        onView(withId(R.id.signUpEmailTextField)).perform(typeText("test@test.pl"))
        onView(withId(R.id.signUpPasswordTextField)).perform(typeText("ABCDEFGHIJK1"))
        onView(withId(R.id.signUpSubmitButton)).perform(click())
        onView(withText(R.string.password_mixed)).inRoot(withDecorView(not(decorView))).check(matches(isDisplayed()));
    }

    @Test
    fun passwordIsNotCaseMixed3() {
        onView(withId(R.id.signUpEmailTextField)).perform(typeText("test@test.pl"))
        onView(withId(R.id.signUpPasswordTextField)).perform(typeText("123456789"))
        onView(withId(R.id.signUpSubmitButton)).perform(click())
        onView(withText(R.string.password_mixed)).inRoot(withDecorView(not(decorView))).check(matches(isDisplayed()));
    }

    @Test
    fun passwordDoesNotHaveSpecialCharacter1() {
        onView(withId(R.id.signUpEmailTextField)).perform(typeText("test@test.pl"))
        onView(withId(R.id.signUpPasswordTextField)).perform(typeText("abcDEF123"))
        onView(withId(R.id.signUpSubmitButton)).perform(click())
        onView(withText(R.string.password_special)).inRoot(withDecorView(not(decorView))).check(matches(isDisplayed()));
    }

    @Test
    fun passwordDoesNotHaveSpecialCharacter2() {
        onView(withId(R.id.signUpEmailTextField)).perform(typeText("test@test.pl"))
        onView(withId(R.id.signUpPasswordTextField)).perform(typeText("  abcDEF123 "))
        onView(withId(R.id.signUpSubmitButton)).perform(click())
        onView(withText(R.string.password_special)).inRoot(withDecorView(not(decorView))).check(matches(isDisplayed()));
    }
}