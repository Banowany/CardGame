package cardgame;

public class Players 
{
    private Player currentTrickLeader;
    private Player currentTrickNonLeader;

    public Players(Player currentTrickLeader, Player currentTricknonLeader) {
        this.currentTrickLeader = currentTrickLeader;
        this.currentTrickNonLeader = currentTricknonLeader;
    }

    public Player getLeader() {
        return currentTrickLeader;
    }

    public Player getNonLeader() {
        return currentTrickNonLeader;
    }        
    
    public boolean haveEmptyHands() 
    {
        return getLeader().hasEmptyHand() && getNonLeader().hasEmptyHand();
    }
    public void switchLeader(Player newLeader)
    {
        this.currentTrickNonLeader=getOtherPlayer(newLeader);
        //this.currentTrickNonLeader=((newLeader==this.currentTrickLeader) ? this.currentTrickNonLeader : this.currentTrickLeader);
        this.currentTrickLeader=newLeader;
        //1 przypadek: obecny leader zostaje leaderem
        //2 przypadek: obecny nonleader zostaje leaderem
    }
    
    public Player getOtherPlayer(Player player)
    {
        if(player == null)
        {
            throw new NullPointerException("Player can not be null");
        }
        if(player!=currentTrickLeader && player!=currentTrickNonLeader)
        {
            throw new IllegalArgumentException("player does not exist in this game");
        }
        
        return (player == getNonLeader() ? getLeader():getNonLeader());
    }

    public void resetSuitDuplicatedIndex() 
    {
        getLeader().resetSuitDuplicatedIndex();
        getNonLeader().resetSuitDuplicatedIndex();
    }
}
