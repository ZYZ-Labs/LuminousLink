package org.zyz.luminous_link.ui.main

import android.content.Intent
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.sik.sikcomposebase.utils.addObserver
import com.sik.sikcore.data.GlobalDataTempStore
import com.sik.sikcore.permission.PermissionUtils
import org.zyz.luminous_link.R
import org.zyz.luminous_link.data.CommonData
import org.zyz.luminous_link.ui.scan.ScanActivity
import org.zyz.luminous_link.ui.scan.ScanScreen

@Preview
@Composable
fun MainHomePreview() {
    MainHome()
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainHome(navHostController: NavHostController? = null) {
    val context = LocalContext.current
    val requireCameraPermission = remember {
        mutableStateOf(false)
    }
    var scanData by remember { mutableStateOf("") }
    LocalLifecycleOwner.current.addObserver(
        onResume = {
            scanData = (GlobalDataTempStore.getInstance()
                .getData(ScanScreen.SCAN_DATA, false) as? String) ?: ""
        }
    )
    Scaffold(topBar = {
        TopAppBar(modifier = Modifier.shadow(elevation = 6.dp), title = {
            Text(text = stringResource(id = R.string.app_name))
        }, actions = {
            Row {
                IconButton(onClick = {
                    requireCameraPermission.value = true
                }) {
                    Icon(Icons.Default.Add, contentDescription = "添加服务")
                }
            }
        })
    }) { contentPadding ->
        Column(
            modifier = Modifier.padding(contentPadding),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(text = scanData)
        }
    }
    PermissionUtils.RequestPermissions(CommonData.cameraPermission, requireCameraPermission) {
        if (it) {
            context.startActivity(Intent(context, ScanActivity::class.java))
        } else {
            Toast.makeText(context, "请先赋予相机权限", Toast.LENGTH_SHORT).show()
        }
    }
}