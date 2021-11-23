package Client.View.View.Panels;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class MyFrame extends JFrame {
    private static final MyFrame frame = new MyFrame();
    private static JPanel panel;
    private final CardLayout cardLayout;
    private AlternativePanel alternativePanel;
    private BoardPanel boardPanel;

    private MyFrame() {
        setSize(1600, 1000);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Image customimage = null;
        try {
            customimage = ImageIO.read(new File("resources/pics/game/mouse.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Cursor customCursor = Toolkit.getDefaultToolkit().createCustomCursor(customimage, new Point(0, 0), "customCursor");
        setCursor(customCursor);

        cardLayout = new CardLayout();
        panel = new JPanel();

        panel.setLayout(cardLayout);

        ConnectionPanel connectionPanel = new ConnectionPanel();
        panel.add("connection", connectionPanel);

        add(panel);
        setVisible(true);

        setPreferredSize(new Dimension(1600, 1000));

    }

    public static MyFrame getInstance() {
        return frame;
    }

    public static JPanel getPanel() {
        return panel;
    }

    public void changePanel(String string) {
        cardLayout.show(panel, string);
    }

    public void createLoginPanel() {
        LoginPanel loginPanel = LoginPanel.getInstance();
        panel.add("login", loginPanel);
        changePanel("login");
        revalidate();
        repaint();
    }

    public void addPanels() {
//        new Thread(() -> {
        ShopPanel shop = ShopPanel.getInstance();
        CollectionPanel collection = CollectionPanel.getInstance();
        Col_Change col_change = Col_Change.getInstance();
        StatusPanel status = StatusPanel.getInstance();
        SettingPanel settingPanel = SettingPanel.getInstance();
        MyFrame.getPanel().add(shop, "shop");
        MyFrame.getPanel().add(collection, "collection");
        MyFrame.getPanel().add("setting", settingPanel);
        MyFrame.getPanel().add("status", status);
        MyFrame.getPanel().add(col_change, "col");
//        }).start();
//        new Thread(() -> {
//
//        }).start();
    }

    public AlternativePanel getAlternativePanel() {
        return alternativePanel;
    }

    public void setAlternativePanel(AlternativePanel alternativePanel) {
        this.alternativePanel = alternativePanel;
    }

    public BoardPanel getBoardPanel() {
        return boardPanel;
    }

    public void setBoardPanel(BoardPanel boardPanel) {
        this.boardPanel = boardPanel;
    }
}
