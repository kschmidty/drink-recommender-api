package com.drink.api.response;

import com.drink.api.assemblers.DrinkResourceAssembler;
import com.drink.api.domain.Drink;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.http.ResponseEntity;

public class DrinkResponseFactory {

  public static ResponseEntity<Resource<Drink>> created(Drink drink,
                                                        DrinkResourceAssembler assembler) {
    return ResponseEntity
            .created(assembler.linkDrink(drink).toUri())
            .body(assembler.toResource(drink));
  }

  public static ResponseEntity<Resource<Drink>> ok(Drink drink,
                                                   DrinkResourceAssembler assembler) {
    return ResponseEntity.ok(assembler.toResource(drink));
  }

  public static ResponseEntity<Resources<Resource<Drink>>> ok(List<Drink> drinkList,
                                                              DrinkResourceAssembler assembler) {
    List<Resource<Drink>> resourceList = drinkList.stream()
            .map(assembler::toResource)
            .collect(Collectors.toList());

    Resources<Resource<Drink>> resources = new Resources<>(resourceList,
            assembler.linkDrinkList().withSelfRel());

    return ResponseEntity.ok(resources);
  }

  public static ResponseEntity<Object> noContent() {
    return ResponseEntity.noContent().build();
  }
}
