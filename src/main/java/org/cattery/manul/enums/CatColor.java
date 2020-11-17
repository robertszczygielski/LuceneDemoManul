package org.cattery.manul.enums;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public enum CatColor {

    WHITE,
    RED,
    BLACK,
    ;

    private static final List<CatColor> VALUES =
            List.of(values());
    private static final int SIZE = VALUES.size();
    private static final Random RANDOM = new Random();

    public static CatColor randomColor()  {
        return VALUES.get(RANDOM.nextInt(SIZE));
    }
}
