package mhstore.apparel.mhstylerush.ui.composable.screen.cart

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil3.compose.AsyncImage
import mhstore.apparel.mhstylerush.ui.state.DataUiState
import mhstore.apparel.mhstylerush.ui.viewmodel.CartViewModel
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun CartScreen(
    modifier: Modifier = Modifier,
    viewModel: CartViewModel = koinViewModel(),
    onNavigateToCheckoutScreen: () -> Unit,
) {
    val state by viewModel.cartItemsState.collectAsStateWithLifecycle()
    val total by viewModel.totalPrice.collectAsStateWithLifecycle()
    val items = (state as? DataUiState.Populated)?.data.orEmpty()
    if (items.isEmpty()) {
        Column(
            modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            Text("Your edit starts here", style = MaterialTheme.typography.headlineMedium)
            Text(
                "Add a piece you love to begin.",
                color = MaterialTheme.colorScheme.onSurfaceVariant,
            )
        }
        return
    }
    Column(
        modifier.fillMaxSize().padding(18.dp),
        verticalArrangement = Arrangement.spacedBy(14.dp),
    ) {
        Text("Your bag", style = MaterialTheme.typography.headlineMedium)
        LazyColumn(Modifier.weight(1f), verticalArrangement = Arrangement.spacedBy(10.dp)) {
            items(items) { item ->
                Card {
                    Row(
                        Modifier.fillMaxWidth().padding(12.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(12.dp),
                    ) {
                        AsyncImage(item.productImageUrl, item.productTitle, Modifier.size(72.dp))
                        Column(Modifier.weight(1f)) {
                            Text(item.productTitle, style = MaterialTheme.typography.titleMedium)
                            Text(
                                "£%.2f".format(item.productPrice),
                                color = MaterialTheme.colorScheme.primary,
                            )
                        }
                        IconButton(
                            onClick = {
                                if (item.quantity == 1) viewModel.deleteFromCart(item.productId)
                                else viewModel.decrementItemInCart(item.productId)
                            }
                        ) {
                            Text("−")
                        }
                        Text(item.quantity.toString())
                        IconButton(onClick = { viewModel.incrementProductInCart(item.productId) }) {
                            Text("+")
                        }
                    }
                }
            }
        }
        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            Text("Total", style = MaterialTheme.typography.titleLarge)
            Text(
                "£%.2f".format(total),
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.primary,
            )
        }
        Button(onClick = onNavigateToCheckoutScreen, modifier = Modifier.fillMaxWidth()) {
            Text("Proceed to Checkout")
        }
    }
}
