package com.gmail.zajcevserg.newsdemo

import androidx.compose.ui.test.assertCountEquals
import androidx.compose.ui.test.junit4.ComposeContentTestRule
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onChildAt
import androidx.compose.ui.test.onChildren
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import com.gmail.zajcevserg.newsdemo.activity.MainActivity
import org.junit.Rule
import org.junit.Test


/**
 * UiTest scenario is:
 * 1. News list screen is opening when the app get started.
 * 2. Perform click on the first article.
 * 3. Details screen is opening.
 * 4. Perform click on the favorites icon to add the article into favorites.
 * 5. Perform click on the back icon.
 * 6. Repeat steps 2 - 5 on the second article.
 * 7. Perform click on the bottom app bar favorites icon.
 * 8. Favorites screen is opening.
 * 9. Check the favorites lazy column contain 2 elements.
 */

class AddToFavoritesScenarioUiTest {

    @Rule
    @JvmField
    var composeTestRule: ComposeContentTestRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun whenIClickFavoritesIcon_articleAddedIntoFavorites() {

        composeTestRule
            .onNodeWithTag("news_list_test_tag")
            .onChildAt(0)
            .performClick()

        composeTestRule
            .onNodeWithTag("add_to_favorites_icon_test_tag")
            .performClick()

        composeTestRule
            .onNodeWithTag("back_button_test_tag")
            .performClick()

        composeTestRule
            .onNodeWithTag("news_list_test_tag")
            .onChildAt(1)
            .performClick()

        composeTestRule
            .onNodeWithTag("add_to_favorites_icon_test_tag")
            .performClick()

        composeTestRule
            .onNodeWithTag("back_button_test_tag")
            .performClick()

        composeTestRule
            .onNodeWithTag("bottom_navigation_bar_favorites/_test_tag")
            .performClick()

        composeTestRule.onNodeWithTag("favorites_list_test_tag")
            .onChildren()
            .assertCountEquals(2)

    }
}