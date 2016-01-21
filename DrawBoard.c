#include "main.h"

//System calls act as placeholders as they are slow.

void DrawBoard(int NumOfRows, int NumOfCols, int FishArray[NumOfRows][NumOfCols], int AllPengs, int PengArray[AllPengs][3]) {

    int i,j;
    HANDLE  hConsole;
    hConsole = GetStdHandle(STD_OUTPUT_HANDLE);
    system ( "cls" );

    //printf columns numbers
    printf("    ");                              //printf columns numbers
    for(j = 1; j < NumOfCols - 1; j++) {         //
        SetConsoleTextAttribute(hConsole, 121); //
        printf("%i ", j);                        //
        SetConsoleTextAttribute(hConsole, 7);   //
        printf(" ");                           //
    }                                           //
    printf("\n");                               //

    for (i = 1; i < NumOfRows - 1; i++) {
        SetConsoleTextAttribute(hConsole, 121);
        printf("%3i", i);
        SetConsoleTextAttribute(hConsole, 7);
        if(i%2==1) printf(" ");
        for(j = 1; j < NumOfCols - 1; j++) {
            if(CheckPeng(i, j, AllPengs, PengArray)) { //Check if there's a penguin in given coordinates
                if(CheckPeng(i, j, AllPengs, PengArray) % 2) {
                    SetConsoleTextAttribute(hConsole, 14);  //yellow
                    printf(" P ");
                } else {
                    SetConsoleTextAttribute(hConsole, 12);  //red
                    printf(" P ");
                }
                SetConsoleTextAttribute(hConsole, 7);       //normal
            } else if(FishArray[i][j] == 0) {
                printf("   ");
            } else {
                printf(" %i ", FishArray[i][j]); //If not just print the amount of fishes
            }
        }
        printf("\n\n");
    }
    printf("\n");

    SetConsoleTextAttribute(hConsole, 7);

}

void PrintCoords(int score1, int score2, int AllPengs, int PengArray[AllPengs][3]) {
    HANDLE  hConsole;
    hConsole = GetStdHandle(STD_OUTPUT_HANDLE);

    //printf coords of pengs under map
    int k;

    SetConsoleTextAttribute(hConsole, 14);
    printf("Player 1");
    SetConsoleTextAttribute(hConsole, 12);
    printf("\t\tPlayer 2\n");
    SetConsoleTextAttribute(hConsole, 14);
    printf("Score: %d", score1);
    SetConsoleTextAttribute(hConsole, 12);
    printf("\t\tScore: %d\n", score2);

    for(k = 0; k < AllPengs; k++) {
        if (k % 2 == 0) {
            SetConsoleTextAttribute(hConsole, 14);
            printf("%i. [%i, %i]", k + 1, PengArray[k][1], PengArray[k][2]);
        }
        else if (k % 2!= 0 && k != 0) {
            SetConsoleTextAttribute(hConsole, 12);
            printf("\t\t%i. [%i, %i]\n", k + 1, PengArray[k][1], PengArray[k][2]);
        }
    }

    SetConsoleTextAttribute(hConsole, 7);
}
