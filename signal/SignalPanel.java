//SingalPanel
package signal;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;
/**
 * ��ʾ�źŵƵ����
* @author 8618534307787
 */
public class SignalPanel extends JPanel {
	/**
	 * ��ɫ����
	 * �ֱ�Ϊ �졢�̡���
	 */
	private static Color cl[]= {Color.RED,Color.GREEN,Color.YELLOW};
	/**
	 * ������ɫ���飨cl�����±�
	 */
	private  int flag;
	/**
	 * ����ʼ��
	 * @param flag ������ɫ����ĵڼ���
	 */
	SignalPanel(int flag){
		super();
		flag=this.flag;
	}
	/**
	 * �汾default
	 */
	private static final long serialVersionUID = 1L;
/**
 * �źŵƳ�ʼ��
 */
	public void paint(Graphics g){
		super.paint(g);

		
		g.setColor(cl[flag]);
		g.drawOval(70, 30, 100, 100);
		g.fillOval(70, 30, 100, 100);
		}
	/**
	 * �����źŵƳ�ʼ��ɫ
	 * @param flag ��ɫ������±�
	 * �ֱ�Ϊ �졢�̡���
	 * ��ӦΪ 0 �� 1�� 2
	 */
	public void setFlag(int flag) {
		
		this.flag=flag;

	}
	/**
	 * �����źŵ���ɫ
	 * @param g Graphics�����ϵͳ�Զ����ã������������
	 */
	public void repaint(Graphics g){
		super.paint(g);
		
		g.setColor(cl[flag]);
		g.drawOval(70,30, 100, 100);
		g.fillOval(70, 30, 100, 100);
		}

	}

