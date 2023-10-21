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

package com.xiboliya.snowjielong.common;

/**
 * 用于标识关卡顺序的枚举
 * 
 * @author 冰原
 * 
 */
public enum BarrierOrder {
  /**
   * 默认升序
   */
  NATURAL_ASCEND("natural_ascend", 0),
  /**
   * 默认反序
   */
  NATURAL_DESCEND("natural_descend", 1),
  /**
   * 整个字符串的ASCII升序
   */
  WHOLE_STRING_ASCEND("whole_string_ascend", 2),
  /**
   * 整个字符串的ASCII降序
   */
  WHOLE_STRING_DESCEND("whole_string_descend", 3),
  /**
   * 每个字符的ASCII之和升序
   */
  CHARS_NUMBER_ASCEND("chars_number_ascend", 4),
  /**
   * 每个字符的ASCII之和降序
   */
  CHARS_NUMBER_DESCEND("chars_number_descend", 5),
  /**
   * 整个字符串反转后的ASCII升序
   */
  WHOLE_STRING_REVERSE_ASCEND("whole_string_reverse_ascend", 6),
  /**
   * 整个字符串反转后的ASCII降序
   */
  WHOLE_STRING_REVERSE_DESCEND("whole_string_reverse_descend", 7);
  
  // 枚举元素名称
  private String name;
  // 枚举元素索引
  private int index;

  /**
   * 构造方法
   * @param name 枚举元素名称
   * @param index 枚举元素索引
   */
  private BarrierOrder(String name, int index) {
    this.name = name;
    this.index = index;
  }

  /**
   * 通过索引获取枚举元素
   * @param index 枚举元素索引
   * @return 枚举元素
   */
  public static BarrierOrder getItemByIndex(int index) {
    for (BarrierOrder item : BarrierOrder.values()) {
      if (item.getIndex() == index) {
        return item;
      }
    }
    return NATURAL_ASCEND;
  }

  /**
   * 通过名称获取枚举元素
   * @param name 枚举元素名称
   * @return 枚举元素
   */
  public static BarrierOrder getItemByName(String name) {
    for (BarrierOrder item : BarrierOrder.values()) {
      if (item.getName().equalsIgnoreCase(name)) {
        return item;
      }
    }
    return NATURAL_ASCEND;
  }

  public String getName() {
    return this.name;
  }

  public int getIndex() {
    return this.index;
  }
}
