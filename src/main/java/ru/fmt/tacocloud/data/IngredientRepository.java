package ru.fmt.tacocloud.data;

import ru.fmt.tacocloud.Ingredient;

public interface IngredientRepository {

    Iterable<Ingredient> findAll();
    Ingredient findOne(String id);
    Ingredient save(Ingredient ingredient);
}
