package com.sparta.shop.service;

import com.sparta.shop.dto.ItemRequestDto;
import com.sparta.shop.dto.ItemResponseDto;
import com.sparta.shop.entity.Item;
import com.sparta.shop.repository.ItemRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ItemService {

    private final ItemRepository itemRepository;

    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }


    public ItemResponseDto createItem(ItemRequestDto requestDto) {

        Item Item = new Item(requestDto);

        Item saveItem = itemRepository.save(Item);

        ItemResponseDto ItemResponseDto = new ItemResponseDto(saveItem);

        return ItemResponseDto;
    }

    public List<ItemResponseDto> getItems() {

        return itemRepository.findAllByOrderByTitleDesc().stream().map(ItemResponseDto::new).toList();
    }

    @Transactional
    public Long updateItem(Long id, ItemRequestDto requestDto) {

        // 해당 메모가 DB에 존재하는지 확인
        Item Item = findItem(id);

        Item.update(requestDto);
        return id;
    }

    public Long deleteItem(Long id) {
        // 해당 메모가 DB에 존재하는지 확인
        Item Item = findItem(id);

        // Item 삭제
        itemRepository.delete(Item);

        return id;
    }
    private Item findItem(Long id) {
        return itemRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("선택한 아이템은 존재하지 않습니다"));
    }


    public List<ItemResponseDto> getItemsByKeyword(String keyword) {
        return itemRepository.findAllByContentContainingOrderByContentDesc(keyword).stream().map(ItemResponseDto::new).toList();
    }
    public List<ItemResponseDto> getItemsByUsername(String username){
        return itemRepository.findAllByUsername(username).stream().map(ItemResponseDto::new).toList();
    }
}
