package com.shop.shopweb.repo;

import com.shop.shopweb.po.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SupplierRepository extends JpaRepository<Supplier, String> {
}
