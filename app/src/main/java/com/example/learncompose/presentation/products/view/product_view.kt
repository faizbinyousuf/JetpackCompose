package com.example.learncompose.presentation.products.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberAsyncImagePainter
import com.example.learncompose.domain.model.Meal
import com.example.learncompose.presentation.products.viewmodel.ProductEvent
import com.example.learncompose.presentation.products.viewmodel.ProductState
import com.example.learncompose.presentation.products.viewmodel.ProductViewModel


@Composable
fun ProductView(viewModel: ProductViewModel = hiltViewModel(), navController: NavHostController) {


    val state by viewModel.state

    LaunchedEffect(Unit) {
        viewModel.onEvent(ProductEvent.LoadCategories)
    }

    Box() {
        Column(
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White),

            ) {
            if (state.isLoading) {
//                    Text("Loading...pls wait")
                CircularProgressIndicator()
            } else {
                CategorySection(modifier = Modifier, state = state)
                ProductSection(navController = navController)
            }


        }
    }


}

@Composable
fun CategorySection(modifier: Modifier = Modifier, state: ProductState) {
    val cats = state.categoryResponse?.categories ?: emptyList()

    val viewModel: ProductViewModel = hiltViewModel()

    LazyRow(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp)

    ) {
        items(cats) {

                c ->
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
                    .clickable {
//                        onClick()
                        viewModel.onEvent(ProductEvent.LoadProductsByCategory(c.strCategory.toString()))
                    },

                ) {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = modifier
                        //                    .size(60.dp)
                        //                    .padding( 20.dp)
                        .width(100.dp)
                        .height(70.dp)
                        .border(
                            color = Color.LightGray,
                            width = 1.dp,
                            shape = RoundedCornerShape(8.dp)
                        )
                        .padding(8.dp)


                ) {
                    val imageUrl = c.strCategoryThumb

                    // Use Coil to load the image from the URL
                    val painter = rememberAsyncImagePainter(imageUrl)


                    Image(
                        painter = painter, contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.fillMaxSize()

                    )


                }
                Text(
                    c.strCategory.toString(),
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }

}


@Composable
fun ProductSection(viewModel: ProductViewModel = hiltViewModel(), navController: NavHostController) {

    val state by viewModel.state

    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {

        val products = state.productModel?.meals ?: emptyList()

        items(products) { it ->
            ProductCard(product = it, onTap = {

                navController.currentBackStackEntry?.savedStateHandle?.set("product", it)

                navController.navigate("prodDetail") {
                    popUpTo(navController.graph.findStartDestination().id) {
                        saveState = true
                    }
                    launchSingleTop = true
                    restoreState = true
                }
            })


        }
    }
}

@Composable
fun ProductCard(
    product: Meal,
    onTap: () -> Unit
) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = Color.White,

            ),
        modifier = Modifier
//            .width(150.dp)
            .padding(8.dp)
            .clickable {
                onTap()
            },

        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 4.dp
        )
    ) {
        Column(
            horizontalAlignment = Alignment.Start,
            modifier = Modifier.padding(8.dp)
        ) {
            // Image
            Image(
                painter = rememberAsyncImagePainter(product.strMealThumb),
                contentDescription = null,
                modifier = Modifier
                    .height(100.dp)
                    .fillMaxWidth()
                    .clip(shape = RoundedCornerShape(10.dp)),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Title
            Text(
                text = product.strMeal.toString(),
                overflow = TextOverflow.Ellipsis,
                fontWeight = FontWeight.Bold,
                maxLines = 1,
                fontSize = 12.sp,
                //style = MaterialTheme.typography.h6,
                textAlign = TextAlign.Left
            )

            // Subtitle
            Text(
                text = product.idMeal.toString(),
                overflow = TextOverflow.Ellipsis,

                maxLines = 1,
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                //style = MaterialTheme.typography.body2,
                color = Color.Gray,
                textAlign = TextAlign.Left
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Price and Add Button Row
//            Row(
//                modifier = Modifier.fillMaxWidth(),
//                horizontalArrangement = Arrangement.SpaceBetween,
//                verticalAlignment = Alignment.CenterVertically
//            ) {
//                Text(
//                    text = "12.0",
//                   // style = MaterialTheme.typography.h6,
//                    color = Color.Red
//                )
//
////                IconButton(onClick = onAddClick) {
////                    Icon(
////                        imageVector = Icons.Default.Add,
////                        contentDescription = "Add to cart"
////                    )
////                }
//            }
        }
    }
}

//@Preview(showSystemUi = true, showBackground = true)
//@Composable
//fun MainScreenPreview() {
//    LearnComposeTheme {
//        ProductView()
//    }
//
//}



