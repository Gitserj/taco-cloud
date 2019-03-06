package ru.fmt.tacocloud;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

@Data
//@RequiredArgsConstructor
public class Taco {

    private long id;
    private Date createdAt;

    @NotNull
    @Size(min = 5, message = "Минимальная длина имени - 5 символов")
    private String name;

    @Size(min = 1, message = "Выберите хотя-бы 1 ингредиент")
    private List<Ingredient> ingredients;

}
