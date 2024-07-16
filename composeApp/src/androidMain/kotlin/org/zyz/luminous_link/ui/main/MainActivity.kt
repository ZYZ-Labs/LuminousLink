package org.zyz.luminous_link.ui.main

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavHostController
import com.sik.sikcomposebase.base.BaseActivity
import com.sik.sikcomposebase.base.Screen
import org.zyz.luminous_link.ui.scan.ScanScreen

class MainActivity : BaseActivity() {
    @Composable
    override fun initContent(navController: NavHostController) {
        addScreen(Screen("home") { MainHome(navController) })
    }
}