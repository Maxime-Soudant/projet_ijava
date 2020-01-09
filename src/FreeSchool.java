import extensions.*;
class FreeSchool extends Program{
    boolean touche=false;char var='v';String sexe=" ";char rep='v';char var2=' ';
//////////////////////////////////////////////////////////Menu/////////////////////////////////////////////////////////////////////////////////////
    void afficherMenu(){
	hide();
	clearScreen();
	cursor(10,70);print("Free School");
	cursor(20,10);print("1- Jouer");
	cursor(22,10);print("2- Projet");
	cursor(24,10);print("3- Credits");
	cursor(26,10);print("4- Quitter");
	cursor(30,10);println("Que veux tu faire ?");
	choixMenu();
	switch(var2){
	case 'j':
	    var='v';jouer();
	    break;
	case 'p':
	    var='v';projet();
	    break;
	case 'c':
	    var='v';credits();
	    break;
	case 'q':
	    clearScreen();cursor(25,75);println("Au revoir ! :)");
	    delay(5000);
	    reset();
	    break;
	}
    }
    
    void projet(){
	clearScreen();
	cursor(10,50);print("Présentation du projet :");
	cursor(40,5);print("Pour retourner au menu, tape 'r' !");
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
	Sound theme=newSound("../ressources/theme.mp3");play(theme,true);// ♪
    Sound themeC=newSound("../ressources/themeCombat.mp3");//  ♪
	Sound themeRencontreLoustique=newSound("../ressources/themeRencontreLoustique.mp3");// ♪
	Sound themeFinal=newSound("../ressources/themeFinal.mp3");// ♪
	CSVFile fichiercsv=loadCSV("../ressources/questions_reponses_stock.csv");
	Joueur eleve = new Joueur(); eleve.vie=3;
	choixSexe();
	cursor(20,65); println("Quel est le nom de ton école ?");
	cursor(21,80); eleve.ecole=readString(); clearScreen();

	//dialogue 1//
	clearScreen();
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
	stop(theme);
	delay(500);
	play(themeRencontreLoustique,true);//  ♪
	dialogue2rencontre(eleve);
	stop(themeRencontreLoustique);// ♪
	affichageChargement();
	//dialogue2(eleve);
	
	//début du combat avec le loustique de Français//
	play(themeC,true);//   ♪
	int[]questionFaitesFrancais=new int[10];i=0;
	afficherCombat(creerLoustique("Rimbaudelaire","francais"),eleve,fichiercsv,questionFaitesFrancais,i);
        stop(themeC);eleve.vie=3;//  ♪
	//Fin de combat//

	//dialogue 3//
	affichageChargement();
	stop(theme);
	delay(500);
	play(themeRencontreLoustique,true);//  ♪
	dialogue3rencontre(eleve);
	stop(themeRencontreLoustique);// ♪
	affichageChargement();
	//dialogue3(eleve);

	//début du combat avec le loustique d'Histoire//
	play(themeC,true);//   ♪
	int[]questionFaitesHistoire=new int[10];i=0;
	afficherCombat(creerLoustique("Napoléonard Bonaparte","histoire"),eleve,fichiercsv,questionFaitesHistoire,i);
        stop(themeC);eleve.vie=3;//  ♪
	//Fin de combat//

	//dialogue 4//
	affichageChargement();
	stop(theme);
	delay(500);
	play(themeRencontreLoustique,true);//  ♪
	dialogue4rencontre(eleve);
	stop(themeRencontreLoustique);// ♪
	affichageChargement();
	//dialogue4(eleve);

	//début du combat avec le loustique de Géographie//
	play(themeC,true);//   ♪
	int[]questionFaitesGeographie=new int[10];i=0;
	afficherCombat(creerLoustique("Cristophe Colombe","geographie"),eleve,fichiercsv,questionFaitesGeographie,i);
        stop(themeC);eleve.vie=3;//  ♪
	//Fin de combat//

	//dialogue 5//
	affichageChargement();
	stop(theme);
	delay(500);
	play(themeRencontreLoustique,true);//  ♪
	dialogue5rencontre(eleve);
	stop(themeRencontreLoustique);// ♪
	affichageChargement();
	//dialogue5(eleve);

	//début du combat avec le loustique d'anglais//
	play(themeC,true);//   ♪
	int[]questionFaitesAnglais=new int[10];i=0;
	afficherCombat(creerLoustique("Miss California","anglais"),eleve,fichiercsv,questionFaitesAnglais,i);
        stop(themeC);eleve.vie=3;//  ♪
	//Fin de combat//

	//dialogue 6//
	affichageChargement();
	stop(theme);
	delay(500);
	play(themeRencontreLoustique,true);//  ♪
	dialogue6rencontre(eleve);
	stop(themeRencontreLoustique);// ♪
	affichageChargement();
	//dialogue6(eleve);

	//début du combat avec le loustique d'art//
	play(themeC,true);//   ♪
	int[]questionFaitesArt=new int[10];i=0;
	afficherCombat(creerLoustique("Eduardo DelArte","art"),eleve,fichiercsv,questionFaitesArt,i);
        stop(themeC);eleve.vie=3;//  ♪
	//Fin de combat//

	//dialogue 7//
	affichageChargement();
	stop(theme);
	delay(500);
	play(themeRencontreLoustique,true);//  ♪
	dialogue7rencontre(eleve);
	stop(themeRencontreLoustique);// ♪
	affichageChargement();
	//dialogue7(eleve);

	//début du combat avec le loustique des sciences//
	play(themeC,true);//   ♪
	int[]questionFaitesSciences=new int[10];i=0;
	afficherCombat(creerLoustique("Thomas Tesla","sciences"),eleve,fichiercsv,questionFaitesSciences,i);
        stop(themeC);eleve.vie=3;//  ♪
	//Fin de combat//

	//dialogue 8//
	affichageChargement();
	stop(theme);
	delay(500);
	play(themeRencontreLoustique,true);//  ♪
	dialogue8rencontre(eleve);
	stop(themeRencontreLoustique);// ♪
	affichageChargement();
	//dialogue8(eleve);

	//début du combat avec le loustique Final//
	play(themeC,true);//   ♪
	int[]questionFaitesFinal=new int[10];i=0;
	afficherCombat(creerLoustique("Chef des Loustiques","final"),eleve,fichiercsv,questionFaitesFinal,i);
        stop(themeC);eleve.vie=3;//  ♪
	//Fin de combat//

    //dialogue final//
	affichageChargement();
	stop(theme);
	delay(500);
	play(themeFinal,true);//  ♪
	dialogue9rencontre(eleve);
	stop(themeRencontreLoustique);// ♪
	affichageChargement();
	afficherMenu();
	//fin du jeu//
	}

//\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
 
//////////////////////////////////////////////////////////////////COMBATS////////////////////////////////////////////////////////////////////////
    
    Loustique creerLoustique(String nomLoustique, String matière){
	Loustique l = new Loustique();
	l.nom=nomLoustique; l.matiere=matière; l.hp=100; return l;}
    
    ////   Choix Aleatoire de la question   ///
    int randomizer(){return (int)(random()*10)+1;}
    
    ////   Ne pas répéter la même question //
    int checkQuestionPasFaite(int[]questionsFaites,int questionActuelle,String matiere){
	
	int q=randomizer();
	if (equals(matiere, "francais")){
		q=q+10;
	}else if(equals(matiere,"histoire")){
		q=q+20;
	}else if(equals(matiere,"geographie")){
		q=q+30;
	}else if(equals(matiere,"anglais")){
		q=q+40;
	}else if(equals(matiere,"art")){
		q=q+50;
	}else if(equals(matiere,"sciences")){
		q=q+60;
	}else if(equals(matiere,"final")){
		q=q+70;
	}
	for(int idx=0;idx<length(questionsFaites);idx++){
	    if(questionsFaites[idx]==questionActuelle){return checkQuestionPasFaite(questionsFaites,q,matiere);}}
	return questionActuelle;
    }

       	

    ///////////affichage du combat //////////////
    void afficherCombat(Loustique loustique,Joueur eleve,CSVFile fichiercsv,int[]questionFaites,int i){clearScreen();
	
	while(loustique.hp>0 && eleve.vie>0){
	//affichage loustique
	    text("green");cursor(5,75);println(loustique.nom);reset();
	    String hpLous="";cursor(10,60);print("Points de vie restants de "+loustique.nom+" : "+loustique.hp+"/100");
	    text("red");cursor(11,58);for(int j=0;j<loustique.hp;j=j+2){hpLous=hpLous+"♥";}print(hpLous);reset();
	
	//question
	int q=randomizer();
	if (equals(loustique.matiere, "francais")){
		q=q+10;
	}else if(equals(loustique.matiere,"histoire")){
		q=q+20;
	}else if(equals(loustique.matiere,"geographie")){
		q=q+30;
	}else if(equals(loustique.matiere,"anglais")){
		q=q+40;
	}else if(equals(loustique.matiere,"art")){
		q=q+50;
	}else if(equals(loustique.matiere,"sciences")){
		q=q+60;
	}else if(equals(loustique.matiere,"final")){
		q=q+70;
	}
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
	String hpEleve="";
	text("red");cursor(33,80);for(int j=0;j<eleve.vie;j++){hpEleve=hpEleve+"♥";}print(hpEleve);reset();
	cursor(38,65);println("Quel réponse choisissez vous ?");cursor(40,80);
	
	//saisie réponse + verification de la réponse et conséquence
	if(verifierBonneReponse(repJoueur(),fichiercsv,q)){
	 //bonne réponse
	    cursor(20,60);print("Bravo,"+loustique.nom+" a perdu 25 points de vie !");delay(2000);
	    loustique.hp=(loustique.hp)-25;
	    afficherCombat(loustique,eleve,fichiercsv,questionFaites,i);
	    
         //Mauvaise réponse
	}else{cursor(20,60);print("Dommage, la bonne réponse était la n°"+getCell(fichiercsv,q,6));delay(2000);
	    eleve.vie=(eleve.vie)-1;
	    afficherCombat(loustique,eleve,fichiercsv,questionFaites,i);}
	}
	//cas de victoire ou défaite//
	if(victoireOuDefaite(loustique)){clearScreen();cursor(20,50);print("Vous avez vaincu "+loustique.nom+" félicitations !");delay(5000);}
	else{clearScreen();cursor(25,50);print("Malheureusement vous avez échoué... retentez votre chance ...");
	delay(5000);
	affichageChargement();
	eleve.vie=3;
	afficherCombat(loustique,eleve,fichiercsv,questionFaites,i);}}
    
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
	while(!touche){
	    String chrono="OOOOOOOOOO";
	    int c=10;
	    while(!touche){
		text("blue");
		cursor(20,75);clearLine();print(chrono);delay(3000);
		c--;
		chrono=substring(chrono,0,c);
		cursor(40,80);
		if(c==0){touche=true;}
		reset();
	    }	    
	}
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

    
//////////////////////////////////////////////Dialogue////////////////////////////////////////////////////////////////////////////////////
    
    void dialogue1(Joueur eleve){
	String test="";
	cursor(20,5);print("𝘿𝙞𝙚𝙪 :");
	cursor(22,7);print("Salutations jeune " + sexe + ", et bienvenue dans Free School, le jeu d’aventure dans lequel tu vas devenir un aventurier hors pair en parcourant ta ville !\n      Tu vas pouvoir rencontrer tes camarades qui auront des missions à te proposer, il faudra les remplir pour les aider.\n      Ces missions peuvent sembler très simples au début mais se corseront rapidement, tu verras. Avant de commencer, peux-tu me dire comment tu t’appelles ?");
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
	cursor(23,7);print("Ils sont au nombre de 7 et possèdent chacuns une spécialité allant des mathématiques au français en passant par l’anglais, les sciences et bien d’autres");
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
    
	void dialogue2rencontre(Joueur eleve){
		cursor(20,5);print("𝙂𝙖𝙗𝙧𝙞𝙚𝙡 :");cursor(22,7);print("Nous l’avons battu ? Enfin en tout cas il ne bouge plus donc je suppose qu’il est hors d’état de nuire ! ");
		cursor(23,7);print("J’espère que les Loustiques suivants seront moins puissants, quel combat ! Tu t’es super bien débrouillé "+eleve.nomDuJoueur+" ! Bravo !");
		cursor(25,7);print("Bon, étant donné que nous sommes directement tombés sur ce pythongore, laisses moi quelques secondes pour repérer où nous nous trouvons …");
		continuer();
		clearScreen();
		cursor(20,5);print("𝙂𝙖𝙗𝙧𝙞𝙚𝙡 :");cursor(22,7);print("Nous sommes donc dans la cour de l’école , il nous faut trouver un moyen d’entrer … Là bas, la fenêtre semble ouverte ! Je vais te faire la courte échelle.");
		continuer();
		clearScreen();
		cursor(24,25);print("Gabriel vous fait la courte échelle pour entrer dans l'école");
		delay(2500);
		clearScreen();
		cursor(20,5);print("𝙂𝙖𝙗𝙧𝙞𝙚𝙡 :");cursor(22,7);print("Ho Hisse la saucisse ! Bon, visiblement nous sommes arrivés dans la salle de français ");
		continuer();
		clearScreen();
		cursor(24,25);print(" La porte grince en s’ouvrant");
		delay(2500);
		clearScreen();
		cursor(20,5);print("Inconnu :");cursor(22,7);print("Qui va là ? J’ai entendu du bruit, montrez vous !");
		cursor(24,5);print("𝙂𝙖𝙗𝙧𝙞𝙚𝙡 :");cursor(26,7);print("Regarde "+eleve.nomDuJoueur+", voilà un nouveau loustique , nous devons le battre ! Nous sommes là pour sauver cette école, et toi qui es tu ?");
		clearScreen();
		cursor(20,5);print("Rimbaudelaire :");cursor(22,7);print("Qui va là ? J’ai entendu du bruit, montrez vous !");
		cursor(24,5);print("𝙂𝙖𝙗𝙧𝙞𝙚𝙡 :");cursor(26,7);print("Regarde "+eleve.nomDuJoueur+", voilà un nouveau loustique , nous devons le battre ! Nous sommes là pour sauver cette école, et toi qui es tu ?");
		cursor(28,5);print("Rimbaudelaire");cursor(30,7);print("Je suis Rimbaudelaire, spécialiste du français. Vous pensez vraiment pouvoir libérer cette école ?");
		cursor(31,7);print("Cela m’étonnerait beaucoup, vous feriez mieux de partir ! Mais j’ai envie de m’amuser un peu avec votre ignorance, alors en garde !");
		continuer();
	}

	void dialogue3rencontre(Joueur eleve){
		cursor(20,5);print("Rimbaudelaire :");cursor(22,7);print("Je… Je n’arrive pas à y croire, vous m’avez vaincu … c’est impossible ! je souffre ! Je me sens partir …");
		cursor(23,7);print("De toute façon, mes frères ne vous laisserons pas passer ! Ils sont bien plus fort que moi !");
		cursor(25,5);print("𝙂𝙖𝙗𝙧𝙞𝙚𝙡 :");cursor(27,7);print("Bravo "+eleve.nomDuJoueur+", nous avons vaincu le deuxième loustique ! D’ailleurs je ne savais pas que les loustiques étaient frères !");
		cursor(27,7);print("M’enfin peu importe nous devons avancer pour libérer ton école de ces malfrats !");
		continuer();
		clearScreen();
		cursor(24,25);print("Vous sortez de la salle avec Gabriel");
		delay(2500);
		clearScreen();
		cursor(20,5);print("𝙂𝙖𝙗𝙧𝙞𝙚𝙡 :");cursor(22,7);print("Bon maintenant que nous sommes dans le couloir de l’école , nous devons trouver par où aller… Par chance à droite c’est un cul de sac nous allons donc à gauche !");
		continuer();
		clearScreen();
		cursor(24,25);print("Vous prenez la route vers la gauche de le couloir de l’école");
		delay(2500);
		clearScreen();
		cursor(20,5);print("𝙂𝙖𝙗𝙧𝙞𝙚𝙡 :");cursor(22,7);print("Chuttt ! Ne fait pas de bruit ! J’ai entendu du bruit sur la gauche !");
		continuer();
		clearScreen();
		cursor(24,25);print("Vous regardez discrètement dans la salle sur votre gauche");
		delay(2500);
		clearScreen();
		cursor(20,5);print("𝙂𝙖𝙗𝙧𝙞𝙚𝙡 :");cursor(22,7);print("Voilà l’accueil , il semble y avoir un loustique à l’intérieur , essayons de passer discrètement");
		continuer();
		clearScreen();
		cursor(24,25);print("Le loustique se retourne brusquement");
		delay(2500);
		clearScreen();
		cursor(20,5);print("Napoléonard :");cursor(22,7);print("Vue ! Arrêtez vous là ! Je sais qui vous êtes , mon frère LOUSTIQUE DE FRANÇAIS m’a prévenu de votre intrusion !");
		cursor(23,7);print("Je suis Napoléonard et je vais vous réduire en bouillie pour venger mon frère !");
		cursor(25,5);print("𝙂𝙖𝙗𝙧𝙞𝙚𝙡 :");cursor(27,7);print("En garde "+eleve.nomDuJoueur+" il va falloir te défendre de nouveau !");
		continuer();
	}

	void dialogue4rencontre(Joueur eleve){
		cursor(20,5);print("𝙂𝙖𝙗𝙧𝙞𝙚𝙡  :");cursor(22,7);print("Quel combat ! Encore une fois, bravo "+eleve.nomDuJoueur+", je n’aurais jamais pu avancer comme cela si tu n’étais pas là… Je serais déjà mort depuis longtemps !");
		cursor(24,7);print("Mais restons vigilant car j’ai l’impression que les combats sont de plus en plus compliqués … Avançons il n’y a pas de temps à perdre , chaque seconde qui passe risque d’être critique");
		cursor(25,7);print("pour tes camarades et tes professeurs !");
		cursor(27,7);print("Au fond du couloir se trouve le gymnase, tout le monde est peut-être enfermé là bas, allons-y !");
		continuer();
		clearScreen();
		cursor(24,25);print("Vous entrez dans le gymnase");
		delay(2500);
		clearScreen();
		cursor(20,5);print("𝙂𝙖𝙗𝙧𝙞𝙚𝙡  :");cursor(22,7);print("Regardes là-bas sur la poutre il semble y avoir un loustique ! EH TOI Là-BAS , DIS-NOUS Où SONT NOS AMIS !");
		cursor(24,5);print("Cristophe Colombe :");cursor(26,7);print("Qui ose m’interrompre le grand Cristophe Colombe pendant qu’il s’amuse ?! Hm mais je vous ai déjà vu vous ! Vos amis ne sont pas ici");
		cursor(27,7);print("mais vous n’irez pas plus loin car je vais vous écraser !");
		cursor(29,5);print("𝙂𝙖𝙗𝙧𝙞𝙚𝙡  :");cursor(31,7);print(eleve.nomDuJoueur+" c'est à toi !");
		continuer();
	}

	void dialogue5rencontre(Joueur eleve){
		cursor(20,5);print("𝙂𝙖𝙗𝙧𝙞𝙚𝙡 :");cursor(22,7);print("Pfiou encore un de moins, comment ce gros plein de soupe a-t’il pu croire qu’il nous écraserait ?");
		cursor(24,7);print(" M’enfin ne prenons pas la grosse tête , nous devons avancer et rester vigilant ! Bon, où nos amis peuvent-ils bien être emprisonnés ?");
		cursor (26,7);print("Je sais ! Allons voir à la cantine ! C’est au bout du couloir à droite en sortant du gymnase");
		continuer();
		clearScreen();
		cursor(24,25);print("vous sortez du gymnase et courez jusqu’à la cantine");
		delay(2500);
		clearScreen();
		cursor(20,5);print("Miss California :");cursor(22,7);print("STOP ARRÊTEZ VOUS ! OU ALLEZ VOUS COMME ÇA ? Je suis Miss California et je vais vous arrêter ! ");
		cursor(24,5);print("𝙂𝙖𝙗𝙧𝙞𝙚𝙡 :");cursor(26,7);print("Mais d’où elle sort celle là, et puis qui est ce ? Oh! Ce doit être encore un des loustiques,enfin une des loustiques. Si mes calculs sont bons il doit en rester 3…");
		cursor(27,7);print("Aller en piste "+eleve.nomDuJoueur+" ! Après celui là, nous en aurons bientôt fini avec eux !");
		continuer();
	}

	void dialogue6rencontre(Joueur eleve){
		cursor(20,5);print("𝙂𝙖𝙗𝙧𝙞𝙚𝙡 :");cursor(22,7);print("C'était pas du gâteau celui là ! Nous pouvons enfin entrer dans la cantine maintenant que ce Nom du loustique ne nous embête plus … d’ailleurs j’ai un petit creux moi …");
		cursor(24,7);print("Je vais voir si je peux prendre un petit quelque chose à manger !");
		continuer();
		clearScreen();
		cursor (24,25);print("Vous entrez dans la cuisine");
		delay(2500);
		clearScreen();
		cursor(20,5);print("𝙂𝙖𝙗𝙧𝙞𝙚𝙡 :");cursor(22,7);print("Il n’y a personne ici … Mais où peuvent-ils bien être ? Je ne sais pas réfléchir le ventre vide … Je vais prendre quelque chose dans le frigo et nous repartirons après …");
		continuer();
		clearScreen();
		cursor (24,25);print("Gabriel ouvre le frigo");
		delay(2500);
		clearScreen();
		cursor(20,5);print("Inconnu :");cursor(22,7);print("BBBOOOOOUUUUUHHHHHHHH !!!!!!");
		cursor(24,5);print("𝙂𝙖𝙗𝙧𝙞𝙚𝙡 :");cursor(26,7);print("HHHHHHHAAAAAAAA !!!!! Mais t’es qui toi encore et puis tu m’as fait peur !");
		continuer();
		clearScreen();
		cursor(20,5);print("Eduardo DelArte");cursor(22,7);print("Quelle question ? Je suis un loustique bien évidement , je suis plus précisément Eduardo DelArte ! Comme tu peux le voir , tes amis ne sont pas dans la cantine , ni");
		cursor(24,7);print("dans ce frigo ! J’aurais pu vous laisser la vie sauve mais puisque vous m’avez dérangé pendant ma sieste , je vais vous anéantir !!");
		cursor(26,5);print("𝙂𝙖𝙗𝙧𝙞𝙚𝙡 :");cursor(28,7);print("Roh mais quel lourdeau celui là … "+eleve.nomDuJoueur+" tu sais ce qu’il te reste à faire !");
		continuer();
	}

	void dialogue7rencontre(Joueur eleve){
		cursor(20,5);print("𝙂𝙖𝙗𝙧𝙞𝙚𝙡 :");cursor(22,7);print("Et plus que deux ! On avance bien "+eleve.nomDuJoueur+" mais il faudrait vraiment trouver tout le monde maintenant il commence à se faire tard et Dieu va commencer à");
		cursor(23,7);print("s’inquiéter, j’aimerais beaucoup qu’il n’aie pas à se déplacer comme ça il pourra me confier des missions plus importantes à l’avenir ...");
		cursor(25,7);print("Réfléchissons , où peuvent-ils bien être ? Nous avons parcouru toutes les salles du bas, il ne reste donc plus que l’étage cependant … Il n’y a que le bureau du directeur là haut.");
		cursor(26,7);print("Se pourrait-il qu’ils y aient été enfermé ? Nous devons aller voir "+eleve.nomDuJoueur+", le temps presse !");
		continuer();
		clearScreen();
		cursor (24,25);print("Vous courrez vers les escaliers");
		delay(2500);
		clearScreen();
		cursor(20,5);print("𝙂𝙖𝙗𝙧𝙞𝙚𝙡 :");cursor(22,7);print("Regardes devant la porte du bureau du directeur, il y a un loustique qui monte la garde , nos amis doivent sûrement être à l’intérieur !");
		cursor(24,7);print("EH TOI ! REL CHE NOS AMIS , NOUS SAVONS QUE TU LES CACHES À L'INTÉRIEUR !");
		cursor(26,5);print("Thomas Tesla");cursor(28,7);print("Tout d’abord bonjour ! Je suis Thomas Tesla ! Je suis le plus puissant de mes frères , vous ne devriez pas me parler comme ça car je vais vous écraser et jamais");
		cursor(30,5);print("𝙂𝙖𝙗𝙧𝙞𝙚𝙡 :");cursor(32,7);print(eleve.nomDuJoueur+", c’est à toi , c’est la dernière ligne droite avant de libérer nos amis !");
		continuer();
	}

	void dialogue8rencontre(Joueur eleve){
		cursor(20,5);print("Thomas Tesla :");cursor(22,7);print("Comment avez vous pu me battre ? C’est impossible ! Déjà battre mes frères je ne comprends pas comment vous avez fait, mais moi ?");
		cursor(23,7);print("Le plus puissant de tous les loustiques ! Papa ne va pas être content !");
		cursor(25,5);print("𝙂𝙖𝙗𝙧𝙞𝙚𝙡 :");cursor(27,7);print("Papa ? Mais de qui parle t-il ? Peu importe nous les avons tous vaincus ! Allons retrouver nos amis !");
		continuer();
		clearScreen();
		cursor (24,25);print("Vous ouvrez la porte");
		delay(2500);
		clearScreen();
		cursor(20,5);print("Maîtresse :");cursor(22,7);print(eleve.nomDuJoueur+" ! Tu nous as retrouvés ! Je commençais à perdre espoir , nous avons eu tellement peur avec tes camarades.");
		cursor(24,5);print("𝙂𝙖𝙗𝙧𝙞𝙚𝙡 :");cursor(26,7);print("Bonjour à tous, je ne voudrais pas vous couper dans vos retrouvailles mais je pense que l’on ferait mieux de partir rapidement.");
		cursor(28,5);print("Maîtresse :");cursor(30,7);print("Vous avez raison, partons !");
		continuer();
		clearScreen();
		cursor (24,25);print("Vous courez tous ensemble vers la sortie de l’école ");
		delay(2500);
		clearScreen();
		cursor(20,5);print("𝙂𝙖𝙗𝙧𝙞𝙚𝙡 :");cursor(22,7);print("Attendez ! Il y a quelqu'un devant la porte ! On dirait un loustique … Mais nous les avons tous battus … Celui-ci semble beaucoup plus grand et plus âgé que les autres …");
		cursor(24,7);print("Ce pourrait-il que ...? "+eleve.nomDuJoueur+" viens avec moi les autres restez en arrière , il doit s’agir du papa des loustique dont parlait le dernier loustique !");
		cursor(25,7);print("C’est notre ultime combat "+eleve.nomDuJoueur+". Nous devons nous donner à fond ! C’est parti !");
		cursor(27,7);print("Eh toi la bas ! Laisse nous passer !");
		continuer();
		clearScreen();
		cursor(20,5);print("Chef des loustiques :");cursor(22,7);print("C’est vous qui avez gâché tout mon travail et anéanti mes fils, les loustiques ?");
		cursor(24,5);print("𝙂𝙖𝙗𝙧𝙞𝙚𝙡 :");cursor(26,7);print("Un peu mon neveu , nous voulions sauver nos amis !");
		cursor(28,5);print("Chef des loustiques");cursor(30,7);print("Hmmm je vois ce sont vos amis , vous vous êtes bien battus et j’ai un marché à vous proposer !");
		continuer();
		clearScreen();
		cursor(20,5);print("Chef des loustiques :");cursor(22,7);print("Si vous me battez vous aurez tous la vie sauve et nous ne viendrons plus jamais vous embêter . Par contre si je vous bats, vous resterez enfermés ici pour le restant de vos jours !");
		cursor(24,5);print("𝙂𝙖𝙗𝙧𝙞𝙚𝙡 :");cursor(26,7);print(eleve.nomDuJoueur+", tu dois absolument gagner , donne tout !");
		continuer();

	}

	void dialogue9rencontre(Joueur eleve){
		cursor(20,5);print("𝙂𝙖𝙗𝙧𝙞𝙚𝙡 :");cursor(22,7);print("Pfiou ce calvaire est enfin fini , le chef des loustique est hors d'état de nuit ! Bravo "+eleve.nomDuJoueur+" !! Maintenant, sortons de cette école !");
		continuer();
		clearScreen();
		cursor (24,25);print("Vous sortez tous ensemble de l’école");
		delay(2500);
		clearScreen();
		cursor(20,5);print("Maîtresse :");cursor(22,7);print(eleve.nomDuJoueur+", je voudrais vous remercier de nous avoir sauvés ! Grâce à toi et ton ami ange , nous avons tous la vie sauve !");
		cursor(24,5);print("Camarades de classe");cursor(26,7);print("Merci "+eleve.nomDuJoueur+" !");
		cursor(28,5);print("Maîtresse");cursor(30,7);print("Allez maintenant tout le monde rentre chez soi vos parents doivent être inquiets ! Encore merci à vous deux !");
		continuer();
		clearScreen();
		cursor (24,25);print("Tout le monde s’en va tandis que vous restez avec Gabriel");
		delay(2500);
		clearScreen();
		cursor(20,5);print("𝙂𝙖𝙗𝙧𝙞𝙚𝙡 :");cursor(22,7);print("Bien "+eleve.nomDuJoueur+", nous avons enfin réussi, tu devrais rentrer chez toi mais avant j’aimerais que nous passions voir Dieu pour lui annoncer la bonne nouvelle !");
		cursor(24,7);print("Allez en route !");
		continuer();
		clearScreen();
		affichageChargement();
		cursor(20,5);print("Dieu :");cursor(22,7);print("Vous revoilà enfin ! Je commençais à me faire du soucis ! J’ai bien cru que j'allais devoir intervenir !");
		cursor(24,5);print("𝙂𝙖𝙗𝙧𝙞𝙚𝙡 :");cursor(26,7);print("Nous avons sauvé tout le monde ! Enfin "+eleve.nomDuJoueur+" à sauvé tout le monde, je n’ai fait que le guider.");
		continuer();
		clearScreen();
		cursor(20,5);print("Dieu :");cursor(22,7);print("Très bien, je vois que j’ai bien fait de te demander ton aide “NOM DU JOUEUR”, je suis très fier de toi, enfin… Très fier de vous, car même si tu n’as fait qu'être le guide");
		cursor(23,7);print("Gabriel, vous étiez une équipe et c’est ensemble que vous avez réussi !");
		cursor(25,7);print("Bon "+eleve.nomDuJoueur+", tu te souviens du jeu dont je t’avais parlé avant que nous soyons interrompu par les loustiques ?");
		continuer();
		clearScreen();
		cursor(20,5);print("Dieu :");cursor(22,7);print("Hé bien je te propose de rentrer chez toi et de te reposer après cette dure journée de combat ! Nous jouerons à ce jeu la prochaine fois, tu l’as bien mérité !");
		cursor(24,7);print("Reviens nous voir quand tu le désires ! Je te souhaite une bonne journée et encore merci pour tes camarades !");
		cursor(26,5);print("𝙂𝙖𝙗𝙧𝙞𝙚𝙡 :");cursor(28,7);print("A la prochaine "+eleve.nomDuJoueur+" ! Encore merci !");
		continuer();
		clearScreen();
		cursor (24,25);print("Vous rentrez donc chez vous, pour vous blottir dans le canapé après cette dure journée");
		delay(5000);
		clearScreen();
		cursor(24,25);print("Bravo "+eleve.nomDuJoueur+" ! Vous avez terminé le jeu avec succès !");
		delay(5000);
		clearScreen();
		cursor(10,70);print("Crédits :");
		cursor(14,40);print("Lucas DELESTREE");
		cursor(15,40);print("Maxime SOUDANT");
		delay(5000);
		clearScreen();
	}
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////



}