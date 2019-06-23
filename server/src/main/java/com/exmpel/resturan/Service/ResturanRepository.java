package com.exmpel.resturan.Service;

import com.exmpel.resturan.Entity.Resturan;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResturanRepository extends JpaRepository<Resturan,Long> {
}
