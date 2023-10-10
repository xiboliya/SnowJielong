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
import javax.swing.ImageIcon;
import javax.swing.JButton;
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
  private JPanel pnlTreasure = new JPanel(this.gridLayout);
  private JPanel pnlDanMedicine = new JPanel(this.gridLayout);
  private BaseKeyAdapter keyAdapter = new BaseKeyAdapter(this);
  private BaseKeyAdapter buttonKeyAdapter = new BaseKeyAdapter(this, false);
  private MouseAdapter mouseAdapter = null;
  private ImageIcon iconHint = new ImageIcon(ClassLoader.getSystemResource("res/hint.png")); // 提示卡图标
  private ArrayList<BaseLabel> toolLabelList = new ArrayList<BaseLabel>();
  private ArrayList<BaseLabel> starLabelList = new ArrayList<BaseLabel>();
  private ArrayList<BaseLabel> treasureLabelList = new ArrayList<BaseLabel>();
  private ArrayList<BaseLabel> danMedicineLabelList = new ArrayList<BaseLabel>();

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
    this.setSize(500, 400);
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
    this.tpnMain.add(this.pnlTreasure, "百宝箱");
    this.tpnMain.add(this.pnlDanMedicine, "丹药匣");
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
    for (int index = 0; index < 108; index++) {
      BaseLabel lblElement = this.createCellElement();
      this.treasureLabelList.add(lblElement);
      this.pnlTreasure.add(lblElement);
    }
    for (int index = 0; index < 108; index++) {
      BaseLabel lblElement = this.createCellElement();
      this.danMedicineLabelList.add(lblElement);
      this.pnlDanMedicine.add(lblElement);
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
      IdiomCache idiomCache = this.setting.idiomCache;
      int hintCount = idiomCache.getHintCount();
      // 提示卡
      BaseLabel lblElement = this.toolLabelList.get(0);
      if (hintCount > 0) {
        lblElement.setIcon(this.iconHint);
        lblElement.setFocusable(true); // 设置标签可以获得焦点
        lblElement.setToolTipText("提示卡：" + hintCount);
      } else {
        lblElement.setIcon(null);
        lblElement.setFocusable(false); // 设置标签不可以获得焦点
        lblElement.setToolTipText(null);
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
      lblElement.setBackground(Color.PINK);
    }
  }

  /**
   * 当标签失去焦点时，将触发此事件
   */
  @Override
  public void focusLost(FocusEvent e) {
    BaseLabel lblElement = (BaseLabel) e.getSource();
    if (this.toolLabelList.contains(lblElement)) {
      lblElement.setBackground(Color.WHITE);
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
