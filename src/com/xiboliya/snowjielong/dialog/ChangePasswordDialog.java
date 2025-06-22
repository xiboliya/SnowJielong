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

package com.xiboliya.snowjielong.dialog;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;

import com.xiboliya.snowjielong.base.BaseButton;
import com.xiboliya.snowjielong.base.BaseDialog;
import com.xiboliya.snowjielong.base.BaseKeyAdapter;
import com.xiboliya.snowjielong.common.ChangePasswordResult;
import com.xiboliya.snowjielong.util.Util;

/**
 * "修改密码"对话框
 * 
 * @author 冰原
 */
public class ChangePasswordDialog extends BaseDialog implements ActionListener {
  private static final long serialVersionUID = 1L;
  private JPanel pnlMain = (JPanel) this.getContentPane();
  private JLabel lblPasswordOld = new JLabel("原密码：");
  private JPasswordField psdPasswordOld = new JPasswordField();
  private JLabel lblPassword = new JLabel("新密码：");
  private JPasswordField psdPassword = new JPasswordField();
  private JLabel lblPasswordAgain = new JLabel("确认新密码：");
  private JPasswordField psdPasswordAgain = new JPasswordField();
  private BaseButton btnChange = new BaseButton("确认修改");
  private BaseKeyAdapter keyAdapter = new BaseKeyAdapter(this);
  private BaseKeyAdapter buttonKeyAdapter = new BaseKeyAdapter(this, false);

  /**
   * 构造方法
   * 
   * @param owner 用于显示该对话框的父组件
   * @param modal 是否为模式对话框
   */
  public ChangePasswordDialog(JFrame owner, boolean modal) {
    super(owner, modal);
    this.init();
    this.addListeners();
    this.setSize(Util.getSize(300), Util.getSize(270));
    this.setVisible(true);
  }

  /**
   * 重写父类的方法：显示或隐藏当前窗口
   */
  @Override
  public void setVisible(boolean visible) {
    this.psdPasswordOld.setText("");
    this.psdPassword.setText("");
    this.psdPasswordAgain.setText("");
    super.setVisible(visible);
  }

  /**
   * 初始化界面
   */
  private void init() {
    this.setTitle("修改密码");
    this.pnlMain.setLayout(null);
    this.lblPasswordOld.setBounds(Util.getSize(20), Util.getSize(20), Util.getSize(80), Util.getSize(Util.VIEW_HEIGHT));
    this.psdPasswordOld.setBounds(Util.getSize(100), Util.getSize(20), Util.getSize(170), Util.getSize(Util.INPUT_HEIGHT));
    this.lblPassword.setBounds(Util.getSize(20), Util.getSize(55), Util.getSize(80), Util.getSize(Util.VIEW_HEIGHT));
    this.psdPassword.setBounds(Util.getSize(100), Util.getSize(55), Util.getSize(170), Util.getSize(Util.INPUT_HEIGHT));
    this.lblPasswordAgain.setBounds(Util.getSize(20), Util.getSize(90), Util.getSize(80), Util.getSize(Util.VIEW_HEIGHT));
    this.psdPasswordAgain.setBounds(Util.getSize(100), Util.getSize(90), Util.getSize(170), Util.getSize(Util.INPUT_HEIGHT));
    this.btnChange.setBounds(Util.getSize(100), Util.getSize(150), Util.getSize(100), Util.getSize(Util.BUTTON_HEIGHT));
    this.pnlMain.add(this.lblPasswordOld);
    this.pnlMain.add(this.psdPasswordOld);
    this.pnlMain.add(this.lblPassword);
    this.pnlMain.add(this.psdPassword);
    this.pnlMain.add(this.lblPasswordAgain);
    this.pnlMain.add(this.psdPasswordAgain);
    this.pnlMain.add(this.btnChange);
  }

  /**
   * 添加事件监听器
   */
  private void addListeners() {
    this.psdPasswordOld.addKeyListener(this.keyAdapter);
    this.psdPassword.addKeyListener(this.keyAdapter);
    this.psdPasswordAgain.addKeyListener(this.keyAdapter);
    this.btnChange.addActionListener(this);
    this.btnChange.addKeyListener(this.buttonKeyAdapter);
  }

  /**
   * 修改密码
   */
  private void changePassword() {
    char[] aarPasswordOld = this.psdPasswordOld.getPassword();
    char[] aarPassword = this.psdPassword.getPassword();
    char[] aarPasswordAgain = this.psdPasswordAgain.getPassword();
    ChangePasswordResult changePasswordResult = Util.settingAdapter.changePassword(aarPasswordOld, aarPassword, aarPasswordAgain);
    if (changePasswordResult.isSuccess()) {
      JOptionPane.showMessageDialog(this, changePasswordResult.toString(), Util.SOFTWARE, JOptionPane.CANCEL_OPTION);
      this.onCancel();
    } else {
      JOptionPane.showMessageDialog(this, changePasswordResult.toString(), Util.SOFTWARE, JOptionPane.ERROR_MESSAGE);
    }
  }

  /**
   * 为各组件添加事件的处理方法
   */
  @Override
  public void actionPerformed(ActionEvent e) {
    Object source = e.getSource();
    if (this.btnChange.equals(source)) {
      this.onEnter();
    }
  }

  /**
   * 默认的"确定"操作方法
   */
  @Override
  public void onEnter() {
    this.changePassword();
  }

  /**
   * 默认的"取消"操作方法
   */
  @Override
  public void onCancel() {
    this.dispose();
  }
}
