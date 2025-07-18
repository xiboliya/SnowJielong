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
import com.xiboliya.snowjielong.base.BaseTextField;
import com.xiboliya.snowjielong.common.RegisterResult;
import com.xiboliya.snowjielong.util.Util;

/**
 * "注册"对话框
 * 
 * @author 冰原
 */
public class RegisterDialog extends BaseDialog implements ActionListener {
  private static final long serialVersionUID = 1L;
  private JPanel pnlMain = (JPanel) this.getContentPane();
  private JLabel lblUserName = new JLabel("账号：");
  private BaseTextField txtUserName = new BaseTextField(true, ".{0,20}"); // 限制用户输入的字符数量不能超过20个
  private JLabel lblPassword = new JLabel("密码：");
  private JPasswordField psdPassword = new JPasswordField();
  private JLabel lblPasswordAgain = new JLabel("确认密码：");
  private JPasswordField psdPasswordAgain = new JPasswordField();
  private JLabel lblInfo = new JLabel("【选填】找回密码：");
  private JLabel lblRetrievePasswordQuestion = new JLabel("问题：");
  private BaseTextField txtRetrievePasswordQuestion = new BaseTextField(true, ".{0,20}"); // 限制用户输入的字符数量不能超过20个
  private JLabel lblRetrievePasswordAnswer = new JLabel("答案：");
  private BaseTextField txtRetrievePasswordAnswer = new BaseTextField(true, ".{0,20}"); // 限制用户输入的字符数量不能超过20个
  private BaseButton btnRegister = new BaseButton("立即注册");
  private BaseKeyAdapter keyAdapter = new BaseKeyAdapter(this);
  private BaseKeyAdapter buttonKeyAdapter = new BaseKeyAdapter(this, false);

  /**
   * 构造方法
   * 
   * @param owner
   *          用于显示该对话框的父组件
   * @param modal
   *          是否为模式对话框
   */
  public RegisterDialog(JFrame owner, boolean modal) {
    super(owner, modal);
    this.init();
    this.addListeners();
    this.setSize(Util.getSize(300), Util.getSize(300));
    this.setVisible(true);
  }

  /**
   * 重写父类的方法：显示或隐藏当前窗口
   */
  @Override
  public void setVisible(boolean visible) {
    this.txtUserName.setText("");
    this.psdPassword.setText("");
    this.psdPasswordAgain.setText("");
    this.txtRetrievePasswordQuestion.setText("");
    this.txtRetrievePasswordAnswer.setText("");
    super.setVisible(visible);
  }

  /**
   * 初始化界面
   */
  private void init() {
    this.setTitle("注册");
    this.pnlMain.setLayout(null);
    this.lblUserName.setBounds(Util.getSize(20), Util.getSize(20), Util.getSize(70), Util.getSize(Util.VIEW_HEIGHT));
    this.txtUserName.setBounds(Util.getSize(90), Util.getSize(20), Util.getSize(170), Util.getSize(Util.INPUT_HEIGHT));
    this.lblPassword.setBounds(Util.getSize(20), Util.getSize(55), Util.getSize(70), Util.getSize(Util.VIEW_HEIGHT));
    this.psdPassword.setBounds(Util.getSize(90), Util.getSize(55), Util.getSize(170), Util.getSize(Util.INPUT_HEIGHT));
    this.lblPasswordAgain.setBounds(Util.getSize(20), Util.getSize(90), Util.getSize(70), Util.getSize(Util.VIEW_HEIGHT));
    this.psdPasswordAgain.setBounds(Util.getSize(90), Util.getSize(90), Util.getSize(170), Util.getSize(Util.INPUT_HEIGHT));
    this.lblInfo.setBounds(Util.getSize(20), Util.getSize(125), Util.getSize(200), Util.getSize(Util.VIEW_HEIGHT));
    this.lblRetrievePasswordQuestion.setBounds(Util.getSize(20), Util.getSize(160), Util.getSize(70), Util.getSize(Util.VIEW_HEIGHT));
    this.txtRetrievePasswordQuestion.setBounds(Util.getSize(90), Util.getSize(160), Util.getSize(170), Util.getSize(Util.INPUT_HEIGHT));
    this.lblRetrievePasswordAnswer.setBounds(Util.getSize(20), Util.getSize(195), Util.getSize(70), Util.getSize(Util.VIEW_HEIGHT));
    this.txtRetrievePasswordAnswer.setBounds(Util.getSize(90), Util.getSize(195), Util.getSize(170), Util.getSize(Util.INPUT_HEIGHT));
    this.btnRegister.setBounds(Util.getSize(100), Util.getSize(230), Util.getSize(100), Util.getSize(Util.BUTTON_HEIGHT));
    this.pnlMain.add(this.lblUserName);
    this.pnlMain.add(this.txtUserName);
    this.pnlMain.add(this.lblPassword);
    this.pnlMain.add(this.psdPassword);
    this.pnlMain.add(this.lblPasswordAgain);
    this.pnlMain.add(this.psdPasswordAgain);
    this.pnlMain.add(this.lblInfo);
    this.pnlMain.add(this.lblRetrievePasswordQuestion);
    this.pnlMain.add(this.txtRetrievePasswordQuestion);
    this.pnlMain.add(this.lblRetrievePasswordAnswer);
    this.pnlMain.add(this.txtRetrievePasswordAnswer);
    this.pnlMain.add(this.btnRegister);
  }

  /**
   * 添加事件监听器
   */
  private void addListeners() {
    this.txtUserName.addKeyListener(this.keyAdapter);
    this.psdPassword.addKeyListener(this.keyAdapter);
    this.psdPasswordAgain.addKeyListener(this.keyAdapter);
    this.txtRetrievePasswordQuestion.addKeyListener(this.keyAdapter);
    this.txtRetrievePasswordAnswer.addKeyListener(this.keyAdapter);
    this.btnRegister.addActionListener(this);
    this.btnRegister.addKeyListener(this.buttonKeyAdapter);
  }

  /**
   * 注册
   */
  private void register() {
    String strUserName = this.txtUserName.getText();
    char[] aarPassword = this.psdPassword.getPassword();
    char[] aarPasswordAgain = this.psdPasswordAgain.getPassword();
    String retrievePasswordQuestion = this.txtRetrievePasswordQuestion.getText();
    String retrievePasswordAnswer = this.txtRetrievePasswordAnswer.getText();
    RegisterResult registerResult = Util.settingAdapter.register(strUserName, aarPassword, aarPasswordAgain, retrievePasswordQuestion, retrievePasswordAnswer);
    if (registerResult.isSuccess()) {
      JOptionPane.showMessageDialog(this, registerResult.toString(), Util.SOFTWARE, JOptionPane.CANCEL_OPTION);
      this.onCancel();
    } else {
      JOptionPane.showMessageDialog(this, registerResult.toString(), Util.SOFTWARE, JOptionPane.ERROR_MESSAGE);
    }
  }

  /**
   * 为各组件添加事件的处理方法
   */
  @Override
  public void actionPerformed(ActionEvent e) {
    Object source = e.getSource();
    if (this.btnRegister.equals(source)) {
      this.onEnter();
    }
  }

  /**
   * 默认的"确定"操作方法
   */
  @Override
  public void onEnter() {
    this.register();
  }

  /**
   * 默认的"取消"操作方法
   */
  @Override
  public void onCancel() {
    this.dispose();
  }
}
