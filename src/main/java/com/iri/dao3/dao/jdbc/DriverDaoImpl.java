package com.iri.dao3.dao.jdbc;

import com.iri.dao3.dao.DriverDao;
import com.iri.dao3.exception.DataProcessingException;
import com.iri.dao3.model.Driver;
import com.iri.dao3.util.ConnectionUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DriverDaoImpl implements DriverDao {
    @Override
    public Driver create(Driver driver) {
        String insertQuery = "INSERT INTO drivers (name, license_number) VALUES(?, ?)";
        try (Connection connection = ConnectionUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS)
        ) {
            preparedStatement.setString(1, driver.getName());
            preparedStatement.setString(2, driver.getLicenceNumber());
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                driver.setId(resultSet.getObject(1, Long.class));
            }
            return driver;
        } catch (SQLException e) {
            throw new DataProcessingException("Can't get connection in method create Driver", e);
        }
    }

    @Override
    public Optional<Driver> get(Long id) {
        String selectQuery = "SELECT * FROM drivers WHERE deleted = false AND id = ?";
        try (Connection connection = ConnectionUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(selectQuery)
        ) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            Driver driver = null;
            while (resultSet.next()) {
                return constructDriverFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            throw new DataProcessingException("Can't receive driver in method get", e);
        }
        return Optional.empty();
    }

    @Override
    public List<Driver> getAll() {
        String selectQuery = "SELECT * FROM drivers WHERE deleted = false";
        try (Connection connection = ConnectionUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(selectQuery)
        ) {
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Driver> drivers = new ArrayList<>();
            while (resultSet.next()) {
                drivers.add(constructDriverFromResultSet(resultSet).get());
            }
            return drivers;
        } catch (SQLException e) {
            throw new DataProcessingException("Can't get connection in method getAll", e);
        }
    }

    @Override
    public Driver update(Driver driver) {
        String updateQuery = "UPDATE drivers SET name = ?, license_number = ? "
                + "WHERE id = ? AND deleted = false";
        try (Connection connection = ConnectionUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)
        ) {
            preparedStatement.setString(1, driver.getName());
            preparedStatement.setString(2, driver.getLicenceNumber());
            preparedStatement.setLong(3, driver.getId());
            int result = preparedStatement.executeUpdate();
            if (result > 0) {
                return driver;
            }
        } catch (SQLException e) {
            throw new DataProcessingException("Can't get connection in method update driver", e);
        }
        throw new DataProcessingException("Can't update driver");
    }

    @Override
    public boolean delete(Long id) {
        String deleteQuery = "UPDATE driver SET deleted = true WHERE id = ? ";
        try (Connection connection = ConnectionUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(deleteQuery)
        ) {
            preparedStatement.setLong(1, id);
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DataProcessingException("Can't get connection in method delete", e);
        }
    }

    private Optional<Driver> constructDriverFromResultSet(ResultSet resultSet)
            throws SQLException {
        Long driverId = resultSet.getObject("id", Long.class);
        String name = resultSet.getNString("name");
        String licenceNumber = resultSet.getNString("license_number");
        Driver driver = new Driver(name, licenceNumber);
        driver.setId(driverId);
        return Optional.of(driver);
    }
}
