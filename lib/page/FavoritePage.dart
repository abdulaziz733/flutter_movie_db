import 'package:flutter/material.dart';

import '../bloc/FavoriteBloc.dart';
import '../model/MovieModel.dart';
import '../utils/widget/adaptive_progress_indicator.dart';

class FavoritePage extends StatefulWidget {
  @override
  State<StatefulWidget> createState() => _FavoritePage();
}

class _FavoritePage extends State<FavoritePage> {
  var deviceSize;

  @override
  void initState() {
    super.initState();
    bloc.fetchAllDataMovies();
  }

  @override
  void dispose() {
    super.dispose();
    bloc.dispose();
  }

  @override
  Widget build(BuildContext context) {
    deviceSize = MediaQuery.of(context).size;
    return Container(
      child: StreamBuilder(
        stream: bloc.allMovies,
        builder: (context, AsyncSnapshot<List<Movie>> snapshot) {
          if (snapshot.hasData) {
            return buildList(snapshot.data);
          } else if (snapshot.hasError) {
            return Text(snapshot.error.toString());
          }
          return Center(child: AdaptiveProgressIndicator());
        },
      ),
    );
  }

  Widget buildList(List<Movie> snapshot) {
    return GridView.builder(
        itemCount: snapshot.length,
        gridDelegate:
            new SliverGridDelegateWithFixedCrossAxisCount(crossAxisCount: 2),
        itemBuilder: (BuildContext context, int index) {
          return GridTile(
            child: InkResponse(
              enableFeedback: true,
              child: movieCard(snapshot[index]),
            ),
          );
        });
  }

  Widget movieCard(Movie movie) {
    var cardHeight = deviceSize.height * 0.8;
    var cardWidth = deviceSize.width * 0.85;
    return Card(
      clipBehavior: Clip.antiAlias,
      elevation: 1.0,
      shape: RoundedRectangleBorder(borderRadius: BorderRadius.circular(15.0)),
      color: Colors.white,
      child: Ink(
        height: cardHeight,
        width: cardWidth,
        child: Stack(
          children: <Widget>[
            Container(
              height: cardHeight - cardHeight / 2 * 1.1,
              width: double.infinity,
              child: Image.network(
                'https://image.tmdb.org/t/p/w185${movie.posterPath}',
                fit: BoxFit.cover,
              ),
            ),
            Align(
              alignment: Alignment.bottomCenter,
              child: Container(
                padding: EdgeInsets.symmetric(vertical: 8.0, horizontal: 14.0),
                color: Colors.brown,
                width: double.infinity,
                child: Text(
                  movie.title,
                  style: TextStyle(fontSize: 14.0, color: Colors.white),
                ),
              ),
            )
          ],
        ),
      ),
    );
  }
}
