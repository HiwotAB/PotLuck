package com.hiwotab.potluck.repositories;

import com.hiwotab.potluck.Models.Pot;
import org.springframework.data.repository.CrudRepository;

public interface PotRepository extends CrudRepository<Pot, Long> {
    Iterable<Pot>findAllByFirstname(String firstname);
    Iterable<Pot>findAllByDish(String dish);
}
