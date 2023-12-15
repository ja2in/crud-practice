package jaein.crudpractice.service;

import jaein.crudpractice.domain.item.Item;
import jaein.crudpractice.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;

    @Transactional
    public void saveItem(Item item){
        itemRepository.save(item);
    }

    @Transactional
    public void updateItem(Long itemId, String name, int stockQuantity){
        Optional<Item> findItem = itemRepository.findById(itemId);
        findItem.orElseThrow(() -> new IllegalArgumentException("no such item")).setName(name);
        findItem.orElseThrow(() -> new IllegalArgumentException("no such item")).setStockQuantity(stockQuantity);
    }

    public List<Item> findItems(){
        return itemRepository.findAll();
    }

    public Item findOne(Long itemId){

        Item item = itemRepository.findById(itemId).orElseThrow(() ->
                new IllegalArgumentException("no such item"));

        return item;

    }
}
