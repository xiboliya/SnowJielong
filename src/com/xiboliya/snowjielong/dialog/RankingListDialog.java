/**
 * Copyright (C) 2025 冰原
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

package com.xiboliya.snowjielong.dialog;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import com.xiboliya.snowjielong.base.BaseDialog;
import com.xiboliya.snowjielong.base.BaseKeyAdapter;
import com.xiboliya.snowjielong.common.LabelAlignment;
import com.xiboliya.snowjielong.common.User;
import com.xiboliya.snowjielong.panel.HorizontalLabelPanel;
import com.xiboliya.snowjielong.util.Util;

/**
 * "排行榜"对话框
 * 
 * @author 冰原
 */
public class RankingListDialog extends BaseDialog implements ActionListener, ChangeListener {
  private static final long serialVersionUID = 1L;
  // 各排行榜显示的最多条数，包括标题栏在内
  private static final int ROW_COUNT = 10;
  // 各排行榜每行显示的元素个数
  private static final int ITEM_COUNT = 3;
  private JTabbedPane tpnMain = new JTabbedPane();
  private JPanel pnlMain = (JPanel) this.getContentPane();
  private GridLayout gridLayout = new GridLayout(ROW_COUNT, 1, 0, 0);
  private JPanel pnlScore = new JPanel(this.gridLayout);
  private JPanel pnlBarrier = new JPanel(this.gridLayout);
  private JPanel pnlSpeed = new JPanel(this.gridLayout);
  private JPanel pnlAccuracy = new JPanel(this.gridLayout);
  private JPanel pnlWealth = new JPanel(this.gridLayout);
  private ArrayList<HorizontalLabelPanel> scorePanelList = new ArrayList<HorizontalLabelPanel>();
  private ArrayList<HorizontalLabelPanel> barrierPanelList = new ArrayList<HorizontalLabelPanel>();
  private ArrayList<HorizontalLabelPanel> speedPanelList = new ArrayList<HorizontalLabelPanel>();
  private ArrayList<HorizontalLabelPanel> accuracyPanelList = new ArrayList<HorizontalLabelPanel>();
  private ArrayList<HorizontalLabelPanel> wealthPanelList = new ArrayList<HorizontalLabelPanel>();
  private BaseKeyAdapter keyAdapter = new BaseKeyAdapter(this);

  /**
   * 构造方法
   * 
   * @param owner 用于显示该对话框的父组件
   * @param modal 是否为模式对话框
   */
  public RankingListDialog(JFrame owner, boolean modal) {
    super(owner, modal);
    this.init();
    this.addListeners();
    this.createElements();
    this.setSize(Util.getSize(350), Util.getSize(350));
    this.setVisible(true);
  }

  /**
   * 重写父类的方法：设置本窗口是否可见
   */
  @Override
  public void setVisible(boolean visible) {
    if (visible) {
      this.updateCurrentPanel();
    }
    super.setVisible(visible);
  }

  /**
   * 初始化界面
   */
  private void init() {
    this.setTitle("排行榜");
    this.addTabbedPane();
  }

  /**
   * 主面板上添加选项卡视图
   */
  private void addTabbedPane() {
    this.tpnMain.add(this.pnlScore, "得分榜");
    this.tpnMain.add(this.pnlBarrier, "闯关榜");
    this.tpnMain.add(this.pnlSpeed, "神速榜");
    this.tpnMain.add(this.pnlAccuracy, "精准榜");
    this.tpnMain.add(this.pnlWealth, "财富榜");
    this.tpnMain.setSelectedIndex(0);
    this.pnlMain.add(this.tpnMain, BorderLayout.CENTER);
  }

  /**
   * 创建所有标签
   */
  private void createElements() {
    for (int index = 0; index < ROW_COUNT; index++) {
      HorizontalLabelPanel scorePanel = this.createCellElement();
      this.scorePanelList.add(scorePanel);
      this.pnlScore.add(scorePanel);
      HorizontalLabelPanel barrierPanel = this.createCellElement();
      this.barrierPanelList.add(barrierPanel);
      this.pnlBarrier.add(barrierPanel);
      HorizontalLabelPanel speedPanel = this.createCellElement();
      this.speedPanelList.add(speedPanel);
      this.pnlSpeed.add(speedPanel);
      HorizontalLabelPanel accuracyPanel = this.createCellElement();
      this.accuracyPanelList.add(accuracyPanel);
      this.pnlAccuracy.add(accuracyPanel);
      HorizontalLabelPanel wealthPanel = this.createCellElement();
      this.wealthPanelList.add(wealthPanel);
      this.pnlWealth.add(wealthPanel);
    }
  }

  /**
   * 创建一个多标签面板
   * 
   * @return 新创建的多标签面板
   */
  private HorizontalLabelPanel createCellElement() {
    HorizontalLabelPanel panel = new HorizontalLabelPanel(ITEM_COUNT);
    panel.setAlignmentByIndex(0, LabelAlignment.X_CENTER);
    panel.setAlignmentByIndex(1, LabelAlignment.X_LEFT);
    panel.setAlignmentByIndex(2, LabelAlignment.X_CENTER);
    return panel;
  }

  private void updateCurrentPanel() {
    int index = this.tpnMain.getSelectedIndex();
    switch (index) {
      case 0:
        this.updateScorePanel();
        break;
      case 1:
        this.updateBarrierPanel();
        break;
      case 2:
        this.updateSpeedPanel();
        break;
      case 3:
        this.updateAccuracyPanel();
        break;
      case 4:
        this.updateWealthPanel();
        break;
    }
  }

  private void updateScorePanel() {
    ArrayList<User> userList = Util.setting.userList;
    int userCount = userList.size();
    if (userCount > 1) {
      Collections.sort(userList, new Comparator<User>() {
        @Override
        public int compare(User user1, User user2) {
          int totalScore1 = user1.idiomCache.getTotalScore();
          int totalScore2 = user2.idiomCache.getTotalScore();
          return totalScore2 - totalScore1;
        }
      });
      if (userCount >= ROW_COUNT) {
        userCount = ROW_COUNT - 1;
      }
    }
    HorizontalLabelPanel panelTitle = this.scorePanelList.get(0);
    panelTitle.setStringByIndex(0, "排名");
    panelTitle.setStringByIndex(1, "账号");
    panelTitle.setStringByIndex(2, "累计分数");
    for (int i = 0; i < userCount; i++) {
      User user = userList.get(i);
      HorizontalLabelPanel panel = this.scorePanelList.get(i + 1);
      panel.setStringByIndex(0, String.valueOf(i + 1));
      panel.setStringByIndex(1, user.getUserName());
      panel.setStringByIndex(2, user.idiomCache.getTotalScore() + "分");
    }
  }

  private void updateBarrierPanel() {
    ArrayList<User> userList = Util.setting.userList;
    int userCount = userList.size();
    if (userCount > 1) {
      Collections.sort(userList, new Comparator<User>() {
        @Override
        public int compare(User user1, User user2) {
          int passedBarrierCount1 = user1.idiomCache.getPassedBarrierCount();
          int passedBarrierCount2 = user2.idiomCache.getPassedBarrierCount();
          return passedBarrierCount2 - passedBarrierCount1;
        }
      });
      if (userCount >= ROW_COUNT) {
        userCount = ROW_COUNT - 1;
      }
    }
    HorizontalLabelPanel panelTitle = this.barrierPanelList.get(0);
    panelTitle.setStringByIndex(0, "排名");
    panelTitle.setStringByIndex(1, "账号");
    panelTitle.setStringByIndex(2, "已闯关卡");
    for (int i = 0; i < userCount; i++) {
      User user = userList.get(i);
      HorizontalLabelPanel panel = this.barrierPanelList.get(i + 1);
      panel.setStringByIndex(0, String.valueOf(i+1));
      panel.setStringByIndex(1, user.getUserName());
      panel.setStringByIndex(2, user.idiomCache.getPassedBarrierCount() + "关");
    }
  }

  private void updateSpeedPanel() {
    ArrayList<User> userList = Util.setting.userList;
    int userCount = userList.size();
    if (userCount > 1) {
      Collections.sort(userList, new Comparator<User>() {
        @Override
        public int compare(User user1, User user2) {
          float speed1 = Util.getSpeed(user1);
          float speed2 = Util.getSpeed(user2);
          if (speed1 < 0 && speed2 < 0) {
            return 0;
          } else if (speed1 < 0) {
            return 1;
          } else if (speed2 < 0) {
            return -1;
          } else {
            return (int)((speed1 - speed2) * 100);
          }
        }
      });
      if (userCount >= ROW_COUNT) {
        userCount = ROW_COUNT - 1;
      }
    }
    HorizontalLabelPanel panelTitle = this.speedPanelList.get(0);
    panelTitle.setStringByIndex(0, "排名");
    panelTitle.setStringByIndex(1, "账号");
    panelTitle.setStringByIndex(2, "闯关速度");
    for (int i = 0; i < userCount; i++) {
      User user = userList.get(i);
      HorizontalLabelPanel panel = this.speedPanelList.get(i + 1);
      panel.setStringByIndex(0, String.valueOf(i+1));
      panel.setStringByIndex(1, user.getUserName());
      float speed = Util.getSpeed(user);
      if (speed < 0) {
        panel.setStringByIndex(2, "--");
      } else {
        panel.setStringByIndex(2, speed + "秒/关");
      }
    }
  }

  private void updateAccuracyPanel() {
    ArrayList<User> userList = Util.setting.userList;
    int userCount = userList.size();
    if (userCount > 1) {
      Collections.sort(userList, new Comparator<User>() {
        @Override
        public int compare(User user1, User user2) {
          float accuracy1 = Util.getAccuracy(user1);
          float accuracy2 = Util.getAccuracy(user2);
          return (int)((accuracy2 - accuracy1) * 100);
        }
      });
      if (userCount >= ROW_COUNT) {
        userCount = ROW_COUNT - 1;
      }
    }
    HorizontalLabelPanel panelTitle = this.accuracyPanelList.get(0);
    panelTitle.setStringByIndex(0, "排名");
    panelTitle.setStringByIndex(1, "账号");
    panelTitle.setStringByIndex(2, "正确率");
    for (int i = 0; i < userCount; i++) {
      User user = userList.get(i);
      HorizontalLabelPanel panel = this.accuracyPanelList.get(i + 1);
      panel.setStringByIndex(0, String.valueOf(i+1));
      panel.setStringByIndex(1, user.getUserName());
      float accuracy = Util.getAccuracy(user);
      if (accuracy < 0) {
        panel.setStringByIndex(2, "--");
      } else {
        panel.setStringByIndex(2, accuracy + "%");
      }
    }
  }

  private void updateWealthPanel() {
    ArrayList<User> userList = Util.setting.userList;
    int userCount = userList.size();
    if (userCount > 1) {
      Collections.sort(userList, new Comparator<User>() {
        @Override
        public int compare(User user1, User user2) {
          int wealthCount1 = getWealthCount(user1);
          int wealthCount2 = getWealthCount(user2);
          return wealthCount2 - wealthCount1;
        }
      });
      if (userCount >= ROW_COUNT) {
        userCount = ROW_COUNT - 1;
      }
    }
    HorizontalLabelPanel panelTitle = this.wealthPanelList.get(0);
    panelTitle.setStringByIndex(0, "排名");
    panelTitle.setStringByIndex(1, "账号");
    panelTitle.setStringByIndex(2, "功能卡数量");
    for (int i = 0; i < userCount; i++) {
      User user = userList.get(i);
      HorizontalLabelPanel panel = this.wealthPanelList.get(i + 1);
      panel.setStringByIndex(0, String.valueOf(i+1));
      panel.setStringByIndex(1, user.getUserName());
      panel.setStringByIndex(2, this.getWealthCount(user) + "张");
    }
  }

  private int getWealthCount(User user) {
    int hintCount = user.idiomCache.getHintCount();
    int pauseCount = user.idiomCache.getPauseCount();
    int delayCount = user.idiomCache.getDelayCount();
    int energyCount = user.idiomCache.getEnergyCount();
    return hintCount + pauseCount + delayCount + energyCount;
  }

  /**
   * 添加事件监听器
   */
  private void addListeners() {
    this.tpnMain.addChangeListener(this);
    this.tpnMain.addKeyListener(this.keyAdapter);
    this.pnlScore.addKeyListener(this.keyAdapter);
    this.pnlBarrier.addKeyListener(this.keyAdapter);
    this.pnlSpeed.addKeyListener(this.keyAdapter);
    this.pnlAccuracy.addKeyListener(this.keyAdapter);
    this.pnlWealth.addKeyListener(this.keyAdapter);
  }

  /**
   * 为各组件添加事件的处理方法
   */
  @Override
  public void actionPerformed(ActionEvent e) {
    Object source = e.getSource();
    
  }

  /**
   * 当选项卡改变当前视图时调用
   */
  @Override
  public void stateChanged(ChangeEvent e) {
    this.updateCurrentPanel();
  }

  /**
   * 默认的"确定"操作方法
   */
  @Override
  public void onEnter() {
    this.dispose();
  }

  /**
   * 默认的"取消"操作方法
   */
  @Override
  public void onCancel() {
    this.dispose();
  }
}
