package yuvital.life.sdk.android.kotlin.sample

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import yuvital.life.sdk.android.kotlin.sample.ui.theme.YuvitalLifeKotlinSampleTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            YuvitalLifeKotlinSampleTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    MainScreen(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

private data class YuvitalCardConfig(
        val title: String,
        @DrawableRes val iconRes: Int = R.drawable.ic_launcher_foreground,
        val isPrimary: Boolean = false,
        val isClickable: Boolean = false
)

@Composable
fun MainScreen(modifier: Modifier = Modifier) {
    val cards =
            listOf(
                    YuvitalCardConfig(
                            title = "Open Yuvital Life",
                            iconRes = R.drawable.yuvital_life,
                            isPrimary = true,
                            isClickable = true
                    ),
                    YuvitalCardConfig("Track Daily Activity"),
                    YuvitalCardConfig("Monitor Heart Health"),
                    YuvitalCardConfig("Manage Fitness Goals"),
                    YuvitalCardConfig("Nutrition & Hydration"),
                    YuvitalCardConfig("Sleep & Recovery"),
                    YuvitalCardConfig("Mindfulness & Stress"),
                    YuvitalCardConfig("Health Reports")
            )

    LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = modifier.fillMaxSize().padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(cards) { card ->
            YuvitalLifeCard(
                    title = card.title,
                    iconRes = card.iconRes,
                    isPrimary = card.isPrimary,
                    onClick =
                            if (card.isClickable) {
                                {
                                    // Implement Yuvital Life SDK open call
                                    Log.d("MainActivity", "Open Yuvital Life card clicked")
                                }
                            } else {
                                null
                            },
                    modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

@Composable
fun YuvitalLifeCard(
        title: String,
        @DrawableRes iconRes: Int = R.drawable.ic_launcher_foreground,
        modifier: Modifier = Modifier,
        isPrimary: Boolean = false,
        onClick: (() -> Unit)? = null
) {
    val baseModifier = modifier.aspectRatio(1f)
    val cardModifier =
            if (onClick != null) {
                baseModifier.clickable(onClick = onClick)
            } else {
                baseModifier
            }

    // Yuvital Life = primary (blue), others = grey
    val colors =
            if (isPrimary) {
                CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primary)
            } else {
                CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
            }

    val textColor =
            if (isPrimary) {
                MaterialTheme.colorScheme.onPrimary
            } else {
                MaterialTheme.colorScheme.onSurface
            }

    Card(modifier = cardModifier, colors = colors) {
        Column(
                modifier = Modifier.fillMaxSize().padding(16.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(painter = painterResource(id = iconRes), contentDescription = title)

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                    text = title,
                    style =
                            MaterialTheme.typography.titleMedium.copy(
                                    fontWeight = FontWeight.SemiBold
                            ),
                    color = textColor,
                    textAlign = TextAlign.Start
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun YuvitalLifeCardListPreview() {
    YuvitalLifeKotlinSampleTheme { MainScreen() }
}
