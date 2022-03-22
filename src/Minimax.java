/*
 * The University of North Carolina at Charlotte
 * ITCS 3153 - Intro to Artificial Intelligence
 * 
 * Programming Assignment 2 - Adversarial Search
 * 
 * Based on code from Dilip Kumar Subramanian
 * 
 * Modified by Julio C. Bahamon
 */

import java.util.ArrayList;


public class Minimax
{
	private static String AI_LETTER = "O";
	private static String PLAYER_LETTER = "X";
	
	/**
	 *
	 * This will recursively call Minimax depending on the current player, if the
	 * current player is O, the algorithm will find the MAX available board and if
	 * the current player is X, the algorithm will find the MIN avaiLable board.
	 * 
	 * We assume that the human player is X and that the AI is O
	 * 
	 * The terminal state check is done at the start before recursively calling
	 * Minimax, the terminal checks are checkWinner for player X(Human) and O(AI) and
	 * if the board state is full, if either of the conditions gets satisfied then
	 * it will return the value as decided if winner is AI(O), assign +1, if
	 * winner is User(X) assign -1 and if the state is draw assign 0 and return
	 *
	 * @param state
	 *            board for which the Minimax will be called recursively
	 * @param player
	 *            player for whom the game state should be generated
	 * @return int 
	 * 			  utility value for the current node
	 **/
	public static int miniMax(GameState state, String player)
	{
		GameAI.setTotalCount(GameAI.getTotalCount() + 1);
		
		// Use Double.NEGATIVE_INFINITY and Double.POSITIVE_INFINITY
		// for the initialization values
		int max = (int) Double.NEGATIVE_INFINITY;
		int min = (int) Double.POSITIVE_INFINITY;
		
		// Use the checkWinner method in GameState to check leaf nodes
		if(state.checkWinner(state.getBoardState(), AI_LETTER)) {
			return 1;		//If AI letter wins, return 1
		} 
		else if(state.checkWinner(state.getBoardState(), PLAYER_LETTER)) {
			return -1;		//If the player letter wins, return -1
		}// Use boardFullCheck method in GameState to check for tied games
		else if(state.boardFullCheck(state.getBoardState())) {
			return 0;		//If the board is full and its a tie game. Return 0
		}
		else {
			//If player is AI, calculate utility for the player
			if(player == AI_LETTER) {
				ArrayList<GameState> stateTree = state.generateSuccessors(state, AI_LETTER);
				int best = min;
				for(GameState states : stateTree) {
					//If the player is AI, generate utility for Human player
					int temp = Minimax.miniMax(states, PLAYER_LETTER);	
					best = Math.min(temp, best);
					state.printBoardStateMax(states.getBoardState());
				}
				return best;	
			}
			else {
				ArrayList<GameState> stateTree = state.generateSuccessors(state, PLAYER_LETTER);
				int best = max;
				for(GameState states : stateTree) {
					//If the player is HUMAN, generate utility for AI player
					int temp = Minimax.miniMax(states, AI_LETTER);	
					best = Math.max(temp, best);
					state.printBoardStateMin(states.getBoardState());
				}
				return best;
			}
		}
		
		/*	Use the printBoardStateMax to produce debug output
		 *  
		 */
		
		
//		DEBUG OUTPUT CODE
//		Log.debug("Inside maxValue " + " " + value);
//		Log.debug("Inside minValue " + " " + best);
		

	}
}