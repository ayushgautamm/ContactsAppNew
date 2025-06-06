package com.example.loginactivityshared

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.*
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.loginactivityshared.NaviGation.DrawerContent
import com.example.loginactivityshared.ui.theme.LoginActivitySharedTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            LoginActivitySharedTheme {
                val authViewModel: AuthViewModel by viewModels()
                val snackbarHostState = remember { SnackbarHostState() }

                val drawerState = rememberDrawerState(DrawerValue.Closed)
                val scope = rememberCoroutineScope()

                ModalNavigationDrawer(
                    drawerState = drawerState,
                    drawerContent = {
                        Box(
                            Modifier
                                .fillMaxHeight()
                                .width(200.dp)  // <-- fix drawer width here (e.g., half screen)
                                .background(Color.White)  // Set drawer background explicitly
                        ) {
                            DrawerContent()
                        }
                    }
                ) {
                    Scaffold(
                        modifier = Modifier.fillMaxSize(),
                        snackbarHost = { SnackbarHost(snackbarHostState) }
                    ) { innerPadding ->
                        MyAppNavigation(
                            modifier = Modifier.padding(innerPadding),
                            authViewModel = authViewModel,
                            drawerState = drawerState, // ✅ Pass the drawer state
                            scope = scope                // ✅ Pass coroutine scope
                        )
                    }
                }
            }
        }

    }
}