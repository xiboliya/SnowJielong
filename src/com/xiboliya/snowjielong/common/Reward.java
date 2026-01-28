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

package com.xiboliya.snowjielong.common;

/**
 * 用于表示奖励类型的枚举
 * 
 * @author 冰原
 * 
 */
public enum Reward {
  /**
   * 提示卡
   */
  HINT("提示卡"),
  /**
   * 暂停卡
   */
  PAUSE("暂停卡"),
  /**
   * 延时卡
   */
  DELAY("延时卡"),
  /**
   * 体力卡
   */
  ENERGY("体力卡"),
  /**
   * 天星
   */
  STAR("");

  // 枚举元素名称
  private String name;

  /**
   * 构造方法
   * @param name 枚举元素名称
   */
  private Reward(String name) {
    this.name = name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getName() {
    return this.name;
  }

}
