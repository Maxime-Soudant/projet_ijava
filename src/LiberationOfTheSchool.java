import extensions.CSVFile;
import extensions.Sound;
class LiberationOfTheSchool extends Program{
    
//////////////////////////////////////////////////////////Menu/////////////////////////////////////////////////////////////////////////////////////
	   void afficherMenu(){
	clearScreen();
	cursor(10,70);print("Liberation Of The School");
	cursor(20,10);print("1- Jouer");
	cursor(22,10);print("2- Projet");
	cursor(24,10);print("3- Credits");
	cursor(26,10);print("4- Quitter");
	cursor(30,10);println("Que veux tu faire ?");
    }
    void projet(){
	clearScreen();
	println("Présentation du projet :");
	println("\n\n\nPour retourner au menu, tape 'r' !");
	if(readChar()=='r' || readChar()=='R'){algorithm();}
    }

    void credits(){
	clearScreen();
	cursor(10,70);print("Crédits :");
	cursor(14,40);print("Lucas DELESTREE");
	cursor(15,40);print("Maxime SOUDANT");
	cursor(40,5);print("Pour retourner au menu, tape 'r'   ");
	if(readChar()=='r' || readChar()=='R'){algorithm();}
    }

//\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
   
//////////////////////////////////////////////////ALGORITHME PRINCIPALE//////////////////////////////////////////////////////////////////////////
    void jouer(){
	affichageChargement();
	Sound theme=newSound("theme.mp3");play(theme,true);// ♪
        Sound themeC=newSound("themeCombat.mp3");//  ♪
	Sound themeRencontreLoustique=newSound("themeRencontreLoustique.mp3");// ♪
	CSVFile fichiercsv = loadCSV("questions_reponses_stock.csv");
	Joueur eleve = new Joueur();eleve.vie=3;
	do{
	    cursor(20,60);print("Es-tu un garçon ou une fille ? (G ou F) :");
	    cursor(21,80);clearLine();eleve.sexe=readChar(); 
	}while (eleve.sexe!='G' && eleve.sexe != 'g' && eleve.sexe!='F' && eleve.sexe!='f');clearScreen();
	cursor(20,65);println("Quel est le nom de ton école ?");
	cursor(21,80);eleve.ecole = readString();clearScreen();

	//dialogue 1//
	dialogue1(eleve);
	stop(theme);
	hide();delay(500);show();
	play(themeRencontreLoustique,true);//  ♪
	dialogue1rencontre(eleve);
	stop(themeRencontreLoustique);// ♪
	affichageChargement();
	
	
	//début de combat avec le loustique de Math//
        play(themeC,true);//   ♪
	int[]questionFaites=new int[10];int i=0;
	afficherCombat(creerLoustique("Pythongore","math"),eleve,fichiercsv,questionFaites,i);
        stop(themeC);//  ♪
	//fin de combat//

	//dialogue 2//
	affichageChargement();
	play(theme,true);// ♪
	continuer();
	//dialogue2(eleve);
	
	
	//fin du jeu//
	}

//\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
 
//////////////////////////////////////////////////////////////////COMBATS////////////////////////////////////////////////////////////////////////
    
    Loustique creerLoustique(String nomLoustique, String matière){
	Loustique l = new Loustique();
	l.nom=nomLoustique; l.matiere=matière; l.hp=100; return l;}
    
    ////   Choix Aleatoire de la question   ///
    int randomizer(){return (int)(random()*10)+1;}
    
    ////   Ne pas répéter la même question ////
    int checkQuestionPasFaite(int[]questionsFaites,int questionActuelle){
	
	for(int i=0;i<length(questionsFaites);i++){
	    if(questionsFaites[i]==questionActuelle){return checkQuestionPasFaite(questionsFaites,randomizer());}
	    }return questionActuelle;}


    ///////////affichage du combat //////////////
        void afficherCombat(Loustique loustique,Joueur eleve,CSVFile fichiercsv,int[]questionFaites,int i){clearScreen();
	
	while(loustique.hp!=0 && eleve.vie!=0){
	//affichage loustique
	cursor(5,80);println(loustique.nom);
	cursor(10,60);print("Points de vie restants de "+loustique.nom+" : "+loustique.hp+"/100");
	
	//question
	int q=randomizer();q=checkQuestionPasFaite(questionFaites,q);	
	if(loustique.matiere=="français"){q=q+10;}
	cursor(30,35);print(getCell(fichiercsv,q,1));
	//Enregistrement de la question dans le tableau pour ne pas retomber sur la même question
	questionFaites[i]=q;
	i++;
	//Afficher les reponses
	cursor(34,55);print("1- "+getCell(fichiercsv,q,2));
	cursor(36,55);print("3- "+getCell(fichiercsv,q,4));	
	cursor(34,115);print("2- "+getCell(fichiercsv,q,3));	
	cursor(36,115);print("4- "+getCell(fichiercsv,q,5));
	
	//reponse du joueur
	cursor(32,80);print(""+eleve.nomDuJoueur+" HP : "+eleve.vie+"/3");
	cursor(38,70);println("Quel réponse choisissez vous ?");cursor(40,80);
	//saisie réponse + verification de la réponse et conséquence
	if(verifierBonneReponse(repJoueur(),fichiercsv,q)){
	 //bonne réponse
	    cursor(20,60);print("Bravo,"+loustique.nom+" a perdu 25 points de vie !");
	    hide();delay(1100);show();
	    loustique.hp=(loustique.hp)-25;
	    afficherCombat(loustique,eleve,fichiercsv,questionFaites,i);
	    
         //Mauvaise réponse
	}else{cursor(20,60);print("Dommage, la bonne réponse était la n°"+getCell(fichiercsv,q,6));
	    hide();delay(2000);show();
	    eleve.vie=(eleve.vie)-1;
	    afficherCombat(loustique,eleve,fichiercsv,questionFaites,i);}
	}
	
	//cas de victoire ou défaite//
	if(victoireOuDefaite(loustique)){clearScreen();cursor(20,50);print("Vous avez vaincu "+loustique.nom+" félicitations !");}
	else{clearScreen();cursor(25,50);print("Malheureusement vous avez échoué... retentez votre chance?");}}
    
    boolean victoireOuDefaite(Loustique loustique){if(loustique.hp==0){return true;}else{return false;}}
    
    boolean verifierBonneReponse(String rep,CSVFile fichiercsv,int q){
	if(equals(rep,getCell(fichiercsv,q,6))){return true;}
	return false; }
    
    String repJoueur(){
        String rep=readString();
	while(charAt(rep,0)>'4' || charAt(rep,0)<'1'){
	    cursor(38,10);clearLine();println("Tu dois choisir entre les réponses de 1 et 4");
	    cursor(40,20);clearLine();rep=readString();}return rep;}


         
//\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\

////////////////////////////////////////////////////// Programmes annexes / dialogues /////////////////////////////////////////////////////////////
    
    void continuer (){
	    char continuer='A';
	    cursor(34,5);print("'C' pour continuer");
	    cursor(32,5);continuer=readChar();
	    if(continuer!='C' && continuer!='c'){continuer();}
	    clearScreen();}
	

	void algorithm(){
		int choixMenu;
		clearScreen();afficherMenu();
		cursor(31,10);choixMenu=readInt();
		if(choixMenu==1){jouer();}
		else if(choixMenu==2){projet();}
		else if(choixMenu==3){credits();}
		else if(choixMenu==4){println("au revoir");}
                else{valeurIncorrecte();}}

     void valeurIncorrecte(){
	clearScreen();cursor(10,75);print("Choix incorect !");
	cursor(14,45);print("Choix incorect mon ami, tu dois choisir un option du menu entre 1 et 4");
        hide();delay(2300);show();algorithm();}


		
		
		


    void affichageChargement(){
	clearScreen();cursor(15,80);print("Chargement");hide();cursor(20,55);
	print("///");delay(80);print("///");delay(80);print("///");delay(80);print("///");delay(80);
	print("///");delay(80);print("///");delay(80);print("///");delay(80);print("///");delay(80);
	print("///");delay(80);print("///");delay(80);print("///");delay(80);print("///");delay(80);
	print("///");delay(80);print("///");delay(80);print("///");delay(80);print("///");delay(80);
	print("///");delay(80);print("///");delay(80);print("///");delay(80);print("///");delay(80);delay(600);show();clearScreen();}

    
//////////////////////////////////////////////Dialogue du début////////////////////////////////////////////////////////////////////////////////////
    
    void dialogue1(Joueur eleve){
	String test="";
	String sexe="fille";
	if(eleve.sexe=='g'||eleve.sexe=='G'){sexe="garçon";}
	cursor(20,5);print("𝘿𝙞𝙚𝙪 :");
	cursor(22,7);print("Salutations jeune " + sexe + ", et bienvenue dans Liberation Of The School, le jeu d’aventure dans lequel tu vas devenir un aventurier hors pair en parcourant ta ville !\n      Tu vas pouvoir rencontrer tes camarades qui auront des missions à te proposer, il faudra les remplir pour les aider.\n      Ces missions peuvent sembler très simples au début mais se corseront rapidement, tu verras. Avant de commencer, peux-tu me dire comment tu t’appelles ?");
	cursor(26,80);clearLine();eleve.nomDuJoueur=readString();clearScreen();
	cursor(20,5);print("𝘿𝙞𝙚𝙪 :");
	cursor(22,3);print("Très bien "+eleve.nomDuJoueur+", nous allons pouvoir commencer. Avant toutes choses, je vais te poser une question afin de voir si tu es apte à aider tes camarades, tu es prêt ?");continuer();
	cursor(20,5);print("𝘿𝙞𝙚𝙪 :");
	cursor(22,7);print("Parfait, pour aider tes copains lorsqu’ils te poseront des questions, tu n’auras qu'à écrire la réponse dans l’encadré juste en dessous");
	cursor(23,7);print("puis appuyer sur entrer pour valider ta réponse,faisons un essai, réponds à cette question : combien font 9x9 ?");
	cursor(25,80);test = readString();
	while(!equals(test,"81")){
	    cursor(25,50);  clearLine();  print("Dieu : Raté, essayes encore : ");
	    cursor(25,80);test=readString();}
	clearScreen();
	cursor(20,5);print("𝘿𝙞𝙚𝙪 :");
	cursor(22,7);print("Bravo, tu vas pouvoir commencer à jouer et partir à la rencontre de tes camarades !\n      Oh j’allais oublier de te présenter mon acolyte, c’est l’ange qui arrive en courant vers nous, là-bas.\n\n      Il se nomme Gabriel et il sera là pour t’aider quand tu en auras besoin !\n      D’ailleurs, il court drôlement vite aujourd’hui, il a l’air d’avoir hâte de faire ta connaissance !");			
	continuer();
	cursor(20,5);print("𝙂𝙖𝙗𝙧𝙞𝙚𝙡 :");
	cursor(22,7);print("DIEU ! C’EST TERRIBLE !");
	continuer();
	cursor(20,5);print("𝘿𝙞𝙚𝙪 :");
	cursor(21,7);print("Calmes toi mon ami, qu’a t-il bien pu se passer pour te mettre dans un état pareil ?");
	cursor(23,5);print("𝙂𝙖𝙗𝙧𝙞𝙚𝙡 :");
	cursor(24,7);print("Ce sont les loustiques ! Ils se sont emparés de l’école "+eleve.ecole+" et ont emprisonné tous les élèves !");
	cursor(26,5);print("𝘿𝙞𝙚𝙪 :");
	cursor(27,7);print("Oh mon MOI, c'est horrible! Il faut absolument libérer les élèves !\n      Peux-tu t’en occuper, Gabriel ?");
	continuer();
	cursor(20,5);print("𝙂𝙖𝙗𝙧𝙞𝙚𝙡 :");
	cursor(22,7);print("Bien Sûr !  Mais je vais avoir besoin d’aide car les loustiques sont très forts !");
	cursor(23,7);print("Ils sont au nombre de 6 et possèdent chacuns une spécialité allant des mathématiques au français en passant par l’anglais, les sciences et bien d’autres");
	cursor(24,7);print("et je ne pense pas posséder assez de connaissances pour les vaincre à moi seul …");
	cursor(26,5);print("𝘿𝙞𝙚𝙪 :");
	cursor(28,7);print("Très bien Gabriel, j’ai justement un élève à te présenter, il s’appelle "+eleve.nomDuJoueur+", il vient de l'école "+eleve.ecole+", il est certainement le seul élève qui ait survécu !");
	cursor(29,7);print(eleve.nomDuJoueur+", nous avons besoin de toi pour délivrer tes camarades, es-tu prêt à nous aider ?");continuer();
	cursor(20,5);print("𝘿𝙞𝙚𝙪 :");
	cursor(22,7);print("Parfait dans ce cas, partez tout de suite, il n’y a pas une minute à perdre !");
	cursor(24,5);print("𝙂𝙖𝙗𝙧𝙞𝙚𝙡 :");cursor(26,7);print("Super ! Accroches toi "+eleve.nomDuJoueur+", ça va secouer !");
	clearScreen();cursor(20,5);print("𝙂𝙖𝙗𝙧𝙞𝙚𝙡 :");cursor(22,7);print("En route pour "+eleve.ecole+", "+eleve.nomDuJoueur+".");
	cursor(24,25);hide();print("Vous marchez tous les deux jusque "+eleve.ecole);delay(500);
	print(".");delay(1000);print(".");delay(1000);print(".");delay(1200);clearLine();show();
    }

    void dialogue1rencontre(Joueur eleve){
	cursor(25,5);print("Inconnu");cursor(27,7);print("Stop, qui va là ?");continuer();
	cursor(20,5);print("𝙂𝙖𝙗𝙧𝙞𝙚𝙡 :");
	cursor(22,7);print("(Psst "+eleve.nomDuJoueur+",l'école est censée être deserte, cet homme doit être un de ces loustiques !)");
	cursor(24,5);print("Inconnu");cursor(26,7);print("Qui êtes-vous, vous n'êtes pas la bienvenue ici !");hide();delay(1000);show();
	cursor(28,5);print("Pythongore");
	cursor(30,7);print("Je suis Pythongore, le cadet des Loustiques, je suis spécialiste des mathématiques et je vais vous faire regretter d'être venus jusqu'ici!");
	cursor(24,5);clearLine();print("Pythongore");continuer();

    }
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


}

//Faire en sorte qu'il y est un message d'erreur lorsque continue continuer !   <== j'ai pas compris ta phrase 
//ouai c'etait un memo pour moi à la base x) en gros pour cotinuer faut appuyer sur C bah si on met autre chose que c ca crash fallait que je modifie ca 
