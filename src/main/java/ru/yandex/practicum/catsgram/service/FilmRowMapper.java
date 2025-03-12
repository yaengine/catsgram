package ru.yandex.practicum.catsgram.service;

import org.springframework.jdbc.core.RowMapper;
import ru.yandex.practicum.catsgram.model.Film;

import java.sql.ResultSet;
import java.sql.SQLException;

public class FilmRowMapper implements RowMapper<Film> {
    @Override
    public Film mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        Film film = new Film();
        film.setId(resultSet.getLong("id"));
        film.setTitle(resultSet.getString("title"));
        film.setProducedYear(resultSet.getInt("produced"));
        film.setReleaseDate(resultSet.getDate("date_prod"));
        film.setKind(resultSet.getString("kind"));
        film.setMinLength(resultSet.getInt("len_min"));
        return film;
    }
}
