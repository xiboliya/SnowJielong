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

package com.xiboliya.snowjielong.dialog;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.border.EtchedBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import com.xiboliya.snowjielong.base.BaseDialog;
import com.xiboliya.snowjielong.base.BaseKeyAdapter;
import com.xiboliya.snowjielong.base.BaseLabel;
import com.xiboliya.snowjielong.common.IdiomCache;
import com.xiboliya.snowjielong.setting.Setting;
import com.xiboliya.snowjielong.util.Util;

/**
 * "仓库"对话框
 * 
 * @author 冰原
 */
public class DepositoryDialog extends BaseDialog implements ActionListener, ChangeListener, FocusListener {
  private static final long serialVersionUID = 1L;
  // 软件参数配置类
  private Setting setting = null;
  private JTabbedPane tpnMain = new JTabbedPane();
  private JPanel pnlMain = (JPanel) this.getContentPane();
  private GridLayout gridLayout = new GridLayout(9, 12, 0, 0);
  private EtchedBorder etchedBorder = new EtchedBorder();
  private JPanel pnlTool = new JPanel(this.gridLayout);
  private JPanel pnlStar = new JPanel(this.gridLayout);
  private BaseKeyAdapter keyAdapter = new BaseKeyAdapter(this);
  private BaseKeyAdapter buttonKeyAdapter = new BaseKeyAdapter(this, false);
  private MouseAdapter mouseAdapter = null;
  private ArrayList<BaseLabel> toolLabelList = new ArrayList<BaseLabel>();
  private ArrayList<BaseLabel> starLabelList = new ArrayList<BaseLabel>();

  /**
   * 构造方法
   * 
   * @param owner 用于显示该对话框的父组件
   * @param modal 是否为模式对话框
   * @param setting 软件参数配置类
   */
  public DepositoryDialog(JFrame owner, boolean modal, Setting setting) {
    super(owner, modal);
    this.setting = setting;
    this.init();
    this.addListeners();
    this.createElements();
    this.setSize(Util.getSize(500), Util.getSize(400));
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
    this.setTitle("仓库");
    this.tpnMain.add(this.pnlTool, "功能卡");
    this.tpnMain.add(this.pnlStar, "群星图");
    this.tpnMain.setSelectedIndex(0);
    this.tpnMain.setFocusable(false);
    this.pnlMain.add(this.tpnMain, BorderLayout.CENTER);
  }

  /**
   * 创建题目、选项区的所有标签
   */
  private void createElements() {
    for (int index = 0; index < 108; index++) {
      BaseLabel lblElement = this.createCellElement();
      this.toolLabelList.add(lblElement);
      this.pnlTool.add(lblElement);
    }
    for (int index = 0; index < 108; index++) {
      BaseLabel lblElement = this.createCellElement();
      this.starLabelList.add(lblElement);
      this.pnlStar.add(lblElement);
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
    lblElement.addFocusListener(this);
    lblElement.addMouseListener(this.mouseAdapter);
    return lblElement;
  }

  private void updateCurrentPanel() {
    if (this.tpnMain.getSelectedIndex() == 0) {
      this.updateToolPanel();
    } else if (this.tpnMain.getSelectedIndex() == 1) {
      this.updateStarPanel();
    }
  }

  private void updateToolPanel() {
    IdiomCache idiomCache = this.setting.user.idiomCache;
    // 提示卡
    int hintCount = idiomCache.getHintCount();
    BaseLabel lblCellHint = this.toolLabelList.get(0);
    lblCellHint.setBackground(Color.WHITE);
    if (hintCount > 0) {
      lblCellHint.setIcon(Util.ICON_HINT);
      lblCellHint.setFocusable(true); // 设置标签可以获得焦点
      lblCellHint.setToolTipText("提示卡：" + hintCount);
    } else {
      lblCellHint.setIcon(null);
      lblCellHint.setFocusable(false); // 设置标签不可以获得焦点
      lblCellHint.setToolTipText(null);
    }
    // 暂停卡
    int pauseCount = idiomCache.getPauseCount();
    BaseLabel lblCellPause = this.toolLabelList.get(1);
    lblCellPause.setBackground(Color.WHITE);
    if (pauseCount > 0) {
      lblCellPause.setIcon(Util.ICON_PAUSE);
      lblCellPause.setFocusable(true); // 设置标签可以获得焦点
      lblCellPause.setToolTipText("暂停卡：" + pauseCount);
    } else {
      lblCellPause.setIcon(null);
      lblCellPause.setFocusable(false); // 设置标签不可以获得焦点
      lblCellPause.setToolTipText(null);
    }
    // 延时卡
    int delayCount = idiomCache.getDelayCount();
    BaseLabel lblCellDelay = this.toolLabelList.get(2);
    lblCellDelay.setBackground(Color.WHITE);
    if (delayCount > 0) {
      lblCellDelay.setIcon(Util.ICON_DELAY);
      lblCellDelay.setFocusable(true); // 设置标签可以获得焦点
      lblCellDelay.setToolTipText("延时卡：" + delayCount);
    } else {
      lblCellDelay.setIcon(null);
      lblCellDelay.setFocusable(false); // 设置标签不可以获得焦点
      lblCellDelay.setToolTipText(null);
    }
    // 体力卡
    int energyCount = idiomCache.getEnergyCount();
    BaseLabel lblCellEnergy = this.toolLabelList.get(3);
    lblCellEnergy.setBackground(Color.WHITE);
    if (energyCount > 0) {
      lblCellEnergy.setIcon(Util.ICON_ENERGY);
      lblCellEnergy.setFocusable(true); // 设置标签可以获得焦点
      lblCellEnergy.setToolTipText("体力卡：" + energyCount);
    } else {
      lblCellEnergy.setIcon(null);
      lblCellEnergy.setFocusable(false); // 设置标签不可以获得焦点
      lblCellEnergy.setToolTipText(null);
    }
  }

  private void updateStarPanel() {
    HashMap<String, Integer> starMap = this.setting.user.idiomCache.getStarMap();
    int size = this.starLabelList.size();
    for (int i = 0; i < size; i++) {
      BaseLabel lblElement = this.starLabelList.get(i);
      lblElement.setBackground(Color.WHITE);
      String name = Util.STAR_NAMES[i];
      Integer count = starMap.get(name);
      if (count == null) {
        lblElement.setIcon(null);
        lblElement.setFocusable(false); // 设置标签不可以获得焦点
        lblElement.setToolTipText(null);
      } else {
        if (i < 36) {
          lblElement.setIcon(Util.ICON_STAR_1);
        } else {
          lblElement.setIcon(Util.ICON_STAR_2);
        }
        lblElement.setFocusable(true); // 设置标签可以获得焦点
        lblElement.setToolTipText(name + "：" + count);
      }
    }
  }

  /**
   * 添加事件监听器
   */
  private void addListeners() {
    this.tpnMain.addChangeListener(this);
    this.mouseAdapter = new MouseAdapter() {
      public void mouseClicked(MouseEvent e) {
        BaseLabel lblElement = (BaseLabel) e.getSource();
        lblElement.requestFocus(); // 当鼠标单击时，获得焦点
      }
    };
  }

  /**
   * 刷新标签背景色
   * @param labelList 标签列表
   * @param lblElement 当前选择的标签
   * @param elementSize 刷新的标签数量
   */
  private void refreshBackground(ArrayList<BaseLabel> labelList, BaseLabel lblElement, int elementSize) {
    for (int i = 0; i < elementSize; i++) {
      BaseLabel lblTool = labelList.get(i);
      if (lblTool.equals(lblElement)) {
        lblTool.setBackground(Color.PINK);
      } else {
        lblTool.setBackground(Color.WHITE);
      }
    }
  }

  /**
   * 为各组件添加事件的处理方法
   */
  @Override
  public void actionPerformed(ActionEvent e) {
  }

  /**
   * 当选项卡改变当前视图时调用
   */
  @Override
  public void stateChanged(ChangeEvent e) {
    this.updateCurrentPanel();
  }

  /**
   * 当标签获得焦点时，将触发此事件
   */
  @Override
  public void focusGained(FocusEvent e) {
    BaseLabel lblElement = (BaseLabel) e.getSource();
    if (this.toolLabelList.contains(lblElement)) {
      this.refreshBackground(this.toolLabelList, lblElement, 4);
    } else if (this.starLabelList.contains(lblElement)) {
      this.refreshBackground(this.starLabelList, lblElement, 108);
    }
  }

  /**
   * 当标签失去焦点时，将触发此事件
   */
  @Override
  public void focusLost(FocusEvent e) {
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
