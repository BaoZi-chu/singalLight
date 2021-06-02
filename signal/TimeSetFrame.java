//TimeSetFrame
package signal;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Pattern;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class TimeSetFrame extends JFrame {

	/**
	* 
	*/
	private static final long serialVersionUID = 1L;
	/**
	 * 初始生成窗口位置（左）
	 */
	private static int positionL_inital = 100;
	/**
	 * 初始生成窗口位置（右）
	 */
	private static int positionR_inital = 100;
	/**
	 * 用于记录红色时间的文本框
	 */
	private JTextField RedTimeText;
	/**
	 * 用于记录绿色时间的文本框
	 */
	private JTextField GreenTimeText;
	/**
	 * 用于记录黄色时间的文本框
	 */
	private JTextField YellowTimeText;
	/**
	 * 提交按钮
	 */
	private JButton submitButton;
	/**
	 * 用于记录来自文本框红色时间的变量
	 */
	private int RedTimeFromText;
	/**
	 * 用于记录来自文本框绿色时间的变量
	 */
	private int GreenTimeFromText;
	/**
	 * 用于记录来自文本框黄色时间的变量
	 */
	private int YellowTimeFromText;
	/**
	 * 信号灯显示及控制界面
	 */
	PaintSignalFrame ps;
	/**
	 * 回馈信息输出
	 */
	JLabel feedback;
/**
 * 时间设置界面的构造函数
 * @param RedTime 红灯时间文本框的初始值
 * @param GreenTime 绿灯时间文本框的初始值
 * @param YellowTime 黄灯时间文本框的初始值
 */
	public TimeSetFrame(int RedTime, int GreenTime, int YellowTime) {
		

		JLabel l1 = new JLabel("红灯时间(s):");
		l1.setBounds(20, 20, 80, 30);
		JLabel l2 = new JLabel("绿灯时间(s):");
		l2.setBounds(20, 75, 80, 30);
		JLabel l3 = new JLabel("黄灯时间(s):");
		l3.setBounds(20, 130, 80, 30);
		//部件生成
		RedTimeText = new JTextField();
		RedTimeText.setBounds(100, 20, 100, 30);
		GreenTimeText = new JTextField();
		GreenTimeText.setBounds(100, 75, 100, 30);
		YellowTimeText = new JTextField();
		YellowTimeText.setBounds(100, 130, 100, 30);
		RedTimeText.setText(RedTime+"");
		GreenTimeText.setText(GreenTime+"");
		YellowTimeText.setText(YellowTime+"");
		submitButton = new JButton("确认");
		submitButton.setBounds(100, 185, 100, 30);
		feedback = new JLabel("");
		feedback.setBounds(100, 240, 80, 30);
		
		// 部件设置

		add(l1);
		add(RedTimeText);
		add(l2);
		add(GreenTimeText);
		add(l3);
		add(YellowTimeText);
		add(submitButton);
		add(feedback);
		setLayout(null);
		setLocationRelativeTo(null);
		setBounds(positionL_inital , positionR_inital, 400, 300);
		setResizable(false);
		setVisible(true);
		setTitle("时间设置");
		// 布局

		submitButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				judge();
			}

		});//提交按钮按下事件处理
	}
	/**
	 * 无参构造函数
	 * 红、绿、黄时间初始值为1s
	 */
	public TimeSetFrame() {
		this(1,1,1);
	}
/**
 * 判断文本是否为数字
 * @param str 被判断的文本
 * @return
 */
	public static boolean isNumeric(String str) {

		Pattern pattern = Pattern.compile("[0-9]*");

		return pattern.matcher(str).matches();

	}
/**
 * 用于提交按钮按下之后判断可进行参数传递后要实现的方法，在此处为第一次调用时执行内容，在之后执行要对其进行重载
 * 为防止ps(信号灯展示及控制界面)反复与时间设置界面相互调用，造成不断申请内存而引发内存泄漏问题
 * @param RT 要创建的信号灯组件的红灯时间
 * @param GT 要创建的信号灯组件的绿灯时间
 * @param YT 要创建的信号灯组件的黄灯时间
 */
	public void useToOver(int RT, int GT, int YT) {

		ps = new PaintSignalFrame(RT, GT, YT);
		setVisible(false);
		ps.setTitle("信号灯");

	}
/**
 * 提交按钮按下后的判断函数，用于判断输入是否为空或数字或为0
 */
	public void judge() {

		if (RedTimeText.getText().isEmpty() || GreenTimeText.getText().isEmpty()
				|| YellowTimeText.getText().isEmpty()) {
			feedback.setText("输入为空");

		} else {
			if (isNumeric(RedTimeText.getText()) && isNumeric(GreenTimeText.getText())
					&& isNumeric(YellowTimeText.getText())) {
				RedTimeFromText = Integer.parseInt(RedTimeText.getText());
				GreenTimeFromText = Integer.parseInt(GreenTimeText.getText());
				YellowTimeFromText = Integer.parseInt(YellowTimeText.getText());
				if(RedTimeFromText==0||GreenTimeFromText==0||YellowTimeFromText==0) {
					feedback.setText("值不可为0");
				}
				else {
				useToOver(RedTimeFromText, GreenTimeFromText, YellowTimeFromText);
				}
			} else {
				feedback.setText("请输入数字");
			}

		}

	}
}
