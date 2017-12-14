package gui;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.net.URL;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class VentanaInicial extends JFrame {

	private static final long serialVersionUID = 6264488990518756617L;
	private JPanel contentPane;
	private JLabel fondo;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaInicial frame = new VentanaInicial();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public VentanaInicial() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 390, 548);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);
		
		setIconImage(Toolkit.getDefaultToolkit().getImage(VentanaInicial.class.getResource("/imagenes/icono.png")));

		
		//Imagen de fondo
		fondo = new JLabel(new ImageIcon(getClass().getResource(
				"/imagenes/background.png")));
		fondo.setPreferredSize(new Dimension(700, 700));
		fondo.setBounds(0, 0, 390, 548);
		
		//Informacion intregrantes
		JLabel lblAbout = new JLabel(new ImageIcon(getClass().getResource("/imagenes/about.png")));
		lblAbout.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				String msg= "INTEGRANTES: \n\n Gómez Germán  - LU: 107031 \n Massetti Matias - LU: 107954 \n\n Universidad Nacional del Sur - 2017";
				JOptionPane.showMessageDialog (null,msg, "Informacion", JOptionPane.INFORMATION_MESSAGE);

			}
		});
		fondo.add(lblAbout);
		lblAbout.setBounds(345,10,32,32);
		
		
		//Titulo principal
		URL url = VentanaInicial.class.getResource("/imagenes/sudoku.png");
		ImageIcon icono = new ImageIcon(url);
		JLabel label = new JLabel("Sudoku");
		label.setIcon(icono);
		label.setBounds(6, 58, 368, 104);
		contentPane.add(label);
	
		//Descripcion del juego
		JLabel lblDescripcion = new JLabel(new ImageIcon(getClass().getResource("/imagenes/descripcion.png")));
		lblDescripcion.setBounds(15, 176, 366, 280);
		fondo.add(lblDescripcion);
		
		
		//Boton Comenzar
		JButton btnComenzar = new JButton(new ImageIcon(getClass().getResource("/imagenes/comenzar.png")));
		btnComenzar.setBorderPainted(false); 
        btnComenzar.setContentAreaFilled(false); 
        btnComenzar.setFocusPainted(false); 
        btnComenzar.setOpaque(false);
		btnComenzar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				GUI gui= new GUI();
				gui.setVisible(true);
				
			}
		});
		btnComenzar.setBounds(10, 464, 173, 49);
		
		//Boton Salir
		JButton btnSalir = new JButton(new ImageIcon(getClass().getResource("/imagenes/salir.png")));
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		btnSalir.setOpaque(false);
		btnSalir.setFocusPainted(false);
		btnSalir.setContentAreaFilled(false);
		btnSalir.setBorderPainted(false);
		btnSalir.setBounds(204, 464, 173, 49);
		
		
		//Se agregan los botones 
		fondo.add(btnComenzar);
		fondo.add(btnSalir);
		fondo.add(lblDescripcion);
		getContentPane().add(fondo);
		


		
		
	
		
		
		
	}
}
