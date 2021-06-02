package signal;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;
 
import javax.swing.JButton;
 
/**
 * ����һ��Բ�εİ�ťʱ����Ҫ�������£� ��һ����������һ���ʵ��Ļ滭�����Ի���һ��Բ�Ρ�
 * �ڶ�����������һЩ�¼�ʹ��ֻ�е�����Բ�ΰ�ť�ķ�Χ�е�ʱ�ť�Ż�������Ӧ
 */
public class CircleButton extends JButton {
 
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CircleButton(String label) {
        super(label);
       
 
        // ��ȡ��ť����Ѵ�С
        Dimension size = getPreferredSize();
//        size.width = size.height = Math.max(size.width, size.height);
        size.width = size.height =30;
        setPreferredSize(size);
       
        setContentAreaFilled(false);
        this.setBorderPainted(false); // �����Ʊ߿�
        this.setFocusPainted(false); // �����ƽ���״̬
    }
 
    // ��Բ�İ�ť�ı����ͱ�ǩ
    protected void paintComponent(Graphics g) {
 
        if (getModel().isArmed()) {
            g.setColor(Color.lightGray); // ���ʱ����
        } else {
            g.setColor(getBackground());
        }
        // fillOval������һ�����ε�������Բ��������������Բ��
        // ������Ϊ������ʱ����������Բ����Բ
        g.fillOval(0, 0, getSize().width - 1, getSize().height - 1);
 
        super.paintComponent(g);
    }
 
    // �ü򵥵Ļ�����ť�ı߽硣
    protected void paintBorder(Graphics g) {
        g.setColor(Color.white);
        // drawOval���������ε�������Բ��������䡣ֻ����һ���߽�
        g.drawOval(0, 0, getSize().width - 1, getSize().height - 1);
    }
 
    // shape�������ڱ��水ť����״�����������������ť�¼�
    Shape shape;
 
    public boolean contains(int x, int y) {
 
        if ((shape == null) || (!shape.getBounds().equals(getBounds()))) {
            // ����һ����Բ�ζ���
            shape = new Ellipse2D.Float(0, 0, getWidth(), getHeight());
        }
        // �ж�����x��y�����Ƿ����ڰ�ť��״�ڡ�
        return shape.contains(x, y);
    }

}