package com.fredson.soap.soapexam.repository;

import com.fredson.soap.soapexam.bean.ItemsModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends JpaRepository<ItemsModel, Integer> {

}
