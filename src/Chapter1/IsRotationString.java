package Chapter1;

/**
 * 假设给定一个方法isSubstring，可检查一个字符串是否是另一个字符串的字串。
 * 题目要求写一个方法，判断一个字符串是否是由另一个字符串旋转而成。
 * 比如waterbottle 和 erbottlewat
 */
public class IsRotationString {

    public static boolean isSubstring(String big, String small) {
        if (big.indexOf(small) >= 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 按s旋转的点将s划分为两部分x和y，即s = xy, 若t = yx，则可以旋转得到。
     * 另s1 = ss = xyxy, t若等于yx，t肯定是s1的字串，反之亦成立。
     * 即只需要判断t是否是s1的字串即可。
     */
    public static boolean isRotationString(String s, String t) {

        if(s.length() != t.length())
            return false;

        if(isSubstring(s + s, t))
            return true;
        return false;
    }

    public static void main(String[] args) {
        String[][] pairs = {{"apple", "pleap"}, {"waterbottle", "erbottlewat"}, {"camera", "macera"}};
        for (String[] pair : pairs) {
            String word1 = pair[0];
            String word2 = pair[1];
            boolean is_rotation = isRotationString(word1, word2);
            System.out.println(word1 + ", " + word2 + ": " + is_rotation);
        }
    }
}
