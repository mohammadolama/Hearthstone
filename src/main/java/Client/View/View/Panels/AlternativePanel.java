package Client.View.View.Panels;

import Client.Model.CardModelView;
import Client.Model.InfoPassive;
import Client.View.View.Panels.Listeners.AlternativeListener;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

import static Client.View.View.Panels.Constants.*;

public class AlternativePanel extends JPanel {

    private static final Object object = new Object();

    private JButton card1;
    private JButton card2;
    private JButton card3;
    private JButton ok;
    private CardModelView model1;
    private CardModelView model2;
    private CardModelView model3;
    private InfoPassive infoPassive;
    private BufferedImage winner;
    private boolean enabled;
    private final boolean discoverMode;
    private boolean winningMode;
    private final int mode;
    private final AlternativeListener al = new AlternativeListener(this);

    public AlternativePanel(boolean discoverMode, int mode) {
        this.mode = mode;
        this.discoverMode = discoverMode;
        setSize(new Dimension(1600, 1000));
        setPreferredSize(new Dimension(1600, 1000));
        setLayout(null);

        initilizeButton();
        changeName2();
        changeName1();
    }


    private void initilizeButton() {
        card1 = new JButton("change");
        card1.setBounds(270, 650, 150, 100);
        card1.setFocusable(false);
        card1.addActionListener(al);
        add(card1);

        card2 = new JButton("change");
        card2.setBounds(720, 650, 150, 100);
        card2.setFocusable(false);
        card2.addActionListener(al);
        add(card2);


        card3 = new JButton("change");
        card3.setBounds(1170, 650, 150, 100);
        card3.setFocusable(false);
        card3.addActionListener(al);
        add(card3);


        ok = new JButton("Start");
        ok.setBounds(720, 880, 150, 80);
        ok.setFocusable(false);
        ok.addActionListener(al);
        add(ok);
    }

    private void changeName1() {
        new Thread(() -> {
            if (discoverMode) {
                while (model1 == null || model2 == null || model3 == null) {
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                synchronized (object) {
                    object.notify();
                }
            }
        }).start();
    }

    private void changeName2() {
        new Thread(() -> {
            if (discoverMode) {
                synchronized (object) {
                    try {
                        object.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                card1.setName(model1.getName());
                card2.setName(model2.getName());
                card3.setName(model3.getName());
                card1.setText("Choose");
                card2.setText("Choose");
                card3.setText("Choose");
                ok.setVisible(false);
            }
        }).start();
    }

    @Override
    protected void paintComponent(Graphics ge) {
        Graphics2D g = (Graphics2D) ge;
        g.setColor(Color.cyan);
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g.clearRect(0, 0, 1600, 1000);
        BufferedImage temp1 = gamePics.get("playbackground");
        BufferedImage temp2 = gamePics.get("playboard");
        g.drawImage(temp1, 0, 0, 1600, 1000, null);
        g.drawImage(temp2, 0, 0, 1600, 1000, null);
        if (!winningMode) {
            if (enabled) {
                g.setColor(new Color(122, 122, 122, 180));
                g.fillRect(0, 0, 1600, 1000);
                g.drawRect(200, 150, 300, 400);
                g.drawImage(cardPics.get(model1.getName().toLowerCase()), 200, 150, 300, 400, null);
                g.drawRect(650, 150, 300, 400);
                g.drawImage(cardPics.get(model2.getName().toLowerCase()), 650, 150, 300, 400, null);
                g.drawRect(1100, 150, 300, 400);
                g.drawImage(cardPics.get(model3.getName().toLowerCase()), 1100, 150, 300, 400, null);
            }
        } else {
            g.setColor(new Color(53, 53, 53, 229));
            g.setFont(fantasy.deriveFont(70.0f));
            g.fillRect(0, 0, 1600, 1000);
            g.drawImage(winner, 600, 300, 400, 400, null);
            g.setColor(new Color(255, 255, 0));
            g.drawString("Winner", 700, 800);
        }
    }

    public CardModelView getModel1() {
        return model1;
    }

    public void setModel1(CardModelView model1) {
        this.model1 = model1;
    }

    public CardModelView getModel2() {
        return model2;
    }

    public void setModel2(CardModelView model2) {
        this.model2 = model2;
    }

    public CardModelView getModel3() {
        return model3;
    }

    public void setModel3(CardModelView model3) {
        this.model3 = model3;
    }

    public void setWinningMode(boolean winningMode) {
        this.winningMode = winningMode;
        remove(card1);
        remove(card2);
        remove(card3);
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    @Override
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public void setInfoPassive(InfoPassive infoPassive) {
        this.infoPassive = infoPassive;
    }

    public void setWinner(BufferedImage winner) {
        this.winner = winner;
        ok.setText("Finish");
    }

    public static Object getObject() {
        return object;
    }

    public JButton getCard1() {
        return card1;
    }

    public JButton getCard2() {
        return card2;
    }

    public JButton getCard3() {
        return card3;
    }

    public JButton getOk() {
        return ok;
    }

    public InfoPassive getInfoPassive() {
        return infoPassive;
    }

    public boolean isDiscoverMode() {
        return discoverMode;
    }

    public boolean isWinningMode() {
        return winningMode;
    }

    public int getMode() {
        return mode;
    }
}
