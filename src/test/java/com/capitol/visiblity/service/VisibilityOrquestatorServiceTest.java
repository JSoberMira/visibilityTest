package com.capitol.visiblity.service;

import com.capitol.visiblity.model.Product;
import com.capitol.visiblity.model.Size;
import com.capitol.visiblity.model.Stock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.io.FileNotFoundException;
import java.net.URL;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.when;

@ExtendWith( MockitoExtension.class  )
class VisibilityOrquestatorServiceTest {
    private static final String productFile = "product.csv";
    private static final String stockFile= "stock.csv";
    private static final String sizeFile= "size.csv";
    private static final String MESSAGE_PRODUCTS= "[3, 2, 1]";

    @InjectMocks
    private VisibilityOrquestatorService visibilityOrquestatorService;

    @Mock
    private LoadFilesService loadFilesService;

    @Mock
    private VisibilityCheckService visibilityCheckService;

    @BeforeEach
    public void setup()  {
        ReflectionTestUtils.setField(visibilityOrquestatorService, "productFile", "product.csv");
        ReflectionTestUtils.setField(visibilityOrquestatorService, "sizeFile", "size.csv");
        ReflectionTestUtils.setField(visibilityOrquestatorService, "stockFile", "stock.csv");
    }

    @Test
    void whenGetVisibilityProducts_getAString() throws FileNotFoundException {
        final URL urlProduct= getClass().getClassLoader().getResource(productFile);
        final URL urlSize= getClass().getClassLoader().getResource(sizeFile);
        final URL urlStock= getClass().getClassLoader().getResource(stockFile);
        List <Product> products = mockProductList();
        List <Size> sizes = mockSizeList();
        HashMap<Integer, Stock> stocks = mockStockHash();

        when( loadFilesService.loadProducts( urlProduct )).thenReturn( products  );
        when( loadFilesService.loadSizes( urlSize )).thenReturn( sizes );
        when( loadFilesService.loadStocks( urlStock )).thenReturn( stocks );
        when( visibilityCheckService.checkStocks( products, sizes, stocks )).thenReturn( MESSAGE_PRODUCTS );

        String result = visibilityOrquestatorService.getVisibilityProducts();

        assertEquals( MESSAGE_PRODUCTS, result);
    }

    @Test
    void whenGetVisibilityProducts_getAnError() throws FileNotFoundException {
        final URL urlProduct= getClass().getClassLoader().getResource(productFile);



        when( loadFilesService.loadProducts( urlProduct )).thenThrow( FileNotFoundException.class );
        when( visibilityCheckService.checkStocks( null, null, null )).thenReturn( null );

        String result = visibilityOrquestatorService.getVisibilityProducts();

        assertNull( result);
    }

    private List<Size> mockSizeList() {
        Size size = new Size(1,1, false, false);
        return Arrays.asList( size );
    }

    private HashMap<Integer, Stock> mockStockHash() {
        HashMap<Integer, Stock> o = null;
        return o;
    }

    private List<Product> mockProductList() {
        Product product_1 = new Product(1,16);
        Product product_2 = new Product(2,12);
        Product product_3 = new Product(3,6);
        return Arrays.asList( product_1, product_2 , product_3 );
    }

}
