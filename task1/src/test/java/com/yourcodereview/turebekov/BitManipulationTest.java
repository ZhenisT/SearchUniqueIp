package com.yourcodereview.turebekov;

import com.yourcodereview.turebekov.BitManipulation;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

class BitManipulationTest {
    BitManipulation manipulation = new BitManipulation();

    public List<String[]> IPArray() {
        return Arrays.asList(
                new String[]{"192", "48", "0", "1"},
                new String[]{"192", "49", "0"},
                new String[]{"192", "50"},
                new String[]{"192"},
                new String[]{}
        );
    }

    public List<String> listWithFiveUIP = Arrays.asList(
            "192.168.40.1",
            "192.168.40.2",
            "192.168.40.3",
            "192.168.40.4",
            "192.168.40.5"
    );

    public List<String> listWithTwoUIP = Arrays.asList(
            "192.168.40.5",
            "192.168.40.2",
            "192.168.40.2",
            "192.168.40.2",
            "192.168.40.5"
    );

    @Test
    public void convertIPArrayTo32Bits() {
        Assertions.assertEquals(-1070596095, manipulation.bitConverter(IPArray().get(0)));
    }

    @Test
    public void convertIPArrayTo24Bits() {
        Assertions.assertEquals(-1070530560, manipulation.bitConverter(IPArray().get(1)));
    }

    @Test
    public void convertIPArrayTo16Bits() {
        Assertions.assertEquals(-1070465024, manipulation.bitConverter(IPArray().get(2)));
    }

    @Test
    public void convertIPArrayTo8Bits() {
        Assertions.assertEquals(-1073741824, manipulation.bitConverter(IPArray().get(3)));
    }

    @Test
    public void convertEmptyIPArray() {
        Assertions.assertEquals(0, manipulation.bitConverter(IPArray().get(4)));
    }

    @Test
    public void containsFiveUniqueIPs() {
        for (String ip : listWithFiveUIP)
            manipulation.setBits(ip);
        Assertions.assertEquals(5, manipulation.getUniqueIp());
    }

    @Test
    public void containsTwoUniqueIPs() {
        for (String ip : listWithTwoUIP)
            manipulation.setBits(ip);
        Assertions.assertEquals(2, manipulation.getUniqueIp());
    }

    @Test
    public void notExistUniqueIPs() {
        Assertions.assertEquals(0, manipulation.getUniqueIp());
    }

}
