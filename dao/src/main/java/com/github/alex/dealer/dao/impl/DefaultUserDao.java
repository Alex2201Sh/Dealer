package com.github.alex.dealer.dao.impl;

import com.github.alex.dealer.dao.DataSource;
import com.github.alex.dealer.dao.UserDao;
import com.github.alex.dealer.data.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DefaultUserDao implements UserDao {

    private static class SingletonHolder {
        static final UserDao HOLDER_INSTANCE = new DefaultUserDao();
    }

    public static UserDao getInstance() {
        return DefaultUserDao.SingletonHolder.HOLDER_INSTANCE;
    }

    @Override
    public List<User> getMembers() {
        try (Connection connection = DataSource.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("select * from user");
             ResultSet resultSet = preparedStatement.executeQuery()){
            final List<User> result = new ArrayList<>();
            while(resultSet.next()){
                final User user = new User(
                        resultSet.getLong("id"),
                        resultSet.getString("firstName"),
                        resultSet.getString("lastName"),
                        resultSet.getString("phoneNumber")
                );
                result.add(user);
            }
            return result;
        } catch (SQLException e){
            throw new RuntimeException();
        }
    }

    @Override
    public Long save(User user) {
        String sql = "insert into user(first_name, last_name, phone, email) values(?,?,?,?)";
        try (Connection connection = DataSource.getInstance().getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){
            preparedStatement.setString(1, user.getFirstName());
            preparedStatement.setString(2, user.getLastName());
            preparedStatement.setString(3, user.getPhoneNumber());
            preparedStatement.executeUpdate();
            try (ResultSet keys = preparedStatement.getGeneratedKeys()){
                keys.next();
                return keys.getLong(1);
            }
        }catch (SQLException e){
            throw new RuntimeException();
        }
    }
}
