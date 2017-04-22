import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * an Applet for an object oriented SETGAME
 *
 * @author: Lam Ngo, James Murphy, Varun Shah and Diego Bazan
 * @version: 02/24/2017
 */
public class MainGame extends JApplet {
    private static final long serialVersionUID = 1L;
    private final int APPLET_WIDTH = 700, APPLET_HEIGHT = 500;
    private static final String CARDS_LEFT = "cards in deck: ";
    private static final String SET_COUNT = "Set: ";

    private SetGame myGame;     // a set game object
    private static CanvasPanel canvasPanel;
    private JPanel solitairePanel, menuPanel, titlePanel, switchPanel, tutorialPanel;       // panels of the applet
    private JLabel titleLabel;
    private CustomButton showButton;
    private CustomButton solitaireButton, tutorialButton, dealButton, addButton, quitButton,
    switchsolitaireButton, switchtutorialButton, switchQuitButton, showAllButton, readyButton; // various button of the applet
    private Container cp;
    private boolean inTutorial = false; // a boolean to suggest when we are in tutorial mode
    private long startTime;
    private int count;
    private JLabel setCount, cardCount, counter;    // JLabel to display text
    private Timer timer;                        // A timer object

    /**
     * Set up the buttons and canvas and register the listeners.
     */
    public void init() {

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

        myGame = new SetGame();    // initialize the game
        canvasPanel = new CanvasPanel();
        canvasPanel.setBackground(Color.pink); // initilize the canvas and set it background color to pink.

        //Main menu Panel
        menuPanel = new JPanel(); //This holds the buttons used to choose a game mode.
        titlePanel = new JPanel();
        titleLabel = new JLabel("Welcome To Our Game of Set!");
        titleLabel.setFont(new Font("Monospaced", Font.PLAIN, 30));
        titlePanel.setLayout(new FlowLayout());
        titlePanel.add(titleLabel);
        titlePanel.setBackground(Color.pink);
        solitaireButton = new CustomButton("solitaire");
        solitaireButton.setFont(new Font("Monospaced", Font.PLAIN, 20));
        tutorialButton = new CustomButton("tutorial");
        tutorialButton.setFont(new Font("Monospaced", Font.PLAIN, 20));
        quitButton = new CustomButton("quit");
        quitButton.setFont(new Font("Monospaced", Font.PLAIN, 20));
        menuPanel.setLayout(new FlowLayout());
        tutorialButton.setBackground(Color.white); //Sets the color of this button to white.
        solitaireButton.setBackground(Color.white); //Sets the color of this button to white.
        quitButton.setBackground(Color.white); //Sets the color of this button to white.
        menuPanel.add(solitaireButton);
        menuPanel.add(tutorialButton);
        menuPanel.add(quitButton);
        menuPanel.add(titlePanel);
        menuPanel.setBackground(Color.pink);
        solitaireButton.addActionListener(new SolitaireButtonListener());
        tutorialButton.addActionListener(new TutorialButtonListener());
        quitButton.addActionListener(new QuitButtonListener());

        //Solitaire Panel to play Solitaire mode
        solitairePanel = new JPanel();
        dealButton = new CustomButton("new game");
        addButton = new CustomButton("add");
        showButton = new CustomButton("hint");
        dealButton.setBackground(Color.white);
        addButton.setBackground(Color.white);
        showButton.setBackground(Color.white);
        solitairePanel.setBackground(Color.cyan);
        solitairePanel.add(dealButton);
        solitairePanel.add(addButton);
        solitairePanel.add(showButton);
        solitairePanel.setLayout(new BoxLayout(solitairePanel,BoxLayout.PAGE_AXIS));
        setCount = new JLabel(SET_COUNT + myGame.getSetCount());
        cardCount = new JLabel(CARDS_LEFT + myGame.getCardsLeft());
        solitairePanel.add(setCount);
        solitairePanel.add(cardCount);
        dealButton.addActionListener(new NewGameButtonListener());
        addButton.addActionListener(new AddButtonListener());
        showButton.addActionListener(new ShowASetButtonListener());
        counter = new JLabel();
        solitairePanel.add(counter);
        count = 0;
        timer = new Timer(1000, new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                count++;
                if (count < 60){
                    counter.setText(0 + ":" + count);
                }else{
                    int min = count/60;
                    counter.setText(min + ":" + (count-min*60));
                }

            }
        });
        timer.start(); // timer to count time upward

        //Tutorial Panel to play tutorial mode
        tutorialPanel = new JPanel();
        tutorialPanel.setLayout(new BoxLayout(tutorialPanel,BoxLayout.PAGE_AXIS));
        readyButton = new CustomButton("next 12");
        showAllButton = new CustomButton("show sets");
        readyButton.setBackground(Color.white);
        showAllButton.setBackground(Color.white);
        tutorialPanel.setBackground(Color.magenta);
        tutorialPanel.add(readyButton);
        tutorialPanel.add(showAllButton);
        readyButton.addActionListener(new Next12Listener());
        showAllButton.addActionListener(new ShowAllListener());

        //Switch panel
        switchPanel = new JPanel();
        switchsolitaireButton = new CustomButton("solitaire");
        switchtutorialButton = new CustomButton("tutorial");
        switchQuitButton = new CustomButton("main menu");
        switchQuitButton.setBackground(Color.white);
        switchPanel.setBackground(Color.yellow);
        switchPanel.add(switchsolitaireButton);
        switchPanel.add(switchtutorialButton);
        switchPanel.add(switchQuitButton);
        switchsolitaireButton.addActionListener(new SolitaireButtonListener());
        switchtutorialButton.addActionListener(new TutorialButtonListener());
        switchQuitButton.addActionListener(new ToMainMenuListener());

        //Organize the panel, first start with menuPanel.
        cp = getContentPane();
        cp.setLayout(new BorderLayout());
        cp.add(menuPanel, BorderLayout.CENTER);

        setSize(APPLET_WIDTH, APPLET_HEIGHT);
    }

    /**
     * Check to see if the game ends.
     * If true:     print out a mesage saying they have won in x time and asks if they want to play again.
     */
    private void checkEndGame(){
        if (myGame.checkEndCondition()) {
            long endTime = System.currentTimeMillis();
            long timeelapsed = endTime - startTime;
            long time1 = timeelapsed/1000;
            String time = timeelapsed/1000 + " seconds.";
            if (time1 >= 60){
                int timeMIN = (int) (long) (time1/60);
                int timeSec = (int) (long)time1 - timeMIN *60;
                time = timeMIN + " minutes and " + timeSec + " seconds.";
            }

            String[] options = {"yes", "no"};
            int userChoice = JOptionPane.showOptionDialog(null, "you finished the game in " + time + " want to play again?", "you are good",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, options, options[0]);
            if (userChoice == 0) {
                changeToSolitaire();
            } else {
                System.exit(0);
            }
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
     * Update the card and set counter for solitaire panel.
     */
    private void updateCounter(){
        cardCount.setText(CARDS_LEFT + myGame.getCardsLeft());
        setCount.setText(SET_COUNT + myGame.getSetCount());
        solitairePanel.revalidate();
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
            if (result == 0){
                myGame.reDeal();
            }
            count = 0;
            updateCounter();
            repaint();
        }
    }

    /**
     * What to do QuitButton is pressed.
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
     * What to do when Next12 button is pressed.
     */
    private class Next12Listener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            myGame.reDeal();
            repaint();
        }
    }

    /**
     * What to do when showAllButton is pressed.
     */
    private class ShowAllListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            String[] options = new String[3];
            options[0] = "Solitaire";
            options[1] = "Try again";
            options[2] = "To main menu";
            int size = myGame.showAllSets().size();
            if (size != 0){
                if (myGame.doneShowing()) {
                    int result = JOptionPane.showOptionDialog((Component) null,
                            "Those were all the sets! Are you ...set... to play solitaire mode?",
                            "Tutorial Complete",
                            JOptionPane.YES_NO_CANCEL_OPTION,
                            JOptionPane.QUESTION_MESSAGE,
                            null,
                            options,
                            options[2]);
                    if (result == 0) {
                        changeToSolitaire();
                    } else if (result == 1) {
                        myGame.reDeal();
                    } else if (result == 2) {
                        cp.removeAll();
                        cp.add(menuPanel);
                        cp.revalidate();
                        repaint();
                    }
                    myGame.setDone();
                }
            }else{
                JOptionPane.showMessageDialog(null, "oops, somehow there is no set. Click on " +
                        "next 12 to try again.");
            }
            repaint();
        }
    }

    /**
     * change the panel to solitaire panel.
     */
    private void changeToSolitaire(){
        startTime = System.currentTimeMillis();
        inTutorial = false;
        switchtutorialButton.setBackground(Color.white);
        switchsolitaireButton.setBackground(Color.pink);
        cp.removeAll();
        cp.add(switchPanel, BorderLayout.NORTH);
        cp.add(solitairePanel, BorderLayout.EAST);
        cp.add(canvasPanel, BorderLayout.CENTER);
        myGame.reDeal();
        updateCounter();
        cp.revalidate();
        count = 0;
        repaint();
    }

    /**
     * What to do when SolitaireButton is pressed.
     */
    private class SolitaireButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            startTime = System.currentTimeMillis();
            inTutorial = false;
            switchtutorialButton.setBackground(Color.white);
            switchsolitaireButton.setBackground(Color.pink);
            myGame.reDeal();
            cp.removeAll();
            cp.add(switchPanel, BorderLayout.NORTH);
            cp.add(solitairePanel, BorderLayout.EAST);
            cp.add(canvasPanel, BorderLayout.CENTER);
            updateCounter();
            cp.revalidate();
            count = 0;
            repaint();
        }
    }

    /**
     * What to do when tutorialButton is pressed.
     */
    private class TutorialButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            inTutorial = true;
            switchsolitaireButton.setBackground(Color.white);
            switchtutorialButton.setBackground(Color.pink);
            cp.removeAll();
            cp.add(switchPanel, BorderLayout.NORTH);
            cp.add(tutorialPanel, BorderLayout.EAST);
            cp.add(canvasPanel, BorderLayout.CENTER);
            myGame.reDeal();
            cp.revalidate();
            repaint();
        }
    }

    /**
     * What to do when toMainMenu Button is pressed.
     */
    private class ToMainMenuListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            String[] options = { "yes", "no" };
            int result = JOptionPane.showOptionDialog(null, "Are you sure?", "Warning",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE,
                    null, options, options[0]);
            if (result == 0) {
                cp.removeAll();
                cp.add(menuPanel);
                cp.revalidate();
            }
            repaint();
        }
    }


    /**
     * What to do when add button is pressed.
     */
    private class AddButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent event){
            int result = myGame.addThreeCards();
            boolean stillSet = myGame.stillSet();
            if (result == 0){
                JOptionPane.showMessageDialog(null, "there are sets. find them.");
            }
            if(result == 2 && stillSet){
                JOptionPane.showMessageDialog(null, "no more cards but there are still sets");
            }else if (!myGame.stillSet()){
                repaint();
                checkEndGame();
            }
            updateCounter();
            repaint();
        }
    }

    /**
     * What to do when showaset button is pressed.
     */
    private class ShowASetButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent event){
            if (!myGame.stillSet()){
                JOptionPane.showMessageDialog(null, "i'm sorry dave i'm afraid i can't open the pod bay, i mean find a set. please add more cards.");
            }else{
                myGame.showASet();
            }
            repaint();
        }
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
            if (inTutorial){
                page.drawString("Welcome to tutorial mode! The goal of this "
                				+ "tutorial is to demonstrate how to make a set. "
                				+ "A set consists", 7, 370);
                page.drawString(" of three cards all of which contain four elements: shape, "
                				+ "number of elements, color, and "
                				+ "shading. ", 7, 390);
                page.drawString("A valid set has either all the same or different "
                				+ "values for EACH of the individual four elements."
        						, 7, 410);
                page.drawString(" If you need help identifying a set, click 'show sets' in the upper right hand corner. "
                				 , 7, 430);
                page.drawString( "Once you are comfortable the formation of a set, test" + "your skills against the clock "
        						+ "in solitaire mode!" , 7, 450);
                				
               
            }
        }

        /**
         * When the mouse is clicked, check to see if setgame can remove card and if the game ends.
         */
        public void mouseClicked(MouseEvent event) {
            if (!inTutorial){
                int result = myGame.getCards(event.getPoint());
                if (result == 1){
                    JOptionPane.showMessageDialog(null, "You got a set!");
                    myGame.removeSelected();
                    myGame.ensureFullField();
                    repaint();
                    checkEndGame();
                }else if (result == 0){
                    JOptionPane.showMessageDialog(null, "Not a set!");
                    myGame.deSelectCards();
                }
                updateCounter();
            }
            repaint();
        }

        //we don't use these.
        public void mousePressed(MouseEvent event) { }
        public void mouseDragged(MouseEvent event)  { }
        public void mouseReleased(MouseEvent event) { }
        public void mouseEntered(MouseEvent event) { }
        public void mouseExited(MouseEvent event) { }
        public void mouseMoved(MouseEvent event) { }
    }
}
