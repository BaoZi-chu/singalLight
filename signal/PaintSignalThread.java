//PaintSignThread
package signal;

import java.util.Timer;
import java.util.TimerTask;
/**
 * �źŵƿ����߳�
* @author 8618534307787
 */
public class PaintSignalThread extends Thread {
	/**
	 *�źŵ�չʾ���
	 */
	SignalPanel mp;
	/**
	 * ��ɫ��ʾʱ��
	 */
	private int RedTime;
	/**
	 * ��ɫ��ʾʱ��
	 */
	private int GreenTime;
	/**
	 * ��ɫ��ʾʱ��
	 */
	private int YellowTime;
	/**
	 * ��ʱ��
	 */
	private int TotalTime;
	/**
	 * ��ɫ��ʾ����
	 */
	TimerTask redTimer;
	/**
	 * ��ɫ��ʾ����
	 */
	TimerTask GreenTimer;
	/**
	 * ��ɫ��ʾʱ��
	 */
	TimerTask YellowTimer;
	/**
	 * ��ʱ��
	 */
	public Timer t;
	/**
	 * �������  task ��
	 */
	private int task;
	/**
	 * �Ƿ��˳�
	 */
	public volatile boolean exit = false;
/**
 * ���캯��
 * @param RedTime �����ʾʱ��
 * @param GreenTime �̵���ʾʱ��
 * @param YellowTime �Ƶ���ʾʱ��
 * @param mp ������������ʾ�źŵƵ����
 */
	PaintSignalThread(int RedTime, int GreenTime, int YellowTime, SignalPanel mp) {
		this.RedTime = RedTime;
		this.GreenTime = GreenTime;
		this.YellowTime = YellowTime;
		this.TotalTime = RedTime + GreenTime + YellowTime;
		this.mp = mp;
		task=0;
		redTimer = new TimerTask() {
			@Override
			public void run() {
				try {
					// startTime=System.currentTimeMillis();
					mp.setFlag(0);
					mp.repaint();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		};
		GreenTimer = new TimerTask() {
			@Override
			public void run() {
				try {
					// startTime=System.currentTimeMillis();
					mp.setFlag(1);
					mp.repaint();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		};
		YellowTimer = new TimerTask() {
			@Override
			public void run() {
				try {
					// startTime=System.currentTimeMillis();
					mp.setFlag(2);
					mp.repaint();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		};

	}

	public void run() {

		t = new Timer();
		exit = false;
		switch (task) {
		case 0: {
			t.schedule(redTimer, 0, TotalTime);
			t.schedule(GreenTimer, RedTime, TotalTime);
			t.schedule(YellowTimer, RedTime + GreenTime, TotalTime);
			break;
		}
		case 1: {
			t.schedule(redTimer, GreenTime+YellowTime, TotalTime);
			t.schedule(GreenTimer, 0, TotalTime);
			t.schedule(YellowTimer,  GreenTime, TotalTime);
			break;
		}
		case 2:{
			t.schedule(redTimer, YellowTime, TotalTime);
			t.schedule(GreenTimer, YellowTime+RedTime, TotalTime);
			t.schedule(YellowTimer,  0, TotalTime);
			break;
		}
		default :
			break;

		}//����ѡ��0�����ɫ�ȿ�ʼ��1������ɫ�ȿ�ʼ��20�����ɫ�ȿ�ʼ

		while (!exit) {
			try {
				sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}//ʹ�̳߳���ִ�У������ж��߳�ִ��

	}

	public void endPaint() {
		exit = true;
		
		if (t != null) {
			t.cancel();//�������Ϊ�յĻ���������ȡ��
		}
		t = null;//��ʱ������գ���ֹ�����쳣
	}
	/**
	 * ��������0�����ɫ�ȿ�ʼ��1������ɫ�ȿ�ʼ��2�����ɫ�ȿ�ʼ
	 * @param t ������
	 */
	public void setTask(int t) {
		task=t;
	}
}
