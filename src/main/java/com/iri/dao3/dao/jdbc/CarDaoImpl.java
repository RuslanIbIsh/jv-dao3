package com.iri.dao3.dao.jdbc;

import com.iri.dao3.dao.CarDao;
import com.iri.dao3.exception.DataProcessingException;
import com.iri.dao3.lib.Dao;
import com.iri.dao3.model.Car;
import com.iri.dao3.model.Driver;
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
public class CarDaoImpl implements CarDao {
    @Override
    public Car create(Car car) {
        String updateQuery = "INSERT INTO cars (model, manufacturer_id) "
                + "VALUES (?, ?) ";
        try (Connection connection = ConnectionUtil.getConnection();
                PreparedStatement preparedStatement =
                        connection.prepareStatement(updateQuery, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, car.getModel());
            preparedStatement.setLong(2, car.getManufacturer().getId());
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                car.setId(resultSet.getObject(1, Long.class));
            }
            return car;
        } catch (SQLException e) {
            throw new DataProcessingException("Couldn't insert" + car + " in method create", e);
        }
    }

    @Override
    public Optional<Car> get(Long id) {
        String selectQuery = "SELECT c.id as id, c.model as model, "
                + "m.id as manufacturer_id, m.name as manufacturer_name, "
                + "m.country as manufacturer_country "
                + "FROM cars c "
                + "INNER JOIN manufacturer m ON c.manufacturer_id = m.id "
                + "WHERE c.deleted = false AND c.id = ? ";
        Car car = null;
        try (Connection connection = ConnectionUtil.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(selectQuery)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                car = constructCarFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            throw new DataProcessingException("Can't retrieve car in method get", e);
        }
        if (car != null) {
            car.setDrivers(getAllDriversByCar(car));
        }
        return Optional.ofNullable(car);
    }

    @Override
    public List<Car> getAll() {
        String selectQuery = "SELECT c.id as id, c.model as model, "
                + "m.id as manufacturer_id, m.name as manufacturer_name, "
                + "m.country as manufacturer_country "
                + "FROM cars c "
                + "INNER JOIN manufacturer m ON c.manufacturer_id = m.id "
                + "WHERE c.deleted = false ";
        List<Car> cars = new ArrayList<>();
        try (Connection connection = ConnectionUtil.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(selectQuery)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                cars.add(constructCarFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            throw new DataProcessingException("Couldn't fetch cars in method getAll", e);
        }
        for (Car car : cars) {
            car.setDrivers(getAllDriversByCar(car));
        }
        return cars;
    }

    @Override
    public Car update(Car car) {
        String updateQuery = "UPDATE cars SET model = ?, manufacturer_id = ? "
                + "WHERE id = ? AND deleted = false ";
        removeDriversFromCar(car);
        try (Connection connection = ConnectionUtil.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)) {
            preparedStatement.setString(1, car.getModel());
            preparedStatement.setLong(2, car.getManufacturer().getId());
            preparedStatement.setLong(3, car.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DataProcessingException("Can't refresh car in method update", e);
        }
        addDriversToCar(car);
        return car;
    }

    @Override
    public boolean delete(Long id) {
        String deleteQuery = "UPDATE cars SET deleted = true WHERE id = ?";
        try (Connection connection = ConnectionUtil.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(deleteQuery)) {
            preparedStatement.setLong(1, id);
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DataProcessingException("Can't mark car in method delete", e);
        }
    }

    private void addDriversToCar(Car car) {
        String insertQuery = "INSERT INTO cars_drivers (driver_id, car_id) "
                + "VALUES (?, ?);";
        try (Connection connection = ConnectionUtil.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
            for (Driver driver : car.getDrivers()) {
                preparedStatement.setLong(1, driver.getId());
                preparedStatement.setLong(2, car.getId());
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            throw new DataProcessingException("Can't insert in method addDriverToCar", e);
        }
    }

    @Override
    public List<Car> getAllByDriver(Long driverId) {
        String selectQuery = "SELECT  c.id as id, c.model as model, "
                + "m.id as manufacturer_id, m.name as manufacturer_name, "
                + "m.country as manufacturer_country "
                + "FROM cars_drivers cd "
                + "INNER JOIN cars c ON cd.car_id = c.id "
                + "INNER JOIN drivers d ON cd.driver_id = d.id "
                + "INNER JOIN manufacturer m ON c.manufacturer_id = m.id "
                + "WHERE c.deleted = false AND d.deleted = false "
                + "AND m.deleted = false AND cd.driver_id = ? ";
        List<Car> cars = new ArrayList<>();
        try (Connection connection = ConnectionUtil.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(selectQuery)) {
            preparedStatement.setLong(1, driverId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                cars.add(constructCarFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            throw new DataProcessingException("Could not get all cars by driver with id "
                    + driverId, e);
        }
        for (Car car : cars) {
            car.setDrivers(getAllDriversByCar(car));
        }
        return cars;
    }

    private List<Driver> getAllDriversByCar(Car car) {
        String selectQuery = "SELECT d.id, d.name, "
                + "d.license_number "
                + "FROM cars_drivers cd "
                + "INNER JOIN drivers d ON cd.driver_id = d.id "
                + "WHERE cd.car_id = ? AND d.deleted = false ";
        try (Connection connection = ConnectionUtil.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(selectQuery)) {
            preparedStatement.setLong(1, car.getId());
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Driver> drivers = new ArrayList<>();
            while (resultSet.next()) {
                drivers.add(constructDriverFromResultSet(resultSet));
            }
            return drivers;
        } catch (SQLException e) {
            throw new DataProcessingException("Can't fetch in method getAllDriversByCar", e);
        }
    }

    private void removeDriversFromCar(Car car) {
        String deleteQuery = "DELETE FROM `cars_drivers` WHERE car_id = ?";
        try (Connection connection = ConnectionUtil.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(deleteQuery)) {
            preparedStatement.setLong(1, car.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DataProcessingException("Can't execute in method removeDriversFromCar", e);
        }
    }

    private Car constructCarFromResultSet(ResultSet resultSet) throws SQLException {
        Long carId = resultSet.getObject("id", Long.class);
        String model = resultSet.getNString("model");
        Long manufacturerId = resultSet.getObject("manufacturer_id", Long.class);
        String manufacturerName = resultSet.getNString("manufacturer_name");
        String manufacturerCountry = resultSet.getNString("manufacturer_country");
        Manufacturer manufacturer = new Manufacturer(manufacturerName, manufacturerCountry);
        manufacturer.setId(manufacturerId);
        Car car = new Car(model, manufacturer);
        car.setId(carId);
        return car;
    }

    private Driver constructDriverFromResultSet(ResultSet resultSet)
            throws SQLException {
        Long driverId = resultSet.getObject("id", Long.class);
        String name = resultSet.getNString("name");
        String licenceNumber = resultSet.getNString("license_number");
        Driver driver = new Driver(name, licenceNumber);
        driver.setId(driverId);
        return driver;
    }
}
