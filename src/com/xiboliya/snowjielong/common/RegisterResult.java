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
 * 用于表示注册结果的枚举
 * 
 * @author 冰原
 * 
 */
public enum RegisterResult {
  /**
   * 账号为空
   */
  USER_NAME_EMPTY,
  /**
   * 密码为空
   */
  PASSWORD_EMPTY,
  /**
   * 确认密码为空
   */
  PASSWORD_AGAIN_EMPTY,
  /**
   * 账号中含有特殊字符
   */
  USER_NAME_HAS_SPECIAL_CHAR,
  /**
   * 密码超过20个字符
   */
  PASSWORD_MORE_THAN_20_CHARS,
  /**
   * 密码中含有特殊字符
   */
  PASSWORD_HAS_SPECIAL_CHAR,
  /**
   * 密码与确认密码不相同
   */
  PASSWORD_AND_PASSWORD_AGAIN_DIFFERENT,
  /**
   * 账号已存在
   */
  USER_NAME_ALREADY_EXIST,
  /**
   * 注册成功
   */
  REGISTER_SUCCESS;

  /**
   * 重写父类的方法
   */
  public String toString() {
    switch (this) {
    case USER_NAME_EMPTY:
      return "账号不能为空！";
    case PASSWORD_EMPTY:
      return "密码不能为空！";
    case PASSWORD_AGAIN_EMPTY:
      return "确认密码不能为空！";
    case USER_NAME_HAS_SPECIAL_CHAR:
      return "账号中不能含有空格、制表符、回车符等特殊字符！";
    case PASSWORD_MORE_THAN_20_CHARS:
      return "密码不能超过20个字符！";
    case PASSWORD_HAS_SPECIAL_CHAR:
      return "密码中不能含有空格、制表符、回车符等特殊字符！";
    case PASSWORD_AND_PASSWORD_AGAIN_DIFFERENT:
      return "确认密码与密码必须相同！";
    case USER_NAME_ALREADY_EXIST:
      return "账号已存在！";
    case REGISTER_SUCCESS:
      return "注册成功！";
    default:
      return "";
    }
  }

  /**
   * 是否注册成功
   * @return 是否注册成功，true表示注册成功，false反之
   */
  public boolean isSuccess() {
    switch (this) {
    case REGISTER_SUCCESS:
      return true;
    default:
      return false;
    }
  }
}
