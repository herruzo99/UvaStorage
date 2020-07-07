public class ComplejidadCiclomatica {
    public static void main(String[] args) {
	int i = 0;
	/******* Pruebas de las palabras claves *******/
	if(i < 10); //condicion solo
	if(i < 10){ int a = 0; } // if en una linea
	if(i < 10){
		int a = 0;
	} // if en varias lineas
	if
	(i < 10); // if cortado
	
	if( i <10){ int a = 0;};if( i < 9){ int a = 0;}; //dos ifs seguidos
	if(i < 10){
		int a = 0;
	} else{
		int a = 1;
	}

	if(i < 10){
		int a = 0;
	} else if (i < 11){
		int a = 1;
	}
	
	for(i = 0; i  < 10; i ++); // for solo
	for(i = 0; i  < 10; i ++){ int a = i;} // for en una línea
	for(i = 0; i  < 10; i ++){ 
		int a = i;
	}; // for en varias lineas

	while(i > 10); // while solo
	while(i < 10){ int a = i; i++;} // while en una línea
	while(i < 10){
		int a = i;
		i++;
	}// while en varias línea
	
	switch(i){
		case 0: // case solo
		case 1: break; // case en una linea
		case 2:
			int a = 0;
			break; // case en varias lineas
		default:
			break; // no se cuenta en complejidad
	}
	
	try{
		int a = 0;
	}
	catch ( ArithmeticException e){}
	catch ( Exception e){
		int a = 0;
	}
	finally{ // no cuenta complejidad
		int a = 1;
	}
	

	int a = (i > 10) ? 1 : 0; // en una línea
	a = (i > 10) 
		? 
		1 : 0; // multi línea

	if( i < 10 && i < 100 || i > 1); // prueba and y or

	/******** Pruebas de no tenes falsos positivos *******/
	// Dentro de comentario if catch for && while || case ?
	int ifnum = 0;
	int for_num = 0; 
	int numcatch = 0; 
	int whilenum = 0; 
	int num_case = 0;
	iffuncion();
	for_funcion();
	catchfuncion();
	funcionwhile();
	funcion_case();

	/******** Pruebas anidadas *******/
	int numero = 5;
	try{
		for(i = (a > 1) ?  0: 1; i < 10; i++){
			if( i < 2 && a < 2){
			
			}
		}
	} catch ( Exception e){
		a = 0;
	}
    }
    
    public static void iffuncion(){};
    public static void for_funcion(){};
    public static void catchfuncion(){};
    public static void funcionwhile(){};
    public static void funcion_case(){};
}
