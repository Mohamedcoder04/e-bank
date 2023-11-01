package com.mohamed.applicationbancaire.repositories;

import com.mohamed.applicationbancaire.models.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Integer > {
}
