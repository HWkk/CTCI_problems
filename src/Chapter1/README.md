# 8.1 && 9.1 数组与字符串

### ASCII、Unicode和UTF-8

**ASCII码**一共规定了128个字符编码，其中有常见的英文大小写字母、数字以及32个不能打印出来的控制符号。**这128个符号只占用了一个字节的后7位，这个字节的第一位均用0表示。**

除了英文字母外，一些国家会使用这个字节的剩余128个状态（即字节的第一位为1）来表示一些字符，如法语的带音调的字母，希伯来语的λ，这128个字符就是**非ASCII编码**。但各个国家的字符都不一样，所以这128个字符并不统一。

**Unicode编码**是一种全球通用的编码方式，其中每个二进制都会唯一地对应一个字符，其中包括英语、德语、数字、中文等等基本所有字符。这么多字符（仅中文就十万以上），肯定不是只用一个字节就能表示的了了，因此有的字符可能1个字节（如英语字母A），但有的字符可能需要2、3甚至4个字节才能存储起来。**Unicode只是一个符号集，它只规定了符号的二进制代码，却没有规定这个二进制代码应该如何存储。**

**UTF-8 是 Unicode 的实现方式之一。**UTF-8 最大的一个特点，就是它是一种变长的编码方式。它可以使用1~4个字节表示一个符号，根据不同的符号而变化字节长度。

UTF-8 的编码规则很简单，只有二条：

1）对于单字节的符号，字节的第一位设为`0`，后面7位为这个符号的 Unicode 码。因此对于英语字母，UTF-8 编码和 ASCII 码是相同的。

2）对于`n`字节的符号（`n > 1`），第一个字节的前`n`位都设为`1`，第`n + 1`位设为`0`，后面字节的前两位一律设为`10`。剩下的没有提及的二进制位，全部为这个符号的 Unicode 码。

下表总结了编码规则，字母`x`表示可用编码的位。

```
Unicode符号范围     |        UTF-8编码方式
(十六进制)        |              （二进制）
----------------------+---------------------------------------------
0000 0000-0000 007F | 0xxxxxxx
0000 0080-0000 07FF | 110xxxxx 10xxxxxx
0000 0800-0000 FFFF | 1110xxxx 10xxxxxx 10xxxxxx
0001 0000-0010 FFFF | 11110xxx 10xxxxxx 10xxxxxx 10xxxxxx
```

跟据上表，解读 UTF-8 编码非常简单。**如果一个字节的第一位是`0`，则这个字节单独就是一个字符；如果第一位是`1`，则连续有多少个`1`，就表示当前字符占用多少个字节。**

下面，还是以汉字`严`为例，演示如何实现 UTF-8 编码。

`严`的 Unicode 是`4E25`（`100111000100101`），根据上表，可以发现`4E25`处在第三行的范围内（`0000 0800 - 0000 FFFF`），因此`严`的 UTF-8 编码需要三个字节，即格式是`1110xxxx 10xxxxxx 10xxxxxx`。然后，从`严`的最后一个二进制位开始，依次从后向前填入格式中的`x`，多出的位补`0`。这样就得到了，`严`的 UTF-8 编码是`11100100 10111000 10100101`，转换成十六进制就是`E4B8A5`。

[参考博客](http://www.ruanyifeng.com/blog/2007/10/ascii_unicode_and_utf-8.html)

