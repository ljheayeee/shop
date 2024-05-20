package com.sparta.shop.controller;

import com.sparta.shop.dto.ItemRequestDto;
import com.sparta.shop.dto.ItemResponseDto;
import com.sparta.shop.entity.Item;
import com.sparta.shop.service.ItemService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ItemController {
    private final ItemService itemService;

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @PostMapping("/post")
    public ItemResponseDto createItem(@RequestBody ItemRequestDto requestDto) {
        return itemService.createItem(requestDto);
    }

    //게시글 전체 조회
    @GetMapping("/post")
    public List<ItemResponseDto> getItems(){
        return itemService.getItems();
    }

    //게시글 상세 조회
    @GetMapping("/post/contents")
    public List<ItemResponseDto> getItemsByKeyword(@RequestParam String keyword){

        return itemService.getItemsByKeyword(keyword);
    }
    @GetMapping("/post/username")
    public List<ItemResponseDto> getItemsByUsername(@RequestParam String username){

        return itemService.getItemsByUsername(username);
    }



    //게시글 수정
    @PutMapping("/post/{id}")
    public Long updateItem(@PathVariable Long id, @RequestBody ItemRequestDto requestDto) {
        return itemService.updateItem(id, requestDto);
    }

    //게시글 삭제
    @DeleteMapping("/post/{id}")
    public Long deleteItem(@PathVariable Long id) {
        return itemService.deleteItem(id);
    }


}
