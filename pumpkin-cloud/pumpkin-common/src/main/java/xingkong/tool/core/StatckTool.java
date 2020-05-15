package xingkong.tool.core;

/**
 * 
 * @author xingkong
 *
 */
public class StatckTool {
    public static String toString(Exception e) {
        StackTraceElement[] stackElements = e.getStackTrace();
        String text = "";
        if (stackElements != null) {
            for (int i = 0; i < stackElements.length; i++) {
                text += stackElements[i].getClassName() + "/t";
                text += stackElements[i].getFileName() + "/t";
                text += stackElements[i].getLineNumber() + "/t";
                text += stackElements[i].getMethodName() + "\n";
            }
        }
        return text;
    }

    public static String toStringFirstLine(Exception e) {
        StackTraceElement[] stackElements = e.getStackTrace();
        String text = "";
        if (stackElements != null && stackElements.length >= 1) {

            text += stackElements[0].getClassName() + "/t";
            text += stackElements[0].getFileName() + "/t";
            text += stackElements[0].getLineNumber() + "/t";
            text += stackElements[0].getMethodName() + "\n";

        }
        return text;
    }
}
