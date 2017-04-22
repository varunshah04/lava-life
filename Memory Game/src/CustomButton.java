import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JButton;

/**
 * Custom Button that extends JButton, enabling us to change the color of a button when we hover over it.
 * Created by Lam Ngo
 * @version 02/22/2017
 */
public class CustomButton extends JButton {
        private Color hoverBackgroundColor = Color.yellow;   // when the button is hovered around.
        private Color pressedBackgroundColor = Color.red;    //when the button is cliecked  on

        /**
         * Constructor that creates a custom button
         */
        public CustomButton() {
            this(null);
        }

        /**
         * Constructor that creates a custom button using the given text
         *
         * @param text The text that will be displayed on the button
         */
        public CustomButton(String text) {
            super(text);
            super.setContentAreaFilled(false);
        }

        @Override
        protected void paintComponent(Graphics g) {
            if (getModel().isPressed()) {                   // check when the button is pressed
                g.setColor(pressedBackgroundColor);
            } else if (getModel().isRollover()) {           //check when the button is hoveraround.
                g.setColor(hoverBackgroundColor);
            } else  {
                g.setColor(getBackground());
            }
            g.fillRect(0, 0, getWidth(), getHeight());
            super.paintComponent(g);
        }

        @Override
        public void setContentAreaFilled(boolean b) {
    }
}