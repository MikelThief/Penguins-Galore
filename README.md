# README: Penguins Galore

Penguins Galore is a computer board game for two players written in C.

### menu.c
This file is the main file of the whole programme. 
It contains all of the stages of the game:
* Game intro - see Intro() function from IntroOutro.c file
* Game set up - here players decide about algorithms that will be used in playing the game
* Game play - see PlayGame() function from PlayGame.c file

More about game set up: in menu.c file players choose which algorithm from Algorithms catalogue will be played. Those algorithms must contain 5 functions which are assigned to 10 (5 for each of the players) pointers in file menu.c.
* First function must return X coordinate of single penguin (for initial placing of the penguins) - PointerX
* Second function must return Y coordinate of single penguin (for initial placing of the penguins) - PointerY
* Third function must return ID of penguin that will be moved - PointerPengID
* Fourth function must return which direction a penguin will be moved (1=NE, 2=E, 3=SE, 4=SW, 5=W, 6=NW) - PointerDir
* Fifth function must return how many spaces will the penguin be moved - PointerSpaces

More about arguments of those functions will be said in section dedicated to algorithms


### PlayGame.c
This file is a core of game process. 





### Algorithms

Functions that will be later passed in menu.c file to PointerX and PointerY must have following arguments (names of these arguments are arbitrary):
* 1st arg: int s - (spaces) - I don't know what is the purpose of this argument
* 2nd arg: int PID - penguin ID which will be placed
* 3rd arg: int a - which penguin of the total number of penguins will be placed
* 4th arg: int NumOfRows - total number of rows of board + 2 (there are virtual, border rows)
* 5th arg: int NumOfCols - total number of columns of board + 2 (there are virtual, border columns)
* 6th arg: int FishArray[NumOfRows][NumOfCols] - array of values assigned to each floe (number of fish)
* 7th arg: int AllPengs - total number of penguins on board
* 8th arg: int PengArray[AllPengs][3] - array of penguins - it is an auxilliary arraynecessary for checking functions 



