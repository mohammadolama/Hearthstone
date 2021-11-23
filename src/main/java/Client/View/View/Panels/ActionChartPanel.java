package Client.View.View.Panels;

import Client.Controller.RequestHandler;
import Client.Controller.Requests.ActionChartRequest;
import Client.Controller.Responses;
import Client.Model.ActionModel;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;

import static Client.View.View.Panels.Constants.*;

public class ActionChartPanel extends JPanel {

    private BufferedImage background;

    private ArrayList<ActionModel> friendlyModel;
    private ArrayList<ActionModel> enemyModel;
    private HashMap<String, Integer> factor;


    ActionChartPanel() {
        friendlyModel = new ArrayList<>();
        enemyModel = new ArrayList<>();
        factor = new HashMap<>();
        factor.put("learnjavadonic", 20);
        factor.put("strengthinnumbers", 16);
        factor.put("strengthinnumbersdr", 16);

        background = Constants.gamePics.get("playbackground");
        setSize(new Dimension(500, 400));
        setPreferredSize(new Dimension(500, 400));
    }

    @Override
    protected void paintComponent(Graphics gr) {
        Graphics2D g = (Graphics2D) gr;
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g.setFont(fantasy.deriveFont(30.0f));
        g.setColor(Color.yellow);

        g.clearRect(0, 0, 500, 400);
        g.drawImage(background, 0, 0, 500, 400, null);
        g.drawString("Enemy", 70, 60);
        g.drawString("You", 360, 60);
        g.drawLine(250, 0, 250, 700);
        g.setFont(f2);

        RequestHandler.getInstance().sendRequest(new ActionChartRequest());
        friendlyModel = Responses.getInstance().getFriendlyModel();
        enemyModel = Responses.getInstance().getEnemyModel();

        for (int i = 0; i < friendlyModel.size(); i++) {
            BufferedImage image = cardPics.get(friendlyModel.get(i).getName().toLowerCase());
            g.drawImage(image, 440, 100 + 70 * i, 40, 60, null);
            g.setColor(Color.yellow);
            g.drawRect(260, 100 + 70 * i, 160, 60);
            g.setPaint(new GradientPaint(5, 5, Color.cyan, 10, 10, Color.yellow, true));
            int fac = factor.get(friendlyModel.get(i).getName().toLowerCase());
            g.fillRect(260, 100 + 70 * i, fac * friendlyModel.get(i).getManaSpendOnSth(), 60);
            g.setColor(Color.RED);
            g.drawString(friendlyModel.get(i).getManaSpendOnSth() + "/" + friendlyModel.get(i).getMaxSpend(), 320, 140 + 70 * i);
        }

        for (int i = 0; i < enemyModel.size(); i++) {
            BufferedImage image = cardPics.get(enemyModel.get(i).getName().toLowerCase());
            g.drawImage(image, 190, 100 + 70 * i, 40, 60, null);
            g.setColor(Color.yellow);
            g.drawRect(10, 100 + 70 * i, 160, 60);
            g.setPaint(new GradientPaint(5, 5, Color.cyan, 10, 10, Color.yellow, true));
            int fac = factor.get(enemyModel.get(i).getName().toLowerCase());
            g.fillRect(10, 100 + 70 * i, fac * enemyModel.get(i).getManaSpendOnSth(), 60);
            g.setColor(Color.RED);
            g.drawString(enemyModel.get(i).getManaSpendOnSth() + "/" + enemyModel.get(i).getMaxSpend(), 70, 140 + 70 * i);
        }

    }

    public ArrayList<ActionModel> getFriendlyModel() {
        return friendlyModel;
    }

    public ArrayList<ActionModel> getEnemyModel() {
        return enemyModel;
    }

    public void setFriendlyModel(ArrayList<ActionModel> friendlyModel) {
        this.friendlyModel = friendlyModel;
    }

    public void setEnemyModel(ArrayList<ActionModel> enemyModel) {
        this.enemyModel = enemyModel;
    }
}
