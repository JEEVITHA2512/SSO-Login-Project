package api1.service.impl;

import api1.dto.mapper.OrderMapper;
import api1.dto.request.OrderRequest;
import api1.dto.response.OrderResponse;
import api1.entity.Order;
import api1.repo.OrderRepo;
import api1.service.OrderService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepo orderRepo;

    public OrderServiceImpl(OrderRepo orderRepo) {
        this.orderRepo = orderRepo;
    }

    @Override
    public List<Order> findAllOrder() {
        return orderRepo.findAll();
    }

    @Override
    public Optional<Order> findById(Long id) {
        return orderRepo.findById(id);
    }

    @Override
    public Order saveOrder(Order order) {
        return orderRepo.save(order);
    }

    @Override
    public Order updateOrder(Order order) {
        return orderRepo.save(order);
    }

    @Override
    public void deleteOrder(Long id) {
        orderRepo.deleteById(id);
    }

//    Using Request and Response with save and update order

    @Override
    public OrderResponse saveOrder(OrderRequest orderRequest) {
        Order order = OrderMapper.MAPPER.fromRequestToEntity(orderRequest);
        orderRepo.save(order);
        return OrderMapper.MAPPER.fromEntityToResponse(order);
    }

    @Override
    public OrderResponse updateOrder(OrderRequest orderRequest, Long id) {

        Optional<Order> checkExistingOrder = findById(id);
        if (! checkExistingOrder.isPresent())
            throw new RuntimeException("Order Id "+ id + " Not Found!");

        Order order = OrderMapper.MAPPER.fromRequestToEntity(orderRequest);
        order.setId(id);
        orderRepo.save(order);
        return OrderMapper.MAPPER.fromEntityToResponse(order);
    }

}