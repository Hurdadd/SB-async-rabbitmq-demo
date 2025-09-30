package com.example.order_service.mapper;

import com.example.order_service.DTO.MessageDTO;
import com.example.order_service.DTO.OrderRequest;
import com.example.order_service.DTO.OrderResponse;
import com.example.order_service.DTO.OrderUpdate;
import com.example.order_service.model.Order;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface OrderMapper {
    Order toEntity(OrderRequest orderRequest);
    OrderResponse toDto(Order order);

   @Mapping(source = "id", target = "orderId")
   MessageDTO toMessageDto(Order order);
    @Mapping(source = "orderId", target = "id")
    Order toEntity(MessageDTO messageDTO);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void partialUpdate(OrderUpdate orderUpdate, @MappingTarget Order order);

}
