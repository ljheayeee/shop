package com.sparta.shop.repository;

import com.sparta.shop.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Long> {


    List<Item> findAllByOrderByTitleDesc();

    List<Item> findAllByUsername(String username);

    List<Item> findAllByContentContainingOrderByContentDesc(String keyword);

}
