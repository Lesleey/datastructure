package com.study.recursionandrecall;

/**
 * @author Lesleey
 * @date 2020/6/2-15:50
 * @function 汉诺塔问题
 */
public class Hanoi {

    /**
     * @param a b c  石柱子编号
     * @param n 盘子数量
     * @func 将n个盘子，从a 移动到 c 上
     * */
    public void hanoiFunc(char a, char b, char c, int n){
        //1. 合法性检查
        if(n < 0) throw new IllegalArgumentException("无效的参数");
        //2. 结束递归的条件
        if(n == 0) return;
        //3. 缩小规模
        hanoiFunc(a, c, b, n - 1); //将 编号为n之上的n -1 个盘子从 a 移动到 b 上
        System.out.println(String.format("从柱子 %s 移动编号为%s的盘子到 柱子%s上", a, n, c)); //将 编号为 n 的盘子移动到合适的柱子中
        hanoiFunc(b, a, c, n -1); // 在 之前移动到 b 中的n -1 个盘子 移动到c中
    }

    public void hanoiFunc(int n, char a, char b, char c ){
        if(n > 0){
            hanoiFunc(n-1, a, c, b);
            System.out.println(n + "编号的盘子:" +  a + "-->" + b);
            hanoiFunc(n-1, b, a, c);
        }
    }

    public static void main(String[] args) {
        new Hanoi().hanoiFunc('A', 'B', 'C', 3);
    }
}
