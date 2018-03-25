package pl.maciejpajak;

import javax.swing.*;
import java.io.File;
import java.util.Iterator;

public class Cifar {
    private static final String PATH = "/Users/mac/Downloads/cifar-10-batches-bin";


    public static void main(String[] args) {
        File[] dataSetsFiles = {new File(PATH + "/data_batch_1.bin"),
                new File(PATH + "/data_batch_2.bin"),
                new File(PATH + "/data_batch_3.bin"),
                new File(PATH + "/data_batch_4.bin"),
                new File(PATH + "/data_batch_5.bin")
        };

        File testDataFile = new File(PATH + "/test_batch.bin");

        CifarData cifarData = new CifarData(10000, dataSetsFiles);
        CifarData cifarTest = new CifarData(10000, testDataFile);

        int right = 0;
        int wrong = 0;

        int count = 1;
        boolean res = false;

        long start = System.currentTimeMillis();

//        CifarUI window = new CifarUI();

        for (CifarData.ImagePointer testPointer : cifarTest) {
            System.out.println(count++);
            FixedSortedStash<Neighbour> fss = new FixedSortedStash<>(1);

            for (CifarData.ImagePointer dataPointer : cifarData) {

                int score = testPointer.compareImages(dataPointer);

                fss.add(new Neighbour(cifarData.new ImagePointer(dataPointer), score));

            }
//            System.out.println(cifarTest.getLabel(testPointer) + " : " + cifarData.getLabel(fss.get(0).getPointer()));
            if (cifarTest.getLabel(testPointer) == cifarData.getLabel(fss.get(0).getPointer())) {
                right++;
//                res = true;
            } else {
                wrong++;
//                res = false;
            }
//            System.out.println("Accuracy " + right / (double) (right + wrong) * 100 + "  %");
//            window.setTestImage(cifarTest.getImageAsByteArray(testPointer));
//            window.setResultImage(cifarData.getImageAsByteArray(fss.get(0).getPointer()));
//            window.setResult(res ? "RIGHT" : "WRONG");
//
//            try {
//                Thread.sleep(1000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }

        }

        System.out.println("Execution time: " + (System.currentTimeMillis() - start) / 1000 + " s");
        System.out.println("Right: " + right);
        System.out.println("Wrong: " + wrong);

        System.out.println("Accuracy " + ((double) right) / (double) (right + wrong) + "  %");

    }

}
