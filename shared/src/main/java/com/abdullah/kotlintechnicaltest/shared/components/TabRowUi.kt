package com.abdullah.kotlintechnicaltest.shared.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.abdullah.kotlintechnicaltest.shared.theme.AquaBlue
import com.abdullah.kotlintechnicaltest.shared.theme.TextPrimary
import com.abdullah.kotlintechnicaltest.shared.theme.White

@Composable
fun TabRowUi(
    titles: List<String>,
    modifier: Modifier = Modifier,
    selectedBackgroundColor: Color = AquaBlue,
    unselectedBackgroundColor: Color = White,
    selectedTitleColor: Color = TextPrimary,
    unselectedTitleColor: Color = selectedTitleColor,
    scrollable: Boolean = false,
) {
    var selectedTabIndex by remember { mutableIntStateOf(0) }
    val content: @Composable () -> Unit = {
        titles.forEachIndexed { index, s ->
            val selected = selectedTabIndex == index
            val tabModifier = Modifier
                .clip(RoundedCornerShape(50))
            Tab(
                modifier = if (selected)
                    tabModifier.background(selectedBackgroundColor)
                else
                    tabModifier.background(unselectedBackgroundColor),
                selected = selected, onClick = {
                    selectedTabIndex = index
                }) {
                Text(
                    text = s,
                    modifier = Modifier.padding(vertical = 8.dp, horizontal = 24.dp),
                    color = if (selected) selectedTitleColor else unselectedTitleColor,
                    fontWeight = FontWeight.Medium
                )
            }
        }
    }
    if (scrollable) {
        ScrollableTabRowUi(selectedTabIndex = selectedTabIndex, modifier = modifier) {
            content.invoke()
        }
    } else {
        DefaultTabRowUi(selectedTabIndex = selectedTabIndex, modifier = modifier) {
            content.invoke()
        }
    }
}

@Composable
private fun ScrollableTabRowUi(
    selectedTabIndex: Int,
    modifier: Modifier,
    content: @Composable () -> Unit,
) {
    ScrollableTabRow(
        selectedTabIndex = selectedTabIndex, indicator = { Box(modifier = Modifier) },
        divider = { Box(modifier = Modifier) },
        modifier = modifier
            .padding(vertical = 4.dp),
        edgePadding = 16.dp
    ) {
        content.invoke()
    }
}

@Composable
private fun DefaultTabRowUi(
    selectedTabIndex: Int,
    modifier: Modifier,
    content: @Composable () -> Unit,
) {
    TabRow(
        selectedTabIndex = selectedTabIndex, indicator = { Box(modifier = Modifier) },
        divider = { Box(modifier = Modifier) },
        modifier = modifier
            .padding(vertical = 4.dp),
    ) {
        content.invoke()
    }
}

@Composable
@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
private fun TabRowPreview() {
    TabRowUi(
        titles = listOf(
            "Profile",
            "Settings"
        )
    )
}