package com.yourcodereview.turebekov;

import java.util.BitSet;

public class BitManipulation {
    //Max IP number in IPv4 is 4_294_967_296 (2^32)
    private final BitSet bitSetPositiveInt = new BitSet(Integer.MAX_VALUE);
    private final BitSet bitSetNegativeInt = new BitSet(Integer.MAX_VALUE);

    public void setBits(String line) {
        if (line.isEmpty())
            return;

        String[] token = line.split("\\.", 4);

        int bits = bitConverter(token);

        if (bits >= 0)
            bitSetPositiveInt.set(bits);
        else
            bitSetNegativeInt.set(~bits);
    }

    public int bitConverter(String[] token) {
        return switch (token.length) {
            case 4 -> Integer.parseInt(token[0]) << 24
                    | Integer.parseInt(token[1]) << 16
                    | Integer.parseInt(token[2]) << 8
                    | Integer.parseInt(token[3]);

            case 3 -> Integer.parseInt(token[0]) << 24
                    | Integer.parseInt(token[1]) << 16
                    | Integer.parseInt(token[2]) << 8;

            case 2 -> Integer.parseInt(token[0]) << 24
                    | Integer.parseInt(token[1]) << 16;

            case 1 -> Integer.parseInt(token[0]) << 24;

            default -> 0;
        };
    }

    public long getUniqueIp() {
        return bitSetPositiveInt.cardinality() + bitSetNegativeInt.cardinality();
    }
}
