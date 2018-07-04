package com.shandong.human.resource.util;

/**
 * 正则表达式常量类
 * <p>
 * Author: syc <522560298@qq.com>
 * Date: 4/2/16 下午1:15
 */
public class RegExpUtil {
    public static final String CHAR = "[a-zA-Z]";
    public static final String NUM_CHAR = "[\\w]";
    public static final String zh_CN = "[\\u4e00-\\u9fa5]";
    public static final String SPECIFIC = "[\\$\\(|\\)|\\*|\\+|\\.|\\[|\\]|\\?|\\\\|\\^|\\{|\\}|\\|]";
    //正则表达式需要替换的字符 '$', '(', ')', '*', '+', '.', '[', ']', '?', '\\', '^', '{', '}', '|'
    public static final String NUM = "[0-9]";
    public static final String CH_EN = "(" + zh_CN + "|" + CHAR + ")";
    public static final String PHONE_ZUOJI = "1\\d{10}$|^(0\\d{2,3}-?|\\(0\\d{2,3}\\))?[1-9]\\d{4,7}(-\\d{1,8})?";
    public static final String FAX = "((\\d{3,4})|\\d{3,4}-)?\\d{7,8}";
    public static final String EMAIL = "\\w[-\\w.+]*@([A-Za-z0-9][-A-Za-z0-9]+\\.)+[A-Za-z]{2,14}";
    public static final String UNSIGNED_INT = "(([1-9][0-9]{0,9})|[0-9])";
}
