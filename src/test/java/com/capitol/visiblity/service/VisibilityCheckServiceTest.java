package com.capitol.visiblity.service;

import com.capitol.visiblity.model.Product;
import com.capitol.visiblity.model.Size;
import com.capitol.visiblity.model.Stock;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith( MockitoExtension.class  )
class VisibilityCheckServiceTest {

    private VisibilityCheckService visibilityCheckService = new VisibilityCheckService();

    private static final String MESSAGE_PRODUCTS= "[5, 6, 1, 3]";

    @Test
    void  whenCheckStocks_getAString() {
        List <Product> products = mockProductList();
        List <Size> sizes = mockSizeList();
        HashMap<Integer, Stock> stocks = mockStockHash();

        String result = visibilityCheckService.checkStocks( products, sizes, stocks );
        assertEquals( MESSAGE_PRODUCTS, result );
    }


    private List<Size> mockSizeList() {
        Size size_1 = new Size(11,1, true, false);
        Size size_2 = new Size(12,1, false, false);
        Size size_3 = new Size(13,1, true, false);
        Size size_4 = new Size(21,2, false, false);
        Size size_5 = new Size(22,2, false, false);
        Size size_6 = new Size(23,2, true, true);
        Size size_7 = new Size(31,3, true, false);
        Size size_8 = new Size(32,3, false, false);
        Size size_9 = new Size(33,3, false, false);
        Size size_10 = new Size(41,4, false, false);
        Size size_11 = new Size(42,4, false, false);
        Size size_12 = new Size(43,4, false, false);
        Size size_13 = new Size(44,4, true, true);
        Size size_14 = new Size(51,5, true, false);
        Size size_15 = new Size(52,5, false, false);
        Size size_16 = new Size(53,5, false, false);
        Size size_17 = new Size(54,5, true, true);
        Size size_18 = new Size(61,6, false, false);
        Size size_19 = new Size(62,6, false, true);
        Size size_20= new Size(71,7, false, false);
        Size size_21 = new Size(72,7, false, false);
        return Arrays.asList( size_1,size_2,size_3,size_4,size_5,size_6,size_7,
                size_8,size_9,size_10,size_11,size_12,size_13,size_14,
                size_15,size_16,size_17,size_18,size_19, size_20, size_21 );
    }

    private HashMap<Integer, Stock> mockStockHash() {
        Stock stocks_1 = new Stock( 11, 0 );
        Stock stocks_2 = new Stock( 12, 0 );
        Stock stocks_3 = new Stock( 13, 0 );
        Stock stocks_4 = new Stock( 22, 0 );
        Stock stocks_5 = new Stock( 31, 10 );
        Stock stocks_6 = new Stock( 32, 10 );
        Stock stocks_7 = new Stock( 33, 10 );
        Stock stocks_8 = new Stock( 41, 0 );
        Stock stocks_9 = new Stock( 42, 0 );
        Stock stocks_10 = new Stock( 43, 0 );
        Stock stocks_11 = new Stock( 44, 10 );
        Stock stocks_12 = new Stock( 51, 10 );
        Stock stocks_13 = new Stock( 52, 10 );
        Stock stocks_14 = new Stock( 53, 10 );
        Stock stocks_15 = new Stock( 54, 10 );
        Stock stocks_16 = new Stock( 61, 5 );
        Stock stocks_17 = new Stock( 62, 5 );
        Stock stocks_18 = new Stock( 71, 0 );
        Stock stocks_19 = new Stock( 72, 0 );
        HashMap<Integer, Stock> o = new HashMap<>();
        o.put( 11, stocks_1 );
        o.put( 12, stocks_2 );
        o.put( 13, stocks_3 );
        o.put( 22, stocks_4 );
        o.put( 31, stocks_5 );
        o.put( 32, stocks_6 );
        o.put( 33, stocks_7 );
        o.put( 41, stocks_8 );
        o.put( 42, stocks_9 );
        o.put( 43, stocks_10 );
        o.put( 44, stocks_11 );
        o.put( 51, stocks_12 );
        o.put( 52, stocks_13 );
        o.put( 53, stocks_14 );
        o.put( 54, stocks_15 );
        o.put( 61, stocks_16 );
        o.put( 62, stocks_17 );
        o.put( 71, stocks_18 );
        o.put( 72, stocks_19 );
        return o;
    }

    private List<Product> mockProductList() {
        Product product_1 = new Product(5,6);
        Product product_2 = new Product(2,7);
        Product product_3 = new Product(6,9);
        Product product_4 = new Product(1,10);
        Product product_5 = new Product(4,13);
        Product product_6 = new Product(3,15);
        Product product_7 = new Product(7,17);

        return Arrays.asList( product_1, product_2 , product_3, product_4, product_5, product_6, product_7);
    }
}
