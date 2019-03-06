package ru.fmt.tacocloud.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import ru.fmt.tacocloud.Ingredient;
import ru.fmt.tacocloud.Ingredient.Type;
import ru.fmt.tacocloud.Order;
import ru.fmt.tacocloud.Taco;
import ru.fmt.tacocloud.data.IngredientRepository;
import ru.fmt.tacocloud.data.TacoRepository;

import javax.validation.Valid;

@Slf4j
@Controller
@RequestMapping("/design")
@SessionAttributes("order")
public class DesignTacoController {

    private final IngredientRepository ingredientRepo;
    private TacoRepository designRepo;

    @Autowired
    public DesignTacoController(IngredientRepository ingredientRepo, TacoRepository designRepo){
        this.ingredientRepo = ingredientRepo;
        this.designRepo = designRepo;
    }

    @ModelAttribute(name = "order")
    public Order order() {
        return new Order();
    }

    @ModelAttribute(name = "design")
    public Taco design() {
        return new Taco();
    }

//    @ModelAttribute
//    public void addIngredientsToModel(Model model) {
//
//    }

    @GetMapping
    public String showDesignForm(Model model){

        List<Ingredient> ingredients = new ArrayList<Ingredient>();
        ingredientRepo.findAll().forEach(i -> ingredients.add(i));

        Type[] types = Ingredient.Type.values();
        for (Type type : types) {
            model.addAttribute(type.toString().toLowerCase(), filterByType(ingredients, type));
        }
        return "design";
    }

    @PostMapping
    public String processDesign(
            @Valid Taco design,
            @ModelAttribute Order order,
            Errors errors){
        log.info("{Процессинг десигн} - До проверки на ошибку");

        if(errors.hasErrors()){
            log.info("{Процессинг десигн} - Внутри проверки на ошибку");
            return "design";
        }

        log.info("{Процессинг десигн} - После проверки на ошибку");
        Taco saved = designRepo.save(design);
        order.addDesign(saved);

        return "redirect:/orders/current";
    }

    private List<Ingredient> filterByType(List<Ingredient> ingredients, Type type) {
        return ingredients.stream()
                .filter(x -> x.getType().equals(type))
                .collect(Collectors.toList());
    }
}
