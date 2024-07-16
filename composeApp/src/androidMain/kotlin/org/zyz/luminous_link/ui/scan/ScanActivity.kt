package org.zyz.luminous_link.ui.scan

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavHostController
import com.sik.sikcomposebase.base.BaseActivity
import com.sik.sikcomposebase.base.Screen

class ScanActivity : BaseActivity() {
    @Composable
    override fun initContent(navController: NavHostController) {
        addScreen(Screen("home") { ScanHome(navController) })
        LaunchedEffect(key1 = Unit) {
            ScanScreen.scanActivityFinish = {
                finish()
            }
        }
    }
}