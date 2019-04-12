import 'package:rxdart/rxdart.dart';

import '../model/MovieModel.dart';
import '../repository/MovieRepository.dart';

class MovieBloc {
  final _repository = MovieRepository();
  var _moviesFetcher = PublishSubject<MovieModel>();

  Observable<MovieModel> get allMovies => _moviesFetcher.stream;

  fetchAllDataMovies() async {
    if (_moviesFetcher.isClosed) {
      _moviesFetcher = PublishSubject<MovieModel>();
    }
    MovieModel itemModel = await _repository.fetchAllMovies();
    _moviesFetcher.sink.add(itemModel);
  }

  saveToFavorite(Movie movie) async {
    await _repository.saveFavorite(movie);
  }

  dispose() {
    _moviesFetcher.close();
  }
}

final bloc = MovieBloc();
