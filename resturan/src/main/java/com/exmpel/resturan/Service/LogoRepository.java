package com.exmpel.resturan.Service;

import com.exmpel.resturan.Entity.Logo;
import com.exmpel.resturan.Entity.Resturan;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LogoRepository extends JpaRepository<Logo,Long> {
    public Logo findByResturan(Resturan resturan);
}
