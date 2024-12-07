package com.example.nighteventsapp.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.nighteventsapp.models.eventList

@Composable
fun SubscribedEventsScreen(navController: NavController) {
    val subscribedEvents = eventList.filter { it.isSubscribed.value }

    LazyColumn(
        modifier = Modifier.padding(16.dp)
    ) {
        if (subscribedEvents.isEmpty()) {
            item {
                Text(
                    text = "Você ainda não está inscrito em nenhum evento.",
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.padding(16.dp)
                )
            }
        } else {
            items(subscribedEvents) { event ->
                Card(
                    modifier = Modifier
                        .padding(vertical = 8.dp)
                        .clickable {
                            navController.navigate("eventDetails/${event.id}")
                        },
                    elevation = CardDefaults.elevatedCardElevation(4.dp)
                ) {
                    Row(
                        modifier = Modifier.padding(16.dp)
                    ) {
                        Image(
                            painter = painterResource(id = event.imageRes),
                            contentDescription = "Imagem do evento",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .size(80.dp)
                                .padding(end = 16.dp)
                        )

                        Column(
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text(
                                text = event.title,
                                style = MaterialTheme.typography.titleMedium
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                text = event.date,
                                style = MaterialTheme.typography.bodySmall
                            )
                            Text(
                                text = event.location,
                                style = MaterialTheme.typography.bodySmall
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(
                                text = event.description,
                                style = MaterialTheme.typography.bodySmall,
                                maxLines = 2
                            )
                        }
                    }
                }
            }
        }
    }
}
