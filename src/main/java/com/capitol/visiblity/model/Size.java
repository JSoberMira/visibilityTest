package com.capitol.visiblity.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Size {
    private int id;
    private int productId;
    private boolean backSoon;
    private boolean special;
}
