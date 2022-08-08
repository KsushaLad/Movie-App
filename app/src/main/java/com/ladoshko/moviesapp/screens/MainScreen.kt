package com.ladoshko.moviesapp.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.ladoshko.moviesapp.MainViewModel
import com.ladoshko.moviesapp.data.models.Movies
import com.ladoshko.moviesapp.navigation.Screens

@Composable
fun MainScreen(navController: NavController, viewModel: MainViewModel) {
    val allMovies = viewModel.allMovies.observeAsState(listOf()).value
    Surface(modifier = Modifier.fillMaxSize()) {
        LazyColumn (modifier = Modifier.padding(20.dp)){
            items(allMovies.take(allMovies.size)){ item ->
                MoviesItem(item = item, navController = navController)
            }
        }
    }
}

@Composable
fun MoviesItem(item: Movies, navController: NavController) {
    Card(
        elevation = 14.dp,
        shape = RoundedCornerShape(20.dp),
        modifier = Modifier
            .padding(top = 15.dp)
            .clickable {
                navController.navigate(Screens.Details.route + "/${item.id}")
            }
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 15.dp)
        ) {
            Image(
                painter = rememberImagePainter(item.image.original),
                contentDescription = null,
                modifier = Modifier.size(128.dp)
            )
            Column {
                Text(
                    text = item.name,
                    fontSize = 23.sp,
                    fontWeight = FontWeight.ExtraBold
                )
                Row {
                    Text(
                        text = "Rating: ",
                        fontWeight = FontWeight.Bold
                    )
                    Text(text = item.rating.average.toString())
                }
                Row {
                    Text(
                        text = "Genre: ",
                        fontWeight = FontWeight.Bold
                    )
                    item.genres.take(2).forEach { Text(text = " $it ") }
                }
                Row {
                    Text(
                        text = "Premiered: ",
                        fontWeight = FontWeight.Bold
                    )
                    Text(text = item.premiered)
                }
            }
        }
    }
}