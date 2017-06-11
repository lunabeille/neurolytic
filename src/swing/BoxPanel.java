package swing;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.LayoutManager;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

class BoxPanel extends JPanel 
{

  public void paintChildren(Graphics g) 
  {
    super.paintChildren(g);
    Dimension size = getSize();
    LayoutManager manager = getLayout();
    if ((manager != null) && (manager instanceof BoxLayout)) 
    {
      BoxLayout layout = (BoxLayout) manager;
      boolean vertical = true;
      if (vertical) 
      {
        int axis = (int) (layout.getLayoutAlignmentX(this) * size.width);
        g.fillRect(axis - 1, 0, 3, size.height);
      } 
      else 
      {
        int axis = (int) (layout.getLayoutAlignmentY(this) * size.height);
        g.fillRect(0, axis - 1, size.width, 3);
      }
    }
  }

}