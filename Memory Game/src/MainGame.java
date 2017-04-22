import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * an Applet for an object oriented MEMORYGAME
 *
 * @author: Lam Ngo, James Murphy, Varun Shah and Diego Bazan
 * @version: 02/24/2017
 */
public class MainGame extends JApplet {
    private static final long serialVersionUID = 1L;
    private MemoryGame myGame;
    private final int APPLET_WIDTH = 850, APPLET_HEIGHT = 700;
    private static CanvasPanel canvasPanel;
    private Container cp;
    private JPanel memoryPanel,loginPanel;
    private JTextField username;
    private CustomButton flipButton,removeButton, quitButton, newGameButton;
    private JLabel turnCounter,timeCounter, login;
    private boolean remove;
    private int turnCount;
    private static final String turn = "Turn: ";
    private Timer timer;
    private int time;

    public void init(){
        try {
            // Set cross-platform Java L&F
            UIManager.setLookAndFeel(
                    UIManager.getCrossPlatformLookAndFeelClassName());
        } catch (UnsupportedLookAndFeelException e) {
            // handle exception
        } catch (ClassNotFoundException e) {
            // handle exception
        } catch (InstantiationException e) {
            // handle exception
        } catch (IllegalAccessException e) {
            // handle exception
        }
        //Login Screen
        loginPanel = new JPanel();
        login = new JLabel("Log In");
        username = new JTextField(3);

        //Memory Panel to play Memory
        memoryPanel = new JPanel();
        newGameButton = new CustomButton("new game");
        quitButton = new CustomButton("main menu");
        removeButton = new CustomButton("remove");
        flipButton = new CustomButton("flip");
        turnCounter = new JLabel(turn + turnCount);
        removeButton.setBackground(Color.white);
        flipButton.setBackground(Color.white);
        quitButton.setBackground(Color.white);
        newGameButton.setBackground(Color.white);
        memoryPanel.setBackground(Color.cyan);
        memoryPanel.add(newGameButton);
        memoryPanel.add(removeButton);
        memoryPanel.add(flipButton);
        memoryPanel.add(quitButton);
        memoryPanel.add(turnCounter);
        memoryPanel.setLayout(new BoxLayout(memoryPanel,BoxLayout.PAGE_AXIS));

        newGameButton.addActionListener(new NewGameButtonListener());
        quitButton.addActionListener(new QuitButtonListener());
        flipButton.addActionListener(new FlipButtonListener());
        removeButton.addActionListener(new RemoveButtonListener());

        canvasPanel = new CanvasPanel();
        canvasPanel.setBackground(Color.pink);
        myGame = new MemoryGame();
        turnCount = 0;
        timeCounter = new JLabel();
        memoryPanel.add(timeCounter);
        time = 0;
        timer = new Timer(1000, new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                time++;
                if (time < 60){
                    if (time < 10){
                        timeCounter.setText("0" + ":" + "0" + time);
                    }else{
                        timeCounter.setText("0" + ":" + time);
                    }
                }else{
                    int min = time/60;
                    if (min < 10 && (time - min*60 < 10)){
                        timeCounter.setText(0 + min + ":" + 0 + (time - min*60));
                    }else{
                        timeCounter.setText(min + ":" + (time - min*60));
                    }
                }

            }
        });
        timer.start(); // timer to count time upward

        cp = getContentPane();
        cp.setLayout(new BorderLayout());
        cp.add(memoryPanel,BorderLayout.EAST);
        cp.add(canvasPanel,BorderLayout.CENTER);
        cp.setBackground(Color.pink);

        setSize(APPLET_WIDTH, APPLET_HEIGHT);
    }

    private void turnUpdate(){
        turnCounter.setText(turn + turnCount);
        memoryPanel.revalidate();
    }

    /**
     * What to do when newGameButton is pressed.
     */
    private class NewGameButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            String[] options = { "yes", "no" };
            int result = JOptionPane.showOptionDialog(null, "Are you sure?", "Warning",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE,
                    null, options, options[0]);
            if (result == 0) {
                myGame.reDeal();
                turnCount = 0;
                remove = false;
                time = 0;
                turnUpdate();
            }
            repaint();
        }
    }

    /**
     * What to do when newGameButton is pressed.
     */
    private class RemoveButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            if (myGame.removeandflipable()){
                if (remove){
                    JOptionPane.showMessageDialog(null, "that's a match!");
                    myGame.removeSelected();
                    remove = false;
                    if (myGame.checkEndCondition()){
                        String[] options = { "yes", "no" };
                        int result = JOptionPane.showOptionDialog(null, "you won! want to play again?", "u the best",
                                JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE,
                                null, options, options[0]);
                        if (result == 0){
                            myGame.reDeal();
                            time = 0;
                            turnCount = 0;
                        }else{
                            System.exit(0);
                        }
                    }
                }else{
                    JOptionPane.showMessageDialog(null, "Not a match, please flip!");
                }
            }else{
                JOptionPane.showMessageDialog(null, "please choose two cards first.");
            }
            repaint();
        }
    }

    /**
     * What to do when newGameButton is pressed.
     */
    private class QuitButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            String[] options = { "yes", "no" };
            int result = JOptionPane.showOptionDialog(null, "Are you sure?", "Warning",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE,
                    null, options, options[0]);
            if (result == 0){
                System.exit(0);
            }
            repaint();
        }
    }

    /**
     * What to do when newGameButton is pressed.
     */
    private class FlipButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            if(myGame.removeandflipable()){
                if (remove){
                    JOptionPane.showMessageDialog(null, "they are a match, don't flip");
                }else{
                    myGame.deSelectCards();
                }
            }else{
                JOptionPane.showMessageDialog(null, "please choose two cards first.");
            }
            repaint();
        }
    }

    /**
     * @return  canvasWidth for playingDeck
     */
    public static int getCanvasWidth(){
        return canvasPanel.getWidth();
    }

    /**
     * @return canvasHeight for playingDeck
     * @return
     */
    public static int getCanvasHeight(){
        return canvasPanel.getHeight();
    }

    /**
     * CanvasPanel is the class upon which we actually draw.  It listens
     * for mouse events.
     */
    private class CanvasPanel extends JPanel implements MouseListener, MouseMotionListener {
        private static final long serialVersionUID = 0;

        /**
         * Constructor just needs to set up the CanvasPanel as a listener.
         */
        public CanvasPanel() {
            addMouseListener(this);
            addMouseMotionListener(this);

        }

        /**
         * Paint the whole drawing.
         * if it is in tutorial, printout the tutorial message.
         * @page the Graphics object to draw on
         */
        public void paintComponent(Graphics page) {
            super.paintComponent(page);
            myGame.display(page);
        }

        /**
         * When the mouse is clicked, check to see if setgame can remove card and if the game ends.
         */
        public void mouseClicked(MouseEvent event) {
        }

        //we don't use these.
        public void mousePressed(MouseEvent event) {
            int result = myGame.getCards(event.getPoint());
            if (result == 1){
                remove = true;
                turnUpdate();
            }else if (result == 0){
                turnUpdate();
                remove = false;
            }else{
                remove = false;
                turnCount++;
            }
            repaint();
        }

        public void mouseDragged(MouseEvent event)  { }
        public void mouseReleased(MouseEvent event) { }
        public void mouseEntered(MouseEvent event) { }
        public void mouseExited(MouseEvent event) { }
        public void mouseMoved(MouseEvent event) { }
    }
}
