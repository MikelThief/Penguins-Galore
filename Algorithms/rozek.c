//SOME TIME AGO IT WORKED, AFTER 124876128914724128741212914820 CHANGES IT MIGHT CRASH...

#include "user.h"
#include <stdio.h>
#include <time.h>

int RandomX(int s, int PID, int a, int NumOfRows, int NumOfCols, int FishArray[NumOfRows][NumOfCols], int AllPengs, int PengArray[AllPengs][3]) {

    int i, j, x;

    for(i = 1; i < NumOfRows - 1; i++) {
        for(j = 1; j < NumOfCols - 1; j++) {
            if(FishArray[i][j] == 1 && CheckPeng(i, j, AllPengs, PengArray) == 0) {
                x = i;
                return x;
            }
        }
    }
    return 0;
}

int RandomY(int s, int PID, int a, int NumOfRows, int NumOfCols, int FishArray[NumOfRows][NumOfCols], int AllPengs, int PengArray[AllPengs][3]) {

    int i, j, y;

    for(i = 1; i < NumOfRows - 1; i++) {
        for(j = 1; j < NumOfCols - 1; j++) {
            if(FishArray[i][j] == 1 && CheckPeng(i, j, AllPengs, PengArray) == 0) {
                y = j;
                return y;
            }
        }
    }
    return 0;
}

int RandomSpaces(int s, int PID, int a, int NumOfRows, int NumOfCols, int FishArray[NumOfRows][NumOfCols], int AllPengs, int PengArray[AllPengs][3]) {

    /*int sp;
    int minspaces=1;
    int maxspaces=3;
    sp = rand()%(maxspaces-minspaces)+minspaces;

    return sp;*/

    int sp;
    sp = 1;
    return sp;
}

int RandomDir(int s, int PID, int a, int NumOfRows, int NumOfCols, int FishArray[NumOfRows][NumOfCols], int AllPengs, int PengArray[AllPengs][3]) {

    int d, idRow;
    idRow = WhichPenguin(PID, AllPengs, PengArray);

    //srand(time(NULL))
    int min=1;
    int max=6;
    d = rand()%(max-min)+min;

    return d;
}

int RandomPengID(int s, int PID, int a, int NumOfRows, int NumOfCols, int FishArray[NumOfRows][NumOfCols], int AllPengs, int PengArray[AllPengs][3]) {

    int i, j;

    if(a % 2) {
        if(AllPengs == 2) {
            return 1;
        }
        for(i = 0; i < AllPengs; i++) {
            if(i % 2 == 0) {
                for(j = 1; j <= 6; j++) {
                    if(CheckMove(i, j, 1, NumOfCols, FishArray, AllPengs, PengArray)) {
                        return ++i;
                    }
                }
            }
        }
    }else if(a % 2 == 0) {
        if(AllPengs == 2) {
            return 2;
        }
        for(i = 0; i < AllPengs; i++) {
            if(i % 2) {
                for(j = 1; j <= 6; j++) {
                    if(CheckMove(i, j, 1, NumOfCols, FishArray, AllPengs, PengArray)) {
                        return ++i;
                    }
                }
            }
        }
    }
    return 0;
}
