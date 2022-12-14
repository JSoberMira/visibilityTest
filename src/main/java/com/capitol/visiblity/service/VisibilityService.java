package com.capitol.visiblity.service;

import com.capitol.visiblity.model.Product;
import com.capitol.visiblity.model.Size;
import com.capitol.visiblity.model.Stock;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.HashSet;

@Service
@Slf4j
public class VisibilityService {

    private HashMap<Integer, Product> products;
    private HashMap<Integer, Size> sizes;
    private HashMap<Integer, Stock> stocks;

    @Autowired
    private LoadFilesService loadFilesService;

    public void getVisibilityProducts() {
        log.info( "START LOAD FILES" );
        loadFiles();
        log.info( "END LOAD FILES" );
    }

    private void loadFiles() {
        try {
            products = loadFilesService.loadProducts();
            log.info( "See products {} ", products.toString() );
            sizes = loadFilesService.loadSizes();
            log.info( "See sizes {} ", sizes.toString() );
            stocks = loadFilesService.loadStocks();
            log.info( "See stocks {} ", stocks.toString() );

        } catch (FileNotFoundException e) {
            log.error( e.getMessage() );
        }

    }


}
