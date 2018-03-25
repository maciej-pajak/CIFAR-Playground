package pl.maciejpajak;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import static org.junit.Assert.*;

public class FixedSortedStashTest {

    @Test
    public void add() {
        FixedSortedStash<Integer> fss = new FixedSortedStash<>(10);
        Random r = new Random();
        int[] testData = r.ints(100,1, 100).toArray();

        for (int i : testData) {
            fss.add(i);
        }

        Arrays.sort(testData);
        assertEquals((Integer) testData[testData.length - 1], fss.getLastElem());
    }

}