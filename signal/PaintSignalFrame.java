//PaintSignalFrame
package signal;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
/**
 * �źŵ�չʾ�����ƽ���
* @author 8618534307787
 */
public class PaintSignalFrame extends JFrame {
	/**
	* �汾default
	*/
	private static final long serialVersionUID = 1L;
	/**
	 * �źŵ�չʾ���
	 */
	SignalPanel mp;
	/**
	 * ��ʼ��ť
	 */
	private JButton startButton;
	/**
	 * ������ť
	 */
	private JButton endButton;
	/**
	 * ����ʱ�䰴ť
	 */
	private JButton setTimeButton;
	/**
	 * �źŵƿ����߳�
	 */
	private PaintSignalThread pt;
	/**
	 * ���ݸ��źŵƿ����̵߳ĺ��ʱ�����
	 */
	private int RedTime_toThread;
	/**
	 * ���ݸ��źŵƿ����̵߳��̵�ʱ�����
	 */
	private int GreenTime_toThread;
	/**
	 * ���ݸ��źŵƿ����̵߳ĻƵ�ʱ�����
	 */
	private int YellowTime_toThread;
	/**
	 * �����źŵ���ɫ���ư�ť��ŵĽ���
	 */
	JPanel ColorButtonP;
	/**
	 * ����ʱ����棨�ڱ������еĵڶ����ý��棩
	 */
	TimeSetFrame tsf = null;
	/**
	 * ��ɫ��ʼѭ����ť
	 */
	CircleButton Red;
	/**
	 * ��ɫ��ʼѭ����ť
	 */
	CircleButton Green;
	/**
	 * ��ɫ��ʼѭ����ť
	 */
	CircleButton Yellow;
/**
 * ���캯��
 * @param RedTime �����ʾʱ��
 * @param GreenTime �̵���ʾʱ��
 * @param YellowTime �Ƶ���ʾʱ��
 */
	PaintSignalFrame(int RedTime, int GreenTime, int YellowTime) {

		
		RedTime_toThread = RedTime;
		GreenTime_toThread = GreenTime;
		YellowTime_toThread = YellowTime;
		mp = new SignalPanel(0);
		mp.setBounds(0, 0, 200, 140);
		startButton = new JButton("��ʼ");
		startButton.setBounds(20, 150, 65, 30);
		endButton = new JButton("����");
		endButton.setBounds(100, 150, 65, 30);
		setTimeButton = new JButton("ʱ������");
		setTimeButton.setBounds(180, 150, 100, 30);
		//���������������
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
		//��ɫ�������ɼ�����

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
		//���沼��
		
		
		
		tsf = new TimeSetFrame(RedTime_toThread, GreenTime_toThread, YellowTime_toThread) {
			
			private static final long serialVersionUID = 1L;
			/**
			 * ����,Ϊ�ڲ��࣬���Ե��ñ���ķ���
			 */
			public void useToOver(int RT, int GT, int YT) {

				setTime(RT, GT, YT);
				tsf.setVisible(false);
			}
		};
		//�ڶ���ʱ�����ý���
		
		tsf.setVisible(false);

		startButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				if (!pt.isAlive()) {
					pt = new PaintSignalThread(RedTime_toThread * 1000, GreenTime_toThread * 1000,
							YellowTime_toThread * 1000, mp);
					pt.start();
				}
			}

		});//��ʼ

		endButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				if (pt.isAlive())
					pt.endPaint();

			}

		});//����

		setTimeButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				if (pt.isAlive())
					pt.endPaint();

				tsf.setVisible(true);
			}

		});//�ڶ�������ʱ��������
		
		Red.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				if (pt.isAlive()) {
					pt.endPaint();
				}
				pt = new PaintSignalThread(RedTime_toThread * 1000, GreenTime_toThread * 1000, YellowTime_toThread * 1000,mp);
				pt.setTask(0);
				pt.start();

			}
		});//��ɫ��ʼ��
		Green.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				if (pt.isAlive()) {
					pt.endPaint();
				}
				pt = new PaintSignalThread(RedTime_toThread * 1000, GreenTime_toThread * 1000, YellowTime_toThread * 1000,mp);
				pt.setTask(1);
				pt.start();

			}
			
		});//��ɫ��ʼ��
		Yellow.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				if (pt.isAlive()) {
					pt.endPaint();
				}
				pt = new PaintSignalThread(RedTime_toThread * 1000, GreenTime_toThread * 1000, YellowTime_toThread * 1000,mp);
				pt.setTask(2);
				pt.start();

			}
		});//��ɫ��ʼ��
	}
/**
 * ���ô������̵߳�ʱ��
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
