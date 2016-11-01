#include "user.h"
#include <stdio.h>



int chng=0, avd;



int AdamiecCrdX(int s, int PID, int a, int NumOfRows, int NumOfCols, int FishArray[NumOfRows][NumOfCols], int AllPengs, int PengArray[AllPengs][3])
{

    int i, j, x;

    for(i = NumOfRows - 1; i >  0; i--) {
        for(j = NumOfCols - 1; j > 0; j--) {
            if(FishArray[i][j] == 1 && CheckPeng(i, j, AllPengs, PengArray) == 0) {
                x = i;
                return x;
            }
        }
    }
    return 0;
}


int AdamiecCrdY(int s, int PID, int a, int NumOfRows, int NumOfCols, int FishArray[NumOfRows][NumOfCols], int AllPengs, int PengArray[AllPengs][3])
{

   int i, j, y;

    for(i = NumOfRows - 1; i >  0; i--) {
        for(j = NumOfCols - 1; j > 0; j--) {
            if(FishArray[i][j] == 1 && CheckPeng(i, j, AllPengs, PengArray) == 0) {
                y = j;
                return y;
            }
        }
    }
    return 0;
}
int AdamiecSpaces(int s, int PID, int a, int NumOfRows, int NumOfCols, int FishArray[NumOfRows][NumOfCols], int AllPengs, int PengArray[AllPengs][3])
{
    return 1;
}

int AdamiecDir(int s, int PID, int a, int NumOfRows, int NumOfCols, int FishArray[NumOfRows][NumOfCols], int AllPengs, int PengArray[AllPengs][3])
{
    int d=5, idRow;
    avd = 0;
    idRow = WhichPenguin(PID, AllPengs, PengArray);

    if(CheckMove(idRow, 6, s, NumOfCols, FishArray, AllPengs, PengArray)==0 &&
    CheckMove(idRow, 1, s, NumOfCols, FishArray, AllPengs, PengArray)==0 &&
    CheckMove(idRow, 1, s, NumOfCols, FishArray, AllPengs, PengArray)==0 &&
    CheckMove(idRow, 2, s, NumOfCols, FishArray, AllPengs, PengArray)) {d=2; chng=1; avd=1; }         //nw corner

    if(CheckMove(idRow, 2, s, NumOfCols, FishArray, AllPengs, PengArray)==0 &&
    CheckMove(idRow, 1, s, NumOfCols, FishArray, AllPengs, PengArray)==0 &&
    CheckMove(idRow, 6, s, NumOfCols, FishArray, AllPengs, PengArray)==0)
    {
        if(CheckMove(idRow, 3, s, NumOfCols, FishArray, AllPengs, PengArray)){ d = 3; chng=2; avd=1;}         //ne corner
        else if(CheckMove(idRow, 4, s, NumOfCols, FishArray, AllPengs, PengArray)){ d = 4; chng=2;avd=1;}
    }

    if(CheckMove(idRow, 2, s, NumOfCols, FishArray, AllPengs, PengArray)==0 &&
    CheckMove(idRow, 3, s, NumOfCols, FishArray, AllPengs, PengArray)==0 &&
    CheckMove(idRow, 4, s, NumOfCols, FishArray, AllPengs, PengArray)==0 &&
    CheckMove(idRow, 5, s, NumOfCols, FishArray, AllPengs, PengArray)){ d = 5; chng=3; avd=1;}         // se corner

    if(CheckMove(idRow, 5, s, NumOfCols, FishArray, AllPengs, PengArray)==0 &&
    CheckMove(idRow, 4, s, NumOfCols, FishArray, AllPengs, PengArray)==0 &&
    CheckMove(idRow, 3, s, NumOfCols, FishArray, AllPengs, PengArray)==0)
    {
        if(CheckMove(idRow, 6, s, NumOfCols, FishArray, AllPengs, PengArray)){ d = 6; chng=0; avd=1;}         // sw corner
        else if(CheckMove(idRow, 1, s, NumOfCols, FishArray, AllPengs, PengArray)) { d = 1; chng=0; avd=1;}
    }


    if(chng == 0 && avd == 0)
    {
        if(CheckMove(idRow, 5, s, NumOfCols, FishArray, AllPengs, PengArray)) d = 5;

        else if(CheckMove(idRow, 6, s, NumOfCols, FishArray, AllPengs, PengArray)) d = 6;

        else if(CheckMove(idRow, 1, s, NumOfCols, FishArray, AllPengs, PengArray)) d = 1;

        else if(CheckMove(idRow, 2, s, NumOfCols, FishArray, AllPengs, PengArray)) d = 2;

        else if(CheckMove(idRow, 3, s, NumOfCols, FishArray, AllPengs, PengArray)) d = 3;

        else if(CheckMove(idRow, 4, s, NumOfCols, FishArray, AllPengs, PengArray)) d = 4;

    }

    if(chng == 2 && avd == 0)
    {
        if(CheckMove(idRow, 3, s, NumOfCols, FishArray, AllPengs, PengArray)) d = 3;

        else if(CheckMove(idRow, 4, s, NumOfCols, FishArray, AllPengs, PengArray)) d = 4;
    }


    if(chng == 1 && avd == 0)
    {
        if(CheckMove(idRow, 2, s, NumOfCols, FishArray, AllPengs, PengArray)) d = 2;

        else if(CheckMove(idRow, 3, s, NumOfCols, FishArray, AllPengs, PengArray)) d = 3;

        else if(CheckMove(idRow, 4, s, NumOfCols, FishArray, AllPengs, PengArray)) d = 4;

        else if(CheckMove(idRow, 5, s, NumOfCols, FishArray, AllPengs, PengArray)) d = 5;

        else if(CheckMove(idRow, 6, s, NumOfCols, FishArray, AllPengs, PengArray)) d = 6;

        else if(CheckMove(idRow, 1, s, NumOfCols, FishArray, AllPengs, PengArray)) d = 1;

    }

    if(chng == 3 && avd == 0)
    {
        if(CheckMove(idRow, 6, s, NumOfCols, FishArray, AllPengs, PengArray)) d = 6;

        else if(CheckMove(idRow, 1, s, NumOfCols, FishArray, AllPengs, PengArray)) d = 1;

        else if(CheckMove(idRow, 2, s, NumOfCols, FishArray, AllPengs, PengArray)) d = 2;

        else if(CheckMove(idRow, 3, s, NumOfCols, FishArray, AllPengs, PengArray)) d = 3;

        else if(CheckMove(idRow, 4, s, NumOfCols, FishArray, AllPengs, PengArray)) d = 4;

        else if(CheckMove(idRow, 5, s, NumOfCols, FishArray, AllPengs, PengArray)) d = 5;
    }

    return d;

}


int AdamiecPengID(int s, int PID, int a, int NumOfRows, int NumOfCols, int FishArray[NumOfRows][NumOfCols], int AllPengs, int PengArray[AllPengs][3])
{

    int i, j;

    if(a % 2)
    {
        if(AllPengs == 2)
        {
            return 1;
        }
        for(i = 0; i < AllPengs; i++)
        {
            if(i % 2 == 0)
            {
                for(j = 1; j <= 6; j++)
                {
                    if(CheckMove(i, j, 1, NumOfCols, FishArray, AllPengs, PengArray))
                    {
                        return ++i;
                    }
                }
            }
        }
    }
    else if(a % 2 == 0)
    {
        if(AllPengs == 2)
        {
            return 2;
        }
        for(i = 0; i < AllPengs; i++)
        {
            if(i % 2)
            {
                for(j = 1; j <= 6; j++)
                {
                    if(CheckMove(i, j, 1, NumOfCols, FishArray, AllPengs, PengArray))
                    {
                        return ++i;
                    }
                }
            }
        }
    }
    return 0;
}
