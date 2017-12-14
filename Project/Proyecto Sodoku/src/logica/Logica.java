package logica;
import org.jpl7.Query;

public class Logica {
	//atributos
	protected int tableroPorDefecto[][];
	
	//constructor
	public Logica(int tablero[][]){
		
		//cargamos el archivo de prolog
		this.cargarLogica();
		
		this.tableroPorDefecto = new int[9][9];
		//inicializamos el tablero
		for(int f=0; f<tablero.length; f++)
			for(int c=0; c<tablero[0].length; c++){
				int numero = tablero[f][c];
				this.tableroPorDefecto[f][c] = numero;
				if(tablero[f][c]>0 && tablero[f][c]<10)
					this.insertar(tablero[f][c],f,c);
			}
		
		
	}
	
	//metodos publicos
	public int[][] getTablero(){
		int[][] tablero = new int[9][9];
		Query c1= new Query("sudoku(L)");		
		char cad[][] = codificar(c1.oneSolution().get("L").toString());
		
		
		for(int f=0; f<cad.length; f++)
			for(int c=0; c<cad[0].length; c++){
				tablero[f][c] =  Integer.parseInt(""+cad[f][c]);
			}
		return tablero;
	}	
	public int[][] getTableroPorDefecto(){
		return this.tableroPorDefecto;
	}
	public boolean verificarNumero(int valor){
		return this.consulta("num("+valor+")");
	}
	public boolean verificarFila(int valor, int fila){
		return this.consulta("validar_fila("+valor+","+(fila+1)+")");
	}
	public boolean verificarColumna(int valor, int columna){
		return this.consulta("validar_columna("+valor+","+(columna+1)+")");
	}
	public boolean verificarBloque(int valor, int fila, int columna){
		return this.consulta("validar_bloque("+valor+","+(fila+1)+","+(columna+1)+")");
	}
	public boolean insertar(int valor, int fila, int columna){
		boolean esta = this.consulta("insertar("+valor+","+(fila+1)+","+(columna+1)+")");
		return esta;
	}
	public boolean eliminarCasilla(int fila, int columna){
		boolean elimino = this.consulta("eliminar("+(fila+1)+","+(columna+1)+")");
		if(elimino){
			this.getTablero()[fila][columna] = 0;
		}
		return elimino;
	}
	public boolean eliminarTodo(){
		return this.consulta("eliminarTodo()");		
	}
	public boolean resolver(){
		int [][] tablero = getTablero();
		cargarLogica();
		for(int f=0; f<tablero.length;f++)
			for(int c=0; c<tablero[0].length; c++)
				if(tablero[f][c]!=0)
					insertar(tablero[f][c],f,c);
		return this.consulta("resolver()");
	}
	public void reniciar(){
		this.eliminarTodo();
		for(int f=0; f<9; f++)
			for(int c=0;c<9;c++){
				if(tableroPorDefecto[f][c]!=0)
					insertar(tableroPorDefecto[f][c],f,c);
			}
	}
	
	//metodos privados
	private void cargarLogica(){
		String consulta = "consult('logica.pl')";
		Query con = new Query(consulta);
		con.hasSolution();		
	}
	private boolean consulta(String consulta){
		Query con = new Query(consulta);
		return con.hasSolution();	
	}
	public void imprimirEstado(){
		Query c1= new Query("sudoku(L)");
		
		char cad[][] = codificar(c1.oneSolution().get("L").toString());
		
		for(int i = 0; i<cad.length; i++){
			for (int j=0;j<cad[0].length;j++) {
				System.out.print("["+cad[i][j]+"]");
			}
			System.out.println();
		}
		System.out.println();
	}
	private char[][] codificar(String cad){
		int col = 0;
		int fil = 0;
		int j = 0;
		char cadena[][] = new char[9][9];
		while(j<cad.length()){	
			if(col==9){
				col=0;
				fil++;
			}
			char c = cad.charAt(j);
			if(c=='0' || c=='1' || c=='2' || c=='3' || c=='4' || c=='5' || c=='6' || c=='7' || c=='8' || c=='9'){				
				cadena[fil][col] = c;
				col++;
			}
			j++;
		}		
		
		return cadena;
	}
}