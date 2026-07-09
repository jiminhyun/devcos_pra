package com.example.junit_5.temp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestTemplate;
import org.junit.jupiter.api.function.Executable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

@SpringJUnitConfig(AppConfig.class) // @ExtendWith(SpringExtension.class) + @ContextConfiguration(classes = AppConfig.class)
// 각각 스프링 프레임워크 사용할 준비, 컨테이너 사용할 준비를 하는 것이다. 즉, 프레임워크 없이 컨테이너를 사용할려면 당연히 오류 난다.
class ProductDaoTest {
    @Autowired
    private ProductDao dao;
    @BeforeEach
    void setUp() {
        dao.deleteAll();
    }

    public Product newProduct(String id, String name, int price) {
        Product product = new Product();
        product.setId(id);
        product.setName(name);
        product.setPrice(price);
        return product;
    }

    @Test
    void add() {
        //given
        assertEquals(0, dao.getCount());
        //when
        dao.add(newProduct("1", "2", 3));
        //then
        assertEquals(1, dao.getCount());
    }

    @Test
    void get() {
        //given
        Product product1 = newProduct("1", "2", 3);
        dao.add(product1);
        //when
        Product product2 = dao.get("1");
        //then
        assertEquals(product1.getName(), product2.getName());
        assertEquals(product1.getPrice(), product2.getPrice());
    }

    @Test
    void add_중복_id_예외() {
        //given
        Executable action = new Executable() {

            @Override
            public void execute() {
                dao.add(newProduct("1", "2", 3));
            }
        };
        dao.add(newProduct("1", "2", 3));

        //then
        assertThrows(IllegalStateException.class, action);
    }

    @Test
    void get_없는_id_예외() {
        //given
        Executable action = new Executable() {

            @Override
            public void execute() {
                dao.get("1");
            }
        };
        //then
        assertThrows(NoSuchElementException.class, action);
    }

    @Disabled
    @Test
    void 일부러_실패하는_테스트() {
        //given
        dao.add(newProduct("1", "2", 3));
        //then
        assertEquals(2, dao.getCount());
    }
}