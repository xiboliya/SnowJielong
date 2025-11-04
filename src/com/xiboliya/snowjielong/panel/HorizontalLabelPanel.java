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

package com.xiboliya.snowjielong.panel;

import java.awt.GridLayout;
import java.util.LinkedList;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import com.xiboliya.snowjielong.common.LabelAlignment;
import com.xiboliya.snowjielong.util.Util;

/**
 * 自定义的水平方向多标签面板
 * 
 * @author 冰原
 * 
 */
public class HorizontalLabelPanel extends JPanel {
  private static final long serialVersionUID = 1L;
  private int count = 1; // 标签的数目
  private GridLayout layout = new GridLayout(1, count); // 面板的布局管理器
  private LinkedList<JLabel> labelList = new LinkedList<JLabel>(); // 存放标签的链表

  public HorizontalLabelPanel() {
    this(1);
  }

  public HorizontalLabelPanel(int count) {
    this.checkCount(count);
    this.init();
  }

  public HorizontalLabelPanel(String[] arrStrLabel) {
    this(arrStrLabel.length);
    if (arrStrLabel.length > 0) {
      for (int i = 0; i < this.count; i++) {
        this.labelList.get(i).setText(arrStrLabel[i]);
      }
    }
  }

  /**
   * 初始化状态栏
   */
  private void init() {
    this.setLayout(this.layout);
    this.resetLabelList();
    this.setVisible(true);
  }

  /**
   * 重置链表
   */
  private void resetLabelList() {
    this.labelList.clear();
    for (int i = 0; i < this.count; i++) {
      this.appendLabelList();
    }
  }

  /**
   * 追加一个空白标签
   * 
   * @return 如果追加成功则返回true，否则返回false
   */
  private boolean appendLabelList() {
    if (this.labelList.size() < this.count) {
      JLabel lblTemp = new JLabel(" ");
      this.labelList.add(lblTemp);
      this.add(lblTemp);
      return true;
    }
    return false;
  }

  /**
   * 为指定下标的标签设置字符串和对齐方式
   * 
   * @param index 标签的下标
   * @param strLabel 设置的字符串
   * @param alignment 对齐方式
   */
  public void setStringByIndex(int index, String strLabel, LabelAlignment alignment) {
    this.setStringByIndex(index, strLabel);
    this.setAlignmentByIndex(index, alignment);
  }

  /**
   * 为指定下标的标签设置字符串
   * 
   * @param index 标签的下标
   * @param strLabel 设置的字符串
   */
  public void setStringByIndex(int index, String strLabel) {
    this.labelList.get(this.checkIndex(index)).setText(strLabel);
  }

  /**
   * 获取指定下标的标签的字符串
   * 
   * @param index 标签的下标
   * @return 标签的字符串
   */
  public String getStringByIndex(int index) {
    return this.labelList.get(this.checkIndex(index)).getText();
  }

  /**
   * 格式化下标
   * 
   * @param index 下标
   * @return 经过格式化的下标
   */
  private int checkIndex(int index) {
    if (index < 0) {
      index = 0;
    } else if (index >= this.labelList.size()) {
      index = this.labelList.size() - 1;
    }
    return index;
  }

  /**
   * 格式化标签数目
   * 
   * @param count 标签的数目
   * @return 经过格式化的标签的数目
   */
  private int checkCount(int count) {
    if (count <= 0) {
      this.count = 1;
    } else {
      this.count = count;
    }
    return this.count;
  }

  /**
   * 获取标签的数目
   * 
   * @return 标签的数目
   */
  public int getCount() {
    return this.count;
  }

  /**
   * 为指定下标的标签设置对齐方式
   * 
   * @param index 标签的下标
   * @param alignment 标签的对齐方式
   */
  public void setAlignmentByIndex(int index, LabelAlignment alignment) {
    JLabel lblTemp = this.labelList.get(this.checkIndex(index));
    switch (alignment) {
    case X_CENTER:
    case X_LEFT:
    case X_RIGHT:
      lblTemp.setHorizontalAlignment(alignment.getValue());
      break;
    case Y_CENTER:
    case Y_TOP:
    case Y_BOTTOM:
      lblTemp.setVerticalAlignment(alignment.getValue());
      break;
    }
  }
}
