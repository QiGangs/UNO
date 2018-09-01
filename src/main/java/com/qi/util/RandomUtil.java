package com.qi.util;

import com.google.common.collect.Sets;

import java.util.Random;
import java.util.Set;

/**
 * @description: 随机生成不同的随机数
 * @author: qigang
 * @create: 2018-05-19 13:00
 **/
public class RandomUtil {
    public static Set<Integer> getRandom(int maxnum, int num) {
        Random ran = new Random();
        Set<Integer> set = Sets.newHashSet();
        while (true) {
            int a = ran.nextInt(maxnum) + 1;
            set.add(a);
            if (set.size() > num) {
                break;
            }
        }
        return set;
    }
}
