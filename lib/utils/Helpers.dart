import 'package:flutter/services.dart';

class Helpers {
  final _platformChannel = MethodChannel('movie-db');

  Future<String> getBatteryLevel() async {
    String batteryLevel;
    try {
      final int result = await _platformChannel.invokeMethod('getBatteryLevel');
      batteryLevel = 'Battery level is $result %. ';
      return result.toString();
    } catch (error) {
      var msg = 'Failed to get battery level yet';
      throw Exception(msg);
    }
  }
}

final helpers = Helpers();
