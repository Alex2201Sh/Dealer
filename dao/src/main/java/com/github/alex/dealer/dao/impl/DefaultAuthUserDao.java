package com.github.alex.dealer.dao.impl;

import com.github.alex.dealer.dao.AuthUserDao;
import com.github.alex.dealer.dao.DataSource;
import com.github.alex.dealer.data.AuthUser;
import com.github.alex.dealer.data.Role;

import java.sql.*;

public class DefaultAuthUserDao implements AuthUserDao {

    private static class SingletonHolder {
        static final AuthUserDao HOLDER_INSTANCE = new DefaultAuthUserDao();
    }

    public static AuthUserDao getInstance() {
        return DefaultAuthUserDao.SingletonHolder.HOLDER_INSTANCE;
    }


    public AuthUser getByLogin(String login) {
        try (Connection connection = DataSource.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("select * from auth_user where login = ?")) {
            preparedStatement.setString(1, login);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return new AuthUser(
                            resultSet.getLong("id"),
                            resultSet.getString("login"),
                            resultSet.getString("password"),
                            Role.valueOf(resultSet.getString("role")),
                            resultSet.getString("user_id"));
                } else {
                    return null;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }


    public long saveAuthUser(AuthUser user) {
        final String sql = "insert into auth_user(login, password, role, user_id) values(?,?,?,?)";
        try (Connection connection = DataSource.getInstance().getConnection();
             PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, user.getLogin());
            ps.setString(2, user.getPassword());
            ps.setString(3, user.getRole().name());
            ps.setString(4, user.getUserId());
            ps.executeUpdate();
            try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                return generatedKeys.getLong(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
