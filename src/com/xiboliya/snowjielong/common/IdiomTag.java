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
 * 空格子属性类
 * 
 * @author 冰原
 * 
 */
public class IdiomTag {
  // 当前空格子的答案文本
  private String content;
  // 当前空格子是否选中
  private boolean isFocused;

  /**
   * 构造方法
   */
  public IdiomTag() {
  }

  /**
   * 构造方法
   * 
   * @param content 当前空格子的答案文本
   * @param isFocused 当前空格子是否选中
   */
  public IdiomTag(String content, boolean isFocused) {
    this.setContent(content);
    this.setFocused(isFocused);
  }

  /**
   * 获取当前空格子的答案文本
   * 
   * @return 当前空格子的答案文本
   */
  public String getContent() {
    return this.content;
  }

  /**
   * 获取当前空格子是否选中
   * 
   * @return 当前空格子是否选中
   */
  public boolean isFocused() {
    return this.isFocused;
  }

  /**
   * 设置当前空格子的答案文本
   * 
   * @param content 当前空格子的答案文本
   */
  public void setContent(String content) {
    this.content = content;
  }

  /**
   * 设置当前空格子是否选中
   * 
   * @param isFocused 当前空格子是否选中
   */
  public void setFocused(boolean isFocused) {
    this.isFocused = isFocused;
  }
}
