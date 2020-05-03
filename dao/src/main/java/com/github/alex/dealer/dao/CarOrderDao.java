package com.github.alex.dealer.dao;

import com.github.alex.dealer.data.CarOrder;

import java.util.List;

public interface CarOrderDao {
    List<CarOrder> getCarOrders();

    Long save(CarOrder carOrder);
}
