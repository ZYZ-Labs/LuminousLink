package org.zyz.luminous_link.ui.scan

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.sik.sikcamera.CameraManager
import com.sik.sikcamera.component.CameraPreview
import com.sik.sikcore.data.GlobalDataTempStore

object ScanScreen {
    /**
     * 关闭扫码界面
     */
    var scanActivityFinish = {}

    /**
     * 扫描的数据
     */
    const val SCAN_DATA = "SCAN_DATA"
}

@Preview
@Composable
fun ScanHomePreview() {
    ScanHome()
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScanHome(navHostController: NavHostController? = null) {
    var cameraManager: CameraManager? = null
    var qrcodeResult by remember {
        mutableStateOf("")
    }
    Scaffold(topBar = {
        TopAppBar(title = {
            Text(text = "二维码扫描")
        }, navigationIcon = {
            IconButton(onClick = {
                cameraManager?.shutdown()
                ScanScreen.scanActivityFinish()
            }) {
                Icon(Icons.AutoMirrored.Default.ArrowBack, contentDescription = "返回")
            }
        }, modifier = Modifier.shadow(elevation = 6.dp))
    }) { contentPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(contentPadding)
        ) {
            CameraPreview(modifier = Modifier.fillMaxSize(), useCameraManager = {
                cameraManager = this
                addImageAnalyzer(QRCodeImageAnalyzer {
                    if (it.isNotEmpty()) {
                        qrcodeResult = it
                        GlobalDataTempStore.getInstance().saveData(ScanScreen.SCAN_DATA, it)
                        cameraManager?.shutdown()
                        ScanScreen.scanActivityFinish()
                    }
                })
            })
        }
    }
}