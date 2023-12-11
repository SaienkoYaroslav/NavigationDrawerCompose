package com.example.navigationdrawercompose

import android.graphics.drawable.Icon
import android.widget.Space
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Button
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Drawer() {
    val items = listOf<DrawerItem>(
        DrawerItem(
            Icons.Default.Favorite,
            "Favorite"
        ),
        DrawerItem(
            Icons.Default.Add,
            "Add"
        ),
        DrawerItem(
            Icons.Default.AccountBox,
            "Account"
        )
    )
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    val selectedItem = remember {
        mutableStateOf(items[0])
    }

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet {
                Row() {
                    Text(text = "MENU")
                    Button(
                        onClick = {

                        }
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.baseline_close_24),
                            contentDescription = "Close",
                            modifier = Modifier
                                .width(70.dp)
                                .height(50.dp)
                        )
                    }
                }
                Spacer(modifier = Modifier.height(15.dp))
                items.forEach { item ->
                    // make by myself!
                    NavigationDrawerItem(
                        label = { 
                                Text(text = item.title)
                        },
                        selected = selectedItem.value == item,
                        icon = {
                               Icon(
                                   imageVector = item.imgVector,
                                   contentDescription = item.title
                               )
                        },
                        onClick = {
                            // close drawer when click on item
                            scope.launch {
                                selectedItem.value = item
                                drawerState.close()
                            }
                        }
                    )
                }

            }
        },
        content = {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Button(
                    onClick = {
                        scope.launch {
                            drawerState.open()
                        }
                    }
                ) {

                }
            }
        }
    )

}

data class DrawerItem(
    val imgVector: ImageVector,
    val title: String,

    )