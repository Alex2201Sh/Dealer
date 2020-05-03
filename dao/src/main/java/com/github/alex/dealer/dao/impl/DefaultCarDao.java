package com.github.alex.dealer.dao.impl;

import com.github.alex.dealer.dao.CarDao;
import com.github.alex.dealer.dao.DataSource;
import com.github.alex.dealer.data.Car;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DefaultCarDao implements CarDao {

    @Override
    public List<Car> getCars() {
        try (Connection connection = DataSource.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("select * from car");
             ResultSet resultSet = preparedStatement.executeQuery()) {
            final List<Car> result = new ArrayList<>();
            while (resultSet.next()) {
                final Car car = new Car(
                        resultSet.getLong("id"),
                        resultSet.getString("model"),
                        resultSet.getString("vinNumber")
                );
                result.add(car);
            }
            return result;
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }

    @Override
    public Long save(Car car) {
        String sql = "insert into car(model, vinNumber) values(?,?)";
        try (Connection connection = DataSource.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, car.getModel());
            preparedStatement.setString(2, car.getVinNumber());
            preparedStatement.executeUpdate();
            try (ResultSet keys = preparedStatement.getGeneratedKeys()) {
                keys.next();
                return keys.getLong(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }
}
