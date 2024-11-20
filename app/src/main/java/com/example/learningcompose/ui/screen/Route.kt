package com.example.learningcompose.ui.screen


sealed class Route(val route:String){
    data object Home:Route(route = "Home")
    data object Explore:Route(route = "Explore")
    data object Orders:Route(route = "Orders")
    data object Profile:Route(route = "Profile")
}




