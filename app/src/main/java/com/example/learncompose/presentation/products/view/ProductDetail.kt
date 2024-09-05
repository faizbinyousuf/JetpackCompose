package com.example.learncompose.presentation.products.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.learncompose.domain.model.Meal

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductDetail(product: Meal?) {
    Surface(

    ) {
        Scaffold(

            topBar = {
                TopAppBar(
                    title = {
                        Text("dssd")
                    }
                )
            }
        ) { innerPadding ->

            product?.let {
                Column(
                    modifier = Modifier
                        .padding(innerPadding)
                        .fillMaxSize()


                )
                {

                    FoodDetailsScreen(product)

                }
            }

        }
    }

}

@Composable
fun FoodDetailsScreen(meal: Meal) {
    BoxWithConstraints(modifier = Modifier.fillMaxSize()) {

        val screenHeight = maxHeight
        val cardOffsetY = screenHeight * 0.45f

        // Background Image
        Image(
            painter = rememberAsyncImagePainter(meal.strMealThumb),
            contentDescription = "Food Image",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .height(screenHeight * 0.5f)
        )

        // Floating Card
        Card(
            shape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp),
            modifier = Modifier
                .fillMaxWidth()

                .offset(y = cardOffsetY)

        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                // Dish Name and Price
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = meal.strMeal.toString(),

                        fontWeight = FontWeight.Bold
                    )
//                    Text(
//                        text = "$13.00",
//
//                        color = Color.Red,
//                        fontWeight = FontWeight.Bold
//                    )
                }

                // Subtitle (e.g., description or location)
                Text(
                    text = meal.idMeal.toString(),

                    color = Color.Gray
                )

                // Rating, Favorites, and Photo Icons
                Spacer(modifier = Modifier.height(8.dp))


                // Details Section
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Details",

                    fontWeight = FontWeight.Bold
                )
                Text(
                    meal.strMeal.toString(),
                    color = Color.Gray,
                    maxLines = 4,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }


    }
}

//@Preview(showBackground = true, showSystemUi = true)
//@Composable
//fun ProductDetailPreview() {
//    ProductDetail()
//}