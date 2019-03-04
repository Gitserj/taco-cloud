package ru.fmt.tacocloud;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Data
public class Taco {

    @NotNull
    @Size(min = 5, message = "Минимальная длина имени - 5 символов")
    private String name;

    @Size(min = 1, message = "Выберите хотя-бы 1 ингредиент")
    private List<String> ingredients;

}
