package com.roc.seatallocation4xhh;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.List;

/**
 * 主类/入口，Git版
 */
public class Main {

    private static JTextField textFieldStuNum = new JTextField(3);

    public static void main(String[] args) {
//        System.out.println("随机的九个数是:");
//        int a[] = new int[60]; //总的有60个座位!
//        Random random = new Random();
//        for (int i=0;i<a.length;i++)
//        {
//            int z = random.nextInt(60); //随机抽取60个座位
//            a[i] = Math.round(z);
//            if (i >= 1)
//            {
//                int j = i;
//                while (j >= 0)
//                {
//                    if (a[i] == a[j])
//                    {
//                        int k = random.nextInt(60);//随机抽取60个座位
//                        a[i] = Math.round(k);
//                    }
//                    j--;
//                }
//            }
//            System.out.print(a[i] + 1 + " ");
//        }

        // 主窗体
        JFrame frameMain = new JFrame("小红红座位随机分配应用");
        // 主窗体设置大小
        frameMain.setSize(1800, 888);
        // 主窗体设置位置
        frameMain.setLocation(100, 100);
        // 主窗体中的组件设置为绝对定位

        //文本显示域和文本框
//        JTextField textFieldStuNum = new JTextField(3);
//        textFieldStuNum.setVisible(true);
//        frameMain.add(textFieldStuNum);   //textFieldStuNum不能直接加到frame上

        JPanel panel = new JPanel();
        // 添加面板
        frameMain.add(panel);

        placeComponents(panel);

//        frameMain.setLayout(null);
//        // 按钮组件
//        JButton btnStart = new JButton("开始分配");
//        // 同时设置组件的大小和位置
//        btnStart.setBounds(20, 200, 88, 50);
//        // 把按钮加入到主窗体中
//        frameMain.add(btnStart);

        // 关闭窗体的时候，退出程序
        frameMain.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // 让窗体变得可见
        frameMain.setVisible(true);

        //获取JTextField的值
        String stuNum = textFieldStuNum.getText();
    }

    private static void placeComponents(JPanel panel) {

        /* 布局部分我们这边不多做介绍
         * 这边设置布局为 null
         */
        panel.setLayout(null);

        // 创建 JLabel
        JLabel userLabel = new JLabel("学生数目（学号数目）:");
        /* 这个方法定义了组件的位置。
         * setBounds(x, y, width, height)
         * x 和 y 指定左上角的新位置，由 width 和 height 指定新的大小。
         */
        userLabel.setBounds(10,20,150,25);
        panel.add(userLabel);

        /*
         * 创建文本域用于用户输入
         */
        textFieldStuNum.setBounds(200,20,50,25);
        panel.add(textFieldStuNum);

        // 输入密码的文本域
        JLabel labelSeatNum = new JLabel("座位数目:");
        labelSeatNum.setBounds(10,50,180,25);
        panel.add(labelSeatNum);

        /*
         *这个类似用于输入的文本域
         * 但是输入的信息会以点号代替，用于包含密码的安全性
         */
//        JPasswordField passwordText = new JPasswordField(3);
        JTextField textFieldSeatNum = new JTextField(3);
        textFieldSeatNum.setBounds(200,50,50,25);
        panel.add(textFieldSeatNum);

        //分配结果
        JLabel labelAlloRes = new JLabel("分配结果:");
        labelAlloRes.setBounds(10,150,100,600);
        labelAlloRes.setOpaque(true);//设置组件JLabel不透明，只有设置为不透明，设置背景色才有效
        labelAlloRes.setBackground(Color.cyan);
        panel.add(labelAlloRes);
        JTextArea textAreaRes = new JTextArea("");
        textAreaRes.setBounds(120,150,1600,600);
        textAreaRes.setOpaque(true);//设置组件JLabel不透明，只有设置为不透明，设置背景色才有效
        textAreaRes.setBackground(Color.ORANGE);
//        textAreaRes.setEnabled(false);
        textAreaRes.setLineWrap(true);        //激活自动换行功能
        textAreaRes.setWrapStyleWord(true);            // 激活断行不断字功能
        panel.add(textAreaRes);

        JButton btnStart = new JButton("开始分配");
        btnStart.setBounds(10, 80, 150, 25);
        btnStart.setBackground(Color.PINK);
        btnStart.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int stuNumInt;
                int seatNumInt;
                StringBuffer sbRes = new StringBuffer();
                Set<Integer> seatNoSet = new HashSet<Integer>();
                List<Integer> seatNoList = new ArrayList<Integer>();
                String stuNum = textFieldStuNum.getText();
                String seatNum = textFieldSeatNum.getText();
                if (stuNum == null || stuNum.trim().length() == 0) {
                    JOptionPane.showMessageDialog(panel, "抱歉，请输入学生数目", "温馨提示",JOptionPane.WARNING_MESSAGE);
                    textFieldStuNum.requestFocus();
                    textFieldStuNum.selectAll();
                    return;
                } else {
                    try {
                        stuNumInt = Integer.parseInt(stuNum);
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(panel, "抱歉，学生数目请输入整数", "温馨提示",JOptionPane.WARNING_MESSAGE);
                        textFieldStuNum.requestFocus();
                        textFieldStuNum.selectAll();
                        return;
                    }
                }
                if (seatNum == null || seatNum.trim().length() == 0) {
                    JOptionPane.showMessageDialog(panel, "抱歉，请输入座位数目", "温馨提示",JOptionPane.WARNING_MESSAGE);
                    textFieldSeatNum.requestFocus();
                    textFieldSeatNum.selectAll();
                    return;
                } else {
                    try {
                        seatNumInt = Integer.parseInt(seatNum);
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(panel, "抱歉，座位数目请输入整数", "温馨提示",JOptionPane.WARNING_MESSAGE);
                        textFieldSeatNum.requestFocus();
                        textFieldSeatNum.selectAll();
                        return;
                    }
                }
                if (seatNumInt < stuNumInt) {
                    JOptionPane.showMessageDialog(panel, "抱歉，座位数目要大于学生数目", "温馨提示",JOptionPane.WARNING_MESSAGE);
                    textFieldSeatNum.requestFocus();
                    textFieldSeatNum.selectAll();
                    return;
                }
                while (seatNoSet.size() < stuNumInt) {  //set可去重
                    int randomInt = (int) (Math.random() * stuNumInt) + 1;
                    if (!seatNoSet.contains(randomInt)) {
                        seatNoList.add(randomInt);
                    }
                    seatNoSet.add(randomInt);
                }
//                Iterator iterator = seatNoSet.iterator();
//                while(iterator.hasNext()){
//                    seatNoList.add((Integer) iterator.next());
//                }
                for (int i = 1; i <= stuNumInt; i++) {
                    sbRes.append("学生" + i + "<--->" + "座位" + seatNoList.get(i - 1) + "，");
                }
                textAreaRes.setText("==================================================================================================================================" +
                        "分配成功，结果如下" +
                        "==================================================================================================================================\r\n" + sbRes);
            }
        });
        panel.add(btnStart);

    }

}
