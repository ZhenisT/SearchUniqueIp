package com.yourcodereview.turebekov;

import java.util.Arrays;
import java.util.BitSet;

/*
 * IPv4 uses 32-bit (four-byte) addresses,
 * limiting the address space to 4 294 967 296 (232) possible locations.
 * The traditional notation of an IPv4 address is a notation in the format
 * of four decimal numbers (from 0 to 255) separated by dots.
 */
public class UniqueIPNumberFinder {
    // Delimiter for IPv4 subnet mask
    private static final String IPV4_SUBNET_MASK_DELIMITER = "\\.";

    /*
     * When max IP addresses space is 4 294 967 296 and BitSet size is limited to Integer.MAX_VALUE
     * and when the max value is greater than Integer.MAX_VALUE it takes a negative value.
     * So, to mark all possible values, needs to have two places to store values.
     */
    private final BitSet bitSetPositiveInt = new BitSet(Integer.MAX_VALUE);
    private final BitSet bitSetNegativeInt = new BitSet(Integer.MAX_VALUE);

    /*
     * This method adds an ip address to BitSet after transforming IP address string to
     * a subnet mask array using the "." delimiter.
     * Converting each element of the mask to a left-shifted bit presentation and getting its value.
     * Depending on the result, whether the value is positive or negative. a place is chosen to save the mark.
     */
    public void markIPAddress(String ipAddress) {
        if (ipAddress.isEmpty()) {
            return;
        }

//        String[] subNetMaskArray = convertIPStringToSubNetMaskArray(ipAddress);

//        int bits = getSubNetMasksIntValue(subNetMaskArray);
        int bits = getSubNetMasksIntValue2(
                convertIPStringToSubNetMaskArray2(ipAddress));

        if (bits >= 0) {
            bitSetPositiveInt.set(bits);
        } else {
            bitSetNegativeInt.set(~bits);
        }
    }

    //Converting IP address string to a subnet mask array using the "." delimiter.
    private String[] convertIPStringToSubNetMaskArray(String ipAddress) {
        return ipAddress.split(IPV4_SUBNET_MASK_DELIMITER);
    }

    private int[] convertIPStringToSubNetMaskArray2(String ip){
        return Arrays.stream(ip.split(IPV4_SUBNET_MASK_DELIMITER)).mapToInt(Integer::parseInt).toArray();
    }

    //Converting each element of the mask to a left-shifted bit presentation and getting its value.
    public int getSubNetMasksIntValue2(int[] subNetMaskArray) {
        return switch (subNetMaskArray.length) {
            case 4 -> subNetMaskArray[0] << 24
                    | subNetMaskArray[1] << 16
                    | subNetMaskArray[2] << 8
                    | subNetMaskArray[3];

            case 3 -> subNetMaskArray[0] << 24
                    | subNetMaskArray[1] << 16
                    | subNetMaskArray[2] << 8;

            case 2 -> subNetMaskArray[0] << 24
                    | subNetMaskArray[1] << 16;

            case 1 -> subNetMaskArray[0] << 24;

            default -> 0;
        };
    }

    public int getSubNetMasksIntValue(String[] subNetMaskArray) {
        return switch (subNetMaskArray.length) {
            case 4 -> Integer.parseInt(subNetMaskArray[0]) << 24
                    | Integer.parseInt(subNetMaskArray[1]) << 16
                    | Integer.parseInt(subNetMaskArray[2]) << 8
                    | Integer.parseInt(subNetMaskArray[3]);

            case 3 -> Integer.parseInt(subNetMaskArray[0]) << 24
                    | Integer.parseInt(subNetMaskArray[1]) << 16
                    | Integer.parseInt(subNetMaskArray[2]) << 8;

            case 2 -> Integer.parseInt(subNetMaskArray[0]) << 24
                    | Integer.parseInt(subNetMaskArray[1]) << 16;

            case 1 -> Integer.parseInt(subNetMaskArray[0]) << 24;

            default -> 0;
        };
    }

    //Returns the number of marked values in the BitSet
    public long getUniqueIpNumber() {
        return bitSetPositiveInt.cardinality() + bitSetNegativeInt.cardinality();
    }
}
