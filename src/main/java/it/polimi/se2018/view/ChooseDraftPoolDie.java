package it.polimi.se2018.view;

import it.polimi.se2018.model.DraftPool;
import it.polimi.se2018.utils.enums.Color;

import javax.swing.*;
import java.awt.event.*;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CountDownLatch;

/**
 * @author Alessio
 */
public class ChooseDraftPoolDie extends JDialog implements MouseListener {
    private JPanel contentPane;
    private JPanel board;
    private DraftPool draftPool;
    private final CountDownLatch latch = new CountDownLatch(1);
    private int p = 0;

    /**
     * Costructor
     * @param draftPool
     */
    protected ChooseDraftPoolDie(DraftPool draftPool) {
        setContentPane(contentPane);
        setModal(true);
        this.draftPool = draftPool;

        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);

        for(int i = 0; i < 9; i ++){
            ((JLabel) (((JPanel) board.getComponent(i)).getComponent(0))).addMouseListener(this);
        }
        populateBoard(draftPool);
    }

    /**
     * get position
     * @return
     */
    public int getPosition(){
        this.pack();
        this.setVisible(true);
        try {
            latch.await();
        }catch (InterruptedException e){
            Thread.currentThread().interrupt();
        }

        return p;
    }

    /**
     * manage cancel button
     */
    private void onCancel() {
        latch.countDown();
        dispose();
    }

    /**
     * clean the Board
     */
    private void whiteRefresh(){
        for(int i = 0; i < 9; i ++){
            ((JLabel) (((JPanel) board.getComponent(i)).getComponent(0))).setIcon(new StretchIcon("src/main/resources/utilsGUI/WHITE.png"));
        }
    }

    /**
     * insert the dice in the draftpool
     * @param draftPool
     */
    private void populateBoard(DraftPool draftPool){
        whiteRefresh();

        for(int i = 0; i < draftPool.size(); i ++) {
            try {
                ((JLabel) (((JPanel) board.getComponent(i)).getComponent(0))).setIcon(new StretchIcon("src/main/resources/utilsGUI/" + GUIUtils.colorToString(draftPool.getDie(i).getColor()) + "" + draftPool.getDie(i).getNumber().getInt() + ".png"));
            } catch (Exception e) {}
        }

    }

    /**
     * manage the click of the mouse
     * @param e
     */
    @Override
    public void mouseClicked(MouseEvent e) {
        JLabel source = (JLabel) e.getSource();
        for(int i = 0; i < draftPool.size(); i ++) {
                if(((JLabel) (((JPanel) board.getComponent(i)).getComponent(0))).equals(source)){
                    p = i;
                    latch.countDown();
                    dispose();
                    return;
                }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}
}
