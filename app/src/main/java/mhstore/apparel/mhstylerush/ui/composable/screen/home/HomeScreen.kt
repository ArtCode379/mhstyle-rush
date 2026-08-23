package mhstore.apparel.mhstylerush.ui.composable.screen.home

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FilterChip
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import mhstore.apparel.mhstylerush.data.model.Product
import mhstore.apparel.mhstylerush.data.model.ProductCategory
import mhstore.apparel.mhstylerush.ui.state.DataUiState
import mhstore.apparel.mhstylerush.ui.viewmodel.ProductViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: ProductViewModel = koinViewModel(),
    onNavigateToProductDetails: (Int) -> Unit,
) {
    val state by viewModel.productsState.collectAsState()
    val products = (state as? DataUiState.Populated)?.data.orEmpty()
    var category by remember { mutableStateOf<ProductCategory?>(null) }
    val shown = products.filter { category == null || it.category == category }
    LazyColumn(
        modifier = modifier.fillMaxSize(),
        contentPadding = PaddingValues(bottom = 28.dp),
        verticalArrangement = Arrangement.spacedBy(18.dp),
    ) {
        item {
            Column(modifier = Modifier.padding(horizontal = 20.dp, vertical = 16.dp)) {
                Text(
                    "MHSTYLE",
                    style = MaterialTheme.typography.labelLarge,
                    color = MaterialTheme.colorScheme.primary,
                )
                Text("Wear the moment.", style = MaterialTheme.typography.displaySmall)
            }
        }
        products.firstOrNull()?.let { featured ->
            item { HeroProduct(featured, onNavigateToProductDetails) }
        }
        item {
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                contentPadding = PaddingValues(horizontal = 20.dp),
            ) {
                item {
                    FilterChip(
                        selected = category == null,
                        onClick = { category = null },
                        label = { Text("All") },
                    )
                }
                items(ProductCategory.entries) { item ->
                    FilterChip(
                        selected = category == item,
                        onClick = { category = item },
                        label = { Text(stringResource(item.titleRes)) },
                    )
                }
            }
        }
        items(shown.chunked(2)) { row ->
            Row(
                modifier = Modifier.fillMaxWidth().padding(horizontal = 20.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp),
            ) {
                row.forEachIndexed { index, product ->
                    ProductTile(product, index, Modifier.weight(1f), onNavigateToProductDetails)
                }
                if (row.size == 1) {
                    Box(Modifier.weight(1f))
                }
            }
        }
    }
}

@Composable
private fun HeroProduct(product: Product, onClick: (Int) -> Unit) {
    Box(
        modifier = Modifier.fillMaxWidth().height(240.dp).clickable { onClick(product.id) },
        contentAlignment = Alignment.BottomStart,
    ) {
        AsyncImage(
            model = product.imageUrl,
            contentDescription = product.title,
            modifier = Modifier.fillMaxSize(),
        )
        Box(
            modifier =
                Modifier.fillMaxSize()
                    .background(
                        Brush.verticalGradient(listOf(Color.Transparent, Color(0xCC241F1C)))
                    )
        )
        Column(modifier = Modifier.padding(24.dp)) {
            Text("THE EDIT", color = Color.White, style = MaterialTheme.typography.labelLarge)
            Text(
                product.title,
                color = Color.White,
                style = MaterialTheme.typography.headlineMedium,
            )
            Text(
                "£%.2f".format(product.price),
                color = Color.White,
                style = MaterialTheme.typography.titleMedium,
            )
        }
    }
}

@Composable
private fun ProductTile(product: Product, index: Int, modifier: Modifier, onClick: (Int) -> Unit) {
    Card(
        modifier = modifier.clickable { onClick(product.id) },
        shape = RoundedCornerShape(18.dp),
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
    ) {
        AsyncImage(
            model = product.imageUrl,
            contentDescription = product.title,
            modifier =
                Modifier.fillMaxWidth()
                    .height(if (index % 2 == 0) 190.dp else 235.dp)
                    .clip(RoundedCornerShape(topStart = 18.dp, topEnd = 18.dp)),
        )
        Column(modifier = Modifier.padding(12.dp)) {
            Text(product.title, style = MaterialTheme.typography.titleMedium)
            Text(
                "£%.2f".format(product.price),
                color = MaterialTheme.colorScheme.primary,
                style = MaterialTheme.typography.labelLarge,
            )
            Text(
                stringResource(product.category.titleRes),
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
            )
        }
    }
}
