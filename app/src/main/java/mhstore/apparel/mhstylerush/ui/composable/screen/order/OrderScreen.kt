package mhstore.apparel.mhstylerush.ui.composable.screen.order

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import mhstore.apparel.mhstylerush.ui.state.DataUiState
import mhstore.apparel.mhstylerush.ui.theme.BrandSuccess
import mhstore.apparel.mhstylerush.ui.viewmodel.OrderViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun OrdersScreen(modifier: Modifier = Modifier, viewModel: OrderViewModel = koinViewModel()) {
    val state by viewModel.ordersState.collectAsState()
    val orders =
        (state as? DataUiState.Populated)?.data.orEmpty().sortedByDescending { it.timestamp }
    Column(
        modifier.fillMaxSize().padding(18.dp),
        verticalArrangement = Arrangement.spacedBy(14.dp),
    ) {
        Text("Reservations", style = MaterialTheme.typography.headlineMedium)
        if (orders.isEmpty())
            Text("No orders yet", color = MaterialTheme.colorScheme.onSurfaceVariant)
        LazyColumn(verticalArrangement = Arrangement.spacedBy(12.dp)) {
            items(orders) { order ->
                Card {
                    Column(
                        Modifier.fillMaxWidth().padding(16.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp),
                    ) {
                        Row(
                            Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                        ) {
                            Text(
                                "Order #${order.orderNumber}",
                                style = MaterialTheme.typography.titleMedium,
                            )
                            Surface(color = BrandSuccess, shape = MaterialTheme.shapes.large) {
                                Text(
                                    "Reserved",
                                    color = Color.White,
                                    modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp),
                                )
                            }
                        }
                        Text(order.description, color = MaterialTheme.colorScheme.onSurfaceVariant)
                        Text(
                            "£%.2f".format(order.price),
                            color = MaterialTheme.colorScheme.primary,
                            style = MaterialTheme.typography.titleMedium,
                        )
                        Text(
                            "Ready for collection for 24 hours from ${order.timestamp.toLocalDate()}",
                            style = MaterialTheme.typography.bodyMedium,
                        )
                    }
                }
            }
        }
    }
}
