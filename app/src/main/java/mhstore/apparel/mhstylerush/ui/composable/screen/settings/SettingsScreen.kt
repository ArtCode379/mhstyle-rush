package mhstore.apparel.mhstylerush.ui.composable.screen.settings

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp

@Composable
fun SettingsScreen(modifier: Modifier = Modifier) {
    val context = LocalContext.current
    Column(
        modifier.fillMaxSize().padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(18.dp),
    ) {
        Text("Settings", style = MaterialTheme.typography.headlineMedium)
        Text(
            "About",
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.primary,
        )
        Card {
            Column(Modifier.fillMaxWidth().padding(16.dp)) {
                Text("MHStyle Rush", style = MaterialTheme.typography.titleLarge)
                Text("MH STORE LTD")
                Text("Version 1.0")
            }
        }
        Text(
            "Support",
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.primary,
        )
        Text(
            "Questions about a reservation or collection? Our team is here to help.",
            color = MaterialTheme.colorScheme.onSurfaceVariant,
        )
        Button(
            onClick = {
                context.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("https://mhstore.surf")))
            },
            modifier = Modifier.fillMaxWidth(),
        ) {
            Text("Customer Support")
        }
    }
}
