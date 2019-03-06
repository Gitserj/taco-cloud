package ru.fmt.tacocloud.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import ru.fmt.tacocloud.Order;
import ru.fmt.tacocloud.data.OrderRepository;

import javax.validation.Valid;

@Slf4j
@Controller
@RequestMapping("/orders")
@SessionAttributes("order")
public class OrderController {

    private OrderRepository orderRepo;

    @Autowired
    public OrderController(OrderRepository orderRepo) {
        this.orderRepo = orderRepo;
    }

    @GetMapping("/current")
    public String orderForm(Model model){
        return "orderForm";
    }

    @PostMapping
    public String processedOrder(@Valid Order order, Errors errors, SessionStatus sessionStatus){

        if(errors.hasErrors()) return "orderForm";

        orderRepo.save(order);
        log.info("Получен заказ: " + order);
        sessionStatus.setComplete();

        return "redirect:/";
    }
}
