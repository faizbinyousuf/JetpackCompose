package com.example.learncompose.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.learncompose.domain.model.Meal
import com.example.learncompose.presentation.products.view.ProductDetail
import com.example.learncompose.presentation.products.view.ProductView



@OptIn(ExperimentalMaterial3Api::class)
@Composable

fun MainScreen() {
    val navController = rememberNavController()


    val navItems = listOf(
        BottomNavigationItem(
            title = "Home",
            route = "Home",
            selectedIcon = Icons.Filled.Home,
            unselectedIcon = Icons.Outlined.Home,
            hasNews = false,
        ),
        BottomNavigationItem(
            title = "Chat",
            route = "Chat",
            selectedIcon = Icons.Filled.Email,
            unselectedIcon = Icons.Outlined.Email,
            hasNews = false,
            badgeCount = 45
        ),
        BottomNavigationItem(
            title = "Settings",
            route = "Settings",
            selectedIcon = Icons.Filled.Settings,
            unselectedIcon = Icons.Outlined.Settings,
            hasNews = true,
        ),
    )
    var selectedItemIndex by rememberSaveable {
        mutableStateOf(0)
    }

    Scaffold(

//        topBar = {
//            TopAppBar(
//
//                title = {
//                    Row(
//                        horizontalArrangement = Arrangement.SpaceBetween,
//                        verticalAlignment = Alignment.Bottom,
//                        modifier = Modifier
//                            .fillMaxWidth()
//                            .padding(end = 15.dp, bottom = 0.dp)
//                    ) {
//                        Icon(
//                            imageVector = Icons.Default.Menu,
//                            tint = colorResource(id = R.color.white),
//                            contentDescription = null
//                        )
//                        Text("The App",
//                            fontSize = 18.sp,
//                            color = Color.White,
//                            fontWeight = FontWeight.Bold,
//                            textAlign = TextAlign.End,
//
//                            )
//                        Box(
//                            modifier = Modifier
//                                .size(20.dp)
//
//                        ) {
//                            Icon(
//                                imageVector = Icons.Default.Favorite,
//                                contentDescription = null,
//                                tint = colorResource(id = R.color.white),
//                            )
//                        }
//                    }
//                },
//                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
//                    titleContentColor = Color.Red,
//                    containerColor = PurpleGrey80,
//
//                )
//
//            )
//        },

        bottomBar = {

            NavigationBar {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentDestination = navBackStackEntry?.destination
                navItems.forEachIndexed { index, item ->
                    NavigationBarItem(
                        selected = currentDestination?.route == item.route,
                        onClick = {
                           // selectedItemIndex = index
                            navController.navigate(item.title){
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                // Restore state when reselecting a previously selected item
                                restoreState = true
                            }

                        },
                        label = {
                            Text(text = item.title)
                        },
                        alwaysShowLabel = false,
                        icon = {
                            BadgedBox(
                                badge = {
                                    if (item.badgeCount != null) {
                                        Badge {
                                            Text(text = item.badgeCount.toString())
                                        }
                                    } else if (item.hasNews) {
                                        Badge()
                                    }
                                }
                            ) {
                                Icon(
                                    imageVector = if (index == selectedItemIndex) {
                                        item.selectedIcon
                                    } else item.unselectedIcon,
                                    contentDescription = item.title
                                )
                            }
                        }
                    )
                }
            }


        }
    ) { innerPadding ->

        Box(modifier = Modifier.padding(innerPadding)) {
            NavigationHost(navController = navController)
        }

    }
}


@Composable
fun NavigationHost(navController: NavHostController) {
    NavHost(navController, startDestination = "Home") {
        composable("Home") { ProductView(navController = navController) }
        composable("prodDetail") {
            val productObject: Meal? =
                navController.previousBackStackEntry?.savedStateHandle?.get("product")
            ProductDetail(productObject)
        }
        composable("Chat") { OrdersScreen() }
        composable("Settings") { ProfileScreen() }
    }
}

@Composable
fun HomeScreen() {
    Text(text = "Home Screen")
}

@Composable
fun OrdersScreen() {
    Text(text = "Orders Screen")
}

@Composable
fun ProfileScreen() {
    Text(text = "Profile Screen")
}


data class BottomNavigationItem(
    val title: String,
    val route : String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    val hasNews: Boolean,
    val badgeCount: Int? = null
) {

}

