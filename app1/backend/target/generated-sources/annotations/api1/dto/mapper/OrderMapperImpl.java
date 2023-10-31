package api1.dto.mapper;

import api1.dto.request.OrderRequest;
import api1.dto.response.OrderResponse;
import api1.entity.Order;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-10-31T14:54:41+0530",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 11.0.20.1 (Ubuntu)"
)
public class OrderMapperImpl implements OrderMapper {

    @Override
    public Order fromRequestToEntity(OrderRequest orderRequest) {
        if ( orderRequest == null ) {
            return null;
        }

        Order order = new Order();

        order.setName( orderRequest.getName() );
        order.setCategory( orderRequest.getCategory() );
        order.setDateOfOrder( orderRequest.getDateOfOrder() );
        order.setPrice( orderRequest.getPrice() );

        return order;
    }

    @Override
    public OrderResponse fromEntityToResponse(Order order) {
        if ( order == null ) {
            return null;
        }

        OrderResponse orderResponse = new OrderResponse();

        orderResponse.setId( order.getId() );
        orderResponse.setName( order.getName() );
        orderResponse.setCategory( order.getCategory() );
        orderResponse.setDateOfOrder( order.getDateOfOrder() );
        orderResponse.setPrice( order.getPrice() );

        return orderResponse;
    }
}
