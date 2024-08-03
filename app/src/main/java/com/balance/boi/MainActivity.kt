package com.balance.boi

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.balance.boi.ui.theme.BalanceBoiTheme
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SmallFloatingActionButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.balance.boi.database.data.Account
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BalanceBoiTheme {
                // A surface container using the 'background' color from the theme
                Scaffold(
                    topBar = {
                        TopAppBar(title = {
                            Text("Overview")
                        })
                    },
                    floatingActionButton = {
                        FloatingActionButton(
                            onClick = {  },
                            containerColor = MaterialTheme.colorScheme.secondaryContainer,
                            contentColor = MaterialTheme.colorScheme.secondary
                        ) {
                            Icon(Icons.Filled.Add, "Small floating action button.")
                        }
                    }
                ) { contentPadding ->
                    // Screen content
                    Surface(modifier = Modifier.fillMaxSize().padding(contentPadding), color = MaterialTheme.colorScheme.background) {
                        Accounts()
                    }
                }
            }
        }
    }
}

@Composable
private fun Account(account: Account) {
    val isSelected by remember {
        mutableStateOf(false)
    }
    Card(
        colors = CardDefaults.cardColors(
            containerColor =
            if (isSelected) MaterialTheme.colorScheme.primaryContainer
            else
                MaterialTheme.colorScheme.surfaceColorAtElevation(2.dp)
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 10.dp
        ),
        modifier = Modifier.padding(vertical = 4.dp, horizontal = 8.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier
            .fillMaxWidth()
            .padding(24.dp)
        ) {
            Image(painter = painterResource(id = R.drawable.ic_launcher_background),
                contentDescription = "icon placeholder",
                modifier = Modifier
                    .size(50.dp)
                    .clip(CircleShape)
                    .border(1.5.dp, MaterialTheme.colorScheme.primary, CircleShape)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(text = account.name)
            }
            Text(text = 0.toString(), fontSize = 28.sp)
        }
    }
}

@Composable
private fun Accounts(
    modifier: Modifier = Modifier,
    accountViewModel: AccountViewModel = viewModel()
) {
    val accounts by accountViewModel.allAccounts.observeAsState(emptyList())
    LazyColumn(modifier = modifier.padding(vertical = 4.dp)) {
        items(accounts) { account ->
            Account(account)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingsPreview() {
    BalanceBoiTheme {
        Accounts()
    }
}