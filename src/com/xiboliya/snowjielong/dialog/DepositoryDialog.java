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
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
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
import com.xiboliya.snowjielong.common.StarTag;
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
  private MouseAdapter mouseAdapter = null;
  private ArrayList<BaseLabel> toolLabelList = new ArrayList<BaseLabel>();
  private ArrayList<BaseLabel> starLabelList = new ArrayList<BaseLabel>();
  private BaseLabel currentStarLabel = null;

  private JPopupMenu popMenuMain = new JPopupMenu();
  private JMenu menuPopCompose = new JMenu("合成功能卡(C)");
  private JMenuItem itemPopComposeHint = new JMenuItem("提示卡(H)", 'H');
  private JMenuItem itemPopComposePause = new JMenuItem("暂停卡(P)", 'P');
  private JMenuItem itemPopComposeDelay = new JMenuItem("延时卡(D)", 'D');
  private JMenuItem itemPopComposeEnergy = new JMenuItem("体力卡(E)", 'E');
  private JMenuItem itemPopComposeRandom = new JMenuItem("抽取随机奖励(R)", 'R');

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
    this.addTabbedPane();
    this.initPopMenu();
  }

  /**
   * 主面板上添加选项卡视图
   */
  private void addTabbedPane() {
    this.tpnMain.add(this.pnlTool, "功能卡");
    this.tpnMain.add(this.pnlStar, "群星图");
    this.tpnMain.setSelectedIndex(0);
    this.tpnMain.setFocusable(false);
    this.pnlMain.add(this.tpnMain, BorderLayout.CENTER);
  }

  /**
   * 初始化快捷菜单
   */
  private void initPopMenu() {
    this.popMenuMain.add(this.menuPopCompose);
    this.menuPopCompose.add(this.itemPopComposeHint);
    this.menuPopCompose.add(this.itemPopComposePause);
    this.menuPopCompose.add(this.itemPopComposeDelay);
    this.menuPopCompose.add(this.itemPopComposeEnergy);
    this.popMenuMain.addSeparator();
    this.popMenuMain.add(this.itemPopComposeRandom);
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
      StarTag starTag = new StarTag(Util.STAR_NAMES[index], index < 36, 0);
      lblElement.setTag(starTag);
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
    lblElement.addKeyListener(this.keyAdapter);
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
      StarTag starTag = (StarTag)lblElement.getTag();
      String name = starTag.getName();
      Integer count = starMap.get(name);
      if (count == null || count <= 0) {
        starTag.setCount(0);
        lblElement.setIcon(null);
        lblElement.setFocusable(false); // 设置标签不可以获得焦点
        lblElement.setToolTipText(null);
      } else {
        starTag.setCount(count);
        if (starTag.isTian()) {
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
    this.pnlTool.addKeyListener(this.keyAdapter);
    this.pnlStar.addKeyListener(this.keyAdapter);
    this.itemPopComposeHint.addActionListener(this);
    this.itemPopComposePause.addActionListener(this);
    this.itemPopComposeDelay.addActionListener(this);
    this.itemPopComposeEnergy.addActionListener(this);
    this.itemPopComposeRandom.addActionListener(this);
    this.mouseAdapter = new MouseAdapter() {
      public void mouseClicked(MouseEvent e) {
        BaseLabel lblElement = (BaseLabel) e.getSource();
        lblElement.requestFocus(); // 当鼠标单击时，获得焦点
        currentStarLabel = lblElement;
        if (e.getButton() == MouseEvent.BUTTON3) { // 点击右键时，显示快捷菜单
          if (tpnMain.getSelectedIndex() == 1 && lblElement.isFocusable()) {
            setMenuState(lblElement);
            popMenuMain.show(lblElement, e.getX(), e.getY());
          }
        }
      }
    };
  }

  /**
   * 根据当前星的信息，设置相关菜单的状态
   * @param lblElement 当前星
   */
  private void setMenuState(BaseLabel lblElement) {
    StarTag starTag = (StarTag)lblElement.getTag();
    int count = starTag.getCount();
    if (starTag.isTian()) {
      // 三十六天罡：1颗星可以抽取随机奖励、2颗星可以合成功能卡
      if (count >= 2) {
        this.menuPopCompose.setEnabled(true);
        this.itemPopComposeRandom.setEnabled(true);
      } else if (count >= 1) {
        this.menuPopCompose.setEnabled(false);
        this.itemPopComposeRandom.setEnabled(true);
      } else {
        this.menuPopCompose.setEnabled(false);
        this.itemPopComposeRandom.setEnabled(false);
      }
    } else {
      // 七十二地煞：2颗星可以抽取随机奖励、3颗星可以合成功能卡
      if (count >= 3) {
        this.menuPopCompose.setEnabled(true);
        this.itemPopComposeRandom.setEnabled(true);
      } else if (count >= 2) {
        this.menuPopCompose.setEnabled(false);
        this.itemPopComposeRandom.setEnabled(true);
      } else {
        this.menuPopCompose.setEnabled(false);
        this.itemPopComposeRandom.setEnabled(false);
      }
    }
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
   * "合成功能卡"的处理方法
   * @param index 合成功能卡的索引
   */
  private void composeTool(int index) {
    String tool = "";
    switch (index) {
      case 0:
        tool = "提示卡";
        break;
      case 1:
        tool = "暂停卡";
        break;
      case 2:
        tool = "延时卡";
        break;
      case 3:
        tool = "体力卡";
        break;
    }
    StarTag starTag = (StarTag)this.currentStarLabel.getTag();
    int useCount = 3;
    if (starTag.isTian()) {
      useCount = 2;
    }
    String name = starTag.getName();
    int result = JOptionPane.showConfirmDialog(this,
        "此操作将消耗" + useCount + "颗" + name + "，合成1张" + tool + "！\n是否继续？",
        Util.SOFTWARE, JOptionPane.YES_NO_OPTION);
    if (result != JOptionPane.YES_OPTION) {
      return;
    }
    HashMap<String, Integer> starMap = this.setting.user.idiomCache.getStarMap();
    Integer count = starMap.get(name);
    count -= useCount;
    starMap.put(name, count);
    switch (index) {
      case 0:
        int hintCount = this.setting.user.idiomCache.getHintCount() + 1;
        this.setting.user.idiomCache.setHintCount(hintCount);
        break;
      case 1:
        int pauseCount = this.setting.user.idiomCache.getPauseCount() + 1;
        this.setting.user.idiomCache.setPauseCount(pauseCount);
        break;
      case 2:
        int delayCount = this.setting.user.idiomCache.getDelayCount() + 1;
        this.setting.user.idiomCache.setDelayCount(delayCount);
        break;
      case 3:
        int energyCount = this.setting.user.idiomCache.getEnergyCount() + 1;
        this.setting.user.idiomCache.setEnergyCount(energyCount);
        break;
    }
    JOptionPane.showMessageDialog(this, "成功合成1张：" + tool + "!", Util.SOFTWARE, JOptionPane.CANCEL_OPTION);
    this.updateCurrentPanel();
  }

  /**
   * "抽取随机奖励"的处理方法
   */
  private void composeRandom() {
    StarTag starTag = (StarTag)this.currentStarLabel.getTag();
    int useCount = 2;
    if (starTag.isTian()) {
      useCount = 1;
    }
    String name = starTag.getName();
    int result = JOptionPane.showConfirmDialog(this,
        "此操作将消耗" + useCount + "颗" + name + "，并有机会抽取到随机奖励！\n是否继续？",
        Util.SOFTWARE, JOptionPane.YES_NO_OPTION);
    if (result != JOptionPane.YES_OPTION) {
      return;
    }
    HashMap<String, Integer> starMap = this.setting.user.idiomCache.getStarMap();
    Integer count = starMap.get(name);
    count -= useCount;
    starMap.put(name, count);
    Random random = new Random();
    // 生成随机奖励的索引
    int index = random.nextInt(8);
    String tool = "";
    switch (index) {
      case 0:
        tool = "提示卡";
        int hintCount = this.setting.user.idiomCache.getHintCount() + 1;
        this.setting.user.idiomCache.setHintCount(hintCount);
        break;
      case 1:
        tool = "暂停卡";
        int pauseCount = this.setting.user.idiomCache.getPauseCount() + 1;
        this.setting.user.idiomCache.setPauseCount(pauseCount);
        break;
      case 2:
        tool = "延时卡";
        int delayCount = this.setting.user.idiomCache.getDelayCount() + 1;
        this.setting.user.idiomCache.setDelayCount(delayCount);
        break;
      case 3:
        tool = "体力卡";
        int energyCount = this.setting.user.idiomCache.getEnergyCount() + 1;
        this.setting.user.idiomCache.setEnergyCount(energyCount);
        break;
      default:
        JOptionPane.showMessageDialog(this, "抱歉：没有抽取到奖励！\n下次再试吧！", Util.SOFTWARE, JOptionPane.CANCEL_OPTION);
        this.updateCurrentPanel();
        return;
    }
    JOptionPane.showMessageDialog(this, "恭喜：成功抽取到1张" + tool + "!", Util.SOFTWARE, JOptionPane.CANCEL_OPTION);
    this.updateCurrentPanel();
  }

  /**
   * 为各组件添加事件的处理方法
   */
  @Override
  public void actionPerformed(ActionEvent e) {
    Object source = e.getSource();
    if (this.itemPopComposeHint.equals(source)) {
      this.composeTool(0);
    } else if (this.itemPopComposePause.equals(source)) {
      this.composeTool(1);
    } else if (this.itemPopComposeDelay.equals(source)) {
      this.composeTool(2);
    } else if (this.itemPopComposeEnergy.equals(source)) {
      this.composeTool(3);
    } else if (this.itemPopComposeRandom.equals(source)) {
      this.composeRandom();
    }
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
