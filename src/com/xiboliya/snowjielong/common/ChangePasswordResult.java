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
 * 用于表示修改密码结果的枚举
 * 
 * @author 冰原
 * 
 */
public enum ChangePasswordResult {
  /**
   * 原密码为空
   */
  OLD_PASSWORD_EMPTY,
  /**
   * 新密码为空
   */
  PASSWORD_EMPTY,
  /**
   * 确认新密码为空
   */
  PASSWORD_AGAIN_EMPTY,
  /**
   * 原密码错误
   */
  OLD_PASSWORD_WRONG,
  /**
   * 新密码与原密码相同
   */
  PASSWORD_AND_OLD_PASSWORD_SAME,
  /**
   * 新密码超过20个字符
   */
  PASSWORD_MORE_THAN_20_CHARS,
  /**
   * 新密码中含有特殊字符
   */
  PASSWORD_HAS_SPECIAL_CHAR,
  /**
   * 新密码与确认新密码不相同
   */
  PASSWORD_AND_PASSWORD_AGAIN_DIFFERENT,
  /**
   * 修改密码成功
   */
  SUCCESS;

  /**
   * 重写父类的方法
   */
  public String toString() {
    switch (this) {
    case OLD_PASSWORD_EMPTY:
      return "原密码不能为空！";
    case PASSWORD_EMPTY:
      return "新密码不能为空！";
    case PASSWORD_AGAIN_EMPTY:
      return "确认新密码不能为空！";
    case OLD_PASSWORD_WRONG:
      return "原密码错误！";
    case PASSWORD_AND_OLD_PASSWORD_SAME:
      return "新密码与原密码不能相同！";
    case PASSWORD_MORE_THAN_20_CHARS:
      return "新密码不能超过20个字符！";
    case PASSWORD_HAS_SPECIAL_CHAR:
      return "新密码中不能含有空格、制表符、回车符等特殊字符！";
    case PASSWORD_AND_PASSWORD_AGAIN_DIFFERENT:
      return "确认新密码与新密码必须相同！";
    case SUCCESS:
      return "修改密码成功！";
    default:
      return "";
    }
  }

  /**
   * 修改密码是否成功
   * @return 修改密码是否成功，true表示成功，false反之
   */
  public boolean isSuccess() {
    switch (this) {
    case SUCCESS:
      return true;
    default:
      return false;
    }
  }
}
