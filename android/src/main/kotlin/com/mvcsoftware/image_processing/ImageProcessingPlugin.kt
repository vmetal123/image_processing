package com.mvcsoftware.image_processing

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.util.DisplayMetrics
import io.flutter.plugin.common.MethodCall
import io.flutter.plugin.common.MethodChannel
import io.flutter.plugin.common.MethodChannel.MethodCallHandler
import io.flutter.plugin.common.MethodChannel.Result
import io.flutter.plugin.common.PluginRegistry.Registrar
import java.io.ByteArrayOutputStream

class ImageProcessingPlugin: MethodCallHandler {
  companion object {
    @JvmStatic
    fun registerWith(registrar: Registrar) {
      val channel = MethodChannel(registrar.messenger(), "image_processing")
      channel.setMethodCallHandler(ImageProcessingPlugin())
    }
  }

  override fun onMethodCall(call: MethodCall, result: Result) {
    if (call.method == "getPlatformVersion") {
      result.success("Android ${android.os.Build.VERSION.RELEASE}")
    } else {
      result.notImplemented()
    }

    if(call.method == "combineImages") {
      val firstImage: ByteArray? = call.argument("firstImage")
      val secondImage: ByteArray? = call.argument("secondImage")
      result.success(combineImages(firstImage, secondImage))
    }
  }

  private fun combineImages(firstImage: ByteArray?, secondImage: ByteArray?): ByteArray? {
    val firstBitmap: Bitmap = BitmapFactory.decodeByteArray(firstImage, 0, firstImage!!.size)
    val secondBitmap: Bitmap = BitmapFactory.decodeByteArray(secondImage, 0, secondImage!!.size)

    val result: Bitmap = Bitmap.createBitmap(firstBitmap.width, firstBitmap.height, firstBitmap.config)

    var canvas = Canvas(result)

    canvas.drawBitmap(firstBitmap, 0f, 0f, null)
    canvas.drawBitmap(secondBitmap, 10f, 10f, null)

    var resultBiteArray = ByteArrayOutputStream()
    result.compress(Bitmap.CompressFormat.PNG, 100, resultBiteArray)
    return resultBiteArray.toByteArray()
  }
}
