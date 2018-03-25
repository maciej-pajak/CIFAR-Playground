package pl.maciejpajak;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.PriorityQueue;

public class CifarData implements Iterable<CifarData.ImagePointer> {

    private byte[][] data;

    private ImagePointer image;

    public CifarData(int numOfImagesInFile, File... files) {
        image = new ImagePointer();
        data = new byte[files.length * numOfImagesInFile][3073];
        int imageCount = 0;

        try {
            FileInputStream fis;
            for (File f : files) {
                fis = new FileInputStream(f);
                for (int j = 0 ; j < numOfImagesInFile ; j++) {
                    fis.read(data[imageCount++], 0, 3073);
                }
                fis.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public byte getLabel(ImagePointer p) {
        return data[p.index][0];
    }

    public byte[] getImageAsByteArray(ImagePointer p) {
        return Arrays.copyOfRange(data[p.index], 1, 3073);
    }

    public class ImagePointer {

        private int index;

        public ImagePointer(ImagePointer pointer) {
            this.index = pointer.index;
        }

        private ImagePointer() {
        }

        private void setIndex(int index) {
            this.index = index;
        }

        private byte[][] getData() {
            return data;
        }

        public int compareImages(ImagePointer o) {
            int score = 0;
            for (int i = 1 ; i < 3073 ; i++) {
                score += Math.abs(data[this.index][i] - o.getData()[o.index][i]);
            }
            return score;
        }

        @Override
        public String toString() {
            return "ImagePointer{" +
                    "index=" + index +
                    '}';
        }
    }

    @Override
    public Iterator<ImagePointer> iterator() {
        return new ImageIterator();
    }

    private class ImageIterator implements Iterator<ImagePointer> {

        int cursor = 0;

        @Override
        public boolean hasNext() {
            return cursor < data.length;
        }

        @Override
        public ImagePointer next() {
            image.setIndex(cursor++);
            return image;
        }
    }


}
