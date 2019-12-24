import extensions.CSVFile;
import extensions.Sound;
class LiberationOfTheSchool extends Program{
    boolean touche=false;char var='v';String sexe=" ";char rep='v';char var2=' ';
//////////////////////////////////////////////////////////Menu/////////////////////////////////////////////////////////////////////////////////////
    void afficherMenu(){
	hide();
	clearScreen();
	cursor(10,70);print("Liberation Of The School");
	cursor(20,10);print("1- Jouer");
	cursor(22,10);print("2- Projet");
	cursor(24,10);print("3- Credits");
	cursor(26,10);print("4- Quitter");
	cursor(30,10);println("Que veux tu faire ?");
	choixMenu();
	switch(var2){
	case 'j':
	    var='v';jouer();
	case 'p':
	    var='v';projet();
	case 'c':
	    var='v';credits();
	case 'q':
	    clearScreen();cursor(25,75);println("Au revoir ! :)");
	}
    }
    
    void projet(){
	clearScreen();
	cursor(10,50);print("Présentation du projet :");
	cursor(40,5);print("Pour retourner au menu, tape 'r' !");
	//if(readChar()=='r' || readChar()=='R'){algorithm();}
	 choixRetour();var='v';
	 afficherMenu();
    }

    void credits(){
	clearScreen();
	cursor(10,70);print("Crédits :");
	cursor(14,40);print("Lucas DELESTREE");
	cursor(15,40);print("Maxime SOUDANT");
	cursor(40,5);print("Pour retourner au menu, tape 'r' !");
	choixRetour();var='v';
	afficherMenu();
    }


    //\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\ \
   
//////////////////////////////////////////////////ALGORITHME PRINCIPALE///////////////////////////////////////////////////////////////////////////
    
    void jouer(){
	enableKeyTypedInConsole(false);touche=true;affichageChargement();
	Sound theme=newSound("theme.mp3");play(theme,true);// ♪
        Sound themeC=newSound("themeCombat.mp3");//  ♪
	Sound themeRencontreLoustique=newSound("themeRencontreLoustique.mp3");// ♪
	CSVFile fichiercsv=loadCSV("questions_reponses_stock.csv");
	Joueur eleve = new Joueur(); eleve.vie=3;
	choixSexe();
	cursor(20,65); println("Quel est le nom de ton école ?");
	cursor(21,80); eleve.ecole=readString(); clearScreen();

	//dialogue 1//
	dialogue1(eleve);
	stop(theme);
	delay(500);
	play(themeRencontreLoustique,true);//  ♪
	dialogue1rencontre(eleve);
	stop(themeRencontreLoustique);// ♪
	affichageChargement();
	
	
	//début de combat avec le loustique de Math//
        play(themeC,true);//   ♪
	int[]questionFaitesMath=new int[10];int i=0;
	afficherCombat(creerLoustique("Pythongore","math"),eleve,fichiercsv,questionFaitesMath,i);
        stop(themeC);eleve.vie=3;//  ♪
	//fin de combat//

	//dialogue 2//
	affichageChargement();
	play(theme,true);// ♪
	continuer();
	//dialogue2(eleve);
	
	//début du combat avec le loustique de Français//
	play(themeC,true);//   ♪
	int[]questionFaitesFrancais=new int[10];i=0;
	afficherCombat(creerLoustique("Rimbaudelaire","francais"),eleve,fichiercsv,questionFaitesFrancais,i);
        stop(themeC);eleve.vie=3;//  ♪
	//Fin de combat//

	//dialogue 3//
	affichageChargement();
	play(theme,true);// ♪
	continuer();
	//dialogue3(eleve);

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
    int checkQuestionPasFaite(int[]questionsFaites,int questionActuelle,String matiere){	
	for(int idx=0;idx<length(questionsFaites);idx++){
	    if(equals(matiere,"math")){
		if(questionsFaites[idx]==questionActuelle){return checkQuestionPasFaite(questionsFaites,randomizer(),"math");}return questionActuelle;}
	    else if(equals(matiere,"francais")){
		if(questionsFaites[idx]==questionActuelle){return checkQuestionPasFaite(questionsFaites,randomizer()+10,"francais");}return questionActuelle;}
	}
	return 0;}


	
		
		   
		

    ///////////affichage du combat //////////////
    void afficherCombat(Loustique loustique,Joueur eleve,CSVFile fichiercsv,int[]questionFaites,int i){clearScreen();
	
	while(loustique.hp>0 && eleve.vie>0){
	//affichage loustique
	cursor(5,75);println(loustique.nom);
	cursor(10,60);print("Points de vie restants de "+loustique.nom+" : "+loustique.hp+"/100");
	
	//question
	int q=randomizer();
	q=checkQuestionPasFaite(questionFaites,q,loustique.matiere);		
	cursor(30,25);print(getCell(fichiercsv,q,1));
	//Enregistrement de la question dans le tableau(contenant toutes les questions déjà faites) pour ne pas retomber sur la même question
	questionFaites[i]=q;
	i++;
	//Afficher les reponses
	cursor(34,40);print("1- "+getCell(fichiercsv,q,2));
	cursor(36,40);print("3- "+getCell(fichiercsv,q,4));	
	cursor(34,115);print("2- "+getCell(fichiercsv,q,3));	
	cursor(36,115);print("4- "+getCell(fichiercsv,q,5));
	
	//reponse du joueur
	cursor(32,75);print(""+eleve.nomDuJoueur+" HP : "+eleve.vie+"/3");
	cursor(38,65);println("Quel réponse choisissez vous ?");cursor(40,80);
	
	//saisie réponse + verification de la réponse et conséquence
	if(verifierBonneReponse(repJoueur(),fichiercsv,q)){
	 //bonne réponse
	    cursor(20,60);print("Bravo,"+loustique.nom+" a perdu 25 points de vie !");delay(1100);
	    loustique.hp=(loustique.hp)-25;
	    afficherCombat(loustique,eleve,fichiercsv,questionFaites,i);
	    
         //Mauvaise réponse
	}else{cursor(20,60);print("Dommage, la bonne réponse était la n°"+getCell(fichiercsv,q,6));delay(2000);
	    eleve.vie=(eleve.vie)-1;
	    afficherCombat(loustique,eleve,fichiercsv,questionFaites,i);}
	}
	//cas de victoire ou défaite//
	if(victoireOuDefaite(loustique)){clearScreen();cursor(20,50);print("Vous avez vaincu "+loustique.nom+" félicitations !");}
	else{clearScreen();cursor(25,50);print("Malheureusement vous avez échoué... retentez votre chance..");}}
    
    boolean victoireOuDefaite(Loustique loustique){if(loustique.hp<=0){return true;}else{return false;}}
    
    boolean verifierBonneReponse(char rep,CSVFile fichiercsv,int q){
	if(rep==charAt(getCell(fichiercsv,q,6),0)){return true;}
	return false; }
    
  
//\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\

////////////////////////////////////////////////////// Programmes annexes / dialogues /////////////////////////////////////////////////////////////
    char repJoueur(){
	var='r';
	rep='v';
	enableKeyTypedInConsole(true);
	touche=false;
	while(!touche){delay(1);}
	enableKeyTypedInConsole(false);
        return rep;}
    
    void choixSexe(){
	var='s';
	cursor(20,55);print("Es-tu un garçon ou une fille ? (appuies sur G ou F) :");cursor(21,80);
	enableKeyTypedInConsole(true);
	touche=false;
	while(!touche){delay(1);}clearScreen();
        enableKeyTypedInConsole(false);}
    
    void continuer (){
	var='c';
	cursor(34,5);print("'C' pour continuer");cursor(35,5);
	enableKeyTypedInConsole(true);
	touche=false;
	while(!touche){delay(1);}
	enableKeyTypedInConsole(false);}
        
    void choixRetour(){
	  var='b';cursor(31,10);
	  enableKeyTypedInConsole(true);
	  touche=false;
	  while(!touche){delay(1);}
	  enableKeyTypedInConsole(false);
      }

     void choixMenu(){
	var='m';cursor(31,10);
	enableKeyTypedInConsole(true);
	touche=false;
	while(!touche){delay(1);}
	enableKeyTypedInConsole(false);}

/// entrée au clavier
    void keyTypedInConsole(char key){
	switch (var){
	    //continuer();
	case 'c':
	    if(key=='c'||key=='C'){clearScreen();touche=true;}
	    else{cursor(35,5);clearLine();}
	    
	    //choixSexe();
	case 's':
	    if(key=='g'||key=='G'){clearScreen();sexe="garçon";touche=true;}
	    else if(key=='f'||key=='F'){clearScreen();sexe="fille";touche=true;}
	    else{cursor(21,80);clearLine();}
	    
	    //repJoueur();
	case'r':
	    if(key=='1'||key=='&'){rep='1';touche=true;}
	    else if(key=='2'||key=='é'){rep='2';touche=true;}
	    else if(key=='3'||key=='\"'){rep='3';touche=true;}
	    else if(key=='4'||key=='\''){rep='4';touche=true;}
	    else{if(var=='r'){cursor(25,63);clearLine();println("Tu dois choisir entre les réponses de 1 et 4");cursor(40,80);clearLine();}}
	    
	    //menu();
	case 'm':
	    if(key=='1'||key=='&'){var2='j';touche=true;}
	    else if(key=='2'||key=='é'){var2='p';touche=true;}
	    else if(key=='3'||key=='\"'){var2='c';touche=true;}
	    else if(key=='4'||key=='\''){var2='q';touche=true;}
	    else{if(var=='m'){cursor(27,60);println("Choix incorect !");print("Tu dois choisir entre les options de 1 à 4");cursor(31,10);clearLine();}}
	    
	    // retour au menu
     	case 'b':
		if(key=='r'||key=='R'){touche=true;}
		else{if(var=='b'){cursor(31,10);clearLine();cursor(32,30);print("Touche incorrecte");delay(1500);clearLine();}}
	}
    }

	
void algorithm(){
	    clearScreen();
	    afficherMenu();}


    void affichageChargement(){
	clearScreen();cursor(15,80);print("Chargement");cursor(20,55);
	print("///");delay(60);print("///");delay(60);print("///");delay(60);print("///");delay(60);
	print("///");delay(60);print("///");delay(60);print("///");delay(60);print("///");delay(60);
	print("///");delay(60);print("///");delay(60);print("///");delay(60);print("///");delay(60);
	print("///");delay(60);print("///");delay(60);print("///");delay(60);print("///");delay(60);
	print("///");delay(60);print("///");delay(60);print("///");delay(60);print("///");delay(60);delay(500);clearScreen();}

    
//////////////////////////////////////////////Dialogue du début////////////////////////////////////////////////////////////////////////////////////
    
    void dialogue1(Joueur eleve){
	String test="";
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
	cursor(29,7);print(eleve.nomDuJoueur+"Nous avons besoin de toi pour délivrer tes camarades, es-tu prêt à nous aider ?");continuer();
	cursor(20,5);print("𝘿𝙞𝙚𝙪 :");
	cursor(22,7);print("Parfait dans ce cas, partez tout de suite, il n’y a pas une minute à perdre !");
	cursor(24,5);print("𝙂𝙖𝙗𝙧𝙞𝙚𝙡 :");cursor(26,7);print("Super ! Accroches toi "+eleve.nomDuJoueur+", ça va secouer !");
	clearScreen();cursor(20,5);print("𝙂𝙖𝙗𝙧𝙞𝙚𝙡 :");cursor(22,7);print("En route pour "+eleve.ecole+", "+eleve.nomDuJoueur+".");
	cursor(24,25);print("Vous marchez tous les deux jusque "+eleve.ecole);delay(500);
	print(".");delay(1000);print(".");delay(1000);print(".");delay(1200);clearLine();}

    void dialogue1rencontre(Joueur eleve){
	cursor(25,5);print("Inconnu");cursor(27,7);print("Stop, qui va là ?");continuer();
	cursor(20,5);print("𝙂𝙖𝙗𝙧𝙞𝙚𝙡 :");
	cursor(22,7);print("(Psst "+eleve.nomDuJoueur+",l'école est censée être deserte, cet homme doit être un de ces loustiques !)");
	cursor(24,5);print("Inconnu");cursor(26,7);print("Qui êtes-vous, vous n'êtes pas la bienvenue ici !");continuer();
	cursor(20,5);print("𝙂𝙖𝙗𝙧𝙞𝙚𝙡 :");
	cursor(22,7);print("(Psst "+eleve.nomDuJoueur+",l'école est censée être deserte, cet homme doit être un de ces loustiques !)");
	cursor(24,5);print("Pythongore");cursor(28,5);print("Pythongore");cursor(26,7);print("Qui êtes-vous, vous n'êtes pas la bienvenue ici !");
	cursor(30,7);print("Je suis Pythongore, le cadet des Loustiques, je suis spécialiste des mathématiques et je vais vous faire regretter d'être venus jusqu'ici!");continuer();}
    
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

}

