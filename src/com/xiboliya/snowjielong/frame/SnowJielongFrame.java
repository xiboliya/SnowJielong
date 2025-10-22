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

package com.xiboliya.snowjielong.frame;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.KeyStroke;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.EtchedBorder;

import com.xiboliya.snowjielong.base.BaseButton;
import com.xiboliya.snowjielong.base.BaseDialog;
import com.xiboliya.snowjielong.base.BaseKeyAdapter;
import com.xiboliya.snowjielong.base.BaseLabel;
import com.xiboliya.snowjielong.common.BarrierOrder;
import com.xiboliya.snowjielong.common.IdiomCache;
import com.xiboliya.snowjielong.common.IdiomTag;
import com.xiboliya.snowjielong.dialog.AboutDialog;
import com.xiboliya.snowjielong.dialog.ChangePasswordDialog;
import com.xiboliya.snowjielong.dialog.DepositoryDialog;
import com.xiboliya.snowjielong.dialog.RulesDialog;
import com.xiboliya.snowjielong.util.Util;
import com.xiboliya.snowjielong.window.TipsWindow;

/**
 * 成语接龙
 * 
 * @author 冰原
 */
public class SnowJielongFrame extends JFrame implements ActionListener {
  private static final long serialVersionUID = 1L;
  // 难度等级名称数组
  private static final String[] TOPIC_LEVEL_NAME = new String[] { "初级", "中级", "高级", "特级", "顶级" };
  // 各难度等级下每轮答题时间，单位：秒
  private static final int[] TOPIC_LEVEL_TIME = new int[] { 20, 30, 40, 50, 60 };
  // 各难度等级下每轮闯关速度，获得随机奖励的几率所需的参数，单位：秒/关
  private static final int[] TOPIC_LEVEL_SPEED = new int[] { 3, 4, 5, 6, 7 };
  // 各难度等级下每轮答题正确率，获得随机奖励的几率所需的参数，单位：%
  private static final int[] TOPIC_LEVEL_ACCURACY = new int[] { 90, 80, 70, 60, 50 };
  // 头衔等级名称数组
  private static final String[] RANK_LEVEL_NAME = new String[] { "童生", "秀才", "附生", "增生", "廪生", "举人", "解元", "贡士", "会元", "进士", "探花", "榜眼", "状元" };
  // 头衔等级分数数组
  private static final int[] RANK_LEVEL_SCORE = new int[] { 0, 60, 180, 360, 600, 900, 1260, 1680, 2160, 2700, 3300, 3960, 4680 };
  // 当前题目显示的默认起始索引数组
  private static final int[] START_INDEX = new int[] { 21, 22, 23, 24, 25, 31, 32, 33, 34, 35, 41, 42, 43, 44, 45, 51, 52, 53, 54, 55, 61, 62, 63, 64, 65, 71, 72, 73, 74, 75 };
  private JMenuBar menuBar = new JMenuBar();
  private JMenu menuGame = new JMenu("游戏(G)");
  private JMenuItem itemRestart = new JMenuItem("从头开始(R)", 'R');
  private JMenuItem itemDepository = new JMenuItem("仓库(D)...", 'D');
  private JMenuItem itemExit = new JMenuItem("退出(X)", 'X');
  private JMenu menuSetting = new JMenu("设置(S)");
  private JMenu menuScale = new JMenu("窗口缩放(C)");
  private JRadioButtonMenuItem itemScaleDefault = new JRadioButtonMenuItem("原始比例(A)");
  private JRadioButtonMenuItem itemScale10 = new JRadioButtonMenuItem("放大10%(B)");
  private JRadioButtonMenuItem itemScale20 = new JRadioButtonMenuItem("放大20%(C)");
  private JRadioButtonMenuItem itemScale30 = new JRadioButtonMenuItem("放大30%(D)");
  private JRadioButtonMenuItem itemScale40 = new JRadioButtonMenuItem("放大40%(E)");
  private JRadioButtonMenuItem itemScale50 = new JRadioButtonMenuItem("放大50%(F)");
  private JMenu menuUser = new JMenu("账号管理(U)");
  private JMenuItem itemChangePassword = new JMenuItem("修改密码(P)...", 'P');
  private JMenuItem itemLogout = new JMenuItem("退出账号(O)...", 'O');
  private JMenu menuHelp = new JMenu("帮助(H)");
  private JMenuItem itemHelp = new JMenuItem("游戏规则(H)", 'H');
  private JMenuItem itemAbout = new JMenuItem("关于(A)", 'A');
  private JPanel pnlMain = (JPanel) this.getContentPane();
  private JLabel lblTopic = new JLabel("题目：");
  private JLabel lblUserName = new JLabel();
  private JLabel lblTopicLevel = new JLabel();
  private JLabel lblBarrier = new JLabel();
  private JLabel lblCountdown = new JLabel();
  private JLabel lblScore = new JLabel();
  private JLabel lblTime = new JLabel();
  private JLabel lblRankLevel = new JLabel();
  private JLabel lblSpeed = new JLabel();
  private JLabel lblAccuracy = new JLabel();
  private JLabel lblEnergy = new JLabel();
  private GridLayout gridLayout = new GridLayout(10, 10, 0, 0);
  private GridLayout optionLayout = new GridLayout(2, 10, 0, 0);
  private JPanel pnlCenter = new JPanel(this.gridLayout);
  private JPanel pnlOption = new JPanel(this.optionLayout);
  private JLabel lblOption = new JLabel("选项：");
  private BaseButton btnHint = new BaseButton("提示", Util.ICON_HINT_SMALL);
  private BaseButton btnPause = new BaseButton("暂停", Util.ICON_PAUSE_SMALL);
  private BaseButton btnDelay = new BaseButton("延时", Util.ICON_DELAY_SMALL);
  private BaseButton btnEnergy = new BaseButton("体力", Util.ICON_ENERGY_SMALL);
  private BaseButton btnStart = new BaseButton("开始闯关");
  private BaseButton btnCancel = new BaseButton("退出");

  // 用于存放窗口缩放比例的按钮组
  private ButtonGroup bgpScale = new ButtonGroup();
  private EtchedBorder etchedBorder = new EtchedBorder();
  private MouseAdapter mouseAdapter = null;
  private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
  // 仓库对话框
  private DepositoryDialog depositoryDialog = null;
  // 游戏规则对话框
  private RulesDialog rulesDialog = null;
  // 修改密码对话框
  private ChangePasswordDialog changePasswordDialog = null;
  // 关于对话框
  private AboutDialog aboutDialog = null;
  // 所有格子的集合
  private ArrayList<BaseLabel> cellList = new ArrayList<BaseLabel>();
  // 显示字符的格子索引集合
  private ArrayList<Integer> charCellIndexList = new ArrayList<Integer>();
  // 空格子索引集合
  private LinkedList<Integer> answerCellIndexList = new LinkedList<Integer>();
  // 显示字符的格子集合
  private ArrayList<BaseLabel> charCellList = new ArrayList<BaseLabel>();
  // 空格子集合
  private ArrayList<BaseLabel> answerCellList = new ArrayList<BaseLabel>();
  // 所有备选格子的集合
  private ArrayList<BaseLabel> optionCellList = new ArrayList<BaseLabel>();
  // 备选的显示字符的格子集合
  private ArrayList<BaseLabel> charOptionCellList = new ArrayList<BaseLabel>();
  // 当前关卡各成语格子集合
  private ArrayList<ArrayList<BaseLabel>> idiomCellsList = new ArrayList<ArrayList<BaseLabel>>();
  // 当前难度等级的题目列表
  private ArrayList<String> idiomList = new ArrayList<String>();
  // 当前难度等级
  private int currentTopicLevel = 0;
  // 当前难度等级的关卡顺序
  private BarrierOrder currentBarrierOrder = null;
  // 当前头衔等级
  private int currentRankLevel = 0;
  // 当前关卡
  private int currentBarrier = 0;
  // 当前关卡闯关失败的次数
  private int currentBarrierFailTimes = 0;
  // 当前关卡是否通过
  private boolean isCurrentBarrierPassed = false;
  // 累计分数
  private int totalScore = 0;
  // 当前剩余作答时间，单位：秒
  private int countdown = 0;
  // 当前关卡答题次数
  private int answerTimes = 0;
  // 累计用时，单位：秒
  private int usedTime = 0;
  // 累计通过关卡数
  private int passedBarrierCount = 0;
  // 累计备选答题次数
  private int totalSubmitCount;
  // 累计备选答对次数
  private int totalRightCount;
  // 正确率
  private float accuracy = -1;
  // 闯关速度
  private float speed = -1;
  // 可用的提示卡数量
  private int hintCount = 0;
  // 可用的暂停卡数量
  private int pauseCount = 0;
  // 可用的延时卡数量
  private int delayCount = 0;
  // 可用的体力卡数量
  private int energyCount = 0;
  // 可用的体力
  private int energy = 0;
  // 一天内第一次闯关的时间戳
  private long startTimeMillis;
  // 计时器
  private Timer timer = null;
  // 计时器执行的任务
  private TimerTask task = null;

  /**
   * 构造方法
   */
  public SnowJielongFrame() {
    this.setSize(Util.getSize(570), Util.getSize(650));
    this.setLocationRelativeTo(null); // 使窗口居中显示
    this.setResizable(false);
    this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE); // 设置默认关闭操作为空，以便添加窗口监听事件
    this.setIcon();
    this.loadIdiomCache();
    this.initIdiomList();
    this.init();
    this.initView();
    this.addListeners();
    this.createElements();
    this.setVisible(true);
  }

  /**
   * 初始化当前难度等级的题目列表
   */
  private void initIdiomList() {
    URL url = ClassLoader.getSystemResource("res/topic/level" + this.currentTopicLevel + ".txt");
    if (url == null) {
      return;
    }
    this.idiomList.clear();
    BufferedReader reader = null;
    try {
      reader = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"));
      String line = reader.readLine();
      while (line != null) {
        line = line.trim();
        if (Util.isTextEmpty(line)) {
          line = reader.readLine();
          continue;
        }
        this.idiomList.add(line);
        line = reader.readLine();
      }
    } catch (Exception x) {
      // x.printStackTrace();
    } finally {
      try {
        reader.close();
      } catch (Exception x) {
        // x.printStackTrace();
      }
    }
    if (this.currentBarrierOrder == null) {
      this.refreshCurrentBarrierOrder();
    }
    Util.setting.user.idiomCache.setCurrentBarrierOrder(this.currentBarrierOrder);
    this.sortIdiomList();
  }

  /**
   * 随机获取关卡顺序
   */
  private void refreshCurrentBarrierOrder() {
    Random random = new Random();
    int length = BarrierOrder.values().length;
    int randomIndex = random.nextInt(length);
    this.currentBarrierOrder = BarrierOrder.getItemByIndex(randomIndex);
  }

  /**
   * 对当前难度等级的关卡进行顺序
   */
  private void sortIdiomList() {
    switch (this.currentBarrierOrder) {
      case NATURAL_DESCEND:
        Collections.reverse(this.idiomList);
        break;
      case WHOLE_STRING_ASCEND:
        Collections.sort(this.idiomList, new Comparator<String>() {
          @Override
          public int compare(String str1, String str2) {
            return str1.compareTo(str2);
          }
        });
        break;
      case WHOLE_STRING_DESCEND:
        Collections.sort(this.idiomList, new Comparator<String>() {
          @Override
          public int compare(String str1, String str2) {
            return str2.compareTo(str1);
          }
        });
        break;
      case CHARS_NUMBER_ASCEND:
        Collections.sort(this.idiomList, new Comparator<String>() {
          @Override
          public int compare(String str1, String str2) {
            char[] array1 = str1.toCharArray();
            char[] array2 = str2.toCharArray();
            int number1 = 0;
            for (char c : array1) {
              number1 += c;
            }
            int number2 = 0;
            for (char c : array2) {
              number2 += c;
            }
            return number1 - number2;
          }
        });
        break;
      case CHARS_NUMBER_DESCEND:
        Collections.sort(this.idiomList, new Comparator<String>() {
          @Override
          public int compare(String str1, String str2) {
            char[] array1 = str1.toCharArray();
            char[] array2 = str2.toCharArray();
            int number1 = 0;
            for (char c : array1) {
              number1 += c;
            }
            int number2 = 0;
            for (char c : array2) {
              number2 += c;
            }
            return number2 - number1;
          }
        });
        break;
      case WHOLE_STRING_REVERSE_ASCEND:
        Collections.sort(this.idiomList, new Comparator<String>() {
          @Override
          public int compare(String str1, String str2) {
            StringBuilder stb1 = new StringBuilder(str1);
            StringBuilder stb2 = new StringBuilder(str2);
            stb1.reverse();
            stb2.reverse();
            return stb1.toString().compareTo(stb2.toString());
          }
        });
        break;
      case WHOLE_STRING_REVERSE_DESCEND:
        Collections.sort(this.idiomList, new Comparator<String>() {
          @Override
          public int compare(String str1, String str2) {
            StringBuilder stb1 = new StringBuilder(str1);
            StringBuilder stb2 = new StringBuilder(str2);
            stb1.reverse();
            stb2.reverse();
            return stb2.toString().compareTo(stb1.toString());
          }
        });
        break;
      case CENTER_NUMBER_ASCEND:
        Collections.sort(this.idiomList, new Comparator<String>() {
          @Override
          public int compare(String str1, String str2) {
            String[] array1 = str1.split("#");
            String[] array2 = str2.split("#");
            str1 = array1[1];
            str2 = array2[1];
            return str1.compareTo(str2);
          }
        });
        break;
      case CENTER_NUMBER_DESCEND:
        Collections.sort(this.idiomList, new Comparator<String>() {
          @Override
          public int compare(String str1, String str2) {
            String[] array1 = str1.split("#");
            String[] array2 = str2.split("#");
            str1 = array1[1];
            str2 = array2[1];
            return str2.compareTo(str1);
          }
        });
        break;
      case CENTER_NUMBER_REVERSE_ASCEND:
        Collections.sort(this.idiomList, new Comparator<String>() {
          @Override
          public int compare(String str1, String str2) {
            String[] array1 = str1.split("#");
            String[] array2 = str2.split("#");
            StringBuilder stb1 = new StringBuilder(array1[1]);
            StringBuilder stb2 = new StringBuilder(array2[1]);
            stb1.reverse();
            stb2.reverse();
            return stb1.toString().compareTo(stb2.toString());
          }
        });
        break;
      case CENTER_NUMBER_REVERSE_DESCEND:
        Collections.sort(this.idiomList, new Comparator<String>() {
          @Override
          public int compare(String str1, String str2) {
            String[] array1 = str1.split("#");
            String[] array2 = str2.split("#");
            StringBuilder stb1 = new StringBuilder(array1[1]);
            StringBuilder stb2 = new StringBuilder(array2[1]);
            stb1.reverse();
            stb2.reverse();
            return stb2.toString().compareTo(stb1.toString());
          }
        });
        break;
    }
  }

  /**
   * 读取游戏存档
   */
  private void loadIdiomCache() {
    IdiomCache idiomCache = Util.setting.user.idiomCache;
    this.currentTopicLevel = idiomCache.getCurrentTopicLevel();
    this.currentBarrierOrder = idiomCache.getCurrentBarrierOrder();
    this.currentBarrier = idiomCache.getCurrentBarrier();
    this.currentBarrierFailTimes = idiomCache.getCurrentBarrierFailTimes();
    this.isCurrentBarrierPassed = idiomCache.isCurrentBarrierPassed();
    this.totalScore = idiomCache.getTotalScore();
    this.usedTime = idiomCache.getUsedTime();
    this.passedBarrierCount = idiomCache.getPassedBarrierCount();
    this.totalSubmitCount = idiomCache.getTotalSubmitCount();
    this.totalRightCount = idiomCache.getTotalRightCount();
    this.hintCount = idiomCache.getHintCount();
    this.pauseCount = idiomCache.getPauseCount();
    this.delayCount = idiomCache.getDelayCount();
    this.energyCount = idiomCache.getEnergyCount();
    this.energy = idiomCache.getEnergy();
    this.startTimeMillis = idiomCache.getStartTimeMillis();
    this.initEnergy();
  }

  /**
   * 初始化体力值
   */
  private void initEnergy() {
    if (this.startTimeMillis <= 0) {
      return;
    }
    long currentTimeMillis = System.currentTimeMillis();
    String dateCurrent = this.simpleDateFormat.format(new Date(currentTimeMillis));
    String dateStart = this.simpleDateFormat.format(new Date(this.startTimeMillis));
    if (!dateCurrent.equals(dateStart)) {
      // 不是同一天，体力恢复为100
      this.energy = 100;
      Util.setting.user.idiomCache.setEnergy(this.energy);
    }
  }

  /**
   * 设置自定义的窗口图标
   */
  private void setIcon() {
    try {
      this.setIconImage(Util.ICON_SW.getImage());
    } catch (Exception x) {
      // x.printStackTrace();
    }
  }

  /**
   * 初始化界面
   */
  private void init() {
    this.setTitle("成语接龙");
    this.initPanel();
    this.initMenuBar();
    this.setMenuDefaultInit();
    this.setMenuMnemonic();
    this.setMenuAccelerator();
  }

  /**
   * 初始化主面板
   */
  private void initPanel() {
    this.pnlMain.setLayout(null);
    this.lblTopic.setBounds(Util.getSize(145), Util.getSize(5), Util.getSize(100), Util.getSize(Util.VIEW_HEIGHT));
    this.lblUserName.setBounds(Util.getSize(5), Util.getSize(5), Util.getSize(135), Util.getSize(Util.VIEW_HEIGHT));
    this.lblTopicLevel.setBounds(Util.getSize(5), Util.getSize(30), Util.getSize(135), Util.getSize(Util.VIEW_HEIGHT));
    this.lblBarrier.setBounds(Util.getSize(5), Util.getSize(70), Util.getSize(135), Util.getSize(Util.VIEW_HEIGHT));
    this.lblCountdown.setBounds(Util.getSize(5), Util.getSize(110), Util.getSize(135), Util.getSize(Util.VIEW_HEIGHT));
    this.lblScore.setBounds(Util.getSize(5), Util.getSize(150), Util.getSize(135), Util.getSize(Util.VIEW_HEIGHT));
    this.lblTime.setBounds(Util.getSize(5), Util.getSize(190), Util.getSize(135), Util.getSize(Util.VIEW_HEIGHT));
    this.lblRankLevel.setBounds(Util.getSize(5), Util.getSize(230), Util.getSize(135), Util.getSize(Util.VIEW_HEIGHT));
    this.lblSpeed.setBounds(Util.getSize(5), Util.getSize(270), Util.getSize(135), Util.getSize(Util.VIEW_HEIGHT));
    this.lblAccuracy.setBounds(Util.getSize(5), Util.getSize(310), Util.getSize(135), Util.getSize(Util.VIEW_HEIGHT));
    this.lblEnergy.setBounds(Util.getSize(5), Util.getSize(350), Util.getSize(135), Util.getSize(Util.VIEW_HEIGHT));
    this.pnlCenter.setBounds(Util.getSize(140), Util.getSize(30), Util.getSize(400), Util.getSize(400));
    this.pnlMain.add(this.lblUserName);
    this.pnlMain.add(this.lblTopicLevel);
    this.pnlMain.add(this.lblBarrier);
    this.pnlMain.add(this.lblCountdown);
    this.pnlMain.add(this.lblScore);
    this.pnlMain.add(this.lblTime);
    this.pnlMain.add(this.lblRankLevel);
    this.pnlMain.add(this.lblSpeed);
    this.pnlMain.add(this.lblAccuracy);
    this.pnlMain.add(this.lblEnergy);
    this.pnlMain.add(this.lblTopic);
    this.pnlMain.add(this.pnlCenter);
    this.lblOption.setBounds(Util.getSize(145), Util.getSize(435), Util.getSize(100), Util.getSize(Util.VIEW_HEIGHT));
    this.pnlOption.setBounds(Util.getSize(140), Util.getSize(460), Util.getSize(400), Util.getSize(80));
    this.pnlCenter.setOpaque(true); // 设置组件可以绘制背景
    this.pnlOption.setOpaque(true); // 设置组件可以绘制背景
    this.pnlCenter.setBackground(Color.LIGHT_GRAY);
    this.pnlOption.setBackground(Color.LIGHT_GRAY);
    this.pnlMain.add(this.lblOption);
    this.pnlMain.add(this.pnlOption);
    this.btnHint.setBounds(Util.getSize(20), Util.getSize(430), Util.getSize(80), Util.getSize(Util.ICON_BUTTON_HEIGHT));
    this.btnPause.setBounds(Util.getSize(20), Util.getSize(470), Util.getSize(80), Util.getSize(Util.ICON_BUTTON_HEIGHT));
    this.btnDelay.setBounds(Util.getSize(20), Util.getSize(510), Util.getSize(80), Util.getSize(Util.ICON_BUTTON_HEIGHT));
    this.btnEnergy.setBounds(Util.getSize(20), Util.getSize(550), Util.getSize(80), Util.getSize(Util.ICON_BUTTON_HEIGHT));
    this.btnStart.setBounds(Util.getSize(200), Util.getSize(560), Util.getSize(100), Util.getSize(Util.BUTTON_HEIGHT));
    this.btnCancel.setBounds(Util.getSize(390), Util.getSize(560), Util.getSize(100), Util.getSize(Util.BUTTON_HEIGHT));
    this.pnlMain.add(this.btnHint);
    this.pnlMain.add(this.btnPause);
    this.pnlMain.add(this.btnDelay);
    this.pnlMain.add(this.btnEnergy);
    this.pnlMain.add(this.btnStart);
    this.pnlMain.add(this.btnCancel);
    this.btnHint.setFocusable(false);
    this.btnPause.setFocusable(false);
    this.btnDelay.setFocusable(false);
    this.btnEnergy.setFocusable(false);
    this.btnStart.setFocusable(false);
    this.btnCancel.setFocusable(false);
    this.btnHint.setEnabled(false);
    this.btnPause.setEnabled(false);
    this.btnDelay.setEnabled(false);
    this.btnEnergy.setEnabled(this.energyCount > 0);
  }

  /**
   * 初始化界面显示
   */
  private void initView() {
    this.lblUserName.setText("账号：" + Util.setting.user.getUserName());
    this.lblTopicLevel.setText("难度：" + TOPIC_LEVEL_NAME[this.currentTopicLevel]);
    this.lblBarrier.setText("关卡：第" + (this.currentBarrier + 1) + "/" + this.idiomList.size() + "关");
    this.lblCountdown.setText("剩余时间：" + this.countdown + "秒");
    this.lblScore.setText("累计分数：" + this.totalScore + "分");
    this.lblTime.setText("累计用时：" + this.usedTime + "秒");
    this.initRankLevel();
    this.lblRankLevel.setText("头衔：" + RANK_LEVEL_NAME[this.currentRankLevel]);
    this.lblSpeed.setText("闯关速度：" + this.getSpeedText() + "秒/关");
    this.lblAccuracy.setText("正确率：" + this.getAccuracyText() + "%");
    this.lblEnergy.setText("体力：" + this.energy);
    this.btnHint.setToolTipText("剩余提示卡：" + this.hintCount);
    this.btnPause.setToolTipText("剩余暂停卡：" + this.pauseCount);
    this.btnDelay.setToolTipText("剩余延时卡：" + this.delayCount);
    this.btnEnergy.setToolTipText("剩余体力卡：" + this.energyCount);
  }

  /**
   * 主面板上添加菜单栏
   */
  private void initMenuBar() {
    this.setJMenuBar(this.menuBar);
    this.menuBar.add(this.menuGame);
    this.menuGame.add(this.itemRestart);
    this.menuGame.add(this.itemDepository);
    this.menuGame.add(this.itemExit);
    this.menuBar.add(this.menuSetting);
    this.menuSetting.add(this.menuScale);
    this.menuScale.add(this.itemScaleDefault);
    this.menuScale.add(this.itemScale10);
    this.menuScale.add(this.itemScale20);
    this.menuScale.add(this.itemScale30);
    this.menuScale.add(this.itemScale40);
    this.menuScale.add(this.itemScale50);
    this.menuSetting.add(this.menuUser);
    this.menuUser.add(this.itemChangePassword);
    this.menuUser.add(this.itemLogout);
    this.menuBar.add(this.menuHelp);
    this.menuHelp.add(this.itemHelp);
    this.menuHelp.add(this.itemAbout);
    this.bgpScale.add(this.itemScaleDefault);
    this.bgpScale.add(this.itemScale10);
    this.bgpScale.add(this.itemScale20);
    this.bgpScale.add(this.itemScale30);
    this.bgpScale.add(this.itemScale40);
    this.bgpScale.add(this.itemScale50);
  }

  /**
   * 界面初始化时，设置有关菜单的初始状态与功能
   */
  private void setMenuDefaultInit() {
    float scale = Util.setting.global.scale;
    if (scale == Util.SCALE_10) {
      this.itemScale10.setSelected(true);
    } else if (scale == Util.SCALE_20) {
      this.itemScale20.setSelected(true);
    } else if (scale == Util.SCALE_30) {
      this.itemScale30.setSelected(true);
    } else if (scale == Util.SCALE_40) {
      this.itemScale40.setSelected(true);
    } else if (scale == Util.SCALE_50) {
      this.itemScale50.setSelected(true);
    } else {
      this.itemScaleDefault.setSelected(true);
    }
  }

  /**
   * 为各菜单项设置助记符
   */
  private void setMenuMnemonic() {
    this.menuGame.setMnemonic('G');
    this.menuSetting.setMnemonic('S');
    this.itemScaleDefault.setMnemonic('A');
    this.itemScale10.setMnemonic('B');
    this.itemScale20.setMnemonic('C');
    this.itemScale30.setMnemonic('D');
    this.itemScale40.setMnemonic('E');
    this.itemScale50.setMnemonic('F');
    this.menuScale.setMnemonic('C');
    this.menuHelp.setMnemonic('H');
    this.menuUser.setMnemonic('U');
  }

  /**
   * 为各菜单项设置快捷键
   */
  public void setMenuAccelerator() {
    this.itemRestart.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, InputEvent.CTRL_DOWN_MASK));
    this.itemDepository.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D, InputEvent.CTRL_DOWN_MASK));
    this.itemExit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, InputEvent.CTRL_DOWN_MASK));
    this.itemChangePassword.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, InputEvent.CTRL_DOWN_MASK));
    this.itemLogout.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_DOWN_MASK));
    this.itemHelp.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_H, InputEvent.CTRL_DOWN_MASK));
    this.itemAbout.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F1, 0));
  }

  /**
   * 初始化头衔等级
   */
  private void initRankLevel() {
    int size = RANK_LEVEL_SCORE.length;
    if (this.totalScore <= 0) {
      this.currentRankLevel = 0;
    } else if (this.totalScore >= RANK_LEVEL_SCORE[size - 1]) {
      this.currentRankLevel = size - 1;
    } else {
      for (int i = 0; i < size - 1; i++) {
        if (this.totalScore >= RANK_LEVEL_SCORE[i] && this.totalScore < RANK_LEVEL_SCORE[i + 1]) {
          this.currentRankLevel = i;
          break;
        }
      }
    }
  }

  /**
   * 获取闯关速度字符形式
   * @return 闯关速度字符形式
   */
  private String getSpeedText() {
    if (this.usedTime <= 0 || this.passedBarrierCount <= 0) {
      return "--";
    }
    this.speed = this.getSpeed();
    if (this.speed > 0) {
      return String.valueOf(this.speed);
    }
    return "--";
  }

  /**
   * 获取闯关速度
   * @return 闯关速度
   */
  private float getSpeed() {
    try {
      BigDecimal number1 = new BigDecimal(this.usedTime);
      BigDecimal number2 = new BigDecimal(this.passedBarrierCount);
      BigDecimal number = number1.divide(number2, 1, RoundingMode.HALF_UP);
      return number.floatValue();
    } catch (Exception x) {
      // x.printStackTrace();
    }
    return -1;
  }

  /**
   * 获取正确率字符形式
   * @return 正确率字符形式
   */
  private String getAccuracyText() {
    if (this.totalRightCount <= 0 || this.totalSubmitCount <= 0) {
      return "--";
    }
    this.accuracy = this.getAccuracy();
    if (this.accuracy > 0) {
      return String.valueOf(this.accuracy);
    }
    return "--";
  }

  /**
   * 获取正确率
   * @return 正确率
   */
  private float getAccuracy() {
    try {
      BigDecimal number1 = new BigDecimal(this.totalRightCount * 100);
      BigDecimal number2 = new BigDecimal(this.totalSubmitCount);
      BigDecimal number = number1.divide(number2, 1, RoundingMode.HALF_UP);
      return number.floatValue();
    } catch (Exception x) {
      // x.printStackTrace();
    }
    return -1;
  }

  /**
   * 创建题目、选项区的所有标签
   */
  private void createElements() {
    for (int index = 0; index < 100; index++) {
      BaseLabel lblElement = this.createCellElement();
      this.cellList.add(lblElement);
      this.pnlCenter.add(lblElement);
    }
    for (int index = 0; index < 20; index++) {
      BaseLabel lblElement = this.createCellElement();
      this.optionCellList.add(lblElement);
      this.pnlOption.add(lblElement);
    }
  }

  /**
   * 创建一个文本标签
   * 
   * @return 新创建的文本标签
   */
  private BaseLabel createCellElement() {
    BaseLabel lblElement = new BaseLabel(SwingConstants.CENTER);
    lblElement.setOpaque(true); // 设置标签可以绘制背景
    lblElement.setBorder(this.etchedBorder);
    lblElement.setFocusable(false); // 设置标签不可以获得焦点
    lblElement.addMouseListener(this.mouseAdapter);
    return lblElement;
  }

  /**
   * 刷新题目
   */
  private void refreshElements() {
    int idiomCount = this.idiomList.size();
    if (this.currentBarrierFailTimes < 0 && this.currentBarrier >= (idiomCount - 1)) {
      // 已通关
      this.btnStart.setEnabled(false);
      this.btnHint.setEnabled(false);
      this.btnPause.setEnabled(false);
      this.btnDelay.setEnabled(false);
      this.btnEnergy.setEnabled(false);
      return;
    }
    long currentTimeMillis = System.currentTimeMillis();
    if (this.startTimeMillis <= 0) {
      // 第一次闯关，记录时间戳
      this.startTimeMillis = currentTimeMillis;
      Util.setting.user.idiomCache.setStartTimeMillis(this.startTimeMillis);
    } else {
      String dateCurrent = this.simpleDateFormat.format(new Date(currentTimeMillis));
      String dateStart = this.simpleDateFormat.format(new Date(this.startTimeMillis));
      if (!dateCurrent.equals(dateStart)) {
        // 不是同一天，重新记录时间戳
        this.startTimeMillis = currentTimeMillis;
        Util.setting.user.idiomCache.setStartTimeMillis(this.startTimeMillis);
      }
    }
    if (this.energy <= 0) {
      TipsWindow.show(this, "当前体力不足，不能继续闯关，请明天再来！", TipsWindow.WindowSize.BIGGER);
      return;
    }
    this.energy--;
    Util.setting.user.idiomCache.setEnergy(this.energy);
    this.lblEnergy.setText("体力：" + this.energy);
    this.btnStart.setText("下一关");
    this.btnStart.setEnabled(false);
    this.btnHint.setEnabled(this.hintCount > 0);
    this.btnPause.setEnabled(this.pauseCount > 0);
    this.btnDelay.setEnabled(this.delayCount > 0);
    this.setCellsVisible(true);
    this.isCurrentBarrierPassed = false;
    Util.setting.user.idiomCache.setCurrentBarrierPassed(this.isCurrentBarrierPassed);
    this.clearElementsText();
    this.clearCurrentBarrierList();
    this.answerTimes = 0;
    this.lblBarrier.setText("关卡：第" + (this.currentBarrier + 1) + "/" + idiomCount + "关");
    String item = this.idiomList.get(this.currentBarrier);
    String[] itemSplit = item.split("#");
    // 成语数组
    String[] idiomArray = itemSplit[0].split("，");
    // 规则数组
    String[] ruleArray = itemSplit[1].split("，");
    boolean isStartVertical = this.getRandomIsStartVertical();
    int startIndex = this.getStartIndex(idiomArray, ruleArray, isStartVertical);
    this.viewCells(idiomArray, ruleArray, startIndex, isStartVertical);
    // 备选答案
    String option = itemSplit[2];
    this.refreshOptionCell(option);
    this.refreshElementsBackground();
    this.refreshOptionElementsBackground();
    this.startTimer(false);
  }

  /**
   * 获取当前题目显示的起始索引
   * @param idiomArray 成语数组
   * @param ruleArray 规则数组
   * @param isStartVertical 第一个成语显示的方向
   * @return 当前题目显示的起始索引
   */
  private int getStartIndex(String[] idiomArray, String[] ruleArray, boolean isStartVertical) {
    int size = ruleArray.length;
    int startIndex = this.getRandomStartIndex();
    int tempStartIndex = startIndex;
    LinkedList<Integer> answerCellIndexList = new LinkedList<Integer>();
    int moveCount = 0;
    do {
      for (int i = 0; i < size; i++) {
        String rule = ruleArray[i];
        LinkedList<Integer> answerIndexList = new LinkedList<Integer>();
        answerIndexList.add(Integer.parseInt(rule.substring(0, 1)));
        if (rule.length() > 1) {
          answerIndexList.add(Integer.parseInt(rule.substring(1, 2)));
        }
        if (!answerCellIndexList.isEmpty()) {
          tempStartIndex = answerCellIndexList.getLast();
        }
        if (i % 2 == 0) {
          if (i != 0) {
            if (isStartVertical) {
              tempStartIndex -= answerIndexList.getFirst() * 10;
            } else {
              tempStartIndex -= answerIndexList.getFirst();
            }
          }
          moveCount = this.checkViewCellIndex(tempStartIndex, answerIndexList, idiomArray[i], isStartVertical, answerCellIndexList);
          if (moveCount != 0) {
            startIndex += moveCount;
            tempStartIndex = startIndex;
            answerCellIndexList.clear();
            break;
          }
        } else {
          if (isStartVertical) {
            tempStartIndex -= answerIndexList.getFirst();
          } else {
            tempStartIndex -= answerIndexList.getFirst() * 10;
          }
          moveCount = this.checkViewCellIndex(tempStartIndex, answerIndexList, idiomArray[i], !isStartVertical, answerCellIndexList);
          if (moveCount != 0) {
            startIndex += moveCount;
            tempStartIndex = startIndex;
            answerCellIndexList.clear();
            break;
          }
        }
      }
    } while (moveCount != 0);
    return startIndex;
  }

  /**
   * 随机获取当前题目显示的默认起始索引
   * @return 当前题目显示的默认起始索引
   */
  private int getRandomStartIndex() {
    Random random = new Random();
    int randomIndex = random.nextInt(START_INDEX.length);
    return START_INDEX[randomIndex];
  }

  /**
   * 随机获取当前题目第一个成语显示的方向
   * @return 当前题目第一个成语显示的方向是否为竖向，true为竖向，否则为横向
   */
  private boolean getRandomIsStartVertical() {
    Random random = new Random();
    return random.nextBoolean();
  }

  /**
   * 获取当前题目显示的起始索引需要移动的格子数目
   * @param startIndex 当前成语显示的起始索引
   * @param answerIndexList 答案索引集合
   * @param idiom 成语
   * @param isVertical 是否为竖向显示，true为竖向，否则为横向
   * @param answerCellIndexList 答案格子索引集合
   * @return 当前题目显示的起始索引需要移动的格子数目
   */
  private int checkViewCellIndex(int startIndex, LinkedList<Integer> answerIndexList, String idiom, boolean isVertical, LinkedList<Integer> answerCellIndexList) {
      int size = idiom.length();
      if (isVertical) {
        if (startIndex <= -1 && startIndex >= -10) {
          return 10;
        } else if (startIndex <= -11 && startIndex >= -20) {
          return 20;
        } else if (startIndex <= -21 && startIndex >= -30) {
          return 30;
        } else if (startIndex >= 70 && startIndex <= 79) {
          return -10;
        } else if (startIndex >= 80 && startIndex <= 89) {
          return -20;
        } else if (startIndex >= 90 && startIndex <= 99) {
          return -30;
        }
      } else {
        if (startIndex + size - 1 > 99) {
          return 99 - (startIndex + size - 1);
        }
        String strStartIndex = String.valueOf(startIndex);
        int charStartIndex = Integer.parseInt(strStartIndex.substring(strStartIndex.length() - 1));
        String strEndIndex = String.valueOf(startIndex + size - 1);
        int charEndIndex = Integer.parseInt(strEndIndex.substring(strEndIndex.length() - 1));
        String strAnswerIndex = String.valueOf(charStartIndex + answerIndexList.get(0));
        int charAnswerIndex = Integer.parseInt(strAnswerIndex.substring(strAnswerIndex.length() - 1));
        if (charStartIndex > charEndIndex) {
          if (charAnswerIndex >= charStartIndex) {
            return charAnswerIndex - 10;
          } else {
            return 10 - charStartIndex;
          }
        }
      }
      for (int i = 0; i < size; i++) {
        int cellIndex = startIndex + i;
        if (isVertical) {
          cellIndex = startIndex + i * 10;
        }
        if (answerIndexList.contains(i)) {
          if (!answerCellIndexList.contains(cellIndex)) {
            answerCellIndexList.add(cellIndex);
          }
        }
      }
    return 0;
  }

  /**
   * 显示题目
   * @param idiomArray 成语数组
   * @param ruleArray 规则数组
   * @param startIndex 当前题目显示的起始索引
   * @param isStartVertical 第一个成语显示的方向
   */
  private void viewCells(String[] idiomArray, String[] ruleArray, int startIndex, boolean isStartVertical) {
    int size = ruleArray.length;
    int tempStartIndex = startIndex;
    for (int i = 0; i < size; i++) {
      String rule = ruleArray[i];
      LinkedList<Integer> answerIndexList = new LinkedList<Integer>();
      answerIndexList.add(Integer.parseInt(rule.substring(0, 1)));
      if (rule.length() > 1) {
        answerIndexList.add(Integer.parseInt(rule.substring(1, 2)));
      }
      if (!this.answerCellIndexList.isEmpty()) {
        tempStartIndex = this.answerCellIndexList.getLast();
      }
      if (i % 2 == 0) {
        if (i != 0) {
          if (isStartVertical) {
            tempStartIndex -= answerIndexList.getFirst() * 10;
          } else {
            tempStartIndex -= answerIndexList.getFirst();
          }
        }
        this.viewCell(tempStartIndex, answerIndexList, idiomArray[i], isStartVertical);
      } else {
        if (isStartVertical) {
          tempStartIndex -= answerIndexList.getFirst();
        } else {
          tempStartIndex -= answerIndexList.getFirst() * 10;
        }
        this.viewCell(tempStartIndex, answerIndexList, idiomArray[i], !isStartVertical);
      }
    }
  }

  /**
   * 显示一个成语
   * @param startIndex 当前成语显示的起始索引
   * @param answerIndexList 答案索引集合
   * @param idiom 成语
   * @param isVertical 是否为竖向显示，true为竖向，否则为横向
   */
  private void viewCell(int startIndex, LinkedList<Integer> answerIndexList, String idiom, boolean isVertical) {
    ArrayList<BaseLabel> idiomCellList = new ArrayList<BaseLabel>();
    this.idiomCellsList.add(idiomCellList);
    int size = idiom.length();
    for (int i = 0; i < size; i++) {
      int cellIndex = startIndex + i;
      if (isVertical) {
        cellIndex = startIndex + i * 10;
      }
      BaseLabel cell = this.cellList.get(cellIndex);
      idiomCellList.add(cell);
      String text = idiom.substring(i, i + 1);
      if (answerIndexList.contains(i)) {
        if (!this.answerCellList.contains(cell)) {
          IdiomTag idiomTag = new IdiomTag(text, false);
          cell.setTag(idiomTag);
          this.answerCellIndexList.add(cellIndex);
          this.answerCellList.add(cell);
        }
      } else {
        cell.setText(text);
        cell.setTag(null);
        this.charCellIndexList.add(cellIndex);
        this.charCellList.add(cell);
      }
    }
  }

  /**
   * 闯关倒计时
   */
  private void countdown() {
    this.countdown--;
    this.lblCountdown.setText("剩余时间：" + this.countdown + "秒");
    this.usedTime++;
    Util.setting.user.idiomCache.setUsedTime(this.usedTime);
    if (this.countdown <= 0) {
      this.stopTimer();
      this.lblTime.setText("累计用时：" + this.usedTime + "秒");
      this.outOfTime();
    }
  }

  /**
   * 开始闯关计时器
   * @param isContinue 是否是从暂停状态继续倒计时，true表示从暂停状态继续倒计时，false表示正常的开始倒计时
   */
  private void startTimer(boolean isContinue) {
    if (!isContinue) {
      this.countdown = TOPIC_LEVEL_TIME[this.currentTopicLevel];
    }
    this.lblCountdown.setText("剩余时间：" + this.countdown + "秒");
    this.timer = new Timer();
    this.task = new TimerTask() {
        @Override
        public void run() {
            countdown();
        }
    };
    // 每隔1秒执行一次task
    this.timer.schedule(this.task, 1000, 1000);
  }

  /**
   * 结束闯关计时器
   */
  private void stopTimer() {
    if (this.timer != null) {
      // 取消计时器
      this.timer.cancel();
    }
  }

  /**
   * 清除答题区和选项区格子的文字
   */
  private void clearElementsText() {
    for (BaseLabel lblElement : this.charCellList) {
      lblElement.setText("");
    }
    for (BaseLabel lblElement : this.answerCellList) {
      lblElement.setText("");
    }
    for (BaseLabel lblElement : this.optionCellList) {
      lblElement.setText("");
    }
  }

  /**
   * 清空当前关卡的题目数据
   */
  private void clearCurrentBarrierList() {
    this.charCellIndexList.clear();
    this.answerCellIndexList.clear();
    this.charCellList.clear();
    this.answerCellList.clear();
    this.charOptionCellList.clear();
    this.idiomCellsList.clear();
  }

  /**
   * 刷新答题区格子背景颜色
   */
  private void refreshElementsBackground() {
    int size = this.cellList.size();
    for (int i = 0; i < size; i++) {
      BaseLabel lblElement = this.cellList.get(i);
      if (this.charCellIndexList.contains(i)) {
        lblElement.setBackground(Color.CYAN);
      } else if (this.answerCellIndexList.contains(i)) {
        lblElement.setBackground(Color.WHITE);
      } else {
        lblElement.setBackground(Color.LIGHT_GRAY);
      }
    }
    if (!this.answerCellList.isEmpty()) {
      BaseLabel lblFirstAnswer = this.answerCellList.get(0);
      lblFirstAnswer.setBackground(Color.PINK);
      IdiomTag idiomTag = (IdiomTag)lblFirstAnswer.getTag();
      idiomTag.setFocused(true);
    }
  }

  /**
   * 刷新选项区格子背景颜色
   */
  private void refreshOptionElementsBackground() {
    int size = this.optionCellList.size();
    int charSize = this.charOptionCellList.size();
    for (int i = 0; i < size; i++) {
      BaseLabel lblElement = this.optionCellList.get(i);
      if (i < charSize) {
        lblElement.setBackground(Color.WHITE);
      } else {
        lblElement.setBackground(Color.LIGHT_GRAY);
      }
    }
  }

  /**
   * 刷新选项区格子
   * @param option 选项
   */
  private void refreshOptionCell(String option) {
    int size = option.length();
    char[] array = option.toCharArray();
    Random random = new Random();
    // 对备选答案随机乱序
    for (int i = 0; i < size; i++) {
      int index = random.nextInt(size);
      char item = array[i];
      array[i] = array[index];
      array[index] = item;
    }
    for (int i = 0; i < size; i++) {
      BaseLabel lblElement = this.optionCellList.get(i);
      String optionText = String.valueOf(array[i]);
      lblElement.setText(optionText);
      lblElement.setTag(optionText);
      this.charOptionCellList.add(lblElement);
    }
  }

  /**
   * 添加事件监听器
   */
  private void addListeners() {
    this.btnHint.addActionListener(this);
    this.btnPause.addActionListener(this);
    this.btnDelay.addActionListener(this);
    this.btnEnergy.addActionListener(this);
    this.btnStart.addActionListener(this);
    this.btnCancel.addActionListener(this);
    this.itemRestart.addActionListener(this);
    this.itemDepository.addActionListener(this);
    this.itemExit.addActionListener(this);
    this.itemScaleDefault.addActionListener(this);
    this.itemScale10.addActionListener(this);
    this.itemScale20.addActionListener(this);
    this.itemScale30.addActionListener(this);
    this.itemScale40.addActionListener(this);
    this.itemScale50.addActionListener(this);
    this.itemChangePassword.addActionListener(this);
    this.itemLogout.addActionListener(this);
    this.itemHelp.addActionListener(this);
    this.itemAbout.addActionListener(this);
    this.mouseAdapter = new MouseAdapter() {
      public void mouseClicked(MouseEvent e) {
        BaseLabel lblElement = (BaseLabel) e.getSource();
        if (answerCellList.contains(lblElement)) {
          cancelAnswer(lblElement);
        } else if (charOptionCellList.contains(lblElement)) {
          checkAnswer(lblElement);
        }
        refreshOptionCellBackground();
      }
    };
    // 为窗口添加事件监听器
    this.addWindowListener(new WindowAdapter() {
      public void windowClosing(WindowEvent e) {
        exit();
      }
    });
  }

  /**
   * 取消答错的格子，将选项还原到选项区
   * @param lblAnswer 当前获得焦点的答题区的格子
   */
  private void cancelAnswer(BaseLabel lblAnswer) {
    if (this.countdown <= 0 || this.isCurrentBarrierPassed || Color.GREEN.equals(lblAnswer.getBackground())) {
      return;
    }
    String text = lblAnswer.getText();
    if (Util.isTextEmpty(text)) {
      IdiomTag idiomTag = (IdiomTag)lblAnswer.getTag();
      if (!idiomTag.isFocused()) {
        // 如果点击的非当前选中的格子，则更新点击的格子和当前选中的格子背景色
        for (BaseLabel lblElement : this.answerCellList) {
          IdiomTag tag = (IdiomTag)lblElement.getTag();
          if (tag.isFocused()) {
            tag.setFocused(false);
            lblElement.setBackground(Color.WHITE);
            break;
          }
        }
        idiomTag.setFocused(true);
        lblAnswer.setBackground(Color.PINK);
      }
      return;
    }
    for (BaseLabel lblOption : this.charOptionCellList) {
      if (text.equals(lblOption.getTag())) {
        lblAnswer.setText("");
        lblOption.setText(text);
        break;
      }
    }
  }

  /**
   * 答题并检测是否回答正确
   * @param lblOption 当前点击的选项区格子
   */
  private void checkAnswer(BaseLabel lblOption) {
    String optionText = lblOption.getText();
    if (this.countdown <= 0 || this.isCurrentBarrierPassed || Util.isTextEmpty(optionText)) {
      return;
    }
    BaseLabel lblAnswer = null;
    for (BaseLabel lblCell : this.answerCellList) {
      IdiomTag idiomTag = (IdiomTag)lblCell.getTag();
      if (idiomTag.isFocused()) {
        // 获取当前选中的空格子
        lblAnswer = lblCell;
        break;
      }
    }
    this.refreshOptionCellText(lblOption, lblAnswer.getText());
    lblAnswer.setText(optionText);
    this.answerTimes++;
    this.totalSubmitCount++;
    Util.setting.user.idiomCache.setTotalSubmitCount(this.totalSubmitCount);
    IdiomTag idiomTag = (IdiomTag)lblAnswer.getTag();
    String content = idiomTag.getContent();
    if (optionText.equals(content)) {
      this.totalRightCount++;
      Util.setting.user.idiomCache.setTotalRightCount(this.totalRightCount);
      this.lblAccuracy.setText("正确率：" + this.getAccuracyText() + "%");
      this.refreshCharCellList(lblAnswer);
      if (!this.isAllAnswerRight()) {
        return;
      }
      this.stopTimer();
      int currentScore = this.getCurrentScore();
      this.totalScore += currentScore;
      Util.setting.user.idiomCache.setTotalScore(this.totalScore);
      this.isCurrentBarrierPassed = true;
      Util.setting.user.idiomCache.setCurrentBarrierPassed(this.isCurrentBarrierPassed);
      this.passedBarrierCount++;
      Util.setting.user.idiomCache.setPassedBarrierCount(this.passedBarrierCount);
      this.currentBarrierFailTimes = 0;
      this.countdown = 0;
      this.lblCountdown.setText("剩余时间：" + this.countdown + "秒");
      this.lblScore.setText("累计分数：" + this.totalScore + "分");
      this.lblTime.setText("累计用时：" + this.usedTime + "秒");
      this.lblSpeed.setText("闯关速度：" + this.getSpeedText() + "秒/关");
      this.btnStart.setEnabled(true);
      this.btnHint.setEnabled(false);
      this.btnPause.setEnabled(false);
      this.btnDelay.setEnabled(false);
      TipsWindow.show(this, "回答正确，加" + currentScore + "分！", TipsWindow.Background.GREEN, TipsWindow.TimerLength.LONG, TipsWindow.WindowSize.SMALL);
      this.refreshRankLevel();
      this.clearAnswerAndOptionCellsTag();
      if (this.isPassedAllBarrier()) {
        if (this.hasNextTopicLevel()) {
          JOptionPane.showMessageDialog(this, "恭喜通关！你可以进入下一等级了！", Util.SOFTWARE, JOptionPane.CANCEL_OPTION);
        } else {
          this.currentBarrierFailTimes = -1;
          this.btnStart.setEnabled(false);
          JOptionPane.showMessageDialog(this, "恭喜通关！已没有更多成语可以挑战了！", Util.SOFTWARE, JOptionPane.CANCEL_OPTION);
        }
      }
      Util.setting.user.idiomCache.setCurrentBarrierFailTimes(this.currentBarrierFailTimes);
      this.reward();
    } else {
      this.lblAccuracy.setText("正确率：" + this.getAccuracyText() + "%");
      if (this.answerTimes >= this.answerCellList.size() * 2) {
        // 作答次数最多为空格子个数乘以2，如有1个空格子则作答次数最多为2次
        this.stopTimer();
        this.lblTime.setText("累计用时：" + this.usedTime + "秒");
        this.currentBarrierFailTimes++;
        this.countdown = 0;
        this.lblCountdown.setText("剩余时间：" + this.countdown + "秒");
        Util.setting.user.idiomCache.setCurrentBarrierFailTimes(this.currentBarrierFailTimes);
        TipsWindow.show(this, "已超过作答次数，闯关失败！");
        this.btnStart.setText("重新闯关");
        this.btnStart.setEnabled(true);
        this.btnHint.setEnabled(false);
        this.btnPause.setEnabled(false);
        this.btnDelay.setEnabled(false);
        this.setCellsVisible(false);
        this.clearAnswerAndOptionCellsTag();
      } else {
        TipsWindow.show(this, "回答错误！", TipsWindow.Background.PINK, TipsWindow.TimerLength.DEFAULT, TipsWindow.WindowSize.SMALLER);
      }
    }
  }

  /**
   * 刷新选项区格子文字
   * @param lblElement 当前点击的选项区格子
   * @param oldOption 当前获得焦点的答题区格子文字
   */
  private void refreshOptionCellText(BaseLabel lblElement, String oldOption) {
    lblElement.setText("");
    if (!Util.isTextEmpty(oldOption)) {
      for (BaseLabel lblOption : this.charOptionCellList) {
        if (oldOption.equals(lblOption.getTag().toString())) {
          lblOption.setText(oldOption);
        }
      }
    }
  }

  /**
   * 获取本关卡得分
   * @return 本关卡得分
   */
  private int getCurrentScore() {
    int currentScore = 0;
    // 当前关卡闯关失败超过1次，则得分为0
    if (this.currentBarrierFailTimes > 0) {
      return currentScore;
    }
    int totalTime = TOPIC_LEVEL_TIME[this.currentTopicLevel];
    int useTime = totalTime - this.countdown;
    currentScore = totalTime / 5 - useTime / 5;
    // 本轮每答错1次则扣1分
    int wrongTimes = this.answerTimes - this.answerCellList.size();
    if (wrongTimes >= currentScore) {
      currentScore = 0;
    } else {
      currentScore -= wrongTimes;
    }
    return currentScore;
  }

  /**
   * 刷新答题区已答对成语的格子
   * @param lblAnswer 当前答题区答对的格子
   */
  private void refreshCharCellList(BaseLabel lblAnswer) {
    for (ArrayList<BaseLabel> lblList : this.idiomCellsList) {
      if (!lblList.contains(lblAnswer)) {
        continue;
      }
      boolean isAllRight = true;
      for (BaseLabel lblCell : lblList) {
        IdiomTag idiomTag = (IdiomTag)lblCell.getTag();
        if (idiomTag != null && !lblCell.getText().equals(idiomTag.getContent())) {
          isAllRight = false;
          break;
        }
      }
      if (isAllRight) {
        for (BaseLabel lblCell : lblList) {
          lblCell.setBackground(Color.GREEN);
        }
      } else {
        lblAnswer.setBackground(Color.GREEN);
      }
      IdiomTag idiomTag = (IdiomTag)lblAnswer.getTag();
      idiomTag.setFocused(false);
    }
    this.refreshNextElementBackground();
  }

  /**
   * 自动更新下一个未答对格子的背景色
   */
  private void refreshNextElementBackground() {
    for (BaseLabel lblAnswer : this.answerCellList) {
      IdiomTag idiomTag = (IdiomTag)lblAnswer.getTag();
      if (!lblAnswer.getText().equals(idiomTag.getContent())) {
        idiomTag.setFocused(true);
        lblAnswer.setBackground(Color.PINK);
        break;
      }
    }
  }

  /**
   * 是否本关卡所有成语都答对
   * @return 是否本关卡所有成语都答对
   */
  private boolean isAllAnswerRight() {
    for (BaseLabel lblAnswer : this.answerCellList) {
      IdiomTag idiomTag = (IdiomTag)lblAnswer.getTag();
      if (!lblAnswer.getText().equals(idiomTag.getContent())) {
        return false;
      }
    }
    return true;
  }

  /**
   * 刷新头衔等级显示
   */
  private void refreshRankLevel() {
    int oldRankLevel = this.currentRankLevel;
    int size = RANK_LEVEL_SCORE.length;
    if (this.totalScore <= 0) {
      this.currentRankLevel = 0;
    } else if (this.totalScore >= RANK_LEVEL_SCORE[size - 1]) {
      this.currentRankLevel = size - 1;
    } else {
      for (int i = 0; i < size - 1; i++) {
        if (this.totalScore >= RANK_LEVEL_SCORE[i] && this.totalScore < RANK_LEVEL_SCORE[i + 1]) {
          this.currentRankLevel = i;
          break;
        }
      }
    }
    String rankLevelName = RANK_LEVEL_NAME[this.currentRankLevel];
    this.lblRankLevel.setText("头衔：" + rankLevelName);
    if (this.currentRankLevel > oldRankLevel) {
      JOptionPane.showMessageDialog(this, "恭喜头衔升级为：" + rankLevelName + "！", Util.SOFTWARE, JOptionPane.CANCEL_OPTION);
    }
  }

  /**
   * 恢复提示过的选项格子背景颜色
   */
  private void refreshOptionCellBackground() {
    for (BaseLabel lblOption : this.charOptionCellList) {
      if (Color.PINK.equals(lblOption.getBackground())) {
        lblOption.setBackground(Color.WHITE);
        break;
      }
    }
  }

  /**
   * 作答超时
   */
  private void outOfTime() {
    TipsWindow.show(this, "作答时间已到，闯关失败！");
    this.btnStart.setText("重新闯关");
    this.btnStart.setEnabled(true);
    this.btnHint.setEnabled(false);
    this.btnPause.setEnabled(false);
    this.btnDelay.setEnabled(false);
    this.setCellsVisible(false);
    this.clearAnswerAndOptionCellsTag();
  }

  /**
   * 是否通过当前难度等级的所有关卡
   * @return 是否通过当前难度等级的所有关卡
   */
  private boolean isPassedAllBarrier() {
    return (this.currentBarrier + 1) >= this.idiomList.size();
  }

  /**
   * 是否存在下一难度等级的关卡
   * @return 是否存在下一难度等级的关卡
   */
  private boolean hasNextTopicLevel() {
    URL url = ClassLoader.getSystemResource("res/idiom/level" + (this.currentTopicLevel + 1) + ".txt");
    return url != null;
  }

  /**
   * 获得随机奖励
   */
  private void reward() {
    int levelSpeed = TOPIC_LEVEL_SPEED[this.currentTopicLevel];
    int LevelAccuracy = TOPIC_LEVEL_ACCURACY[this.currentTopicLevel];
    Random random = new Random();
    String strRandomNumber = String.valueOf(random.nextInt(1000) + 1000);
    if (this.speed <= levelSpeed || this.accuracy >= LevelAccuracy) {
      // 获得每类奖励的概率均为1/10
      if (strRandomNumber.endsWith("1")) {
        this.obtainReward();
      }
    } else if (this.speed <= levelSpeed * 2 || this.accuracy >= LevelAccuracy - 10) {
      // 获得每类奖励的概率均为1/50
      if (strRandomNumber.endsWith("01") || strRandomNumber.endsWith("02")) {
        this.obtainReward();
      }
    } else if (this.speed <= levelSpeed * 3 || this.accuracy >= LevelAccuracy - 20) {
      // 获得每类奖励的概率均为1/100
      if (strRandomNumber.endsWith("01")) {
        this.obtainReward();
      }
    } else {
      // 获得每类奖励的概率均为1/500
      if (strRandomNumber.endsWith("001") || strRandomNumber.endsWith("002")) {
        this.obtainReward();
      }
    }
  }

  /**
   * 随机分配获得的奖励
   */
  private void obtainReward() {
    Random random = new Random();
    int index = random.nextInt(5);
    switch (index) {
      case 0:
        this.obtainHint();
        break;
      case 1:
        this.obtainPause();
        break;
      case 2:
        this.obtainDelay();
        break;
      case 3:
        this.obtainEnergy();
        break;
      case 4:
        this.obtainStar();
        break;
      default:
        break;
    }
  }

  /**
   * 获得1张提示卡
   */
  private void obtainHint() {
    this.hintCount++;
    Util.setting.user.idiomCache.setHintCount(this.hintCount);
    this.refreshHint();
    TipsWindow.show(this, "恭喜：获得1张提示卡！", TipsWindow.Background.GREEN, TipsWindow.TimerLength.SHORT, TipsWindow.WindowSize.DEFAULT);
  }

  /**
   * 获得1张暂停卡
   */
  private void obtainPause() {
    this.pauseCount++;
    Util.setting.user.idiomCache.setPauseCount(this.pauseCount);
    this.refreshPause();
    TipsWindow.show(this, "恭喜：获得1张暂停卡！", TipsWindow.Background.GREEN, TipsWindow.TimerLength.SHORT, TipsWindow.WindowSize.DEFAULT);
  }

  /**
   * 获得1张延时卡
   */
  private void obtainDelay() {
    this.delayCount++;
    Util.setting.user.idiomCache.setDelayCount(this.delayCount);
    this.refreshDelay();
    TipsWindow.show(this, "恭喜：获得1张延时卡！", TipsWindow.Background.GREEN, TipsWindow.TimerLength.SHORT, TipsWindow.WindowSize.DEFAULT);
  }

  /**
   * 获得1张体力卡
   */
  private void obtainEnergy() {
    this.energyCount++;
    Util.setting.user.idiomCache.setEnergyCount(this.energyCount);
    this.refreshEnergy();
    TipsWindow.show(this, "恭喜：获得1张体力卡！", TipsWindow.Background.GREEN, TipsWindow.TimerLength.SHORT, TipsWindow.WindowSize.DEFAULT);
  }

  /**
   * 获得1颗星
   */
  private void obtainStar() {
    Random random = new Random();
    int index = random.nextInt(Util.STAR_NAMES.length);
    String name = Util.STAR_NAMES[index];
    HashMap<String, Integer> starMap = Util.setting.user.idiomCache.getStarMap();
    Integer count = starMap.get(name);
    if (count == null) {
      count = 0;
    }
    starMap.put(name, count + 1);
    TipsWindow.show(this, "恭喜：获得1颗" + name + "！", TipsWindow.Background.GREEN, TipsWindow.TimerLength.SHORT, TipsWindow.WindowSize.DEFAULT);
  }

  /**
   * 从头开始闯关
   */
  private void restart() {
    int result = JOptionPane.showConfirmDialog(this, "此操作将清空所有的等级、分数、奖励和头衔，从头开始闯关！\n是否继续？",
        Util.SOFTWARE, JOptionPane.YES_NO_OPTION);
    if (result != JOptionPane.YES_OPTION) {
      return;
    }
    this.stopTimer();
    this.countdown = 0;
    this.btnStart.setText("开始闯关");
    this.btnStart.setEnabled(true);
    Util.setting.user.idiomCache = new IdiomCache();
    this.loadIdiomCache();
    this.initIdiomList();
    this.initView();
    this.refreshToolCount();
    this.clearElementsText();
    this.clearCurrentBarrierList();
    this.clearAnswerAndOptionCellsTag();
    this.refreshElementsBackground();
    this.refreshOptionElementsBackground();
  }

  /**
   * 打开仓库对话框
   */
  private void openDepositoryDialog() {
    if (this.depositoryDialog == null) {
      this.depositoryDialog = new DepositoryDialog(this, true, Util.setting);
    } else {
      this.depositoryDialog.setVisible(true);
    }
    this.refreshToolCount();
  }

  /**
   * 刷新功能卡数据和显示
   */
  private void refreshToolCount() {
    this.hintCount = Util.setting.user.idiomCache.getHintCount();
    this.pauseCount = Util.setting.user.idiomCache.getPauseCount();
    this.delayCount = Util.setting.user.idiomCache.getDelayCount();
    this.energyCount = Util.setting.user.idiomCache.getEnergyCount();
    this.refreshHint();
    this.refreshPause();
    this.refreshDelay();
    this.refreshEnergy();
  }

  /**
   * 打开游戏规则对话框
   */
  private void openIdiomRulesDialog() {
    if (this.rulesDialog == null) {
      this.rulesDialog = new RulesDialog(this, true);
    } else {
      this.rulesDialog.setVisible(true);
    }
  }

  /**
   * "关于"的处理方法
   */
  private void showAbout() {
    if (this.aboutDialog == null) {
      this.aboutDialog = new AboutDialog(this, true);
      this.aboutDialog.pack(); // 自动调整窗口大小，以适应各组件
    }
    this.aboutDialog.setVisible(true);
  }

  /**
   * 提示
   */
  private void hint() {
    if (this.countdown <= 0) {
      return;
    }
    BaseLabel lblAnswer = this.getFocusedAnswerCell();
    if (lblAnswer == null) {
      return;
    }
    IdiomTag tag = (IdiomTag)lblAnswer.getTag();
    String text = tag.getContent();
    for (BaseLabel lblOption : this.charOptionCellList) {
      if (text.equals(lblOption.getTag())) {
        lblOption.setBackground(Color.PINK);
        this.hintCount--;
        Util.setting.user.idiomCache.setHintCount(this.hintCount);
        this.refreshHint();
        break;
      }
    }
  }

  /**
   * 刷新提示功能
   */
  private void refreshHint() {
    if (this.countdown > 0) {
      this.btnHint.setEnabled(this.hintCount > 0);
    } else {
      this.btnHint.setEnabled(false);
    }
    this.btnHint.setToolTipText("剩余提示卡：" + this.hintCount);
  }

  /**
   * 暂停
   */
  private void pause() {
    if (this.countdown <= 0) {
      return;
    }
    if ("继续".equals(this.btnPause.getText())) {
      this.setCellsVisible(true);
      this.btnPause.setText("暂停");
      this.startTimer(true);
      this.refreshPause();
      // 继续后可以使用提示功能，因此恢复提示按钮状态
      this.btnHint.setEnabled(this.hintCount > 0);
    } else {
      this.setCellsVisible(false);
      this.btnPause.setText("继续");
      this.stopTimer();
      this.pauseCount--;
      Util.setting.user.idiomCache.setPauseCount(this.pauseCount);
      this.refreshPause();
      // 暂停后无法使用提示功能，因此设置提示按钮不可用
      this.btnHint.setEnabled(false);
   }
  }

  /**
   * 设置显示或隐藏答题区和备选区的非空格子
   * @param visible 显示或隐藏答题区和备选区的非空格子，显示为true，反之为false
   */
  private void setCellsVisible(boolean visible) {
    for (BaseLabel lblElement : this.charCellList) {
      lblElement.setVisible(visible);
    }
    for (BaseLabel lblElement : this.answerCellList) {
      lblElement.setVisible(visible);
    }
    for (BaseLabel lblElement : this.charOptionCellList) {
      lblElement.setVisible(visible);
    }
  }

  /**
   * 清除答题区和选项区格子的属性
   */
  private void clearAnswerAndOptionCellsTag() {
    for (BaseLabel lblElement : this.answerCellList) {
      lblElement.setTag(null);
    }
    for (BaseLabel lblElement : this.optionCellList) {
      lblElement.setTag(null);
    }
  }

  /**
   * 刷新暂停功能
   */
  private void refreshPause() {
    if (this.countdown > 0) {
      if ("继续".equals(this.btnPause.getText())) {
        // 已暂停状态下，按钮可用
        this.btnPause.setEnabled(true);
      } else {
        this.btnPause.setEnabled(this.pauseCount > 0);
      }
    } else {
      this.btnPause.setEnabled(false);
    }
    this.btnPause.setToolTipText("剩余暂停卡：" + this.pauseCount);
  }

  /**
   * 延时
   */
  private void delay() {
    if (this.countdown <= 0) {
      return;
    }
    // 一次延时10秒
    this.countdown += 10;
    this.delayCount--;
    Util.setting.user.idiomCache.setDelayCount(this.delayCount);
    this.refreshDelay();
  }

  /**
   * 刷新延时功能
   */
  private void refreshDelay() {
    if (this.countdown > 0) {
      this.btnDelay.setEnabled(this.delayCount > 0);
    } else {
      this.btnDelay.setEnabled(false);
    }
    this.btnDelay.setToolTipText("剩余延时卡：" + this.delayCount);
  }

  /**
   * 体力
   */
  private void energy() {
    // 使用一次体力卡，增加体力值：5
    this.energy += 5;
    Util.setting.user.idiomCache.setEnergy(this.energy);
    this.lblEnergy.setText("体力：" + this.energy);
    this.energyCount--;
    Util.setting.user.idiomCache.setEnergyCount(this.energyCount);
    this.refreshEnergy();
  }

  /**
   * 刷新体力功能
   */
  private void refreshEnergy() {
    this.btnEnergy.setEnabled(this.energyCount > 0);
    this.btnEnergy.setToolTipText("剩余体力卡：" + this.energyCount);
  }

  /**
   * 获取当前选中的空格子
   * @return 当前选中的空格子
   */
  private BaseLabel getFocusedAnswerCell() {
    for (BaseLabel lblCell : this.answerCellList) {
      IdiomTag idiomTag = (IdiomTag)lblCell.getTag();
      if (idiomTag.isFocused()) {
        return lblCell;
      }
    }
    return null;
  }

  /**
   * 开始闯关
   */
  private void start() {
    if (this.isCurrentBarrierPassed) {
      if (this.isPassedAllBarrier()) {
        if (this.hasNextTopicLevel()) {
          this.currentTopicLevel++;
          Util.setting.user.idiomCache.setCurrentTopicLevel(this.currentTopicLevel);
          this.currentBarrierOrder = null;
          this.initIdiomList();
          this.lblTopicLevel.setText("难度：" + TOPIC_LEVEL_NAME[this.currentTopicLevel]);
          this.currentBarrier = 0;
          Util.setting.user.idiomCache.setCurrentBarrier(this.currentBarrier);
          this.refreshElements();
        } else {
          TipsWindow.show(this, "您已通关，没有成语可以挑战了！", TipsWindow.Background.GREEN);
        }
      } else {
        this.currentBarrier++;
        Util.setting.user.idiomCache.setCurrentBarrier(this.currentBarrier);
        this.refreshElements();
      }
    } else if (this.countdown <= 0) {
      this.refreshElements();
    } else {
      TipsWindow.show(this, "通过当前关卡，才能进入下一关！");
    }
  }

  /**
   * 退出
   */
  private void exit() {
    if (this.countdown > 0) {
      int result = JOptionPane.showConfirmDialog(this, "当前正在闯关中，如果退出会导致本轮闯关失败。\n是否继续退出？", 
        Util.SOFTWARE, JOptionPane.YES_NO_CANCEL_OPTION);
      if (result != JOptionPane.YES_OPTION) {
        return;
      }
    }
    this.stopTimer();
    Util.settingAdapter.save();
    System.exit(0);
  }

  /**
   * 缩放页面显示比例
   * @param scale 缩放比例
   */
  private void zoom(float scale) {
    if (Util.setting.global.scale == scale) {
      return;
    }
    Util.setting.global.scale = scale;
    this.closeAllWindows();
    SwingUtilities.invokeLater(new Runnable() {
      public void run() {
        Util.setDefaultFont();
        new SnowJielongFrame();
      }
    });
  }

  /**
   * 关闭所有打开的窗口
   */
  private void closeAllWindows() {
    Window[] windows = Window.getWindows();
    for (Window window : windows) {
      window.dispose();
    }
  }

  /**
   * 打开修改密码对话框
   */
  private void openChangePasswordDialog() {
    if (this.changePasswordDialog == null) {
      this.changePasswordDialog = new ChangePasswordDialog(this, true);
    } else {
      this.changePasswordDialog.setVisible(true);
    }
  }

  /**
   * 打开退出账号对话框
   */
  private void openLogoutDialog() {
    String info = "";
    if (this.countdown > 0) {
      info = "此操作将退出当前账号，当前正在闯关中，如果退出会导致本轮闯关失败。\n是否继续？";
    } else {
      info = "此操作将退出当前账号。\n是否继续？";
    }
    int result = JOptionPane.showConfirmDialog(this, info, Util.SOFTWARE, JOptionPane.YES_NO_OPTION);
    if (result != JOptionPane.YES_OPTION) {
      return;
    }
    this.stopTimer();
    Util.setting.user = null;
    this.closeAllWindows();
    SwingUtilities.invokeLater(new Runnable() {
      public void run() {
        Util.setDefaultFont();
        new LoginFrame();
      }
    });
  }

  /**
   * 为各组件添加事件的处理方法
   */
  @Override
  public void actionPerformed(ActionEvent e) {
    Object source = e.getSource();
    if (this.btnHint.equals(source)) {
      this.hint();
    } else if (this.btnPause.equals(source)) {
      this.pause();
    } else if (this.btnDelay.equals(source)) {
      this.delay();
    } else if (this.btnEnergy.equals(source)) {
      this.energy();
    } else if (this.btnStart.equals(source)) {
      this.start();
    } else if (this.btnCancel.equals(source)) {
      this.exit();
    } else if (this.itemRestart.equals(source)) {
      this.restart();
    } else if (this.itemDepository.equals(source)) {
      this.openDepositoryDialog();
    } else if (this.itemExit.equals(source)) {
      this.exit();
    } else if (this.itemScaleDefault.equals(source)) {
      this.zoom(Util.SCALE_DEFAULT);
    } else if (this.itemScale10.equals(source)) {
      this.zoom(Util.SCALE_10);
    } else if (this.itemScale20.equals(source)) {
      this.zoom(Util.SCALE_20);
    } else if (this.itemScale30.equals(source)) {
      this.zoom(Util.SCALE_30);
    } else if (this.itemScale40.equals(source)) {
      this.zoom(Util.SCALE_40);
    } else if (this.itemScale50.equals(source)) {
      this.zoom(Util.SCALE_50);
    } else if (this.itemChangePassword.equals(source)) {
      this.openChangePasswordDialog();
    } else if (this.itemLogout.equals(source)) {
      this.openLogoutDialog();
    } else if (this.itemHelp.equals(source)) {
      this.openIdiomRulesDialog();
    } else if (this.itemAbout.equals(source)) {
      this.showAbout();
    }
  }
}
