import 'package:rxdart/rxdart.dart';

import '../model/MovieModel.dart';
import '../repository/MovieRepository.dart';

class FavoriteBloc {
  final _repository = MovieRepository();
  var favoriteFetcher = PublishSubject<List<Movie>>();

  Observable<List<Movie>> get allMovies => favoriteFetcher.stream;

  fetchAllDataMovies() async {
    if (favoriteFetcher.isClosed) {
      favoriteFetcher = PublishSubject<List<Movie>>();
    }
    List<Movie> listMovie = await _repository.getDataLocal();
    favoriteFetcher.sink.add(listMovie);
  }

  dispose() {
    favoriteFetcher.close();
  }
}

final bloc = FavoriteBloc();
