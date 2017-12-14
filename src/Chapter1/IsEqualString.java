package Chapter1;

import java.util.HashMap;

/**
 * 判断其中一个字符串的字符重新排列后，能否变成另一个字符串
 * 假设区分大小写，以及区分空格
 */
public class IsEqualString {

    /**
     * 还是使用map，计算字符出现的频率
     * 书上使用的是字符数组，与第一题（IsUniqueChars）类似，这里不再赘述
     */
    public static boolean isEqualString(String s, String t) {
        int length = s.length();
        if(length != t.length()) return false;

        HashMap<Character, Integer> frequencyMap = new HashMap<>();
        for (int i = 0; i < length; i++) {
            frequencyMap.put(s.charAt(i), frequencyMap.getOrDefault(s.charAt(i), 0) + 1);
        }
        for (int i = 0; i < length; i++) {
            if(!frequencyMap.containsKey(t.charAt(i)))
                return false;
            frequencyMap.put(t.charAt(i), frequencyMap.get(t.charAt(i)) - 1);
            if(frequencyMap.get(t.charAt(i)) < 0)
                return false;
        }
        return true;
    }

    public static void main(String[] args) {
        String t = "apple";
        String s = "pplea";
        System.out.println(isEqualString(s, t));
    }
}
