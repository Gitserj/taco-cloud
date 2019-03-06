package ru.fmt.tacocloud.data;

import ru.fmt.tacocloud.Order;

public interface OrderRepository {
    Order save(Order order);
}
