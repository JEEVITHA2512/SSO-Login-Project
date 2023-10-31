package api1.dto.mapper;

import api1.dto.request.OrderRequest;
import api1.dto.response.OrderResponse;
import api1.entity.Order;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface OrderMapper {
    OrderMapper MAPPER = Mappers.getMapper(OrderMapper.class);
    Order fromRequestToEntity(OrderRequest orderRequest);
    OrderResponse fromEntityToResponse(Order order);
}