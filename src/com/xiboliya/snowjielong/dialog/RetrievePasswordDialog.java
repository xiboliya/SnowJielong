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

import java.awt.CardLayout;
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
import com.xiboliya.snowjielong.common.ChangePasswordResult;
import com.xiboliya.snowjielong.util.Util;
import com.xiboliya.snowjielong.window.TipsWindow;

/**
 * "找回密码"对话框
 * 
 * @author 冰原
 */
public class RetrievePasswordDialog extends BaseDialog implements ActionListener {
  private static final long serialVersionUID = 1L;
  private JPanel pnlMain = (JPanel) this.getContentPane();
  private CardLayout layoutMain = new CardLayout();

  // 输入账号
  private JPanel pnlFirst = new JPanel();
  private JLabel lblUserName = new JLabel("账号：");
  private BaseTextField txtUserName = new BaseTextField(true, ".{0,20}"); // 限制用户输入的字符数量不能超过20个
  private BaseButton btnNext1 = new BaseButton("下一步");

  // 回答问题
  private JPanel pnlSecond = new JPanel();
  private JLabel lblRetrievePasswordQuestion = new JLabel("问题：");
  private BaseTextField txtRetrievePasswordQuestion = new BaseTextField();
  private JLabel lblRetrievePasswordAnswer = new JLabel("答案：");
  private BaseTextField txtRetrievePasswordAnswer = new BaseTextField();
  private BaseButton btnNext2 = new BaseButton("下一步");

  // 重置密码
  private JPanel pnlThird = new JPanel();
  private JLabel lblPassword = new JLabel("密码：");
  private JPasswordField psdPassword = new JPasswordField();
  private JLabel lblPasswordAgain = new JLabel("确认密码：");
  private JPasswordField psdPasswordAgain = new JPasswordField();
  private BaseButton btnConfirm = new BaseButton("确认修改");

  private BaseKeyAdapter keyAdapter = new BaseKeyAdapter(this);
  private BaseKeyAdapter buttonKeyAdapter = new BaseKeyAdapter(this, false);
  private String userName;

  /**
   * 构造方法
   * @param owner 用于显示该对话框的父组件
   * @param modal 是否为模式对话框
   */
  public RetrievePasswordDialog(JFrame owner, boolean modal) {
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
    this.txtRetrievePasswordQuestion.setText("");
    this.txtRetrievePasswordAnswer.setText("");
    this.psdPassword.setText("");
    this.psdPasswordAgain.setText("");
    this.layoutMain.show(this.pnlMain, "0");
    super.setVisible(visible);
  }

  /**
   * 初始化界面
   */
  private void init() {
    this.setTitle("找回密码");
    this.pnlMain.setLayout(this.layoutMain);

    this.lblUserName.setBounds(Util.getSize(20), Util.getSize(90), Util.getSize(70), Util.getSize(Util.VIEW_HEIGHT));
    this.txtUserName.setBounds(Util.getSize(90), Util.getSize(90), Util.getSize(170), Util.getSize(Util.INPUT_HEIGHT));
    this.btnNext1.setBounds(Util.getSize(100), Util.getSize(180), Util.getSize(100), Util.getSize(Util.BUTTON_HEIGHT));

    this.lblRetrievePasswordQuestion.setBounds(Util.getSize(20), Util.getSize(90), Util.getSize(70), Util.getSize(Util.VIEW_HEIGHT));
    this.txtRetrievePasswordQuestion.setBounds(Util.getSize(90), Util.getSize(90), Util.getSize(170), Util.getSize(Util.INPUT_HEIGHT));
    this.lblRetrievePasswordAnswer.setBounds(Util.getSize(20), Util.getSize(125), Util.getSize(70), Util.getSize(Util.VIEW_HEIGHT));
    this.txtRetrievePasswordAnswer.setBounds(Util.getSize(90), Util.getSize(125), Util.getSize(170), Util.getSize(Util.INPUT_HEIGHT));
    this.btnNext2.setBounds(Util.getSize(100), Util.getSize(180), Util.getSize(100), Util.getSize(Util.BUTTON_HEIGHT));

    this.lblPassword.setBounds(Util.getSize(20), Util.getSize(90), Util.getSize(70), Util.getSize(Util.VIEW_HEIGHT));
    this.psdPassword.setBounds(Util.getSize(90), Util.getSize(90), Util.getSize(170), Util.getSize(Util.INPUT_HEIGHT));
    this.lblPasswordAgain.setBounds(Util.getSize(20), Util.getSize(125), Util.getSize(70), Util.getSize(Util.VIEW_HEIGHT));
    this.psdPasswordAgain.setBounds(Util.getSize(90), Util.getSize(125), Util.getSize(170), Util.getSize(Util.INPUT_HEIGHT));
    this.btnConfirm.setBounds(Util.getSize(100), Util.getSize(180), Util.getSize(100), Util.getSize(Util.BUTTON_HEIGHT));

    this.pnlFirst.setLayout(null);
    this.pnlFirst.add(this.lblUserName);
    this.pnlFirst.add(this.txtUserName);
    this.pnlFirst.add(this.btnNext1);
    this.pnlSecond.setLayout(null);
    this.pnlSecond.add(this.lblRetrievePasswordQuestion);
    this.pnlSecond.add(this.txtRetrievePasswordQuestion);
    this.txtRetrievePasswordQuestion.setEditable(false);
    this.pnlSecond.add(this.lblRetrievePasswordAnswer);
    this.pnlSecond.add(this.txtRetrievePasswordAnswer);
    this.pnlSecond.add(this.btnNext2);
    this.pnlThird.setLayout(null);
    this.pnlThird.add(this.lblPassword);
    this.pnlThird.add(this.psdPassword);
    this.pnlThird.add(this.lblPasswordAgain);
    this.pnlThird.add(this.psdPasswordAgain);
    this.pnlThird.add(this.btnConfirm);
    this.pnlMain.add(this.pnlFirst, "0");
    this.pnlMain.add(this.pnlSecond, "1");
    this.pnlMain.add(this.pnlThird, "2");
    this.layoutMain.show(this.pnlMain, "0");
  }

  /**
   * 添加事件监听器
   */
  private void addListeners() {
    this.txtUserName.addKeyListener(this.keyAdapter);
    this.btnNext1.addActionListener(this);
    this.btnNext1.addKeyListener(this.buttonKeyAdapter);
    this.txtRetrievePasswordAnswer.addKeyListener(this.keyAdapter);
    this.btnNext2.addActionListener(this);
    this.btnNext2.addKeyListener(this.buttonKeyAdapter);
    this.psdPassword.addKeyListener(this.keyAdapter);
    this.psdPasswordAgain.addKeyListener(this.keyAdapter);
    this.btnConfirm.addActionListener(this);
    this.btnConfirm.addKeyListener(this.buttonKeyAdapter);
  }

  private void onNext1() {
    this.userName = this.txtUserName.getText();
    if (Util.isTextEmpty(this.userName)) {
      TipsWindow.show(this, "账号不能为空！");
      return;
    }
    boolean isUserExist = Util.settingAdapter.isUserExist(this.userName);
    if (!isUserExist) {
      TipsWindow.show(this, "账号未注册！");
      return;
    }
    String retrievePasswordQuestion = Util.settingAdapter.getRetrievePasswordQuestion(this.userName);
    if (Util.isTextEmpty(retrievePasswordQuestion)) {
      TipsWindow.show(this, "未设置找回密码问题！");
      return;
    }
    this.txtRetrievePasswordQuestion.setText(retrievePasswordQuestion);
    this.layoutMain.show(this.pnlMain, "1");
    this.txtRetrievePasswordAnswer.requestFocus();
  }

  private void onNext2() {
    String strRetrievePasswordAnswer = this.txtRetrievePasswordAnswer.getText();
    if (Util.isTextEmpty(strRetrievePasswordAnswer)) {
      TipsWindow.show(this, "找回密码答案不能为空！");
      return;
    }
    String retrievePasswordAnswer = Util.settingAdapter.getRetrievePasswordAnswer(this.userName);
    if (!strRetrievePasswordAnswer.equals(retrievePasswordAnswer)) {
      TipsWindow.show(this, "找回密码答案错误！");
      return;
    }
    this.layoutMain.show(this.pnlMain, "2");
    this.psdPassword.requestFocus();
  }

  private void onConfirm() {
    char[] aarPassword = this.psdPassword.getPassword();
    char[] aarPasswordAgain = this.psdPasswordAgain.getPassword();
    ChangePasswordResult changePasswordResult = Util.settingAdapter.changePassword(this.userName, aarPassword, aarPasswordAgain);
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
    if (this.btnNext1.equals(source) || this.btnNext2.equals(source) || this.btnConfirm.equals(source)) {
      this.onEnter();
    }
  }

  /**
   * 默认的"确定"操作方法
   */
  @Override
  public void onEnter() {
    if (this.pnlFirst.isVisible()) {
      this.onNext1();
    } else if (this.pnlSecond.isVisible()) {
      this.onNext2();
    } else if (this.pnlThird.isVisible()) {
      this.onConfirm();
    }
  }

  /**
   * 默认的"取消"操作方法
   */
  @Override
  public void onCancel() {
    this.dispose();
  }
}
