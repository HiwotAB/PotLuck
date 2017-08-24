package com.hiwotab.potluck.repositories;

import com.hiwotab.potluck.Models.PotLuck;
import org.springframework.data.repository.CrudRepository;

public interface PotLuckRepository extends CrudRepository<PotLuck, Long> {

    Iterable<PotLuck> findAllByFirstname(String partialString);
    Iterable<PotLuck> findAllByDishContains(String partialString);
}
