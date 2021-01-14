package com.iri.dao3.dao.jdbc;

import com.iri.dao3.dao.ManufacturerDao;
import com.iri.dao3.exception.DataProcessingException;
import com.iri.dao3.lib.Dao;
import com.iri.dao3.model.Manufacturer;
import com.iri.dao3.util.ConnectionUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Dao
public class ManufacturerDaoJdbcImpl implements ManufacturerDao {
    @Override
    public Manufacturer create(Manufacturer manufacturer) {
        String query = "INSERT INTO manufacturer (name, country, deleted) "
                + "VALUES (?, ?, ?)";
        try (Connection connection = ConnectionUtil.getConnection();
                PreparedStatement preparedStatement =
                        connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, manufacturer.getName());
            preparedStatement.setString(2, manufacturer.getCountry());
            preparedStatement.setBoolean(3, false);
            int result = preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                manufacturer.setId(resultSet.getObject(1, Long.class));
            }
            if (result > 0) {
                return manufacturer;
            }
        } catch (SQLException throwables) {
            throw new DataProcessingException("Can't get connection in method create", throwables);
        }
        throw new DataProcessingException("Can't return manufacturer from method create");
    }

    @Override
    public Optional<Manufacturer> get(Long id) {
        String query = "SELECT * FROM manufacturer WHERE deleted = false AND id = ?";
        try (Connection connection = ConnectionUtil.getConnection(); PreparedStatement statement =
                connection.prepareStatement(query)) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            Manufacturer manufacturer = null;
            while (resultSet.next()) {
                manufacturer = constructManufaturerFromResultSet(resultSet);
            }
            return Optional.of(manufacturer);
        } catch (SQLException throwables) {
            throw new DataProcessingException("Can't get connection in method get", throwables);
        }
    }

    @Override
    public List<Manufacturer> getAll() {
        String query = "SELECT * FROM manufacturer WHERE deleted = false";
        try (Connection connection = ConnectionUtil.getConnection(); PreparedStatement statement =
                connection.prepareStatement(query)) {
            ResultSet resultSet = statement.executeQuery();
            List<Manufacturer> manufacturers = new ArrayList<>();
            while (resultSet.next()) {
                Manufacturer manufacturer = constructManufaturerFromResultSet(resultSet);
                manufacturers.add(manufacturer);
            }
            return manufacturers;
        } catch (SQLException throwables) {
            throw new DataProcessingException("Can't get connection in method get", throwables);
        }
    }

    @Override
    public Manufacturer update(Manufacturer manufacturer) {
        String query = "UPDATE manufacturer SET name = ?, country = ? "
                + "WHERE id = ? AND deleted = false";
        try (Connection connection = ConnectionUtil.getConnection();
                PreparedStatement preparedStatement =
                        connection.prepareStatement(query)) {
            preparedStatement.setString(1, manufacturer.getName());
            preparedStatement.setString(2, manufacturer.getCountry());
            preparedStatement.setLong(3, manufacturer.getId());
            int result = preparedStatement.executeUpdate();
            if (result > 0) {
                return manufacturer;
            }
        } catch (SQLException throwables) {
            throw new DataProcessingException("Can't get connection in method delete", throwables);
        }
        throw new DataProcessingException("Can't update Manufacturer");
    }

    @Override
    public boolean delete(Long id) {
        String query = "UPDATE manufacturer SET deleted=true WHERE id = ?";
        try (Connection connection = ConnectionUtil.getConnection();
                PreparedStatement preparedStatement =
                        connection.prepareStatement(query)) {
            preparedStatement.setLong(1, id);
            int result = preparedStatement.executeUpdate();
            return result > 0;
        } catch (SQLException throwables) {
            throw new DataProcessingException("Can't get connection in method delete", throwables);
        }
    }

    private Manufacturer constructManufaturerFromResultSet(ResultSet resultSet)
            throws SQLException {
        long manufacturerId = resultSet.getObject("id", Long.class);
        String name = resultSet.getNString("name");
        String country = resultSet.getNString("country");
        Manufacturer manufacturer = new Manufacturer(name, country);
        manufacturer.setId(manufacturerId);
        return manufacturer;
    }
}
