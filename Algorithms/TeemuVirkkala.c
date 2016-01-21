#include "user.h"
#include <stdio.h>

int TeemuCrdX(int s, int PID, int a, int NumOfRows, int NumOfCols, int FishArray[NumOfRows][NumOfCols], int AllPengs, int PengArray[AllPengs][3]) {

    //Search for the x coordinate closest to center with 1 fish floes in it.
    int i, j, x, centerx;
    centerx = NumOfRows / 2;

    for(i = centerx; i < NumOfRows - 1; i++) {
        for(j = 1; j < NumOfCols - 1; j++) {
            if(FishArray[i][j] == 1 && CheckPeng(i, j, AllPengs, PengArray) == 0) {
                x = i;
                return x;
            }
        }
    }
    for(i = centerx; i > 0; i--) {
        for(j = 1; j < NumOfCols - 1; j++) {
            if(FishArray[i][j] == 1 && CheckPeng(i, j, AllPengs, PengArray) == 0) {
                x = i;
                return x;
            }
        }
    }
    return 0;
}

int TeemuCrdY(int s, int PID, int a, int NumOfRows, int NumOfCols, int FishArray[NumOfRows][NumOfCols], int AllPengs, int PengArray[AllPengs][3]) {

    //Search for the y coordinate closest to center with 1 fish floes in it.
    int i, j, y, centerx, centery;
    centerx = NumOfRows / 2;
    centery = NumOfCols / 2;

    for(i = centerx; i < NumOfRows - 1; i++) {
        for(j = centery; j < NumOfCols - 1; j++) {
            if(FishArray[i][j] == 1 && CheckPeng(i, j, AllPengs, PengArray) == 0) {
                y = j;
                return y;
            }
        }
        for(j = centery; j > 0; j--) {
            if(FishArray[i][j] == 1 && CheckPeng(i, j, AllPengs, PengArray) == 0) {
                y = j;
                return y;
            }
        }
    }
    for(i = centerx+1; i > 0; i--) {
        for(j = centery; j < NumOfCols - 1; j++) {
            if(FishArray[i][j] == 1 && CheckPeng(i, j, AllPengs, PengArray) == 0) {
                y = j;
                return y;
            }
        }
        for(j = centery; j > 0; j--) {
            if(FishArray[i][j] == 1 && CheckPeng(i, j, AllPengs, PengArray) == 0) {
                y = j;
                return y;
            }
        }
    }
    return 0;
}

int TeemuSpaces(int s, int PID, int a, int NumOfRows, int NumOfCols, int FishArray[NumOfRows][NumOfCols], int AllPengs, int PengArray[AllPengs][3]) {

    int sp;
    sp = 1; //Always move only 1 space
    return sp;
}

int TeemuDir(int s, int PID, int a, int NumOfRows, int NumOfCols, int FishArray[NumOfRows][NumOfCols], int AllPengs, int PengArray[AllPengs][3]) {

    int i, d, idRow;
    static int turn = 0;
    idRow = WhichPenguin(PID, AllPengs, PengArray);

    if(turn == 0) {
        if(AllPengs == 2)
            turn = 0;
        else if(AllPengs == 4)
            turn = 3;
        else
            turn = 1;
        //Set the counter to 2 so next time we'll have another moveset for the other penguin
        if(PengArray[idRow][1] % 2) { //Check if the current row the penguin is on is odd or even
            //Decide movement direction 1-6, preference on floes with 3 or 2 fishes on them
            if(CheckMove(idRow, 5, s, NumOfCols, FishArray, AllPengs, PengArray) && (CheckBoard(PengArray[idRow][1], PengArray[idRow][2]-1, NumOfCols, FishArray) == 3 || CheckBoard(PengArray[idRow][1], PengArray[idRow][2]-1, NumOfCols, FishArray) == 2)) {
                d = 5; //West
            } else if(CheckMove(idRow, 6, s, NumOfCols, FishArray, AllPengs, PengArray) && (CheckBoard(PengArray[idRow][1]-1, PengArray[idRow][2], NumOfCols, FishArray) == 3 || CheckBoard(PengArray[idRow][1]-1, PengArray[idRow][2], NumOfCols, FishArray) == 2)) {
                d = 6; //North-West
            } else if(CheckMove(idRow, 1, s, NumOfCols, FishArray, AllPengs, PengArray) && (CheckBoard(PengArray[idRow][1]-1, PengArray[idRow][2]+1, NumOfCols, FishArray) == 3 || CheckBoard(PengArray[idRow][1]-1, PengArray[idRow][2]+1, NumOfCols, FishArray) == 2)) {
                d = 1; //North-East
            } else if(CheckMove(idRow, 2, s, NumOfCols, FishArray, AllPengs, PengArray) && (CheckBoard(PengArray[idRow][1], PengArray[idRow][2]+1, NumOfCols, FishArray) == 3 || CheckBoard(PengArray[idRow][1], PengArray[idRow][2]+1, NumOfCols, FishArray) == 2)) {
                d = 2; //East
            } else if(CheckMove(idRow, 3, s, NumOfCols, FishArray, AllPengs, PengArray) && (CheckBoard(PengArray[idRow][1]+1, PengArray[idRow][2]+1, NumOfCols, FishArray) == 3 || CheckBoard(PengArray[idRow][1]+1, PengArray[idRow][2]+1, NumOfCols, FishArray) == 2)) {
                d = 3; //South-East
            } else if(CheckMove(idRow, 4, s, NumOfCols, FishArray, AllPengs, PengArray) && (CheckBoard(PengArray[idRow][1]+1, PengArray[idRow][2], NumOfCols, FishArray) == 3 || CheckBoard(PengArray[idRow][1]+1, PengArray[idRow][2], NumOfCols, FishArray) == 2)) {
                d = 4; //South-West
            } else {
                for(i = 5; i <= 6; i++) {
                    if(CheckMove(idRow, i, s, NumOfCols, FishArray, AllPengs, PengArray))
                        return i;
                }
                for(i = 1; i <= 4; i++) {
                    if(CheckMove(idRow, i, s, NumOfCols, FishArray, AllPengs, PengArray))
                        return i;
                }
            }
        } else {
            if(CheckMove(idRow, 5, s, NumOfCols, FishArray, AllPengs, PengArray) && (CheckBoard(PengArray[idRow][1], PengArray[idRow][2]-1, NumOfCols, FishArray) == 3 || CheckBoard(PengArray[idRow][1], PengArray[idRow][2]-1, NumOfCols, FishArray) == 2)) {
                d = 5; //West
            } else if(CheckMove(idRow, 6, s, NumOfCols, FishArray, AllPengs, PengArray) && (CheckBoard(PengArray[idRow][1]-1, PengArray[idRow][2]-1, NumOfCols, FishArray) == 3 || CheckBoard(PengArray[idRow][1]-1, PengArray[idRow][2]-1, NumOfCols, FishArray) == 2)) {
                d = 6; //North-West
            } else if(CheckMove(idRow, 1, s, NumOfCols, FishArray, AllPengs, PengArray) && (CheckBoard(PengArray[idRow][1]-1, PengArray[idRow][2], NumOfCols, FishArray) == 3 || CheckBoard(PengArray[idRow][1]-1, PengArray[idRow][2], NumOfCols, FishArray) == 2)) {
                d = 1; //North-East
            } else if(CheckMove(idRow, 2, s, NumOfCols, FishArray, AllPengs, PengArray) && (CheckBoard(PengArray[idRow][1], PengArray[idRow][2]+1, NumOfCols, FishArray) == 3 || CheckBoard(PengArray[idRow][1], PengArray[idRow][2]+1, NumOfCols, FishArray) == 2)) {
                d = 2; //East
            } else if(CheckMove(idRow, 3, s, NumOfCols, FishArray, AllPengs, PengArray) && (CheckBoard(PengArray[idRow][1]+1, PengArray[idRow][2], NumOfCols, FishArray) == 3 || CheckBoard(PengArray[idRow][1]+1, PengArray[idRow][2], NumOfCols, FishArray) == 2)) {
                d = 3; //South-East
            } else if(CheckMove(idRow, 4, s, NumOfCols, FishArray, AllPengs, PengArray) && (CheckBoard(PengArray[idRow][1]+1, PengArray[idRow][2]-1, NumOfCols, FishArray) == 3 || CheckBoard(PengArray[idRow][1]+1, PengArray[idRow][2]-1, NumOfCols, FishArray) == 2)) {
                d = 4; //West
            } else {
                for(i = 5; i <= 6; i++) {
                    if(CheckMove(idRow, i, s, NumOfCols, FishArray, AllPengs, PengArray))
                        return i;
                }
                for(i = 1; i <= 4; i++) {
                    if(CheckMove(idRow, i, s, NumOfCols, FishArray, AllPengs, PengArray))
                        return i;
                }
            }
        }
    } else if(turn == 1) {
        if(AllPengs == 6)
            turn = 3; //Set the counter to 2 so next time we'll have another moveset for the other penguin
        else
            turn = 2;
        if(PengArray[idRow][1] % 2) { //Check if the current row the penguin is on is odd or even
            //Decide movement direction 1-6, preference on floes with 3 or 2 fishes on them
            if(CheckMove(idRow, 1, s, NumOfCols, FishArray, AllPengs, PengArray) && (CheckBoard(PengArray[idRow][1]-1, PengArray[idRow][2]+1, NumOfCols, FishArray) == 3 || CheckBoard(PengArray[idRow][1]-1, PengArray[idRow][2]+1, NumOfCols, FishArray) == 2)) {
                d = 1; //North-East
            } else if(CheckMove(idRow, 2, s, NumOfCols, FishArray, AllPengs, PengArray) && (CheckBoard(PengArray[idRow][1], PengArray[idRow][2]+1, NumOfCols, FishArray) == 3 || CheckBoard(PengArray[idRow][1], PengArray[idRow][2]+1, NumOfCols, FishArray) == 2)) {
                d = 2; //East
            } else if(CheckMove(idRow, 3, s, NumOfCols, FishArray, AllPengs, PengArray) && (CheckBoard(PengArray[idRow][1]+1, PengArray[idRow][2]+1, NumOfCols, FishArray) == 3 || CheckBoard(PengArray[idRow][1]+1, PengArray[idRow][2]+1, NumOfCols, FishArray) == 2)) {
                d = 3; //South-East
            } else if(CheckMove(idRow, 4, s, NumOfCols, FishArray, AllPengs, PengArray) && (CheckBoard(PengArray[idRow][1]+1, PengArray[idRow][2], NumOfCols, FishArray) == 3 || CheckBoard(PengArray[idRow][1]+1, PengArray[idRow][2], NumOfCols, FishArray) == 2)) {
                d = 4; //South-West
            } else if(CheckMove(idRow, 5, s, NumOfCols, FishArray, AllPengs, PengArray) && (CheckBoard(PengArray[idRow][1], PengArray[idRow][2]-1, NumOfCols, FishArray) == 3 || CheckBoard(PengArray[idRow][1], PengArray[idRow][2]-1, NumOfCols, FishArray) == 2)) {
                d = 5; //West
            } else if(CheckMove(idRow, 6, s, NumOfCols, FishArray, AllPengs, PengArray) && (CheckBoard(PengArray[idRow][1]-1, PengArray[idRow][2], NumOfCols, FishArray) == 3 || CheckBoard(PengArray[idRow][1]-1, PengArray[idRow][2], NumOfCols, FishArray) == 2)) {
                d = 6; //North-West
            } else {
                for(i = 1; i <= 6; i++) {
                    if(CheckMove(idRow, i, s, NumOfCols, FishArray, AllPengs, PengArray))
                        return i;
                }
            }
        } else {
            if(CheckMove(idRow, 1, s, NumOfCols, FishArray, AllPengs, PengArray) && (CheckBoard(PengArray[idRow][1]-1, PengArray[idRow][2], NumOfCols, FishArray) == 3 || CheckBoard(PengArray[idRow][1]-1, PengArray[idRow][2], NumOfCols, FishArray) == 2)) {
                d = 1; //North-East
            } else if(CheckMove(idRow, 2, s, NumOfCols, FishArray, AllPengs, PengArray) && (CheckBoard(PengArray[idRow][1], PengArray[idRow][2]+1, NumOfCols, FishArray) == 3 || CheckBoard(PengArray[idRow][1], PengArray[idRow][2]+1, NumOfCols, FishArray) == 2)) {
                d = 2; //East
            } else if(CheckMove(idRow, 3, s, NumOfCols, FishArray, AllPengs, PengArray) && (CheckBoard(PengArray[idRow][1]+1, PengArray[idRow][2], NumOfCols, FishArray) == 3 || CheckBoard(PengArray[idRow][1]+1, PengArray[idRow][2], NumOfCols, FishArray) == 2)) {
                d = 3; //South-East
            } else if(CheckMove(idRow, 4, s, NumOfCols, FishArray, AllPengs, PengArray) && (CheckBoard(PengArray[idRow][1]+1, PengArray[idRow][2]-1, NumOfCols, FishArray) == 3 || CheckBoard(PengArray[idRow][1]+1, PengArray[idRow][2]-1, NumOfCols, FishArray) == 2)) {
                d = 4; //West
            } else if(CheckMove(idRow, 5, s, NumOfCols, FishArray, AllPengs, PengArray) && (CheckBoard(PengArray[idRow][1], PengArray[idRow][2]-1, NumOfCols, FishArray) == 3 || CheckBoard(PengArray[idRow][1], PengArray[idRow][2]-1, NumOfCols, FishArray) == 2)) {
                d = 5; //West
            } else if(CheckMove(idRow, 6, s, NumOfCols, FishArray, AllPengs, PengArray) && (CheckBoard(PengArray[idRow][1]-1, PengArray[idRow][2]-1, NumOfCols, FishArray) == 3 || CheckBoard(PengArray[idRow][1]-1, PengArray[idRow][2]-1, NumOfCols, FishArray) == 2)) {
                d = 6; //North-West
            } else {
                for(i = 1; i <= 6; i++) {
                    if(CheckMove(idRow, i, s, NumOfCols, FishArray, AllPengs, PengArray))
                        return i;
                }
            }
        }
    } else if(turn == 2) {
        turn = 3; //Set the counter to 2 so next time we'll have another moveset for the other penguin
        if(PengArray[idRow][1] % 2) { //Check if the current row the penguin is on is odd or even
            //Decide movement direction 1-6, preference on floes with 3 or 2 fishes on them
            if(CheckMove(idRow, 4, s, NumOfCols, FishArray, AllPengs, PengArray) && (CheckBoard(PengArray[idRow][1]+1, PengArray[idRow][2], NumOfCols, FishArray) == 3 || CheckBoard(PengArray[idRow][1]+1, PengArray[idRow][2], NumOfCols, FishArray) == 2)) {
                d = 4; //South-West
            } else if(CheckMove(idRow, 5, s, NumOfCols, FishArray, AllPengs, PengArray) && (CheckBoard(PengArray[idRow][1], PengArray[idRow][2]-1, NumOfCols, FishArray) == 3 || CheckBoard(PengArray[idRow][1], PengArray[idRow][2]-1, NumOfCols, FishArray) == 2)) {
                d = 5; //West
            } else if(CheckMove(idRow, 6, s, NumOfCols, FishArray, AllPengs, PengArray) && (CheckBoard(PengArray[idRow][1]-1, PengArray[idRow][2], NumOfCols, FishArray) == 3 || CheckBoard(PengArray[idRow][1]-1, PengArray[idRow][2], NumOfCols, FishArray) == 2)) {
                d = 6; //North-West
            } else if(CheckMove(idRow, 1, s, NumOfCols, FishArray, AllPengs, PengArray) && (CheckBoard(PengArray[idRow][1]-1, PengArray[idRow][2]+1, NumOfCols, FishArray) == 3 || CheckBoard(PengArray[idRow][1]-1, PengArray[idRow][2]+1, NumOfCols, FishArray) == 2)) {
                d = 1; //North-East
            } else if(CheckMove(idRow, 2, s, NumOfCols, FishArray, AllPengs, PengArray) && (CheckBoard(PengArray[idRow][1], PengArray[idRow][2]+1, NumOfCols, FishArray) == 3 || CheckBoard(PengArray[idRow][1], PengArray[idRow][2]+1, NumOfCols, FishArray) == 2)) {
                d = 2; //East
            } else if(CheckMove(idRow, 3, s, NumOfCols, FishArray, AllPengs, PengArray) && (CheckBoard(PengArray[idRow][1]+1, PengArray[idRow][2]+1, NumOfCols, FishArray) == 3 || CheckBoard(PengArray[idRow][1]+1, PengArray[idRow][2]+1, NumOfCols, FishArray) == 2)) {
                d = 3; //South-East
            } else {
                for(i = 4; i <= 6; i++) {
                    if(CheckMove(idRow, i, s, NumOfCols, FishArray, AllPengs, PengArray))
                        return i;
                }
                for(i = 1; i <= 3; i++) {
                    if(CheckMove(idRow, i, s, NumOfCols, FishArray, AllPengs, PengArray))
                        return i;
                }
            }
        } else {
            if(CheckMove(idRow, 4, s, NumOfCols, FishArray, AllPengs, PengArray) && (CheckBoard(PengArray[idRow][1]+1, PengArray[idRow][2]-1, NumOfCols, FishArray) == 3 || CheckBoard(PengArray[idRow][1]+1, PengArray[idRow][2]-1, NumOfCols, FishArray) == 2)) {
                d = 4; //South-West
            } else if(CheckMove(idRow, 5, s, NumOfCols, FishArray, AllPengs, PengArray) && (CheckBoard(PengArray[idRow][1], PengArray[idRow][2]-1, NumOfCols, FishArray) == 3 || CheckBoard(PengArray[idRow][1], PengArray[idRow][2]-1, NumOfCols, FishArray) == 2)) {
                d = 5; //West
            } else if(CheckMove(idRow, 6, s, NumOfCols, FishArray, AllPengs, PengArray) && (CheckBoard(PengArray[idRow][1]-1, PengArray[idRow][2]-1, NumOfCols, FishArray) == 3 || CheckBoard(PengArray[idRow][1]-1, PengArray[idRow][2]-1, NumOfCols, FishArray) == 2)) {
                d = 6; //North-West
            } else if(CheckMove(idRow, 1, s, NumOfCols, FishArray, AllPengs, PengArray) && (CheckBoard(PengArray[idRow][1]-1, PengArray[idRow][2], NumOfCols, FishArray) == 3 || CheckBoard(PengArray[idRow][1]-1, PengArray[idRow][2], NumOfCols, FishArray) == 2)) {
                d = 1; //North-East
            } else if(CheckMove(idRow, 2, s, NumOfCols, FishArray, AllPengs, PengArray) && (CheckBoard(PengArray[idRow][1], PengArray[idRow][2]+1, NumOfCols, FishArray) == 3 || CheckBoard(PengArray[idRow][1], PengArray[idRow][2]+1, NumOfCols, FishArray) == 2)) {
                d = 2; //East
            } else if(CheckMove(idRow, 3, s, NumOfCols, FishArray, AllPengs, PengArray) && (CheckBoard(PengArray[idRow][1]+1, PengArray[idRow][2], NumOfCols, FishArray) == 3 || CheckBoard(PengArray[idRow][1]+1, PengArray[idRow][2], NumOfCols, FishArray) == 2)) {
                d = 3; //South-East
            }else {
                for(i = 4; i <= 6; i++) {
                    if(CheckMove(idRow, i, s, NumOfCols, FishArray, AllPengs, PengArray))
                        return i;
                }
                for(i = 1; i <= 3; i++) {
                    if(CheckMove(idRow, i, s, NumOfCols, FishArray, AllPengs, PengArray))
                        return i;
                }
            }
        }
    } else { //Check which penguin we should be moving
        turn = 0; //Set the counter to 1 so next time we'll have another moveset for the other penguin
        if(PengArray[idRow][1] % 2) { //Check if the current row the penguin is on is odd or even
            //Decide movement direction 1-6, preference on floes with 3 or 2 fishes on them
            if(CheckMove(idRow, 2, s, NumOfCols, FishArray, AllPengs, PengArray) && (CheckBoard(PengArray[idRow][1], PengArray[idRow][2]+1, NumOfCols, FishArray) == 3 || CheckBoard(PengArray[idRow][1], PengArray[idRow][2]+1, NumOfCols, FishArray) == 2)) {
                d = 2; //East
            } else if(CheckMove(idRow, 3, s, NumOfCols, FishArray, AllPengs, PengArray) && (CheckBoard(PengArray[idRow][1]+1, PengArray[idRow][2]+1, NumOfCols, FishArray) == 3 || CheckBoard(PengArray[idRow][1]+1, PengArray[idRow][2]+1, NumOfCols, FishArray) == 2)) {
                d = 3; //South-East
            } else if(CheckMove(idRow, 4, s, NumOfCols, FishArray, AllPengs, PengArray) && (CheckBoard(PengArray[idRow][1]+1, PengArray[idRow][2], NumOfCols, FishArray) == 3 || CheckBoard(PengArray[idRow][1]+1, PengArray[idRow][2], NumOfCols, FishArray) == 2)) {
                d = 4; //South-West
            } else if(CheckMove(idRow, 5, s, NumOfCols, FishArray, AllPengs, PengArray) && (CheckBoard(PengArray[idRow][1], PengArray[idRow][2]-1, NumOfCols, FishArray) == 3 || CheckBoard(PengArray[idRow][1], PengArray[idRow][2]-1, NumOfCols, FishArray) == 2)) {
                d = 5; //West
            } else if(CheckMove(idRow, 6, s, NumOfCols, FishArray, AllPengs, PengArray) && (CheckBoard(PengArray[idRow][1]-1, PengArray[idRow][2], NumOfCols, FishArray) == 3 || CheckBoard(PengArray[idRow][1]-1, PengArray[idRow][2], NumOfCols, FishArray) == 2)) {
                d = 6; //North-West
            } else if(CheckMove(idRow, 1, s, NumOfCols, FishArray, AllPengs, PengArray) && (CheckBoard(PengArray[idRow][1]-1, PengArray[idRow][2]+1, NumOfCols, FishArray) == 3 || CheckBoard(PengArray[idRow][1]-1, PengArray[idRow][2]+1, NumOfCols, FishArray) == 2)) {
                d = 1; //North-East
            } else {
                for(i = 2; i <= 6; i++) {
                    if(CheckMove(idRow, i, s, NumOfCols, FishArray, AllPengs, PengArray))
                        return i;
                }
                return 1;
            }
        } else {
            //Decide movement direction 1-6, preference on floes with 3 or 2 fishes on them
            if(CheckMove(idRow, 2, s, NumOfCols, FishArray, AllPengs, PengArray) && (CheckBoard(PengArray[idRow][1], PengArray[idRow][2]+1, NumOfCols, FishArray) == 3 || CheckBoard(PengArray[idRow][1], PengArray[idRow][2]+1, NumOfCols, FishArray) == 2)) {
                d = 2; //East
            } else if(CheckMove(idRow, 3, s, NumOfCols, FishArray, AllPengs, PengArray) && (CheckBoard(PengArray[idRow][1]+1, PengArray[idRow][2], NumOfCols, FishArray) == 3 || CheckBoard(PengArray[idRow][1]+1, PengArray[idRow][2], NumOfCols, FishArray) == 2)) {
                d = 3; //South-East
            } else if(CheckMove(idRow, 4, s, NumOfCols, FishArray, AllPengs, PengArray) && (CheckBoard(PengArray[idRow][1]+1, PengArray[idRow][2]-1, NumOfCols, FishArray) == 3 || CheckBoard(PengArray[idRow][1]+1, PengArray[idRow][2]-1, NumOfCols, FishArray) == 2)) {
                d = 4; //South-West
            } else if(CheckMove(idRow, 5, s, NumOfCols, FishArray, AllPengs, PengArray) && (CheckBoard(PengArray[idRow][1], PengArray[idRow][2]-1, NumOfCols, FishArray) == 3 || CheckBoard(PengArray[idRow][1], PengArray[idRow][2]-1, NumOfCols, FishArray) == 2)) {
                d = 5; //West
            } else if(CheckMove(idRow, 6, s, NumOfCols, FishArray, AllPengs, PengArray) && (CheckBoard(PengArray[idRow][1]-1, PengArray[idRow][2]-1, NumOfCols, FishArray) == 3 || CheckBoard(PengArray[idRow][1]-1, PengArray[idRow][2]-1, NumOfCols, FishArray) == 2)) {
                d = 6; //North-West
            } else if(CheckMove(idRow, 1, s, NumOfCols, FishArray, AllPengs, PengArray) && (CheckBoard(PengArray[idRow][1]-1, PengArray[idRow][2], NumOfCols, FishArray) == 3 || CheckBoard(PengArray[idRow][1]-1, PengArray[idRow][2], NumOfCols, FishArray) == 2)) {
                d = 1; //North-East
            } else {
                for(i = 2; i <= 6; i++) {
                    if(CheckMove(idRow, i, s, NumOfCols, FishArray, AllPengs, PengArray))
                        return i;
                }
                return 1;
            }
        }
    }

    return d;
}

int TeemuPengID(int s, int PID, int a, int NumOfRows, int NumOfCols, int FishArray[NumOfRows][NumOfCols], int AllPengs, int PengArray[AllPengs][3]) {

    int i, j;
    static int turn = 0;

    if(a % 2) { //If the algorithm is player1
        if(AllPengs == 2) {
            return 1; //With only 1 penguin for each the penguin ID for player1 will be 1
        }
        for(i = turn; i < AllPengs; i++) {
            if(i % 2 == 0) { //Player1 can use even penguins on even rows
                for(j = 1; j <= 6; j++) {
                    if(CheckMove(i, j, 1, NumOfCols, FishArray, AllPengs, PengArray)) {
                        if(turn < AllPengs-2)
                            turn += 2; //Turn counter to alternate between penguins
                        else
                            turn = 0;
                        return ++i; //Return the penguin ID, which is array row number + 1
                    }
                }
            }
        }
        for(i = turn; i > -1; i--) {
            if(i % 2 == 0) { //Player1 can use even penguins on even rows
                for(j = 1; j <= 6; j++) {
                    if(CheckMove(i, j, 1, NumOfCols, FishArray, AllPengs, PengArray)) {
                        if(turn < AllPengs-2)
                            turn += 2; //Turn counter to alternate between penguins
                        else
                            turn = 0;
                        return ++i; //Return the penguin ID, which is array row number + 1
                    }
                }
            }
        }
    }else if(a % 2 == 0) { //If the algorithm is player2
        if(AllPengs == 2) {
            return 2; //With only 1 penguin for each the penguin ID for player2 will be 2
        }
        for(i = turn; i < AllPengs; i++) {
            if(i % 2) { //Player2 can use even penguins on odd rows
                for(j = 1; j <= 6; j++) {
                    if(CheckMove(i, j, 1, NumOfCols, FishArray, AllPengs, PengArray)) {
                        if(turn < AllPengs-1)
                            turn += 2; //Turn counter to alternate between penguins
                        else
                            turn = 1;
                        return ++i; //Return the penguin ID, which is array row number + 1
                    }
                }
            }
        }
        for(i = turn; i > -1; i--) {
            if(i % 2) { //Player2 can use even penguins on odd rows
                for(j = 1; j <= 6; j++) {
                    if(CheckMove(i, j, 1, NumOfCols, FishArray, AllPengs, PengArray)) {
                        if(turn < AllPengs-1)
                            turn += 2; //Turn counter to alternate between penguins
                        else
                            turn = 1;
                        return ++i; //Return the penguin ID, which is array row number + 1
                    }
                }
            }
        }
    }
    return 0;
}
