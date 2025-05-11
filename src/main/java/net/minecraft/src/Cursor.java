import java.awt.Cursor;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import javax.swing.ImageIcon;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Cursor {

    public static void main(String[] args) {
        ImageIcon imageIcon = new ImageIcon("cursor.png")
        Image image = imageIcon.getImage();

        Cursor customCursor = Toolkit.getDefaultToolkit().createCustomCursor(image, new Point(0, 0), "customCursor");

        Component testComponent = new Component() {};

        testComponent.setCursor(customCursor);

        while(true){
            try{
                Thread.sleep(1000); // Sleep for 1 second
            } catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }
}
