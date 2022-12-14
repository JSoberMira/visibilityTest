package com.capitol.visiblity.service;

import com.capitol.visiblity.model.Product;
import com.capitol.visiblity.model.Size;
import com.capitol.visiblity.model.Stock;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


class LoadFilesServiceTest {

    private static final String productFile = "product.csv";
    private static final String productFileError = "productError.csv";
    private static final String stockFile= "stock.csv";
    private static final String sizeFile= "size.csv";
    private static final String sizeFileError= "sizeError.csv";
    private static final Integer PRODUCT_ID= 6;
    private static final Integer SIZE_ID= 17;
    private LoadFilesService loadFilesService = new LoadFilesService();
    @Test
    void whenLoadProducts_thenCorrect() throws IOException {
        final URL urlProduct= getClass().getClassLoader().getResource(productFile);

        List<Product> products = loadFilesService.loadProducts(urlProduct);

        assertNotNull( products );
        assertEquals(PRODUCT_ID, products.get( 0 ).getId() );
    }

    @Test
    void whenLoadProducts_thenException() throws FileNotFoundException {
        final URL urlProduct= getClass().getClassLoader().getResource(productFileError);
        List<Product> products = loadFilesService.loadProducts(urlProduct);

        assertEquals(1, products.size() );
    }

    @Test
    void whenLoadSize_thenCorrect() throws IOException {
        final URL urlSize= getClass().getClassLoader().getResource(sizeFile);

        List<Size> sizes = loadFilesService.loadSizes(urlSize);

        assertNotNull( sizes );
        assertEquals(SIZE_ID, sizes.get( 0 ).getId() );
        assertEquals(PRODUCT_ID, sizes.get( 0 ).getProductId() );

    }

    @Test
    void whenLoadSize_thenException() throws FileNotFoundException {
        final URL urlSize= getClass().getClassLoader().getResource(sizeFileError);
        List<Size> sizes = loadFilesService.loadSizes(urlSize);

        assertEquals(1, sizes.size() );
    }

    @Test
    void whenLoadStock_thenCorrect() throws IOException {
        final URL urlStock= getClass().getClassLoader().getResource(stockFile);

        HashMap<Integer, Stock> stock = loadFilesService.loadStocks(urlStock);

        assertNotNull( stock );
        assertEquals(SIZE_ID, stock.get( SIZE_ID ).getSizeId() );

    }
}
