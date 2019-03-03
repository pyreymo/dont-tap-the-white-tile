package pyreymo.test.fallingblocks;

//存储一组矩形的位置
public class RectPosition {
    private int LeftPos;
    private int RightPos;
    private int TopPos;
    private int BottomPos;

    public void setBottomPos(int bottomPos) {
        this.BottomPos = bottomPos;
    }

    public void setLeftPos(int leftPos) {
        this.LeftPos = leftPos;
    }

    public void setRightPos(int rightPos) {
        this.RightPos = rightPos;
    }

    public void setTopPos(int topPos) {
        this.TopPos = topPos;
    }


    public int getBottomPos() {
        return BottomPos;
    }

    public int getLeftPos() {
        return LeftPos;
    }

    public int getRightPos() {
        return RightPos;
    }

    public int getTopPos() {
        return TopPos;
    }
}
