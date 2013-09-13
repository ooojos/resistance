import java.util.*;

public class ResistanceGame
{
	public static void main(String[] args)
	{
		int[] numSpiesIndex = {-1,-1,-1,-1,-1,2,2,3,3,3,4};
		String[] nameIndex = {"Abbey","Beth","Carmen","Dee","Eleanor", "Frida","Georgina","Harriet","Isabel","Julie"};

		Scanner cons = new Scanner(System.in);
		System.out.println("How many players?");

		int numPlayers = cons.nextInt();

		System.out.println("Number of players is "+numPlayers);

		if(numPlayers<5 || numPlayers>10){
			System.out.println("Doesn't Work");
			System.exit(0);
		}

		Player[] players = new Player[numPlayers];

		int numSpies = numSpiesIndex[numPlayers];

		System.out.println("There will be "+numSpies+" spies.");

		int[] spyIndex = new int[numPlayers];
		
		for(int i = 0 ; i < numSpies ; i++){
			int determineSpy = (int)(Math.random()*numPlayers);
			while(spyIndex[determineSpy]==1)
				determineSpy = (int)(Math.random()*numPlayers);
			spyIndex[determineSpy]=1;
		}
		

		for(int i = 0 ; i < numPlayers ; i++){
			players[i] = new Player(nameIndex[i], spyIndex[i], numPlayers);
			if(spyIndex[i] == 1){
				for(int j=0;j<numPlayers;j++){
					players[i].tableStatus[j] = spyIndex[j];
				}
			}
			if(spyIndex[i] == 0){
				for(int j=0;j<numPlayers;j++){
					players[i].tableStatus[j] = 0.5;
					if(i==j) players[i].tableStatus[j] = 0;
				}
			}
		}


		if(args.length !=0){ 
			for(int i = 0;i<numPlayers;i++)System.out.println(players[i]);
		}

		int goingFirst = (int)(Math.random()*numPlayers);

		int[][] missionSize = {{},{},{},{},{},{2,3,2,3,3},{2,3,4,3,4},{2,3,3,4,4},{3,4,4,5,5},{3,4,4,5,5},{3,4,4,5,5}};

		if(args.length != 0)
			System.out.println("The missions sizes are " + Arrays.toString(missionSize[numPlayers]));	

		int resScore = 0;
		int spyScore = 0;

		int captain = goingFirst;
		//ROUNDS
		for(int round = 0; round <5; round++){
			System.out.println("\nROUND "+round+":\n");
			int teamSize = missionSize[numPlayers][round];
			
			if(args.length != 0)System.out.println("Team size is "+teamSize);

			int proposalCounter = 0;
			boolean missionGoing = false;
			int[] proposal = {};

			while(proposalCounter < 5 && !missionGoing){
				proposal = players[captain].proposeTeam(teamSize);
				int noVotes = 0;
				for(int i = 0 ; i < numPlayers; i++){
					noVotes += players[i].voteOnMission(proposal);
				}
				if(noVotes < numPlayers/2){
				       missionGoing = true;
				}
				else{
					captain++;
					captain = captain % numPlayers;
					proposalCounter++;
				}
			}
			if(missionGoing){
				int[] missionActions = new int[teamSize];
				for(int i = 0 ; i < proposal.length; i++){
					missionActions[i] = players[proposal[i]].goOnMission();
				}

				if(args.length != 0) System.out.println(Arrays.toString(missionActions));
				//TODO: Shuffle the results if displaying them for human
				
				int succeeds = 0;
				int fails = 0;
				for(int i = 0 ; i < missionActions.length; i++){
					if(missionActions[i]==0)succeeds++;
					if(missionActions[i]==1)fails++;
				}

				//TODO Round 4 double fails, for big games.
				if(fails == 0)resScore++;
				else spyScore++;
	
				System.out.println(succeeds+" Succeeds and "+fails+" fails");

				boolean gameOver = false;
				if(resScore == 3){
					System.out.println("Resistance Wins");
					gameOver = true;
				}
				if(spyScore == 3){
					System.out.println("Spies Win");
					gameOver = true;
				}	
				if(gameOver){
					System.out.println("\nThe Spies were "+Arrays.toString(spyIndex));
					System.exit(0);
				}
							
			}
			if(proposalCounter==5){
				System.out.println("Too many Proposals; Evil wins");
				System.exit(0);
			}
		}


	}
}
