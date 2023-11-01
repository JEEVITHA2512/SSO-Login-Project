package api1.service;

import api1.dto.request.OrderRequest;
import api1.dto.response.OrderResponse;
import api1.entity.OrderJson;
import api1.entity.Order;

import java.util.List;
import java.util.Optional;

public interface OrderService {
    List<Order> findAllOrder();
    Optional<Order> findById(Long id);
    Order saveOrder(Order Order);
    Order updateOrder(Order Order);
    void deleteOrder(Long id);

//    Using Request for Save and Update Order
    OrderResponse saveOrder(OrderRequest orderRequest);
    OrderResponse updateOrder(OrderRequest orderRequest, Long id);


}