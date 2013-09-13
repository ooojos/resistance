import java.util.*;

public class Player
{
	public int status = 0;		//0=res, 1=spy
	public String name;
	public double[] tableStatus;

	public int[] proposeTeam(int teamSize)
	{
		int[] props = new int[teamSize];
		for(int i = 0 ; i<teamSize;i++)props[i]=i;
		return props;
	}

	public int voteOnMission(int[] team)
	{
		return 0;		//0 is approve, 1 is reject mission
	}

	public int goOnMission()
	{
		//0 is Play a Succeed Card, 1 is play a fail.
		if(status == 0) return 0;
		return (int)(Math.random()*2);
	}

	public Player(String _name, int _status, int np)
	{
		status = _status;
		name = _name;
		tableStatus = new double[np];
	}

	public String toString(){
		
		String ret = "Hello, I'm "+name+" and I'm a ";
		if(status == 0) ret+="member of the resistance.";
		if(status == 1) ret+="spy.";
		ret+=" And I think: "+Arrays.toString(tableStatus);
		return ret;
	}
}

