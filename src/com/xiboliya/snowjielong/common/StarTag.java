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

/**
 * 群星图格子属性类
 * 
 * @author 冰原
 * 
 */
public class StarTag {
  // 当前星的名称
  private String name;
  // 当前星是否为天罡星
  private boolean isTian;
  // 当前星的数量
  private int count;

  /**
   * 构造方法
   */
  public StarTag() {
  }

  /**
   * 构造方法
   * 
   * @param name 当前星的名称
   * @param isTian 当前星是否为天罡星
   * @param count 当前星的数量
   */
  public StarTag(String name, boolean isTian, int count) {
    this.setName(name);
    this.setTian(isTian);
    this.setCount(count);
  }

  /**
   * 获取当前星的名称
   * 
   * @return 当前星的名称
   */
  public String getName() {
    return this.name;
  }

  /**
   * 获取当前星是否为天罡星
   * 
   * @return 当前星是否为天罡星
   */
  public boolean isTian() {
    return this.isTian;
  }

  /**
   * 获取当前星的数量
   * 
   * @return 当前星的数量
   */
  public int getCount() {
    return this.count;
  }

  /**
   * 设置当前星的名称
   * 
   * @param name 当前星的名称
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * 设置当前星是否为天罡星
   * 
   * @param isTian 当前星是否为天罡星
   */
  public void setTian(boolean isTian) {
    this.isTian = isTian;
  }

  /**
   * 设置当前星的数量
   * 
   * @param count 当前星的数量
   */
  public void setCount(int count) {
    this.count = count;
  }

}
