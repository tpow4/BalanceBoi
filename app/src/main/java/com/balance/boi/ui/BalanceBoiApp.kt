package com.balance.boi.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.balance.boi.AccountViewModel
import com.balance.boi.R
import com.balance.boi.database.data.AccountWithBalances

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun NewAccountScreen(navController: NavController, viewModel: AccountViewModel) {
    var accountType by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var expanded by remember { mutableStateOf(false) }

    val institutions by viewModel.allInstitutions.observeAsState(emptyList())
    val institutionOptions = institutions.map { it.institutionName }
    var selectedOptionText by remember { mutableStateOf("Select institution...") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(stringResource(R.string.new_account_title)) },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.Default.Close,
                            contentDescription = "Close"
                        )
                    }
                },
                actions = {
                    IconButton(onClick = {
//                        viewModel.insertAccount(
//                            Account(
//                                accountName = accountName,
//                                accountInstitutionId = 1,
//                                accountTaxType =
//                            )
//                        )
                        navController.popBackStack()
                    }) {
                        Icon(Icons.Filled.Done, "Save")
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(16.dp)
        )
        {
            ExposedDropdownMenuBox(
                expanded = expanded,
                onExpandedChange = { expanded = !expanded }) {
                OutlinedTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .menuAnchor(),
                    value = selectedOptionText,
                    onValueChange = { },
                    readOnly = true,
                    label = { Text(stringResource(R.string.new_account_institution)) },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.ShoppingCart,
                            contentDescription = null
                        )
                    },
                    trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
                    supportingText = { Text(stringResource(R.string.institution_helper)) }
                )
                ExposedDropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false }
                )
                {
                    institutionOptions.forEach { selectionOption ->
                        DropdownMenuItem(
                            text = { Text(selectionOption) },
                            onClick = {
                                selectedOptionText = selectionOption
                                expanded = false
                            },
                            contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = accountType,
                onValueChange = { accountType = it },
                label = { Text(stringResource(R.string.new_account_type)) },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.AccountBox,
                        contentDescription = null
                    )
                },
                modifier = Modifier.fillMaxWidth(),
                supportingText = { Text(stringResource(R.string.new_account_helper)) }
            )

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = description,
                onValueChange = { description = it },
                label = { Text(stringResource(R.string.account_description)) },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Menu,
                        contentDescription = null
                    )
                },
                modifier = Modifier.fillMaxWidth(),
            )
        }
    }
}


@Composable
private fun AccountCard(accountWithBalances: AccountWithBalances) {
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
        Row(
            verticalAlignment = Alignment.CenterVertically, modifier = Modifier
                .fillMaxWidth()
                .padding(24.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_launcher_background),
                contentDescription = "icon placeholder",
                modifier = Modifier
                    .size(50.dp)
                    .clip(CircleShape)
                    .border(1.5.dp, MaterialTheme.colorScheme.primary, CircleShape)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(text = accountWithBalances.account.accountName)
            }
            Text(
                text = accountWithBalances.balances.last().balanceAmount.toString(),
                fontSize = 28.sp
            )
        }
    }
}

@Composable
private fun Accounts(
    modifier: Modifier = Modifier,
    accountViewModel: AccountViewModel
) {
    val accounts by accountViewModel.allAccounts.observeAsState(emptyList())
    LazyColumn(modifier = modifier.padding(vertical = 4.dp)) {
        items(accounts) { accountWithBalances ->
            AccountCard(accountWithBalances)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun OverviewScreen(navController: NavController, viewModel: AccountViewModel) {
    Scaffold(
        topBar = {
            TopAppBar(title = {
                Text("Overview")
            },
                actions = {
                    IconButton(onClick = { navController.navigate(Screen.Settings.route) }) {
                        Icon(Icons.Filled.Settings, contentDescription = "Settings icon")
                    }
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { navController.navigate(Screen.AddAccount.route) },
                containerColor = MaterialTheme.colorScheme.secondaryContainer,
                contentColor = MaterialTheme.colorScheme.secondary
            ) {
                Icon(Icons.Filled.Add, "Small floating action button.")
            }
        }
    ) { padding ->
        // Screen content
        Surface(
            modifier = Modifier
                .fillMaxSize(), color = MaterialTheme.colorScheme.background
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
            )
            {
                Accounts(accountViewModel = viewModel)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Settings") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.Default.Close,
                            contentDescription = "Close"
                        )
                    }
                },
            )
        }
    ) { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        )
    }
}

@Composable
fun BalanceBoiApp() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screen.Overview.route
    ) {
        composable(Screen.Overview.route) {
            val viewModel: AccountViewModel = hiltViewModel()
            OverviewScreen(navController, viewModel)
        }
        composable(Screen.AddAccount.route) {
            val viewModel: AccountViewModel = hiltViewModel()
            NewAccountScreen(navController, viewModel)
        }

        composable(Screen.Settings.route) {
            SettingsScreen(navController)
        }
    }
}