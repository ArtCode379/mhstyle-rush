package mhstore.apparel.mhstylerush.ui.composable.screen.splash

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kotlinx.coroutines.delay
import mhstore.apparel.mhstylerush.R
import mhstore.apparel.mhstylerush.ui.theme.GradientEnd
import mhstore.apparel.mhstylerush.ui.theme.GradientStart
import mhstore.apparel.mhstylerush.ui.viewmodel.JNVNJSplashVM
import org.koin.androidx.compose.koinViewModel

@Composable
fun SplashScreen(
    modifier: Modifier = Modifier,
    viewModel: JNVNJSplashVM = koinViewModel(),
    onNavigateToHomeScreen: () -> Unit,
    onNavigateToOnboarding: () -> Unit,
) {
    val onboarded by viewModel.onboardedState.collectAsStateWithLifecycle()
    var visible by remember { mutableStateOf(false) }
    val alpha by animateFloatAsState(if (visible) 1f else 0f, label = "splashAlpha")
    val scale by animateFloatAsState(if (visible) 1f else 0.8f, label = "splashScale")
    LaunchedEffect(Unit) {
        visible = true
        delay(1500)
        if (onboarded) onNavigateToHomeScreen() else onNavigateToOnboarding()
    }
    Column(
        modifier
            .fillMaxSize()
            .background(Brush.verticalGradient(listOf(GradientStart, GradientEnd))),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Image(
            painterResource(R.mipmap.ic_launcher),
            contentDescription = "MHStyle Rush",
            Modifier.size(132.dp).alpha(alpha).scale(scale),
        )
    }
}
