package pl.maciejpajak;

import javax.swing.*;
import java.awt.*;
import java.awt.image.*;

public class CifarUI extends JFrame {

    private JPanel imageA;
    private JPanel imageB;

    private JLabel testImage;
    private JLabel resultImage;
    private JLabel result;

    public CifarUI() {
        setTitle("CIFAR-100");

        testImage = new JLabel();
        testImage.setBounds(0,0, 32, 32);

        resultImage = new JLabel();
        resultImage.setBounds(50,0, 32, 32);

        result = new JLabel();
        result.setBounds(100, 0, 100, 30);

        add(testImage);
        add(resultImage);
        add(result);


//        add(imageA);
//        add(imageB);

        setSize(400,400);
        setLayout(null);
        setVisible(true);
    }

    public void setTestImage(byte[] imageByte) {
        BufferedImage image = getImageFromByte(refactor(imageByte), 32, 32);

        testImage.setIcon(new ImageIcon(image));
        testImage.revalidate();
        testImage.repaint();
    }

    public void setResultImage(byte[] imageByte) {
        BufferedImage image = getImageFromByte(refactor(imageByte), 32, 32);

        resultImage.setIcon(new ImageIcon(image));
        resultImage.revalidate();
        resultImage.repaint();
    }

    public void setResult(String res) {
        result.setText(res);
        result.repaint();
    }

    private static BufferedImage getImageFromByte(byte[] byteArray, int width, int height) {
        DataBuffer buffer;
        buffer = new DataBufferByte(byteArray, byteArray.length);

        //3 bytes per pixel: red, green, blue
        WritableRaster raster = Raster.createInterleavedRaster(buffer, width, height, 3 * width, 3, new int[] {0, 1, 2}, null);
        ColorModel cm = new ComponentColorModel(ColorModel.getRGBdefault().getColorSpace(), false, true, Transparency.OPAQUE, DataBuffer.TYPE_BYTE);
        BufferedImage image = new BufferedImage(cm, raster, true, null);

        return image;
    }

    private static byte[] refactor(byte[] arr) {
        byte[] res = new byte[arr.length];
        int pos = 0;
        for (int i = 0; i < arr.length ; i += 3) {
            pos = i / 3;
            res[i] = arr[pos];
            res[i + 1] = arr[1024 + pos];
            res[i + 2] = arr[2048 + pos];
        }
        return res;
    }

}
