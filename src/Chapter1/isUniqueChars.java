package Chapter1;

import java.util.HashMap;

/**
 * 判断一个字符串的所有字符是否全都不同，题目要求使用与不使用额外的数据结构各实现一次。
 */
public class isUniqueChars {


    /**
     * 最初想法，把每个字符放在map中统计
     */
    public static boolean isUniqueCharsUsingMap(String s) {

        HashMap<Character, Boolean> map = new HashMap<>();
        int length = s.length();

        for (int i = 0; i < length; i++) {
            if(!map.containsKey(s.charAt(i)))
                map.put(s.charAt(i), true);
            else
                return false;
        }
        return true;
    }

    /**
     * 书上解法，假设所给字符串为ASCII字符串，则字母表只有128个字符，开一个128的数组即可。
     */
    public static boolean isUniqueCharsUsingArray(String s) {

        if(s.length() > 128) return false;

        boolean[] charSet = new boolean[128];
        int length = s.length();

        for (int i = 0; i < length; i++) {
            int ch = s.charAt(i);
            if(charSet[ch])
                return false;
            charSet[ch] = true;
        }
        return true;
    }

    /**
     * 书上解法，假设所给字符串只有小写字母。
     * 找到字符的ASCII编码，将一个变量checker相应的位置为1，如果出现重复的1则false。
     */
    public static boolean isUniqueCharsUsingBitOperation(String s) {

        if(s.length() > 256) return false;

        int checker = 0, length = s.length();
        for (int i = 0; i < length; i++) {
            int ch = s.charAt(i) - 'a';
            if((checker & (1 << ch)) != 0)
                return false;
            checker = checker | (1 << ch);
        }
        return true;
    }

    /**
     * 还有O（n方）的暴力解法，以及O(nlogn)的排序解法。
     */


    public static void main(String[] args) {
        String[] strArr = {"hello", "world", "apple", "work"};
        for (int i = 0; i < strArr.length; i++) {
            System.out.println(isUniqueCharsUsingMap(strArr[i]));
            System.out.println(isUniqueCharsUsingArray(strArr[i]));
            System.out.println(isUniqueCharsUsingBitOperation(strArr[i]));
            System.out.println();
        }
    }
}
