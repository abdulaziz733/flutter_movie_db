import 'dart:async';
import 'dart:convert';

import 'package:flutter/services.dart';

import '../apiProvider/ApiMovieProvider.dart';
import '../model/MovieModel.dart';

class MovieRepository {
  final movieDataProvider = ApiMovieProvider();
  final _platformChannel = MethodChannel('movie-db');

  Future<MovieModel> fetchAllMovies() => movieDataProvider.fetchDataMovie();

  Future<Null> saveFavorite(Movie movie) async {
    try {
      final String result = await _platformChannel.invokeMethod(
        'movieAddFavorite',
        <String, String>{
          'data': json.encode(movie.toJson()).toString(),
        },
      );
      // result hold the response from plaform calls
      print("hasil $result");
    } on PlatformException catch (error) {
      // handle error
      print('Error: $error'); // here
    }
  }

  Future<List<Movie>> getDataLocal() async {
    try {
      var res = await _platformChannel.invokeMethod('getDataLocal');
      print("hasil $res");
      Iterable data = json.decode(res);
      List<Movie> movieList =
          data.map((model) => Movie.fromJson(model)).toList();
//      print("hasil $movieList");
      return movieList;
    } on PlatformException catch (error) {
      throw Exception('Failed to load post');
    }
  }
}
