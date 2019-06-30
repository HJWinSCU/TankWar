import java.awt.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StrongBullets {
    public static  int speedX = 10;
    public static  int speedY = 10; // �ӵ���ȫ�־�̬�ٶ�

    public static final int width = 10;
    public static final int length = 10;

    private int x, y;
    Direction diretion;

    private boolean good;
    private boolean live = true;

    private TankClient tc;

    private static Toolkit tk = Toolkit.getDefaultToolkit();
    private static Image[] sbulletImages = null;
    private static Map<String, Image> imgs = new HashMap<String, Image>(); // ����Map��ֵ�ԣ��ǲ�ͬ�����Ӧ��ͬ�ĵ�ͷ

    static {
        sbulletImages = new Image[] { // ��ͬ������ӵ��������ӵ��ӵ������ӿ��Ի�һ��
                tk.getImage(StrongBullets.class.getClassLoader().getResource(
                        "images/bulletL.gif")),

                tk.getImage(StrongBullets.class.getClassLoader().getResource(
                        "images/bulletU.gif")),

                tk.getImage(StrongBullets.class.getClassLoader().getResource(
                        "images/bulletR.gif")),

                tk.getImage(StrongBullets.class.getClassLoader().getResource(
                        "images/bulletD.gif")),

        };

        imgs.put("L", sbulletImages[0]); // ����Map����

        imgs.put("U", sbulletImages[1]);

        imgs.put("R", sbulletImages[2]);

        imgs.put("D", sbulletImages[3]);

    }

    public StrongBullets(int x, int y, Direction dir) { // ���캯��1������λ�úͷ���
        this.x = x;
        this.y = y;
        this.diretion = dir;
    }

    // ���캯��2������������������
    public StrongBullets(int x, int y, boolean good, Direction dir, TankClient tc) {
        this(x, y, dir);
        this.good = good;
        this.tc = tc;
    }

    private void move() {

        switch (diretion) {
            case LM:
                x -= speedX; // �ӵ������������
                break;

            case UM:
                y -= speedY;
                break;

            case RM:
                x += speedX; // �ֶβ�������
                break;

            case DM:
                y += speedY;
                break;

            case STOP:
                break;
        }

        if (x < 0 || y < 0 || x > TankClient.Fram_width
                || y > TankClient.Fram_length) {
            live = false;
        }
    }

    public void draw(Graphics g) {
        if (!live) {
            tc.strongBullets.remove(this);
            return;
        }

        switch (diretion) { // ѡ��ͬ������ӵ�
            case LM:
                g.drawImage(imgs.get("L"), x, y, null);
                break;

            case UM:
                g.drawImage(imgs.get("U"), x, y, null);
                break;

            case RM:
                g.drawImage(imgs.get("R"), x, y, null);
                break;

            case DM:
                g.drawImage(imgs.get("D"), x, y, null);
                break;

        }

        move(); // �����ӵ�move()����
    }

    public boolean isLive() { // �ж��Ƿ񻹻���
        return live;
    }

    public Rectangle getRect() {
        return new Rectangle(x, y, width, length);
    }

    public boolean hitTanks(List<Tank> tanks) {// ���ӵ���̹��ʱ
        for (int i = 0; i < tanks.size(); i++) {
            if (hitTank(tanks.get(i))) { // ��ÿһ��̹�ˣ�����hitTank
                return true;
            }
        }
        return false;
    }

    public boolean hitTank(Tank t) { // ���ӵ���̹����

        if (this.live && this.getRect().intersects(t.getRect()) && t.isLive()
                && this.good != t.isGood()) {

            BombTank e = new BombTank(t.getX(), t.getY(), tc);
            tc.bombTanks.add(e);
            if (t.isGood()) {
                t.setLife(t.getLife() - 50); // ��һ���ӵ���������50������4ǹ����,������ֵ200
                if (t.getLife() <= 0)
                    t.setLive(false); // ������Ϊ0ʱ����������Ϊ����״̬
            } else {
                t.setLive(false);
            }

            this.live = false;

            return true; // ����ɹ�������true
        }
        return false; // ���򷵻�false
    }

    public boolean hitWall(CommonWall w) { // �ӵ���CommonWall��
        if (this.live && this.getRect().intersects(w.getRect())) {
            this.live = false;
            this.tc.otherWall.remove(w); // �ӵ���CommonWallǽ��ʱ���Ƴ��˻���ǽ
            this.tc.homeWall.remove(w);
            return true;
        }
        return false;
    }

    public boolean hitWall(MetalWall w) { // �ӵ��򵽽���ǽ��
        if (this.live && this.getRect().intersects(w.getRect())) {
            this.live = false;
            this.tc.metalWall.remove(w); // �ӵ���MetalWallǽ��ʱ���Ƴ��˻���ǽ
            return true;
        }
        return false;
    }

    public boolean hitHome() { // ���ӵ��򵽼�ʱ
        if (this.live && this.getRect().intersects(tc.home.getRect())) {
            this.live = false;
            this.tc.home.setLive(false); // ���ҽ���һǹʱ������
            return true;
        }
        return false;
    }

}