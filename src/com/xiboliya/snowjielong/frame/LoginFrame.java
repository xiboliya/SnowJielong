/**
 * Copyright (C) 2024 冰原
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

package com.xiboliya.snowjielong.frame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;

import com.xiboliya.snowjielong.base.BaseButton;
import com.xiboliya.snowjielong.base.BaseTextField;
import com.xiboliya.snowjielong.common.LoginResult;
import com.xiboliya.snowjielong.dialog.RegisterDialog;
import com.xiboliya.snowjielong.util.Util;
import com.xiboliya.snowjielong.window.TipsWindow;

/**
 * 成语接龙登录界面
 * 
 * @author 冰原
 */
public class LoginFrame extends JFrame implements ActionListener {
  private static final long serialVersionUID = 1L;
  private JPanel pnlMain = (JPanel) this.getContentPane();
  private JLabel lblUserName = new JLabel("账号：");
  private BaseTextField txtUserName = new BaseTextField();
  private JLabel lblPassword = new JLabel("密码：");
  private JPasswordField psdPassword = new JPasswordField();
  private BaseButton btnLogin = new BaseButton("登录");
  private BaseButton btnRegister = new BaseButton("注册");
  private BaseButton btnQuit = new BaseButton("退出");
  private RegisterDialog registerDialog = null;

  /**
   * 构造方法
   */
  public LoginFrame() {
    this.setSize(300, 300);
    this.setLocationRelativeTo(null); // 使窗口居中显示
    this.setResizable(false);
    this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE); // 设置默认关闭操作为空，以便添加窗口监听事件
    this.setIcon();
    this.init();
    this.addListeners();
    this.setVisible(true);
  }

  /**
   * 设置自定义的窗口图标
   */
  private void setIcon() {
    try {
      this.setIconImage(Util.ICON_SW.getImage());
    } catch (Exception x) {
      // x.printStackTrace();
    }
  }

  /**
   * 初始化界面
   */
  private void init() {
    this.setTitle("登录");
    this.initPanel();
  }

  /**
   * 初始化主面板
   */
  private void initPanel() {
    this.pnlMain.setLayout(null);
    this.lblUserName.setBounds(20, 20, 70, Util.VIEW_HEIGHT);
    this.txtUserName.setBounds(90, 20, 170, Util.INPUT_HEIGHT);
    this.lblPassword.setBounds(20, 55, 70, Util.VIEW_HEIGHT);
    this.psdPassword.setBounds(90, 55, 170, Util.INPUT_HEIGHT);
    this.btnLogin.setBounds(100, 110, 100, Util.BUTTON_HEIGHT);
    this.btnRegister.setBounds(100, 150, 100, Util.BUTTON_HEIGHT);
    this.btnQuit.setBounds(100, 190, 100, Util.BUTTON_HEIGHT);
    this.pnlMain.add(this.lblUserName);
    this.pnlMain.add(this.txtUserName);
    this.pnlMain.add(this.lblPassword);
    this.pnlMain.add(this.psdPassword);
    this.pnlMain.add(this.btnLogin);
    this.pnlMain.add(this.btnRegister);
    this.pnlMain.add(this.btnQuit);
  }

  /**
   * 添加事件监听器
   */
  private void addListeners() {
    this.btnLogin.addActionListener(this);
    this.btnRegister.addActionListener(this);
    this.btnQuit.addActionListener(this);
    // 为窗口添加事件监听器
    this.addWindowListener(new WindowAdapter() {
      public void windowClosing(WindowEvent e) {
        exit();
      }
    });
  }

  /**
   * 登录
   */
  private void login() {
    String strUserName = this.txtUserName.getText();
    char[] aarPassword = this.psdPassword.getPassword();
    LoginResult loginResult = Util.settingAdapter.login(strUserName, aarPassword);
    if (loginResult.isSuccess()) {
      new SnowJielongFrame();
      this.dispose();
    } else {
      JOptionPane.showMessageDialog(this, loginResult.toString(), Util.SOFTWARE, JOptionPane.ERROR_MESSAGE);
    }
  }

  /**
   * 打开注册对话框
   */
  private void openRegisterDialog() {
    if (this.registerDialog == null) {
      this.registerDialog = new RegisterDialog(this, true);
    } else {
      this.registerDialog.setVisible(true);
    }
  }

  /**
   * 退出
   */
  private void exit() {
    int result = JOptionPane.showConfirmDialog(this, "是否继续退出？", Util.SOFTWARE, JOptionPane.YES_NO_CANCEL_OPTION);
    if (result != JOptionPane.YES_OPTION) {
      return;
    }
    System.exit(0);
  }

  /**
   * 为各组件添加事件的处理方法
   */
  @Override
  public void actionPerformed(ActionEvent e) {
    Object source = e.getSource();
    if (this.btnLogin.equals(source)) {
      this.login();
    } else if (this.btnRegister.equals(source)) {
      this.openRegisterDialog();
    } else if (this.btnQuit.equals(source)) {
      this.exit();
    }
  }
}
