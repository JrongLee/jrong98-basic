package com.jrong98.basic.common.util;

import com.jrong98.basic.common.entity.BaseEntity;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.GenericTypeResolver;
import org.springframework.util.ClassUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collector;

/**
 * 综合工具类
 * <p>由于业务编程习惯，此工具类偏向与作者本人使用</p>
 * @author jrong98
 * @date 2021/07/21
 */
public class Utils {

    public final static Byte BYTE_0 = 0;
    public final static Byte BYTE_1 = 1;
    public final static Byte BYTE_2 = 2;
    public final static Byte BYTE_3 = 3;

    public final static Integer INT_0 = 0;
    public final static Integer INT_1 = 1;
    public final static Integer INT_2 = 2;
    public final static Integer INT_3 = 3;
    public final static Integer INT_4 = 4;

    public final static Long LONG_0 = 0L;


    /**
     * 获取第一个不为 <code>null</code> 的结果
     * @param a 参数一
     * @param b 参数二
     * @return
     */
    public static <T> T ifNull(T a, T b) {
        return a == null ? b : a;
    }

    /**
     * 获取第一个不为 <code>null</code> 的结果
     * @param a 参数一
     * @param b 参数二
     * @param c 参数三
     * @return
     */
    public static <T> T ifNull(T a, T b, T c) {
        return a == null ? ifNull(b, c) : a;
    }

    /**
     * 非 <code>null</code> 判断
     * @param src   源
     * @param func  获取函数
     * @return 如果都不为 <code>null</code> 则返回 <code>true</code>
     */
    public static <T> boolean nonNull(T src, Function<T, ?> func) {
        if (src == null) {
            return false;
        }
        return func.apply(src) != null;
    }

    /**
     *  <code>null</code> 判断
     * @param src   源
     * @param func  获取函数
     * @return 如果都不为 <code>null</code> 则返回 <code>true</code>
     */
    public static <T> boolean isNull(T src, Function<T, ?> func) {
        return src == null || func.apply(src) == null;
    }

    /**
     * 对比之间
     * @param min   最小值
     * @param val   对比值
     * @param max   最大值
     * @return
     */
    public static <T extends Comparable<T>> T between(T min, T val, T max) {
        if (val.compareTo(min) < 0) {
            return min;
        }
        if (val.compareTo(max) > 0) {
            return max;
        }
        return val;
    }

    /**
     * 范围（包含最小和最大值）
     * @param min	最新值
     * @param v		比较值
     * @param max	最大值
     */
    public static <T extends Comparable<T>> boolean inBetween(T min, T v, T max) {
        if (min == null || v == null || max == null) {
            return false;
        }
        return min.compareTo(v) < 1 && max.compareTo(v) > -1;
    }

    /**
     * 较大值
     * @param a 第一个值
     * @param b 第二个值
     * @return
     */
    public static <T extends Comparable<T>> T max(T a, T b) {
        return a != null && a.compareTo(b) > 0 ? a : b;
    }

    /**
     * 较小值
     * @param a 第一个值
     * @param b 第二个值
     * @return
     */
    public static <T extends Comparable<T>> T min(T a, T b) {
        return a != null && a.compareTo(b) < 0 ? a : b;
    }

    public static <T extends Comparable<T>> T min(T a, T b, T c) {
        return min(a, min(b, c));
    }

    /**
     * 反射对象获取泛型
     *
     * @param clazz      对象
     * @param genericIfc 所属泛型父类
     * @param index      泛型所在位置
     * @return Class
     */
    public static Class<?> getSuperClassGenericType(final Class<?> clazz, final Class<?> genericIfc, final int index) {
        Class<?>[] typeArguments = GenericTypeResolver.resolveTypeArguments(ClassUtils.getUserClass(clazz), genericIfc);
        return null == typeArguments ? null : typeArguments[index];
    }

    /**
     * 漂亮的日志
     *
     * @param title     标题
     * @param args      &lt;arg1&gt;:&lt;arg2&gt; ...
     * @return <p>内容结果</p>
     * <p>========== ${title} ========== </p>
     * <p>==== ${arg1}  : ${arg2} </p>
     * <p>==== ${arg3}  : ${arg4} </p>
     * <p>=========================</p>
     */
    public static String prettyLog(String title, String... args) {
        StringBuilder log = new StringBuilder(1024);

        int width = 64, tab = 4;
        char space = ' ', symbol = '=', le = '\n';;

        int titleUTF8Length = utf8len(title);
        titleUTF8Length += titleUTF8Length & 0x01;
        int sideWidth = (width - titleUTF8Length - 2) >> 1;
        String side = StringUtils.repeat(symbol, sideWidth);
        log.append(le).append(side).append(space).append(title).append(space).append(side);
        int argsSize = args.length;
        if ((argsSize & 0x01) == 1) {
            argsSize--;
        }
        int max = 0;
        for (int i = 0; i < argsSize; i+=2) {
            if (args[i].length() > max) {
                max = args[i].length();
            }
        }
        max += tab - max % tab;
        side = StringUtils.repeat(symbol, tab);
        String fill = "";
        for (int i = 0; i < argsSize; i+=2) {
            fill = StringUtils.repeat(space, max - args[i].length());
            log.append(le)
                .append(side)
                .append(space)
                .append(args[i])
                .append(fill)
                .append(':')
                .append(space)
                .append(args[i + 1]);
        }

        log.append(le).append(StringUtils.repeat(symbol, width));
        return log.toString();
    }

    /**
     * UT8 字符长度
     *
     * @param sequence  字符
     * @return
     */
    public static int utf8len(CharSequence sequence) {
        int count = 0;
        for (int i = 0, len = sequence.length(); i < len; i++) {
            char ch = sequence.charAt(i);
            if (ch <= 0x7F) {
                count++;
            } else if (ch <= 0x7FF) {
                count += 2;
            } else if (Character.isHighSurrogate(ch)) {
                count += 4;
                ++i;
            } else {
                count += 3;
            }
        }
        return count;
    }

    /**
     * 设置数据库默认字段的数据值
     * @param entity entity
     */
    public static <T extends BaseEntity> void resolveEntity(T entity) {

    }

    public static <T, R> Collector<T, List<R>, List<R>> toList(Function<T, R> function) {
        return Collector.of(ArrayList::new, (a, b) -> a.add(function.apply(b)), (a, b) -> {
            a.addAll(b);
            return a;
        });
    }

    public static <T, R> Collector<T, Set<R>, Set<R>> toSet(Function<T, R> function) {
        return Collector.of(HashSet::new, (a, b) -> a.add(function.apply(b)), (a, b) -> {
            a.addAll(b);
            return a;
        });
    }

    public static final String LIMIT_1_SQL = "LIMIT 1";
    public static String limitSQL(Collection<?> v) {
        return "LIMIT " + v.size();
    }


}