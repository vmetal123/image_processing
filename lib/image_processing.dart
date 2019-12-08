import 'dart:async';
import 'dart:typed_data';

import 'package:flutter/services.dart';

class ImageProcessing {
  static const MethodChannel _channel =
      const MethodChannel('image_processing');

  static Future<String> get platformVersion async {
    final String version = await _channel.invokeMethod('getPlatformVersion');
    return version;
  }
  
  static Future<Uint8List> combineImages(Uint8List firstImage, Uint8List secondImage) async {
    final imageResult = await _channel.invokeMethod('combineImages', <String, dynamic> {
      'firstImage': firstImage,
      'secondImage': secondImage
    });
    return imageResult;
  }

  static Future<String> getMessage() async {
    final message = await _channel.invokeMethod('getMessage');
    return message;
  }
}
