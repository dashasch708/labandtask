package com.company;

import javax.swing.*;
import java.awt.image.*;
import java.awt.*;

/** Этот класс позволяет нам отображать наши фракталы. Он является производным от javax.swing.JComponent. */

class JImageDisplay extends JComponent
{
    /** Экземпляр буферизованного изображения.Управляет изображением, содержимое которого мы можем записывать.*/
    private BufferedImage displayImage;

    /** Конструктор JImageDisplay должен принимать целочисленные
     значения ширины и высоты, и инициализировать объект BufferedImage новым
     изображением с этой шириной и высотой, и типом изображения
     TYPE_INT_RGB. */
    /**
     Метод для получения изображения из другого класса.
     */
    public BufferedImage getImage() {
        return displayImage;
    }

    public JImageDisplay(int width, int height) {
        displayImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        /** Конструктор также должен вызвать метод setPreferredSize()
         родительского класса метод с указанной шириной и высотой. Таким образом,
         когда компонент будет включен в пользовательский интерфейс, он
         отобразит на экране все изображение.
         */

        Dimension imageDimension = new Dimension(width, height);
        super.setPreferredSize(imageDimension);

    }
    /** нужно всегда
     вызывать метод суперкласса paintComponent (g) так, чтобы объекты
     отображались правильно. Затем изображение втягивается в компонент. */

    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        g.drawImage(displayImage, 0, 0, displayImage.getWidth(), displayImage.getHeight(), null);
    }
    /**Устанавливает все пиксели в данных изображения черными.*/
    public void clearImage()
    {
        int[] blankArray = new int[getWidth() * getHeight()];
        displayImage.setRGB(0, 0, getWidth(), getHeight(), blankArray, 0, 1);
    }
    /**Устанавливает пиксель определенного цвета.*/
    public void drawPixel(int x, int y, int rgbColor)
    {
        displayImage.setRGB(x, y, rgbColor);
    }
}
