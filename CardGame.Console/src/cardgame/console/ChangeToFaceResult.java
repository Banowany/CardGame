package cardgame.console;

//przechowuje wynik zamiany i ewentualny Face

import cardgame.Face;

public class ChangeToFaceResult 
{
    private Face face;
    private boolean changeResult;

    public Face getFace() 
    {
        if(changeResult==false)
        {
       //     throw 
        }
        return face;
    }

    public boolean isChangeResult() 
    {
        return changeResult;
    }
}
