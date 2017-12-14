package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.net.URL;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import logica.Logica;

public class GUI extends JFrame {
	// atributos
	private static final long serialVersionUID = 1L;
	protected JPanel contentPane;
	protected JButton[] menu;
	protected JTextField[][] casillas;
	protected Logica logica;
	protected JLabel fondo;
	protected JButton comprobar;
	protected JButton resolver;
	protected JButton volver;
	protected JButton reiniciar;
	protected JButton crearManualmente;
	protected JButton[] botoneraNumeros;
	private JTextField casillaSeleccionada;
	private int filaActual, columnaActual;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI frame = new GUI();
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
	public GUI() {
		// Agregamos el fondo y otras configuraciones generales
		this.configurarVentana();
		getContentPane().add(fondo);	
		setIconImage(Toolkit.getDefaultToolkit().getImage(VentanaInicial.class.getResource("/imagenes/icono.png")));	
		fondo.repaint();

		// creamos los botones del tablero
		this.crearBotones();

		// ponemos las opciones principales del juego
		this.ponerMenuInicio();
		
		
	}

	/*******************************************************************************/
	/******************************** METODOS ***************************************/
	/*****************************************************************************/

	private void configurarVentana() {
		// creamos la ventana
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 390, 548);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBackground(Color.BLACK);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);

		// Imagen de fondo
		fondo = new JLabel(new ImageIcon(getClass().getResource("/imagenes/background.png")));
		fondo.setPreferredSize(new Dimension(700, 700));
		fondo.setBounds(0, 0, 390, 548);

	}

	private void crearBotones() {
		crearManualmente = new JButton("Cargar Tablero Actual");
		crearManualmente.addActionListener(new OyenteCargarTablero());
		crearManualmente.setBounds(5, 410, 378, 39);
		crearManualmente.setBackground(Color.LIGHT_GRAY);
		setearPropiedadesBoton(crearManualmente, "cargar_tablero_actual");
		fondo.add(crearManualmente);
		crearManualmente.setVisible(false);

		menu = new JButton[4];
		menu[0] = new JButton("Cargar n\u00FAmeros manualmente");
		menu[0].setFont(new Font("Arial", Font.PLAIN, 14));
		menu[0].setBackground(Color.LIGHT_GRAY);
		menu[0].addActionListener(new OyenteCargarManualmente());
		menu[0].setBounds(10, 10, 360, 120);
		setearPropiedadesBoton(menu[0], "cargar_manualmente");
		fondo.add(menu[0]);

		menu[1] = new JButton("Configuaci\u00F3n 1");
		menu[1].setFont(new Font("Arial", Font.PLAIN, 14));
		menu[1].addActionListener(new OyenteConfiguracion1());
		menu[1].setBackground(Color.LIGHT_GRAY);
		menu[1].setBounds(10, 135, 360, 120);
		setearPropiedadesBoton(menu[1], "facil");
		fondo.add(menu[1]);

		menu[2] = new JButton("Configuaci\u00F3n 2");
		menu[2].setFont(new Font("Arial", Font.PLAIN, 14));
		menu[2].addActionListener(new OyenteConfiguracion2());
		menu[2].setBackground(Color.LIGHT_GRAY);
		menu[2].setBounds(10, 260, 360, 120);
		setearPropiedadesBoton(menu[2], "medio");
		fondo.add(menu[2]);

		menu[3] = new JButton("Configuaci\u00F3n 3");
		menu[3].setFont(new Font("Arial", Font.PLAIN, 14));
		menu[3].addActionListener(new OyenteConfiguracion3());
		menu[3].setBackground(Color.LIGHT_GRAY);
		menu[3].setBounds(10, 385, 360, 120);
		setearPropiedadesBoton(menu[3], "dificil");
		fondo.add(menu[3]);

		this.sacarMenuInicio();

		// agregar botones
		comprobar = new JButton();
		comprobar.addActionListener(new OyenteComprobar());
		comprobar.setBounds(5, 420, 182, 39);
		fondo.add(comprobar);
		setearPropiedadesBoton(comprobar, "comprobar");

		resolver = new JButton();
		resolver.addActionListener(new OyenteResolver());
		resolver.setBounds(192, 420, 182, 39);
		fondo.add(resolver);
		setearPropiedadesBoton(resolver, "resolver");

		volver = new JButton();
		volver.addActionListener(new OyenteVolver());
		volver.setBounds(5, 465, 182, 39);
		fondo.add(volver);
		setearPropiedadesBoton(volver, "volver");

		reiniciar = new JButton();
		reiniciar.addActionListener(new OyenteReiniciar());
		reiniciar.setBounds(192, 465, 182, 39);
		fondo.add(reiniciar);
		setearPropiedadesBoton(reiniciar, "reiniciar");

		comprobar.setVisible(false);
		resolver.setVisible(false);
		volver.setVisible(false);
		reiniciar.setVisible(false);

	}

	private void ponerMenuInicio() {
		for (int i = 0; i < menu.length; i++) {
			menu[i].setVisible(true);
		}
		repaint();
	}

	private void sacarMenuInicio() {
		for (int i = 0; i < menu.length; i++) {
			menu[i].setVisible(false);
		}
		repaint();
	}
	private void sacarBotonera(){
		for (int i = 0; i <= 9; i++) {
			botoneraNumeros[i].setVisible(false);
		}
		repaint();
	}

	private void generarCasillas(int[][] tablero) {
		int x = 5;
		int y = 35;
		casillas = new JTextField[9][9];
		for (int i = 1; i <= casillas.length; i++) {
			for (int j = 1; j <= casillas[0].length; j++) {
				JTextField t = new JTextField();
				// formato de celda
				t.setHorizontalAlignment(SwingConstants.CENTER);
				t.setColumns(10);
				t.setBounds(x, y, 40, 40);
				t.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
				t.setFont(new Font("Courier", Font.BOLD + Font.ITALIC, 20));

				// agregar oyentes
				t.addActionListener(new OyenteCasilla(i - 1, j - 1));
				t.addMouseListener(new OyenteCasilla(i - 1, j - 1));

				// contenido
				if (tablero[i - 1][j - 1] > 0 && tablero[i - 1][j - 1] < 10) {
					t.setText("" + tablero[i - 1][j - 1]);
					t.setEditable(false);
				}

				casillas[i - 1][j - 1] = t;
				fondo.add(t);
				x += 40;
				if (j % 3 == 0)
					x += 5;
			}
			y += 40;
			x = 5;
			if (i % 3 == 0) {
				y += 5;
			}
		 
			repaint();
		}
		this.crearBotoneraNumeros();
	}

	private void crearBotoneraNumeros() {

		int x = 5;
		int y = 5;
		botoneraNumeros = new JButton[10];
		JButton btn;
		for (int i = 0; i <= 9; i++) {
			btn = new JButton();
			if (i == 0) {
				btn.setText("x");
			} else {
				btn.setText("" + i);
			}
			btn.setBounds(x, y, 35, 25);
			setearPropiedadesBoton(btn, btn.getText());
			btn.addActionListener(new OyenteNumeroElegido());
			botoneraNumeros[i] = btn;
			fondo.add(btn);
			repaint();
			x += 37;
		}
	}
	private ImageIcon getImagen(String nombre) {
		URL url = GUI.class.getResource("/imagenes/" + nombre + ".png");
		ImageIcon icono = new ImageIcon(url);

		return icono;
	}

	private void setearPropiedadesBoton(JButton btn, String nombre) {
		btn.setIcon(getImagen(nombre));
		btn.setBorderPainted(false);
		btn.setContentAreaFilled(false);
		btn.setFocusPainted(false);
		btn.setOpaque(false);
	}

	private void ponerMenuJuego() {
		comprobar.setVisible(true);
		resolver.setVisible(true);
		volver.setVisible(true);
		reiniciar.setVisible(true);

	}

	private void sacarMenuJuego() {
		comprobar.setVisible(false);
		resolver.setVisible(false);
		volver.setVisible(false);
		reiniciar.setVisible(false);
	}

	private void sacarCasillas() {
		for (int f = 0; f < casillas.length; f++)
			for (int c = 0; c < casillas[0].length; c++) {
				casillas[f][c].setText("");
				casillas[f][c].setVisible(false);
			}
		for (int i = 0; i < botoneraNumeros.length; i++) {
			botoneraNumeros[i].setVisible(false);
		}
	}

	private void limpiarSeleccion() {
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				JTextField txt = casillas[i][j];
				if (txt.getText().equals("")) {
					txt.setBackground(Color.white);
				} else {
					txt.setBackground(Color.LIGHT_GRAY);
				}
			}
		}
	}

	private boolean verificarPartidaGanada(){
		int [][] tablero = logica.getTablero();
		boolean gano = true; 
		for(int f = 0; f<tablero.length && gano; f++)
			for(int c=0; c<tablero[0].length && gano;c++){
				gano = tablero[f][c]!=0;
					
			}
		return gano;
	}

	/*******************************************************************************/
	/******************************** OYENTES ***************************************/
	/*****************************************************************************/

	private class OyenteCargarManualmente implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			int[][] tablero = new int[9][9];
			logica = new Logica(tablero);
			generarCasillas(tablero);
			sacarMenuInicio();
			volver.setVisible(true);
			reiniciar.setVisible(true);
			crearManualmente.setVisible(true);
			repaint();
		}
	}

	private class OyenteCargarTablero implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			for (int f = 0; f < casillas.length; f++)
				for (int c = 0; c < casillas[0].length; c++) {
					casillas[f][c].setText("");
					casillas[f][c].setVisible(false);
				}

			int[][] tablero = logica.getTablero();

			logica = new Logica(tablero);
			generarCasillas(tablero);
			ponerMenuJuego();
			crearManualmente.setVisible(false);
			repaint();
		}
	}

	private class OyenteConfiguracion1 implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			int[][] tablero = new int[9][9];
			tablero[0][1] = 4;
			tablero[0][3] = 8;
			tablero[0][7] = 3;
			tablero[1][1] = 5;
			tablero[1][2] = 7;
			tablero[1][5] = 3;
			tablero[2][5] = 4;
			tablero[7][3] = 6;
			tablero[7][4] = 4;
			tablero[7][8] = 7;
			tablero[8][3] = 7;
			tablero[8][5] = 9;
			tablero[8][7] = 8;
			tablero[8][8] = 4;

			logica = new Logica(tablero);
			generarCasillas(tablero);
			ponerMenuJuego();
			sacarMenuInicio();
			repaint();
			
		}
	}

	private class OyenteConfiguracion2 implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			int[][] tablero = new int[9][9];
			tablero[0][1] = 4;
			tablero[0][3] = 8;
			tablero[0][7] = 3;
			tablero[1][1] = 5;
			tablero[1][2] = 7;
			tablero[1][5] = 3;
			tablero[2][5] = 4;
			tablero[2][7] = 2;
			tablero[2][8] = 9;
			tablero[3][1] = 2;
			tablero[3][5] = 7;
			tablero[3][6] = 5;
			tablero[4][0] = 7;
			tablero[4][3] = 4;
			tablero[4][4] = 6;
			tablero[5][0] = 4;
			tablero[5][5] = 5;
			tablero[5][6] = 9;
			tablero[5][7] = 7;
			tablero[5][8] = 2;
			tablero[6][1] = 7;
			tablero[6][5] = 8;
			tablero[6][6] = 6;
			tablero[6][8] = 3;
			tablero[7][3] = 6;
			tablero[7][4] = 4;
			tablero[7][8] = 7;
			tablero[8][3] = 7;
			tablero[8][5] = 9;
			tablero[8][7] = 8;
			tablero[8][8] = 4;

			logica = new Logica(tablero);
			generarCasillas(tablero);
			ponerMenuJuego();
			sacarMenuInicio();
			repaint();
		}
	}

	private class OyenteConfiguracion3 implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			int[][] tablero = new int[9][9];
			tablero[0][0] = 8;
			tablero[1][2] = 3;
			tablero[1][3] = 6;
			tablero[2][1] = 7;
			tablero[2][4] = 9;
			tablero[2][6] = 2;
			tablero[3][1] = 5;
			tablero[3][5] = 7;
			tablero[4][4] = 4;
			tablero[4][5] = 5;
			tablero[4][6] = 7;
			tablero[5][3] = 1;
			tablero[5][7] = 3;
			tablero[6][2] = 1;
			tablero[6][7] = 6;
			tablero[6][8] = 8;
			tablero[7][2] = 8;
			tablero[7][3] = 5;
			tablero[7][7] = 1;
			tablero[8][1] = 9;
			tablero[8][6] = 4;

			logica = new Logica(tablero);
			generarCasillas(tablero);
			ponerMenuJuego();
			sacarMenuInicio();
			repaint();
		}
	}

	private class OyenteVolver implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			sacarMenuJuego();
			ponerMenuInicio();
			sacarBotonera();
			crearManualmente.setVisible(false);
			sacarCasillas();
			logica.eliminarTodo();
			repaint();
		}
	}

	private class OyenteCasilla implements ActionListener, MouseListener {
		int nroFila, nroCol;

		public OyenteCasilla(int f, int c) {
			this.nroFila = f;
			this.nroCol = c;
		}

		public void actionPerformed(ActionEvent e) {
			try {
				// apretoEnter= true;

				if (casillas[nroFila][nroCol].isEditable()) { // primero chequeo
																// que no sea
																// una casilla
																// predeterminada
																// del tablero
					String valorIngresado = e.getActionCommand();

					if (valorIngresado.equals("")) {
						logica.eliminarCasilla(nroFila, nroCol);
						casillas[nroFila][nroCol].setText("");
					} else {
						int valorCasilla = Integer.parseInt(e.getActionCommand());

						// verificamos que sea mayor a 0 y menor a 10
						if (valorIngresado.length() > 1 || !logica.verificarNumero(valorCasilla)) {
							JOptionPane.showMessageDialog(contentPane, "El valor debe ser un número entre 1 y 9",
									"Jugada inválida", JOptionPane.ERROR_MESSAGE);
							casillas[nroFila][nroCol].setText("");
							logica.eliminarCasilla(nroFila, nroCol);
							// apretoEnter= false;
						} else {
							if (!logica.verificarFila(valorCasilla, nroFila)) {
								JOptionPane.showMessageDialog(contentPane, "El valor ya se encuentra en la fila",
										"Jugada inválida", JOptionPane.ERROR_MESSAGE);
								casillas[nroFila][nroCol].setText("");
								logica.eliminarCasilla(nroFila, nroCol);
								// apretoEnter=false;
							} else {
								if (!logica.verificarColumna(valorCasilla, nroCol)) {
									JOptionPane.showMessageDialog(contentPane, "El valor ya se encuentra en la columna",
											"Jugada inválida", JOptionPane.ERROR_MESSAGE);
									casillas[nroFila][nroCol].setText("");
									logica.eliminarCasilla(nroFila, nroCol);
									// apretoEnter= false;
								} else {
									if (!logica.verificarBloque(valorCasilla, nroFila, nroCol)) {
										JOptionPane.showMessageDialog(contentPane,
												"El valor ya se encuentra en la región", "Jugada inválida",
												JOptionPane.ERROR_MESSAGE);
										casillas[nroFila][nroCol].setText("");
										logica.eliminarCasilla(nroFila, nroCol);
										// apretoEnter=false;
									} else {
										// es válido el número
										casillas[nroFila][nroCol].setForeground(Color.RED);
										contentPane.requestFocus();
										logica.insertar(valorCasilla, nroFila, nroCol);
										if(verificarPartidaGanada())
											JOptionPane.showMessageDialog(contentPane, "¡Felicitaciones! Ganaste","Partida ganada", JOptionPane.INFORMATION_MESSAGE);
											
									}
								}
							}
						}
					}

				}

			} catch (NumberFormatException exception) {
				JOptionPane.showMessageDialog(contentPane, "Debe ingresar un número!", "Intente nuevamente",
						JOptionPane.ERROR_MESSAGE);
				casillas[nroFila][nroCol].setText("");
			}
			repaint();
		}

		@Override
		public void mouseClicked(MouseEvent arg0) {

			casillaSeleccionada = casillas[nroFila][nroCol];
			casillas[nroFila][nroCol].requestFocus();

			filaActual = nroFila;
			columnaActual = nroCol;

			limpiarSeleccion();
			// Pintar fila y columna donde esta el TextField seleccionado
			for (int i = 0; i < 9; i++) {
				casillas[nroFila][i].setBackground(new Color(255, 255, 0));
				casillas[i][nroCol].setBackground(new Color(255, 255, 0));

			}
			// Pintar subregion donde esta el TextField seleccionado
			int maxFila = (int) ((((Math.floor((nroFila) / 3)) + 1) * 3));
			int maxCol = (int) ((((Math.floor((nroCol) / 3)) + 1) * 3));
			int minFila = maxFila - 2;
			int minCol = maxCol - 2;
			for (int i = 1; i < 10; i++) {
				for (int j = 1; j < 10; j++) {
					if (i >= minFila && i <= maxFila && j >= minCol && j <= maxCol) {
						casillas[i - 1][j - 1].setBackground(new Color(255, 255, 0));
					}
				}
			}
			// Destacar con otro color la casilla seleccionada

			casillas[nroFila][nroCol].setBackground(new Color(135, 196, 31));
			repaint();

		}

		@Override
		public void mouseEntered(MouseEvent arg0) {
		}

		@Override
		public void mouseExited(MouseEvent arg0) {
		}

		@Override
		public void mousePressed(MouseEvent arg0) {
		}

		@Override
		public void mouseReleased(MouseEvent arg0) {
		}

	}

	private class OyenteComprobar implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			if (!logica.resolver()) {
				JOptionPane.showMessageDialog(contentPane, "No se puede resolver el sudoku!");
			} else {
				JOptionPane.showMessageDialog(contentPane, "Se puede resolver!");
				for (int i = 0; i < casillas.length; i++) {
					for (int j = 0; j < casillas[0].length; j++) {
						if (casillas[i][j].getText().equals("")) {
							logica.eliminarCasilla(i, j);
						}
					}
				}
			}
			repaint();
		}
	}

	private class OyenteResolver implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			if (!logica.resolver()) {
				JOptionPane.showMessageDialog(contentPane, "No se puede resolver el sudoku!");
			} else {
				int[][] resultado = logica.getTablero();
				for (int i = 0; i < casillas.length; i++) {
					for (int j = 0; j < casillas[0].length; j++) {
						casillas[i][j].setText("" + resultado[i][j]);
					}
				}
			}
			repaint();
		}
	}

	private class OyenteReiniciar implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			logica.reniciar();
			for (int i = 0; i < casillas.length; i++) {
				for (int j = 0; j < casillas[0].length; j++) {
					if (casillas[i][j].isEditable())
						casillas[i][j].setText("");
				}
			}
			repaint();
		}
	}

	private class OyenteNumeroElegido implements ActionListener {

		public void actionPerformed(ActionEvent arg0) {
			if (casillas[filaActual][columnaActual].isEditable()) {
				if (casillaSeleccionada != null) {
					if (arg0.getActionCommand().equals("x")) { // caso donde se
																// elige la X ,
																// que sirve
																// para borrar
																// el contenido
																// de una
																// casilla
						logica.eliminarCasilla(filaActual, columnaActual);
						casillas[filaActual][columnaActual].setText("");
					} else { // se eligio uno de los numeros de la botonera
						casillaSeleccionada.setText(arg0.getActionCommand());
						casillaSeleccionada.repaint();

						int valorCasilla = Integer.parseInt(casillaSeleccionada.getText());

						if (!logica.verificarNumero(valorCasilla)) {
							JOptionPane.showMessageDialog(contentPane, "El valor debe ser un número entre 1 y 9",
									"Jugada inválida", JOptionPane.ERROR_MESSAGE);
							casillas[filaActual][columnaActual].setText("");
							logica.eliminarCasilla(filaActual, columnaActual);
							// apretoEnter=false;
						} else {
							if (!logica.verificarFila(valorCasilla, filaActual)) {
								JOptionPane.showMessageDialog(contentPane, "El valor ya se encuentra en la fila",
										"Jugada inválida", JOptionPane.ERROR_MESSAGE);
								casillas[filaActual][columnaActual].setText("");
								logica.eliminarCasilla(filaActual, columnaActual);
								// apretoEnter=false;
							} else {
								if (!logica.verificarColumna(valorCasilla, columnaActual)) {
									JOptionPane.showMessageDialog(contentPane, "El valor ya se encuentra en la columna",
											"Jugada inválida", JOptionPane.ERROR_MESSAGE);
									casillas[filaActual][columnaActual].setText("");
									logica.eliminarCasilla(filaActual, columnaActual);
									// apretoEnter=false;
								} else {
									if (!logica.verificarBloque(valorCasilla, filaActual, columnaActual)) {
										JOptionPane.showMessageDialog(contentPane,
												"El valor ya se encuentra en la región", "Jugada inválida",
												JOptionPane.ERROR_MESSAGE);
										casillas[filaActual][columnaActual].setText("");
										logica.eliminarCasilla(filaActual, columnaActual);
									} else {
										// El numero no esta ni en la fila ni en
										// la
										// columna ni la region
										casillas[filaActual][columnaActual].setForeground(Color.RED);
										contentPane.requestFocus();

										// insertamos en prolog el numero
										logica.insertar(valorCasilla, filaActual, columnaActual);
										
										if(verificarPartidaGanada())
											JOptionPane.showMessageDialog(contentPane, "¡Felicitaciones! Ganaste","Partida ganada", JOptionPane.INFORMATION_MESSAGE);
										

									}
								}
							}
						}
					}

				}
			}
			repaint();
		}

	}
}
