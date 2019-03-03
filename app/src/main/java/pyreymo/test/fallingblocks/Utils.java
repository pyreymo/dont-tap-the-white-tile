package pyreymo.test.fallingblocks;

public class Utils {
    private static final int MIN_CLICK_DELAY_TIME = 500;//延迟范围
    private static long lastClickTime;

    public static boolean isFastClick() {
        boolean flag = false;
        long curClickTime = System.currentTimeMillis();
        if ((curClickTime - lastClickTime) <= MIN_CLICK_DELAY_TIME) {
            flag = true;
        }
        lastClickTime = curClickTime;
        return flag;
    }
}