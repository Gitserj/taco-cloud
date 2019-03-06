package ru.fmt.tacocloud.data;

import ru.fmt.tacocloud.Taco;

public interface TacoRepository {
    Taco save(Taco design);
}
