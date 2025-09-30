package com.example.order_service.controller;

import com.example.order_service.DTO.*;
import com.example.order_service.service.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
@Tag(name = "Order API", description = "CRUD operations for managing Orders in Snappay system")
public class OrderController {

    private final OrderService orderService;

    @Operation(
            summary = "Create new order",
            description = "Creates a new order for a user and sets its status to PENDING.",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Order successfully created"),
                    @ApiResponse(responseCode = "400", description = "Invalid input data")
            }
    )
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public OrderResponse createOrder(
            @RequestBody OrderRequest request
    ) {
        return orderService.createOrder(request);
    }

    @Operation(
            summary = "Get order by ID",
            description = "Retrieves order details by its unique ID.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Order found"),
                    @ApiResponse(responseCode = "404", description = "Order not found")
            }
    )
    @GetMapping("/{id}")
    public OrderResponse getOrder(
            @Parameter(description = "ID of the order to be retrieved")
            @PathVariable String id
    ) {
        return orderService.getOrder(id);
    }

    @Operation(
            summary = "Get all orders",
            description = "Retrieves a list of all orders in the system.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "List of orders returned successfully")
            }
    )
    @GetMapping
    public List<OrderResponse> getAllOrders() {
        return orderService.getAllOrders();
    }

    @Operation(
            summary = "Update existing order",
            description = "Updates order amount or products. Status and immutable fields cannot be changed here.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Order successfully updated"),
                    @ApiResponse(responseCode = "404", description = "Order not found")
            }
    )
    @PutMapping("/{id}")
    public OrderResponse updateOrder(
            @Parameter(description = "ID of the order to be updated")
            @PathVariable String id,
            @RequestBody OrderUpdate update
    ) {
        return orderService.updateOrder(id, update);
    }

    @Operation(
            summary = "Delete order",
            description = "Soft deletes an order by changing its status to DELETED. Only logical delete is performed.",
            responses = {
                    @ApiResponse(responseCode = "204", description = "Order successfully deleted"),
                    @ApiResponse(responseCode = "404", description = "Order not found")
            }
    )
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteOrder(
            @Parameter(description = "ID of the order to be deleted")
            @PathVariable String id
    ) {
        orderService.deleteOrder(id);
    }
}
