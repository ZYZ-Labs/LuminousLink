package org.zyz.luminous_link.ui.scan

import androidx.camera.core.ImageAnalysis.Analyzer
import androidx.camera.core.ImageProxy
import com.sik.sikimage.QRCodeUtils

/**
 * 二维码分析器
 */
class QRCodeImageAnalyzer(private val analyzerSuccess: (String) -> Unit) : Analyzer {
    override fun analyze(image: ImageProxy) {
        analyzerSuccess(QRCodeUtils.readQRCode(image.toBitmap()))
    }
}