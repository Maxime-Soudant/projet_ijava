class affichageCombat extends Program{
    void algorithm(){
    afficherCombat(Loustique.Pythongore);
    }
    void afficherCombat(Loustique loustique){
	clearScreen();

	//loustique
	cursor(5,30);
	print(loustique);
	//
	
	//question
	cursor(30,10);
	print("combien font 8x0");
	//

	//reponses
	cursor(34,10);
	print("1- 0");
	
	cursor(36,10);
	print("3- 8");
	
	cursor(34,55);
	print("2- 12");
	
	cursor(36,55);
	print("4- 1");
	//
	cursor(37,10);
	println("Quel réponse choisissez vous ?");
	cursor(38,20);
	repJoueur();
        
    }
    int repJoueur(){
	int rep=0;
	rep=readInt();
	clearLine();
	while(rep>4 || rep<1){
	    cursor(38,20);
	    println("La réponse doit être compris entre 1 et 4");
	    rep=readInt();
	}
	return rep;
    }
}
