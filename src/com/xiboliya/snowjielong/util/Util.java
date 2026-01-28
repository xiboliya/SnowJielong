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
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import javax.swing.ImageIcon;
import javax.swing.UIManager;
import javax.swing.plaf.FontUIResource;

import com.xiboliya.snowjielong.common.Reward;
import com.xiboliya.snowjielong.common.User;
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
  public static final int MSG_LINE_SIZE = 30; // 提示框中每行字符串显示的最大字数
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
  // 难度等级名称数组
  public static final String[] TOPIC_LEVEL_NAME = new String[] { "初级", "中级", "高级", "特级", "顶级" };
  // 头衔等级名称数组
  public static final String[] RANK_LEVEL_NAME = new String[] { "童生", "秀才", "附生", "增生", "廪生", "举人", "解元", "贡士", "会元", "进士", "探花", "榜眼", "状元" };
  // 头衔等级分数数组
  public static final int[] RANK_LEVEL_SCORE = new int[] { 0, 60, 180, 360, 600, 900, 1260, 1680, 2160, 2700, 3300, 3960, 4680 };
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

  // 当前难度等级的所有成语的解释
  public static HashMap<String, String> currentTopicDefinitionMap = new HashMap<String, String>();
  // 当前难度等级的所有关卡数量
  public static int currentTopicBarrierCount = 0;
  // 当前头衔等级
  public static int currentRankLevel = 0;
  // 当前关卡的成语列表
  public static ArrayList<String> currentIdiomList = new ArrayList<String>();
  // 当前关卡获得的分数
  public static int currentScore = 0;
  // 当前关卡获得的奖励
  public static Reward currentReward = null;

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

  /**
   * 获取闯关速度
   * @param user 账号
   * @return 闯关速度
   */
  public static float getSpeed(User user) {
    try {
      BigDecimal usedTime = new BigDecimal(user.idiomCache.getUsedTime());
      BigDecimal passedBarrierCount = new BigDecimal(user.idiomCache.getPassedBarrierCount());
      BigDecimal number = usedTime.divide(passedBarrierCount, 1, RoundingMode.HALF_UP);
      return number.floatValue();
    } catch (Exception x) {
      // x.printStackTrace();
    }
    return -1;
  }

  /**
   * 获取正确率
   * @param user 账号
   * @return 正确率
   */
  public static float getAccuracy(User user) {
    try {
      BigDecimal totalRightCount = new BigDecimal(user.idiomCache.getTotalRightCount() * 100);
      BigDecimal totalSubmitCount = new BigDecimal(user.idiomCache.getTotalSubmitCount());
      BigDecimal number = totalRightCount.divide(totalSubmitCount, 1, RoundingMode.HALF_UP);
      return number.floatValue();
    } catch (Exception x) {
      // x.printStackTrace();
    }
    return -1;
  }

  /**
   * 是否通过当前难度等级的所有关卡
   * @return 是否通过当前难度等级的所有关卡
   */
  public static boolean isPassedAllBarrier() {
    return (setting.user.idiomCache.getCurrentBarrier() + 1) >= currentTopicBarrierCount;
  }

  /**
   * 是否存在下一难度等级的关卡
   * @return 是否存在下一难度等级的关卡
   */
  public static boolean hasNextTopicLevel() {
    URL url = ClassLoader.getSystemResource("res/topic/level" + (setting.user.idiomCache.getCurrentTopicLevel() + 1) + ".txt");
    return url != null;
  }

  /**
   * 刷新头衔等级
   * @return 头衔等级
   */
  public static int updateRankLevel() {
    int totalScore = setting.user.idiomCache.getTotalScore();
    currentRankLevel = getRankLevel(totalScore);
    return currentRankLevel;
  }

  /**
   * 获取头衔等级
   * @param score 得分
   * @return 头衔等级
   */
  public static int getRankLevel(int score) {
    int size = RANK_LEVEL_SCORE.length;
    int rankLevel = 0;
    if (score <= 0) {
      rankLevel = 0;
    } else if (score >= RANK_LEVEL_SCORE[size - 1]) {
      rankLevel = size - 1;
    } else {
      for (int i = 0; i < size - 1; i++) {
        if (score >= RANK_LEVEL_SCORE[i] && score < RANK_LEVEL_SCORE[i + 1]) {
          rankLevel = i;
          break;
        }
      }
    }
    return rankLevel;
  }

  /**
   * 将给定字符串重新分行，以适应对话框的显示
   * 
   * @param str 待处理的字符串
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

}
