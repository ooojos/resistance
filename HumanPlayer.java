import java.util.*;

public class HumanPlayer extends Player
{
	public Scanner cons = new Scanner(System.in);

	public int[] proposeTeam(int teamSize){
		int[] ret = new int[teamSize];
		System.out.println(name+", Pick "+teamSize+" people");
		for(int i = 0 ; i < teamSize ; i++){
			ret[i] = cons.nextInt();
		}
		return ret;
	}

	public HumanPlayer(String name_, int status_, int np){
		super(name_, status_, np);
		System.out.println("You are "+name+" and you are a:");
		if(status==0)System.out.println("resistance fighter");
		else System.out.println("Spy");
	}

	public String toString(){
	
		String ret = "Hello, I'm "+name+" and I'm a ";
		if(status == 0) ret+="member of the resistance.";
		if(status == 1) ret+="spy.";
		ret+=" And I'm Human";
		return ret;
	}

	public int goOnMission()
	{
		//0 is Play a Succeed Card, 1 is play a fail.
		System.out.println(name +", You're on a mission 0=play Succeed, 1=play fail");
		return cons.nextInt();
	}

	public int voteOnMission(int[] team)
	{
		//0=approve, 1=reject
		System.out.println(name+ ", 0=approve, 1=reject");
		return cons.nextInt();	
	}

}
