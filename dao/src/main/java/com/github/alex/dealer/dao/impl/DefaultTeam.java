package com.github.alex.dealer.dao.impl;


import com.github.alex.dealer.dao.DataSource;
import com.github.alex.dealer.dao.TeamDao;
import com.github.alex.dealer.data.Team;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DefaultTeam implements TeamDao {

    @Override
    public List<Team> getMembers() {
        try (Connection connection = DataSource.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("select * from team");
             ResultSet resultSet = preparedStatement.executeQuery()) {
            final List<Team> result = new ArrayList<>();
            while (resultSet.next()) {
                final Team team = new Team(
                        resultSet.getLong("id"),
                        resultSet.getString("name")
                );
                result.add(team);
            }
            return result;
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }

}
