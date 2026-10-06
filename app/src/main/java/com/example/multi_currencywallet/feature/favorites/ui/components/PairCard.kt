package com.example.multi_currencywallet.feature.favorites.ui.components


import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.multi_currencywallet.core.theme.Mint
import com.example.multi_currencywallet.feature.favorites.ui.FavoritePair
@Composable
fun PairCard(
    pair: FavoritePair,
    modifier: Modifier = Modifier
) {
    val shape = RoundedCornerShape(20.dp)
    val colors = MaterialTheme.colorScheme

    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(88.dp)
            .clip(shape)
            .background(colors.surface)
            .border(1.dp, colors.outlineVariant, shape)
            .padding(horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // الأعلام فوق بعض
        Box(modifier = Modifier.width(64.dp).height(44.dp)) {
            FlagCircle(flag = pair.from.flag, modifier = Modifier.align(Alignment.CenterStart))
            FlagCircle(
                flag = pair.to.flag,
                modifier = Modifier
                    .align(Alignment.CenterStart)
                    .offset(x = 22.dp)
                    .border(2.dp, colors.surface, CircleShape)
            )
        }

        Spacer(modifier = Modifier.width(12.dp))

        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = "${pair.from.code} / ${pair.to.code}",
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold,
                color = colors.onSurface
            )
            Text(
                text = "Updated 2 min ago",
                fontSize = 13.sp,
                color = colors.onSurfaceVariant
            )
        }

        Column(horizontalAlignment = Alignment.End) {
            Text(
                text = formatRate(pair.rate),
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = colors.onSurface
            )
            Text(
                text = "▲ ${pair.change}",
                fontSize = 12.sp,
                color = Mint
            )
        }
    }
}

@Composable
private fun FlagCircle(flag: String, modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .size(40.dp)
            .clip(CircleShape)
            .background(MaterialTheme.colorScheme.primary.copy(alpha = 0.12f)),
        contentAlignment = Alignment.Center
    ) {
        Text(text = flag, fontSize = 20.sp)
    }
}

// الأسعار الصغيرة (زي 0.0208) بتتعرض بـ4 خانات
private fun formatRate(rate: Double): String =
    if (rate < 1.0) "%.4f".format(rate) else "%.2f".format(rate)