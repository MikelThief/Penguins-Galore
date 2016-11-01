#include "user.h"
#include <stdlib.h>
#include <time.h>

int PengCol(int ID, int AllPengs, int PengArray[AllPengs][3], int Row)
{
    int i;

    for(i=0; i < AllPengs; i++) {
        if(PengArray[Row][i] == ID)
            return i;
    }
    return -1;
}

int FBCrdX(int s, int PID, int a, int NumOfRows, int NumOfCols, int FishArray[NumOfRows][NumOfCols], int AllPengs, int PengArray[AllPengs][3])
{
    int crd;
    srand(time(NULL));
    if (NumOfRows!=1)
        crd = rand() % (NumOfRows-2) + 1;
    else crd=0;
    return(crd);
}


int FBCrdY(int s, int PID, int a, int NumOfRows, int NumOfCols, int FishArray[NumOfRows][NumOfCols], int AllPengs, int PengArray[AllPengs][3])
{
    int crd;
    srand(time(NULL));
    do
    {
        if (NumOfCols!=1)
            crd = rand() % (NumOfCols-2) + 1;
        else crd=0;
    } while (CheckPeng(FBCrdX(s, PID, a, NumOfRows, NumOfCols, FishArray, AllPengs, PengArray), crd, AllPengs, PengArray)!=0);

    return(crd);
}


int FBSpaces(int s, int PID, int a, int NumOfRows, int NumOfCols, int FishArray[NumOfRows][NumOfCols], int AllPengs, int PengArray[AllPengs][3])
{
    return (1);
}

int FBDir(int s, int PID, int a, int NumOfRows, int NumOfCols, int FishArray[NumOfRows][NumOfCols], int AllPengs, int PengArray[AllPengs][3]) {

    int d, idRow, d1, d2, d3, d4, d5, d6, idCol;
    idRow = WhichPenguin(PID, AllPengs, PengArray);
    idCol = PengCol(PID, AllPengs, PengArray, idRow);

    srand(time(NULL));

    /*Searching for the number of fish*/
    if (idRow!=0)
    {
        d6=FishArray[idRow-1][idCol];
        if (idCol+1<=NumOfCols)
            d1=FishArray[idRow-1][idCol+1];
        else
            d1=0;
    }
    else
    {
        d1=0;
        d6=0;
    }

    if (idCol+1<=NumOfCols)
    {
        d2=FishArray[idRow][idCol+1];
        if (idRow+1<=NumOfRows)
            d3=FishArray[idRow+1][idCol+1];
        else
            d3=0;
    }
    else
    {
        d2=0;
        d3=0;
    }

    if (idRow+1<=NumOfRows)
        d4=FishArray[idRow+1][idCol];
    else
        d4=0;

    if (idCol!=0)
        d5=FishArray[idRow][idCol-1];
    else
        d5=0;

    /*Ends HERE*/


    /*The most horrible way of choosing*/

    int choice[6], i=0;

    for (i=0; i<6; i++)
        choice[i]=0;

    i=0;

    /*Any 3 fish possibility*/

    if (d1==3&&CheckMove(idRow, 1, s, NumOfCols, FishArray, AllPengs, PengArray))
    {
        choice[i]=1;
        i++;
    }
    if (d2==3&&CheckMove(idRow, 2, s, NumOfCols, FishArray, AllPengs, PengArray))
    {
        choice[i]=2;
        i++;
    }
    if (d3==3&&CheckMove(idRow, 3, s, NumOfCols, FishArray, AllPengs, PengArray))
    {
        choice[i]=3;
        i++;
    }
    if (d4==3&&CheckMove(idRow, 4, s, NumOfCols, FishArray, AllPengs, PengArray))
    {
        choice[i]=4;
        i++;
    }
    if (d5==3&&CheckMove(idRow, 5, s, NumOfCols, FishArray, AllPengs, PengArray))
    {
        choice[i]=5;
        i++;
    }
    if (d6==3&&CheckMove(idRow, 6, s, NumOfCols, FishArray, AllPengs, PengArray))
    {
        choice[i]=6;
        i++;
    }

    /*2 fish?*/
    if (i==0)
    {
        if (d1==2&&CheckMove(idRow, 1, s, NumOfCols, FishArray, AllPengs, PengArray))
        {
            choice[i]=1;
            i++;
        }
        if (d2==2&&CheckMove(idRow, 2, s, NumOfCols, FishArray, AllPengs, PengArray))
        {
            choice[i]=2;
            i++;
        }
        if (d3==2&&CheckMove(idRow, 3, s, NumOfCols, FishArray, AllPengs, PengArray))
        {
            choice[i]=3;
            i++;
        }
        if (d4==2&&CheckMove(idRow, 4, s, NumOfCols, FishArray, AllPengs, PengArray))
        {
            choice[i]=4;
            i++;
        }
        if (d5==2&&CheckMove(idRow, 5, s, NumOfCols, FishArray, AllPengs, PengArray))
        {
            choice[i]=5;
            i++;
        }
        if (d6==2&&CheckMove(idRow, 6, s, NumOfCols, FishArray, AllPengs, PengArray))
        {
            choice[i]=6;
            i++;
        }
        /*And one fish*/
        if (i==0)
        {
            if (d1==1&&CheckMove(idRow, 1, s, NumOfCols, FishArray, AllPengs, PengArray))
            {
                choice[i]=1;
                i++;
            }
            if (d2==1&&CheckMove(idRow, 2, s, NumOfCols, FishArray, AllPengs, PengArray))
            {
                choice[i]=2;
                i++;
            }
            if (d3==1&&CheckMove(idRow, 3, s, NumOfCols, FishArray, AllPengs, PengArray))
            {
                choice[i]=3;
                i++;
            }
            if (d4==1&&CheckMove(idRow, 4, s, NumOfCols, FishArray, AllPengs, PengArray))
            {
            choice[i]=4;
            i++;
            }
            if (d5==1&&CheckMove(idRow, 5, s, NumOfCols, FishArray, AllPengs, PengArray))
            {
            choice[i]=5;
            i++;
            }
            if (d6==1&&CheckMove(idRow, 6, s, NumOfCols, FishArray, AllPengs, PengArray))
            {
            choice[i]=6;
            i++;
            }
            if (i==0)
            {
                d= rand()% 6 + 1;
                return(d);
            }
        }
    }
    /*Now checking how many were found*/
    int j=0;

    for (i=0; i<6; i++)
        if(choice[i]!=0)
            j++;

    /*And randomly picking one of them*/

    i= rand() % j;
    d=choice[i];
    /*Ends HERE*/

    return(d);

}

int FBPengID(int s, int PID, int a, int NumOfRows, int NumOfCols, int FishArray[NumOfRows][NumOfCols], int AllPengs, int PengArray[AllPengs][3]) {

    int i, j;
    static int turn = 0;

    if(a % 2) {
        if(AllPengs == 2) {
            return 1;
        }
        for(i = turn; i < AllPengs; i++) {
            if(i % 2 == 0) {
                for(j = 1; j <= 6; j++) {
                    if(CheckMove(i, j, 1, NumOfCols, FishArray, AllPengs, PengArray)) {
                        if(turn == 0)
                            turn = 2;
                        else
                            turn = 0;
                        return ++i;
                    }
                }
            }
        }
        for(i = turn; i > -1; i--) {
            if(i % 2 == 0) {
                for(j = 1; j <= 6; j++) {
                    if(CheckMove(i, j, 1, NumOfCols, FishArray, AllPengs, PengArray)) {
                        if(turn == 0)
                            turn = 2;
                        else
                            turn = 0;
                        return ++i;
                    }
                }
            }
        }
    }else if(a % 2 == 0) {
        if(AllPengs == 2) {
            return 2;
        }
        for(i = turn; i < AllPengs; i++) {
            if(i % 2) {
                for(j = 1; j <= 6; j++) {
                    if(CheckMove(i, j, 1, NumOfCols, FishArray, AllPengs, PengArray)) {
                        if(turn == 0)
                            turn = 2;
                        else
                            turn = 0;
                        return ++i;
                    }
                }
            }
        }
        for(i = turn; i > -1; i--) {
            if(i % 2) {
                for(j = 1; j <= 6; j++) {
                    if(CheckMove(i, j, 1, NumOfCols, FishArray, AllPengs, PengArray)) {
                        if(turn == 0)
                            turn = 2;
                        else
                            turn = 0;
                        return ++i;
                    }
                }
            }
        }
    }
    return 0;
}
