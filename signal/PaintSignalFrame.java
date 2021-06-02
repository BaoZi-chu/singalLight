//PaintSignalFrame
package signal;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
/**
 * 信号灯展示及控制界面
* @author 8618534307787
 */
public class PaintSignalFrame extends JFrame {
	/**
	* 版本default
	*/
	private static final long serialVersionUID = 1L;
	/**
	 * 信号灯展示面板
	 */
	SignalPanel mp;
	/**
	 * 开始按钮
	 */
	private JButton startButton;
	/**
	 * 结束按钮
	 */
	private JButton endButton;
	/**
	 * 设置时间按钮
	 */
	private JButton setTimeButton;
	/**
	 * 信号灯控制线程
	 */
	private PaintSignalThread pt;
	/**
	 * 传递给信号灯控制线程的红灯时间变量
	 */
	private int RedTime_toThread;
	/**
	 * 传递给信号灯控制线程的绿灯时间变量
	 */
	private int GreenTime_toThread;
	/**
	 * 传递给信号灯控制线程的黄灯时间变量
	 */
	private int YellowTime_toThread;
	/**
	 * 用于信号灯颜色控制按钮存放的界面
	 */
	JPanel ColorButtonP;
	/**
	 * 设置时间界面（在本程序中的第二个该界面）
	 */
	TimeSetFrame tsf = null;
	/**
	 * 红色开始循环按钮
	 */
	CircleButton Red;
	/**
	 * 绿色开始循环按钮
	 */
	CircleButton Green;
	/**
	 * 黄色开始循环按钮
	 */
	CircleButton Yellow;
/**
 * 构造函数
 * @param RedTime 红灯显示时间
 * @param GreenTime 绿灯显示时间
 * @param YellowTime 黄灯显示时间
 */
	PaintSignalFrame(int RedTime, int GreenTime, int YellowTime) {

		
		RedTime_toThread = RedTime;
		GreenTime_toThread = GreenTime;
		YellowTime_toThread = YellowTime;
		mp = new SignalPanel(0);
		mp.setBounds(0, 0, 200, 140);
		startButton = new JButton("开始");
		startButton.setBounds(20, 150, 65, 30);
		endButton = new JButton("结束");
		endButton.setBounds(100, 150, 65, 30);
		setTimeButton = new JButton("时间设置");
		setTimeButton.setBounds(180, 150, 100, 30);
		//界面基础部件生成
		pt = new PaintSignalThread(RedTime_toThread * 1000, GreenTime_toThread * 1000, YellowTime_toThread * 1000, mp);
		
		Red = new CircleButton("");
		Red.setBackground(Color.red);
		Red.setLocation(0, 0);
		Green = new CircleButton("");
		Green.setBackground(Color.green);
		Green.setLocation(0, 40);
		Yellow = new CircleButton("");
		Yellow.setBackground(Color.yellow);
		Yellow.setLocation(0, 80);
		ColorButtonP = new JPanel();
		ColorButtonP.add(Red);
		ColorButtonP.add(Green);
		ColorButtonP.add(Yellow);
		ColorButtonP.setBounds(200, 0, 100, 140);
		//颜色控制生成及布局

		add(ColorButtonP);
		add(startButton);
		add(endButton);
		add(setTimeButton);
		add(mp);
		setLayout(null);
		this.setSize(300, 250);
		this.setLocation(600, 300);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
		setResizable(false);
		//界面布局
		
		
		
		tsf = new TimeSetFrame(RedTime_toThread, GreenTime_toThread, YellowTime_toThread) {
			
			private static final long serialVersionUID = 1L;
			/**
			 * 重载,为内部类，可以调用本类的方法
			 */
			public void useToOver(int RT, int GT, int YT) {

				setTime(RT, GT, YT);
				tsf.setVisible(false);
			}
		};
		//第二个时间设置界面
		
		tsf.setVisible(false);

		startButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				if (!pt.isAlive()) {
					pt = new PaintSignalThread(RedTime_toThread * 1000, GreenTime_toThread * 1000,
							YellowTime_toThread * 1000, mp);
					pt.start();
				}
			}

		});//开始

		endButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				if (pt.isAlive())
					pt.endPaint();

			}

		});//结束

		setTimeButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				if (pt.isAlive())
					pt.endPaint();

				tsf.setVisible(true);
			}

		});//第二个设置时间界面调出
		
		Red.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				if (pt.isAlive()) {
					pt.endPaint();
				}
				pt = new PaintSignalThread(RedTime_toThread * 1000, GreenTime_toThread * 1000, YellowTime_toThread * 1000,mp);
				pt.setTask(0);
				pt.start();

			}
		});//红色开始！
		Green.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				if (pt.isAlive()) {
					pt.endPaint();
				}
				pt = new PaintSignalThread(RedTime_toThread * 1000, GreenTime_toThread * 1000, YellowTime_toThread * 1000,mp);
				pt.setTask(1);
				pt.start();

			}
			
		});//绿色开始！
		Yellow.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				if (pt.isAlive()) {
					pt.endPaint();
				}
				pt = new PaintSignalThread(RedTime_toThread * 1000, GreenTime_toThread * 1000, YellowTime_toThread * 1000,mp);
				pt.setTask(2);
				pt.start();

			}
		});//黄色开始！
	}
/**
 * 设置传递至线程的时间
 * @param RedTime
 * @param GreenTime
 * @param YellowTime
 */
	private void setTime(int RedTime, int GreenTime, int YellowTime) {

		RedTime_toThread = RedTime;
		GreenTime_toThread = GreenTime;
		YellowTime_toThread = YellowTime;
	}
}
