import 'package:fancy_bottom_navigation/fancy_bottom_navigation.dart';
import 'package:flutter/material.dart';

import './FavoritePage.dart';
import './MoviePage.dart';
import './ProfilePage.dart';

class HomePage extends StatefulWidget {
  @override
  State<StatefulWidget> createState() => _HomePageState();
}

class _HomePageState extends State<HomePage> {
  int currentPage = 0;
  String title = "Movie";

  var pages = [
    MoviePage(),
    FavoritePage(),
    ProfilePage(),
  ];

  @override
  Widget build(BuildContext context) {
    return Scaffold(
        appBar: AppBar(
          title: Text(title),
          elevation:
              Theme.of(context).platform == TargetPlatform.android ? 4.0 : 0.0,
        ),
        body: pages[currentPage],
        bottomNavigationBar: FancyBottomNavigation(
          initialSelection: currentPage,
          tabs: [
            TabData(iconData: Icons.movie, title: "Movie"),
            TabData(iconData: Icons.favorite, title: "Favorite"),
            TabData(iconData: Icons.person, title: "Profile")
          ],
          onTabChangedListener: (position) {
            setState(() {
              currentPage = position;
              switch (currentPage) {
                case 0:
                  title = "Movie";
                  break;
                case 1:
                  title = "Favorite";
                  break;
                case 2:
                  title = "Profile";
                  break;
                default:
                  title = "Movie";
              }
            });
          },
        ));
  }
}
