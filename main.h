#include <time.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <conio.h>

#ifdef __unix__
# include <unistd.h>
#elif defined _WIN32
# include <windows.h>
#endif

#define MAX_LEN 128 // for ascii printing

//Preaparation for pointers to functions
typedef int (*Pointer)(int s, int PID, int a, int NumOfRows, int NumOfCols, int FishArray[NumOfRows][NumOfCols], int AllPengs, int PengArray[AllPengs][3]);

//Commands for user//
//Manual//
int UserEnterDir(int s, int PID, int a, int NumOfRows, int NumOfCols, int FishArray[NumOfRows][NumOfCols], int AllPengs, int PengArray[AllPengs][3]);
int UserEnterCrdY(int s, int PID, int a, int NumOfRows, int NumOfCols, int FishArray[NumOfRows][NumOfCols], int AllPengs, int PengArray[AllPengs][3]);
int UserEnterCrdX(int s, int PID, int a, int NumOfRows, int NumOfCols, int FishArray[NumOfRows][NumOfCols], int AllPengs, int PengArray[AllPengs][3]);
int UserEnterNumOfSpaces(int s, int PID, int a, int NumOfRows, int NumOfCols, int FishArray[NumOfRows][NumOfCols], int AllPengs, int PengArray[AllPengs][3]);
int UserEnterPengID(int s, int PID, int a, int NumOfRows, int NumOfCols, int FishArray[NumOfRows][NumOfCols], int AllPengs, int PengArray[AllPengs][3]);

//Dummy
int DummyDir(int s, int PID, int a, int NumOfRows, int NumOfCols, int FishArray[NumOfRows][NumOfCols], int AllPengs, int PengArray[AllPengs][3]);
int DummyCrdX(int s, int PID, int a, int NumOfRows, int NumOfCols, int FishArray[NumOfRows][NumOfCols], int AllPengs, int PengArray[AllPengs][3]);
int DummyCrdY(int s, int PID, int a, int NumOfRows, int NumOfCols, int FishArray[NumOfRows][NumOfCols], int AllPengs, int PengArray[AllPengs][3]);
int DummySpaces(int s, int PID, int a, int NumOfRows, int NumOfCols, int FishArray[NumOfRows][NumOfCols], int AllPengs, int PengArray[AllPengs][3]);
int DummyPengID(int s, int PID, int a, int NumOfRows, int NumOfCols, int FishArray[NumOfRows][NumOfCols], int AllPengs, int PengArray[AllPengs][3]);

//Center
int CenterDir(int s, int PID, int a, int NumOfRows, int NumOfCols, int FishArray[NumOfRows][NumOfCols], int AllPengs, int PengArray[AllPengs][3]);
int CenterCrdX(int s, int PID, int a, int NumOfRows, int NumOfCols, int FishArray[NumOfRows][NumOfCols], int AllPengs, int PengArray[AllPengs][3]);
int CenterCrdY(int s, int PID, int a, int NumOfRows, int NumOfCols, int FishArray[NumOfRows][NumOfCols], int AllPengs, int PengArray[AllPengs][3]);
int CenterSpaces(int s, int PID, int a, int NumOfRows, int NumOfCols, int FishArray[NumOfRows][NumOfCols], int AllPengs, int PengArray[AllPengs][3]);
int CenterPengID(int s, int PID, int a, int NumOfRows, int NumOfCols, int FishArray[NumOfRows][NumOfCols], int AllPengs, int PengArray[AllPengs][3]);

//Functions from Julian Tian's algorithm
int JulianDir(int s, int PID, int a, int NumOfRows, int NumOfCols, int FishArray[NumOfRows][NumOfCols], int AllPengs, int PengArray[AllPengs][3]);
int JulianCrdX(int s, int PID, int a, int NumOfRows, int NumOfCols, int FishArray[NumOfRows][NumOfCols], int AllPengs, int PengArray[AllPengs][3]);
int JulianCrdY(int s, int PID, int a, int NumOfRows, int NumOfCols, int FishArray[NumOfRows][NumOfCols], int AllPengs, int PengArray[AllPengs][3]);
int JulianSpaces(int s, int PID, int a, int NumOfRows, int NumOfCols, int FishArray[NumOfRows][NumOfCols], int AllPengs, int PengArray[AllPengs][3]);
int JulianPengID(int s, int PID, int a, int NumOfRows, int NumOfCols, int FishArray[NumOfRows][NumOfCols], int AllPengs, int PengArray[AllPengs][3]);

//Photon - Michal Bator
int PhotonCrdX(int Spaces, int PengID, int Turn, int NumberOfRows, int NumberOfColumns, int FishArray[NumberOfRows][NumberOfColumns], int AllPengs, int PengArray[AllPengs][3]);
int PhotonCrdY(int Spaces, int PengID, int Turn, int NumberOfRows, int NumberOfColumns, int FishArray[NumberOfRows][NumberOfColumns], int AllPengs, int PengArray[AllPengs][3]);
int PhotonSpaces(int Spaces, int PengID, const int Turn, const int NumberOfRows, const int NumberOfColumns, const int FishArray[NumberOfRows][NumberOfColumns], const int AllPengs, int PengArray[AllPengs][3]);
int PhotonDir(int Spaces, int PengID, const int Turn, const int NumberOfRows, const int NumberOfColumns, const int FishArray[NumberOfRows][NumberOfColumns], const int AllPengs, int PengArray[AllPengs][3]);
int PhotonPengID(int Spaces, int PengID, const int Turn, const int NumberOfRows, const int NumberOfColumns, const int FishArray[NumberOfRows][NumberOfColumns], const int AllPengs, int PengArray[AllPengs][3]);

//Katja's Simple Random
int KatjaRandomDir(int s, int PID, int a, int NumOfRows, int NumOfCols, int FishArray[NumOfRows][NumOfCols], int AllPengs, int PengArray[AllPengs][3]);
int KatjaRandomCrdX(int s, int PID, int a, int NumOfRows, int NumOfCols, int FishArray[NumOfRows][NumOfCols], int AllPengs, int PengArray[AllPengs][3]);
int KatjaRandomCrdY(int s, int PID, int a, int NumOfRows, int NumOfCols, int FishArray[NumOfRows][NumOfCols], int AllPengs, int PengArray[AllPengs][3]);
int KatjaRandomSpaces(int s, int PID, int a, int NumOfRows, int NumOfCols, int FishArray[NumOfRows][NumOfCols], int AllPengs, int PengArray[AllPengs][3]);
int KatjaRandomPengID(int s, int PID, int a, int NumOfRows, int NumOfCols, int FishArray[NumOfRows][NumOfCols], int AllPengs, int PengArray[AllPengs][3]);

// Fatoumata Bocar
int FBCrdX(int s, int PID, int a, int NumOfRows, int NumOfCols, int FishArray[NumOfRows][NumOfCols], int AllPengs, int PengArray[AllPengs][3]);
int FBCrdX(int s, int PID, int a, int NumOfRows, int NumOfCols, int FishArray[NumOfRows][NumOfCols], int AllPengs, int PengArray[AllPengs][3]);
int FBCrdY(int s, int PID, int a, int NumOfRows, int NumOfCols, int FishArray[NumOfRows][NumOfCols], int AllPengs, int PengArray[AllPengs][3]);
int FBSpaces(int s, int PID, int a, int NumOfRows, int NumOfCols, int FishArray[NumOfRows][NumOfCols], int AllPengs, int PengArray[AllPengs][3]);
int FBDir(int s, int PID, int a, int NumOfRows, int NumOfCols, int FishArray[NumOfRows][NumOfCols], int AllPengs, int PengArray[AllPengs][3]);
int FBPengID(int s, int PID, int a, int NumOfRows, int NumOfCols, int FishArray[NumOfRows][NumOfCols], int AllPengs, int PengArray[AllPengs][3]);

//Michal Adamiec
int AdamiecCrdX(int s, int PID, int a, int NumOfRows, int NumOfCols, int FishArray[NumOfRows][NumOfCols], int AllPengs, int PengArray[AllPengs][3]);
int AdamiecCrdY(int s, int PID, int a, int NumOfRows, int NumOfCols, int FishArray[NumOfRows][NumOfCols], int AllPengs, int PengArray[AllPengs][3]);
int AdamiecSpaces(int s, int PID, int a, int NumOfRows, int NumOfCols, int FishArray[NumOfRows][NumOfCols], int AllPengs, int PengArray[AllPengs][3]);
int AdamiecDir(int s, int PID, int a, int NumOfRows, int NumOfCols, int FishArray[NumOfRows][NumOfCols], int AllPengs, int PengArray[AllPengs][3]);
int AdamiecPengID(int s, int PID, int a, int NumOfRows, int NumOfCols, int FishArray[NumOfRows][NumOfCols], int AllPengs, int PengArray[AllPengs][3]);

//Mateusz Roszkowski
int RandomX(int s, int PID, int a, int NumOfRows, int NumOfCols, int FishArray[NumOfRows][NumOfCols], int AllPengs, int PengArray[AllPengs][3]);
int RandomY(int s, int PID, int a, int NumOfRows, int NumOfCols, int FishArray[NumOfRows][NumOfCols], int AllPengs, int PengArray[AllPengs][3]);
int RandomSpaces(int s, int PID, int a, int NumOfRows, int NumOfCols, int FishArray[NumOfRows][NumOfCols], int AllPengs, int PengArray[AllPengs][3]);
int RandomDir(int s, int PID, int a, int NumOfRows, int NumOfCols, int FishArray[NumOfRows][NumOfCols], int AllPengs, int PengArray[AllPengs][3]);
int RandomPengID(int s, int PID, int a, int NumOfRows, int NumOfCols, int FishArray[NumOfRows][NumOfCols], int AllPengs, int PengArray[AllPengs][3]);

//Boiev
/*
int MikebotXcord(int a, int PID, int b, int NumOfCols, int NumOfCols,int FishArray[NumOfRows][NumOfCols],int Allpengs, int PengArray[AllPengs][3]);
int MikebotYcord(int a, int PID, int b, int NumOfCols, int NumOfCols,int FishArray[NumOfRows][NumOfCols],int Allpengs, int PengArray[AllPengs][3]);
int MikeRandDirrection(int a, int PID, int b, int NumOfRows, int NumOfCols, int Fisharray[NumofRows][NumOfCols],int AllPengs,int Pengarray[AllPengs][3]);
int MikeRandpeng(int a, int PID,int b, int NumOfRows, int NumOfCols, int FishArray[NumOfRows][NumOfCols], int AllPengs,int PengArray[Allpengs][3]);
int NumberofSpaces(int a, int PID,int b, int NumOfRows, int NumOfCols, int FishArray[NumOfRows][NumOfCols], int AllPengs,int PengArray[Allpengs][3]);
*/















//Functions from Teemu Virkkala's algorithm
int TeemuDir(int s, int PID, int a, int NumOfRows, int NumOfCols, int FishArray[NumOfRows][NumOfCols], int AllPengs, int PengArray[AllPengs][3]);
int TeemuCrdX(int s, int PID, int a, int NumOfRows, int NumOfCols, int FishArray[NumOfRows][NumOfCols], int AllPengs, int PengArray[AllPengs][3]);
int TeemuCrdY(int s, int PID, int a, int NumOfRows, int NumOfCols, int FishArray[NumOfRows][NumOfCols], int AllPengs, int PengArray[AllPengs][3]);
int TeemuSpaces(int s, int PID, int a, int NumOfRows, int NumOfCols, int FishArray[NumOfRows][NumOfCols], int AllPengs, int PengArray[AllPengs][3]);
int TeemuPengID(int s, int PID, int a, int NumOfRows, int NumOfCols, int FishArray[NumOfRows][NumOfCols], int AllPengs, int PengArray[AllPengs][3]);

//BasicFunctions
void MapGenerator(int NumOfRows, int NumOfCols, int FishArray[NumOfRows][NumOfCols]);
void Cleaner(int iLimit, int jLimit, int Array[iLimit][jLimit]);
void PrintCoords(int, int, int AllPengs, int PengArray[AllPengs][3]);
void DrawBoard(int NumOfRows, int NumOfCols, int FishArray[NumOfRows][NumOfCols], int AllPengs, int PengArray[AllPengs][3]);
int PosPeng(int, int, int NumOfRows, int NumOfCols, int FishArray[NumOfRows][NumOfCols], int AllPengs, int PengArray[AllPengs][3]);
void Intro();
void AsciiArtPrinter(FILE *fptr);
void PlayGame(Pointer PointerX1, Pointer PointerY1, Pointer PointerDir1, Pointer PointerSpaces1, Pointer PointerPengID1, Pointer PointerX2, Pointer PointerY2, Pointer PointerDir2, Pointer PointerSpaces2, Pointer PointerPengID2);
int Score (int, int, int, int NumOfCols, int FishArray[][NumOfCols]);

//Saving and Loading
int SaveBoard(const char *file, int i, int NumOfRows, int NumOfCols, int FishArray[NumOfRows][NumOfCols], int AllPengs, int PengArray[AllPengs][3], int, int);
int LoadBoard(const char *file, int NumOfRows, int NumOfCols, int FishArray[NumOfRows][NumOfCols], int AllPengs, int PengArray[AllPengs][3]);
int LoadRoC(const char *file, int);
int FileExists(const char *file);

//CheckingFunctions
int CheckPeng(int, int, int AllPengs, int PengArray[AllPengs][3]);
int CheckBoard(int, int, int NumOfCols, int FishArray[][NumOfCols]);
int CheckTile(int, int, int NumOfCols, int FishArray[][NumOfCols], int AllPengs, int PengArray[AllPengs][3]);
int CheckMove(int, int, int, int NumOfCols, int FishArray[][NumOfCols], int AllPengs, int PengArray[AllPengs][3]);
int CheckEnd(int a, int NumOfCols, int FishArray[][NumOfCols], int AllPengs, int PengArray[AllPengs][3]);
int WhichPenguin(int, int AllPengs, int PengArray[AllPengs][3]);

//Movement function prototypes
int MovePengNE(int, int, int NumOfRows, int NumOfCols, int FishArray[NumOfRows][NumOfCols], int AllPengs, int PengArray[AllPengs][3]);
int MovePengE(int, int, int NumOfRows, int NumOfCols, int FishArray[NumOfRows][NumOfCols], int AllPengs, int PengArray[AllPengs][3]);
int MovePengSE(int, int, int NumOfRows, int NumOfCols, int FishArray[NumOfRows][NumOfCols], int AllPengs, int PengArray[AllPengs][3]);
int MovePengSW(int, int, int NumOfRows, int NumOfCols, int FishArray[NumOfRows][NumOfCols], int AllPengs, int PengArray[AllPengs][3]);
int MovePengW(int, int, int NumOfRows, int NumOfCols, int FishArray[NumOfRows][NumOfCols], int AllPengs, int PengArray[AllPengs][3]);
int MovePengNW(int, int, int NumOfRows, int NumOfCols, int FishArray[NumOfRows][NumOfCols], int AllPengs, int PengArray[AllPengs][3]);
