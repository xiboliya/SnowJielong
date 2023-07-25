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

package com.xiboliya.snowjielong;

import javax.swing.SwingUtilities;

import com.xiboliya.snowjielong.frame.SnowJielongFrame;
import com.xiboliya.snowjielong.setting.Setting;
import com.xiboliya.snowjielong.setting.SettingAdapter;
import com.xiboliya.snowjielong.util.Util;

/**
 * 本程序的主类
 * 
 * @author 冰原
 * 
 */
public class SnowJielong {

  /**
   * 程序的总入口
   * 
   * @param args 命令行参数，如：java -jar SnowJielong.jar
   */
  public static void main(final String[] args) {
    SwingUtilities.invokeLater(new Runnable() {
      public void run() {
        Setting setting = new Setting();
        SettingAdapter settingAdapter = new SettingAdapter(setting);
        settingAdapter.parse();
        Util.setDefaultFont();
        System.setProperty("java.awt.im.style", "on-the-spot"); // 去掉文本框输入中文时所弹出的输入窗口
        new SnowJielongFrame(setting, settingAdapter); // 初始化界面和设置的同时打开文件
      }
    });
  }
}
