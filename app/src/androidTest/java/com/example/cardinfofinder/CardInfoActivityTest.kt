import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.example.cardinfofinder.R
import com.example.cardinfofinder.ui.CardInfoActivity
import org.junit.Rule
import org.junit.Test




class CardInfoActivityTest {

    @get:Rule
    val activityScenario = ActivityScenarioRule(CardInfoActivity::class.java)

    @Test
    fun test_card_number_edit_text_in_view() {
        onView(withId(R.id.card_number_edit_text))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }
}
