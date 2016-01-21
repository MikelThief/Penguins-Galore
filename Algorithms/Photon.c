#include <math.h>
#include <stdio.h>
#include <stdlib.h>
#include "user.h"



/* Legend - parameter Dir
NE - 1
E -2
SE - 3
SW - 4
W - 5
NW - 6
*/

int PhotonCrdX(int Spaces, int PengID, int Turn, int NumberOfRows, int NumberOfColumns, int FishArray[NumberOfRows][NumberOfColumns], int AllPengs, int PengArray[AllPengs][3]){

    static int i,j;
    i = 3;
    j = 3;
    for (i; i<NumberOfRows - 1; i++){
            for (j; j<NumberOfColumns - 1; j++){
                if(FishArray[i][j] == 3){
                    if(i % 2){
                        if (FishArray[i-1][j+1] == 1 && CheckPeng(i-1, j+1, AllPengs, PengArray) == 0) return i-1;  //NE
                    else if (FishArray[i][j+1] == 1 && CheckPeng(i, j+1, AllPengs, PengArray) == 0) return i; //E
                    else if (FishArray[i+1][j+1] == 1 && CheckPeng(i+1, j+1, AllPengs, PengArray) == 0) return i+1; //SE
                    else if (FishArray[i+1][j] == 1 && CheckPeng(i+1, j, AllPengs, PengArray) == 0) return i+1; //SW
                    else if (FishArray[i][j-1] == 1 && CheckPeng(i, j-1, AllPengs, PengArray) == 0) return i; //W
                    else if (FishArray[i-1][j] == 1 && CheckPeng(i-1, j, AllPengs, PengArray) == 0) return i-1; //NW
                    else j+=1;
                        }
                        else{
                        if (FishArray[i-1][j] == 1 && CheckPeng(i-1, j, AllPengs, PengArray) == 0) return i-1; //NE
                    else if (FishArray[i][j+1] == 1 && CheckPeng(i, j+1, AllPengs, PengArray) == 0) return i; //E
                    else if (FishArray[i+1][j] == 1 && CheckPeng(i+1, j, AllPengs, PengArray) == 0) return i+1; //SE
                    else if (FishArray[i+1][j-1] == 1 && CheckPeng(i+1, j-1, AllPengs, PengArray) == 0) return i+1; //SW
                    else if (FishArray[i][j-1] == 1 && CheckPeng(i, j-1, AllPengs, PengArray) == 0) return i; //W
                    else if (FishArray[i-1][j-1] == 1 && CheckPeng(i-1, j-1, AllPengs, PengArray) == 0) return i-1; //NW
                    else j+=1;
                        }
                }
            }
    }
}
int PhotonCrdY(int Spaces, int PengID, int Turn, int NumberOfRows, int NumberOfColumns, int FishArray[NumberOfRows][NumberOfColumns], int AllPengs, int PengArray[AllPengs][3]){
    static int i,j;
    i = 3;
    j = 3;
    for (i; i<NumberOfRows - 1; i++){
            for (j; j<NumberOfColumns - 1; j++){
                if(FishArray[i][j] == 3){
                    if(i % 2){
                        if (FishArray[i-1][j+1] == 1 && CheckPeng(i-1, j+1, AllPengs, PengArray) == 0) return j+1;  //NE
                    else if (FishArray[i][j+1] == 1 && CheckPeng(i, j+1, AllPengs, PengArray) == 0) return j+1; //E
                    else if (FishArray[i+1][j+1] == 1 && CheckPeng(i+1, j+1, AllPengs, PengArray) == 0) return j+1; //SE
                    else if (FishArray[i+1][j] == 1 && CheckPeng(i+1, j, AllPengs, PengArray) == 0) return j; //SW
                    else if (FishArray[i][j-1] == 1 && CheckPeng(i, j-1, AllPengs, PengArray) == 0) return j-1; //W
                    else if (FishArray[i-1][j] == 1 && CheckPeng(i-1, j, AllPengs, PengArray) == 0) return j; //NW
                    else j+=1;
                        }
                        else{
                        if (FishArray[i-1][j] == 1 && CheckPeng(i-1, j, AllPengs, PengArray) == 0) return j; //NE
                    else if (FishArray[i][j+1] == 1 && CheckPeng(i, j+1, AllPengs, PengArray) == 0) return j+1; //E
                    else if (FishArray[i+1][j] == 1 && CheckPeng(i+1, j, AllPengs, PengArray) == 0) return j; //SE
                    else if (FishArray[i+1][j-1] == 1 && CheckPeng(i+1, j-1, AllPengs, PengArray) == 0) return j-1; //SW
                    else if (FishArray[i][j-1] == 1 && CheckPeng(i, j-1, AllPengs, PengArray) == 0) return j-1; //W
                    else if (FishArray[i-1][j-1] == 1 && CheckPeng(i-1, j-1, AllPengs, PengArray) == 0) return j-1; //NW
                    else j+=1;
                        }
                }
            }
    }

}

int PhotonMaxFinder(int var1, int var2, int var3, int var4, int var5, int var6)
{
    if(var1 >= var2 && var1 >= var3 && var1 >= var4 && var1 >= var5 && var1 >= var6) return var1;
    else if(var2 >= var1 && var2 >= var3 && var2 >= var4 && var2 >= var5 && var2 >= var6) return var2;
    else if(var3 >= var1 && var3 >= var2 && var3 >= var4 && var3 >= var5 && var3 >= var6) return var3;
    else if(var4 >= var1 && var4 >= var2 && var4 >= var3 && var4 >= var5 && var4 >= var6) return var4;
    else if(var5 >= var1 && var5 >= var2 && var5 >= var3 && var5 >= var4 && var5 >= var6) return var5;
    else if(var6 >= var1 && var6 >= var2 && var6 >= var3 && var6 >= var4 && var6 >= var5) return var6;
}

int PhotonDirFinder(int Spaces, int X_coord, int Y_coord, int NumberOfRows, int NumberOfColumns, int FishArray[NumberOfRows][NumberOfColumns], int AllPengs, int PengArray[AllPengs][3]) {

    int Highest = 0, NE, E, SE, SW, W, NW, TargetX, TargetY;
    int BestDir;

    if(X_coord%2) {
        TargetX = X_coord - Spaces;
        TargetY = Y_coord + Spaces/2;
        if(TargetX > 0 && TargetY > 0 && TargetX < NumberOfRows && TargetY < NumberOfColumns && CheckTile(TargetX, TargetY, NumberOfColumns, FishArray, AllPengs, PengArray) == 1) {
            NE = FishArray[TargetX][TargetY];
        } else NE = 0;

        TargetX = X_coord;
        TargetY = Y_coord + Spaces;
        if(TargetX > 0 && TargetY > 0 && TargetX < NumberOfRows && TargetY < NumberOfColumns && CheckTile(TargetX, TargetY, NumberOfColumns, FishArray, AllPengs, PengArray) == 1) {
            E = FishArray[TargetX][TargetY];
        } else E = 0;

        TargetX = X_coord + Spaces;
        TargetY = Y_coord + Spaces/2;
        if(TargetX > 0 && TargetY > 0 && TargetX < NumberOfRows && TargetY < NumberOfColumns && CheckTile(TargetX, TargetY, NumberOfColumns, FishArray, AllPengs, PengArray) == 1) {
            SE = FishArray[TargetX][TargetY];
        } else SE = 0;

        TargetX = X_coord + Spaces;
        TargetY = Y_coord - Spaces/2 - 1;
        if(TargetX > 0 && TargetY > 0 && TargetX < NumberOfRows && TargetY < NumberOfColumns && CheckTile(TargetX, TargetY, NumberOfColumns, FishArray, AllPengs, PengArray) == 1) {
            SW = FishArray[TargetX][TargetY];
        } else SW = 0;

        TargetX = X_coord;
        TargetY = Y_coord - Spaces;
        if(TargetX > 0 && TargetY > 0 && TargetX < NumberOfRows && TargetY < NumberOfColumns && CheckTile(TargetX, TargetY, NumberOfColumns, FishArray, AllPengs, PengArray) == 1) {
            W = FishArray[TargetX][TargetY];
        } else W = 0;

        TargetX = X_coord - Spaces;
        TargetY = Y_coord - Spaces/2 - 1;
        if(TargetX > 0 && TargetY > 0 && TargetX < NumberOfRows && TargetY < NumberOfColumns && CheckTile(TargetX, TargetY, NumberOfColumns, FishArray, AllPengs, PengArray) == 1) {
            NW = FishArray[TargetX][TargetY];
        } else NW = 0;

    } else {
        TargetX = X_coord - Spaces;
        TargetY = Y_coord + Spaces/2 + 1;
        if(TargetX > 0 && TargetY > 0 && TargetX < NumberOfRows && TargetY < NumberOfColumns && CheckTile(TargetX, TargetY, NumberOfColumns, FishArray, AllPengs, PengArray) == 1) {
            NE = FishArray[TargetX][TargetY];
        } else NE = 0;

        TargetX = X_coord;
        TargetY = Y_coord + Spaces;
        if(TargetX > 0 && TargetY > 0 && TargetX < NumberOfRows && TargetY < NumberOfColumns && CheckTile(TargetX, TargetY, NumberOfColumns, FishArray, AllPengs, PengArray) == 1) {
            E = FishArray[TargetX][TargetY];
        } else E = 0;

        TargetX = X_coord + Spaces;
        TargetY = Y_coord + Spaces/2 + 1;
        if(TargetX > 0 && TargetY > 0 && TargetX < NumberOfRows && TargetY < NumberOfColumns && CheckTile(TargetX, TargetY, NumberOfColumns, FishArray, AllPengs, PengArray) == 1) {
            SE = FishArray[TargetX][TargetY];
        } else SE = 0;

        TargetX = X_coord + Spaces;
        TargetY = Y_coord - Spaces/2;
        if(TargetX > 0 && TargetY > 0 && TargetX < NumberOfRows && TargetY < NumberOfColumns && CheckTile(TargetX, TargetY, NumberOfColumns, FishArray, AllPengs, PengArray) == 1) {
            SW = FishArray[TargetX][TargetY];
        } else SW = 0;

        TargetX = X_coord;
        TargetY = Y_coord - Spaces;
        if(TargetX > 0 && TargetY > 0 && TargetX < NumberOfRows && TargetY < NumberOfColumns && CheckTile(TargetX, TargetY, NumberOfColumns, FishArray, AllPengs, PengArray) == 1) {
            W = FishArray[TargetX][TargetY];
        } else W = 0;

        TargetX = X_coord - Spaces;
        TargetY = Y_coord - Spaces/2;
        if(TargetX > 0 && TargetY > 0 && TargetX < NumberOfRows && TargetY < NumberOfColumns && CheckTile(TargetX, TargetY, NumberOfColumns, FishArray, AllPengs, PengArray) == 1) {
            NW = FishArray[TargetX][TargetY];
        } else NW = 0;
    }

    Highest = PhotonMaxFinder(NE, E, SE, SW, W, NW);

    if(Highest == NE) return 1;
    else if(Highest == E) return 2;
    else if(Highest == SE) return 3;
    else if(Highest == SW) return 4;
    else if(Highest == W) return 5;
    else if(Highest == NW) return 6;
}

int PhotonFishFinder(int Spaces, int X_coord, int Y_coord, int NumberOfRows, int NumberOfColumns, int FishArray[NumberOfRows][NumberOfColumns], int AllPengs, int PengArray[AllPengs][3]) {

    int Highest = 0, NE, E, SE, SW, W, NW, TargetX, TargetY;

    if(X_coord%2) {
        TargetX = X_coord - Spaces;
        TargetY = Y_coord + Spaces/2;
        if(TargetX > 0 && TargetY > 0 && TargetX < NumberOfRows && TargetY < NumberOfColumns && CheckTile(TargetX, TargetY, NumberOfColumns, FishArray, AllPengs, PengArray) == 1) {
            NE = FishArray[TargetX][TargetY];
        } else NE = 0;

        TargetX = X_coord;
        TargetY = Y_coord + Spaces;
        if(TargetX > 0 && TargetY > 0 && TargetX < NumberOfRows && TargetY < NumberOfColumns && CheckTile(TargetX, TargetY, NumberOfColumns, FishArray, AllPengs, PengArray) == 1) {
            E = FishArray[TargetX][TargetY];
        } else E = 0;

        TargetX = X_coord + Spaces;
        TargetY = Y_coord + Spaces/2;
        if(TargetX > 0 && TargetY > 0 && TargetX < NumberOfRows && TargetY < NumberOfColumns && CheckTile(TargetX, TargetY, NumberOfColumns, FishArray, AllPengs, PengArray) == 1) {
            SE = FishArray[TargetX][TargetY];
        } else SE = 0;

        TargetX = X_coord + Spaces;
        TargetY = Y_coord - Spaces/2 - 1;
        if(TargetX > 0 && TargetY > 0 && TargetX < NumberOfRows && TargetY < NumberOfColumns && CheckTile(TargetX, TargetY, NumberOfColumns, FishArray, AllPengs, PengArray) == 1) {
            SW = FishArray[TargetX][TargetY];
        } else SW = 0;

        TargetX = X_coord;
        TargetY = Y_coord - Spaces;
        if(TargetX > 0 && TargetY > 0 && TargetX < NumberOfRows && TargetY < NumberOfColumns && CheckTile(TargetX, TargetY, NumberOfColumns, FishArray, AllPengs, PengArray) == 1) {
            W = FishArray[TargetX][TargetY];
        } else W = 0;

        TargetX = X_coord - Spaces;
        TargetY = Y_coord - Spaces/2 - 1;
        if(TargetX > 0 && TargetY > 0 && TargetX < NumberOfRows && TargetY < NumberOfColumns && CheckTile(TargetX, TargetY, NumberOfColumns, FishArray, AllPengs, PengArray) == 1) {
            NW = FishArray[TargetX][TargetY];
        } else NW = 0;

    } else {
        TargetX = X_coord - Spaces;
        TargetY = Y_coord + Spaces/2 + 1;
        if(TargetX > 0 && TargetY > 0 && TargetX < NumberOfRows && TargetY < NumberOfColumns && CheckTile(TargetX, TargetY, NumberOfColumns, FishArray, AllPengs, PengArray) == 1) {
            NE = FishArray[TargetX][TargetY];
        } else NE = 0;

        TargetX = X_coord;
        TargetY = Y_coord + Spaces;
        if(TargetX > 0 && TargetY > 0 && TargetX < NumberOfRows && TargetY < NumberOfColumns && CheckTile(TargetX, TargetY, NumberOfColumns, FishArray, AllPengs, PengArray) == 1) {
            E = FishArray[TargetX][TargetY];
        } else E = 0;

        TargetX = X_coord + Spaces;
        TargetY = Y_coord + Spaces/2 + 1;
        if(TargetX > 0 && TargetY > 0 && TargetX < NumberOfRows && TargetY < NumberOfColumns && CheckTile(TargetX, TargetY, NumberOfColumns, FishArray, AllPengs, PengArray) == 1) {
            SE = FishArray[TargetX][TargetY];
        } else SE = 0;

        TargetX = X_coord + Spaces;
        TargetY = Y_coord - Spaces/2;
        if(TargetX > 0 && TargetY > 0 && TargetX < NumberOfRows && TargetY < NumberOfColumns && CheckTile(TargetX, TargetY, NumberOfColumns, FishArray, AllPengs, PengArray) == 1) {
            SW = FishArray[TargetX][TargetY];
        } else SW = 0;

        TargetX = X_coord;
        TargetY = Y_coord - Spaces;
        if(TargetX > 0 && TargetY > 0 && TargetX < NumberOfRows && TargetY < NumberOfColumns && CheckTile(TargetX, TargetY, NumberOfColumns, FishArray, AllPengs, PengArray) == 1) {
            W = FishArray[TargetX][TargetY];
        } else W = 0;

        TargetX = X_coord - Spaces;
        TargetY = Y_coord - Spaces/2;
        if(TargetX > 0 && TargetY > 0 && TargetX < NumberOfRows && TargetY < NumberOfColumns && CheckTile(TargetX, TargetY, NumberOfColumns, FishArray, AllPengs, PengArray) == 1) {
            NW = FishArray[TargetX][TargetY];
        } else NW = 0;
    }

    return PhotonMaxFinder(NE, E, SE, SW, W, NW);
}

void PhotonMemory(int *ScanArray, int idRow, int spaces, int X_coord, int Y_coord, int NumberOfRows, int NumberOfColumns, int FishArray[NumberOfRows][NumberOfColumns], int AllPengs, int PengArray[AllPengs][3]) {
    int Precision=50; //Precision is fighting coefficient (higher vs advanced algs, lower for "stupid" algs
    int i, j;
    int Scanner[Precision][3];
    for(i = 0; i<Precision; i++)
        for(j=1; j <= 3; j++)
        {
            Scanner[i][0] = j;
            Scanner[i][1] = PhotonDirFinder(j, X_coord, Y_coord, NumberOfRows, NumberOfColumns, FishArray, AllPengs, PengArray);
            Scanner[i][2] = PhotonFishFinder(j, X_coord, Y_coord, NumberOfRows, NumberOfColumns, FishArray, AllPengs, PengArray);
        }

    ScanArray[0] = Scanner[0][0];
    ScanArray[1] = Scanner[0][1];

    for(i = 1; i<Precision; i++) {
        if(CheckMove(idRow, ScanArray[1], ScanArray[1], NumberOfColumns, FishArray, AllPengs, PengArray) == 0)
        {
            ScanArray[0] = Scanner[i][0];
            ScanArray[1] = Scanner[i][1];
        }
    }
}


int PhotonSpaces(int Spaces, int PengID, const int Turn, const int NumberOfRows, const int NumberOfColumns, const int FishArray[NumberOfRows][NumberOfColumns], const int AllPengs, int PengArray[AllPengs][3])
{
    int idRow = WhichPenguin(PengID, AllPengs, PengArray);
    int MyX = PengArray[idRow][1];
    int MyY = PengArray[idRow][2];
    int *MovePTR[2];

    PhotonMemory(MovePTR, idRow, Spaces, MyX, MyY, NumberOfRows, NumberOfColumns, FishArray, AllPengs, PengArray);

    int NumSpaces = MovePTR[0];
    int dir = MovePTR[1];

    if(CheckMove(idRow, dir, NumSpaces, NumberOfRows, FishArray, AllPengs, PengArray) == 0) NumSpaces = 1;

    return NumSpaces;

}

int PhotonPengID(int Spaces, int PengID, const int a, const int NumberOfRows, const int NumberOfColumns, const int FishArray[NumberOfRows][NumberOfColumns], const int AllPengs, int PengArray[AllPengs][3])
{
    int i, j;
    static int turn = 0;

    if(a % 2) {
        if(AllPengs == 2) {
            return 1;
        }
        for(i = turn; i < AllPengs; i++) {
            if(i % 2 == 0) {
                for(j = 1; j <= 6; j++) {
                    if(CheckMove(i, j, 1, NumberOfColumns, FishArray, AllPengs, PengArray)) {
                        if(turn == 0)  turn = 2;
                        else turn = 0;

                        return i+1;
                    }
                }
            }
        }
        for(i = turn; i > -1; i--) {
            if(i % 2 == 0) {
                for(j = 1; j <= 6; j++) {
                    if(CheckMove(i, j, 1, NumberOfColumns, FishArray, AllPengs, PengArray)) {
                        if(turn == 0) turn = 2;
                        else turn = 0;

                        return i+1;
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
                    if(CheckMove(i, j, 1, NumberOfColumns, FishArray, AllPengs, PengArray)) {
                        if(turn == 0) turn = 2;
                        else turn = 2;

                        return i+1;
                    }
                }
            }
        }
        for(i = turn; i > -1; i--) {
            if(i % 2) {
                for(j = 1; j <= 6; j++) {
                    if(CheckMove(i, j, 1, NumberOfColumns, FishArray, AllPengs, PengArray)) {
                        if(turn == 0) turn = 2;
                        else turn = 0;

                        return i+1;
                    }
                }
            }
        }
    }
    return 0;

}

int PhotonDir(int Spaces, int PengID, const int Turn, const int NumberOfRows, const int NumberOfColumns, const int FishArray[NumberOfRows][NumberOfColumns], const int AllPengs, int PengArray[AllPengs][3])
{
    int idRow = WhichPenguin(PengID, AllPengs, PengArray);
    int MyX = PengArray[idRow][1];
    int MyY = PengArray[idRow][2];

    int *MovePTR[2];

    PhotonMemory(MovePTR, idRow, Spaces, MyX, MyY, NumberOfRows, NumberOfColumns, FishArray, AllPengs, PengArray);

    int NumSpaces = MovePTR[0];
    int dir = MovePTR[1];

    if(CheckMove(idRow, dir, NumSpaces, NumberOfColumns, FishArray, AllPengs, PengArray) == 0) {
        NumSpaces = 1;
        dir = 1;

        while(CheckMove(idRow, dir, NumSpaces, NumberOfColumns, FishArray, AllPengs, PengArray) == 0) dir += 1;

    }

    return dir;

}






