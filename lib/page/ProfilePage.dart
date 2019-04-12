import 'package:flutter/material.dart';
import 'package:percent_indicator/percent_indicator.dart';
import 'package:flutter/services.dart';

class ProfilePage extends StatefulWidget {
  @override
  State<StatefulWidget> createState() => _ProfilePage();
}

class _ProfilePage extends State<ProfilePage> {
  final _platformChannel = MethodChannel('movie-db');
  double batteryLevel = 0.0;
  String batteryLevelText = "";

  Future<Null> _getBatteryLevel() async {
    try {
      final int result = await _platformChannel.invokeMethod('getBatteryLevel');
      setState(() {
        batteryLevel = result * 0.01;
        batteryLevelText = "$result %";
      });
//      batteryLevel = 'Battery level is $result %. ';
    } catch (error) {
//      batteryLevel = 'Failed to get battery level yet';
    }
    print(batteryLevel);
  }

  @override
  void initState() {
    super.initState();
    _getBatteryLevel();
  }

  @override
  Widget build(BuildContext context) {
    return Center(
      child: CircularPercentIndicator(
        radius: 120.0,
        lineWidth: 13.0,
        animation: true,
        percent: batteryLevel,
        center: new Text(
          batteryLevelText,
          style: new TextStyle(fontWeight: FontWeight.bold, fontSize: 20.0),
        ),
        footer: new Text(
          "Battery Level",
          style: new TextStyle(fontWeight: FontWeight.bold, fontSize: 17.0),
        ),
        circularStrokeCap: CircularStrokeCap.round,
        progressColor: Colors.blueAccent,
      ),
    );
  }
}
