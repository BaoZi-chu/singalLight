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
	 * ��ʼ���ɴ���λ�ã���
	 */
	private static int positionL_inital = 100;
	/**
	 * ��ʼ���ɴ���λ�ã��ң�
	 */
	private static int positionR_inital = 100;
	/**
	 * ���ڼ�¼��ɫʱ����ı���
	 */
	private JTextField RedTimeText;
	/**
	 * ���ڼ�¼��ɫʱ����ı���
	 */
	private JTextField GreenTimeText;
	/**
	 * ���ڼ�¼��ɫʱ����ı���
	 */
	private JTextField YellowTimeText;
	/**
	 * �ύ��ť
	 */
	private JButton submitButton;
	/**
	 * ���ڼ�¼�����ı����ɫʱ��ı���
	 */
	private int RedTimeFromText;
	/**
	 * ���ڼ�¼�����ı�����ɫʱ��ı���
	 */
	private int GreenTimeFromText;
	/**
	 * ���ڼ�¼�����ı����ɫʱ��ı���
	 */
	private int YellowTimeFromText;
	/**
	 * �źŵ���ʾ�����ƽ���
	 */
	PaintSignalFrame ps;
	/**
	 * ������Ϣ���
	 */
	JLabel feedback;
/**
 * ʱ�����ý���Ĺ��캯��
 * @param RedTime ���ʱ���ı���ĳ�ʼֵ
 * @param GreenTime �̵�ʱ���ı���ĳ�ʼֵ
 * @param YellowTime �Ƶ�ʱ���ı���ĳ�ʼֵ
 */
	public TimeSetFrame(int RedTime, int GreenTime, int YellowTime) {
		

		JLabel l1 = new JLabel("���ʱ��(s):");
		l1.setBounds(20, 20, 80, 30);
		JLabel l2 = new JLabel("�̵�ʱ��(s):");
		l2.setBounds(20, 75, 80, 30);
		JLabel l3 = new JLabel("�Ƶ�ʱ��(s):");
		l3.setBounds(20, 130, 80, 30);
		//��������
		RedTimeText = new JTextField();
		RedTimeText.setBounds(100, 20, 100, 30);
		GreenTimeText = new JTextField();
		GreenTimeText.setBounds(100, 75, 100, 30);
		YellowTimeText = new JTextField();
		YellowTimeText.setBounds(100, 130, 100, 30);
		RedTimeText.setText(RedTime+"");
		GreenTimeText.setText(GreenTime+"");
		YellowTimeText.setText(YellowTime+"");
		submitButton = new JButton("ȷ��");
		submitButton.setBounds(100, 185, 100, 30);
		feedback = new JLabel("");
		feedback.setBounds(100, 240, 80, 30);
		
		// ��������

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
		setTitle("ʱ������");
		// ����

		submitButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				judge();
			}

		});//�ύ��ť�����¼�����
	}
	/**
	 * �޲ι��캯��
	 * �졢�̡���ʱ���ʼֵΪ1s
	 */
	public TimeSetFrame() {
		this(1,1,1);
	}
/**
 * �ж��ı��Ƿ�Ϊ����
 * @param str ���жϵ��ı�
 * @return
 */
	public static boolean isNumeric(String str) {

		Pattern pattern = Pattern.compile("[0-9]*");

		return pattern.matcher(str).matches();

	}
/**
 * �����ύ��ť����֮���жϿɽ��в������ݺ�Ҫʵ�ֵķ������ڴ˴�Ϊ��һ�ε���ʱִ�����ݣ���֮��ִ��Ҫ�����������
 * Ϊ��ֹps(�źŵ�չʾ�����ƽ���)������ʱ�����ý����໥���ã���ɲ��������ڴ�������ڴ�й©����
 * @param RT Ҫ�������źŵ�����ĺ��ʱ��
 * @param GT Ҫ�������źŵ�������̵�ʱ��
 * @param YT Ҫ�������źŵ�����ĻƵ�ʱ��
 */
	public void useToOver(int RT, int GT, int YT) {

		ps = new PaintSignalFrame(RT, GT, YT);
		setVisible(false);
		ps.setTitle("�źŵ�");

	}
/**
 * �ύ��ť���º���жϺ����������ж������Ƿ�Ϊ�ջ����ֻ�Ϊ0
 */
	public void judge() {

		if (RedTimeText.getText().isEmpty() || GreenTimeText.getText().isEmpty()
				|| YellowTimeText.getText().isEmpty()) {
			feedback.setText("����Ϊ��");

		} else {
			if (isNumeric(RedTimeText.getText()) && isNumeric(GreenTimeText.getText())
					&& isNumeric(YellowTimeText.getText())) {
				RedTimeFromText = Integer.parseInt(RedTimeText.getText());
				GreenTimeFromText = Integer.parseInt(GreenTimeText.getText());
				YellowTimeFromText = Integer.parseInt(YellowTimeText.getText());
				if(RedTimeFromText==0||GreenTimeFromText==0||YellowTimeFromText==0) {
					feedback.setText("ֵ����Ϊ0");
				}
				else {
				useToOver(RedTimeFromText, GreenTimeFromText, YellowTimeFromText);
				}
			} else {
				feedback.setText("����������");
			}

		}

	}
}
