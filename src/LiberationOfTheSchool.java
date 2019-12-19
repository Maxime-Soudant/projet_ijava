class LiberationOfTheSchool extends Program{
	void menu(){
		clearScreen();

		println("			Liberation Of The School\n\n\n\n1- Jouer\n2- Projet\n3- Credits\n4- Quiter\n\n\nQue veux tu faire ?");
	}

	void jouer(){
		int test=-1;

		clearScreen();

		Joueur eleve = new Joueur();

		do{
			println("Est-tu un garçon ou une fille ? (G ou F) :");
			eleve.sexe=readChar();
		}while (eleve.sexe!='G' && eleve.sexe != 'g' && eleve.sexe!='F' && eleve.sexe!='f');

		println("Quel est le nom de ton école ?");
		eleve.ecole = readString();

		clearScreen();
		
		String sexe;
		if (eleve.sexe=='g' || eleve.sexe=='G'){
			sexe="garçon";
		}else {
			sexe="fille";
		}

		println ("Dieu :\nSalutations jeune " + sexe + ", et bienvenue dans Liberation Of The School, le jeu d’aventure dans lequel tu vas devenir un aventurier hors pair en parcourant ta ville !\n\nTu vas pouvoir rencontrer tes camarades qui auront des missions à te proposer, il faudra les remplir pour les aider.\n\nCes missions peuvent sembler très simples au début mais se corseront rapidement, tu verras. Avant de commencer, peux-tu me dire comment tu t’appelles ?");
		eleve.nomDuJoueur=readString();

		clearScreen();

		println("Dieu : \nTrès bien "+eleve.nomDuJoueur+" , nous allons pouvoir commencer. Avant toute chose, je vais te poser une question afin de voir si tu es apte à aider tes camarades, tu es prêt ?");
		continuer();

		println("Dieu : \nParfait, pour aider tes copains lorsqu’ils te poseront des questions, tu n’auras qu'à écrire la réponse dans l’encadré juste en dessous puis appuyer sur entrer pour valider ta réponse.\nEssayons : combien fait 9x9 =");

		test = readInt();

		while(test != 9){
			println("\nDieu : Raté, essaye encore : ");
			test=readInt();
		}

		clearScreen();

		println("Dieu : \nBravo, tu vas pouvoir commencer à jouer et partir à la rencontre de tes camarades !\nOh j’allais oublier de te présenter mon acolyte; c’est l’ange qui arrive en courant vers nous là-bas.\nIl se nomme Gabriel et il sera là pour t’aider quand tu en auras besoin !\nD’ailleurs, il court drôlement vite aujourd’hui; il a l’air d’avoir hâte de faire ta connaissance !\n\n");
		
		continuer();

		println("Gabriel : \nDIEU ! C’EST TERRIBLE !\n\nDieu : \nCalme toi donc un peu Gabriel, qu’a t’il bien pu se passer pour te mettre dans un état pareil ?\n\nGabriel : \nCe sont les loustiques ! Ils ont attaqué l’école "+eleve.ecole+" et on emprisonné tout les élèves !\n\nDieu :  \nRAHHH encore ces fichus loustiques… \nIl faut absolument libérer les élèves ! \nPeux-tu t’en occuper Gabriel ?");

		continuer();

		println("Gabriel : \nBien Sûr !  Mais je vais avoir besoin d’aide car les loustiques sont très forts ! \nIls sont au nombre de ??? et possèdent chacun une spécialité allant des mathématiques au français en passant évidemment par l’anglais, l’informatique, les sciences et bien d’autre et je ne pense pas posséder assez de connaissances pour les vaincres à moi seul …\n\nDieu :  \nTrès bien Gabriel, j’ai justement quelqu’un à te présenter, il s’appelle "+eleve.nomDuJoueur+" et est élève à l’école "+eleve.ecole+", il est certainement le seul élève qui n’a pas été fait prisonnier !\n"+eleve.nomDuJoueur+", nous avons vraiment besoin de toi pour délivrer tes camarades, es-tu prêt à nous aider ?");

		continuer();

		println("Dieu : \nParfait dans ce cas, partez tout de suite, il n’y a pas une minute à perdre !\n\nGabriel : \nSuper ! Accroche toi "+eleve.nomDuJoueur+", ça va secouer !");

		continuer();

		algorithm();
	}	

	void projet(){
		clearScreen();

		println("			Présentation du projet :");

		println("\n\n\nPour retourner au menu, tape 'r' !");
		if(readChar()=='r' || readChar()=='R'){
			algorithm();
		}
	}

	void credits(){
		clearScreen();

		println("			Credits :\n\n\nLucas DELESTREE\nMaxime SOUDANT");

		println("\n\n\nPour retourner au menu, tape 'r' !");
		if(readChar()=='r' || readChar()=='R'){
			algorithm();
		}
	}

	void valeurIncorect(){
		clearScreen();

		println("			Choix incorect !\n\n\nChoix incorect mon ami, tu dois choisir un option du menu (1, 2, 3 ou4) !");

		println("\n\n\nPour retourner au menu, tape 'r' !");
		if(readChar()=='r' || readChar()=='R'){
			algorithm();
		}
	}

	void continuer (){
		char continuer='A';
		while (continuer!='C' && continuer!='c'){
			println("\n'C'pour continuer");
			continuer=readChar();
			clearScreen();
		}
	}

	void algorithm(){
		int choixMenu;

		menu();
		choixMenu=readInt();
		switch (choixMenu){
			case 1 :
				jouer();
				break;
			case 2 :
				projet();//à compléter
				break;
			case 3 :
				credits();
				break;
			case 4 :
				break;
			default :
				valeurIncorect();
				break;
		}
	}
}

//Faire en sorte que le test ne plante pas lorsque l'on entre autre chose qu'un chiffre
//Faire en sorte qu'il y est un message d'erreur lorsque continue continuer !=c