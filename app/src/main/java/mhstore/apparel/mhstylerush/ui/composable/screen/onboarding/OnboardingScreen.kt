package mhstore.apparel.mhstylerush.ui.composable.screen.onboarding

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import mhstore.apparel.mhstylerush.R
import mhstore.apparel.mhstylerush.ui.viewmodel.JNVNJOnboardingVM
import org.koin.androidx.compose.koinViewModel

private data class Page(val title: String, val text: String, val image: Int)

private val pages =
    listOf(
        Page(
            "Find your signature",
            "Explore considered edits for every mood, moment, and wardrobe.",
            R.drawable.onboarding_1,
        ),
        Page(
            "Details make the look",
            "Discover accessories and footwear chosen to bring every outfit together.",
            R.drawable.onboarding_2,
        ),
        Page(
            "Reserve with confidence",
            "Build your basket, reserve your order, and collect it in store within 24 hours.",
            R.drawable.onboarding_3,
        ),
    )

@Composable
fun OnboardingScreen(
    modifier: Modifier = Modifier,
    viewModel: JNVNJOnboardingVM = koinViewModel(),
    onNavigateToHomeScreen: () -> Unit,
) {
    val saved by viewModel.onboardingSetState.collectAsState()
    val pager = rememberPagerState { pages.size }
    LaunchedEffect(saved) { if (saved) onNavigateToHomeScreen() }
    Column(modifier.fillMaxSize()) {
        HorizontalPager(state = pager, modifier = Modifier.weight(1f)) { index ->
            val page = pages[index]
            Box(Modifier.fillMaxSize()) {
                Image(
                    painterResource(page.image),
                    page.title,
                    Modifier.align(Alignment.BottomCenter).fillMaxWidth().height(520.dp),
                    contentScale = ContentScale.Crop,
                )
                Column(
                    Modifier.fillMaxWidth()
                        .background(MaterialTheme.colorScheme.surface.copy(alpha = 0.94f))
                        .padding(26.dp),
                    verticalArrangement = Arrangement.spacedBy(10.dp),
                ) {
                    Text(page.title, style = MaterialTheme.typography.headlineMedium)
                    Text(
                        page.text,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        style = MaterialTheme.typography.bodyMedium,
                    )
                }
            }
        }
        Row(
            Modifier.fillMaxWidth().padding(20.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Row(horizontalArrangement = Arrangement.spacedBy(7.dp)) {
                repeat(3) { index ->
                    Box(
                        Modifier.size(if (pager.currentPage == index) 10.dp else 7.dp)
                            .background(
                                if (pager.currentPage == index) MaterialTheme.colorScheme.primary
                                else MaterialTheme.colorScheme.outline,
                                CircleShape,
                            )
                    )
                }
            }
            if (pager.currentPage == 2)
                Button(onClick = viewModel::setOnboarded) { Text("Get Started") }
            else Text("Swipe to explore", style = MaterialTheme.typography.bodyMedium)
        }
    }
}
