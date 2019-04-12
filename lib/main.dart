import 'package:flutter/material.dart';
import 'utils/adaptive_theme.dart';
import 'page/HomePage.dart';

void main() => runApp(MyApp());

class MyApp extends StatelessWidget {
  // This utils.widget is the root of your application.
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Flutter Demo',
      theme: getAdaptiveTheme(context),
      home: HomePage(),
    );
  }
}
