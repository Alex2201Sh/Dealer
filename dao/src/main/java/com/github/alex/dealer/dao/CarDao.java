package com.github.alex.dealer.dao;

import com.github.alex.dealer.data.Car;

import java.util.List;

public interface CarDao {
    List<Car> getCars();

    Long save(Car car);
}
