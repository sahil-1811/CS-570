//Assignment: 4
//Name: Sahil Mahendra Mody
//CWID:20007262
//Course-CS 570-B




package Maze;

import java.util.*;


public class Maze implements GridColors {

  
    private TwoDimGrid maze;

    public Maze(TwoDimGrid m) {
        maze = m;
    }

    public boolean findMazePath() {
        return findMazePath(0, 0); 
    }
    public boolean findMazePath(int x, int y) {

    	if (x < 0 || y < 0 || x >= maze.getNCols() || y >= maze.getNRows()) {
            return false;
        } else if (!maze.getColor(x, y).equals(NON_BACKGROUND)) {
            return false;
        } else if (x == maze.getNCols() - 1 && y == maze.getNRows() - 1) {
            maze.recolor(x, y, PATH);
            return true;
            } else {
            maze.recolor(x, y, PATH);
           if (findMazePath(x - 1, y) || findMazePath(x+1, y) ||findMazePath(x, y-1) || findMazePath(x, y + 1)) {
                return true;
                } else 
                {
                maze.recolor(x, y, TEMPORARY);
                return false;
            }
        }

    }

    public void findMazePathStackBased(int x, int y,ArrayList<ArrayList<PairInt>> result,Stack<PairInt> trace){

    	if (x < 0 || y < 0 || x > maze.getNCols() - 1 || y > maze.getNRows() - 1 ||
                (!maze.getColor(x, y).equals(NON_BACKGROUND))){
            return;
            } else if (x == maze.getNCols() - 1 && y == maze.getNRows() - 1) {
            trace.push(new PairInt(x, y));
            ArrayList<PairInt> cur = new ArrayList<>(trace);
            result.add(cur);
            trace.pop(); 
            maze.recolor(x, y, NON_BACKGROUND);
            return;
        } else {

            trace.push(new PairInt(x, y));
            maze.recolor(x, y, PATH); 
            findMazePathStackBased(x+1, y, result, trace);
            findMazePathStackBased(x-1, y, result, trace);
            findMazePathStackBased(x, y+1, result, trace);
            findMazePathStackBased(x, y-1, result, trace);
            maze.recolor(x, y, NON_BACKGROUND);
            trace.pop();
            return;
        }

    }

    public ArrayList<ArrayList<PairInt>> findAllMazePaths(int x, int y) {

        ArrayList<ArrayList<PairInt>> result = new ArrayList<>();
        Stack<PairInt> trace = new Stack<>();
        findMazePathStackBased(0, 0, result, trace);

        return result;
    }


    /*Finding the shortest path by counting element in ArrayList*/
    public ArrayList<PairInt> findMazePathMin(int x, int y) {

    	int index=0;
        ArrayList<ArrayList<PairInt>> paths=findAllMazePaths(x,y);
        if(paths.size()==0) {
       	 throw new ArrayIndexOutOfBoundsException("NO PATH");
        }
        int min=paths.get(0).size();
        for(int i=1;i<paths.size();i++) {
    		if(min>paths.get(i).size()) {
    			min=paths.get(i).size();
    			index=i;
    		}
    	}
       ArrayList<PairInt> smallest=paths.get(index);
       for(int i=0;i<smallest.size();i++) {
       	 maze.recolor(smallest.get(i).getX(),smallest.get(i).getY(),PATH);  
       }
       return smallest;
   }
    

    public void resetTemp() {
        maze.recolor(TEMPORARY, BACKGROUND);
    }
       public void restore() {
        resetTemp();
        maze.recolor(PATH, BACKGROUND);
        maze.recolor(NON_BACKGROUND, BACKGROUND);
    }
   }
