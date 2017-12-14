package Chapter1;

/**
 * 将字符串/字符数组中的空格换成"%20"。
 * 假定该字符串尾部有足够的空间存放新增字符，并且知道字符串的真实长度。
 */
public class ReplaceSpace {

    public static void replaceSpace(char[] chars, int length) {

        int newLength = 0, spaceNum = 0;
        for (int i = 0; i < length; i++) {
            if(chars[i] == ' ')
                spaceNum++;
        }

        newLength = length + 2 * spaceNum;
        chars[newLength] = '\0';
        for (int i = length - 1, newIndex = newLength - 1; i >= 0; i--) {
            if(chars[i] == ' ') {
                chars[newIndex] = '0';
                chars[newIndex - 1] = '2';
                chars[newIndex - 2] = '%';
                newIndex -= 3;
            } else {
                chars[newIndex] = chars[i];
                newIndex--;
            }
        }
    }

    public static void main(String[] args) {
        String str = "abc d e f";
        char[] arr = new char[str.length() + 3 * 2 + 1];
        for (int i = 0; i < str.length(); i++) {
            arr[i] = str.charAt(i);
        }
        replaceSpace(arr, str.length());
        System.out.println(new String(arr));
    }
}
