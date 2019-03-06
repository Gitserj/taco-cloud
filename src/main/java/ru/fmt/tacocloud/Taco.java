package ru.fmt.tacocloud;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

@Data
public class Taco {

    private long id;
    private Date createdAt;

    @NotNull
    @Size(min = 5, message = "Минимальная длина имени - 5 символов")
    private String name;

    @NotNull(message = "Выберите хотя-бы 1 ингредиент")
    private List<String> ingredients;

}
