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

package com.xiboliya.snowjielong.setting;

import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;
import java.util.HashMap;

import com.xiboliya.snowjielong.common.IdiomCache;
import com.xiboliya.snowjielong.common.User;
import com.xiboliya.snowjielong.util.Util;

/**
 * 软件参数配置类
 * 
 * @author 冰原
 * 
 */
public class Setting {
  // 成语接龙游戏的进度存档
  public ArrayList<User> userList = new ArrayList<User>();
  // 当前账号的进度存档
  public User user = null;
}
