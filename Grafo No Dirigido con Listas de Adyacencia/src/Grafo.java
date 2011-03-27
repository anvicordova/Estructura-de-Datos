class Nodo{
	int vertice;
	int pesoArista;
	Nodo sgte;
	
	public Nodo(int v, int p){
		this.vertice = v;
		this.pesoArista = p;
		
		sgte = null;
	}
}

public class Grafo {
	Nodo grafo[];
	int nroVertices;
	
	public Grafo(int nroVertices){
		this.nroVertices = nroVertices;
		grafo = new Nodo[this.nroVertices];
		
		for(int i = 0; i < grafo.length; i++){
			grafo[i] = null;
		}
	}
	
	public boolean existeArista(int v1, int v2){
		Nodo actual = grafo[v1];
		while(actual != null){
			if(actual.vertice == v2) return true;
			actual = actual.sgte;
		}
		return false;
	}
	
	public void insertarArista(int v1, int v2, int peso){
		if(!existeArista(v1, v2)){
			Nodo nuevo = new Nodo(v2, peso);
			
			if(grafo[v1] == null){
				grafo[v1] = nuevo;
				insertarArista(v2, v1,peso);				
			}
			else{
				Nodo actual = grafo[v1];
				
				while(actual.sgte != null)
				{ 
					actual = actual.sgte;
				}
				actual.sgte = nuevo;
				insertarArista(v2,v1,peso);				
			}
		}		
	}
	
	public void eliminarArista(int v1, int v2){
		if(existeArista(v1, v2)){
			if(grafo[v1].vertice == v2){
				grafo[v1] = grafo[v1].sgte;
				eliminarArista(v2,v1);
				return;
			}
			Nodo actual = grafo[v1].sgte;
			Nodo anterior = grafo[v1];
			
			while(actual != null){
				if(actual.vertice == v2){
					anterior.sgte = actual.sgte;
					eliminarArista(v2,v1);
					return;
				}
				anterior = actual;
				actual = actual.sgte;
			}
		}		
	}
	
	public void mostrarGrafo(){
		for( int i = 0; i < grafo.length; i++){
			Nodo actual = grafo[i];
			
			while(actual != null){
				System.out.printf("%d -> " , i);
				System.out.printf("%d(%d) \n" , actual.vertice, actual.pesoArista);
				
				actual = actual.sgte;
			}
			System.out.println();
		}
	}
	
	public void eliminarGrafo(){
		for( int i = 0; i < grafo.length; i++){
			grafo[i] = null;
		}
	}
	
// ----- Operaciones para obtener la lista de adyacentes -----//
	public boolean existeAdyacentes(int v){
		if(grafo[v] == null) return false;
		return true;
	}
	
	public Nodo getFirstAdy(int v){
		return grafo[v];
	}
	
	//Si retorna null es que no hay más adyacentes
	public Nodo getNextAdy(Nodo anteriorAd){
		return anteriorAd.sgte;
	}
// ----- Fin de operadores para obtener la lista de adyacentes -----//
}
