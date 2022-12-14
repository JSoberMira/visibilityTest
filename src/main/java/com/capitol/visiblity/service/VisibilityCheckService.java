package com.capitol.visiblity.service;

import com.capitol.visiblity.model.Product;
import com.capitol.visiblity.model.Size;
import com.capitol.visiblity.model.Stock;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;

@Service
@Slf4j
public class VisibilityCheckService {
    public String checkStocks(final List<Product> products, final List<Size> sizes, final HashMap<Integer, Stock> stocks ) {
        List<Product> finalProducts = new ArrayList<>();
        products.stream().forEach( product -> {
            if (isVisibilityProduct(product, sizes, stocks)) {
                finalProducts.add( product );
            }

        } );
        return finalProducts.stream().map(Product::getId).collect( Collectors.toList()).toString();
    }

    private boolean isVisibilityProduct(final Product product, final List<Size> sizes, final HashMap<Integer, Stock> stocks) {
        AtomicBoolean show = new AtomicBoolean( false );
        AtomicBoolean haveSpecialWithStock = new AtomicBoolean( false );
        AtomicBoolean haveNotSpecialWithStock = new AtomicBoolean( false );
        List<Size> sizesProduct = sizes.stream()
                .filter( size -> size.getProductId() == product.getId() ).collect( Collectors.toList());
        sizesProduct.stream().forEach( size -> {
            if (stocks.containsKey( size.getId() ) ) {
                if (isException( size, stocks ) || isBackSoon( size ) ) {
                    show.set( true );
                }
                haveSpecialWithStock.set( isSpecialWithStock( size, stocks ) );
                haveNotSpecialWithStock.set( isNotSpecialWithStock( size, stocks ) );
            }
        } );
        if (haveNotSpecialWithStock.get() && haveSpecialWithStock.get() ){
            show.set( true );
        }

        return show.get();
    }

    private boolean isNotSpecialWithStock(final Size size, final HashMap<Integer, Stock> stocks) {
        final Stock stock = stocks.get( size.getId() );
        return stock.getQuantity()>0 && !size.isSpecial();
    }

    private boolean isSpecialWithStock(final Size size, final HashMap<Integer, Stock> stocks) {
        final Stock stock = stocks.get( size.getId() );
        return stock.getQuantity()>0 && size.isSpecial();
    }

    private boolean isBackSoon(final Size size) {
        return size.isBackSoon() && !size.isSpecial();
    }

    private boolean isException(final Size size, final HashMap<Integer, Stock> stocks) {
        final Stock stock = stocks.get( size.getId() );
        return stock.getQuantity()>0 && !size.isSpecial();
    }
}
