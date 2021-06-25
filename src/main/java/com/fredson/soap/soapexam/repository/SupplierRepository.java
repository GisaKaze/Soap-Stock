package com.fredson.soap.soapexam.repository;

import com.fredson.soap.soapexam.bean.SuppliersModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SupplierRepository extends JpaRepository<SuppliersModel, Integer> {

}
