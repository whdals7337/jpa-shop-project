package jpabook.jpashop.Service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.domain.item.Book;
import jpabook.jpashop.domain.item.Item;
import jpabook.jpashop.repository.ItemRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class ItemServiceTest {

    @Autowired
    ItemService itemService;
    @Autowired
    ItemRepository itemRepository;

    @Test
    public void 상품_등록() throws Exception {
        // given
        Book book = new Book();
        book.setAuthor("작가");
        book.setIsbn("123");

        // when
        itemService.saveItem(book);

        // then
        assertEquals(book, itemRepository.findOne(book.getId()));
    }

    @Test
    public void all_조회() throws Exception {
        // given
        List<Item> items = new ArrayList<>();

        Book book1 = new Book();
        book1.setAuthor("작가1");
        book1.setIsbn("123");
        items.add(book1);
        itemRepository.save(book1);

        Book book2 = new Book();
        book2.setAuthor("작가2");
        book2.setIsbn("123");
        items.add(book2);
        itemRepository.save(book2);

        // when
        List<Item> findItems = itemService.findItems();

        // then
        assertEquals(items.size(), findItems.size());
        for(int i = 0; i < items.size(); i++){
            assertEquals(items.get(i), findItems.get(i));
        }

    }
}