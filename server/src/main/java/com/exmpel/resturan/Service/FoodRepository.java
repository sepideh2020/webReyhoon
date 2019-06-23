package com.exmpel.resturan.Service;

import com.exmpel.resturan.Entity.Food;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FoodRepository extends JpaRepository<Food,Long> {
}
