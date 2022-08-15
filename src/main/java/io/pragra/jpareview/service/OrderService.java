package io.pragra.jpareview.service;

import io.pragra.jpareview.entity.Order;
import io.pragra.jpareview.exceptions.OrderNotFoundException;
import io.pragra.jpareview.repo.OrderRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

//Service - to implement business logics(service layer = business layer)
@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepo orderRepo;

    public Order createOrder(Order order){
        return this.orderRepo.save(order);
    }

    public List<Order> createMany(List<Order> orders){
        return this.orderRepo.saveAll(orders);
    }

    public Order findById(String uuid) throws OrderNotFoundException {
        UUID uuid1 = UUID.fromString(uuid);
        //Optional<Order> byId = this.orderRepo.findById(uuid1);
        Optional<Order> order = this.orderRepo.findAll().stream().filter(order1 -> order1.getId().equals(uuid1)).findFirst();
        return order.orElseThrow(() -> new OrderNotFoundException("order for UUID doesn't exists"));
    }

    public List<Order> getAll(){
        return this.orderRepo.findAll();
    }

}
