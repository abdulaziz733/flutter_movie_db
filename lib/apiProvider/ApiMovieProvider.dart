import 'dart:async';
import 'dart:convert';

import 'package:http/http.dart' as http;

import '../model/MovieModel.dart';
import '../utils/Const.dart';

class ApiMovieProvider {
  Future<MovieModel> fetchDataMovie() async {
    final response = await http.get("$baseUrl/popular?api_key=$apiKey");
    if (response.statusCode == 200) {
//      print(response.body);
      return MovieModel.fromJson(json.decode(response.body));
    } else {
      throw Exception('Failed to load post');
    }
  }
}
