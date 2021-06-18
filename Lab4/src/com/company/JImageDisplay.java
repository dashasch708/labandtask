package com.company;

import javax.swing.*;
import java.awt.image.*;
import java.awt.*;

 /** Этот класс позволяет нам отображать фракталы. Он является производным от javax.swing.JComponent. */

class JImageDisplay extends JComponent
{
    /** Управляет изображением, содержимое которого можно записать.*/
    private BufferedImage displayImage;

    /** Конструктор JImageDisplay принимает целочисленные
     значения ширины и высоты, и инициализирует объект BufferedImage новым
     изображением с этой шириной и высотой, и типом изображения
     TYPE_INT_RGB. */
    public JImageDisplay(int width, int height) {
        displayImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        /** Конструктор также вызвает метод setPreferredSize()
         родительского класса с указанной шириной и высотой. Таким образом,
         когда компонент будет включен в пользовательский интерфейс, он
         отобразит на экране все изображение.
         */
        Dimension imageDimension = new Dimension(width, height);
        super.setPreferredSize(imageDimension);

    }
    /** Вызывать метод суперкласса paintComponent (g) нужно так, чтобы объекты
     отображались правильно. Затем изображение втягивается в компонент. */

    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        g.drawImage(displayImage, 0, 0, displayImage.getWidth(), displayImage.getHeight(), null);
    }
    /**Устанавливает все пиксели изображения в черный.*/
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
