#include "user.h"
#include <stdio.h>
#include <math.h>
#include <time.h>
#include <stdlib.h>

// It is basically a greedy type algorithm

// max_6 - returns maximum of 6 values
int max_6(int var1, int var2, int var3, int var4, int var5, int var6) {

    int max;

    if(var1 >= var2 && var1 >= var3 && var1 >= var4 && var1 >= var5 && var1 >= var6)
        max = var1;
    if(var2 >= var1 && var2 >= var3 && var2 >= var4 && var2 >= var5 && var2 >= var6)
        max = var2;
    if(var3 >= var1 && var3 >= var2 && var3 >= var4 && var3 >= var5 && var3 >= var6)
        max = var3;
    if(var4 >= var1 && var4 >= var2 && var4 >= var3 && var4 >= var5 && var4 >= var6)
        max = var4;
    if(var5 >= var1 && var5 >= var2 && var5 >= var3 && var5 >= var4 && var5 >= var6)
        max = var5;
    if(var6 >= var1 && var6 >= var2 && var6 >= var3 && var6 >= var4 && var6 >= var5)
        max = var6;

    return max;
}

int RandCrd(int start, int end) {
    srand(time(NULL));
    return (start + rand()%(end-start+1));
}

// returns maximum possible gain by moving n spaces in each direction
int getHighest(int spaces, int Xcrd, int Ycrd, int NumOfRows, int NumOfCols, int FishArray[NumOfRows][NumOfCols], int AllPengs, int PengArray[AllPengs][3]) {

    int Highest = 0;
    int NE_val, E_val, SE_val, SW_val, W_val, NW_val;
    int X_crd2, Y_crd2;

    if(Xcrd%2) {
        X_crd2 = Xcrd - spaces;
        Y_crd2 = Ycrd + spaces/2;
        if(X_crd2 > 0 && Y_crd2 > 0 && X_crd2 < NumOfRows && Y_crd2 < NumOfCols && CheckTile(X_crd2, Y_crd2, NumOfCols, FishArray, AllPengs, PengArray) == 1) {
            NE_val = FishArray[X_crd2][Y_crd2];
        } else {
            NE_val = 0;
        }

        X_crd2 = Xcrd;
        Y_crd2 = Ycrd + spaces;
        if(X_crd2 > 0 && Y_crd2 > 0 && X_crd2 < NumOfRows && Y_crd2 < NumOfCols && CheckTile(X_crd2, Y_crd2, NumOfCols, FishArray, AllPengs, PengArray) == 1) {
            E_val = FishArray[X_crd2][Y_crd2];
        } else {
            E_val = 0;
        }

        X_crd2 = Xcrd + spaces;
        Y_crd2 = Ycrd + spaces/2;
        if(X_crd2 > 0 && Y_crd2 > 0 && X_crd2 < NumOfRows && Y_crd2 < NumOfCols && CheckTile(X_crd2, Y_crd2, NumOfCols, FishArray, AllPengs, PengArray) == 1) {
            SE_val = FishArray[X_crd2][Y_crd2];
        } else {
            SE_val = 0;
        }

        X_crd2 = Xcrd + spaces;
        Y_crd2 = Ycrd - spaces/2 - 1;
        if(X_crd2 > 0 && Y_crd2 > 0 && X_crd2 < NumOfRows && Y_crd2 < NumOfCols && CheckTile(X_crd2, Y_crd2, NumOfCols, FishArray, AllPengs, PengArray) == 1) {
            SW_val = FishArray[X_crd2][Y_crd2];
        } else {
            SW_val = 0;
        }

        X_crd2 = Xcrd;
        Y_crd2 = Ycrd - spaces;
        if(X_crd2 > 0 && Y_crd2 > 0 && X_crd2 < NumOfRows && Y_crd2 < NumOfCols && CheckTile(X_crd2, Y_crd2, NumOfCols, FishArray, AllPengs, PengArray) == 1) {
            W_val = FishArray[X_crd2][Y_crd2];
        } else {
            W_val = 0;
        }

        X_crd2 = Xcrd - spaces;
        Y_crd2 = Ycrd - spaces/2 - 1;
        if(X_crd2 > 0 && Y_crd2 > 0 && X_crd2 < NumOfRows && Y_crd2 < NumOfCols && CheckTile(X_crd2, Y_crd2, NumOfCols, FishArray, AllPengs, PengArray) == 1) {
            NW_val = FishArray[X_crd2][Y_crd2];
        } else {
            NW_val = 0;
        }
    } else {
        X_crd2 = Xcrd - spaces;
        Y_crd2 = Ycrd + spaces/2 + 1;
        if(X_crd2 > 0 && Y_crd2 > 0 && X_crd2 < NumOfRows && Y_crd2 < NumOfCols && CheckTile(X_crd2, Y_crd2, NumOfCols, FishArray, AllPengs, PengArray) == 1) {
            NE_val = FishArray[X_crd2][Y_crd2];
        } else {
            NE_val = 0;
        }

        X_crd2 = Xcrd;
        Y_crd2 = Ycrd + spaces;
        if(X_crd2 > 0 && Y_crd2 > 0 && X_crd2 < NumOfRows && Y_crd2 < NumOfCols && CheckTile(X_crd2, Y_crd2, NumOfCols, FishArray, AllPengs, PengArray) == 1) {
            E_val = FishArray[X_crd2][Y_crd2];
        } else {
            E_val = 0;
        }

        X_crd2 = Xcrd + spaces;
        Y_crd2 = Ycrd + spaces/2 + 1;
        if(X_crd2 > 0 && Y_crd2 > 0 && X_crd2 < NumOfRows && Y_crd2 < NumOfCols && CheckTile(X_crd2, Y_crd2, NumOfCols, FishArray, AllPengs, PengArray) == 1) {
            SE_val = FishArray[X_crd2][Y_crd2];
        } else {
            SE_val = 0;
        }

        X_crd2 = Xcrd + spaces;
        Y_crd2 = Ycrd - spaces/2;
        if(X_crd2 > 0 && Y_crd2 > 0 && X_crd2 < NumOfRows && Y_crd2 < NumOfCols && CheckTile(X_crd2, Y_crd2, NumOfCols, FishArray, AllPengs, PengArray) == 1) {
            SW_val = FishArray[X_crd2][Y_crd2];
        } else {
            SW_val = 0;
        }

        X_crd2 = Xcrd;
        Y_crd2 = Ycrd - spaces;
        if(X_crd2 > 0 && Y_crd2 > 0 && X_crd2 < NumOfRows && Y_crd2 < NumOfCols && CheckTile(X_crd2, Y_crd2, NumOfCols, FishArray, AllPengs, PengArray) == 1) {
            W_val = FishArray[X_crd2][Y_crd2];
        } else {
            W_val = 0;
        }

        X_crd2 = Xcrd - spaces;
        Y_crd2 = Ycrd - spaces/2;
        if(X_crd2 > 0 && Y_crd2 > 0 && X_crd2 < NumOfRows && Y_crd2 < NumOfCols && CheckTile(X_crd2, Y_crd2, NumOfCols, FishArray, AllPengs, PengArray) == 1) {
            NW_val = FishArray[X_crd2][Y_crd2];
        } else {
            NW_val = 0;
        }
    }

    Highest = max_6(NE_val, E_val, SE_val, SW_val, W_val, NW_val);

    return Highest;
}



// returns direction which guarantees the best gain in moving n-spaces
int getHighestDir(int spaces, int Xcrd, int Ycrd, int NumOfRows, int NumOfCols, int FishArray[NumOfRows][NumOfCols], int AllPengs, int PengArray[AllPengs][3]) {

    int Highest = 0;
    int NE_val, E_val, SE_val, SW_val, W_val, NW_val;
    int X_crd2, Y_crd2;
    int Direction;

    if(Xcrd%2) {
        X_crd2 = Xcrd - spaces;
        Y_crd2 = Ycrd + spaces/2;
        if(X_crd2 > 0 && Y_crd2 > 0 && X_crd2 < NumOfRows && Y_crd2 < NumOfCols && CheckTile(X_crd2, Y_crd2, NumOfCols, FishArray, AllPengs, PengArray) == 1) {
            NE_val = FishArray[X_crd2][Y_crd2];
        } else {
            NE_val = 0;
        }

        X_crd2 = Xcrd;
        Y_crd2 = Ycrd + spaces;
        if(X_crd2 > 0 && Y_crd2 > 0 && X_crd2 < NumOfRows && Y_crd2 < NumOfCols && CheckTile(X_crd2, Y_crd2, NumOfCols, FishArray, AllPengs, PengArray) == 1) {
            E_val = FishArray[X_crd2][Y_crd2];
        } else {
            E_val = 0;
        }

        X_crd2 = Xcrd + spaces;
        Y_crd2 = Ycrd + spaces/2;
        if(X_crd2 > 0 && Y_crd2 > 0 && X_crd2 < NumOfRows && Y_crd2 < NumOfCols && CheckTile(X_crd2, Y_crd2, NumOfCols, FishArray, AllPengs, PengArray) == 1) {
            SE_val = FishArray[X_crd2][Y_crd2];
        } else {
            SE_val = 0;
        }

        X_crd2 = Xcrd + spaces;
        Y_crd2 = Ycrd - spaces/2 - 1;
        if(X_crd2 > 0 && Y_crd2 > 0 && X_crd2 < NumOfRows && Y_crd2 < NumOfCols && CheckTile(X_crd2, Y_crd2, NumOfCols, FishArray, AllPengs, PengArray) == 1) {
            SW_val = FishArray[X_crd2][Y_crd2];
        } else {
            SW_val = 0;
        }

        X_crd2 = Xcrd;
        Y_crd2 = Ycrd - spaces;
        if(X_crd2 > 0 && Y_crd2 > 0 && X_crd2 < NumOfRows && Y_crd2 < NumOfCols && CheckTile(X_crd2, Y_crd2, NumOfCols, FishArray, AllPengs, PengArray) == 1) {
            W_val = FishArray[X_crd2][Y_crd2];
        } else {
            W_val = 0;
        }

        X_crd2 = Xcrd - spaces;
        Y_crd2 = Ycrd - spaces/2 - 1;
        if(X_crd2 > 0 && Y_crd2 > 0 && X_crd2 < NumOfRows && Y_crd2 < NumOfCols && CheckTile(X_crd2, Y_crd2, NumOfCols, FishArray, AllPengs, PengArray) == 1) {
            NW_val = FishArray[X_crd2][Y_crd2];
        } else {
            NW_val = 0;
        }
    } else {
        X_crd2 = Xcrd - spaces;
        Y_crd2 = Ycrd + spaces/2 + 1;
        if(X_crd2 > 0 && Y_crd2 > 0 && X_crd2 < NumOfRows && Y_crd2 < NumOfCols && CheckTile(X_crd2, Y_crd2, NumOfCols, FishArray, AllPengs, PengArray) == 1) {
            NE_val = FishArray[X_crd2][Y_crd2];
        } else {
            NE_val = 0;
        }

        X_crd2 = Xcrd;
        Y_crd2 = Ycrd + spaces;
        if(X_crd2 > 0 && Y_crd2 > 0 && X_crd2 < NumOfRows && Y_crd2 < NumOfCols && CheckTile(X_crd2, Y_crd2, NumOfCols, FishArray, AllPengs, PengArray) == 1) {
            E_val = FishArray[X_crd2][Y_crd2];
        } else {
            E_val = 0;
        }

        X_crd2 = Xcrd + spaces;
        Y_crd2 = Ycrd + spaces/2 + 1;
        if(X_crd2 > 0 && Y_crd2 > 0 && X_crd2 < NumOfRows && Y_crd2 < NumOfCols && CheckTile(X_crd2, Y_crd2, NumOfCols, FishArray, AllPengs, PengArray) == 1) {
            SE_val = FishArray[X_crd2][Y_crd2];
        } else {
            SE_val = 0;
        }

        X_crd2 = Xcrd + spaces;
        Y_crd2 = Ycrd - spaces/2;
        if(X_crd2 > 0 && Y_crd2 > 0 && X_crd2 < NumOfRows && Y_crd2 < NumOfCols && CheckTile(X_crd2, Y_crd2, NumOfCols, FishArray, AllPengs, PengArray) == 1) {
            SW_val = FishArray[X_crd2][Y_crd2];
        } else {
            SW_val = 0;
        }

        X_crd2 = Xcrd;
        Y_crd2 = Ycrd - spaces;
        if(X_crd2 > 0 && Y_crd2 > 0 && X_crd2 < NumOfRows && Y_crd2 < NumOfCols && CheckTile(X_crd2, Y_crd2, NumOfCols, FishArray, AllPengs, PengArray) == 1) {
            W_val = FishArray[X_crd2][Y_crd2];
        } else {
            W_val = 0;
        }

        X_crd2 = Xcrd - spaces;
        Y_crd2 = Ycrd - spaces/2;
        if(X_crd2 > 0 && Y_crd2 > 0 && X_crd2 < NumOfRows && Y_crd2 < NumOfCols && CheckTile(X_crd2, Y_crd2, NumOfCols, FishArray, AllPengs, PengArray) == 1) {
            NW_val = FishArray[X_crd2][Y_crd2];
        } else {
            NW_val = 0;
        }
    }

    Highest = max_6(NE_val, E_val, SE_val, SW_val, W_val, NW_val);

    if(Highest == NE_val) Direction = 1;
    if(Highest == E_val) Direction = 2;
    if(Highest == SE_val) Direction = 3;
    if(Highest == SW_val) Direction = 4;
    if(Highest == W_val) Direction = 5;
    if(Highest == NW_val) Direction = 6;

    return Direction;
}

void get_best_move(int *result, int idRow, int spaces, int Xcrd, int Ycrd, int NumOfRows, int NumOfCols, int FishArray[NumOfRows][NumOfCols], int AllPengs, int PengArray[AllPengs][3]) {

    int i, j;

    int best_4_moves[4][3]; // spaces, direction, fish

    for(i = 0; i<4; i++)
        for(j=1; j <= 3; j++) {
            best_4_moves[i][0] = j;
            best_4_moves[i][1] = getHighestDir(j, Xcrd, Ycrd, NumOfRows, NumOfCols, FishArray, AllPengs, PengArray);
            best_4_moves[i][2] = getHighest(j, Xcrd, Ycrd, NumOfRows, NumOfCols, FishArray, AllPengs, PengArray);
        }

    result[0] = best_4_moves[0][0];
    result[1] = best_4_moves[0][1];

    for(i = 1; i<4; i++) {
        if(CheckMove(idRow, result[1], result[1], NumOfCols, FishArray, AllPengs, PengArray) == 0) {
            result[0] = best_4_moves[i][0];
            result[1] = best_4_moves[i][1];
        }
    }
}







// Functions JulianCrdX and JulianCrdY will place penguin on a floe with 1 fish and
// with highest possible amount of fish to be gained in next 4 turns


int JulianCrdX(int s, int PID, int a, int NumOfRows, int NumOfCols, int FishArray[NumOfRows][NumOfCols], int AllPengs, int PengArray[AllPengs][3]) {

    int x, y;
    int loop = 1;

    while(loop == 1) {
        x = RandCrd(1,NumOfRows-1);
        y = RandCrd(1,NumOfCols-1);

        if(FishArray[x][y] == 1 && CheckPeng(x, y, AllPengs, PengArray) == 0) {
            loop = 0;
            return x;
        }
    }

    return 0;
}

int JulianCrdY(int s, int PID, int a, int NumOfRows, int NumOfCols, int FishArray[NumOfRows][NumOfCols], int AllPengs, int PengArray[AllPengs][3]) {

    int x, y;
    int loop = 1;

    while(loop == 1) {
        x = RandCrd(1,NumOfRows-1);
        y = RandCrd(1,NumOfCols-1);

        if(FishArray[x][y] == 1 && CheckPeng(x, y, AllPengs, PengArray) == 0) {
            loop = 0;
            return y;
        }
    }

    return 0;
}

int JulianSpaces(int s, int PID, int a, int NumOfRows, int NumOfCols, int FishArray[NumOfRows][NumOfCols], int AllPengs, int PengArray[AllPengs][3]) {

    int idRow = WhichPenguin(PID, AllPengs, PengArray);
    int PengX = PengArray[idRow][1];
    int PengY = PengArray[idRow][2];

    int *Move;
    Move = (int*)malloc(2*sizeof(int));

    get_best_move(Move, idRow, s, PengX, PengY, NumOfRows, NumOfCols, FishArray, AllPengs, PengArray);

    int sp = Move[0];
    int dir = Move[1];

    if(CheckMove(idRow, dir, sp, NumOfCols, FishArray, AllPengs, PengArray) == 0)
        sp = 1;

    return sp;
}

int JulianDir(int s, int PID, int a, int NumOfRows, int NumOfCols, int FishArray[NumOfRows][NumOfCols], int AllPengs, int PengArray[AllPengs][3]) {

    int idRow = WhichPenguin(PID, AllPengs, PengArray);
    int PengX = PengArray[idRow][1];
    int PengY = PengArray[idRow][2];

    int *Move;
    Move = (int*)malloc(2*sizeof(int));

    get_best_move(Move, idRow, s, PengX, PengY, NumOfRows, NumOfCols, FishArray, AllPengs, PengArray);

    int sp = Move[0];
    int dir = Move[1];

    // in some cases 'Greedy' algorithm does not work properly
    if(CheckMove(idRow, dir, sp, NumOfCols, FishArray, AllPengs, PengArray) == 0) {
        sp = 1;
        dir = 1;

        while(CheckMove(idRow, dir, sp, NumOfCols, FishArray, AllPengs, PengArray) == 0) {
            dir = dir + 1;
        }
    }

    return dir;
}

int JulianPengID(int s, int PID, int a, int NumOfRows, int NumOfCols, int FishArray[NumOfRows][NumOfCols], int AllPengs, int PengArray[AllPengs][3]) {

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
