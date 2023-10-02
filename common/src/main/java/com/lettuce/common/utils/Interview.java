package com.lettuce.common.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * Code is far away from bug with the animal protected
 * 　┏┓　　  ┏┓
 * ┏┻┻━━━┻┻┓
 * ┃　　　　　　　┃
 * ┃　　　━　　　┃
 * ┃　┳┛　┗┳　┃
 * ┃　　　　　　　┃
 * ┃　　　┻　　　┃
 * ┃　　　　　　　┃
 * ┗━┓　　　┏━┛
 * 　　┃　　　┃神兽保佑
 * 　　┃　　　┃代码无BUG！
 * 　　┃　　　┗━━━┓
 * 　　┃　　　　　　　┣┓
 * 　　┃　　　　　　　┣┛
 * 　　┗┳┓┏━┳┓┏┛
 * 　　　┃┫┫　┃┫┫
 * 　　　┗┻┛　┗┻┛
 *
 * @author 37593
 * @date 2022年03月07日
 */
public class Interview {
    public static void main(String[] args) {
        List<Integer> array = new ArrayList<>();
        array.add(33);
        array.add(33);
        array.add(35);
        array.add(36);
        array.add(37);
        array.add(38);
        array.add(39);
        array.add(40);
        array.add(41);
        for (int i = 0; i < array.size(); i++) {
            for (int j = i; j < array.size(); j++) {
                if (array.get(i) > array.get(j)) {
                    int bigger = array.get(i);
                    array.set(i, array.get(j));
                    array.set(j, bigger);
                }
            }
        }
        for (int i = 0; i < array.size(); i++) {
            System.out.print(array.get(i) + ",");
        }

        if (array.size() > 1) {
            int min = 0;
            int max = array.size() - 1;
            for (int i = 0; min <= max; i++) {
                int middle = (min + max) / 2;
                if (33 == array.get(middle)) {
                    System.out.println(middle + 1);
                    break;
                }
                if (array.get(middle) > 33) {
                    max = middle - 1;
                }
                if (array.get(middle) < 33) {
                    min = middle + 1;
                }
            }
        }
    }
}
