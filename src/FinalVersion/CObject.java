package FinalVersion;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class CObject {
	protected int x;
    protected int y;
    protected int width;
    protected int heigth;
    protected BufferedImage image;

    public void setX(int Ix) {
        this.x = Ix;
    }

    public int getX() {
        return x;
    }

    public void setY(int Iy) {
        this.y = Iy;
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return heigth;
    }

    BufferedImage getImage()
    {
      return image;
    }

    public Rectangle getRect()
    {
      return new Rectangle(x, y,image.getWidth(), image.getHeight());
    }
}
