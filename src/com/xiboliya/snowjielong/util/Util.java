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

import java.awt.Font;
import java.util.Enumeration;
import javax.swing.ImageIcon;
import javax.swing.UIManager;
import javax.swing.plaf.FontUIResource;

import com.xiboliya.snowjielong.setting.Setting;
import com.xiboliya.snowjielong.setting.SettingAdapter;

/**
 * 实用工具类，包括可重用的各种属性和方法。设计为final类型，使本类不可被继承
 * 
 * @author 冰原
 * 
 */
public final class Util {
  public static final String SOFTWARE = "成语接龙"; // 软件名称
  public static final String VERSION = "V1.1"; // 软件版本号
  public static final String OS_NAME = System.getProperty("os.name", "Windows"); // 当前操作系统的名称
  public static final String FILE_SEPARATOR = System.getProperty("file.separator", "/"); // 当前操作系统的文件分隔符
  public static final String SETTING_FILE_NAME = "snowjielong.cfg"; // 用来保存软件设置的配置文件名
  public static final String SETTING_USER_SEPARATOR = "\n===\n"; // 配置文件中分隔账号的分隔符
  public static final String CTRL_H = "Ctrl+H"; // 组合键Ctrl+H的字符串
  public static final String CTRL_Y = "Ctrl+Y"; // 组合键Ctrl+Y的字符串
  public static final String CTRL_Z = "Ctrl+Z"; // 组合键Ctrl+Z的字符串
  public static final int INPUT_HEIGHT = 22; // 单行输入框的高度
  public static final int VIEW_HEIGHT = 18; // 标签、单选按钮、复选框的高度
  public static final int BUTTON_HEIGHT = 23; // 按钮的高度
  public static final int ICON_BUTTON_HEIGHT = 30; // 图标按钮的高度
  public static final int BUFFER_LENGTH = 1024; // 缓冲区的大小
  public static final int MSG_LINE_SIZE = 60; // 提示框中每行字符串显示的最大字数
  public static final float SCALE_DEFAULT = 1.0f; // 窗口缩放比例：原始比例
  public static final float SCALE_10 = 1.1f; // 窗口缩放比例：放大10%
  public static final float SCALE_20 = 1.2f; // 窗口缩放比例：放大20%
  public static final float SCALE_30 = 1.3f; // 窗口缩放比例：放大30%
  public static final float SCALE_40 = 1.4f; // 窗口缩放比例：放大40%
  public static final float SCALE_50 = 1.5f; // 窗口缩放比例：放大50%
  public static final ImageIcon ICON_SW = new ImageIcon(ClassLoader.getSystemResource("res/icon.png")); // 主程序图标
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

  public static Setting setting = new Setting(); // 软件参数配置类
  public static SettingAdapter settingAdapter = new SettingAdapter(setting); // 用于解析和保存软件配置文件的工具类

  /**
   * 由于此类为工具类，故将构造方法私有化
   */
  private Util() {
  }

  /**
   * 判断给定的字符串是否为空
   * @param str 待判断的字符串
   * @return 字符串是否为空，true表示为空，false反之
   */
  public static boolean isTextEmpty(String str) {
    return (str == null || str.isEmpty());
  }

  /**
   * 修改整个程序的默认字体
   */
  public static void setDefaultFont() {
    Font font = new Font("宋体", Font.PLAIN, getSize(12));
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
   * 获取按比例缩放的实际大小
   * @param size 原始大小
   * @return 实际大小
   */
  public static int getSize(int size) {
    return (int)(size * setting.global.scale);
  }

}
