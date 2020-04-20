import 'dart:async';

import 'package:flutter/services.dart';

class Dashcam {
  static const MethodChannel _channel =
      const MethodChannel('dashcam');

  static Future<String> get platformVersion async {
    final String version = await _channel.invokeMethod('getPlatformVersion');
    return version;
  }

  static Future<String> configureCamera(
      int width,
      int height,
      double frameRate) async {
    return await _channel.invokeMethod(
      "configureCamera",
      {"width": width, "height": height, "frameRate": frameRate}
    );
  }
}
