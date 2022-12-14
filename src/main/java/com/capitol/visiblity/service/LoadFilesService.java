package com.capitol.visiblity.service;

import com.capitol.visiblity.model.Product;
import com.capitol.visiblity.model.Size;
import com.capitol.visiblity.model.Stock;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

@Service
@Slf4j
public class LoadFilesService {

    private static final String COMMA_DELIMITER = ",";

    public List<Product> loadProducts(URL resource) throws FileNotFoundException {
        List<Product> products = new ArrayList<>();
        try (Scanner scanner = new Scanner(new File(resource.getFile()) ) ) {
            while (scanner.hasNextLine()) {
                List<String> records = getRecordFromLine(scanner.nextLine());
                Product product = new Product( converToInt( records.get( 0 ) ), converToInt( records.get( 1 ) ) );
                products.add( product);
            }
        }
        return products;
    }

    public List< Size> loadSizes(URL resource) throws FileNotFoundException {
        List<Size> sizes = new ArrayList<>();
        try (Scanner scanner = new Scanner(new File(resource.getFile()) ) ) {
            while (scanner.hasNextLine()) {
                List<String> records = getRecordFromLine(scanner.nextLine());
                Size size = new Size( converToInt( records.get( 0 ) ), converToInt( records.get( 1 ) ),
                        converToBoolean( records.get( 2 ) ), converToBoolean( records.get( 3 ) ) );
                sizes.add( size);
            }
        }
        return sizes;
    }

    public HashMap<Integer,Stock> loadStocks(URL resource) throws FileNotFoundException {
        HashMap<Integer, Stock> stocks = new HashMap<>();
        try (Scanner scanner = new Scanner(new File(resource.getFile()) ) ) {
            while (scanner.hasNextLine()) {
                List<String> records = getRecordFromLine(scanner.nextLine());
                Stock stock = new Stock( converToInt( records.get( 0 ) ), converToInt( records.get( 1 ) ) );
                stocks.put( stock.getSizeId(), stock);
            }
        }
        return stocks;
    }

    private int converToInt(String value) {
        int result = 0;
        try {
            result = Integer.parseInt( value.trim() );
        } catch ( Exception e ) {
            log.error( "Error message to {}. Message {} ",value, e.getMessage() );
        }

        return result;
    }

    private boolean converToBoolean(String value) {
        return Boolean.parseBoolean( value.trim() );
    }

    private List<String> getRecordFromLine(String line) {
        List<String> values = new ArrayList<>();
        try (Scanner rowScanner = new Scanner(line)) {
            rowScanner.useDelimiter(COMMA_DELIMITER);
            while (rowScanner.hasNext()) {
                values.add(rowScanner.next());
            }
        }
        return values;
    }


}
