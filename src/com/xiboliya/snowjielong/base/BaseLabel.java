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

package com.xiboliya.snowjielong.base;

import javax.swing.JLabel;

/**
 * 自定义的标签
 * 
 * @author 冰原
 * 
 */
public class BaseLabel extends JLabel {
  private static final long serialVersionUID = 1L;
  // 自定义标识
  private Object tag = null;

  /**
   * 默认的构造方法
   */
  public BaseLabel() {
    super();
  }

  public BaseLabel(String text) {
    super(text);
  }

  public BaseLabel(int horizontalAlignment) {
    super("", horizontalAlignment);
  }

  public BaseLabel(String text, int horizontalAlignment) {
    super(text, horizontalAlignment);
  }

  public Object getTag() {
    return this.tag;
  }

  public void setTag(Object tag) {
    this.tag = tag;
  }
}
