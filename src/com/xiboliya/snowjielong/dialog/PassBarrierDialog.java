/**
 * Copyright (C) 2026 冰原
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

import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import com.xiboliya.snowjielong.base.BaseButton;
import com.xiboliya.snowjielong.base.BaseDialog;
import com.xiboliya.snowjielong.base.BaseKeyAdapter;
import com.xiboliya.snowjielong.common.Reward;
import com.xiboliya.snowjielong.frame.SnowJielongFrame;
import com.xiboliya.snowjielong.util.Util;

/**
 * "通过关卡"对话框
 * 
 * @author 冰原
 */
public class PassBarrierDialog extends BaseDialog implements ActionListener {
  private static final long serialVersionUID = 1L;
  private static final int MAX_IDIOMS_PER_ROW = 3;
  private static final String PROPERTY_KEY = "Property";
  private JPanel pnlMain = (JPanel) this.getContentPane();
  private JLabel lblBarrier = new JLabel();
  private JLabel lblScore = new JLabel();
  private JLabel lblReward = new JLabel();
  private GridLayout gridLayout = new GridLayout(1, MAX_IDIOMS_PER_ROW, Util.getSize(10), Util.getSize(10));
  private JPanel pnlIdiom = new JPanel(this.gridLayout);
  private JLabel lblInfo = new JLabel("点击成语可查看解释");
  private BaseButton btnOk = new BaseButton("确定");
  private BaseButton btnNext = new BaseButton("下一关");
  private BaseKeyAdapter buttonKeyAdapter = new BaseKeyAdapter(this, false);

  /**
   * 构造方法
   * 
   * @param owner 用于显示该对话框的父组件
   * @param modal 是否为模式对话框
   */
  public PassBarrierDialog(JFrame owner, boolean modal) {
    super(owner, modal);
    this.init();
    this.addListeners();
    this.setSize(Util.getSize(360), Util.getSize(300));
    this.setVisible(true);
  }

  /**
   * 重写父类的方法：设置本窗口是否可见
   */
  @Override
  public void setVisible(boolean visible) {
    if (visible) {
      this.updateView();
    }
    super.setVisible(visible);
  }

  /**
   * 初始化界面
   */
  private void init() {
    this.setTitle("通过关卡");
    this.pnlMain.setLayout(null);
    this.lblBarrier.setBounds(Util.getSize(20), Util.getSize(20), Util.getSize(320), Util.getSize(Util.VIEW_HEIGHT));
    this.lblScore.setBounds(Util.getSize(20), Util.getSize(45), Util.getSize(320), Util.getSize(Util.VIEW_HEIGHT));
    this.lblReward.setBounds(Util.getSize(20), Util.getSize(70), Util.getSize(320), Util.getSize(Util.VIEW_HEIGHT));
    this.pnlIdiom.setBounds(Util.getSize(20), Util.getSize(100), Util.getSize(320), Util.getSize(80));
    this.lblInfo.setBounds(Util.getSize(20), Util.getSize(190), Util.getSize(320), Util.getSize(Util.VIEW_HEIGHT));
    this.btnOk.setBounds(Util.getSize(50), Util.getSize(220), Util.getSize(100), Util.getSize(Util.BUTTON_HEIGHT));
    this.btnNext.setBounds(Util.getSize(210), Util.getSize(220), Util.getSize(100), Util.getSize(Util.BUTTON_HEIGHT));
    this.lblBarrier.setHorizontalAlignment(SwingConstants.CENTER);
    this.lblScore.setHorizontalAlignment(SwingConstants.CENTER);
    this.lblReward.setHorizontalAlignment(SwingConstants.CENTER);
    this.lblInfo.setHorizontalAlignment(SwingConstants.CENTER);
    this.pnlMain.add(this.lblBarrier);
    this.pnlMain.add(this.lblScore);
    this.pnlMain.add(this.lblReward);
    this.pnlMain.add(this.pnlIdiom);
    this.pnlMain.add(this.lblInfo);
    this.pnlMain.add(this.btnOk);
    this.pnlMain.add(this.btnNext);
  }

  /**
   * 刷新页面显示
   */
  private void updateView() {
    int currentBarrier = Util.setting.user.idiomCache.getCurrentBarrier();
    int currentTopicLevel = Util.setting.user.idiomCache.getCurrentTopicLevel();
    this.lblBarrier.setText("恭喜通过" + Util.TOPIC_LEVEL_NAME[currentTopicLevel] + "第" + (currentBarrier + 1) + "关！");
    if (this.isRankLevelChanged()) {
      this.lblScore.setText("加" + Util.currentScore + "分！恭喜头衔升级为：" + Util.RANK_LEVEL_NAME[Util.currentRankLevel] + "！");
    } else {
      this.lblScore.setText("加" + Util.currentScore + "分！");
    }
    if (Util.currentReward != null) {
      String unit;
      if (Util.currentReward.equals(Reward.STAR)) {
        unit = "颗";
      } else {
        unit = "张";
      }
      this.lblReward.setText("获得1" + unit + Util.currentReward.getName() + "！");
    } else {
      this.lblReward.setText("未获得随机奖励，再接再厉！");
    }
    this.updateIdiomElements();
    if (Util.isPassedAllBarrier()) {
      if (Util.hasNextTopicLevel()) {
        this.btnNext.setText("下一等级");
        this.btnNext.putClientProperty(PROPERTY_KEY, 1);
      } else {
        this.btnNext.setText("已通关");
        this.btnNext.putClientProperty(PROPERTY_KEY, 2);
      }
    } else {
      this.btnNext.setText("下一关");
      this.btnNext.putClientProperty(PROPERTY_KEY, 0);
    }
  }

  /**
   * 判断头衔等级是否变化
   * @return 头衔等级是否变化
   */
  private boolean isRankLevelChanged() {
    if (Util.currentScore <= 0) {
      return false;
    }
    int totalScore = Util.setting.user.idiomCache.getTotalScore();
    int oldRankLevel = Util.getRankLevel(totalScore - Util.currentScore);
    return oldRankLevel != Util.currentRankLevel;
  }

  /**
   * 更新所有成语按钮
   */
  private void updateIdiomElements() {
    int size = Util.currentIdiomList.size();
    if (size <= 0) {
      return;
    }
    int rowCount = size / MAX_IDIOMS_PER_ROW;
    int mod = size % MAX_IDIOMS_PER_ROW;
    if (rowCount >= 1 && mod > 0) {
      rowCount++;
    } else if (rowCount < 1) {
      rowCount = 1;
    }
    int columnCount = MAX_IDIOMS_PER_ROW;
    if (rowCount == 1 && mod > 0) {
      columnCount = mod;
    }
    if (this.gridLayout.getRows() != rowCount) {
      this.gridLayout.setRows(rowCount);
    }
    if (this.gridLayout.getColumns() != columnCount) {
      this.gridLayout.setColumns(columnCount);
    }
    int elementCount = this.pnlIdiom.getComponentCount();
    if (elementCount != size) {
      this.pnlIdiom.removeAll();
      for (String idiom : Util.currentIdiomList) {
        BaseButton btnElement = this.createElement(idiom);
        this.pnlIdiom.add(btnElement);
      }
    } else {
      Component[] elements = this.pnlIdiom.getComponents();
      for (int i = 0; i < size; i++) {
        BaseButton btnElement = (BaseButton)elements[i];
        String text = Util.currentIdiomList.get(i);
        btnElement.setText(text);
        btnElement.putClientProperty(PROPERTY_KEY, text);
      }
    }
  }

  /**
   * 创建成语按钮
   */
  private BaseButton createElement(String text) {
    BaseButton btnElement = new BaseButton(text);
    btnElement.putClientProperty(PROPERTY_KEY, text);
    btnElement.addActionListener(this);
    btnElement.setFocusable(false);
    return btnElement;
  }

  /**
   * 开始下一关
   */
  private void startNextBarrier() {
    int property = (Integer)this.btnNext.getClientProperty(PROPERTY_KEY);
    if (property == 1) {
      int currentTopicLevel = Util.setting.user.idiomCache.getCurrentTopicLevel();
      String topicLevel = Util.TOPIC_LEVEL_NAME[currentTopicLevel + 1];
      JOptionPane.showMessageDialog(this, "恭喜您已进入" + topicLevel + "！", Util.SOFTWARE, JOptionPane.CANCEL_OPTION);
    }
    ((SnowJielongFrame) this.getOwner()).start();
    this.onCancel();
  }

  /**
   * 显示成语解释
   */
  private void showDefinition(String idiom) {
    String definition = Util.currentTopicDefinitionMap.get(idiom);
    if (Util.isTextEmpty(definition)) {
      JOptionPane.showMessageDialog(this, "成语：" + idiom + "\n解释：暂缺！", Util.SOFTWARE, JOptionPane.QUESTION_MESSAGE);
    } else {
      JOptionPane.showMessageDialog(this, "成语：" + idiom + "\n" + Util.convertToMsg("解释：" + definition), Util.SOFTWARE, JOptionPane.QUESTION_MESSAGE);
    }
  }

  /**
   * 添加事件监听器
   */
  private void addListeners() {
    this.btnOk.addActionListener(this);
    this.btnNext.addActionListener(this);
    this.btnOk.addKeyListener(this.buttonKeyAdapter);
    this.btnNext.addKeyListener(this.buttonKeyAdapter);
  }

  /**
   * 为各组件添加事件的处理方法
   */
  @Override
  public void actionPerformed(ActionEvent e) {
    Object source = e.getSource();
    if (this.btnOk.equals(source)) {
      this.onCancel();
    } else if (this.btnNext.equals(source)) {
      this.startNextBarrier();
    } else if (source instanceof BaseButton){
      BaseButton button = (BaseButton)source;
      String property = (String)button.getClientProperty(PROPERTY_KEY);
      if (!Util.isTextEmpty(property)) {
        this.showDefinition(property);
      }
    }
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
