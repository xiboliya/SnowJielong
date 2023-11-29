/**
 * Copyright (C) 2023 冰原
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */

package com.xiboliya.snowjielong.util;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Enumeration;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;
import javax.swing.UIManager;
import javax.swing.plaf.FontUIResource;

/**
 * 实用工具类，包括可重用的各种属性和方法。设计为final类型，使本类不可被继承
 * 
 * @author 冰原
 * 
 */
public final class Util {
  public static final String SOFTWARE = "成语接龙"; // 软件名称
  public static final String VERSION = "V1.0"; // 软件版本号
  public static final String OS_NAME = System.getProperty("os.name", "Windows"); // 当前操作系统的名称
  public static final String FILE_SEPARATOR = System.getProperty("file.separator", "/"); // 当前操作系统的文件分隔符
  public static final String SETTING_XML = "snowjielong.xml"; // 用来保存软件设置的配置文件名
  public static final int DEFAULT_FRAME_WIDTH = 570; // 窗口默认宽度
  public static final int DEFAULT_FRAME_HEIGHT = 650; // 窗口默认高度
  public static final int INPUT_HEIGHT = 22; // 单行输入框的高度
  public static final int VIEW_HEIGHT = 18; // 标签、单选按钮、复选框的高度
  public static final int BUTTON_HEIGHT = 23; // 按钮的高度
  public static final int ICON_BUTTON_HEIGHT = 30; // 图标按钮的高度
  public static final int BUFFER_LENGTH = 1024; // 缓冲区的大小
  public static final int MIN_FONT_SIZE = 8; // 字体最小值
  public static final int MAX_FONT_SIZE = 100; // 字体最大值
  public static final int MSG_LINE_SIZE = 60; // 提示框中每行字符串显示的最大字数
  public static final Font TEXT_FONT = new Font("宋体", Font.PLAIN, 14); // 文本域的默认字体
  public static final ImageIcon ICON_SW = new ImageIcon(ClassLoader.getSystemResource("res/icon.gif")); // 主程序图标
  public static final ImageIcon ICON_HINT = new ImageIcon(ClassLoader.getSystemResource("res/hint.png")); // 提示卡图标
  public static final ImageIcon ICON_PAUSE = new ImageIcon(ClassLoader.getSystemResource("res/pause.png")); // 暂停卡图标
  public static final ImageIcon ICON_DELAY = new ImageIcon(ClassLoader.getSystemResource("res/delay.png")); // 延时卡图标
  public static final ImageIcon ICON_ENERGY = new ImageIcon(ClassLoader.getSystemResource("res/energy.png")); // 体力卡图标
  public static final ImageIcon ICON_STAR_1 = new ImageIcon(ClassLoader.getSystemResource("res/star_1.png")); // 天罡星图标
  public static final ImageIcon ICON_STAR_2 = new ImageIcon(ClassLoader.getSystemResource("res/star_2.png")); // 地煞星图标
  public static final ImageIcon ICON_HINT_SMALL = new ImageIcon(ClassLoader.getSystemResource("res/hint_small.png")); // 提示卡小图标
  public static final ImageIcon ICON_PAUSE_SMALL = new ImageIcon(ClassLoader.getSystemResource("res/pause_small.png")); // 暂停卡小图标
  public static final ImageIcon ICON_DELAY_SMALL = new ImageIcon(ClassLoader.getSystemResource("res/delay_small.png")); // 延时卡小图标
  public static final ImageIcon ICON_ENERGY_SMALL = new ImageIcon(ClassLoader.getSystemResource("res/energy_small.png")); // 体力卡小图标
  // 群星名称，即三十六天罡、七十二地煞
  public static final String[] STAR_NAMES = new String[] {
    "天魁星", "天罡星", "天机星", "天闲星", "天勇星", "天雄星", "天猛星", "天威星", "天英星", "天贵星", "天富星", "天满星",
    "天孤星", "天伤星", "天立星", "天捷星", "天暗星", "天佑星", "天空星", "天速星", "天异星", "天杀星", "天微星", "天究星",
    "天退星", "天寿星", "天剑星", "天平星", "天罪星", "天损星", "天败星", "天牢星", "天慧星", "天暴星", "天哭星", "天巧星",
    "地魁星", "地煞星", "地勇星", "地杰星", "地雄星", "地威星", "地英星", "地奇星", "地猛星", "地文星", "地正星", "地阔星",
    "地阖星", "地强星", "地暗星", "地轴星", "地会星", "地佐星", "地佑星", "地灵星", "地兽星", "地微星", "地慧星", "地暴星",
    "地然星", "地猖星", "地狂星", "地飞星", "地走星", "地巧星", "地明星", "地进星", "地退星", "地满星", "地遂星", "地周星",
    "地隐星", "地异星", "地理星", "地俊星", "地乐星", "地捷星", "地速星", "地镇星", "地稽星", "地魔星", "地妖星", "地幽星",
    "地伏星", "地空星", "地僻星", "地全星", "地孤星", "地角星", "地短星", "地藏星", "地囚星", "地平星", "地损星", "地奴星",
    "地察星", "地恶星", "地丑星", "地数星", "地阴星", "地刑星", "地壮星", "地劣星", "地健星", "地耗星", "地贼星", "地狗星" };

  /**
   * 由于此类为工具类，故将构造方法私有化
   */
  private Util() {
  }

  /**
   * 判断给定的字符串是否为空
   * 
   * @param str
   *          待判断的字符串
   */
  public static boolean isTextEmpty(String str) {
    return (str == null || str.isEmpty());
  }

  /**
   * 将给定字符串重新分行，以适应对话框的显示
   * 
   * @param str
   *          待处理的字符串
   * @return 处理过的字符串
   */
  public static String convertToMsg(String str) {
    String[] arrContents = str.split("\n", -1);
    StringBuilder stbContent = new StringBuilder(); // 用于存放处理后的文本
    for (int n = 0; n < arrContents.length; n++) {
      String content = "";
      if (arrContents[n].length() > MSG_LINE_SIZE) {
        int lines = arrContents[n].length() / MSG_LINE_SIZE;
        int remain = arrContents[n].length() % MSG_LINE_SIZE;
        for (int i = 0; i < lines; i++) {
          content = content + arrContents[n].substring(MSG_LINE_SIZE * i, MSG_LINE_SIZE * (i + 1)) + "\n";
        }
        if (remain > 0) {
          content += arrContents[n].substring(MSG_LINE_SIZE * lines);
        } else {
          content = content.substring(0, content.length() - 1);
        }
      } else {
        content = arrContents[n];
      }
      stbContent.append(content + "\n");
    }
    return stbContent.toString();
  }

  /**
   * 修改整个程序的默认字体
   */
  public static void setDefaultFont() {
    Font font = new Font("宋体", Font.PLAIN, 12);
    FontUIResource fontRes = new FontUIResource(font);
    Enumeration<Object> keys = UIManager.getDefaults().keys();
    while (keys.hasMoreElements()) {
      Object key = keys.nextElement();
      Object value = UIManager.get(key);
      if (value instanceof FontUIResource) {
        UIManager.put(key, fontRes);
      }
    }
  }

  /**
   * 将给定的字符串进行转义替换，即将字符串中的\n替换为换行符，\t替换为tab字符
   * 
   * @param strSource
   *          处理的字符串
   * @return 替换后的字符串
   */
  public static String transfer(String strSource) {
    return strSource.replace("\\n", "\n").replace("\\t", "\t"); // 将字符串中的\n替换为换行符，\t替换为tab字符
  }

  /**
   * 将给定的字符串形式的按键常量转换为按键描述
   * 
   * @param strKeyCode
   *          表示按键常量的字符串
   * @return 表示按键描述的字符串
   */
  public static String transferKeyCode(String strKeyCode) {
    String strKey = "";
    try {
      strKey = KeyEvent.getKeyText(Integer.parseInt(strKeyCode));
    } catch (Exception x) {
      // x.printStackTrace();
      return "";
    }
    return strKey;
  }

  /**
   * 检测文件以及所在的目录是否存在
   * 
   * @param file
   *          被检测的文件
   * @return 被检测文件是否存在，如果存在返回true，反之则为false
   */
  public static boolean checkFile(File file) {
    File fileParent = new File(file.getParent()); // 获取文件的父目录
    if (!fileParent.exists()) {
      fileParent.mkdirs(); // 如果父目录不存在，则创建之
    }
    return file.exists();
  }
}
