package ru.madrabit.databasedemo.jdbc;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import ru.madrabit.databasedemo.DatabaseDemoApplication;
import ru.madrabit.databasedemo.entity.Person;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

@Repository
public class PersonJdbcDao {

    private final JdbcTemplate jdbcTemplate;

    public PersonJdbcDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    class PersonRowMapper implements RowMapper<Person> {
        @Override
        public Person mapRow(ResultSet rs, int rowNum) throws SQLException {
            Person person = new Person();
            person.setId(rs.getInt("id"));
            person.setName(rs.getString("name"));
            person.setLocation(rs.getString("location"));
            person.setBirthDate(rs.getTimestamp("birth_date"));
            return person;
        }
    }

    public List<Person> findAll() {
        return jdbcTemplate.query("SELECT * FROM person", new PersonRowMapper());
    }

    public Person findById(int id) {
        return jdbcTemplate.queryForObject("SELECT * FROM person WHERE id=?",
                new BeanPropertyRowMapper<>(Person.class), new Object[]{id});
    }

    public int delete(int id) {
        return jdbcTemplate.update("DELETE FROM person WHERE id=?",
                new Object[]{id});
    }

    public int create(Person person) {
        return jdbcTemplate.update("INSERT INTO person (id, name, location, birth_date)" +
                        " VALUES (?,?,?,?)",
                new Object[]{person.getId(), person.getName(), person.getLocation(), new Timestamp(person.getBirthDate().getTime())});
    }

    public int update(Person person) {
        return jdbcTemplate.update("UPDATE person SET name=?, location=?, birth_date=? WHERE id=?" ,
                new Object[]{person.getName(), person.getLocation(), new Timestamp(person.getBirthDate().getTime()), person.getId(),});
    }
}
