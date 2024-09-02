package com.example.send

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.send.ui.theme.SendTheme
import androidx.compose.foundation.Image
import androidx.compose.foundation.Canvas
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SendTheme {
                CryptoPortfolio()
            }
        }
    }
}

data class Currency(
    val name: String,
    val fullName: String,
    val change: String,
    val value: Double,
    val amount: Double,
    val color: Color,
    val iconResId: Int,
    val trendData: List<Float>
)

data class PortfolioData(
    val totalBalance: Double,
    val currencies: List<Currency>
)

@Composable
fun CryptoPortfolio() {
    val portfolioData = PortfolioData(
        totalBalance = 49329.77,
        currencies = listOf(
            Currency(
                name = "BTC",
                fullName = "Bitcoin",
                change = "+1.6%",
                value = 29850.15,
                amount = 2.73,
                color = Color(0xFFFFCDD2),
                iconResId = R.drawable.ic_bitcoin,
                trendData = listOf(28.5f, 28.7f, 28.6f, 29.0f, 29.1f, 29.2f, 29.85f) 
            ),
            Currency(
                name = "ETH",
                fullName = "Ethereum",
                change = "+0.8%",
                value = 10561.24,
                amount = 47.61,
                color = Color(0xFFE1F5FE),
                iconResId = R.drawable.ic_ethereum,
                trendData = listOf(10.2f, 10.3f, 10.1f, 10.4f, 10.5f, 10.6f, 10.56f) 
            ),
            Currency(
                name = "LTC",
                fullName = "Litecoin",
                change = "+0.4%",
                value = 3676.76,
                amount = 39.27,
                color = Color(0xFFE8F5E9),
                iconResId = R.drawable.ic_litecoin,
                trendData = listOf(3.6f, 3.62f, 3.58f, 3.63f, 3.65f, 3.67f, 3.68f) 
            ),
            Currency(
                name = "XRP",
                fullName = "Ripple",
                change = "+0.1%",
                value = 5241.62,
                amount = 1447.65,
                color = Color(0xFFE3F2FD),
                iconResId = R.drawable.ic_ripple,
                trendData = listOf(5.20f, 5.22f, 5.19f, 5.21f, 5.23f, 5.24f, 5.24f) 
            ))
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            Header()
            Spacer(modifier = Modifier.height(16.dp))
            BalanceCard(portfolioData.totalBalance)
            Spacer(modifier = Modifier.height(24.dp))
            ChartsHeader()
            Spacer(modifier = Modifier.height(16.dp))
            CurrenciesList(portfolioData.currencies)
        }
        BottomNavigationBar(
            isActiveDashboard = true,
            isActiveSync = false,
            isActiveProfile = false,
            modifier = Modifier.align(Alignment.BottomCenter)
        )
    }
}

@Composable
fun Header() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text("Portfolio", fontSize = 28.sp, fontWeight = FontWeight.Bold)
        Icon(Icons.Default.Settings, contentDescription = "Settings", tint = Color.Gray)
    }
}

@Composable
fun BalanceCard(balance: Double) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(190.dp) 
            .shadow(8.dp, RoundedCornerShape(24.dp)) 
            .background(
                Brush.linearGradient(
                    colors = listOf(
                        Color(0xFF7B75F8),
                        Color(0xFFCD62F5),
                        Color(0xFFF898D3),
                        Color(0xFF7DDDF3)
                    ),
                    start = Offset(0f, 0f), // Top-left start
                    end = Offset(1000f, 1000f) // Diagonal direction
                ),
                shape = RoundedCornerShape(24.dp) // Rounded corners
            )
            .padding(24.dp),
        contentAlignment = Alignment.CenterStart
    ) {
        Column(
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                "$${balance}",
                color = Color.White,
                fontSize = 36.sp, // Increased font size
                fontWeight = FontWeight.Bold
            )
            Text(
                "Your balance is equivalent",
                color = Color.White.copy(alpha = 0.9f),
                fontSize = 18.sp,
                fontWeight = FontWeight.Medium
            )
            Spacer(modifier = Modifier.height(16.dp))
            Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                Button(
                    onClick = {},
                    colors = ButtonDefaults.buttonColors(containerColor = Color.White.copy(alpha = 0.3f)),
                    shape = RoundedCornerShape(50) // Rounded button edges
                ) {
                    Text("Deposit", color = Color.White)
                }
                Button(
                    onClick = {},
                    colors = ButtonDefaults.buttonColors(containerColor = Color.White.copy(alpha = 0.3f)),
                    shape = RoundedCornerShape(50) // Rounded button edges
                ) {
                    Text("Withdraw", color = Color.White)
                }
            }
        }
        // Adding a floating icon aligned to the top right corner
        Icon(
            painter = painterResource(id = R.drawable.ic_btc), 
            contentDescription = "Crypto Icon",
            tint = Color.White,
            modifier = Modifier
                .align(Alignment.TopEnd)
                .size(45.dp) 
                .padding(end = 16.dp, top = 16.dp)
        )
    }
}

@Composable
fun ChartsHeader() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text("Charts", fontSize = 18.sp, fontWeight = FontWeight.SemiBold, color = Color.Gray)
        Text("See All", color = Color(0xFFFF5252), fontWeight = FontWeight.Medium)
    }
}

@Composable
fun CurrencyChart(dataPoints: List<Float>, color: Color) {
    Canvas(modifier = Modifier
        .width(60.dp)
        .height(30.dp)
    ) {
        val width = size.width
        val height = size.height
        val path = Path()
        val points = dataPoints.mapIndexed { index, y ->
            Offset(
                x = index * (width / (dataPoints.size - 1)),
                y = height - (y / dataPoints.maxOrNull()!!) * height
            )
        }

        // Create a smooth curve
        path.moveTo(points.first().x, points.first().y)
        for (i in 1 until points.size) {
            val p1 = points[i - 1]
            val p2 = points[i]
            path.quadraticBezierTo(
                (p1.x + p2.x) / 2f, p1.y,
                p2.x, p2.y
            )
        }

        // Draw the line
        drawPath(
            path = path,
            color = color,
            style = Stroke(
                width = 2.dp.toPx(),
                cap = StrokeCap.Round,
                join = StrokeJoin.Round
            )
        )

        // Draw area under the curve
        val fillPath = Path()
        fillPath.addPath(path)
        fillPath.lineTo(width, height)
        fillPath.lineTo(0f, height)
        fillPath.close()

        drawPath(
            path = fillPath,
            brush = Brush.verticalGradient(
                colors = listOf(color.copy(alpha = 0.2f), color.copy(alpha = 0.0f)),
                startY = 0f,
                endY = height
            )
        )
    }
}

@Composable
fun CurrenciesList(currencies: List<Currency>) {
    LazyColumn(verticalArrangement = Arrangement.spacedBy(16.dp)) {
        items(currencies) { currency ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White)
                    .padding(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .size(60.dp)
                        .background(currency.color, shape = RoundedCornerShape(15.dp))
                        .padding(8.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        painter = painterResource(id = currency.iconResId),
                        contentDescription = "${currency.name} icon",
                        tint = Color.Unspecified,
                        modifier = Modifier.size(40.dp)
                    )
                }
                Spacer(modifier = Modifier.width(16.dp))
                Column(modifier = Modifier.weight(1f)) {
                    Text(currency.name, fontWeight = FontWeight.Bold, color = Color.Black, fontSize = 18.sp)
                    Text(currency.change, color = Color.Green, fontSize = 14.sp)
                }
                Spacer(modifier = Modifier.width(16.dp))

                Box(
                    modifier = Modifier
                        .width(60.dp)
                        .height(30.dp)
                        .background(Color.LightGray, shape = RoundedCornerShape(4.dp))
                ) {
                    CurrencyChart(dataPoints = currency.trendData, color = if (currency.change.startsWith("+")) Color.Green else Color.Red)
                }

                Spacer(modifier = Modifier.width(16.dp))
                Column(horizontalAlignment = Alignment.End) {
                    Text("$${currency.value}", fontWeight = FontWeight.Bold, color = Color.Black, fontSize = 18.sp)
                    Text("${currency.amount} ${currency.name}", color = Color.Gray, fontSize = 14.sp)
                }
            }
        }
    }
}

@Composable
fun BottomNavigationBar(
    isActiveDashboard: Boolean,
    isActiveSync: Boolean,
    isActiveProfile: Boolean,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = if (isActiveDashboard) R.drawable.active_dashboard else R.drawable.inactive_dashboard),
            contentDescription = "Dashboard",
            modifier = Modifier.size(25.dp)
        )
        Image(
            painter = painterResource(id = if (isActiveSync) R.drawable.active_sync else R.drawable.inactive_sync),
            contentDescription = "Sync",
            modifier = Modifier.size(25.dp)
        )
        Image(
            painter = painterResource(id = if (isActiveProfile) R.drawable.active_profile else R.drawable.inactive_profile),
            contentDescription = "Profile",
            modifier = Modifier.size(25.dp)
        )
    }
}
