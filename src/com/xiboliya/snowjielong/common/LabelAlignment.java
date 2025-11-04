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

package com.xiboliya.snowjielong.common;

import javax.swing.SwingConstants;

/**
 * 用于标签对齐方式的枚举
 * 
 * @author 冰原
 * 
 */
public enum LabelAlignment {
  /**
   * 水平方向：中间对齐
   */
  X_CENTER,
  /**
   * 水平方向：左对齐
   */
  X_LEFT,
  /**
   * 水平方向：右对齐
   */
  X_RIGHT,
  /**
   * 垂直方向：中间对齐
   */
  Y_CENTER,
  /**
   * 垂直方向：顶部对齐
   */
  Y_TOP,
  /**
   * 垂直方向：底部对齐
   */
  Y_BOTTOM;

  /**
   * 获取对齐方式的值
   * 
   * @return 对齐方式的值
   */
  public int getValue() {
    switch (this) {
    case X_CENTER:
    case Y_CENTER:
      return SwingConstants.CENTER;
    case X_LEFT:
      return SwingConstants.LEFT;
    case X_RIGHT:
      return SwingConstants.RIGHT;
    case Y_TOP:
      return SwingConstants.TOP;
    case Y_BOTTOM:
      return SwingConstants.BOTTOM;
    default:
      return SwingConstants.CENTER;
    }
  }
}
