#include "user.h"
#include <stdio.h>
#include <time.h>
#include <stdlib.h>


//start coordinate X
int KatjaRandomCrdX(int s, int PID, int a, int NumOfRows, int NumOfCols, int FishArray[NumOfRows][NumOfCols], int AllPengs, int PengArray[AllPengs][3]) {
srand(time(NULL));
    int i, j, x;
    do{
        x = rand()%(NumOfRows-1) + 1;
    }while(CheckPeng(i, j, AllPengs, PengArray) == 1);
    return x;
}
//start coordinate Y
int KatjaRandomCrdY(int s, int PID, int a, int NumOfRows, int NumOfCols, int FishArray[NumOfRows][NumOfCols], int AllPengs, int PengArray[AllPengs][3]) {
srand(time(NULL));
    int i, j, y;
     do{
        y = rand()%(NumOfCols-1) + 1;
    }while(CheckPeng(i, j, AllPengs, PengArray) == 1);
    return y;
}
//How much spaces?
int KatjaRandomSpaces(int s, int PID, int a, int NumOfRows, int NumOfCols, int FishArray[NumOfRows][NumOfCols], int AllPengs, int PengArray[AllPengs][3]) {
srand(time(NULL));
    int sp,i,j;
    do{
        sp = rand()%3 +1;
    }while(CheckPeng(i, j, AllPengs, PengArray) == 1);


    return sp;
}


int KatjaRandomDir(int s, int PID, int a, int NumOfRows, int NumOfCols, int FishArray[NumOfRows][NumOfCols], int AllPengs, int PengArray[AllPengs][3]) {

    int d, idRow;
    idRow = WhichPenguin(PID, AllPengs, PengArray);

    if(CheckMove(idRow, 2, s, NumOfCols, FishArray, AllPengs, PengArray)) {
        d = 2;
    } else if(CheckMove(idRow, 3, s, NumOfCols, FishArray, AllPengs, PengArray)) {
        d = 3;
    } else if(CheckMove(idRow, 4, s, NumOfCols, FishArray, AllPengs, PengArray)) {
        d = 4;
    } else if(CheckMove(idRow, 5, s, NumOfCols, FishArray, AllPengs, PengArray)) {
        d = 5;
    } else if(CheckMove(idRow, 6, s, NumOfCols, FishArray, AllPengs, PengArray)) {
        d = 6;
    } else if(CheckMove(idRow, 1, s, NumOfCols, FishArray, AllPengs, PengArray)) {
        d = 1;
    }

    return d;
}

int KatjaRandomPengID(int s, int PID, int a, int NumOfRows, int NumOfCols, int FishArray[NumOfRows][NumOfCols], int AllPengs, int PengArray[AllPengs][3]) {

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
