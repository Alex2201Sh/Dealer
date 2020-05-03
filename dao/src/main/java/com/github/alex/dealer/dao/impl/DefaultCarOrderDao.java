package com.github.alex.dealer.dao.impl;

import com.github.alex.dealer.dao.CarOrderDao;
import com.github.alex.dealer.dao.DataSource;
import com.github.alex.dealer.data.CarOrder;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DefaultCarOrderDao implements CarOrderDao {

    @Override
    public List<CarOrder> getCarOrders() {
        try (Connection connection = DataSource.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("select * from order_for_car");
             ResultSet resultSet = preparedStatement.executeQuery()) {
            final List<CarOrder> result = new ArrayList<>();
            while (resultSet.next()) {
                final CarOrder carOrder = new CarOrder(
                        resultSet.getString("user"),
                        resultSet.getString("date"),
                        resultSet.getString("vinNumber")
                );
                result.add(carOrder);
            }
            return result;
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }

    @Override
    public Long save(CarOrder carOrder) {
        String sql = "insert into order_for_car(user, date, vinNumber) values(?,?,?)";
        try (Connection connection = DataSource.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, carOrder.getUser());
            preparedStatement.setString(2, carOrder.getDate());
            preparedStatement.setString(3, carOrder.getVinNumber());
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
