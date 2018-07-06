package it.polimi.se2018.view;

import javax.swing.*;
import java.awt.event.*;
import java.util.concurrent.CountDownLatch;

public class addDieValue extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JRadioButton aumentaDiUnoRadioButton;
    private JRadioButton diminuisciDiUnoRadioButton;
    private final CountDownLatch latch = new CountDownLatch(1);

    protected addDieValue() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });

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
        buttonOK.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                latch.countDown();
            }
        });

        ButtonGroup group = new ButtonGroup();
        group.add(aumentaDiUnoRadioButton);
        group.add(diminuisciDiUnoRadioButton);
        aumentaDiUnoRadioButton.setSelected(true);
    }

    public boolean getValue(){
        this.pack();
        this.setVisible(true);
        try{
            latch.await();
        }catch (InterruptedException e){}

        return aumentaDiUnoRadioButton.isSelected();
    }

    private void onOK() {
        latch.countDown();
        dispose();
    }

    private void onCancel() {
        latch.countDown();
        dispose();
    }

    public static void main(String[] args) {
        addDieValue dialog = new addDieValue();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }
}
