package com.capitol.visiblity.service;

import com.capitol.visiblity.model.Product;
import com.capitol.visiblity.model.Size;
import com.capitol.visiblity.model.Stock;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.net.URL;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

@Service
@Slf4j
public class VisibilityOrquestatorService {

    @Value("${product.name.file}")
    private String productFile;
    @Value("${stock.name.file}")
    private String stockFile;
    @Value("${size.name.file}")
    private String sizeFile;
    private List<Product> products;
    private List<Size> sizes;
    private HashMap<Integer, Stock> stocks;

    @Autowired
    private LoadFilesService loadFilesService;

    @Autowired
    private VisibilityCheckService visibilityCheckService;

    public String getVisibilityProducts() {
        log.warn( "START LOAD FILES" );
        loadFiles();
        log.warn( "END LOAD FILES" );
        orderProducts();
        return visibilityCheckService.checkStocks( products, sizes, stocks);
    }

    private void loadFiles() {
        final URL urlProduct = getClass().getClassLoader().getResource(productFile);
        final URL urlSize = getClass().getClassLoader().getResource(sizeFile);
        final URL urlStock = getClass().getClassLoader().getResource(stockFile);
        try {
            products = loadFilesService.loadProducts(urlProduct);
            sizes = loadFilesService.loadSizes(urlSize);
            stocks = loadFilesService.loadStocks(urlStock);
        } catch (FileNotFoundException e) {
            log.error( e.getMessage() );
        }
    }

    private void orderProducts() {
        if (products!=null) {
            products.sort( Comparator.comparing( Product::getSequence ) );
        }
    }

}
