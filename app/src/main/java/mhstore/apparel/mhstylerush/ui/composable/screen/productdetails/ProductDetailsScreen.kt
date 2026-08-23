package mhstore.apparel.mhstylerush.ui.composable.screen.productdetails

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.CheckCircle
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import kotlinx.coroutines.delay
import mhstore.apparel.mhstylerush.data.model.Product
import mhstore.apparel.mhstylerush.ui.state.DataUiState
import mhstore.apparel.mhstylerush.ui.viewmodel.ProductDetailsViewModel
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun ProductDetailsScreen(
    productId: Int,
    modifier: Modifier = Modifier,
    viewModel: ProductDetailsViewModel = koinViewModel(),
) {
    val state by viewModel.productDetailsState.collectAsState()
    LaunchedEffect(productId) { viewModel.observeProductDetails(productId) }
    val product = (state as? DataUiState.Populated)?.data ?: return
    ProductDetails(product, modifier, viewModel::addProductToCart)
}

@Composable
private fun ProductDetails(product: Product, modifier: Modifier, onAddToCart: () -> Unit) {
    var cartAdded by remember { mutableStateOf(false) }
    LaunchedEffect(cartAdded) {
        if (cartAdded) {
            delay(2000)
            cartAdded = false
        }
    }
    Box(modifier.fillMaxSize()) {
        Column(
            Modifier.fillMaxSize().verticalScroll(rememberScrollState()).padding(bottom = 92.dp)
        ) {
            AsyncImage(
                model = product.imageUrl,
                contentDescription = product.title,
                modifier = Modifier.fillMaxWidth().height(380.dp),
            )
            Column(
                modifier = Modifier.padding(22.dp),
                verticalArrangement = Arrangement.spacedBy(14.dp),
            ) {
                Text(
                    product.category.name.lowercase().replaceFirstChar { it.uppercase() },
                    color = MaterialTheme.colorScheme.secondary,
                    style = MaterialTheme.typography.labelLarge,
                )
                Text(product.title, style = MaterialTheme.typography.headlineMedium)
                Text(
                    product.description,
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                )
                Text(
                    "Considered materials, versatile styling, and a comfortable finish make this piece a lasting addition to your wardrobe.",
                    style = MaterialTheme.typography.bodyMedium,
                )
            }
        }
        Row(
            modifier =
                Modifier.align(Alignment.BottomCenter)
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.surface)
                    .padding(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                "£%.2f".format(product.price),
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.primary,
            )
            Button(
                onClick = {
                    onAddToCart()
                    cartAdded = true
                },
                modifier = Modifier.weight(1f),
            ) {
                Text("Add to Cart")
            }
        }
        AnimatedVisibility(
            visible = cartAdded,
            modifier = Modifier.align(Alignment.BottomCenter).padding(bottom = 78.dp),
            enter = slideInVertically { it },
            exit = fadeOut(),
        ) {
            Row(
                Modifier.fillMaxWidth()
                    .background(MaterialTheme.colorScheme.surface)
                    .padding(14.dp),
                horizontalArrangement = Arrangement.Center,
            ) {
                Icon(
                    Icons.Rounded.CheckCircle,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.primary,
                )
                Text("  Added to cart", style = MaterialTheme.typography.bodyMedium)
            }
        }
    }
}
