import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.text.BadLocationException;

public class Crotaleitor extends JFrame implements ActionListener{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	//fuentes grandotas para los resultados
	Font correcto=new Font("Verdana",Font.BOLD,20);
	Font incorrecto=new Font("Verdana",Font.BOLD,20);
	
	//BottonGroup para los radioButtons
	ButtonGroup bg = new ButtonGroup();
	//dos radioButtons para seleccionar si queremos introducir serie y numero
	//o tan s�lo el n�mero
	JRadioButton jrbSerieNumero = new JRadioButton("C.Aut. Serie y Número");
	JRadioButton jrbCompleto = new JRadioButton("Crotal completo");
	
	//Dos botones, uno para comprobar y otro para borrar
	JButton jbComprobar=new JButton("Comprobar");
	JButton jbBorrar=new JButton("Borrar");
	
	//Tres JTextField para los datos del crotal a depurar
	JTextField jtfCa=new JTextField(2);
	JTextField jtfSerie=new JTextField(4);
	JTextField jtfNumero=new JTextField(4);
	JTextField jtfCompleto=new JTextField(14);	
	
	//Un JLabel para informar de los resultados. Vac�o se rellenar� en tiempo de ejecuci�n
	JLabel jlResultado=new JLabel("Resultado");
	
	//el GridBagLayout y el GridBagConstraints
	GridBagLayout gbl=new GridBagLayout();
	GridBagConstraints gbc=new GridBagConstraints();
	
	//un JPanel para los botones
	JPanel jpBotonero=new JPanel();
	
	//Un JMenuBar para los menus
	JMenuBar jmbMenu=new JMenuBar();
	//Un JMenu para Ayuda
	JMenu jmAyuda=new JMenu("Ayuda");
	//Un JMenu para Archivo
	JMenu jmArchivo=new JMenu("Archivo");
	//Un JMenuItem para el Acerca de
	JMenuItem jmiAcerca=new JMenuItem ("Acerca de...",KeyEvent.VK_S);
	//Un JMenuItem para el Acerca de
	JMenuItem jmiSerieNumero=new JMenuItem ("C.Aut, serie y número",KeyEvent.VK_I);
	//Un JMenuItem para el Acerca de
	JMenuItem jmiCompleto=new JMenuItem ("Completo",KeyEvent.VK_C);
	//Un JMenuItem para el Acerca de
	JMenuItem jmiComprobar=new JMenuItem ("Comprobar",KeyEvent.VK_P);
	//Un JMenuItem para el Acerca de
	JMenuItem jmiBorrar=new JMenuItem ("Borrar",KeyEvent.VK_B);	
	
	//un GridLayout para el panel botonero
	GridLayout gl=new GridLayout(0,1);
	
	Crotaleitor() {
		//Gesti�n de la barra de menus
		jmArchivo.setMnemonic('A');
		jmAyuda.setMnemonic('y');
		
		jmArchivo.add(jmiSerieNumero);
		jmArchivo.add(jmiCompleto);
		jmArchivo.add(jmiComprobar);
		jmArchivo.add(jmiBorrar);
		jmbMenu.add(jmArchivo);
		
		jmAyuda.add(jmiAcerca);
		jmbMenu.add(jmAyuda);
		this.setJMenuBar(jmbMenu);
		
		//metodo de salida
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		//Establecemos el Layout
		setLayout(gbl);
		setTitle("Crotaleitor");
		// pongo la fuente correcto al JLabel
		jlResultado.setFont(correcto);
		//a�adimos los JRadioButtons al BottonGroup
		bg.add(jrbSerieNumero);
		bg.add(jrbCompleto);
		//a�ado los botones al Botonero
		jpBotonero.add(jbComprobar);
		jbComprobar.addActionListener(this);
		jbBorrar.addActionListener(this);
		jpBotonero.add(jbBorrar);
		jpBotonero.setLayout(gl);
		
		//los listeners a los jMenuItems
		jmiCompleto.addActionListener(this);
		jmiAcerca.addActionListener(this);
		jmiBorrar.addActionListener(this);
		jmiComprobar.addActionListener(this);
		jmiSerieNumero.addActionListener(this);
				
		
		//JRadioButton jrbSerieNumero
		gbc.gridx=0;
		gbc.gridy=0;
		gbc.gridwidth=1;
		gbc.weightx=1;
		gbc.anchor=GridBagConstraints.WEST;
		add(jrbSerieNumero,gbc);
		
		//JRadioButton jrbCompleto
		gbc.gridx=0;
		gbc.gridy=1;
		add(jrbCompleto,gbc);
		
		//jtfSerie
		gbc.gridx=1;
		gbc.gridy=0;
		gbc.ipadx=50;
		gbc.anchor=GridBagConstraints.CENTER;
		add(jtfCa,gbc);
		
		//jtfNumero
		gbc.gridx=2;
		gbc.gridy=0;
		add(jtfSerie,gbc);
		
		gbc.gridx=3;
		gbc.gridy=0;
		add(jtfNumero,gbc);
		
		//jtfCompleto
		gbc.gridx=1;
		gbc.gridy=1;
		gbc.gridwidth=3;
		gbc.fill=GridBagConstraints.HORIZONTAL;
		add(jtfCompleto,gbc);
		gbc.gridwidth=1;
		gbc.fill=GridBagConstraints.NONE;
		gbc.anchor=GridBagConstraints.NONE;
		
		//boton comprobar
		gbc.gridx=0;
		gbc.gridy=2;
		gbc.anchor=GridBagConstraints.CENTER;
		add(jpBotonero,gbc);
		
		//JLabel Resultado
		gbc.gridx=1;
		gbc.gridy=2;
		gbc.gridwidth=3;
		gbc.anchor=GridBagConstraints.CENTER;
		add(jlResultado,gbc);
		
		jrbCompleto.setSelected(true);
		jtfCompleto.requestFocus();
	
	}

	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(jbComprobar)||e.getSource().equals(jmiComprobar)){
			//Lo primero comprobar si nos han pasado serie y numero o completo
			if (jrbSerieNumero.isSelected()) {
				//nos han pasado serie y numero				
				comprobarCrotal(jtfCa.getText(),jtfSerie.getText(),jtfNumero.getText());
			} else {
				//nos han pasado numero completo
				try {
					comprobarCrotal(jtfCompleto.getText(4, 2),jtfCompleto.getText(6, 4),jtfCompleto.getText(10, 4));
				} catch (BadLocationException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}		
		}else if(e.getSource().equals(jbBorrar)||e.getSource().equals(jmiBorrar)){
				borrar();
				}else if(e.getSource().equals(jmiCompleto)){
					jrbCompleto.setSelected(true);
				}else{
					jrbSerieNumero.setSelected(true);
			
		}
	}
	
	public void comprobarCrotal(String ca,String serie,String numero){
		int acumulador=0;
		if (Integer.parseInt(serie)<200) {
			//la serie es menor de 200
			String crotal="84"+ca+serie+numero;
			for (int i = 0; i < crotal.length(); i++) {
				if(i%2==0){
					acumulador+=(Integer.parseInt(crotal.substring(i, i+1)));
					acumulador+=2;
				}else{
					acumulador+=(Integer.parseInt(crotal.substring(i, i+1)));
					acumulador+=4;
				}					
			}//cierre for
		}else{
			//la serie es mayor de 200
			String crotal=ca+serie+numero;
			int contador=1;
			for (int i = 0; i < crotal.length(); i++) {
				acumulador+=(Integer.parseInt(crotal.substring(i, i+1))*contador);
				contador++;
				if (contador==10) {
					contador=1;
				}
			}//cierre for
			
		}
		//damos valor al JLabel
		jlResultado.setText("ES0"+acumulador%10+ca+serie+numero);
	}
	
	public void borrar(){
		jtfCa.setText("");
		jtfSerie.setText("");
		jtfNumero.setText("");
		jtfCompleto.setText("");
	}
	
	
	public static void main(String[] args) {		
		Crotaleitor ventana=new Crotaleitor();		
		ventana.setSize(440,180);
		ventana.setVisible(true);	
		ventana.setResizable(false);
	}

}
