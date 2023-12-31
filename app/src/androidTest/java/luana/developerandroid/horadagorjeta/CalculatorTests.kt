package luana.developerandroid.horadagorjeta

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.hamcrest.Matchers.containsString
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class CalculatorTests {
    @get:Rule()
    val activity = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun calculate_20_percent_tip() {
        onView(withId(R.id.viewValorDoServicoEditText))
            .perform(typeText("50.00"))


        onView(withId(R.id.btn_calcular))
            .perform(click())

        onView(withId(R.id.view_gorjeta_resultado))
            .check(matches(withText(containsString("$10.00"))))
    }


    @Test
    fun calculate_18_percent_tip() {
        onView(withId(R.id.viewValorDoServicoEditText))
            .perform(typeText("50.00"))

        onView(withId(R.id.btn_dezoito_porcento))
            .perform(click())

        onView(withId(R.id.btn_calcular))
            .perform(click())

        onView(withId(R.id.view_gorjeta_resultado))
            .check(matches(withText(containsString("$9.00"))))
    }

    @Test
    fun calculate_15_percent_tip() {
        onView(withId(R.id.viewValorDoServicoEditText))
            .perform(typeText("50.00"))

        onView(withId(R.id.btn_quinze_porcento))
            .perform(click())

        onView(withId(R.id.btn_calcular))
            .perform(click())

        onView(withId(R.id.view_gorjeta_resultado))
            .check(matches(withText(containsString("$8.00"))))
    }
}