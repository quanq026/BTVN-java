package business;

import model.Movie;

import java.util.ArrayList;
import java.util.List;

public class MovieManager<T extends Movie> {
    private final List<T> movies = new ArrayList<>();

    public void addMovie(T movie) {
        movies.add(movie);
    }

    public boolean updateMovie(int id, T newMovie) {
        for (int i = 0; i < movies.size(); i++) {
            if (movies.get(i).getId() == id) {
                movies.set(i, newMovie);
                return true;
            }
        }

        return false;
    }

    public boolean removeMovie(int id) {
        T movie = findById(id);

        if (movie == null) {
            return false;
        }

        movies.remove(movie);
        return true;
    }

    public List<T> getMovies() {
        return movies;
    }

    public List<T> searchByTitle(String title) {
        List<T> result = new ArrayList<>();

        for (T movie : movies) {
            if (movie.getTitle().toLowerCase().contains(title.toLowerCase())) {
                result.add(movie);
            }
        }

        return result;
    }

    public List<T> filterByRating(double minRating) {
        List<T> result = new ArrayList<>();

        for (T movie : movies) {
            if (movie.getRating() > minRating) {
                result.add(movie);
            }
        }

        return result;
    }

    public T findById(int id) {
        for (T movie : movies) {
            if (movie.getId() == id) {
                return movie;
            }
        }

        return null;
    }
}
