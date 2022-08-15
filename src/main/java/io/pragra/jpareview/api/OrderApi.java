package io.pragra.jpareview.api;

import io.pragra.jpareview.dto.ErrorDto;
import io.pragra.jpareview.entity.Order;
import io.pragra.jpareview.exceptions.OrderNotFoundException;
import io.pragra.jpareview.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
@Slf4j
public class OrderApi {

    private final OrderService orderService;

    /*//create an order
    @PostMapping("/order")
    public Order createOrder(@RequestBody Order order){
        return this.orderService.createOrder(order);
    }
    //create many orders
    @PostMapping("/orders")
    public List<Order> createManyOrders(@RequestBody List<Order> orders){
        return this.orderService.createMany(orders);
    }
    //get order by its uuid
    @GetMapping("/order/{id}")
    public Order getOrderById(@PathVariable String id) throws OrderNotFoundException {
        return this.orderService.findById(id);
    }
    //get all orders
    @GetMapping("/orders")
    public List<Order> getAllOrders(){
        return this.orderService.getAll();
    }
*/

    //create an order
    @PostMapping("/order")
    public ResponseEntity<?> createOrder(@RequestBody Order order, @RequestHeader("X-MARKET") String market) {
        log.info("GOT HEADER{} ", market);
        Order serviceOrder = this.orderService.createOrder(order);
        return ResponseEntity
                .status(201)
                .header("X-COMPLETE", "true")
                .body(serviceOrder);
    }

    //get all orders
    @GetMapping("/order")
    public ResponseEntity<?> getAllOrders() {
        List<Order> orderList = this.orderService.getAll();
        return ResponseEntity
                .status(200)
                .body(orderList);
    }

    //get order by uuid
    @GetMapping(value = "/order/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getById(@PathVariable String id) {
        try {
            Order order = orderService.findById(id);
            return ResponseEntity.ok().body(order);
        } catch (OrderNotFoundException ex) {
            return ResponseEntity
                    .status(404)
                    .body(ErrorDto
                            .builder()
                            .errCode("AP404")
                            .apiCode("ORDER101")
                            .errMessage(ex.getMessage())
                            .timeStamp(Instant.now())
                            .build());
        }
    }
}
