package FinalVersion;

import java.awt.Graphics;


abstract public class AbstractBrick extends CObject {

	abstract public void destroy();

	abstract public boolean isDestroyed();

	abstract public boolean isSetDie();

	abstract public void setDestroyed(boolean destroyed);

	abstract public int getScore();

	abstract public void draw(Graphics g);

	abstract public int getType();

}
