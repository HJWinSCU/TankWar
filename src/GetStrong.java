import java.awt.*;
import java.util.Random;

public class GetStrong {
    public static final int width=36;
    public static final int length=36;

    private int x,y;
    TankClient tc;
    private static Random r=new Random();

    int step=1;

    private boolean live =false;
    private static Toolkit tk = Toolkit.getDefaultToolkit();
    private static Image[] bloodImags = null;
    static {
        bloodImags = new Image[] { tk.getImage(CommonWall.class
                .getResource("Images/mogu3.png")), };//找张图做超级子弹图标
    }

    private int[][] poition = { { 100, 196 }, { 50, 58 }, { 280, 340 },
            { 399, 199 }, { 666, 456 }, { 223, 321 }, { 528, 413 } };

    public void draw(Graphics g) {
        if (r.nextInt(100) > 98) {
            this.live = true;
            move();
        }
        if (!live)
            return;
        g.drawImage(bloodImags[0], x, y, null);

    }

    private void move() {
        step++;
        if (step == poition.length) {
            step = 0;
        }
        x = poition[step][0];
        y = poition[step][1];

    }

    public Rectangle getRect() { //返回长方形实例
        return new Rectangle(x, y, width, length);
    }

    public boolean isLive() {//判断是否还活着
        return live;
    }

    public void setLive(boolean live) {  //设置生命
        this.live = live;
    }

}
