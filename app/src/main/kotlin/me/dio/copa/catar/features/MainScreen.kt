package me.dio.copa.catar.features

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import me.dio.copa.catar.R
import me.dio.copa.catar.domain.extensions.getDate
import me.dio.copa.catar.domain.model.MatchDomain
import me.dio.copa.catar.domain.model.TeamDomain
import me.dio.copa.catar.ui.theme.Shapes

typealias NotificationClick =  (match: MatchDomain) -> Unit

@Composable
fun MainScreen(matches: List<MatchDomain>, onNotificationClick: NotificationClick) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(matches.size) { match ->
                MatchInfo(match = matches[match],onNotificationClick)

            }
        }
    }

}

@Composable
fun MatchInfo(match: MatchDomain, onNotificationClick: NotificationClick) {
    Card(
        shape = Shapes.large,
        modifier = Modifier.fillMaxWidth()
    ) {
        Box {
            AsyncImage(
                model = match.stadium.image,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.height(160.dp)
            )
        }
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Notification(match, onNotificationClick)
            Title(match)
            Teams(match)
        }
    }
}

@Composable
fun Notification(match: MatchDomain, onClick: NotificationClick) {
    val drawable = if (match.notificationEnabled)
        R.drawable.ic_notifications_active
    else R.drawable.ic_notifications

    Row (
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.End

    ) {
        Image(
            painter = painterResource(id = drawable),
            modifier = Modifier.clickable {
            onClick(match)
            },
            contentDescription = null
        )
    }
}

@Composable
fun Title(match: MatchDomain) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ) {
        Text(
            text = "${match.date.getDate()} - ${match.name}",
            style = MaterialTheme.typography.h6.copy(
                color = Color.White
            )
        )
    }
}


@Composable
fun Teams(match: MatchDomain) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        TeamItem(team = match.team1)
        Text(
            text = "X",
            modifier = Modifier.padding(end = 16.dp, start = 16.dp),
            style = MaterialTheme.typography.h6.copy(color = Color.White)
        )
        TeamItem(team = match.team2)
    }

}


@Composable
fun TeamItem(team: TeamDomain) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Text(
            text = team.flag,
            modifier = Modifier
                .align(Alignment.CenterVertically),
            style = MaterialTheme.typography.h3.copy(
                color = Color.White
            )
        )
        Spacer(modifier = Modifier.size(16.dp))

        Text(
            text = team.displayName,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.h6.copy(
                color = Color.White
            )
        )
    }

}
