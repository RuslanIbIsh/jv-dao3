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
        String insertQuery = "INSERT INTO manufacturer (name, country) "
                + "VALUES (?, ?)";
        try (Connection connection = ConnectionUtil.getConnection();
                PreparedStatement preparedStatement =
                        connection.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, manufacturer.getName());
            preparedStatement.setString(2, manufacturer.getCountry());
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                manufacturer.setId(resultSet.getObject(1, Long.class));
            }
            return manufacturer;
        } catch (SQLException e) {
            throw new DataProcessingException("Can't get connection in method create", e);
        }
    }

    @Override
    public Optional<Manufacturer> get(Long id) {
        String selectQuery = "SELECT * FROM manufacturer WHERE deleted = false AND id = ?";
        try (Connection connection = ConnectionUtil.getConnection(); PreparedStatement statement =
                connection.prepareStatement(selectQuery)) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            Manufacturer manufacturer = null;
            while (resultSet.next()) {
                return constructManufaturerFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            throw new DataProcessingException("Can't get connection in method get", e);
        }
        return Optional.empty();
    }

    @Override
    public List<Manufacturer> getAll() {
        String selectQuery = "SELECT * FROM manufacturer WHERE deleted = false";
        try (Connection connection = ConnectionUtil.getConnection(); PreparedStatement statement =
                connection.prepareStatement(selectQuery)) {
            ResultSet resultSet = statement.executeQuery();
            List<Manufacturer> manufacturers = new ArrayList<>();
            while (resultSet.next()) {
                manufacturers.add(constructManufaturerFromResultSet(resultSet).get());
            }
            return manufacturers;
        } catch (SQLException e) {
            throw new DataProcessingException("Can't get connection in method getAll", e);
        }
    }

    @Override
    public Manufacturer update(Manufacturer manufacturer) {
        String updateQuery = "UPDATE manufacturer SET name = ?, country = ? "
                + "WHERE id = ? AND deleted = false";
        try (Connection connection = ConnectionUtil.getConnection();
                PreparedStatement preparedStatement =
                        connection.prepareStatement(updateQuery)) {
            preparedStatement.setString(1, manufacturer.getName());
            preparedStatement.setString(2, manufacturer.getCountry());
            preparedStatement.setLong(3, manufacturer.getId());
            int result = preparedStatement.executeUpdate();
            if (result > 0) {
                return manufacturer;
            }
        } catch (SQLException e) {
            throw new DataProcessingException("Can't get connection in method update", e);
        }
        throw new DataProcessingException("Can't update Manufacturer");
    }

    @Override
    public boolean delete(Long id) {
        String deleteQuery = "UPDATE manufacturer SET deleted=true WHERE id = ?";
        try (Connection connection = ConnectionUtil.getConnection();
                PreparedStatement preparedStatement =
                        connection.prepareStatement(deleteQuery)) {
            preparedStatement.setLong(1, id);
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DataProcessingException("Can't get connection in method delete", e);
        }
    }

    private Optional<Manufacturer> constructManufaturerFromResultSet(ResultSet resultSet)
            throws SQLException {
        long manufacturerId = resultSet.getObject("id", Long.class);
        String name = resultSet.getNString("name");
        String country = resultSet.getNString("country");
        Manufacturer manufacturer = new Manufacturer(name, country);
        manufacturer.setId(manufacturerId);
        return Optional.of(manufacturer);
    }
}
